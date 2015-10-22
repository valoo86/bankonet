package com.bankonet.dao.client;

import java.util.List;
import java.util.Map;

import com.bankonet.ClientException;
import com.bankonet.dto.Client;

public interface ClientDao {
	Map<String, Client> findAll() throws ClientException;
	void save(Client client) throws ClientException;
	List<Client> findClientByName(String name) throws ClientException;
	List<Client> findClientByFirstname(String firstname) throws ClientException;
	Client findClientById(String id);
	void updateClient(Client client);
	void deleteClient(Client client);
	void deleteAllClients();
}
