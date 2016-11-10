package com.travelzen.tops.mediaserver.test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaServerConnectTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    private static final String MEDIA_SERVER_URL = "http://180.169.46.149:8080/tops-mediaserver/uploadRelation";
    private static final String MEDIA_SERVER_URL = "http://localhost:8080/tops-mediaserver/uploadRelation";

    @Test
    public void getUpdateImageUrl() {
        getResultMethod("http://www.elongstatic.com/gp1/M00/17/71/ooYBAFIEeuOAT1FqAABnRW-YPKo263.jpg?v=20130809131555");
    }

    private void getResultMethod(String imageUrl) {
        try {
            PostMethod postMethod = new PostMethod(MEDIA_SERVER_URL);
            postMethod.setParameter("imageUrl", imageUrl);
            HttpClient httpclient = new HttpClient();
            httpclient.getParams().setContentCharset("UTF-8");
            int code = httpclient.executeMethod(postMethod);
            logger.info("Response status code: " + code);
            String responseString = postMethod.getResponseBodyAsString();
            logger.info("Response body:\n" + responseString);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
