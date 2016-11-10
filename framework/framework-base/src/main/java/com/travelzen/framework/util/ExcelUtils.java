package com.travelzen.framework.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils {
	
	public static <T> void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, boolean hasSequence) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		generateWorkbook(workbook, title, headers, dataset, out, hasSequence);
		workbook.write(out);
		IOUtils.closeQuietly(out);
	}
	
	public static <T> void generateWorkbook(HSSFWorkbook workbook, String title, String[] headers, Collection<T> dataset, OutputStream out, boolean hasSequence) {
		HSSFSheet sheet = workbook.createSheet(title);
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
		}
		Iterator<T> iterator = dataset.iterator();
		int index = 0;
		int sequence = 0;
		while (iterator.hasNext()) {
			row = sheet.createRow(++index);
			int i = 0;
			if(hasSequence) {
				HSSFCell sequenceCell = row.createCell(0);
				sequenceCell.setCellValue(++sequence);
				++i;
			}
			T t = (T) iterator.next();
			Field[] fields = t.getClass().getDeclaredFields();
			for (; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = fields[i];
				String fieldName = field.getName();
				Class<? extends Object> type = field.getType();

                if ("serialVersionUID".equals(fieldName))
                    continue;
                String getMethodName;
                if (type.isAssignableFrom(Collection.class) || type.isAssignableFrom(Map.class))
                    return;
                if (type.isAssignableFrom(Boolean.class)){
                    getMethodName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                }else {
                    getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                }
				try {
					Class<? extends Object> cls = t.getClass();
					Method getMethod = cls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = null;
					if(value instanceof Boolean){
						if((boolean)value)
							textValue = "是";
						else
							textValue = "否";
					}else {
						textValue = value == null ? "" : value.toString();
					}
					if(null != value){
						Pattern pattern = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = pattern.matcher(textValue);
						if(matcher.matches()){
							cell.setCellValue(Double.parseDouble(textValue));
						}else{
							cell.setCellValue(textValue);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
