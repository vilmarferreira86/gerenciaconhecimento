package br.ufpa.configuracoes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ufpa.configuracoes.dao.CoordenadoriaDao;
import br.ufpa.configuracoes.dao.SistemaDao;
import br.ufpa.configuracoes.model.Coordenadoria;
import br.ufpa.configuracoes.model.Gerencia;
import br.ufpa.configuracoes.model.Sistema;

@ManagedBean
@SessionScoped
public class SistemaController {
	private Sistema sistema = new Sistema();
	private List<Coordenadoria> coordenadorias = new ArrayList<Coordenadoria>();
	private SistemaDao sdao = new SistemaDao();
	private Gerencia gerencia;
	private CoordenadoriaDao cdao = new CoordenadoriaDao();
	private List<Coordenadoria> coords;
	

	public SistemaController() {
	}

	public SistemaController(Sistema sistema) {
		super();
		this.sistema = sistema;
	}
	
	public void limpar() {
		if(!this.sistema.equals(null) || !this.gerencia.equals(null)) {
			this.sistema = new Sistema();
			this.gerencia = new Gerencia();
		}
	}

	// persistindo na base de dados
	public void adicionar() {

		try {
			sdao.adicionar(this.sistema);
			this.gerencia = new Gerencia();
			this.sistema = new Sistema();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema cadastrado com sucesso!", null));
		}

		catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}

	// METODO UPDATE
	public String update() {
		try {
			sdao.update(this.sistema);
			this.gerencia = new Gerencia();
			this.sistema = new Sistema();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema atualizado com sucesso!", null));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "sistemas";
	}

	// METODO REMOVER
	public String remove() {
		try {

			sdao.remove(this.sistema);
			sistema = new Sistema();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "sistema";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editSistema";
	}

	public String cancela() {
		this.sistema = new Sistema();
		this.gerencia = new Gerencia();
		return "sistema";
	}

	public List<Sistema> getTodas() {
		List<Sistema> list = null;
		try {
			list = sdao.getTodas();
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return list;

	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public List<SelectItem> getCoordenadorias() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Coordenadoria> q = manager.createQuery("FROM Coordenadoria c ORDER BY c.sigla",
					Coordenadoria.class);
			this.coordenadorias = q.getResultList();
			for (Coordenadoria c : this.coordenadorias) {
				items.add(new SelectItem(c, c.getSigla()));
			}
			return items;
		} finally {
			manager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateComboCoordenadoria() {
		HashMap<String, Object> params = new HashMap<>();
		params.put("gerencia", gerencia.getIdGerencia());
		this.coords = cdao.getCoordenadoriaPorGerencia(params);
	}

	public void setCoordenadorias(List<Coordenadoria> coordenadorias) {
		this.coordenadorias = coordenadorias;
	}

	public Gerencia getGerencia() {
		return gerencia;
	}

	public void setGerencia(Gerencia gerencia) {
		this.gerencia = gerencia;
	}

	public SistemaDao getSdao() {
		return sdao;
	}

	public void setSdao(SistemaDao sdao) {
		this.sdao = sdao;
	}
	
	public CoordenadoriaDao getCdao() {
		return cdao;
	}

	public void setCdao(CoordenadoriaDao cdao) {
		this.cdao = cdao;
	}

	public List<Coordenadoria> getCoords() {
		return coords;
	}

	public void setCoords(List<Coordenadoria> coords) {
		this.coords = coords;
	}
	

}
