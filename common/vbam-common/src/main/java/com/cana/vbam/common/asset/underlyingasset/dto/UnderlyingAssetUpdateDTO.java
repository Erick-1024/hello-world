package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UnderlyingAssetUpdateDTO implements Serializable{
	
	private static final long serialVersionUID = -93968759546915339L;

	private List<String> underlyingAssetIdList;
	
	private boolean isAndOperation;
	
	private Map<Integer,Integer> operatePositionAndNum;
	
	private int operationNum;

	public List<String> getUnderlyingAssetIdList() {
		return underlyingAssetIdList;
	}

	public void setUnderlyingAssetIdList(List<String> underlyingAssetIdList) {
		this.underlyingAssetIdList = underlyingAssetIdList;
	}
	
	public boolean isAndOperation() {
		return isAndOperation;
	}

	public void setAndOperation(boolean isAndOperation) {
		this.isAndOperation = isAndOperation;
	}

	public Map<Integer, Integer> getOperatePositionAndNum() {
		return operatePositionAndNum;
	}

	public void setOperatePositionAndNum(Map<Integer, Integer> operatePositionAndNum) {
		this.operatePositionAndNum = operatePositionAndNum;
	}

	public int getOperationNum() {
		return operationNum;
	}

	public void setOperationNum(int operationNum) {
		this.operationNum = operationNum;
	} 
}
