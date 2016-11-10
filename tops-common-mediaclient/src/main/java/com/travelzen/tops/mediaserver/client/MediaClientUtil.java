package com.travelzen.tops.mediaserver.client;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.media.MediaId;

/**
 * 图片上传工具类 1.mediaid统一用Mongodb的ObjectId 2.服务端ip,port,url在配置文件中配置 3.上传成功返回mediaId
 *
 * @author wangmeng
 *
 */
public class MediaClientUtil {

	private static ThreadLocal<MediaClient> clientLocal = new ThreadLocal<>();
	private static String configFilePath = "properties/media-client.properties";

	public static MediaClient getClient() {
		if (clientLocal.get() == null) {
			MediaClient client = new MediaClient();
			client.setHost(TopsConfReader.getConfContent(configFilePath, "media.client.ip", ConfScope.R),
					Integer.parseInt(TopsConfReader.getConfContent(configFilePath, "media.client.port", ConfScope.R)),
					TopsConfReader.getConfContent(configFilePath, "media.client.url", ConfScope.R));
			clientLocal.set(client);
		}
		return clientLocal.get();
	}
	
	public static MediaClient getRemoveClient() {
		if (clientLocal.get() == null) {
			MediaClient client = new MediaClient();
			client.setHost(TopsConfReader.getConfContent(configFilePath, "media.client.ip", ConfScope.R),
					Integer.parseInt(TopsConfReader.getConfContent(configFilePath, "media.client.port", ConfScope.R)),
					TopsConfReader.getConfContent(configFilePath, "media.client.removeurl", ConfScope.R));
			clientLocal.set(client);
		}
		return clientLocal.get();
	}

	public static synchronized String upload(String filePath) throws IOException, Exception {
		return upload(filePath, MediaType.IMAGE);
	}

	/**
	 *
	 * @param filePath
	 *            上传文件路径
	 * @param mediaType
	 *            上传文件类型
	 * @return 文件id
	 * @throws IOException
	 *             文件未找到
	 * @throws Exception
	 *             上传失败
	 */
	public static synchronized String upload(String filePath, MediaType mediaType) throws IOException, Exception {
		if (StringUtils.isBlank(filePath)) {
			throw new FileNotFoundException();
		}
		String mediaId = MediaId.createMediaId();
		boolean result = getClient().uploadPhoto(filePath, mediaId, mediaType.value());
		if (!result) {
			throw new Exception("图片上传失败,请重新上传");
		}
		return mediaId;
	}

	public static synchronized String upload(byte[] b) throws IOException, Exception {
		return upload(b, MediaType.IMAGE, null);
	}

	public static synchronized String upload(byte[] b, MediaType mediaType) throws IOException, Exception {
		return upload(b, mediaType, null);
	}

	public static synchronized String uploadTemplete(byte[] b, MediaType mediaType) throws IOException, Exception {
		return upload(b, mediaType, null);
	}

	public static synchronized String upload(byte[] b, MediaType mediaType, String fileName) throws IOException, Exception {
		if (b == null || b.length == 0) {
			throw new FileNotFoundException();
		}
		String mediaId = MediaId.createMediaId();
		boolean result = getClient().uploadPhoto(b, mediaId, mediaType.value(), fileName);
		if (!result) {
			throw new Exception("图片上传失败,请重新上传");
		}
		return mediaId;
	}

	public static synchronized String uploadTemplete(byte[] b, MediaType mediaType, String fileName) throws IOException, Exception {
		if (b == null || b.length == 0) {
			throw new FileNotFoundException();
		}
		String mediaId = MediaId.createMediaId();
		boolean result = getClient().uploadNoticeTemplate(b, mediaId, mediaType.value(), fileName);
		if (!result) {
			throw new Exception("图片上传失败,请重新上传");
		}
		return mediaId;
	}

	/**
	 * 裁剪成制定长度的正方型
	 *
	 * @param b
	 * @param mediaType
	 * @param squareLength
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static synchronized String upload(byte[] b, MediaType mediaType, int squareLength) throws IOException, Exception {
		if (b == null || b.length == 0) {
			throw new FileNotFoundException();
		}
		String mediaId = MediaId.createMediaId();
		boolean result = getClient().uploadPhotoWithSquareWidth(b, mediaId, mediaType.value(), squareLength);
		if (!result) {
			throw new Exception("图片上传失败,请重新上传");
		}
		return mediaId;
	}

	public static synchronized String uploadWithWidth(byte[] b, MediaType mediaType, int width) throws IOException, Exception {
		if (b == null || b.length == 0) {
			throw new FileNotFoundException();
		}
		String mediaId = MediaId.createMediaId();
		boolean result = getClient().uploadPhotoWithWidth(b, mediaId, mediaType.value(), width);
		if (!result) {
			throw new Exception("图片上传失败,请重新上传");
		}
		return mediaId;
	}

	public static synchronized String uploadWithHeight(byte[] b, MediaType mediaType, int height) throws IOException, Exception {
		if (b == null || b.length == 0) {
			throw new FileNotFoundException();
		}
		String mediaId = MediaId.createMediaId();
		boolean result = getClient().uploadPhotoWithHeight(b, mediaId, mediaType.value(), height);
		if (!result) {
			throw new Exception("图片上传失败,请重新上传");
		}
		return mediaId;
	}

	public static synchronized String removeFile(String mediaId, MediaType mediaType) throws IOException, Exception {
		boolean result = getRemoveClient().removeFile(mediaId, mediaType.value());
		if (!result) {
			throw new Exception("图片上传失败,请重新上传");
		}
		return mediaId;
	}
	
	public enum MediaType {
		IMAGE("image"), Video("video"), CONTRACT("contract"),
		DOC("doc"),DOWNLOAD("download"),
		;
		private String mediaType;

		private MediaType(String mediaType) {
			this.mediaType = mediaType;
		}

		public String value() {
			return mediaType;
		}
	}
}
