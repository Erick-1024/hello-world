package com.travelzen.framework.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PinYinUtil {
	private static final Logger logger = LoggerFactory.getLogger(PinYinUtil.class);

	public static String[] getPinYin(char c) {
		return getPinYin(c, HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.UPPERCASE);
	}

	public static String[] getPinYin(char c, HanyuPinyinToneType toneType) {
		return getPinYin(c, toneType, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.UPPERCASE);
	}

	public static String[] getPinYin(char c, HanyuPinyinVCharType vCharType) {
		return getPinYin(c, HanyuPinyinToneType.WITHOUT_TONE, vCharType, HanyuPinyinCaseType.UPPERCASE);
	}

	public static String[] getPinYin(char c, HanyuPinyinCaseType caseType) {
		return getPinYin(c, HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_V, caseType);
	}

	public static String[] getPinYin(char c, HanyuPinyinToneType toneType, HanyuPinyinVCharType vCharType) {
		return getPinYin(c, toneType, vCharType, HanyuPinyinCaseType.UPPERCASE);
	}

	public static String[] getPinYin(char c, HanyuPinyinToneType toneType, HanyuPinyinCaseType caseType) {
		return getPinYin(c, toneType, HanyuPinyinVCharType.WITH_V, caseType);
	}

	public static String[] getPinYin(char c, HanyuPinyinVCharType vCharType, HanyuPinyinCaseType caseType) {
		return getPinYin(c, HanyuPinyinToneType.WITHOUT_TONE, vCharType, caseType);
	}

	public static String[] getPinYin(char c, HanyuPinyinToneType toneType, HanyuPinyinVCharType vCharType, HanyuPinyinCaseType caseType) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		/**
		 * WITH_TONE_NUMBER(以数字代替声调) : zhong1 zhong4
		 *
		 * WITH_TONE_MARK (有声调) : zhōng zhòng
		 *
		 * WITHOUT_TONE (无声调) : zhong zhong
		 */
		format.setToneType(toneType);
		/**
		 * WITH_U_AND_COLON : lu:3
		 *
		 * WITH_U_UNICODE : lü3
		 *
		 * WITH_V : lv3
		 */
		format.setVCharType(vCharType);
		/**
		 * LOWERCASE ：guó
		 *
		 * UPPERCASE ：GUÓ
		 */
		format.setCaseType(caseType);
		try {
			return PinyinHelper.toHanyuPinyinStringArray(c, format);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String getPinYin(String s) {
		return getPinYin(s, HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.UPPERCASE);
	}

	public static String getPinYin(String s, HanyuPinyinToneType toneType) {
		return getPinYin(s, toneType, HanyuPinyinVCharType.WITH_V, HanyuPinyinCaseType.UPPERCASE);
	}

	public static String getPinYin(String s, HanyuPinyinVCharType vCharType) {
		return getPinYin(s, HanyuPinyinToneType.WITHOUT_TONE, vCharType, HanyuPinyinCaseType.UPPERCASE);
	}

	public static String getPinYin(String s, HanyuPinyinCaseType caseType) {
		return getPinYin(s, HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_V, caseType);
	}

	public static String getPinYin(String s, HanyuPinyinToneType toneType, HanyuPinyinVCharType vCharType) {
		return getPinYin(s, toneType, vCharType, HanyuPinyinCaseType.UPPERCASE);
	}

	public static String getPinYin(String s, HanyuPinyinToneType toneType, HanyuPinyinCaseType caseType) {
		return getPinYin(s, toneType, HanyuPinyinVCharType.WITH_V, caseType);
	}

	public static String getPinYin(String s, HanyuPinyinVCharType vCharType, HanyuPinyinCaseType caseType) {
		return getPinYin(s, HanyuPinyinToneType.WITHOUT_TONE, vCharType, caseType);
	}

	public static String getPinYin(String s, HanyuPinyinToneType toneType, HanyuPinyinVCharType vCharType, HanyuPinyinCaseType caseType) {
		StringBuilder output = new StringBuilder();
		if (StringUtils.isNotBlank(s))
			for (char c : s.trim().toCharArray()) {
				String[] ss = getPinYin(c, toneType, vCharType, caseType);
				output.append(ss == null ? c : ss[0]);
			}
		return output.toString();
	}

	public static void main(String[] args) {
		logger.info(getPinYin("真旅网"));
	}
}
