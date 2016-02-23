/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TabChangeEvent;

import com.github.rjeschke.txtmark.Processor;
import com.j7ss.entity.Documento;
import com.j7ss.entity.constraint.DocumentoKey;
import com.j7ss.util.BasicView;
import com.j7ss.util.DAOException;
import com.j7ss.util.Messages;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AdminDocumentoBean extends BasicView<Documento>{
	private static final long serialVersionUID = 1L;

	private String htmlPage = "";
	
	@Override
	public Documento getEntity() {
		return entity == null ? entity = new Documento() : entity;
	}
	
	@Override
	public List<Documento> getEntitys() {
		return entitys == null ? entitys = Documento.findAll() : entitys;
	}
	
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Documentos", "Documento <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Documento entity) {
		Messages.showGrowlInfo("Documentos", "Documento <strong>{0}</strong> removido com sucesso!", entity.getNome());
	}
	
	@Override
	public void save() {
		setKeys();
		super.save();
	}
	
	private void setKeys(){
		StringBuilder builder = new StringBuilder();
		Set<String> keys = new HashSet<>();

		if(entity.getHtmlPage() != null && entity.getHtmlPage().length() > 0){
			for (DocumentoKey key : DocumentoKey.values()) {
				if(entity.getHtmlPage().contains(key.getKey())){
					keys.add(key.getKey());
				}
			}
		}
		
		for (String key : keys) {
			builder.append(key).append(",");
		}
		entity.setKeys(builder.toString());
	}	
	
    public void onTabChange(TabChangeEvent event) {
    	getHtmlPage();
    }
    
    public String getHtmlPage() {
    	if(!htmlPage.equals("")){
    		setKeys();
        	try {
        		entity.save();
        	} catch (DAOException e) {
        	}
    	}
    	if(getEntity().getHtmlPage() != null){
    		 htmlPage = Processor.process(getEntity().getHtmlPage());
    	}
		return htmlPage;
	}
}
