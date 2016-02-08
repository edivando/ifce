package com.j7ss.view.convert;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lombok.Setter;

import com.j7ss.entity.Instituicao;
import com.j7ss.view.InstituicaoBean;

@ManagedBean
@ViewScoped
public class InstituicaoConverter implements Converter {

	@Setter
	@ManagedProperty(value="#{instituicaoBean}")
	private InstituicaoBean instituicaoBean;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return instituicaoBean.getEntityByNome(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (value != null) ? ((Instituicao) value).getNome() : null;
	}

}
