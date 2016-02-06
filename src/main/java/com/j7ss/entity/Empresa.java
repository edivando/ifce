package com.j7ss.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.j7ss.util.DAO;
import com.j7ss.util.DAOException;
import com.j7ss.util.IGenericEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@Entity
@Table(name = "empresa")
public class Empresa implements IGenericEntity<Empresa>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idEmpresa;
	@Getter @Setter
	private Integer idUsuario;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private String email;
	@Getter @Setter
	private String telefone;
	@OneToOne
	@Getter @Setter
	private Endereco endereco;
	@Getter @Setter
	private String supervisor;
	@Getter @Setter
	private String cargoSupervisor;
	@Getter @Setter
	private String telefoneSupervisor;
	@Getter @Setter
	private String cnpj;
	@Getter @Setter
	private String site;
	@Getter @Setter
	private String ramoAtividade;
	@OneToMany
	@Getter @Setter
	private List<VagaEstagio> vagaEstagios;
	
	public Empresa(){
		endereco = new Endereco();
	}
	
//## Builder
	public Empresa idEmpresa(Integer idEmpresa){
		this.idEmpresa = idEmpresa;
		return this;
	}
	
	public Empresa idUsuario(Integer idUsuario){
		this.idUsuario = idUsuario;
		return this;
	}
	
	public Empresa nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public Empresa email(String email){
		this.email = email;
		return this;
	}
	
	public Empresa supervisor(String supervisor){
		this.supervisor = supervisor;
		return this;
	}
	
	public Empresa cargoSupervisor(String cargoSupervisor){
		this.cargoSupervisor = cargoSupervisor;
		return this;
	}
	
	public Empresa telefoneSupervisor(String telefoneSupervisor){
		this.telefoneSupervisor = telefoneSupervisor;
		return this;
	}
	
	public Empresa cnpj(String cnpj){
		this.cnpj = cnpj;
		return this;
	}
	
	public Empresa site(String site){
		this.site = site;
		return this;
	}
	
	public Empresa ramoAtividade(String ramoAtividade){
		this.ramoAtividade = ramoAtividade;
		return this;
	}
	
	
//## DAO
	private static DAO<Empresa> dao = new DAO<Empresa>(Empresa.class);
	
	@Override
	public Empresa save() throws DAOException{
		return idEmpresa == null ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(idEmpresa);
	}
	
	public static List<Empresa> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
	
	public static Empresa findByIdUsuario(Integer idUsuario){
		return dao.findOneByQuery("SELECT e FROM Empresa e WHERE e.idUsuario = ?1" , idUsuario);
	}
}
