package br.edu.ifce.view.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifce.view.LoginBean;


public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private List<String> publicPages;
	
	public AuthorizationListener() {
		publicPages = new ArrayList<String>();
		publicPages.add("index");
		publicPages.add("login");
		publicPages.add("erro404");
		publicPages.add("erro500");
		publicPages.add("erro-acessoNegado");	
		publicPages.add("cadastro");
	}
	


	@Override
	public void beforePhase(PhaseEvent event) {	
		FacesContext ctx = event.getFacesContext();	
		if (ctx.getViewRoot() != null) {
			String paginaDestino = ctx.getViewRoot().getViewId();
			
			// imprime a URL destino
			if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
				
				System.out.println(event.getPhaseId()+" - "+paginaDestino);
				paginaDestino = getNamePage(paginaDestino);
				HttpSession session = (HttpSession) event.getFacesContext().getExternalContext().getSession(false);

				LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");			
				if( !isPermissionPublicPage( paginaDestino ) ){
					if(loginBean == null || loginBean.getUsuario() == null || loginBean.getUsuario().getIdUsuario() == null){	
						redirect("login.html");
					}else if( !loginBean.isPagePermission( paginaDestino ) ){
						redirect("erro-acessoNegado.html");
					}	
				}		
			}
		}
	}
	
	private void redirect(String page){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, page);
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext ctx = event.getFacesContext();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();

		String paginaDestino = getNamePage( ctx.getViewRoot().getViewId() );
	}
	
	private String getNamePage(String page){
		return page.replaceAll("error", "").replaceAll(".xhtml", "").replaceAll("/", "").replaceAll(".html", "");
	}
	
	private boolean isPermissionPublicPage(String page){
		return isPermission(publicPages, page);
	}
	
	private boolean isPermission(List<String> listPage, String page){
		for (String string : listPage) 
			if(string.equals(page)){
				return true;
			}
		return false;
	}
	

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
	

}