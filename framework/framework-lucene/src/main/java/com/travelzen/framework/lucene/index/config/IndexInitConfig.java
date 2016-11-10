package com.travelzen.framework.lucene.index.config;

/**
 * @author qifeifei
 */
public class IndexInitConfig {

	private String indexClassBeanName;
	private String DEFAULT_INDEX_PATH = "/data/tzjn/Index";
	private boolean isFileDirectoryIndex = true;
	private boolean isCreate = false;

	public String getIndexClassBeanName() {
		return indexClassBeanName;
	}

	public void setIndexClassBeanName(String indexClassBeanName) {
		this.indexClassBeanName = indexClassBeanName;
	}

	public String getDEFAULT_INDEX_PATH() {
		return DEFAULT_INDEX_PATH;
	}

	public void setDEFAULT_INDEX_PATH(String dEFAULT_INDEX_PATH) {
		DEFAULT_INDEX_PATH = dEFAULT_INDEX_PATH;
	}

	public boolean isFileDirectoryIndex() {
		return isFileDirectoryIndex;
	}

	public void setFileDirectoryIndex(boolean isFileDirectoryIndex) {
		this.isFileDirectoryIndex = isFileDirectoryIndex;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

}
