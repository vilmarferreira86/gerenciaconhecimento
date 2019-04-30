package br.ufpa.configuracoes.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.ufpa.configuracoes.controller.JpaUtil;
import br.ufpa.configuracoes.model.Coordenadoria;
import br.ufpa.configuracoes.model.Gerencia;

public class CoordenadoriaDao {
	
	private EntityManager manager = JpaUtil.getEntityManager();

	public Coordenadoria getById(int id) {
		try {
			return this.manager.find(Coordenadoria.class, id);
		} finally {
			this.manager.close();
		}

	}
	
	// BUSCA TODAS AS Coordenadorias
		public List<Coordenadoria> getTodas() {
			EntityManager manager = JpaUtil.getEntityManager();
			try {
				TypedQuery<Coordenadoria> query = manager.createQuery("from Coordenadoria", Coordenadoria.class);
				return query.getResultList();
			} finally {
				manager.close();
			}

		}
		// Adicionar Coordenadoria
		public void adicionar(Coordenadoria coordenadoria) {
			try {

				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();

				manager.persist(coordenadoria);
				transaction.commit();
				manager.close();
				System.out.println("Coordenadoria cadastrada com sucesso!");
			} catch (PersistenceException e) {
				System.out.println("Erro ao adicionar ->"+e.getMessage());
			}
		}
		
		// Alterar Coordenadoria
		public void update(Coordenadoria coordenadoria) {
			try {
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();
				manager.merge(coordenadoria);
				transaction.commit();
				manager.close();
				System.out.println("Coordenadoria alterada com sucesso!");
			}catch (PersistenceException e) {
				System.out.println("Erro ao alterar ->"+e.getMessage());
			}
		}
		
		// Remover Coordenadoria
		public void remove(Coordenadoria coordenadoria) {
			try {
				EntityManager manager = JpaUtil.getEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				Object o = manager.merge(coordenadoria);
				transaction.begin();
				manager.remove(o);
				transaction.commit();
				manager.close();
				System.out.println("Coordenadoria removida com sucesso!");
			}catch (PersistenceException e) {
				System.out.println("Erro ao remover ->"+e.getMessage());
			}
		}
		
		public List<Coordenadoria> getCoordenadoriaPorGerencia(HashMap<String, Object> params){
			EntityManager manager = JpaUtil.getEntityManager();
			try {
				TypedQuery<Coordenadoria> query = manager.createQuery("from Coordenadoria c WHERE c.gerencia.idGerencia=:gerencia", Coordenadoria.class);
				query.setParameter("gerencia", params.get("gerencia"));
				return query.getResultList();
			} finally {
				manager.close();
			}
			
		}

}
