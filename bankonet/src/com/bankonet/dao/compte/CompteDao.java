package com.bankonet.dao.compte;

import java.util.Map;

import com.bankonet.CompteException;
import com.bankonet.dto.Compte;
import com.bankonet.dto.CompteCourant;

public interface CompteDao {
	Map<String, CompteCourant> findAllCC() throws CompteException;
	void saveCC(CompteCourant compte) throws CompteException;
	
	Map<String, Compte> findAll() throws CompteException;
	void save(Compte compte) throws CompteException;
}
