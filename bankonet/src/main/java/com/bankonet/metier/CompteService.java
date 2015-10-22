package com.bankonet.metier;

import com.bankonet.CompteException;
import com.bankonet.dto.Client;
import com.bankonet.dto.CompteCourant;

public interface CompteService {
	CompteCourant creerCompteClient(Client client) throws CompteException;
}
