package com.cana.vbam.common.utils;

/**
 * 银行账号工具类
 * @author XuMeng
 *
 */
public class AccountNoUtil {

    /**
     * 去除银行账号中的所有空格字符
     */
    public static String parseBankAccountNo(String accountNo) {
        if (accountNo == null) return "";
        return accountNo.replaceAll("\\ +","");
    }

    /**
     * 每隔4位数字加一个空格
     */
    public static String formatBankAccountNo(String accountNo) {
        if (accountNo == null) return "";
        return parseBankAccountNo(accountNo).replaceAll("(\\d{4})(?=\\d)", "$1 ");
    }
    
    /**
     * luhn校验的过程：
     * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加.
     * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和.
     * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除
     * 例如，卡号是：5432123456788881
     * 则奇数、偶数位（用红色标出）分布：5432123456788881 
     * 奇数位和=35 
     * 偶数位乘以2（有些要减去9）的结果：1 6 2 6 1 5 7 7，求和=35
     *  最后35+35=70 可以被10整除，认定校验通过。
     */ 
    public static boolean luhnBankAccountNoPass(String bankAccountNo) {
      char[] chs = bankAccountNo.trim().toCharArray();
      int luhmSum = 0;
      for (int i = chs.length - 1;i >= 0; i--) {
          int k = Integer.parseInt(String.valueOf(chs[i]));
          if ((chs.length - i) % 2 == 0) {
              k *= 2;
              k = k / 10 + k % 10;
          }
          luhmSum += k;
      }
      return (luhmSum % 10 == 0) ? true : false;
  }
}
