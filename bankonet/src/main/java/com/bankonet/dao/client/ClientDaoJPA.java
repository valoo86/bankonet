package com.bankonet.dao.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.bankonet.ClientException;
import com.bankonet.dto.Client;
import com.bankonet.dto.Compte;
import com.bankonet.dto.CompteCourant;
import com.bankonet.dto.CompteEpargne;

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
			for (Compte compte : client.getComptesList()) {
				if(compte instanceof CompteCourant) {
					client.getComptesListComptesCourantsId().add(compte.getNumero());
				}
				else if(compte instanceof CompteEpargne) {
					client.getComptesListComptesEpargneId().add(compte.getNumero());
				}
			}
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

	@Override
	public List<Client> findClientByName(String name) throws ClientException {
		em = emFactory.createEntityManager();
		List<Client> clientsList = em.createNamedQuery("client.findByName",Client.class)
				.setParameter("name", name)
				.getResultList();
		em.close();
		return clientsList;
	}

	@Override
	public List<Client> findClientByFirstname(String firstname) throws ClientException {
		em = emFactory.createEntityManager();
		List<Client> clientsList = em.createNamedQuery("client.findByFirstname",Client.class)
				.setParameter("firstname", firstname)
				.getResultList();
		em.close();
		return clientsList;
	}

	@Override
	public Client findClientById(String id) {
		em = emFactory.createEntityManager();

		Client client = em.find(Client.class, Integer.parseInt(id));

		em.close();
		return client;
	}

	@Override
	public void updateClient(Client client) {
		em = emFactory.createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();

		em.merge(client);

		et.commit();
		em.close();
	}

	@Override
	public void deleteClient(Client client) {
		em = emFactory.createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(client);

		et.commit();
		em.close();
	}

	@Override
	public void deleteAllClients() {
		em = emFactory.createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();
		em.createQuery("delete c from Client c").executeUpdate();

		et.commit();
		em.close();
	}
}
