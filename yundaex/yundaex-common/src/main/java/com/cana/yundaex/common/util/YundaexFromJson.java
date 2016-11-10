package com.cana.yundaex.common.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cana.yundaex.common.dto.YundaexTstationInfoResponse;
import com.google.gson.Gson;

public class YundaexFromJson {

	/**
	 * 拿韵达json串中的data
	 * 
	 * @param returnStr
	 * @return
	 * @throws JSONException
	 */
	public static String getData(String returnStr) throws JSONException {
		JSONObject json = new JSONObject(returnStr);
		Object data = json.get("data");
		if (data == null) {
			return null;
		}
		return data.toString();
	}

	public static void main(String[] args) throws JSONException {
		Gson gson = new Gson();
		String aa = "";
		getData(aa);
		gson.fromJson(aa,YundaexTstationInfoResponse.class);
}}
