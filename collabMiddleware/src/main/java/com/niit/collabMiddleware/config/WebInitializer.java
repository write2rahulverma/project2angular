package com.niit.collabMiddleware.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {WebResolver.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {org.springframework.web.servlet.DispatcherServlet.class};
		
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
		
	}

}
