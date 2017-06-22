package org.lzdn.web.dto;

public class Body extends Element {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public Body() {
	}

	public Body(String key, Object value) {
		this.put(key, value);
	}

}
