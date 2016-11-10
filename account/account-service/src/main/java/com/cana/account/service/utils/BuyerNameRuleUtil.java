package com.cana.account.service.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cana.vbam.common.account.consts.AccountConsts;
import com.cana.vbam.common.utils.Constants;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

/**
 * 买方名称规则
 * @author XuMeng
 *
 */
public class BuyerNameRuleUtil {

	private static final String INVALID_BUYER_NAME = "其它其他";
    /**
     * 对买方名称列表做校验，首先去除空白字符，其次检查名称中是否包涵特殊字符，负责向上抛异常
     */
    public static List<String> trimBuyerNames(List<String> buyerNames) {
        if (buyerNames == null || buyerNames.isEmpty())
            return null;

        List<String> validNames = Lists.newArrayList();
        for (String buyerName : buyerNames) {
        	if(buyerName.indexOf(INVALID_BUYER_NAME)>-1){
        		throw WebException.instance("非法字符：其他.");
        	}
            if (StringUtils.isNotBlank(buyerName)
                    && !buyerName.contains(AccountConsts.SEMICOLON)) {
                validNames.add(StringUtils.trim(buyerName));
            }
        }
        return validNames.isEmpty() ? null : validNames;
    }
}
