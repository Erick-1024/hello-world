package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

public class ContractListReqDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4701904543540221851L;

	
    
    private int page=1;
    
    private int pageSize=5;

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
