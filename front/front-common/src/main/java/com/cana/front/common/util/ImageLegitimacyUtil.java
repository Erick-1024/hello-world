package com.cana.front.common.util;

import org.springframework.web.multipart.MultipartFile;

import com.travelzen.framework.core.exception.WebException;

public class ImageLegitimacyUtil {
	private static long MAX_SIZE = 5 * 1024 * 1024;

	private static long MAX_SIZE_ENTERPRISE = 20 * 1024 * 1024;
	
	public static MultipartFile verifyImage(MultipartFile image){
		if(verifyImageType(image) && verifyImageSize(image))
			return image;
		return null;
	}

	public static MultipartFile verifyImageForEnterpriseInfo(MultipartFile image){
		if(verifyImageTypeForEnterpriseInfo(image) && verifyImageSizeForEnterpriseInfo(image))
			return image;
		return null;
	}
	
	private static boolean verifyImageType(MultipartFile image) {
		if(ImageType.IMAGE_JPEG.desc.equals(image.getContentType()))
			return true;
		if(ImageType.IMAGE_PJPEG.desc.equals(image.getContentType()))
			return true;
		if(ImageType.IMAGE_PNG.desc.equals(image.getContentType()))
			return true;
		if(ImageType.IMAGE_XPNG.desc.equals(image.getContentType()))
			return true;
		if(ImageType.PDF.desc.equals(image.getContentType()))
			return true;
		throw WebException.instance("格式不支持");
	}

	private static boolean verifyImageTypeForEnterpriseInfo(MultipartFile image) {
		if(ImageType.IMAGE_JPEG.desc.equals(image.getContentType()))
			return true;
		if(ImageType.IMAGE_PJPEG.desc.equals(image.getContentType()))
			return true;
		if(ImageType.IMAGE_PNG.desc.equals(image.getContentType()))
			return true;
		if(ImageType.IMAGE_XPNG.desc.equals(image.getContentType()))
			return true;
		if(ImageType.PDF.desc.equals(image.getContentType()))
			return true;
		if(ImageType.WORD.desc.equals(image.getContentType()))
			return true;
		if(ImageType.WORDX.desc.equals(image.getContentType()))
			return true;
		if(ImageType.XLS.desc.equals(image.getContentType()))
			return true;
		if(ImageType.XLSX.desc.equals(image.getContentType()))
			return true;
		if(ImageType.TXT.desc.equals(image.getContentType()))
			return true;
		if(ImageType.PPT.desc.equals(image.getContentType()))
			return true;
		if(ImageType.PPTX.desc.equals(image.getContentType()))
			return true;
		throw WebException.instance("格式不支持");
	}
	
	private static boolean verifyImageSize(MultipartFile image) {
		if(MAX_SIZE >= image.getSize())
			return true;
		throw WebException.instance("大小超过限制");
	}

	private static boolean verifyImageSizeForEnterpriseInfo(MultipartFile image) {
		if(MAX_SIZE_ENTERPRISE >= image.getSize())
			return true;
		throw WebException.instance("大小超过限制");
	}
	
	private enum ImageType{
		IMAGE_JPEG("image/jpeg"),
		IMAGE_PJPEG("image/pjpeg"),
		IMAGE_PNG("image/png"),
		IMAGE_XPNG("image/x-png"),
		PDF("application/pdf"),
		WORD("application/msword"),
		WORDX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
		XLS("application/vnd.ms-excel"),
		XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
		TXT("text/plain"),
		PPT("application/vnd.ms-powerpoint"),
		PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
		;
		
		private String desc;
		
		private ImageType(String desc){
			this.desc = desc;
		}
	}
}
