package com.j7ss.util;

import java.io.IOException;
import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
}
