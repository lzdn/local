package org.lzdn.web.base;

import org.lzdn.common.utils.Json;

import com.alibaba.fastjson.JSONObject;

public class BaseResource {

	public static final String success = "success";
	public static final String fail = "fail";

	public Object simpleJsonToDto(String requestContext, Object model) {

		return Json.simpleJsonToDto(requestContext, model);
	}

	public JSONObject result() {
		JSONObject result = Json.createResult();
		//setHeader(result, key, obj);

		return result;
	}

	public void setHeader(JSONObject jSONObject, String key, Object obj) {

		Json.setHeader(jSONObject, key, obj);
	}

	public void setBody(JSONObject jSONObject, String key, Object obj) {

		Json.setBody(jSONObject, key, obj);
	}
}
