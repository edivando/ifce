/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.instituicao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.j7ss.entity.DocumentoVagaEstagio;
import com.j7ss.util.BasicView;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class InstituicaoHomeBean extends BasicView<DocumentoVagaEstagio> {
	
	private static final long serialVersionUID = 1L;
	
//	@Setter
//	@ManagedProperty(value="#{loginBean}")
//	private LoginBean loginBean;
//	
//	@Setter
//	private DocumentoVagaEstagioMessage docMessage;
//	
//	@Override
//	public List<DocumentoVagaEstagio> getEntitys() {
//		return entitys == null ? entitys = DocumentoVagaEstagio.findByDocumentoStatus(DocumentoStatus.AGUARDANDO_VERIFICACAO) : entitys;
//	}
//	
//	public String getDocPage(){
//		return new DocumentoParse(getEntity()).toPage();
//	}
//	
//	public void saveErro(){
//		try {
//			entity.status(DocumentoStatus.VERIFICADO_COM_ERRO).save();
//			getDocMessage().date(new Date()).usuario(loginBean.getUsuario()).documentoVagaEstagio(entity).save();
//			entity.save();
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		grid();
//	}
//	
//	public void saveDownload(){
//		try {
//			entity.status(DocumentoStatus.DISPONIVEL_DOWNLOAD).save();
//			getDocMessage().date(new Date()).usuario(loginBean.getUsuario()).documentoVagaEstagio(entity).save();
//			entity.save();
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		grid();
//	}
//	
//	public DocumentoVagaEstagioMessage getDocMessage() {
//		return docMessage == null ? docMessage = new DocumentoVagaEstagioMessage() : docMessage;
//	}
	

}
