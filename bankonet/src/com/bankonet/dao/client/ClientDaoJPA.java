package com.bankonet.dao.client;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.bankonet.ClientException;
import com.bankonet.dto.Client;

public class ClientDaoJPA implements ClientDao{

	private EntityManagerFactory emFactory;
	private EntityManager em;
	
	public ClientDaoJPA(EntityManagerFactory emFactory) {
		this.emFactory = emFactory;
	}

	@Override
	public Map<String, Client> findAll() throws ClientException {
		em = emFactory.createEntityManager();
		return null;
	}

	@Override
	public void save(Client client) throws ClientException {
		em = emFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(client);
		et.commit();
		em.close();
	}
}
