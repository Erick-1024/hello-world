package com.travelzen.framework.lucene.index;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.travelzen.framework.lucene.index.analyzer.AnalyzerBuilder;
import com.travelzen.framework.lucene.index.util.SearchUtil;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**  
 *  
 * @author lewis.yang  
 */
public class AutoCompleteConstants {

	public static final String AUTO_COMPLETE_SIMPLEPINYIN_NAME = "simpinyin";
	public static final String AUTO_COMPLETE_PINYIN_NAME = "pinyin";
	public static final String AUTO_COMPLETE_FULL_PINYIN_NAME = "fullPinyin";
	public static final String AUTO_COMPLETE_SHORT_PINYIN_NAME = "shortPinyin";
	public static final String AUTO_COMPLETE_CHINESE_NAME = "chineseName";

	private static Map<Character, Character> numToCnNumMap = new HashMap<Character, Character>();
	
	private static AnalyzerBuilder analyzerBuilder = AnalyzerBuilder.getAnalyzerBuilder();
	
	static {
		numToCnNumMap.put('0', '零');
		numToCnNumMap.put('1', '一');
		numToCnNumMap.put('2', '二');
		numToCnNumMap.put('3', '三');
		numToCnNumMap.put('4', '四');
		numToCnNumMap.put('5', '五');
		numToCnNumMap.put('6', '六');
		numToCnNumMap.put('7', '七');
		numToCnNumMap.put('8', '八');
		numToCnNumMap.put('9', '九');
	}
	
	public static List<String> analyzerWords(String text) throws Exception {
		text = numToCnNum(text);
		return analyzerBuilder.ikString(text);
	}
	
	public static String[] getPinyin(String text) throws Exception{
		Boolean isEnglish = SearchUtil.isEnglish(text); // 判断是否是纯英文
		List<String> keywords = analyzerWords(text);
		StringBuffer sbFullPinyin = new StringBuffer();
		for (String keyword:keywords) {
			if (isEnglish) { // 纯英文的处理
				sbFullPinyin.append(keyword + " ");
			} else { // 中文或者中英混合处理
				for(char c:keyword.toCharArray()){
					String py = toPinyinString(c);
					if (null == py) {
						sbFullPinyin.append(" " + keyword); // 将拆分的字符分别加上新得到的
					} else {
						String pinyin = py.split(" ")[0];
						sbFullPinyin.append(" " + pinyin); // 将拆分的字符分别加上新得到的
					}
				}
			}
		}
		return new String[] { sbFullPinyin.toString().trim()};
		
	}
	
	private static String numToCnNum(String text) {
		if (null == text || "".equals(text)) {
			return text;
		}

		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < text.length(); ++i) {
			char c = text.charAt(i);
			if (numToCnNumMap.containsKey(c)) {
				sb.append(numToCnNumMap.get(c));
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}
	
	private static String toPinyinString(char keyword) throws Exception{
		StringBuffer pinyins = new StringBuffer();
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(keyword, outputFormat);
		Set<String> pinyinSet = new HashSet<>();
		if(null != pinyinStringArray && pinyinStringArray.length > 0){
			for(String pinyin:pinyinStringArray){
				if(!pinyinSet.contains(pinyin)){
					pinyins.append(pinyin);
					pinyinSet.add(pinyin);
				}
			}
		}
		return pinyins.toString();
	}
}
