package org.lzdn.web.dto;

public class Result extends Element {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public static final String header_ = "header";
	public static final String body_ = "body";

	public Result() {
	}

	public Result(Header header, Body body) {
		this.put(header_, header);
		this.put(body_, body);

	}

	public void setBody(Body body) {
		this.put(body_, body);
	}

	public void setHeader(Header header) {
		this.put(header_, header);
	}
}
