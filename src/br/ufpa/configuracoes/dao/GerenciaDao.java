package br.ufpa.configuracoes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ufpa.configuracoes.controller.JpaUtil;
import br.ufpa.configuracoes.model.Gerencia;

public class GerenciaDao {
	public Gerencia getById(int id) {
		EntityManager manager = JpaUtil.getEntityManager();

		try {
			return manager.find(Gerencia.class, id);
		} finally {
			manager.close();
		}

	}

	// BUSCA TODAS AS Coordenadorias
	public List<Gerencia> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<Gerencia> query = manager.createQuery("from Gerencia", Gerencia.class);
			return query.getResultList();
		} finally {
			manager.close();
		}

	}

	// Adicionar Gerencia
	public void adicionar(Gerencia gerencia) {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(gerencia);
			transaction.commit();
			manager.close();
			System.out.println("Gerencia cadastrada com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao adicionar ->" + e.getMessage());
		}
	}

	// Alterar Gerencia
	public void update(Gerencia gerencia) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.merge(gerencia);
			transaction.commit();
			manager.close();
			System.out.println("Gerencia alterada com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao alterar ->" + e.getMessage());
		}
	}

	// Remover Coordenadoria
	public void remove(Gerencia gerencia) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(gerencia);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			System.out.println("Gerencia removida com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao remover ->" + e.getMessage());
		}
	}

	

}
