package com.cana.asset.dao.custom.po;

/**
 * 查询资产池规模时，mybatis返回的对象
 * @author XuMeng
 *
 */
public class SpecialProgramGrossResult {

	private String id;
	private Long gross;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getGross() {
		return gross;
	}

	public void setGross(Long gross) {
		this.gross = gross;
	}

}
