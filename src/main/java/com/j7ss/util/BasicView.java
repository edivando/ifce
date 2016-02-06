package com.j7ss.util;

import java.io.Serializable;
import java.util.List;

import com.j7ss.util.DAOException;
import com.j7ss.util.IGenericEntity;
import com.j7ss.util.Messages;

import lombok.Getter;
import lombok.Setter;

public abstract class BasicView<T extends IGenericEntity<T>> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Getter @Setter 
	protected T entity;
	@Getter
	protected List<T> entitys;

	@Getter
	public boolean form = false;
	
	public void grid(){
		entity = null;
		entitys = null;
		form = false;
	}
	
	public void form(){
		form = true;
	}
	
	public void save(){
		try {
			entity.save();
			onSave();
		} catch (DAOException e) {
			onError(e);
		}
		grid();
	}
	
	public void remove(T entity){
		try {
			if (entity.remove()){
				onRemove(entity);
			}
		} catch (DAOException e) {
			onError(e);
		}
		grid();
	}
	
	
	public void onSave(){
		Messages.showGrowlInfo("Entidade", "Entidade salva com sucesso!");
	}
	
	public void onRemove(T entity){
		Messages.showGrowlInfo("Entidade", "Entidade removido com sucesso!");
	}
	
	public void onError(Exception e){
		Messages.showGrowlErro("Entidade", e.getMessage());
	}
}
