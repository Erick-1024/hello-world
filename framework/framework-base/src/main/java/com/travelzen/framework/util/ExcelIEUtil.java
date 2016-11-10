/**
* author: Simon Lee
* Date  : Aug 28, 2013
*/
package com.travelzen.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Lists;

public class ExcelIEUtil {

	private static Logger logger = LoggerFactory.getLogger(ExcelIEUtil.class);

	private static int size = 1000;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void export(Map<String, String> fields, List<?> data, String fullName) {
		exportExcel(fields, data, fullName, size);
	}

	/**
	 * 导出数据到excel中，每个文件1000条数据，数据过多时，自动拆分文件
	 *
	 * @param fields
	 *            导出的表的表头
	 * @param data
	 *            需要导出的数据
	 * @param fullName
	 *            导出的文件全路径名
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static File exportExcel(Map<String, String> fields, List<?> data, String fullName, int fileSize) {
		try {
			Object fObj = data.get(0);
			Map<String, Field> fieldMap = new HashMap<>();
			getFieldMap(fObj.getClass(), fieldMap);

			// 拆分后的数据构成一个列表
			List list = new ArrayList();
			for (int i = 0; i * fileSize < data.size(); i++) {
				int end = (i + 1) * fileSize > data.size() ? data.size() - 1 : (i + 1) * fileSize;
				list.add(data.subList(i * fileSize, end));
			}

			// 拆分后的文件名列表
			List<String> fileName = new ArrayList<>();
			if (list.size() == 1) {
				fileName.add(fullName);
			} else {
				for (int i = 1; i <= list.size(); i++) {
					int dotIdx = fullName.lastIndexOf('.');
					if (dotIdx == -1) {
						System.out.println("文件名应该包含后缀！");
					} else if (dotIdx == 0) {
						fileName.add("_" + i + fullName.substring(dotIdx));
					} else {
						fileName.add(fullName.substring(0, dotIdx) + "_" + i + fullName.substring(dotIdx));
					}
				}
			}

			// 设置每个文件内容
			for (int i = 0; i < list.size(); i++) {
				List dataI = (List) list.get(i);
				Workbook workbook = new HSSFWorkbook();
				Sheet sheet = workbook.createSheet();
				Row row0 = sheet.createRow(0);
				Row row1 = sheet.createRow(1);
				int coll0Num = 0;
				int coll1Num = 0;
				for (Map.Entry<String, String> entry : fields.entrySet()) {
					Cell cell0 = row0.createCell(coll0Num++);
					Cell cell1 = row1.createCell(coll1Num++);
					cell0.setCellValue(entry.getKey());
					cell1.setCellValue(entry.getValue());
				}
				int rowNum = 2;
				for (Object obj : dataI) {
					Row row = sheet.createRow(rowNum++);
					int collNum = 0;
					for (String field : fields.keySet()) {
						Cell cell = row.createCell(collNum++);
						Field f = fieldMap.get(field);
						f.setAccessible(true);
						Object value = f.get(obj);
						if (f.getType() == Date.class) {
							cell.setCellValue(sdf.format(value));
						} else if (f.getType() == DateTime.class) {
							cell.setCellValue(((DateTime) value).toString("yyyy-MM-dd HH:mm:ss"));
						} else {
							cell.setCellValue(value.toString());
						}
					}
				}
				FileOutputStream outputStream = new FileOutputStream(fileName.get(i));
				workbook.write(outputStream);
				IOUtils.closeQuietly(outputStream);
			}
			if (fileName.size() == 1) {
				return new File(fileName.get(0));
			} else {
				List<File> files = new ArrayList<>();
				for (String fn : fileName) {
					files.add(new File(fn));
				}
				File result = GZIPUtil.compress(GZIPUtil.pack(files.toArray(new File[] {}), new File("/tmp/stu.tar")));
				for (File f : files) {
					f.delete();
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 导出数据到excel中，并返回对应的二进制数据
	 *
	 * @param fields
	 *            导出的表的表头
	 * @param data
	 *            需要导出的数据
	 */
	public static byte[] exportBytes(Map<String, String> fields, List<?> data) {
		try {
			Object fObj = data.get(0);
			Map<String, Field> fieldMap = new HashMap<>();
			getFieldMap(fObj.getClass(), fieldMap);

			// 设置每个文件内容
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet();
			Row headRow = sheet.createRow(0);
			int coll1Num = 0;
			for (Map.Entry<String, String> entry : fields.entrySet()) {
				Cell cell1 = headRow.createCell(coll1Num++);
				cell1.setCellValue(entry.getValue());
			}
			int rowNum = 1;
			for (Object obj : data) {
				Row row = sheet.createRow(rowNum++);
				int collNum = 0;
				for (String field : fields.keySet()) {
					Cell cell = row.createCell(collNum++);
					Field f = fieldMap.get(field);
					f.setAccessible(true);
					Object value = f.get(obj);
					if (value == null) {
						cell.setCellValue("");
					} else if (f.getType() == Date.class) {
						cell.setCellValue(sdf.format(value));
					} else if (f.getType() == DateTime.class) {
						cell.setCellValue(((DateTime) value).toString("yyyy-MM-dd HH:mm:ss"));
					} else {
						cell.setCellValue(value.toString());
					}
				}
			}
			ByteArrayOutputStream opStrm = new ByteArrayOutputStream();
			workbook.write(opStrm);
			return opStrm.toByteArray();
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	public static <T extends Object> List<T> importFromInputStream(Class<T> clz, InputStream input) throws Exception {
		Map<String, Field> fields = new HashMap<>();
		getFieldMap(clz, fields);
		Workbook workbook = new HSSFWorkbook(input);
		Sheet sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			logger.error("Nothing in the excel file at sheet[0]");
			return null;
		}
		Row row0 = sheet.getRow(0);
		List<Field> fieldList = new ArrayList<>();
		int i = 0;
		Cell cell0 = null;
		while ((cell0 = row0.getCell(i++)) != null && StringUtils.isNotBlank(cell0.getStringCellValue())) {
			String name = cell0.getStringCellValue();
			fieldList.add(fields.get(name));
		}
		int j = 2;
		Row rowI = sheet.getRow(j++);
		if (rowI == null) {
			logger.error("data should start at the 3rd row");
		}
		List<T> list = new LinkedList<>();
		while (rowI != null) {
			T obj = clz.newInstance();
			boolean notSet = true;
			int k = 0;
			Cell cellK = null;
			while (k < fieldList.size()) {
				cellK = rowI.getCell(k);
				if (cellK == null) {
					k++;
					continue;
				}
				Field field = fieldList.get(k);
				field.setAccessible(true);
				if (StringUtils.isNotBlank(cellK.getStringCellValue())) {
					String strValue = cellK.getStringCellValue();
					if (StringUtils.isBlank(strValue)) {
						continue;
					}
					strValue = strValue.trim();
					Object value = null;
					if (field.getType() == Integer.class) {
						value = Integer.parseInt(strValue);
					} else if (field.getType() == Double.class) {
						value = Double.parseDouble(strValue);
					} else if (field.getType() == Float.class) {
						value = Float.parseFloat(strValue);
					} else if (field.getType() == String.class) {
						value = strValue;
					} else if (field.getType() == Date.class) {
						if (strValue.matches("\\d+")) {
							value = new Date(Long.parseLong(strValue));
						} else {
							value = sdf.parse(strValue);
						}
					} else if (field.getType() == DateTime.class) {
						if (strValue.matches("\\d+")) {
							value = new DateTime(Long.parseLong(strValue));
						} else {
							value = DateTime.parse(strValue, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
						}
					} else if (field.getType() == Boolean.class) {
						value = Boolean.parseBoolean(strValue);
					}
					field.set(obj, value);
					notSet = false;
				}
				k++;
			}
			if (!notSet) {
				list.add(obj);
			}
			rowI = sheet.getRow(j++);
		}
		return list;
	}

	public static <T extends Object> List<T> importFromExcel(Class<T> clz, String fileName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		return importFromInputStream(clz, fis);
	}

	private static <T extends Object> void getFieldMap(Class<T> clz, Map<String, Field> result) {
		for (Field field : clz.getDeclaredFields()) {
			result.put(field.getName(), field);
		}
		if (clz.getSuperclass() != null) {
			getFieldMap(clz.getSuperclass(), result);
		}
	}

	/**
	 * 
	 * @param clz
	 * @param input
	 * @param fileName
	 * @param startIndex 从第几行开始映射
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> importFromExcelOnlyOneColumn(Class<T> clz, InputStream input,String fileName,int startIndex)
			throws IOException, ParseException {
		List<T> list = Lists.newArrayList();
		Workbook workbook = createWorkBook(input, fileName);
		Sheet sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			logger.error("Nothing in the excel file at sheet[0]");
			return null;
		}
		int count = startIndex;
		while (sheet.getRow(count) != null) {
			Cell cell = sheet.getRow(count).getCell(0);
			if (StringUtils.isNotBlank(cell.getStringCellValue())) {
				String strValue = cell.getStringCellValue();
				if (StringUtils.isBlank(strValue)) {
					break;
				}
				strValue = strValue.trim();
				Object value = null;
				if (clz == Integer.class) {
					value = Integer.parseInt(strValue);
				} else if (clz == Double.class) {
					value = Double.parseDouble(strValue);
				} else if (clz == Float.class) {
					value = Float.parseFloat(strValue);
				} else if (clz == String.class) {
					value = strValue;
				} else if (clz == Date.class) {
					if (strValue.matches("\\d+")) {
						value = new Date(Long.parseLong(strValue));
					} else {
						value = sdf.parse(strValue);
					}
				} else if (clz == DateTime.class) {
					if (strValue.matches("\\d+")) {
						value = new DateTime(Long.parseLong(strValue));
					} else {
						value = DateTime.parse(strValue, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
					}
				} else if (clz == Boolean.class) {
					value = Boolean.parseBoolean(strValue);
				}
				list.add((T) value);
			}
			count++;
		}
		return list;
	}
	
	/**
	 * 从导入的Excel的输入流中读取文件中的信息
	 * @param inputStream
	 * @param fileName Excel的文件名
	 * @param columnTotalNum 一共要读取的列数
	 * @param sheetNum 要读取的sheet编号
	 * @param skipRowNum 跳过前几行
	 * @return 返回 List<List<String>>
	 * @throws Exception
	 */
	public static List<List<String>> readFromInputStream(InputStream inputStream,String fileName,int columnTotalNum,int sheetNum, int skipRowNum)throws Exception
	{
		List<List<String>> listAll = new ArrayList<>();
		//根据文件名，判断是xls文件还是xlsx文件，返回对应的workbook
		Workbook workbook = ExcelIEUtil.createWorkBook(inputStream, fileName);
		if(workbook == null)
			return null;
		
		//获取某一个sheet
		Sheet sheet = workbook.getSheetAt(sheetNum);
		if (sheet == null) 
		{
			logger.error("Nothing in the excel file at sheet[" + sheetNum +"]");
			return null;
		}
		
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext())
		{
			List<String> rowList = new ArrayList<>();
			Row row = (Row)rows.next();
			//跳过前skipRowNum行
			if(row.getRowNum() < skipRowNum)
				continue;
			int nullCellNum = 0; //空单元格的数量
			for(int i = 0; i < columnTotalNum; i++)
			{
				Cell cell = row.getCell(i);
				if(null != cell)
				{
					switch (cell.getCellType())
					{
						case Cell.CELL_TYPE_NUMERIC: // 数字,说明：数字类型包含一般类型、日期格式、货币格式、百分比
							String style = cell.getCellStyle().getDataFormatString();
							double cellValue = cell.getNumericCellValue();
							String result = cellValue + "";
							result = handleNumericCell(cell,cellValue,result,style);//处理NUMERIC类型的单元格
							rowList.add(result);
                            break;  
                        case Cell.CELL_TYPE_STRING: // 字符串  
                            rowList.add(cell.getStringCellValue()); 
                            break;  
                        case Cell.CELL_TYPE_BOOLEAN: // Boolean  
                        	rowList.add(cell.getBooleanCellValue() + "");
                            break;  
                        case Cell.CELL_TYPE_FORMULA: // 公式  
                        	rowList.add(handleFormulaCell(cell));
                            break;  
                        case Cell.CELL_TYPE_BLANK: // 空值
                        	nullCellNum ++;
                        	rowList.add("");
                            break;
                        case Cell.CELL_TYPE_ERROR: // 故障 
                        	nullCellNum ++;
                        	rowList.add("");
                            break;  
                        default:
                        	nullCellNum ++;
                        	rowList.add("");
                            break;
					}
				}
				else
				{
					nullCellNum ++;
					rowList.add("");
				}
			}
			//如果空单元格的数量大于等于所读的单元格，则不加如改行rowList
			if(nullCellNum >= columnTotalNum)
				continue;
			listAll.add(rowList);
		}
		return listAll;
	}
	/*  * 判断是xls文件还是xlsx文件  */  
    public static Workbook createWorkBook(InputStream inputStream,String fileName) throws IOException
    {
		if (fileName.toLowerCase().endsWith("xls")) {
			try {
				return new HSSFWorkbook(inputStream);
			} catch (Exception e) {
				return new XSSFWorkbook(inputStream);
			}
		}
		if (fileName.toLowerCase().endsWith("xlsx")) {
			try {
				return new XSSFWorkbook(inputStream);
			} catch (Exception e) {
				return new HSSFWorkbook(inputStream);
			}
		}
		return null;
	} 
    
    /**
     * 对NUMERIC类型的单元格进行相应处理
     * 格式化 常规、货币、百分比、日期格式
     * @param cellValue 单元格的NUMERIC值
     * @param styleNum 单元格style
     * @param result 处理后的字符串值
     */
    private static String handleNumericCell(Cell cell,double cellValue,String result,String style)
    {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		result = cell.getStringCellValue();
		if("%".equals(style.substring(style.length()-1, style.length()))) {
			DecimalFormat format = new DecimalFormat(style.substring(0,style.length()));//设置百分比格式，保留小数点后以为
			result = format.format(cellValue);
		} else if(style.length() == 1 && "@".equals(style))
			result = new BigDecimal(cellValue).toPlainString();
		else if("@".equals(style.substring(style.length()-1, style.length()))) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//			SimpleDateFormat format = new SimpleDateFormat(style.substring(0,style.length()-2));//设置日期格式
			Date date = DateUtil.getJavaDate(cellValue);//将double类型的日期值转为Date类型
			result = format.format(date);
		}
		else if("#,##".equals(style.substring(0, 4))) {
			DecimalFormat format = new DecimalFormat(style.substring(4,style.length()));//设置货币格式
			result = format.format(cellValue);
		} else if("m/d/yy".equals(style))
			result = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getJavaDate(cellValue));
		return result;
    }
    
	/**
	 * 对公式(Formula)类型的单元格进行相应处理
	 * @param cell
	 * @return
	 */
	public static String handleFormulaCell(Cell cell)
	{
		String result = "";
		try
		{
			double cellValue = cell.getNumericCellValue();
			String style = cell.getCellStyle().getDataFormatString();
			if("#,##".equals(style.substring(0, 4)))
			{
				DecimalFormat format = new DecimalFormat(style.substring(4,style.length()));//设置货币格式
				result = format.format(cellValue);
			}
			else 
			{
				cell.setCellType(Cell.CELL_TYPE_STRING);
				result = cell.getStringCellValue();
			}
		} catch (Exception e)
		{
			cell.setCellType(Cell.CELL_TYPE_STRING);
			result = cell.getStringCellValue();
		}
		return result;
	}
}
