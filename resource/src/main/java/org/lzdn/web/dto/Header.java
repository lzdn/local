package org.lzdn.web.dto;

public class Header extends Element {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public Header() {
	}

	public Header(String key, Object value) {
		this.put(key, value);
	}
}
