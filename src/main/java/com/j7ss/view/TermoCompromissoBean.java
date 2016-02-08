package com.j7ss.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.Empresa;
import com.j7ss.entity.Usuario;
import com.j7ss.entity.VagaEstagio;

import lombok.Getter;
import lombok.Setter;

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
	private VagaEstagio vagaEstagio = new VagaEstagio();
	
	@Setter
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public Aluno getAluno(){
		return loginBean.getAluno();
	}
	
	public Usuario getUsuario(){
		return loginBean.getUsuario();
	}
	
	public VagaEstagio getVagaEstagio() {
		return vagaEstagio == null ? vagaEstagio = new VagaEstagio() : vagaEstagio;
	}
	
	public List<Empresa> getEmpresas(){
		return empresas == null ? empresas = Empresa.findAll() : empresas;
	}
	

}
