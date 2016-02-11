/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Setter;

import com.j7ss.entity.Aluno;
import com.j7ss.entity.DocumentoAluno;
import com.j7ss.entity.DocumentoCurso;
import com.j7ss.entity.DocumentoStatus;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class HomeAlunoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	public List<DocumentoAluno> getDocumentoCursos(){
		for (int i = 0; i < getDocCursos().size(); i++) {
			if(!isEqualDocCursoAlunoByIndex(i)){
				getDocAlunos().add(
					new DocumentoAluno(getAluno())
						.documento(getDocCursos().get(i).getDocumento()).ordem(i)
						.status((i == 0 || !getDocCursos().get(i).getDocumento().getObrigatorio()) ? DocumentoStatus.DISPONIVEL : DocumentoStatus.INDISPONIVEL) );
			}
		}
		return getDocAlunos();
	}
	
//	private boolean verifyUpdate(){
//		if(loginBean.getAluno().getCurso().getDocumentoCursos().size() != loginBean.getAluno().getDocumentosAluno().size()){
//			return true;
//		}else{
//			for (int i = 0; i < loginBean.getAluno().getDocumentosAluno().size(); i++) {
//				if(!loginBean.getAluno().getCurso().getDocumentoCursos().get(i).getDocumento().getIdDocumento().equals(loginBean.getAluno().getDocumentosAluno().get(i).getDocumento().getIdDocumento())){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	private DocumentoAluno getDocumentoAluno(Integer id){
		for(DocumentoAluno docAluno : loginBean.getAluno().getDocumentosAluno()){
			if(id.equals(docAluno.getDocumento().getIdDocumento())){
				return docAluno;
			}
		}
		return null;
	}
	
	private boolean isEqualDocCursoAlunoByIndex(int id){
		if(getDocCursos().size() <= id && getDocAlunos().size() <= id){
			return getDocCursos().get(id).getDocumento().getIdDocumento().equals( getDocAlunos().get(id).getDocumento().getIdDocumento() );
		}
		return false;
	}
	
	
	private List<DocumentoAluno> getDocAlunos(){
		return loginBean.getAluno().getDocumentosAluno();
	}
	
	private List<DocumentoCurso> getDocCursos(){
		return loginBean.getAluno().getCurso().getDocumentoCursos();
	}
	
	private Aluno getAluno(){
		return loginBean.getAluno();
	}

}
