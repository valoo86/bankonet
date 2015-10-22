package com.bankonet.dao.compte;

import java.util.Map;

import com.bankonet.CompteException;
import com.bankonet.dto.Compte;
import com.bankonet.dto.CompteCourant;

public class CompteDaoMySQL implements CompteDao{

	@Override
	public Map<String, CompteCourant> findAllCC() throws CompteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCC(CompteCourant compte) throws CompteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Compte compte) throws CompteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Compte> findAll() throws CompteException {
		// TODO Auto-generated method stub
		return null;
	}

}
