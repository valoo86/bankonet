package com.bankonet.metier;

import com.bankonet.CompteException;
import com.bankonet.dto.Client;

public interface CompteService {
	void creerCompteParDefautDuClient(Client client) throws CompteException;
}
