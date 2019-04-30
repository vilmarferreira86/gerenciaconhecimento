package br.ufpa.configuracoes.controller;

import java.util.ArrayList;
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

import br.ufpa.configuracoes.dao.ServicoDao;
import br.ufpa.configuracoes.model.Coordenadoria;
import br.ufpa.configuracoes.model.Servico;
import br.ufpa.configuracoes.model.TipoServico;

@ManagedBean
@SessionScoped
public class ServicoController {
	private Servico servico = new Servico();
	private List<TipoServico> tiposervicos = new ArrayList<TipoServico>();
	ServicoDao sdao = new ServicoDao();
	
	public ServicoController() {}
	
	public ServicoController(Servico service) {
		super();
		this.servico = service;
	}
	
	public void limpar() {
		if(!this.servico.equals(null)) {
			this.servico = new Servico();
		}
	}
	
	// persistindo na base de dados
		public void adicionar() {
			
			try {

				sdao.adicionar(this.servico);

				this.servico = new Servico();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Serviço cadastrado com sucesso!", null));
			}

			catch (PersistenceException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}

		}

		// METODO UPDATE
		public String update() {
			try {
				sdao.update(this.servico);
				this.servico = new Servico();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Serviço atualizado com sucesso!", null));

			} catch (PersistenceException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}

			return "servico";
		}

		// METODO REMOVER
		public String remove() {
			try {

				sdao.remove(this.servico);
				servico = new Servico();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Serviço"
								+ " removido com sucesso!", null));
			} catch (PersistenceException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}

			return "servico";
		}

		// MUDAR DE PAGINA
		public String editar() {
			return "editServico";
		}

		public String cancela() {
			this.servico = new Servico();
			return "servico";
		}

		public List<Servico> getTodas() {
			List<Servico> list = null;
			try {
				list = sdao.getTodas();
				return list;
			} catch(Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}
			return list;

		}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<SelectItem> getTiposervicos() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<TipoServico> q = manager.createQuery("FROM TipoServico",
					TipoServico.class);
			this.tiposervicos = q.getResultList();
			for (TipoServico t : this.tiposervicos) {
				items.add(new SelectItem(t, t.getNome()));
			}
			return items;
		} finally {
			manager.close();
		}
	}

	public void setTiposervicos(List<TipoServico> tiposervicos) {
		this.tiposervicos = tiposervicos;
	}
	
	
	

}
