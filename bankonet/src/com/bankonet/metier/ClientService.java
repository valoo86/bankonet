package com.bankonet.metier;

import com.bankonet.exception.BankonetException;

public interface ClientService {
	void creerClient(String nom, String prenom, String login) throws BankonetException;
}
