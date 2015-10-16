package com.bankonet.dao.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.bankonet.Client;
import com.bankonet.ClientException;

public class ClientDaoFile implements ClientDao {
	private Map<String, Client> clientsMap;

	@Override
	public Map<String, Client> findAll() throws ClientException {
		clientsMap = new HashMap<>();
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
				Client client = creerClient(login, format);
				clientsMap.put(client.login, client);
			}
			
			return clientsMap;
		} catch (IOException e) {
			throw new ClientException("Erreur lors de la récupération des clients", e);
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

	@Override
	public void save(Client client) throws ClientException {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(
					"C:/Users/ETY/Desktop/Formation DTA/Java/Eclipse/bankonet-conseiller/clients.properties");

			for (Client aClient : clientsMap.values()) {
				prop.setProperty(aClient.login, obtenirFormatEnregistrementFichier(aClient));
			}

			// set the properties value
			prop.setProperty(client.login, obtenirFormatEnregistrementFichier(client));

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException e) {
			throw new ClientException("Erreur lors de l'enregistrement du client", e);
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
	
	private String obtenirFormatEnregistrementFichier(Client client) {
		StringBuilder sb = new StringBuilder();
		// Paul82=nom:Paul&prenom:Hugues&comptes_courant:CC001,CC002
		// ,CC003
		// sb.append(this.login+"=");
		sb.append("nom:" + client.nom + "&");
		sb.append("prenom:" + client.prenom + "&");
		sb.append("password:" + client.password);
		if(client.comptesListComptesCourantsId.size() > 0){
			sb.append("&comptes_courant:");
			for (String compteId : client.comptesListComptesCourantsId) {
				sb.append(compteId + ",");
			}
			sb.setLength(sb.length() - 1);
		}
		
		if(client.comptesListComptesEpargneId.size() > 0){
			sb.append("&comptes_epargne:");
			for (String compteId : client.comptesListComptesEpargneId) {
				sb.append(compteId + ",");
			}
		}
		
		return sb.toString();
	}
	
	private Client creerClient(String login, String format) {
		// nom:Paul&prenom:Hugues&comptes_courant:CC001,CC002
		// ,CC003
		Client client = new Client();

		client.login = login;

		String[] attributes = format.split("&");
		for (String attribute : attributes) {
			String[] att = attribute.split(":");
			if (att[0].equals("comptes_courant") || att[0].equals("comptes_epargne")) {
				// récupérer les comptes
				String[] comptes = att[1].split(",");
				for (String compte : comptes) { // TODO : voir pour implémenter
					// la récupération du compte
					if (att[0].equals("comptes_courant")) {
						client.comptesListComptesCourantsId.add(compte);
					} else if (att[0].equals("comptes_epargne")) {
						client.comptesListComptesEpargneId.add(compte);
					}
				}
			} else {
				switch (att[0]) {
				case "nom":
					client.nom = att[1];
					break;

				case "prenom":
					client.prenom = att[1];
					break;

				case "password":
					client.password = att[1];
					break;

				default:
					break;
				}
			}
		}

		return client;
	}
	
}
