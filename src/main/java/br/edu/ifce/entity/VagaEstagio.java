package br.edu.ifce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.util.entity.IGenericEntity;


@Entity
@Table(name = "vaga_estagio")
@NamedQueries({ @NamedQuery(name = "VagaEstagio", query = "Select v from VagaEstagio v") })
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
	private String area;
	
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
	
}
