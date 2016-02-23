/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Setter;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.Documento;
import com.j7ss.entity.DocumentoVagaEstagio;
import com.j7ss.entity.constraint.DocumentoParse;
import com.j7ss.entity.constraint.DocumentoStatus;
import com.j7ss.util.BasicView;
import com.j7ss.util.DAOException;
import com.j7ss.util.WebContext;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class REMOVEAlunoDocumentoBean extends BasicView<Aluno>{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private DocumentoVagaEstagio documentoVagaEstagio;
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	private String docPage;
	
	
	public void saveDocumento(DocumentoStatus status){
		try {
			documentoVagaEstagio.status(status).save();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getDocPage() {
		if(docPage == null){
			id = Integer.parseInt( WebContext.getExternalContext().getRequestParameterMap().get("id") );
			setDocumentoAluno();
			docPage = new DocumentoParse(documentoVagaEstagio).toPage();
		}
		return docPage;
	}
	
	private void setDocumentoAluno(){
		documentoVagaEstagio = getDocumentoVagaEstagio();
		if(documentoVagaEstagio == null){
			for (Documento doc : loginBean.getUsuario().getAluno().getCurso().getDocumentos()) {
				if(doc.getId().equals(id)){
					//TODO: AlunoVagaEstagioBean + AlunoHomeBean + AlunoDocumentoBean + AlunoAtividadeDiaria
//					documentoVagaEstagio = new DocumentoVagaEstagio(loginBean.getUsuario().getAluno())
//										.documento(doc)
//										.ordem(loginBean.getUsuario().getAluno().getDocumentos().size())
//										.status(DocumentoStatus.DISPONIVEL);
//					try {
//						loginBean.getUsuario().getAluno().addDocumento(documentoAluno);
//					} catch (DAOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}
		}
	}
	
	private DocumentoVagaEstagio getDocumentoVagaEstagio(){
		//TODO: AlunoVagaEstagioBean + AlunoHomeBean + AlunoDocumentoBean + AlunoAtividadeDiaria
//		if(loginBean.getUsuario().getAluno().getDocumentosAluno().size() != 0){
//			for (DocumentoVagaEstagio doc : loginBean.getUsuario().getAluno().getDocumentosAluno()) {
//				if(doc.getDocumento() != null &&  doc.getDocumento().getId().equals(id)){
//					return doc;
//				}	
//			}
//		}
		return null;
	}
	
}
