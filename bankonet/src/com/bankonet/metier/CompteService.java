package com.bankonet.metier;

import com.bankonet.Client;
import com.bankonet.CompteException;

public interface CompteService {
	void creerCompteParDefautDuClient(Client client) throws CompteException;
}
