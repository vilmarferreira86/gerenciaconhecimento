package br.ufpa.configuracoes.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ufpa.configuracoes.dao.TipoServicoDao;
import br.ufpa.configuracoes.model.Coordenadoria;
import br.ufpa.configuracoes.model.TipoServico;

@ManagedBean
@SessionScoped
public class TipoServicoController {
	private TipoServico tiposervico = new TipoServico();
	TipoServicoDao tpDao = new TipoServicoDao();

	public TipoServicoController() {
	}

	public TipoServicoController(TipoServico tpservice) {
		super();
		this.tiposervico = tpservice;
	}

	public void limpar() {
		if (!this.tiposervico.equals(null)) {
			this.tiposervico = new TipoServico();
		}
	}

	// persistindo na base de dados
	public void adicionar() {
		try {

			tpDao.adicionar(this.tiposervico);
			this.tiposervico = new TipoServico();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo Serviço cadastrado com sucesso!", null));
		}

		catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}

	// METODO UPDATE
	public String update() {
		try {
			tpDao.update(this.tiposervico);
			this.tiposervico = new TipoServico();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo Serviço atualizado com sucesso!", null));
			return "tpservico";

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "tpservico";
	}

	// METODO REMOVER
	public String remove() {
		try {

			tpDao.remove(this.tiposervico);
			this.tiposervico = new TipoServico();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo Serviço removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "tpservico";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editTpServico";
	}

	public String cancela() {
		this.tiposervico = new TipoServico();
		return "tpservico";
	}

	public List<TipoServico> getTodas() {
		List<TipoServico> list = null;
		try {
			list = tpDao.getTodas();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return list;

	}

	public TipoServico getTiposervico() {
		return tiposervico;
	}

	public void setTiposervico(TipoServico tiposervico) {
		this.tiposervico = tiposervico;
	}

}
