package com.j7ss.view.convert;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.j7ss.entity.Curso;
import com.j7ss.view.professor.ProfessorCadastroBean;

import lombok.Setter;

@ManagedBean
@ViewScoped
public class ProfessorConverter implements Converter, Serializable {

private static final long serialVersionUID = 1L;
	
	@Setter
	@ManagedProperty(value="#{professorCadastroBean}")
	private ProfessorCadastroBean professorCadastroBean;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		return professorCadastroBean.getCursoByNome(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		return (value != null) ? ((Curso) value).getNome() : null;
	}

}
