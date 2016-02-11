/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.util;

import java.io.IOException;
import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
public class WebContext {
	
	public static FacesContext getCurrentInstance(){
		return FacesContext.getCurrentInstance();
	}
	
	public static UIViewRoot getViewRoot(){
		return getCurrentInstance().getViewRoot();
	}

	public static Locale getLocale(){
		return getViewRoot().getLocale();
	}
	
	public static void redirect(String page) throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect(page);
	}
	
	public static HttpSession getSession(){
		return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true));
	}
	
	public static void invalidateSession(){
		getSession().invalidate();
	}
}
