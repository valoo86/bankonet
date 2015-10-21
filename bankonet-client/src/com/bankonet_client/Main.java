package com.bankonet_client;

import static com.bankonet.dto.Client.clientsMap;
import static com.bankonet.dto.CompteCourant.comptesCourantsMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;

import com.bankonet.CompteException;
import com.bankonet.dto.Client;
import com.bankonet.dto.Compte;
import com.bankonet.dto.CompteCourant;

public class Main {

	private static final String MENU_MESSAGE = "***** APPLICATION CLIENT ******\n\n0. Arrêter le programme\n1. Consulter les soldes des comptes\n2. Effectuer un dépôt\n3. Effectuer un retrait\nVeuillez choisir une action.";
	private static Scanner sc;

	private static Client clientCourant;

	public static void main(String[] args) {
		lancerApplication();
	}

	public static void lancerApplication() {
		System.out.println("Saisissez votre login.");
		sc = new Scanner(System.in);
		String loginSaisi = sc.nextLine();

		System.out.println("Saisissez votre mot de passe.");
		String mdpSaisi = sc.nextLine();

		if (tantiveDeConnexion(loginSaisi, mdpSaisi) == true) {
			System.out.println("La connexion a été effectuée !");
			afficherMenu();
		} else {
			System.err.println("Une erreur s'est produite lors de la tentative de connexion");
			lancerApplication();
		}
	}

	public static void afficherMenu() {
		System.out.println(MENU_MESSAGE);

		String choix = sc.nextLine();
		traiterLeChoixUtilisateur(choix);
	}
	
	public static void traiterLeChoixUtilisateur(String choix) {
		switch (choix) {
		case "0":
			System.out.println("Vous avez choisi d'arrêter le programme courant.\nArrêt de l’application");
			System.exit(0);
			break;

		case "1":
			System.out.println("Vous avez choisi de consulter les soldes des comptes");
			try {
				récupérerLaListeDesComptes();
				afficherLesComptesDeLutilisateurCourant();
			} catch (IOException e) {
				System.err.println("Une erreur s'est produite.");
			}finally {
				afficherMenu();
			}
			break;

		case "2":
			System.out.println("Vous avez choisi d'effectuer un dépôt");
			try {
				récupérerLaListeDesComptes();
				afficherLesComptesDeLutilisateurCourant();

				if(clientCourant.getComptesListComptesCourantsId().size() > 0) {
					Compte compteSaisi = null;
					do {
						System.out.println("Choisissez le compte à créditer");
						String compteIdSaisi = sc.nextLine();
						compteSaisi = comptesCourantsMap.get(compteIdSaisi);
					} while(compteSaisi == null);
					
					System.out.println("Saisissez un montant");
					double montantSaisi = Double.parseDouble(sc.nextLine());
					
					compteSaisi.crediter(montantSaisi);
					enregistrementDesComptes();
					System.out.println("Votre nouveau solde est: "+compteSaisi.getSolde());
					afficherMenu();
				}
			} catch (IOException e) {
				System.err.println("Une erreur s'est produite.");
				afficherMenu();
			}

			break;
			
		case "3":
			System.out.println("Vous avez choisi d'effectuer un retrait");
			try {
				récupérerLaListeDesComptes();
				afficherLesComptesDeLutilisateurCourant();

				if(clientCourant.getComptesListComptesCourantsId().size() > 0) {
					Compte compteSaisi = null;
					do {
						System.out.println("Choisissez le compte à débiter");
						String compteIdSaisi = sc.nextLine();
						compteSaisi = comptesCourantsMap.get(compteIdSaisi);
					} while(compteSaisi == null);
					
					System.out.println("Saisissez un montant");
					double montantSaisi = Double.parseDouble(sc.nextLine());
					
					compteSaisi.debiter(montantSaisi);
					enregistrementDesComptes();
					System.out.println("Votre nouveau solde est: "+compteSaisi.getSolde());
					afficherMenu();
				}
			} catch (IOException e) {
				System.err.println("Une erreur s'est produite.");
			} catch (CompteException e) {
				System.err.println(e.getMessage());
			}finally {
				afficherMenu();
			}

			break;

		default:
			System.err.println("Vous n'avez pas saisi une option valide !\nVeuillez resaisir votre action.");
			choix = sc.nextLine();
			traiterLeChoixUtilisateur(choix);
			break;
		}
	}

	public static void afficherLesComptesDeLutilisateurCourant() {
		for (String compteId : clientCourant.getComptesListComptesCourantsId()) {
			System.out.println(comptesCourantsMap.get(compteId).toString());
		}
	}

	public static boolean tantiveDeConnexion(String login, String mdp) {
		boolean resultat = false;
		clientCourant = null;

		try {
			récupérerLaListeDesClients();
			for (Client client : clientsMap.values()) {
				if (client.getLogin().equals(login) && client.getPassword().equals(mdp)) {
					resultat = true;
					clientCourant = client;
				}
			}
		} catch (IOException e) {
			System.err.println("Une erreur s'est produite lors de la tentative de connexion");
			lancerApplication();
		}

		return resultat;
	}

	public static void récupérerLaListeDesClients() throws IOException {
		clientsMap.clear();
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					"C:/Users/ETY/Desktop/Formation DTA/Java/Eclipse/bankonet-conseiller/clients.properties");

			// load a properties file
			prop.load(input);

			Enumeration<Object> enumeration = prop.keys();
			while (enumeration.hasMoreElements()) {
				String login = (String) enumeration.nextElement();
				String format = prop.getProperty(login);
				Client client = Client.creerClient(login, format);
				clientsMap.put(client.getLogin(), client);
			}
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void enregistrementDesComptes() throws IOException {
		
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("C:/Users/ETY/Desktop/Formation DTA/Java/Eclipse/bankonet-conseiller/comptes.properties");
			
			for (CompteCourant compte : comptesCourantsMap.values()) {
				prop.setProperty(compte.getNumero(), compte.obtenirFormatEnregistrementFichier());
			}
			// save properties to project root folder
			prop.store(output, null);

		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public static void récupérerLaListeDesComptes() throws IOException {
		comptesCourantsMap.clear();
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					"C:/Users/ETY/Desktop/Formation DTA/Java/Eclipse/bankonet-conseiller/comptes.properties");

			// load a properties file
			prop.load(input);

			Enumeration<Object> enumeration = prop.keys();
			while (enumeration.hasMoreElements()) {
				String numero = (String) enumeration.nextElement();
				String format = prop.getProperty(numero);

				CompteCourant compteCourant = CompteCourant.creerCompteCourant(numero, format);
				comptesCourantsMap.put(compteCourant.getNumero(), compteCourant);
			}
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
