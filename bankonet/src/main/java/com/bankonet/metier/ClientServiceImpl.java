package com.bankonet.metier;

import java.util.List;
import java.util.Map;

import com.bankonet.ClientException;
import com.bankonet.dao.client.ClientDao;
import com.bankonet.dto.Client;
import com.bankonet.exception.BankonetException;

public class ClientServiceImpl implements ClientService {

	private CompteService compteService;
	private ClientDao clientDao;
	
	public ClientServiceImpl(CompteService compteService, ClientDao clientDao) {
		this.compteService = compteService;
		this.clientDao = clientDao;
	}

	@Override
	public void creerClient(String nom, String prenom, String login) throws BankonetException {
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setLogin(login);
		client.setPassword("azerty");
		
		client.creerCompte(compteService.creerCompteClient(client));
		
		clientDao.save(client);
	}
	
	@Override
	public Map<String, Client> findAll() throws ClientException {
		return clientDao.findAll();
	}

	@Override
	public List<Client> findClientByName(String name) throws ClientException {
		return clientDao.findClientByName(name);
	}

	@Override
	public List<Client> findClientByFirstname(String firstname) throws ClientException {
		return clientDao.findClientByFirstname(firstname);
	}

	@Override
	public Client findClientById(String id) {
		return clientDao.findClientById(id);
	}

	@Override
	public void updateClient(Client client) {
		clientDao.updateClient(client);
	}

	@Override
	public void deleteClient(Client client) {
		clientDao.deleteClient(client);
	}

	@Override
	public void deleteAllClients() {
		clientDao.deleteAllClients();
	}
}
