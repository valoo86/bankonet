package com.bankonet.metier;

import java.util.List;
import java.util.Map;

import com.bankonet.ClientException;
import com.bankonet.dto.Client;
import com.bankonet.exception.BankonetException;

public interface ClientService {
	void creerClient(String nom, String prenom, String login) throws BankonetException;
	Map<String, Client> findAll() throws ClientException;
	List<Client> findClientByName(String name) throws ClientException;
	List<Client> findClientByFirstname(String firstname) throws ClientException;
	Client findClientById(String id);
	void updateClient(Client client);
	void deleteClient(Client client);
	void deleteAllClients();
}
