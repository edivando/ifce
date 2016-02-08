package com.j7ss.entity;

import java.util.Date;
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
@Table(name = "vaga_estagio")
public class VagaEstagio implements IGenericEntity<VagaEstagio>{
	
	private static final long serialVersionUID = 1L;
	
	enum Turno{ MANHA, TARDE, NOITE} 

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idVaga;
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private String descricao;
	@Getter @Setter
	private String requisitos;
	@Getter @Setter
	private String atividades;
	@Getter @Setter
	private String beneficios;
	@Getter @Setter
	private Integer cargaHoraria;
	@Getter @Setter
	private Double remuneracao;
	@Getter @Setter
	private Turno turno;
	@Getter @Setter
	private Date horaInicioEstagio;
	@Getter @Setter
	private Date horaFimOuIntervalo;
	@Getter @Setter
	private Date horaRetorno;
	@Getter @Setter
	private Date horaFimEstagio;
	@Getter @Setter
	private Date vigenciaInicio;
	@Getter @Setter
	private Date vigenciaFim;
	@Getter @Setter
	private Double valorTransporte;
	@Getter @Setter
    private Integer apoliceNumero;
	@Getter @Setter
	private String apoliceEmpresa;
	@Getter @Setter
    private String supervisorEstagio;
    @Getter @Setter
    private String supervisorCargo;
    
	@Override
	public boolean isNew() {
		return idVaga == null;
	}
    
//## Builder
	public VagaEstagio idVaga(Integer idVaga){
		this.idVaga = idVaga;
		return this;
	}
	
	public VagaEstagio nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public VagaEstagio descricao(String descricao){
		this.descricao = descricao;
		return this;
	}
	
	public VagaEstagio requisitos(String requisitos){
		this.requisitos = requisitos;
		return this;
	}
	
	public VagaEstagio atividades(String atividades){
		this.atividades = atividades;
		return this;
	}
	
	public VagaEstagio beneficios(String beneficios){
		this.beneficios = beneficios;
		return this;
	}
	
	public VagaEstagio cargaHoraria(Integer cargaHoraria){
		this.cargaHoraria = cargaHoraria;
		return this;
	}
	
	public VagaEstagio remuneracao(Double remuneracao){
		this.remuneracao = remuneracao;
		return this;
	}
	
	public VagaEstagio turno(Turno turno){
		this.turno = turno;
		return this;
	}
	
	public VagaEstagio horaInicioEstagio(Date horaInicioEstagio){
		this.horaInicioEstagio = horaInicioEstagio;
		return this;
	}
	
	public VagaEstagio horaFimOuIntervalo(Date horaFimOuIntervalo){
		this.horaFimOuIntervalo = horaFimOuIntervalo;
		return this;
	}
	
	public VagaEstagio horaRetorno(Date horaRetorno){
		this.horaRetorno = horaRetorno;
		return this;
	}
	
	public VagaEstagio horaFimEstagio(Date horaFimEstagio){
		this.horaFimEstagio = horaFimEstagio;
		return this;
	}
	
	public VagaEstagio vigenciaInicio(Date vigenciaInicio){
		this.vigenciaInicio = vigenciaInicio;
		return this;
	}
	
	public VagaEstagio vigenciaFim(Date vigenciaFim){
		this.vigenciaFim = vigenciaFim;
		return this;
	}
	
	public VagaEstagio valorTransporte(Double valorTransporte){
		this.valorTransporte = valorTransporte;
		return this;
	}
	
	public VagaEstagio apoliceNumero(Integer apoliceNumero){
		this.apoliceNumero = apoliceNumero;
		return this;
	}
	
	public VagaEstagio apoliceEmpresa(String apoliceEmpresa){
		this.apoliceEmpresa = apoliceEmpresa;
		return this;
	}
	
	public VagaEstagio supervisorEstagio(String supervisorEstagio){
		this.supervisorEstagio = supervisorEstagio;
		return this;
	}
	
	public VagaEstagio supervisorCargo(String supervisorCargo){
		this.supervisorCargo = supervisorCargo;
		return this;
	}

	
//## DAO
	private static DAO<VagaEstagio> dao = new DAO<VagaEstagio>(VagaEstagio.class);
	
	@Override
	public VagaEstagio save() throws DAOException{
		return isNew() ? dao.add(this) : dao.update(this);
	}

	@Override
	public boolean remove() throws DAOException {
		return dao.remove(idVaga);
	}
	
	public static List<VagaEstagio> findAll(){
		return dao.findAll();
	}
	
	public static Long countAll(){
		return dao.countAll();
	}

}
