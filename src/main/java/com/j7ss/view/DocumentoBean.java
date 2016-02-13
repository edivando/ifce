/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TabChangeEvent;

import com.github.rjeschke.txtmark.Processor;
import com.j7ss.entity.Documento;
import com.j7ss.entity.DocumentoKey;
import com.j7ss.util.BasicView;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class DocumentoBean extends BasicView<Documento>{
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
	public void save() {
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
		super.save();
	}
	
    public void onTabChange(TabChangeEvent event) {
    	getHtmlPage();
    }
    
    public String getHtmlPage() {
		return htmlPage = Processor.process(getEntity().getHtmlPage());
	}
}
