package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * @author jiangzhou.Ren
 *分页参数
 */
public class ProjectListRequestDTO implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7107459035613656373L;


	/**
	 * 分页参数
	 */
	private int page;
	
	private int pageSize;
    
    /**
     *项目名称
     */
    private String name;
    
	/**
     *核心企业名称
     */
    private String coreCompanyName;
    
    /**
     *项目类型，平台、保理、租赁、小贷
     */
    private String type;
    
    /**
     *行业
     */
    private String coreIndustry;
    
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoreIndustry() {
		return coreIndustry;
	}

	public void setCoreIndustry(String coreIndustry) {
		this.coreIndustry = coreIndustry;
	}

	

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
