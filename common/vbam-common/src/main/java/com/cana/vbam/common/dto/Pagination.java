package com.cana.vbam.common.dto;

import java.io.Serializable;

public class Pagination implements Serializable {

	private static final long serialVersionUID = -5499445556373073337L;

	private int page = 1;
	
    private int pageSize = 10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
