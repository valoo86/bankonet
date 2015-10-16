package com.bankonet.metier;

import com.bankonet.CompteException;
import com.bankonet.dao.DaoFactory;
import com.bankonet.dao.DaoFactoryFile;
import com.bankonet.dto.Client;
import com.bankonet.dto.CompteCourant;

public class CompteServiceImpl implements CompteService{

	@Override
	public void creerCompteParDefautDuClient(Client client) throws CompteException {
		CompteCourant compteCourant = new CompteCourant(client.login+"_CC00"+(client.comptesListComptesCourantsId.size()+1), client.nom + "_" + client.prenom + "_COURANT_"+(client.comptesListComptesCourantsId.size()+1), 300,
				400);
		client.creerCompteCourantId(compteCourant.getNumero());
		
		DaoFactory daoFactoryFile = new DaoFactoryFile();
		daoFactoryFile.getCompteDao().save(compteCourant);
		
	}

}
