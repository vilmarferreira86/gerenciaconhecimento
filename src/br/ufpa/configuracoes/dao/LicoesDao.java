package br.ufpa.configuracoes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ufpa.configuracoes.controller.JpaUtil;
import br.ufpa.configuracoes.model.Gerencia;
import br.ufpa.configuracoes.model.LicoesAprendida;

public class LicoesDao {
	private EntityManager manager = JpaUtil.getEntityManager();

	public LicoesAprendida getById(int id) {
		try {
			return this.manager.find(LicoesAprendida.class, id);
		} finally {
			this.manager.close();
		}

	}

	// BUSCA TODAS AS LicoesAprendida
	public List<LicoesAprendida> getTodas() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			TypedQuery<LicoesAprendida> query = manager.createQuery("from LicoesAprendida", LicoesAprendida.class);
			return query.getResultList();
		} finally {
			manager.close();
		}

	}

	// Adicionar LicoesAprendida
	public void adicionar(LicoesAprendida licoes) {
		try {

			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(licoes);
			transaction.commit();
			manager.close();
			System.out.println("Conhecimento cadastrado com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao adicionar ->" + e.getMessage());
		}
	}

	// Alterar LicoesAprendida 
	public void update(LicoesAprendida licoes) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(licoes);
			transaction.commit();
			manager.close();
			System.out.println("Conhecimento alterado com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao alterar ->" + e.getMessage());
		}
	}

	// Remover Coordenadoria
	public void remove(LicoesAprendida licoes) {
		try {
			EntityManager manager = JpaUtil.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			Object o = manager.merge(licoes);
			transaction.begin();
			manager.remove(o);
			transaction.commit();
			manager.close();
			System.out.println("Conhecimento removido com sucesso!");
		} catch (PersistenceException e) {
			System.out.println("Erro ao remover ->" + e.getMessage());
		}
	}
}
