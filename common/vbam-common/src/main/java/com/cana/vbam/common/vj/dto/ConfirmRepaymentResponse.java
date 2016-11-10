package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.BaseResponse;

/**
 * 确定还款响应
 * @author tangyihong
 *
 */
public class ConfirmRepaymentResponse extends BaseResponse{
	
	private static final long serialVersionUID = -6905900986785153042L;

	private ConfirmRepaymentResponseData data;

	public ConfirmRepaymentResponseData getData() {
		return data;
	}

	public void setData(ConfirmRepaymentResponseData data) {
		this.data = data;
	}
	

}
