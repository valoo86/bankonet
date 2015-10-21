package com.bankonet.metier;

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
		client.setPassword("azrety");
		
		compteService.creerCompteClient(client);
		clientDao.save(client);
	}
	
	@Override
	public Map<String, Client> findAll() throws ClientException {
		return clientDao.findAll();
	}
	
}
