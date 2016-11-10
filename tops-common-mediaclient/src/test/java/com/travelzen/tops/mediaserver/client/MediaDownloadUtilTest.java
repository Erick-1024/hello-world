package com.travelzen.tops.mediaserver.client;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Author  : simon
 * @version : Jun 14, 2014 3:55:49 PM
 *
 **/
public class MediaDownloadUtilTest {

	public static void main(String[] args) {
		try {
			MediaDownloadUtil.download("567bbad5831464997bf0a27e", "/tmp/pic.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

}
