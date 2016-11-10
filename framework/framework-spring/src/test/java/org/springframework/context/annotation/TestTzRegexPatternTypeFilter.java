package org.springframework.context.annotation;

import java.util.regex.Pattern;

public class TestTzRegexPatternTypeFilter {

	public static void main(String[] arg) {

		Pattern p = Pattern.compile("com.travelzen.tops.order.hotel.*");

		System.out.print(p.matcher("com.travelzen.tops.order.hotel.common.exportExcel.HotelOrderExcelBuilderFactory").matches());
	}
}
