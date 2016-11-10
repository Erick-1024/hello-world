package com.travelzen.tops.mediaserver.db.projo;
public class Image extends Media {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3139447807541096866L;

	private int width;

	private int height;

	public Image() {
		mediaType = MediaType.IMAGE.getValue();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("width       : " + width + "\n");
		builder.append("height      : " + height + "\n");
		builder.append("type        : " + type + "\n");
		return builder.toString();
	}

}
