package br.ufpa.configuracoes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ufpa.configuracoes.controller.JpaUtil;
import br.ufpa.configuracoes.model.Servico;
import br.ufpa.configuracoes.model.Sistema;

public class SistemaDao {
	private EntityManager manager = JpaUtil.getEntityManager();

	public Sistema getById(int id) {
		try {
			return this.manager.find(Sistema.class, id);
		} finally {
			this.manager.close();
		}

	}

	// BUSCA TODAS AS Sistema
	public List<Sistema> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Sistema> query = manager.createQuery("from Sistema", Sistema.class);
			return query.getResultList();
		} finally {
			manager.close();
		}

	}

	// Adicionar Sistema
	public void adicionar(Sistema sistema) {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(sistema);
			transaction.commit();
			manager.close();
			System.out.println("Sistema cadastrado com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao adicionar ->" + e.getMessage());
		}
	}

	// Alterar Sistema
	public void update(Sistema sistema) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(sistema);
			transaction.commit();
			manager.close();
			System.out.println("Sistema alterado com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao alterar ->" + e.getMessage());
		}
	}

	// Remover Servico
	public void remove(Sistema sistema) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(sistema);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			System.out.println("Sistema removido com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao remover ->" + e.getMessage());
		}
	}
}
