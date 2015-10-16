package com.bankonet.test;

import java.util.Map;

import com.bankonet.CompteNonTrouveException;
import com.bankonet.dto.Civilite;
import com.bankonet.dto.Client;
import com.bankonet.dto.Compte;
import com.bankonet.dto.CompteCourant;
import com.bankonet.dto.CompteEpargne;;

public class TestClient {

	public static void main(String[] args) {
		CompteCourant compte1 = new CompteCourant("c2", "c2 name", 50, 100);
		CompteEpargne compte2 = new CompteEpargne("c3", "c3", 300, 3);

		// try {
		// compte1.debiter(300);
		// compte2.debiter(150);
		// } catch (DebitException e) {
		// System.out.println("DebitException :"+e.getMessage());
		// }

		// try {
		// compte1.effectuerVirement(compte2, 50000);
		// //compte1.effectuerVirement(compte2, 201);
		// } catch (DebitException e) {
		// System.out.println("DebitException :"+e.getMessage());
		// } catch (CreditException e) {
		// System.out.println("CreditException :"+e.getMessage());
		// } finally {
		// System.out.println("solde compte1 = "+compte1.getSolde());
		// System.out.println("solde compte2 = "+compte2.getSolde());
		// }

		// try {
		// compte1.effectuerVirement(compte2, 50000);
		// //compte1.effectuerVirement(compte2, 201);
		// } catch (CompteException e) {
		// e.printStackTrace();
		// System.out.println("CompteException :"+e.getMessage());
		// }
		//// catch (CreditException e) { //compilation fail : exception déjà
		// gérée par la classe mère
		//// System.out.println("CreditException :"+e.getMessage());
		//// }
		// finally {
		// System.out.println("solde compte1 = "+compte1.getSolde());
		// System.out.println("solde compte2 = "+compte2.getSolde());
		// }

		// Partie List
		// Client client = new Client("MATIGNON", "Valentin", "MATIGVAL");
		// client.creerCompte(compte1);
		// client.creerCompte(compte2);
		//
		// client.supprimerCompte(compte1);
		// try {
		// client.supprimerCompte(compte2.getNumero());
		// } catch (CompteNonTrouveException e) {
		// System.out.println("CompteNonTrouveException: "+e.getMessage());
		// }
		//
		// try {
		// client.supprimerCompte(compte1.getNumero());
		// } catch (CompteNonTrouveException e) {
		// System.out.println("CompteNonTrouveException: "+e.getMessage());
		// }
		//
		// Collection<Compte> list = client.getComptesList();
		// System.out.println("avoir global = "+client.calculerAvoirGlobal());

		// Partie Map
		Client client = new Client("MATIGNON", "Valentin", "MATIGVAL");
		client.creerCompteMap(compte1);
		client.creerCompteMap(compte2);

		client.supprimerCompteMap(compte1);
		try {
			client.supprimerCompteMap(compte2.getNumero());
		} catch (CompteNonTrouveException e) {
			System.out.println("CompteNonTrouveException: " + e.getMessage());
		}

		try {
			client.supprimerCompteMap(compte1.getNumero());
		} catch (CompteNonTrouveException e) {
			System.out.println("CompteNonTrouveException: " + e.getMessage());
		}

		Map<String, Compte> map = client.getComptesMap();
		System.out.println("avoir global = " + client.calculerAvoirGlobalMap());

		// TP10
		Client clientCiviliteMonsieur = new Client("MARTIN", "Bob", "MARBOB", Civilite.MONSIEUR);
		Client clientCiviliteMadame = new Client("DUFOUR", "Martine", "DUFMAR", Civilite.MADAME);
		Client clientCiviliteMademoiselle = new Client("FOUX", "Coralie", "MARBOB", Civilite.MADEMOISELLE);

		Client[] clients = new Client[3];
		clients[0] = clientCiviliteMonsieur;
		clients[1] = clientCiviliteMadame;
		clients[2] = clientCiviliteMademoiselle;

		for (Client clt : clients) {
			switch (clt.civilite) {
			case MONSIEUR:
			case MADAME:
			case MADEMOISELLE:
				// System.out.println(clt.toString());
				// System.out.println("Civilité: "+clt.civilite);
				// System.out.println("Civilité value:
				// "+clt.civilite.getCivilite());
				break;

			default:
				break;
			}
		}
		System.out.println(Civilite.MADAME.compareTo(Civilite.MADEMOISELLE));
		System.out.println(Civilite.MADAME.equals(Civilite.MADAME));
		System.out.println(Civilite.values().length);

		// TP11
		Client aClient = new Client("MATIGNON", "Thierry", "MATIGTHI", Civilite.MONSIEUR);
		String aClientToString = aClient.toString();
		System.out.println(aClientToString);
	}
}
