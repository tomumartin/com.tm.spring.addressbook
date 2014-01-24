package com.blizzard.addressbook.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
* @author twmartin
* @since 1/23/14
*/
public class Initializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	public void onStartup(final ServletContext servletContext)
			throws ServletException {
		final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(ApplicationConfig.class);
		servletContext.addListener(new ContextLoaderListener(ctx));

		ctx.setServletContext(servletContext);

		final ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
				new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}