/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view.aluno;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;

import com.j7ss.entity.Documento;
import com.j7ss.entity.DocumentoCurso;
import com.j7ss.entity.DocumentoVagaEstagio;
import com.j7ss.entity.VagaEstagio;
import com.j7ss.entity.VagaEstagioAtividadeDiaria;
import com.j7ss.entity.constraint.DocumentoParse;
import com.j7ss.entity.constraint.DocumentoStatus;
import com.j7ss.util.DAOException;
import com.j7ss.view.LoginBean;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AlunoHomeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Setter
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;
	
	private VagaEstagio vagaEstagio; 
	
	@Setter
	private VagaEstagioAtividadeDiaria atividadeDiaria;
	
	private List<VagaEstagio> vagasEstagio;
	
	// DocumentView
	private String docPage;
	private DocumentoVagaEstagio documentoVagaEstagio;
	
	@Getter
	private boolean grid = true;
	@Getter
	private boolean formEstagio;
	@Getter
	private boolean gridAtividade;
	@Getter
	private boolean documentos;
	@Getter
	private boolean documentoView;
	@Getter
	private boolean formAtividade;
	
	public void documentos(){
		grid = false;
		formEstagio = false;
		formAtividade = false;
		gridAtividade = false;
		documentos = true;
		documentoView = false;
	}
	
	public void documentoView(){
		grid = false;
		formEstagio = false;
		formAtividade = false;
		gridAtividade = false;
		documentos = false;
		documentoView = true;
	}
	
	public void formEstagio(){
		grid = false;
		formEstagio = true;
		formAtividade = false;
		gridAtividade = false;
		documentos = false;
		documentoView = false;
	}
	
	public void formAtividade(){
		grid = false;
		formEstagio = false;
		formAtividade = true;
		gridAtividade = false;
		documentos = false;
		documentoView = false;
	}
	
	public void gridAtividade(){
		grid = false;
		formEstagio = false;
		formAtividade = false;
		gridAtividade = true;
		documentos = false;
		documentoView = false;
	}
	
	public void grid(){
		grid = true;
		formEstagio = false;
		formAtividade = false;
		gridAtividade = false;
		documentos = false;
		documentoView = false;
		vagaEstagio = null;
	}
	
	public VagaEstagio getVagaEstagio() {
		return vagaEstagio == null ? vagaEstagio = new VagaEstagio() : vagaEstagio;
	}
	
	public void setVagaEstagio(VagaEstagio vagaEstagio) {
		this.vagaEstagio = vagaEstagio;
		int i = 0;
		boolean first = true;
		for (DocumentoCurso dc : getDocCursos()) {
			if(dc.getDocumento().getExtra()){									// Documento Obrigatório
				if(!isDocAluno(dc.getDocumento())){
					//TODO: AlunoVagaEstagioBean + AlunoHomeBean + AlunoDocumentoBean + AlunoAtividadeDiaria
					getDocVagaEstagio().add( new DocumentoVagaEstagio()
											.vagaEstagio(vagaEstagio)
											.documento(dc.getDocumento())
											.ordem(i)
											.status(DocumentoStatus.DISPONIVEL) );
				}
			}else if(!isEqualDocCursoAndAlunoByIndex(i)){								// Próximo Document
					//TODO: AlunoVagaEstagioBean + AlunoHomeBean + AlunoDocumentoBean + AlunoAtividadeDiaria
				getDocVagaEstagio().add( new DocumentoVagaEstagio()
											.vagaEstagio(vagaEstagio)
											.documento(dc.getDocumento())
											.ordem(i)
											.status(first ?  DocumentoStatus.DISPONIVEL : DocumentoStatus.INDISPONIVEL) );
				first = false;
			}
			i++;
		}
	}
	
	public List<VagaEstagio> getVagasEstagio() {
		return vagasEstagio == null ? vagasEstagio = VagaEstagio.findByAluno(loginBean.getUsuario().getAluno()) : vagasEstagio;
//		return loginBean.getUsuario().getAluno().getVagasEstagio();
	}
	
	public VagaEstagioAtividadeDiaria getAtividadeDiaria() {
		return atividadeDiaria == null ? atividadeDiaria = new VagaEstagioAtividadeDiaria() : atividadeDiaria;
	}
	
	public void save(){
		try {
			vagaEstagio.save();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		grid();
	}
	
	public void saveAtividadeDiaria(){
		try {
			if(atividadeDiaria.isNew()){
				vagaEstagio.addAtividadeDiaria(atividadeDiaria).save();
			}else{
				atividadeDiaria.save();
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		gridAtividade();
	}
	
	public void removeAtividadeDiaria(VagaEstagioAtividadeDiaria atividadeDiaria){
		try {
			if(atividadeDiaria.remove()){
				System.out.println("Removed");
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		gridAtividade();
	}
	
	private boolean isEqualDocCursoAndAlunoByIndex(int id){
		if(getDocCursos() != null && getDocVagaEstagio() != null && getDocCursos().size() > id && getDocVagaEstagio().size() > id){
			return getDocCursos().get(id).getDocumento().getId().equals( getDocVagaEstagio().get(id).getDocumento().getId() );
		}
		return false;
	}
	
	private boolean isDocAluno(Documento doc){
		for (DocumentoVagaEstagio docVaga : getDocVagaEstagio()) {
			if(docVaga.getDocumento().equals(doc)){
				return true;
			}
		}
		return false;
	}
	
	public List<DocumentoVagaEstagio> getDocVagaEstagio(){
		return getVagaEstagio().getDocumentosVagaEstagio();
	}
	
	private List<DocumentoCurso> getDocCursos(){
		return loginBean.getUsuario().getAluno().getCurso().getDocumentoCursos();
	}
	
	
	
	
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
//			setDocumentoAluno();
			docPage = new DocumentoParse(documentoVagaEstagio).toPage();
		}
		return docPage;
	}
	
	public void setDocumentoVagaEstagio(DocumentoVagaEstagio documentoVagaEstagio) {
		this.documentoVagaEstagio = documentoVagaEstagio;
	}
	
	
	
	
	
	
	
	
//	private void setDocumentoAluno(){
//		documentoVagaEstagio = getDocumentoVagaEstagio();
//		if(documentoVagaEstagio == null){
//			for (Documento doc : loginBean.getUsuario().getAluno().getCurso().getDocumentos()) {
//				if(doc.getId().equals(id)){
//					//TODO: AlunoVagaEstagioBean + AlunoHomeBean + AlunoDocumentoBean + AlunoAtividadeDiaria
////					documentoVagaEstagio = new DocumentoVagaEstagio(loginBean.getUsuario().getAluno())
////										.documento(doc)
////										.ordem(loginBean.getUsuario().getAluno().getDocumentos().size())
////										.status(DocumentoStatus.DISPONIVEL);
////					try {
////						loginBean.getUsuario().getAluno().addDocumento(documentoAluno);
////					} catch (DAOException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//				}
//			}
//		}
//	}
//	
//	

}
