package com.cana.netdisk.dao.projo;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.io.IOUtils;

public class Media implements Serializable{

	private static final long serialVersionUID = -5422760841578788139L;

	protected String mediaId;

	protected Date createTime;

	protected String filename;

	protected long length;

	protected byte[] content;

	transient protected InputStream inputStream;

	protected String mediaType;

	protected String type;

	protected String description;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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
			IOUtils.closeQuietly(inputStream);
			content = temp;
		}
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
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
	
}
