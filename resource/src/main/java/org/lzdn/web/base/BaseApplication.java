package org.lzdn.web.base;

import org.glassfish.jersey.server.ResourceConfig;

public class BaseApplication extends ResourceConfig {

	public BaseApplication() {
		packages("org.lzdn.web.api.service.spring");
	}

}
