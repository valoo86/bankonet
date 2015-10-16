package com.bankonet.dao.compte;

import java.util.Map;

import com.bankonet.CompteCourant;
import com.bankonet.CompteException;

public interface CompteDao {
	Map<String, CompteCourant> findAll() throws CompteException;
	void save(CompteCourant compte) throws CompteException;
}
