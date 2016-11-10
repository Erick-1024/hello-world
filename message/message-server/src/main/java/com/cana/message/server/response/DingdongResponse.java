package com.cana.message.server.response;


public class DingdongResponse {


	private String code;
	private String msg;
	private String result;

	public DingdongResponse() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "DingdongResponse [code=" + code + ", msg=" + msg + ", result=" + result + "]";
	}

}
