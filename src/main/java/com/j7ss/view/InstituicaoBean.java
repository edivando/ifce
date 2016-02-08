package com.j7ss.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.j7ss.entity.Campus;
import com.j7ss.entity.Curso;
import com.j7ss.entity.Departamento;
import com.j7ss.entity.Instituicao;
import com.j7ss.util.BasicView;
import com.j7ss.util.DAOException;
import com.j7ss.util.Messages;

/**
 * 
 * 
 * 
 * @author edivandoalves
 *
 */
@ManagedBean
@ViewScoped
public class InstituicaoBean extends BasicView<Instituicao>{
	private static final long serialVersionUID = 1L;
	
	@Getter
	private TreeNode root;
	@Getter @Setter
	private TreeNode selectedNode;

	private Campus campus;
	private Departamento departamento;
	private Curso curso;
	
	@Getter 
	private boolean showCampus;
	@Getter 
	private boolean showDepartamento;
	@Getter 
	private boolean showCurso;
	
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
    
    public void onNodeExpand(NodeExpandEvent event) {
//    	if(event.getTreeNode().getType().equals("campus")){
////    		TreeNode campusNode = event.getTreeNode();
////			for (Departamento departamento : ((Campus)campusNode.getData()).getDepartamentos()) {
////				DefaultTreeNode departamentoNode = new DefaultTreeNode("departamento", departamento, campusNode);
////				for(Curso curso : departamento.getCursos()){
////					new DefaultTreeNode("curso", curso, departamentoNode);
////				}
////			}
//    		
////			for (Departamento departamento : ((Campus)campusNode.getData()).getDepartamentos()) {
////				new DefaultTreeNode("departamento", departamento, campusNode);
////			}
//    		
//    		System.out.println("onNodeExpand - Campus");
//    	}else if(event.getTreeNode().getType().equals("departamento")){
////    		TreeNode departamentoNode = event.getTreeNode();
////    		for(Curso curso : ((Departamento)departamentoNode.getData()).getCursos()){
////				new DefaultTreeNode("curso", curso, departamentoNode);
////			}
//    		
//    		
//    		System.out.println("onNodeExpand - Departamento");
//    	}else if(event.getTreeNode().getType().equals("curso")){
//    		System.out.println("onNodeExpand - Curso");
//    	}
    	
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
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
    	
    	System.out.println("Type: "+event.getTreeNode().getType());
    	System.out.println(event.getTreeNode().getData());
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
    		}else{
    			campus.save();
    		}
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
    		}else{
    			departamento.save();
    		}
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
    		}else{
    			curso.save();
    		}
    		reloadTree();
    		back();
    		Messages.showGrowlInfo("Test", "test");
		} catch (DAOException e) {
			Messages.showGrowlInfo("Test", "test");
			e.printStackTrace();
		}
    }
	

	
	public Instituicao getEntityByNome(String nome){
		for (Instituicao instituicao : entitys) {
			if(instituicao.getNome().equals(nome)) return instituicao;
		}
		return null;
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

	
	public Campus getCampus() {
		return campus == null ? campus = new Campus() : campus;
	}
	
	public void setCampus(Campus campus) {
		this.campus = campus;
//		this.departamento = null;
//		this.curso = null;
	}
	
	public Departamento getDepartamento() {
		return departamento == null ? departamento = new Departamento() : departamento;
	}
	
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
//		this.curso = null;
	}
	
	public Curso getCurso() {
		return curso == null ? curso = new Curso() : curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public boolean getNovoDepartamento(){
		return campus == null ? false : showCampus && !campus.isNew();
	}
	
	public boolean getNovoCurso(){
		return departamento == null ? false : showDepartamento && !departamento.isNew();
	}
	
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
