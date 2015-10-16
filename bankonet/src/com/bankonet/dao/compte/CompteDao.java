package com.bankonet.dao.compte;

import java.util.Map;

import com.bankonet.CompteException;
import com.bankonet.dto.CompteCourant;

public interface CompteDao {
	Map<String, CompteCourant> findAll() throws CompteException;
	void save(CompteCourant compte) throws CompteException;
}
