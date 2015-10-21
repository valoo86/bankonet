package com.bankonet.dao.compte;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.bankonet.CompteException;
import com.bankonet.dto.Compte;
import com.bankonet.dto.CompteCourant;

public class CompteDaoJPA implements CompteDao{

	private EntityManagerFactory emFactory;
	private EntityManager em;

	public CompteDaoJPA(EntityManagerFactory emFactory) {
		super();
		this.emFactory = emFactory;
	}

	@Override
	public Map<String, Compte> findAll() throws CompteException {
		Map<String, Compte> comptesMap = new HashMap<>();
		em = emFactory.createEntityManager();

//		for (Compte compte : em.createQuery("select c from Compte c", Compte.class).getResultList()) {
//			if(compte)
//			comptesMap.put(compte.getNumero(), compte);
//		}

		em.close();
		return comptesMap;
	}

	@Override
	public void save(Compte compte) throws CompteException {
		em = emFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		em.persist(compte); //opération (il pourrait y en avoir plusieurs)

		et.commit();
		em.close();
	}

	@Override
	public Map<String, CompteCourant> findAllCC() throws CompteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCC(CompteCourant compte) throws CompteException {
		// TODO Auto-generated method stub
		
	}

}
