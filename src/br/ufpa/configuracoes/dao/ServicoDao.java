package br.ufpa.configuracoes.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ufpa.configuracoes.controller.JpaUtil;
import br.ufpa.configuracoes.model.Coordenadoria;
import br.ufpa.configuracoes.model.LicoesAprendida;
import br.ufpa.configuracoes.model.Servico;

public class ServicoDao {
	private EntityManager manager = JpaUtil.getEntityManager();

	public Servico getById(int id) {
		try {
			return this.manager.find(Servico.class, id);
		} finally {
			this.manager.close();
		}

	}
	
	// BUSCA TODAS AS Servico
		public List<Servico> getTodas() {
			EntityManager manager = JpaUtil.getEntityManager();
			try {
				TypedQuery<Servico> query = manager.createQuery("from Servico", Servico.class);
				return query.getResultList();
			} finally {
				manager.close();
			}

		}

		// Adicionar Servico
		public void adicionar(Servico servico) {
			try {

				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();

				manager.persist(servico);
				transaction.commit();
				manager.close();
				System.out.println("Servico cadastrado com sucesso!");
			} catch (PersistenceException e) {
				System.out.println("Erro ao adicionar ->" + e.getMessage());
			}
		}

		// Alterar Servico 
		public void update(Servico servico) {
			try {
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();
				manager.merge(servico);
				transaction.commit();
				manager.close();
				System.out.println("Servico alterado com sucesso!");
			} catch (PersistenceException e) {
				System.out.println("Erro ao alterar ->" + e.getMessage());
			}
		}

		// Remover Servico
		public void remove(Servico servico) {
			try {
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				Object o = manager.merge(servico);
				transaction.begin();
				manager.remove(o);
				transaction.commit();
				manager.close();
				System.out.println("Servico removido com sucesso!");
			} catch (PersistenceException e) {
				System.out.println("Erro ao remover ->" + e.getMessage());
			}
		}
		
		public List<Servico> getServicoPorTipo(HashMap<String, Object> params){
			EntityManager manager = JpaUtil.getEntityManager();
			try {
				TypedQuery<Servico> query = manager.createQuery("from Servico s WHERE s.tipoServico.idTipoServico=:servico", Servico.class);
				query.setParameter("servico", params.get("servico"));
				return query.getResultList();
			} finally {
				manager.close();
			}
			
		}
}
