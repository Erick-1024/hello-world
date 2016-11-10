package com.travelzen.tops.mediaserver.client;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.travelzen.framework.picture.TZPhotoUtil;
import com.travelzen.framework.util.HashUtils;

public class MediaClientTest {

    @SuppressWarnings("unused")
    private static Logger LOG = LoggerFactory.getLogger(MediaClientTest.class);

    @SuppressWarnings("unused")
    private String testMediaId = "7034482887350049844";
    private MediaClient mdaClient;

    @Before
    public void initMediaClient() {
        mdaClient = new MediaClient();
//        mdaClient.setHost("media.tdxinfo.com", 0, "/tops-mediaserver/uploadImageService");
        mdaClient.setHost("192.168.1.13", 9993, "/tops-mediaserver/uploadImageService");
//        mdaClient.setHost("127.0.0.1", 8080, "/tops-mediaserver/uploadImageService");
//        mdaClient.setTarget("http://media.tdxinfo.com/tops-mediaserver/uploadImageService");
    }
    
    @Test
    public void testUploadExcelTemplate()
    {
    	try
		{
//			File file = new File("/home/dev3/放款信息模板.xls");
    		//下载路径：http://192.168.1.7:9993/tops-mediaserver/imageservice?mediaImageId=loaninfotemplatedownload&mediaType=download
//			boolean  result = mdaClient.uploadNoticeTemplate(MediaClientUtilTest.getBytes("/Users/lewis/Downloads/还款计划及费用模板.xls"), "repaymentplanandexpensetemplatedownload", "image", "还款计划及费用模板.xls");
//			boolean  result = mdaClient.uploadNoticeTemplate(MediaClientUtilTest.getBytes("/Users/guguanggong/Desktop/信韵融线下数据模板.xlsx"), "lineImporttemplatedownload", "image", "信韵融线下数据模板.xlsx");
    		boolean  result = mdaClient.uploadNoticeTemplate(MediaClientUtilTest.getBytes("/home/xiaoyu/桌面/信韵融监控信息导入模板.xlsx"), "yundaexmonitortemplatedownload", "image", "信韵融监控信息导入模板.xlsx");
			System.out.print(result);
		} catch (Exception e)
		{
			// TODO: handle exception
			System.out.println(e.toString());
		}
    }
    
//    @Test
//    public void uploadFile(){
//    	HttpClient httpclient = new HttpClient();  
//        try {  
//            HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");  
//  
//            FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));  
//            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
//  
//            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();  
//  
//            httppost.setEntity(reqEntity);  
//  
//            System.out.println("executing request " + httppost.getRequestLine());  
//            CloseableHttpResponse response = httpclient.execute(httppost);  
//            try {  
//                System.out.println("----------------------------------------");  
//                System.out.println(response.getStatusLine());  
//                HttpEntity resEntity = response.getEntity();  
//                if (resEntity != null) {  
//                    System.out.println("Response content length: " + resEntity.getContentLength());  
//                }  
//                EntityUtils.consume(resEntity);  
//            } finally {  
//                response.close();  
//            }  
//        } catch (ClientProtocolException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } finally {  
//            try {  
//                httpclient.close();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            }  
//        }  
//    }
    
    @Test
    public void testUploadExcelTemplate2()
    {
    	try
		{
//			File file = new File("/home/dev3/放款信息模板.xls");
    		//下载路径：http://192.168.1.7:9993/tops-mediaserver/imageservice?mediaImageId=loaninfotemplatedownload&mediaType=download
			boolean  result = mdaClient.uploadNoticeTemplate(MediaClientUtilTest.getBytes("/home/hu/Downloads/放款信息模板.xls"), "loaninfotemplatedownload", "image", "放款信息模板.xls");
			System.out.print(result);
		} catch (Exception e)
		{
			// TODO: handle exception
			System.out.println(e.toString());
		}
    }
    @Test
    public void testUploadImage() throws IOException {
        boolean result = mdaClient.uploadPhoto("/home/dev1/Downloads/1.jpg", "22333313421342134444", "image");
        assertTrue(result);
    }

//    @Test
    public void testUploadPhotoWithHeight() throws IOException {
        byte[] content = TZPhotoUtil.getBytesFromFile(new File("/home/dev1/Downloads/1.jpg"));
        boolean result = mdaClient.uploadPhotoWithHeight(content, "22333313421342134444555_heigth", "image", 200);
        assertTrue(result);
    }

//    @Test
    public void testUploadPhotoWithSquareWidth() throws IOException {
        byte[] content = TZPhotoUtil.getBytesFromFile(new File("/home/dev1/Downloads/1.jpg"));
        boolean result = mdaClient.uploadPhotoWithSquareWidth(content, "22333313421342134444_square", "image", 100);
        assertTrue(result);
    }

//    @Test
    public void testUploadPhotoWithWidth() throws IOException {
        byte[] content = TZPhotoUtil.getBytesFromFile(new File("/home/dev1/Downloads/1.jpg"));
        boolean result = mdaClient.uploadPhotoWithWidth(content, "22333313421342134444_width", "image", 100);
        assertTrue(result);
    }

    @Test
    public void testUploadContract() throws IOException {
        String filename = "/home/dev1/Downloads/123";
        String mediaStringId = filename.replaceAll("/", "");
        if (mediaStringId.length() < 10) {
            return;
        }
        mediaStringId = mediaStringId.substring(mediaStringId.length() - 7, mediaStringId.length() - 1);
        String mediaId = "" + HashUtils.murmurHash2(mediaStringId);
        boolean result = mdaClient.uploadPhoto(filename, mediaId, "contract");
        assertTrue(result);
    }
    
    public void printTest()
    {
    	System.out.println("hello world");
    }

    @Test
    public void test()
    {
    	printTest();
    }
}
