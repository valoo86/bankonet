package com.bankonet.dao.client;

import java.util.Map;

import com.bankonet.Client;
import com.bankonet.ClientException;

public interface ClientDao {
	Map<String, Client> findAll() throws ClientException;
	void save(Client client) throws ClientException;
}
