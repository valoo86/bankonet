package com.bankonet.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bankonet.dao.client.ClientDao;
import com.bankonet.dao.client.ClientDaoJPA;
import com.bankonet.dao.compte.CompteDao;

public class DaoFactoryJPA implements DaoFactory {

	private EntityManagerFactory emf;

	public DaoFactoryJPA(String bdd) {
		this.emf = Persistence.createEntityManagerFactory(bdd);
	}

	@Override
	public CompteDao getCompteDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDao getClientDao() {
		// TODO Auto-generated method stub
		return new ClientDaoJPA(emf);
	}

}
