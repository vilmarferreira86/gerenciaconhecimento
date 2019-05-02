package br.ufpa.configuracoes.controller;

import java.io.ByteArrayInputStream;
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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.ufpa.configuracoes.dao.LicoesDao;
import br.ufpa.configuracoes.dao.ServicoDao;
import br.ufpa.configuracoes.model.LicoesAprendida;
import br.ufpa.configuracoes.model.Servico;
import br.ufpa.configuracoes.model.Sistema;
import br.ufpa.configuracoes.model.TipoServico;
import net.bytebuddy.asm.Advice.This;

@ManagedBean
@SessionScoped
public class LicoesAprendidasController {
	private LicoesAprendida licoes = new LicoesAprendida();
	private List<Sistema> sistemas = new ArrayList<Sistema>();
	private List<Servico> servicos = new ArrayList<Servico>();
	private LicoesDao ldao = new LicoesDao();
	private TipoServico tpServico;
	private List<Servico> services;
	private ServicoDao sdao = new ServicoDao();
	private List<LicoesAprendida> filteredLicoes;
	private List<LicoesAprendida> todas;
	private StreamedContent anexo;
	
	public LicoesAprendidasController() {
	}

	public LicoesAprendidasController(LicoesAprendida licao) {
		super();
		this.licoes = licao;
	}

	@SuppressWarnings("unchecked")
	public void updateComboServico() {
		HashMap<String, Object> params = new HashMap<>();
		params.put("servico", this.tpServico.getIdTipoServico());
		this.services = sdao.getServicoPorTipo(params);
	}

	// persistindo na base de dados
	public void adicionar() {

		try {

			ldao.adicionar(this.licoes);

			this.licoes = new LicoesAprendida();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Conhecimento cadastrado com sucesso!", null));
		}

		catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}

	// METODO UPDATE
	public String update() {
		try {
			ldao.update(this.licoes);

			this.licoes = new LicoesAprendida();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Conhecimento atualizado com sucesso!", null));
			return "licoes";

		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "licoes";
	}

	// METODO REMOVER
	public String remove() {
		try {

			ldao.remove(this.licoes);
			licoes = new LicoesAprendida();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Conhecimento removido com sucesso!", null));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		return "licoes";
	}

	// MUDAR DE PAGINA
	public String editar() {
		return "editLicoes";
	}

	public String cancela() {
		this.licoes = new LicoesAprendida();
		return "licoes";
	}

	

	public void upload(FileUploadEvent event) {
		this.licoes.setAnexo(event.getFile().getContents());
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public LicoesAprendida getLicoes() {
		return licoes;
	}

	public void setLicoes(LicoesAprendida licoes) {
		this.licoes = licoes;
	}

	public List<SelectItem> getSistemas() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Sistema> q = manager.createQuery("FROM Sistema s ORDER BY s.nome", Sistema.class);
			this.sistemas = q.getResultList();
			for (Sistema s : this.sistemas) {
				items.add(new SelectItem(s, s.getNome()));
			}
			return items;
		} finally {
			manager.close();
		}
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	public List<SelectItem> getServicos() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Servico> q = manager.createQuery("FROM Servico s ORDER BY s.descricao", Servico.class);
			this.servicos = q.getResultList();
			for (Servico s : this.servicos) {
				items.add(new SelectItem(s, s.getDescricao()));
			}
			return items;
		} finally {
			manager.close();
		}
	}
	
	

	public StreamedContent getAnexo(LicoesAprendida licoes) {
		this.anexo = new DefaultStreamedContent(new ByteArrayInputStream(licoes.getAnexo()));
		return anexo;
		
	}
	

	public StreamedContent getAnexo() {
		return anexo;
	}

	public void setAnexo(StreamedContent anexo) {
		this.anexo = anexo;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public LicoesDao getLdao() {
		return ldao;
	}

	public void setLdao(LicoesDao ldao) {
		this.ldao = ldao;
	}

	public TipoServico getTpServico() {
		return tpServico;
	}

	public void setTpServico(TipoServico tpServico) {
		this.tpServico = tpServico;
	}

	

	

	public List<Servico> getServices() {
		return services;
	}

	public void setServices(List<Servico> services) {
		this.services = services;
	}

	public ServicoDao getSdao() {
		return sdao;
	}

	public void setSdao(ServicoDao sdao) {
		this.sdao = sdao;
	}

	public List<LicoesAprendida> getFilteredLicoes() {
		return filteredLicoes;
	}

	public void setFilteredLicoes(List<LicoesAprendida> filteredLicoes) {
		this.filteredLicoes = filteredLicoes;
	}
	
	public List<LicoesAprendida> getTodas() {
		todas = null;
		try {
			todas = ldao.getTodas();
			return todas;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao lista -> "+e.getMessage(), null));
		}
		return todas;

	}

	public void setTodas(List<LicoesAprendida> todas) {
		this.todas = todas;
	}
	
	

}
