package com.cana.vbam.front.biz.utils;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.travelzen.framework.util.ExcelIEUtil;

public class AssetExcelIEUtil {

	private static Logger logger = LoggerFactory.getLogger(AssetExcelIEUtil.class);
	
	/**
	 * 从导入的Excel的输入流中读取文件中的信息
	 * @param inputStream
	 * @param fileName Excel的文件名
	 * @param columnTotalNum 一共要读取的列数
	 * @param sheetNum 要读取的sheet编号
	 * @param jumpRow 模板需要跳过的行数（从1开始）
	 * @return 返回 List<List<String>>
	 * @throws Exception
	 */
	public static List<List<String>> readFromInputStream(InputStream inputStream,String fileName,int columnTotalNum,int sheetNum,int jumpRow)throws Exception
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
			//跳过jumpRow行
			if(row.getRowNum() < jumpRow)
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
//							System.out.println("数字类型:"+style+"----value:"+result);
							result = handleNumericCell(cell,cellValue,result,style);//处理NUMERIC类型的单元格
//							System.out.println("数字类型处理后value:"+result);
							rowList.add(result);
                            break;  
                        case Cell.CELL_TYPE_STRING: // 字符串  
                            rowList.add(cell.getStringCellValue()); 
                            break;  
                        case Cell.CELL_TYPE_BOOLEAN: // Boolean  
                        	rowList.add(cell.getBooleanCellValue() + "");
                            break;  
                        case Cell.CELL_TYPE_FORMULA: // 公式  
//                        	System.out.println("公式类型:"+cell.getCellStyle().getDataFormatString()+"-----value:"+cell.getNumericCellValue()+"");
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

	/**
	 * 从导入的Excel的输入流中读取文件中的信息
	 * @param inputStream
	 * @param fileName Excel的文件名
	 * @param columnTotalNum 一共要读取的列数
	 * @param sheetNum 要读取的sheet编号
	 * @param jumpRow 模板需要跳过的行数（从1开始）
	 * @return 返回 List<List<String>>
	 * @throws Exception
	 */
	public static List<List<List<String>>> readFromInputStreamMulSheet(InputStream inputStream,String fileName,List<Integer> columnTotalNum,List<Integer> sheetNums,List<Integer> jumpRow)throws Exception
	{
		List<List<List<String>>> listAll = Lists.newArrayList();
		//根据文件名，判断是xls文件还是xlsx文件，返回对应的workbook
		Workbook workbook = ExcelIEUtil.createWorkBook(inputStream, fileName);
		if(workbook == null)
			return null;
		
		for(int j=0; j< sheetNums.size(); j++){
			List<List<String>> list = new ArrayList<>();
			//获取某一个sheet
			Sheet sheet = workbook.getSheetAt(sheetNums.get(j));
			if (sheet == null) 
			{
				logger.error("Nothing in the excel file at sheet[" + sheetNums.get(j) +"]");
				return null;
			}
			
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext())
			{
				List<String> rowList = new ArrayList<>();
				Row row = (Row)rows.next();
				//跳过jumpRow行
				if(row.getRowNum() < jumpRow.get(j))
					continue;
				int nullCellNum = 0; //空单元格的数量
				for(int i = 0; i < columnTotalNum.get(j); i++)
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
	//							System.out.println("数字类型:"+style+"----value:"+result);
								result = handleNumericCellForMulSheet(cell,cellValue,result,style);//处理NUMERIC类型的单元格
	//							System.out.println("数字类型处理后value:"+result);
								rowList.add(result);
	                            break;  
	                        case Cell.CELL_TYPE_STRING: // 字符串  
	                            rowList.add(cell.getStringCellValue()); 
	                            break;  
	                        case Cell.CELL_TYPE_BOOLEAN: // Boolean  
	                        	rowList.add(cell.getBooleanCellValue() + "");
	                            break;  
	                        case Cell.CELL_TYPE_FORMULA: // 公式  
	//                        	System.out.println("公式类型:"+cell.getCellStyle().getDataFormatString()+"-----value:"+cell.getNumericCellValue()+"");
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
				if(nullCellNum >= columnTotalNum.get(j))
					continue;
				list.add(rowList);
			}
			listAll.add(list);
		}
		return listAll;
	}
	
	private static String handleFormulaCell(Cell cell) {
		String result = "";
		try
		{
			double cellValue = cell.getNumericCellValue();
			String style = cell.getCellStyle().getDataFormatString();
			if(style.length()>=4 && "#,##".equals(style.substring(0, 4)))
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

	private static String handleNumericCell(Cell cell, double cellValue, String result, String style) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		result = cell.getStringCellValue();
		if("%".equals(style.substring(style.length()-1, style.length()))){
			DecimalFormat format = new DecimalFormat(style.substring(0,style.length()));//设置百分比格式，保留小数点后一位
			result = format.format(cellValue);
			
		}else if(style.length()>=4 && "#,##".equals(style.substring(0, 4))){
			DecimalFormat format = new DecimalFormat(style.substring(4,style.length()));//设置货币格式
			result = format.format(cellValue);
		}else if("m/d/yy".equals(style)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//			SimpleDateFormat format = new SimpleDateFormat(style.substring(0,style.length()-2));//设置日期格式
			Date date = DateUtil.getJavaDate(cellValue);//将double类型的日期值转为Date类型
			result = format.format(date);
		}else if("yyyy/m/d;@".equals(style)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//			SimpleDateFormat format = new SimpleDateFormat(style.substring(0,style.length()-2));//设置日期格式
			Date date = DateUtil.getJavaDate(cellValue);//将double类型的日期值转为Date类型
			result = format.format(date);
		}
		return result;
	}
	
	private static String handleNumericCellForMulSheet(Cell cell, double cellValue, String result, String style) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		result = cell.getStringCellValue();
		if("%".equals(style.substring(style.length()-1, style.length()))){
			DecimalFormat format = new DecimalFormat(style.substring(0,style.length()));//设置百分比格式，保留小数点后一位
			result = format.format(cellValue);
			
		}else if(style.length()>=10 && "#,##0.00_)".equals(style.substring(0, 10))){
			
		}else if(style.length()>=4 && "#,##".equals(style.substring(0, 4))){
			DecimalFormat format = new DecimalFormat(style.substring(4,style.length()));//设置货币格式
			result = format.format(cellValue);
		}else if("m/d/yy".equals(style)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//			SimpleDateFormat format = new SimpleDateFormat(style.substring(0,style.length()-2));//设置日期格式
			Date date = DateUtil.getJavaDate(cellValue);//将double类型的日期值转为Date类型
			result = format.format(date);
		}else if("yyyy/m/d;@".equals(style)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//			SimpleDateFormat format = new SimpleDateFormat(style.substring(0,style.length()-2));//设置日期格式
			Date date = DateUtil.getJavaDate(cellValue);//将double类型的日期值转为Date类型
			result = format.format(date);
		}
		return result;
	}

}
