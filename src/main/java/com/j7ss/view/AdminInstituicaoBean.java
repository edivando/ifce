/*
 * @version     1.0.0
 * @author      Edivando J. Alves
 * @contact     edivando@j7ss.com ( http://www.j7ss.com )
 * 
 * @copyright  	Copyright 2010 - 2016 J7 Smart Solutions, all rights reserved.
 * 
 */
package com.j7ss.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

import com.j7ss.entity.Campus;
import com.j7ss.entity.Curso;
import com.j7ss.entity.Departamento;
import com.j7ss.entity.Documento;
import com.j7ss.entity.Instituicao;
import com.j7ss.util.BasicView;
import com.j7ss.util.DAOException;
import com.j7ss.util.Messages;

/**
 * 
 * @author Edivando Alves
 * @date  10/02/2016
 * 
 */
@ManagedBean
@ViewScoped
public class AdminInstituicaoBean extends BasicView<Instituicao>{
	private static final long serialVersionUID = 1L;
	
	@Getter
	private TreeNode root;
	@Getter @Setter
	private TreeNode selectedNode;

	@Setter
	private Campus campus;
	@Setter
	private Departamento departamento;
	private Curso curso;
	
	@Getter 
	private boolean showCampus;
	@Getter 
	private boolean showDepartamento;
	@Getter 
	private boolean showCurso;
	
	private List<Documento> documentos;
	@Getter @Setter
	private DualListModel<Documento> pickListDocumentos;
	
	@PostConstruct
	public void initPickListDocumentos(){
		pickListDocumentos = new DualListModel<Documento>(getDocumentos(), new ArrayList<Documento>());
	}
	
	@Override
	public void grid() {
		super.grid();
		back();
		this.campus = null;
		this.departamento = null;
		this.curso = null;
	}
	
	@Override
	public void setEntity(Instituicao entity) {
		super.setEntity(entity);
		reloadTree();
	}
	
	private void reloadTree(){
		// Instituicao
		if(getEntity() != null){
			root = new DefaultTreeNode(entity, null);
			// Campus
			if(entity.getCampus() != null){
				for (Campus campus : entity.getCampus()) {
					DefaultTreeNode campusNode = new DefaultTreeNode("campus", campus, root);
					// Departamentos
					if(campus.getDepartamentos() != null){
						for (Departamento departamento : campus.getDepartamentos()) {
							DefaultTreeNode departamentoNode = new DefaultTreeNode("departamento", departamento, campusNode);
							// Cursos
							if(departamento.getCursos() != null){
								for(Curso curso : departamento.getCursos()){
									new DefaultTreeNode("curso", curso, departamentoNode);
								}
							}
						}
					}
				}
			}
		}
	}
 
    public void onNodeSelect(NodeSelectEvent event) {
    	if(event.getTreeNode().getType().equals("campus")){
    		addCampus();
    		if( event.getTreeNode().getData() instanceof Campus){
    			setCampus( (Campus)event.getTreeNode().getData() );
    		}
    	}else if(event.getTreeNode().getType().equals("departamento")){
    		addDepartamento();
    		if( event.getTreeNode().getData() instanceof Departamento){
    			setDepartamento( (Departamento)event.getTreeNode().getData() );
    		}
    	}else if(event.getTreeNode().getType().equals("curso")){
    		addCurso();
    		if( event.getTreeNode().getData() instanceof Curso){
    			setCurso( (Curso)event.getTreeNode().getData() );
    		}
    	}
    }
    
    public void addCampus(){
    	campus = new Campus();
    	campus.setInstituicao(entity);
    	showCampus = true;
    	showDepartamento = false;
    	showCurso = false;
    }
    
    public void addDepartamento(){
    	departamento = new Departamento();
    	departamento.setCampus(campus);
    	showCampus = false;
    	showDepartamento = true;
    	showCurso = false;
    }
    
    public void addCurso(){
    	curso = new Curso();
    	curso.setDepartamento(departamento);
		initPickListDocumentos();
    	showCampus = false;
    	showDepartamento = false;
    	showCurso = true;
    }
    
    public void back(){
    	showCampus = false;
    	showDepartamento = false;
    	showCurso = false;
    	campus = null;
    	departamento = null;
    	curso = null;
    }
    
    public void removeCampus(){
    	if(campus != null){
    		try {
    			entity.removeCampus(campus);
				campus.remove();
				reloadTree();
	    		back();
	    		Messages.showGrowlInfo("Test", "test");
			} catch (DAOException e) {
				Messages.showGrowlInfo("Test", "test");
				e.printStackTrace();
			}
    	}
    }
    
