package com.bankonet.test;

import com.bankonet.Client;
import com.bankonet.Compte;
import com.bankonet.CompteCourant;
import com.bankonet.CompteEpargne;
import com.bankonet.Stockage;

public class TestStockage {

	public static void main(String[] args) {
		Client client1 = new Client("MATIGNON", "Valentin", "MATIGVAL");
		Client client2 = new Client("DUPOND", "Robert", "DUPRO");
		Client client3 = new Client("BIDULE", "Marcel", "BIMA");

		Stockage<String, Client> stockageClient = new Stockage<String, Client>();
		stockageClient.ajouter(client1.identifiant, client1);
		stockageClient.ajouter(client2.identifiant, client2);
		stockageClient.ajouter(client3.identifiant, client3);

		Client client = stockageClient.retournerElement(client1.identifiant);

		stockageClient.supprimer(client2.identifiant);

		CompteCourant compte1 = new CompteCourant("c1", "c1 name", 50, 100);
		CompteEpargne compte2 = new CompteEpargne("c2", "c2 name", 200, 2);
		CompteEpargne compte3 = new CompteEpargne("c3", "c3 name", 300, 3);

		Stockage<String, Compte> stockageCompte = new Stockage<String, Compte>();

		stockageCompte.ajouter(compte1.getNumero(), compte1);
		stockageCompte.ajouter(compte2.getNumero(), compte2);
		stockageCompte.ajouter(compte3.getNumero(), compte3);

		Compte compte = stockageCompte.retournerElement(compte1.getNumero());

		stockageCompte.supprimer(compte2.getNumero());
	}

}
