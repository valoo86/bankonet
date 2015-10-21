package com.bankonet.conseiller;

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

import com.bankonet.dto.Client;
import com.bankonet.dto.CompteCourant;
import com.bankonet.dto.CompteEpargne;

public class Main {

	public static final String MENU_MESSAGE = "*****	APPLICATION	CONSEILLER	BANCAIRE ******\n0.	Arrêter	le programme\n1.	Ouvrir un compte courant\n2.	Lister tous les	clients\n3.	Ajouter un compte courant\nVeuillez choisir une action.";
	private static Scanner sc;

	public static void main(String[] args) {
		lancerApplication();
	}

	public static void lancerApplication() {
		System.out.println(MENU_MESSAGE);
		sc = new Scanner(System.in);
		String choix = sc.nextLine();
		// int choix = sc.nextInt();
		traiterLeChoixUtilisateur(choix);
	}

	public static void traiterLeChoixUtilisateur(String choix) {
		switch (choix) {
		case "0":
			System.out.println("Vous avez choisi d'arrêter le programme courant.\nArrêt de l’application");
			System.exit(0);
			break;

		case "1":
			System.out.println("Vous avez choisi d'ouvrir un compte courant.");
			traiterLaCreation();
			break;

		case "2":
			System.out.println("Vous avez choisi de lister tous les clients.");
			try {
				récupérerLaListeDesClients();
				afficherLaListeDesClients();
			} catch (IOException e) {
				System.err.println("Une erreur s'est produite.");
			}finally {
				lancerApplication();
			}
			break;

		case "3":
			System.out.println("Vous avez choisi d'ajouter un compte courant.");
			
			try {
				récupérerLaListeDesClients();
				afficherLaListeDesClients();
				if(clientsMap.size() > 0) {
					Client clientSaisi = null;
					do {
						System.out.println("Choisissez un client");
						String clientId = sc.nextLine();
						clientSaisi = clientsMap.get(clientId);
					} while(clientSaisi == null);

					CompteCourant compteCourant = genererCompteCourantPourLeClient(clientSaisi);
					try {
						enregistrementDuClient(clientSaisi);
						enregistrementDuCompteCourant(compteCourant);
						lancerApplication();
					} catch (IOException e) {
						System.err.println("Une erreur s'est produite merci de recommencer la saisie");
						traiterLaCreation();
					}
					//					génère un compte épargne avec le libellé
					//					[NOM_CLIENT]_[PRENOM_CLIENT]_EPARGNE_X » où X vaut 2 s’il s’agit du 2nd compte
					//					courant, 3 s’il s’agit du 3e compte courant, …

				}

			} catch (IOException e) {
				System.err.println("Une erreur s'est produite.");
			}
			break;

//		case "4":
//			System.out.println("Vous avez choisi d'ajouter un compte épargne.");
//			try {
//				récupérerLaListeDesClients();
//				afficherLaListeDesClients();
//				if(clientsMap.size() > 0) {
//					Client clientSaisi = null;
//					do {
//						System.out.println("Choisissez un client");
//						String clientId = sc.nextLine();
//						clientSaisi = clientsMap.get(clientId);
//					} while(clientSaisi == null);
//
//					CompteEpargne compteEpargne = genererCompteEpargnePourLeClient(clientSaisi);
//					try {
//						enregistrementDuClient(clientSaisi);
//						enregistrementDuCompteCourant(compteCourant);
//						lancerApplication();
//					} catch (IOException e) {
//						System.err.println("Une erreur s'est produite merci de recommencer la saisie");
//						traiterLaCreation();
//					}
//					//					génère un compte épargne avec le libellé
//					//					[NOM_CLIENT]_[PRENOM_CLIENT]_EPARGNE_X » où X vaut 2 s’il s’agit du 2nd compte
//					//					courant, 3 s’il s’agit du 3e compte courant, …
//
//				}
//
//			} catch (IOException e) {
//				System.err.println("Une erreur s'est produite.");
//			}
//			break;

		default:
			System.err.println("Vous n'avez pas saisi une option valide !\nVeuillez resaisir votre action.");
			choix = sc.nextLine();
			traiterLeChoixUtilisateur(choix);
			break;
		}
	}

	public static CompteEpargne genererCompteEpargnePourLeClient(Client client) {
		CompteEpargne compteEpargne = new CompteEpargne(client.getLogin()+"_CE"+clientsMap.size()+1, client.getNom() + "_" + client.getPrenom() + "_EPARGNE_"+clientsMap.size()+1, 300,
				400);
		client.creerCompteEpargneId(compteEpargne.getNumero());

		return compteEpargne;
	}

	public static void traiterLaCreation() {
		Client client = new Client();

		System.out.println("Veuillez saisir votre nom.");
		client.setNom(sc.nextLine());

		System.out.println("Veuillez saisir votre prénom.");
		client.setPrenom(sc.nextLine());

		System.out.println("Veuillez saisir votre login.");
		client.setLogin(sc.nextLine());
		CompteCourant compteCourant = genererCompteCourantPourLeClient(client);

		try {
			enregistrementDuClient(client);
			enregistrementDuCompteCourant(compteCourant);
			lancerApplication();
		} catch (IOException e) {
			System.err.println("Une erreur s'est produite merci de recommencer la saisie");
			traiterLaCreation();
		} 
	}

	public static void afficherLaListeDesClients() {
		for (Client client : clientsMap.values()) {
			System.out.println(client.toString());
		}
	}

	public static void récupérerLaListeDesClients() throws IOException {
		clientsMap.clear();
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("clients.properties");

			// load a properties file
			prop.load(input);

			Enumeration<Object> enumeration = prop.keys();
			while(enumeration.hasMoreElements()) {
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

	public static CompteCourant genererCompteCourantPourLeClient(Client client) {
//		CompteCourant compteCourant = new CompteCourant(client.login+"_CC001", client.nom + "_" + client.prenom + "_COURANT_1", 300,
//				400);
		CompteCourant compteCourant = new CompteCourant(client.getLogin()+"_CC00"+(client.getComptesListComptesCourantsId().size()+1), client.getNom() + "_" + client.getPrenom() + "_COURANT_"+(client.getComptesListComptesCourantsId().size()+1), 300,
				400);
		client.creerCompteCourantId(compteCourant.getNumero());

		return compteCourant;
	}

	public static void enregistrementDuClient(Client client) throws IOException {
		récupérerLaListeDesClients();

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("clients.properties");

			for (Client aClient : clientsMap.values()) {
				prop.setProperty(aClient.getLogin(), aClient.obtenirFormatEnregistrementFichier());
			}

			// set the properties value
			prop.setProperty(client.getLogin(), client.obtenirFormatEnregistrementFichier());

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

	
	
	public static void enregistrementDuCompteCourant(CompteCourant compteCourant) throws IOException {
		récupérerLaListeDesComptes();

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("comptes.properties");

			for (CompteCourant compte : comptesCourantsMap.values()) {
				prop.setProperty(compte.getNumero(), compte.obtenirFormatEnregistrementFichier());
			}

			// set the properties value
			prop.setProperty(compteCourant.getNumero(), compteCourant.obtenirFormatEnregistrementFichier());

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
