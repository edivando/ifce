package com.j7ss.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "endereco")
public class Endereco implements IGenericEntity<Endereco>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idEndereco;
	@Getter @Setter
	private String endereco;
	@Getter @Setter
	private String bairro;
	@Getter @Setter
	private String cep;
	@Getter @Setter
	private String uf;
	@Getter @Setter
	private String cidade;
	
	
	//## Builder
	public Endereco idEndereco(Integer idEndereco){
		this.idEndereco = idEndereco;
		return this;
	}
	
	public Endereco endereco(String endereco){
		this.endereco = endereco;
		return this;
	}
	
	public Endereco bairro(String bairro){
		this.bairro = bairro;
		return this;
	}
	
	public Endereco cep(String cep){
		this.cep = cep;
		return this;
	}
	
	public Endereco uf(String uf){
		this.uf = uf;
		return this;
	}
	
	public Endereco cidade(String cidade){
		this.cidade = cidade;
		return this;
	}
	
//## DAO
	private static DAO<Endereco> dao = new DAO<Endereco>(Endereco.class);
	
	@Override
	public Endereco save() throws DAOException{
		return idEndereco == null ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(idEndereco);
	}
	
	public static List<Endereco> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}
}
