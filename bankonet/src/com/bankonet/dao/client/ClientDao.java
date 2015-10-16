package com.bankonet.dao.client;

import java.util.Map;

import com.bankonet.ClientException;
import com.bankonet.dto.Client;

public interface ClientDao {
	Map<String, Client> findAll() throws ClientException;
	void save(Client client) throws ClientException;
}
