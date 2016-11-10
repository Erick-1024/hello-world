package com.travelzen.tops.mediaserver.db.projo;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.travelzen.framework.util.DateUtils;

public class Media implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8773988298497229965L;

	protected String mediaId;

	protected java.util.Date createTime;

	protected String filename;

	protected long length;

	protected byte[] content;

	transient protected java.io.InputStream inputStream;

	protected String mediaType;

	protected String type;

	protected String description;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public byte[] getContent() {
		if (content == null) {
			byte[] temp = new byte[(int)length];
			try {
				IOUtils.read(inputStream, temp);
			} catch (IOException e) {
				throw new RuntimeException("Read inputstream error occured!" + filename, e);
			}
//			content = FileUtils.readByte(inputStream);
			IOUtils.closeQuietly(inputStream);
			content = temp;
		}
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public java.io.InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(java.io.InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("mediaId     : " + mediaId + "\n");
		builder.append("createTime  : " + DateUtils.format(createTime, 8));
		builder.append("filename    : " + filename + "\n");
		String c = content == null ? "" : (content.length > 1024 ? "..." : (new String(content)));
		builder.append("content     : " + c + "\n");
		builder.append("mediaType   : " + mediaType + "\n");
		builder.append("description : " + (description == null ? "" : description) + "\n");
		return builder.toString();
	}

}
