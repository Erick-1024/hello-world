package com.cana.wechat.openapi.testcontroller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.vbam.common.wechat.WechatConfig;
import com.cana.wechat.api.IWeChatApi;

@Controller
@RequestMapping(value = "test")
public class imgTestController {

	@Resource
	private IWeChatApi weChatApi;

	private String TOKEN = "canacorp";
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "imgTest", method = RequestMethod.GET)
	public String gotoImgTest(Model model) {
		return "page/wechat/imgTest";
	}

	// 微信加密签名
	// 随机字符串
	// 时间戳
	// 随机数
	@RequestMapping(value = "token", method = RequestMethod.GET)
	@ResponseBody
	public String token(Model model, String signature, String echostr, String timestamp, String nonce) {
		logger.info("微信加密签名:" + signature + ",随机字符串:" + echostr + ",时间戳:" +timestamp + "，随机数:" + nonce);
		
		//		String[] str = { TOKEN, timestamp, nonce };
//		Arrays.sort(str); // 字典序排序
//		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
//		String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
//		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//		crypt.reset();
//		crypt.update(bigStr.getBytes("UTF-8"));
//		
//		// 确认请求来至微信
//		if (digest.equals(signature)) {
////			response.getWriter().print(echostr);
//		}
		return echostr;
	}

	@RequestMapping(value = "/getConfig", method = RequestMethod.POST)
	@ResponseBody
	public WechatConfig getConfig(String url) {
		try {
			return weChatApi.getWechatWebConfig(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
