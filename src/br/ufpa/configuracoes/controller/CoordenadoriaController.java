package br.ufpa.configuracoes.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufpa.configuracoes.controller.JpaUtil;
import br.ufpa.configuracoes.dao.CoordenadoriaDao;
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
public class CoordenadoriaController {
	CoordenadoriaDao cdao = new CoordenadoriaDao();
	private Coordenadoria coord = new Coordenadoria();
	private List<Coordenadoria> coords;
	private List<Gerencia> gerencias;
	public CoordenadoriaController() {

	}

	public CoordenadoriaController(Coordenadoria coordenadoria) {
		super();
		this.coord = coordenadoria;
	}
	
	public void limpar() {
		if(!this.coord.equals(null)) {
			this.coord = new Coordenadoria();
		}
	}

	// persistindo na base de dados
	public void adicionar() {
		try {

			cdao.adicionar(this.coord);

			this.coord = new Coordenadoria();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Coordenadoria cadastrada com sucesso!", null));
		}

		catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		
	}

	// METODO UPDATE
	public String update() {
		try {
			cdao.update(this.coord);
			this.coord = new Coordenadoria();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Coordenadoria atualizada com sucesso!", null));

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "coordenadoria";
	}

	// METODO REMOVER
	public String remove() {
		try {
			
			cdao.remove(this.coord);
			this.coord = new Coordenadoria();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Coordenadoria removida com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "coordenadoria";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editCoordenadoria";
	}

	public String cancela() {
		this.coord = new Coordenadoria();
		return "coordenadoria";
	}

	public List<Coordenadoria> getTodas() {
		List<Coordenadoria> list = null;
		try {
			list = cdao.getTodas();
			return list;
		} catch(Exception e) {
			
		}
		return list;

	}

	public List<SelectItem> getGerencias() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Gerencia> q = manager.createQuery("FROM Gerencia g ORDER BY g.sigla",
					Gerencia.class);
			gerencias = q.getResultList();
			for (Gerencia g : this.gerencias) {
				items.add(new SelectItem(g, g.getSigla()));
			}
			return items;
		} finally {
			manager.close();
		}
	}

	// GETTERS AND SETTERS

	public Coordenadoria getCoord() {
		return coord;
	}

	public void setCoord(Coordenadoria coordenadoria) {
		this.coord = coordenadoria;
	}

	public List<Coordenadoria> getCoords() {
		return coords;
	}

	public void setCoords(List<Coordenadoria> coordenadorias) {
		this.coords = coordenadorias;
	}

}