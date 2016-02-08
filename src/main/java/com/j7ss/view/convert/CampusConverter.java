package com.j7ss.view.convert;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lombok.Setter;

import com.j7ss.entity.Campus;
import com.j7ss.view.CampusBean;

@ManagedBean
@ViewScoped
public class CampusConverter implements Converter {

	@Setter
	@ManagedProperty(value="#{campusBean}")
	private CampusBean campusBean;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return campusBean.getEntityByNome(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (value != null) ? ((Campus) value).getNome() : null;
	}

}