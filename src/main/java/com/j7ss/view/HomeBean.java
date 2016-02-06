package com.j7ss.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@ManagedBean
@ViewScoped
public class HomeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@Getter
	private String text = "aaaaaa";
}
