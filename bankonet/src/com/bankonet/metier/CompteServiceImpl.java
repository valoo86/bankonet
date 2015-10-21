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
				client.getLogin() + "_CC00" + (client.getComptesListComptesCourantsId().size() + 1),
				client.getNom() + "_" + client.getPrenom() + "_COURANT_" + (client.getComptesListComptesCourantsId().size() + 1), 300,
				400);
		client.creerCompteCourantId(compteCourant.getNumero());

		compteDao.save(compteCourant);
	}
}
