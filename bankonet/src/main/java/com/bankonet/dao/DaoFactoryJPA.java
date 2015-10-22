package com.bankonet.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bankonet.dao.client.ClientDao;
import com.bankonet.dao.client.ClientDaoJPA;
import com.bankonet.dao.compte.CompteDao;
import com.bankonet.dao.compte.CompteDaoJPA;

public class DaoFactoryJPA implements DaoFactory {

	private EntityManagerFactory emf;

	public DaoFactoryJPA(String bdd) {
		this.emf = Persistence.createEntityManagerFactory(bdd);
	}

	@Override
	public CompteDao getCompteDao() {
		return new CompteDaoJPA(emf);
	}

	@Override
	public ClientDao getClientDao() {
		return new ClientDaoJPA(emf);
	}

}
