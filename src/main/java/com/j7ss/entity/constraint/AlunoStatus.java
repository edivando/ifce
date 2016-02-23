package com.j7ss.entity.constraint;

import lombok.Getter;

public enum AlunoStatus {

	NOVO("label-primary", "Novo"), 
	VALIDO("label-success", "Válido"), 
	INVALIDO("label-danger", "Inválido");
	
	@Getter
	private String color; 
	@Getter
	private String title;
	
	private AlunoStatus(String color, String title) {
		this.color = color;
		this.title = title;
	}
}
