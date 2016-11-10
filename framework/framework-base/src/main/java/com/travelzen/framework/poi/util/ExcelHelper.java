package com.travelzen.framework.poi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel助手类
 *
 */
public class ExcelHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelHelper.class);
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat DATE_FORMAT_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static ExcelHelper helper = null;

	private ExcelHelper() {
	}

	public static synchronized ExcelHelper getInstanse() {
		if (helper == null) {
			helper = new ExcelHelper();
		}
		return helper;
	}

	public <T> void exportToOS(OutputStream os, ExcelHead head, List<T> list, Class<T> cls) throws Exception {
		try {
			HSSFWorkbook workbook = exportToHSSFWorkbook("sheet", head, list, cls);
			workbook.write(os);
			os.flush();
		} finally {
			if(os != null){
				os.close();
			}
		}
	}

	public <T> File exportFile(List<T> refundList, ExcelHead head,
			Class<T> clazz) {
		FileOutputStream os = null;
		try {
			File file = File.createTempFile("exportdata",".xls");
			os = new FileOutputStream(file);
			ExcelHelper.getInstanse().exportToOS(os, head, refundList, clazz);
			return file;
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	public <T> HSSFWorkbook generateHSSFWorkbook(ExcelHead head,List<T> list,Class<T> cls) throws  Exception{
		return exportToHSSFWorkbook("sheet", head, list, cls);
	}

	public <T> HSSFWorkbook exportToHSSFWorkbook(String sheetName,ExcelHead head,List<T> list,Class<T> cls) throws  Exception{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0,sheetName);

		//创建表头
		List<ExcelColumn> columns = head.getColumns();
		HSSFRow row = sheet.createRow(0);
		for (int i=0;i<columns.size();i++) {
			ExcelColumn column = columns.get(i);
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(column.getFieldDispName());
		}

		//获得数据
		for (int i=0;i<list.size();i++) {
			row = sheet.createRow(i+head.getColumnCount());
			T t = list.get(i);
			for (int j=0;j<columns.size();j++) {
				ExcelColumn column = columns.get(j);
				String fieldName = column.getFieldName();
				Field field = getField(cls, fieldName);
				field.setAccessible(true);
				Object value = field.get(t);
				HSSFCell cell = row.createCell(j);

				value = convertValueByType(value,column.getType());

				//转换值
				String valueStr = convertValue(fieldName, value, head.getColumnsConvertMap());
				if(value instanceof Float) {
                    cell.setCellValue(Float.valueOf(valueStr));
                } else if (value instanceof Integer) {
                    cell.setCellValue(Integer.valueOf(valueStr));
                } else if (value instanceof Double) {
                    cell.setCellValue(Double.valueOf(valueStr));
                } else if (value instanceof Long) {
                    cell.setCellValue(Long.valueOf(valueStr));
                } else {
                    cell.setCellValue(valueStr);
                }
			}
		}
		return workbook;
	}

	private static <T> Field getField(Class<T> cls, String fieldName) throws NoSuchFieldException {
		if (cls.getName().equals("java.lang.Object")) {
			throw new NoSuchFieldException(fieldName);
		}
		Field field = null;
		try {
			field = cls.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			field = getField(cls.getSuperclass(), fieldName);
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return field;
	}

	private Object convertValueByType(Object value, String type) {
		if (StringUtils.isBlank(type) || value == null) {
			return value;
		}
		if ("money".equals(type)) {
			if (value instanceof Long) {
				value = (Long) value / 100d;
			} else {
				try {
					value = Long.parseLong(value.toString()) / 100d;
				} catch (NumberFormatException e) {
					return value;
				}
			}
			value = String.format("%.2f", value);
		} else if ("time".equals(type)) {
			if (value instanceof Date) {
				value = DATE_FORMAT_YYYYMMDDHHMMSS.format(value);
			}
		} else if (value instanceof Date) {
			value = DATE_FORMAT.format(value);
		}else if("boolean".equals(type)){
			value = ((Boolean)value) ? "是" : "否";
		}else if("double".equals(type)){
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(false);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(0);
			nf.setParseIntegerOnly(false);
			nf.setRoundingMode(RoundingMode.HALF_UP);
			value = nf.format(value);
		}
		return value;
	}

	@SuppressWarnings("rawtypes")
	private String convertValue(String fieldName, Object value, Map<String, Map> convertMap) {
		Map valueMap = convertMap.get(fieldName);
		Object returnValue = null;
		if (valueMap == null || valueMap.isEmpty()) {
			returnValue = value;
		} else {
			returnValue = valueMap.get(value) == null ? value : valueMap.get(value);
		}
		String valueStr = returnValue == null ? "" : returnValue.toString();
		return valueStr;
	}


	/**
	 * 将Excel文件导入到list对象
	 *
	 * @param head
	 *            文件头信息
	 * @param file
	 *            导入的数据源
	 * @param cls
	 *            保存当前数据的对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List importToObjectList(ExcelHead head, File file, Class cls) {
		return importToObjectList(head, file, cls, 0);
	}
	/**
	 * 将Excel文件导入到list对象
	 *
	 * @param head
	 *            文件头信息
	 * @param file
	 *            导入的数据源
	 * @param cls
	 *            保存当前数据的对象
	 * @param sheetIndex
	 *            导入的sheet索引，从0开始
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List importToObjectList(ExcelHead head, File file, Class cls, int sheetIndex) {
		List contents = null;
		FileInputStream fis;
		// 根据excel生成list类型的数据
		List<List> rows;
		try {
			fis = new FileInputStream(file);
			rows = excelFileConvertToList(fis, sheetIndex);

			// 删除头信息
			for (int i = 0; i < head.getRowCount(); i++) {
				rows.remove(0);
			}

			// 将表结构转换成Map
			Map<Integer, String> excelHeadMap = convertExcelHeadToMap(head.getColumns());
			// 构建为对象
			contents = buildDataObject(excelHeadMap, head.getColumnsConvertMap(), rows, cls);
		} catch (FileNotFoundException ex) {
			LOGGER.error(ex.getMessage(), ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return contents;
	}

	/**
	 * 将报表结构转换成Map
	 *
	 * @param excelColumns
	 */
	private Map<Integer, String> convertExcelHeadToMap(List<ExcelColumn> excelColumns) {
		Map<Integer, String> excelHeadMap = new HashMap<Integer, String>();
		for (ExcelColumn excelColumn : excelColumns) {
			if (StringUtils.isEmpty(excelColumn.getFieldName())) {
				continue;
			} else {
				excelHeadMap.put(excelColumn.getIndex(), excelColumn.getFieldName());
			}
		}
		return excelHeadMap;
	}

	/**
	 * 将Excel文件内容转换为List对象
	 *
	 * @param fis
	 *            excel文件
	 * @return List<List> list存放形式的内容
	 * @throws Exception
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public List<List> excelFileConvertToList(InputStream fis) throws Exception{
		return excelFileConvertToList(fis, 0);
	}
	/**
	 * 将Excel文件内容转换为List对象
	 *
	 * @param fis
	 *            excel文件
	 * @param sheetIndex
	 *            sheet索引
	 * @return List<List> list存放形式的内容
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public List<List> excelFileConvertToList(InputStream fis, int sheetIndex) throws Exception {
		Workbook wb = WorkbookFactory.create(fis);

		int sheetNum = wb.getNumberOfSheets();
		if(sheetIndex<0 || sheetIndex>=sheetNum){
			LOGGER.error("sheet index error", new IndexOutOfBoundsException("sheet index error, index: " + sheetIndex));
			throw new IndexOutOfBoundsException("sheet index error, index: " + sheetIndex);
		}
		Sheet sheet = wb.getSheetAt(sheetIndex);

		List<List> rows = new ArrayList<List>();
		for (Row row : sheet) {
			List<Object> cells  = new ArrayList<>();
			for (Cell cell : row) {
				Object obj = null;

				// CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
				// CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex(), true, true);
				if(cells.size() <= cell.getColumnIndex()){
					int size = cells.size();
					for (int i = size; i < cell.getColumnIndex() + 1; i++) {
						cells.add(null);
					}
				}

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					obj = cell.getRichStringCellValue().getString();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						obj = cell.getDateCellValue();
					} else {
						obj = cell.getNumericCellValue();
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					obj = cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_FORMULA:
					obj = cell.getNumericCellValue();
					break;
				default:
					obj = null;
				}
				cells.add(cell.getColumnIndex(), obj);
			}
			if(cells != null){
				rows.add(cells);
			}
		}
		return rows;
	}

	/**
	 * 根据Excel生成数据对象
	 *
	 * @param excelHeadMap
	 *            表头信息
	 * @param excelHeadConvertMap
	 *            需要特殊转换的单元
	 * @param rows
	 * @param cls
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List buildDataObject(Map<Integer, String> excelHeadMap, Map<String, Map> excelHeadConvertMap, List<List> rows, Class cls) {
		List contents = new ArrayList();
		for (List list : rows) {
			// 如果当前第一列中无数据,则忽略当前行的数据
			if (list == null || list.get(0) == null) {
				break;
			}
			// 当前行的数据放入map中,生成<fieldName, value>的形式
			Map<String, Object> rowMap = rowListToMap(excelHeadMap, excelHeadConvertMap, list);

			// 将当前行转换成对应的对象
			Object obj = null;
			try {
				obj = cls.newInstance();
				BeanUtils.populate(obj, rowMap);
			} catch (InstantiationException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				LOGGER.error(e.getMessage(), e);
			}

			contents.add(obj);
		}
		return contents;
	}

	/**
	 * 将行转行成map,生成<fieldName, value>的形式
	 *
	 * @param excelHeadMap
	 *            表头信息
	 * @param excelHeadConvertMap
	 *            excelHeadConvertMap
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, Object> rowListToMap(Map<Integer, String> excelHeadMap, Map<String, Map> excelHeadConvertMap, List list) {
		Map<String, Object> rowMap = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			String fieldName = excelHeadMap.get(i);
			// 存在所定义的列
			if (fieldName != null) {
				Object value = list.get(i);
				if (excelHeadConvertMap != null && excelHeadConvertMap.get(fieldName) != null) {
					value = excelHeadConvertMap.get(fieldName).get(value);
				}
				rowMap.put(fieldName, value);
			}
		}
		return rowMap;
	}
}