    public void removeDepartamento(){
    	if(departamento != null){
    		try {
				departamento.remove();
				departamento.getCampus().removeDepartamento(departamento);
				reloadTree();
	    		back();	
	    		Messages.showGrowlInfo("Test", "test");
			} catch (DAOException e) {
				Messages.showGrowlInfo("Test", "test");
				e.printStackTrace();
			}
    	}
    }
    
    public void removeCurso(){
    	if(curso != null){
    		try {
    			curso.getDepartamento().removeCurso(curso);
				curso.remove();
				reloadTree();
	    		back();
	    		Messages.showGrowlInfo("Test", "test");
			} catch (DAOException e) {
				Messages.showGrowlInfo("Test", "test");
				e.printStackTrace();
			}
    	}
    }
    
    public void saveCampus() {
    	try {
    		if(campus.isNew()){
    			entity.addCampus(campus.save());
    		} 
    		campus.save();
    		reloadTree();
    		back();
    		Messages.showGrowlInfo("Test", "test");
		} catch (DAOException e) {
			Messages.showGrowlInfo("Test", "test");
			e.printStackTrace();
		}
    }
    
    public void saveDepartamento() {
    	try {
    		if(departamento.isNew()){
    			campus.addDepartamento(departamento.save());
    		}
    		departamento.save();
    		reloadTree();
    		back();
    		Messages.showGrowlInfo("Test", "test");
		} catch (DAOException e) {
			Messages.showGrowlInfo("Test", "test");
			e.printStackTrace();
		}
    }
    
    public void saveCurso() {
    	try {
    		if(curso.isNew()){
    			departamento.addCurso(curso.save());
    		}
			curso.save();
			curso.setDocumentos(pickListDocumentos.getTarget());
    		reloadTree();
    		back();
    		Messages.showGrowlInfo("Test", "test");
		} catch (DAOException e) {
			Messages.showGrowlInfo("Test", "test");
			e.printStackTrace();
		}
    }
    
	@Override
	public void onSave() {
		Messages.showGrowlInfo("Instituicao", "Instituicao <strong>{0}</strong> salvo com sucesso!", entity.getNome());
	}
	
	@Override
	public void onRemove(Instituicao instituicao) {
		Messages.showGrowlInfo("Instituicao", "Instituicao <strong>{0}</strong> removido com sucesso!", instituicao.getNome());
	}
	
	@Override
	public Instituicao getEntity() {
		return entity == null ? entity = new Instituicao() : entity;
	}
	
	@Override
	public List<Instituicao> getEntitys() {
		return entitys == null ? entitys = Instituicao.findAll() : entitys;
	}
	
	public List<Documento> getDocumentos() {
		return documentos = documentos == null ? Documento.findAll() : documentos;
	}
	
	public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Documento) item).getNome()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    } 
	
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    } 
	
	public Documento getDocumentoByNome(String nome){
		for(Documento documento : getDocumentos()){
			if(documento.getNome().equals(nome)) return documento;
		}
		return null;
	}


	
	public Campus getCampus() {
		return campus == null ? campus = new Campus() : campus;
	}
	
	public Departamento getDepartamento() {
		return departamento == null ? departamento = new Departamento() : departamento;
	}
	
	public Curso getCurso() {
		return curso == null ? curso = new Curso() : curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
		List<Documento> docs = new ArrayList<>();
		for(Documento docSource : getDocumentos()){
			boolean exist = false;
			for(Documento docTarget : curso.getDocumentos()){
				if(docSource.getIdDocumento().equals(docTarget.getIdDocumento())) exist = true;
			}
			if(!exist){
				docs.add(docSource);
			}
		}
		this.pickListDocumentos.setSource(docs);
		this.pickListDocumentos.setTarget(curso.getDocumentos());
	}
	
	public boolean getNovoDepartamento(){
		return campus == null ? false : showCampus && !campus.isNew();
	}
	
	public boolean getNovoCurso(){
		return departamento == null ? false : showDepartamento && !departamento.isNew();
	}
	
	
//## Button Remove Rendered	
	public boolean isBtnRemoveCampus(){
		return campus == null ? false : !campus.isNew(); 
	}
	
	public boolean isBtnRemoveDepartamento(){
		return departamento == null ? false : !departamento.isNew(); 
	}
	
	public boolean isBtnRemoveCurso(){
		return curso == null ? false : !curso.isNew(); 
	}
	
}
