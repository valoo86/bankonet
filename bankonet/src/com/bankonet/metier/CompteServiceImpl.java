package com.bankonet.metier;

import com.bankonet.CompteException;
import com.bankonet.dao.compte.CompteDao;
import com.bankonet.dto.Client;
import com.bankonet.dto.CompteCourant;

public class CompteServiceImpl implements CompteService {

	private CompteDao compteDao;

	public CompteServiceImpl(CompteDao compteDao) {
		super();
		this.compteDao = compteDao;
	}

	@Override
	public void creerCompteClient(Client client) throws CompteException {
		CompteCourant compteCourant = new CompteCourant(
				client.login + "_CC00" + (client.comptesListComptesCourantsId.size() + 1),
				client.nom + "_" + client.prenom + "_COURANT_" + (client.comptesListComptesCourantsId.size() + 1), 300,
				400);
		client.creerCompteCourantId(compteCourant.getNumero());

		compteDao.save(compteCourant);
	}
}
