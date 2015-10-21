package com.bankonet.metier;

import com.bankonet.ClientException;
import com.bankonet.dao.client.ClientDao;
import com.bankonet.dto.Client;

public class InitService {
	
	private ClientDao clientDao;

	public InitService(ClientDao clientDao) {
		super();
		this.clientDao = clientDao;
	}


	public void init() throws ClientException {
		Client client1 = new Client("NAME1", "FIRSTNAME1", "IDENTIFIANT1");
		client1.setLogin("LOGIN1");
		Client client2 = new Client("NAME2", "FIRSTNAME2", "IDENTIFIANT2");
		client2.setLogin("LOGIN2");
		Client client3 = new Client("NAME3", "FIRSTNAME3", "IDENTIFIANT3");
		client3.setLogin("LOGIN3");
		Client client4 = new Client("NAME4", "FIRSTNAME4", "IDENTIFIANT4");
		client4.setLogin("LOGIN4");
		Client client5 = new Client("NAME5", "FIRSTNAME5", "IDENTIFIANT5");
		client5.setLogin("LOGIN5");
		
		clientDao.save(client1);
		clientDao.save(client2);
		clientDao.save(client3);
		clientDao.save(client4);
		clientDao.save(client5);
		
	}
}
