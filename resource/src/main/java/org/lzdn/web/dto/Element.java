package org.lzdn.web.dto;

import com.alibaba.fastjson.JSONObject;

public class Element extends JSONObject {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	
	public Element() {
	}

	public Element(String key, Object value) {
		this.put(key, value);
	}

}
