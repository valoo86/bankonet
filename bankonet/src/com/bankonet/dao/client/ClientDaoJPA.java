package com.bankonet.dao.client;

import java.util.HashMap;
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
		Map<String, Client> clientsMap = new HashMap<>();
		em = emFactory.createEntityManager();
		
		for (Client client : em.createQuery("select c from Client c", Client.class).getResultList()) {
			clientsMap.put(client.getLogin(), client);
		}
		
		em.close();
		return clientsMap;
	}

	@Override
	public void save(Client client) throws ClientException {
		em = emFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		em.persist(client); //opération (il pourrait y en avoir plusieurs)
		
		et.commit();
		em.close();
	}
	
	
	//public Client searchClientBy(String keyword, )
}
