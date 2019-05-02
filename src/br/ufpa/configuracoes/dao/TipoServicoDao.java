package br.ufpa.configuracoes.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ufpa.configuracoes.controller.JpaUtil;
import br.ufpa.configuracoes.model.Servico;
import br.ufpa.configuracoes.model.Sistema;
import br.ufpa.configuracoes.model.TipoServico;

public class TipoServicoDao {
	private EntityManager manager = JpaUtil.getEntityManager();

	public TipoServico getById(int id) {
		try {
			return this.manager.find(TipoServico.class, id);
		} finally {
			this.manager.close();
		}

	}
	
	// BUSCA TODAS AS TipoServico
		public List<TipoServico> getTodas() {
			EntityManager manager = JpaUtil.getEntityManager();
			try {
				TypedQuery<TipoServico> query = manager.createQuery("from TipoServico", TipoServico.class);
				return query.getResultList();
			} finally {
				manager.close();
			}

		}

		// Adicionar TipoServico
		public void adicionar(TipoServico tpServico) {
			try {

				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();

				manager.persist(tpServico);
				transaction.commit();
				manager.close();
				System.out.println("TipoServico cadastrado com sucesso!");
			} catch (PersistenceException e) {
				System.out.println("Erro ao adicionar ->" + e.getMessage());
			}
		}

		// Alterar TipoServico
		public void update(TipoServico tpServico) {
			try {
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();
				manager.merge(tpServico);
				transaction.commit();
				manager.close();
				System.out.println("TipoServico alterado com sucesso!");
			} catch (PersistenceException e) {
				System.out.println("Erro ao alterar ->" + e.getMessage());
			}
		}

		// Remover TipoServico
		public void remove(TipoServico tpServico) {
			try {
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				Object o = manager.merge(tpServico);
				transaction.begin();
				manager.remove(o);
				transaction.commit();
				manager.close();
				System.out.println("TipoServico removido com sucesso!");
			} catch (PersistenceException e) {
				System.out.println("Erro ao remover ->" + e.getMessage());
			}
		}
		
	
}
