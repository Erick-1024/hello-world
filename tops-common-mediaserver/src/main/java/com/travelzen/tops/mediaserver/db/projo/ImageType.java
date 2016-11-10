package com.travelzen.tops.mediaserver.db.projo;



public enum ImageType {
	
	GIF("gif"),	
	JPG("jpg"),
	JPEG("jpeg"),
	BMP("bmp"),
	PNG("png"),
	PSD("psd"),
	PCX("pcx");
	
	private final String value;
	
	ImageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
	
}
