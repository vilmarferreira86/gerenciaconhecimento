package br.ufpa.configuracoes.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufpa.configuracoes.controller.JpaUtil;
import br.ufpa.configuracoes.dao.GerenciaDao;
import br.ufpa.configuracoes.model.Coordenadoria;
import br.ufpa.configuracoes.model.Gerencia;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

@ManagedBean
@SessionScoped
public class GerenciaController {
	private Gerencia gerencia = new Gerencia();
	private List<Gerencia> gerencias;
	GerenciaDao gdao = new GerenciaDao();

	public GerenciaController() {

	}
	
	public GerenciaController(Gerencia ger) {
		super();
		this.gerencia = ger;
	}

	public void limpar() {
		if(!this.gerencia.equals(null)) {
			this.gerencia = new Gerencia();
		}
	}

	// persistindo na base de dados
	public void adicionar() {
		
		try {
			
			gdao.adicionar(this.gerencia);
			this.gerencia = new Gerencia();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerencia cadastrada com sucesso!", null));
		}

		catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	
	}

	// METODO UPDATE
	public String update() {
		try {
			gdao.update(this.gerencia);
			this.gerencia = new Gerencia();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerencia atualizada com sucesso!", null));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "gerencia";
	}

	// METODO REMOVER
	public String remove() {
		try {
			
			gdao.remove(this.gerencia);
			gerencia = new Gerencia();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerencia removida com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "gerencia";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editGerencia";
	}

	public String cancela() {
		this.gerencia = new Gerencia();
		return "gerencia";
	}

	public List<Gerencia> getTodas() {
		List<Gerencia> list = null;
		try {
			list = gdao.getTodas();
			return list;
		} catch(Exception e) {
			
		}
		return list;

	}


	// GETTERS AND SETTERS

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public List<Gerencia> getGerencias() {
		return gerencias;
	}

	public void setGerencias(List<Gerencia> gerencias) {
		this.gerencias = gerencias;
	}

}