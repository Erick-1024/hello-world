package com.cana.wechat.server.api.impl;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.member.api.IUserApi;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.repayment.dto.FinanceInfo4FinanceDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanActiveRepaymentDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanSearchCriteriaDTO;
import com.cana.vbam.common.wechat.WechatConfig;
import com.cana.vbam.common.wechat.account.AccountWechatDTO;
import com.cana.vbam.common.wechat.account.TradeRecordRequest;
import com.cana.vbam.common.wechat.account.TradeRecordResponse;
import com.cana.vbam.common.wechat.member.user.CustomerWechatDetailDTO;
import com.cana.vbam.common.wechat.repayment.LoanInfoRequest;
import com.cana.vbam.common.wechat.repayment.LoanInfoResponse;
import com.cana.wechat.api.IWeChatApi;
import com.cana.wechat.common.dto.TokenRedisDTO;
import com.cana.wechat.common.util.Constants;
import com.cana.wechat.service.IWeChatService;
import com.cana.wechat.service.IWeChatTokenService;
import com.cana.wechat.service.transaction.IWeChatTransactionService;
import com.google.gson.Gson;

public class WeChatApiImpl implements IWeChatApi {

	@Resource
	private IWeChatTokenService weChatTokenService;
	
	@Resource
	private IWeChatService weChatService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IUserApi userApi;
	
	@Resource
	private IWeChatTransactionService weChatTransactionService;
	
	@Override
	public WechatConfig getWechatWebConfig(String url) throws IOException {
		TokenRedisDTO tokenRedisDTO = weChatTokenService.getToken();
		String jsapi_ticket = tokenRedisDTO.getToken();
		String timestamp = create_timestamp();
		String nonceStr = create_nonce_str();
		String sign = getSignature(jsapi_ticket, create_timestamp(), create_nonce_str(), url);
		System.out.println("jsapi_ticket:" + jsapi_ticket + "appid:" + Constants.WECHAT_APPID_PARAM + ",nonceStr:"
				+ create_nonce_str() + ",timestamp:" + create_timestamp() + ",signature:" + sign);
		System.out.println(sign);
		// Map<String, String> ret = sign(jsapi_ticket, url);
		WechatConfig config = new WechatConfig();

		config.setAppId(Constants.WECHAT_APPID_PARAM);
		config.setNonceStr(nonceStr);
		config.setTimestamp(timestamp);
		config.setSignature(sign);

		logger.info("jsapi_ticket:" + jsapi_ticket + "appid:" + Constants.WECHAT_APPID_PARAM + ",nonceStr:"
				+ create_nonce_str() + ",timestamp:" + create_timestamp() + ",signature:" + sign);

		List<String> js = new ArrayList<>();
		js.add("chooseImage");
		js.add("previewImage");
		js.add("uploadImage");
		js.add("downloadImage");

		config.setJsApiList(js);
		String configStr = new Gson().toJson(config);
		logger.info("JS config：" + configStr);
		return config;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获得js signature
	 * 
	 * @param jsapi_ticket
	 * @param timestamp
	 * @param nonce
	 * @param jsurl
	 * @return
	 * @throws IOException
	 */
	public static String getSignature(String jsapi_ticket, String timestamp, String nonce, String jsurl)
			throws IOException {
		/****
		 * 对 jsapi_ticket、 timestamp 和 nonce 按字典排序 对所有待签名参数按照字段名的 ASCII
		 * 码从小到大排序（字典序）后，使用 URL 键值对的格式（即key1=value1&key2=value2…）拼接成字符串
		 * string1。这里需要注意的是所有参数名均为小写字符。 接下来对 string1 作 sha1 加密，字段名和字段值都采用原始值，不进行
		 * URL 转义。即 signature=sha1(string1)。
		 * **如果没有按照生成的key1=value&key2=value拼接的话会报错
		 */
		String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket, "timestamp=" + timestamp,
				"noncestr=" + nonce, "url=" + jsurl };
		Arrays.sort(paramArr);
		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat("&" + paramArr[1]).concat("&" + paramArr[2]).concat("&" + paramArr[3]);
		System.out.println("拼接之后的content为:" + content);
		String gensignature = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对拼接后的字符串进行 sha1 加密
			byte[] digest = md.digest(content.toString().getBytes());
			gensignature = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将 sha1 加密后的字符串与 signature 进行对比
		if (gensignature != null) {
			return gensignature;// 返回signature
		} else {
			return "false";
		}
		// return (String) (ciphertext != null ? ciphertext: false);
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 *
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 *
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	@Override
	public List<TradeRecordResponse> queryTradeRecords(TradeRecordRequest request) {
		CustomerDetailDTO user = userApi.checkCustomerIsValid(request.getCustomerId());
		updateTradeRecordRequest(user,request);
		return weChatTransactionService.queryTradeRecords(request);
	}


	@Override
	public List<LoanInfoResponse> queryLoanInfos(LoanInfoRequest request) {
		
		return weChatTransactionService.queryLoanInfos(request);
	}
	@Override
	public CustomerWechatDetailDTO queryCustomerDetail(String customerId) {
		return weChatService.queryCustomerDetail(customerId);
	}


	@Override
	public AccountWechatDTO getAccountInfo(String customerId) {
		return weChatService.getAccountInfo(customerId);
	}
	
	/**
	 * 流水明细 查询条件转换
	 * @param customer
	 * @param criteria
	 * @return
	 */
	private void updateTradeRecordRequest(CustomerDetailDTO user,TradeRecordRequest request){
		request.setPageSize(request.getPageSize() < 1 ? 10 : request.getPageSize());
		
		List<AccountTradeType> accountTradeTypes = new ArrayList<>(Arrays.asList(AccountTradeType.TRANSFER_FUND,AccountTradeType.WITHDRAW_FUND));
    	request.setTradeTypes(accountTradeTypes);
    	
		if (UserType.FACTOR.equals(user.getUserType())) 
			request.setFactorId(request.getCustomerId());
		if (UserType.FINACE.equals(user.getUserType())) 
			request.setFinaceId(request.getCustomerId());
		if (UserType.CORECOMPANY.equals(user.getUserType()))
			request.setCoreCompanyId(request.getCustomerId());
	}

	@Override
	public FinanceInfo4FinanceDTO getFinanceInfo(UserSessionDTO userSessionDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepaymentPlanActiveRepaymentDTO> getOverdueRepaymentPlan(UserSessionDTO userSessionDTO, RepaymentPlanSearchCriteriaDTO searchDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepaymentPlanActiveRepaymentDTO> getRepaymentPlansWhtin7Days(UserSessionDTO userSessionDTO, RepaymentPlanSearchCriteriaDTO searchDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateContactName(String userId, String contactName) throws Exception {
		weChatService.updateContactName(userId, contactName);
	}

	@Override
	public void updateContactTel(String userId, String mobileNum) throws Exception {
		weChatService.updateContactTel(userId, mobileNum);
	}

	@Override
	public void updateContactMail(String userId, String mail) throws Exception {
		weChatService.updateContactMail(userId, mail);
	}

	@Override
	public void updateContactJobTitle(String userId, String jobTitle) throws Exception {
		weChatService.updateContactJobTitle(userId, jobTitle);
	}
}
