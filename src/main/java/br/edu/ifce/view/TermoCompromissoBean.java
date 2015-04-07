package br.edu.ifce.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.dao.EmpresaDAO;
import br.edu.ifce.entity.Aluno;
import br.edu.ifce.entity.Empresa;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@ManagedBean
@ViewScoped
public class TermoCompromissoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Empresa empresa = new Empresa();
	private List<Empresa> empresas;
	
	@Setter
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	
	public Aluno getAluno(){
		return loginBean.getAluno();
	}
	
	public List<Empresa> getEmpresas(){
		return empresas == null ? empresas = empresaDAO.findAll() : empresas;
	}

}
