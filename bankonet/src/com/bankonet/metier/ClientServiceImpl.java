package com.bankonet.metier;

import com.bankonet.Client;
import com.bankonet.dao.DaoFactory;
import com.bankonet.dao.DaoFactoryFile;
import com.bankonet.exception.BankonetException;

public class ClientServiceImpl implements ClientService {

	private DaoFactory daoFactory = null;
	private CompteService compteService = null;
	
	@Override
	public void creerClient(String nom, String prenom, String login) throws BankonetException {
		Client client = new Client();
		client.nom = nom;
		client.prenom = prenom;
		client.login = login;
		client.password = "azerty";
		
		CompteService compteService = new CompteServiceImpl();
		compteService.creerCompteParDefautDuClient(client);
		
		DaoFactory daoFactoryFile = new DaoFactoryFile();
		daoFactoryFile.getClientDao().save(client);
	}
	
}
