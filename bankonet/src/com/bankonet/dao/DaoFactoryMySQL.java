package com.bankonet.dao;

import com.bankonet.dao.client.ClientDao;
import com.bankonet.dao.client.ClientDaoMySQL;
import com.bankonet.dao.compte.CompteDao;
import com.bankonet.dao.compte.CompteDaoMySQL;

public class DaoFactoryMySQL implements DaoFactory{

	@Override
	public CompteDao getCompteDao() {
		return new CompteDaoMySQL();
	}

	@Override
	public ClientDao getClientDao() {
		return new ClientDaoMySQL();
	}

}
