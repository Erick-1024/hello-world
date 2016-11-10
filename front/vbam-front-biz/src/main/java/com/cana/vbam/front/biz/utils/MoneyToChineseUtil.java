package com.cana.vbam.front.biz.utils;

public class MoneyToChineseUtil {

	/**
	 * 转换金额为中文大写
	 * 
	 * 转换结果不包含元字，为了真旅项目签合同用
	 * 
	 * @param amount
	 *            不为负数
	 */
	public static String cent2Chinese(long amount) {
		if (amount < 0)
			return "";
		if (amount == 0)
			return "零";

		String amountStr = String.valueOf(amount);
		if (amountStr.length() > 14)
			return "";

		String unit = "仟佰拾亿仟佰拾万仟佰拾元角分";
		StringBuilder str = new StringBuilder();
		unit = unit.substring(unit.length() - amountStr.length());
		for (int i = 0; i < amountStr.length(); i++)
			str.append("零壹贰叁肆伍陆柒捌玖".toCharArray()[amountStr.charAt(i) - '0'])
			.append(unit.charAt(i));
		return str.toString().replaceAll("零(仟|佰|拾|角)", "零").replaceAll("(零)+", "零").replaceAll("零(万|亿|元)", "$1")
				.replaceAll("(亿)万|壹(拾)", "$1$2").replaceAll("^元零?|零分", "").replaceAll("元$", "");
	}
}
