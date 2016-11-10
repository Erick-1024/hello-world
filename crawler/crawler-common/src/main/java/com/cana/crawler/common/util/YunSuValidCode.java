package com.cana.crawler.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Created by Yini.Xie
 */

@Component
public class YunSuValidCode implements IYunSuValidCode{

	private static Logger logger = LoggerFactory.getLogger(YunSuValidCode.class);

	@Resource(name = "yunsu-httpClient")
	private HttpClient httpClient;

	private static String USERNAME = "alfredjones_af";
	private static String PASSWORD = "147258";
	private static String TIMEOUT = "90";
	private static String SOFTID = "4014";
	private static String SOFTKEY = "44c109d61ba449e8913da7b85ef21b7d";
	
	public static final String TYPEID_DIGITS_4 = "1040";

	
	/**
	 * 根据页面元素获取验证码
	 * @param typeId
	 * @param element
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	@Override
	public synchronized String getCheckCodeByWebElement(String typeId, WebElement element, WebDriver driver) throws Exception{
		File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage screenImg = ImageIO.read(screenFile);
		BufferedImage image = screenImg.getSubimage(element.getLocation().x, element.getLocation().y, element.getSize().width, element.getSize().height);
		ImageIO.write(image, "png", screenFile);
		return createByPost(typeId, screenFile);
	}

	/**
	 * 上传题目图片返回结果

	 * @param typeid		题目类型
	 * @param imageFile		题目截图或原始图
	 * @return
	 */
	@Override
	public synchronized String createByPost(String typeid, File imageFile) {
		String result = "";

		try {
			StopWatch watch = new Slf4JStopWatch("云速");
			result = httpPostImage("http://api.ysdm.net/create.xml", typeid, imageFile);
			watch.stop();
			logger.info("----------------------------------云速查询返回结果开始----------------------------------\n");
			logger.info(result);
			logger.info("----------------------------------云速查询返回结果结束----------------------------------\n");
			result = displayXmlResult(result);
		} catch (Exception e) {
			logger.error("", e);
			result = "";
		}
		return result;
	}

	/**
	 * 上传图片URL并返回结果
	 * @param typeid
	 * @param valiCodeUrl
	 * @return
	 */
	@Override
	public synchronized String createByPostWithURL(String typeid,String valiCodeUrl) {
		String filePath = getValiCodeFilePath(valiCodeUrl);
		File file = new File(filePath);
		return createByPost(typeid, file);
	}

	/**
	 * 上传题目图片返回结果

	 * @param typeid		题目类型
	 * @param imagePath		题目截图或原始图路径
	 * @return
	 */
	@Override
	public synchronized String createByPost(String typeid, String imagePath) {

		File imageFile = new File(imagePath);

		return createByPost(typeid, imageFile);
	}

	/**
	 * 字符串MD5加密
	 * @param s 原始字符串
	 * @return  加密后字符串
	 */
	private static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通用Http访问接口
	 * @param url           访问地址，不能带有?字符
	 * @param typeid	题目类型
	 * @param file          上传文件
	 * @return
	 * @throws Exception
	 * */
	private String httpPostImage(String url, String typeid, File file) throws Exception {
		httpClient.getParams().setContentCharset("UTF-8");

		PostMethod method = new PostMethod(url);

		List<Part> parts = new ArrayList<Part>();
		parts.add(new StringPart("username", USERNAME));
		parts.add(new StringPart("password", MD5(PASSWORD)));
		parts.add(new StringPart("typeid", typeid));
		parts.add(new StringPart("timeout", TIMEOUT));
		parts.add(new StringPart("softid", SOFTID));
		parts.add(new StringPart("softkey", SOFTKEY));
		parts.add(new FilePart("image", file, " image/gif", "UTF-8"));

		RequestEntity requestEntity = new MultipartRequestEntity(parts.toArray(new Part[0]), method.getParams());
		method.setRequestEntity(requestEntity);

		int statusCode = httpClient.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {
			throw new Exception("建立链接失败。");
		}
		String returnValue = method.getResponseBodyAsString();

		return returnValue;
	}

	/**
	 * 解析xml结果
	 * @param xml xml返回结果字符串
	 * @return 成功返回验证码的值，失败则返回“”
	 */
	private  String displayXmlResult(String xml) {
		Document dm;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;

		try {
			db = dbf.newDocumentBuilder();
			dm = db.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
			NodeList resultNl = dm.getElementsByTagName("Result");

			if (resultNl.getLength() > 0) {
				return resultNl.item(0).getFirstChild().getNodeValue();
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	private String  getValiCodeFilePath(String url){
		 InputStream is = null;
		 OutputStream os = null;
		try {
	        URL myURL;
			myURL = new URL(url);
			URLConnection httpsConn = myURL.openConnection();
	        is = httpsConn.getInputStream();
	        os = new FileOutputStream("/tmp/"+Thread.currentThread().getName()+".jpg");
	        byte[] buffer = new byte[4096];
	        int length;
	        while ((length = is.read(buffer)) != -1) {
	            os.write(buffer, 0, length);
	        }
		} catch (Exception e) {
			logger.info( "获取验证码图片失败,",e);
		}finally{
	        try {
				is.close();
		        os.close();
			} catch (IOException e) {
				logger.info( "获取验证码图片失败,",e);
			}
		}
		return  "/tmp/"+Thread.currentThread().getName()+".jpg";

	}
}
