package br.edu.ifce.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import br.edu.ifce.util.entity.IGenericEntity;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@Entity
@Table(name = "vaga_estagio_atividade_diaria")
public class VagaEstagioAtividadeDiaria implements IGenericEntity<VagaEstagioAtividadeDiaria>{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer idAtividade;
	
	@Getter @Setter
	private Date data;
	
	@Getter @Setter
	private String descricao;
	
	@Getter @Setter
	private Integer quantidadeHoras;
}
