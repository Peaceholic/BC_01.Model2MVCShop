package com.model2.mvc.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Action {
	
	private ServletContext servletContext;
	
	public Action(){
		System.out.println("\n* [ Action : default Constructor() ] ");
	}	
	
	public ServletContext getServletContext() {
		System.out.println("\n* [ Action : getServletContext() ] ");
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		System.out.println("\n* [ Action : setServletContext() ] ");
		this.servletContext = servletContext;
	}

	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}