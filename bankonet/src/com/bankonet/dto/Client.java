package com.bankonet.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bankonet.CompteNonTrouveException;
import com.bankonet.ToString;

public class Client {
	@ToString(uppercase = true)
	public String nom;
	public String prenom;

	public String login;
	public String password;

	@ToString
	public String identifiant;

	public Civilite civilite;

	public List<Compte> comptesList = new ArrayList<Compte>();
	public Map<String, Compte> comptesMap = new HashMap<String, Compte>();

	public List<String> comptesListId = new ArrayList<String>();

	public List<String> comptesListComptesCourantsId = new ArrayList<String>();
	public List<String> comptesListComptesEpargneId = new ArrayList<String>();

	public static Map<String, Client> clientsMap = new HashMap<>();
	
	public Client() {
		super();
		this.password = "azerty";
//		comptesList.stream().filter(c -> c instanceof CompteCourant).collect(Collectors.toList());
	}

	public Client(String nom, String prenom, String identifiant, Civilite civilite) {
		this(nom, prenom, identifiant);
		this.civilite = civilite;
	}

	public Client(String nom, String prenom, String identifiant) {
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
	}

	public Collection<Compte> getComptesList() {
		return comptesList;
	}

	public Map<String, Compte> getComptesMap() {
		return comptesMap;
	}

	public Collection<String> getComptesListId() {
		return comptesListId;
	}

	public void creerCompte(Compte compte) {
		comptesList.add(compte); // un add cr�e un pointeur sur l'objet qu'on
		// veut ajouter et ajoute ce pointer dans la
		// List
	}

	public void creerCompteMap(Compte compte) {
		comptesMap.put(compte.getNumero(), compte);
	}

	public void creerCompteId(String compteId) {
		comptesListId.add(compteId); // un add cr�e un pointeur sur l'objet
		// qu'on
	}

	public void creerCompteCourantId(String compteCourantId) {
		comptesListComptesCourantsId.add(compteCourantId); // un add cr�e un pointeur sur l'objet
	}
	
	public void creerCompteEpargneId(String compteEpargneId) {
		comptesListComptesEpargneId.add(compteEpargneId); // un add cr�e un pointeur sur l'objet
	}
	
	public void supprimerCompte(Compte compte) {
		comptesList.remove(compte);
	}

	public void supprimerCompteMap(Compte compte) {
		comptesMap.remove(compte.getNumero());
	}

	public void supprimerCompteId(String compteId) {
		comptesListId.remove(compteId);
	}

	public void supprimerCompte(String numero) throws CompteNonTrouveException {
		// Iterator<Compte> iterator = comptesList.iterator();
		// while (iterator.hasNext()) {
		// Compte compte = iterator.next();
		// if (compte.getNumero().equals(numero)) {
		// iterator.remove();
		// break;
		// }
		// }
		Compte compte = retournerCompte(numero);
		if (compte != null) {
			comptesList.remove(compte);
		}

	}

	public void supprimerCompteMap(String numero) throws CompteNonTrouveException {
		Compte compte = retournerCompteMap(numero);
		if (compte != null) {
			comptesMap.remove(compte.getNumero());
		}
	}

	public Compte retournerCompte(String numero) throws CompteNonTrouveException {
		Compte result = null;
		Iterator<Compte> iterator = comptesList.iterator();
		while (iterator.hasNext()) {
			Compte compte = iterator.next();
			if (compte.getNumero().equals(numero)) {
				result = compte;
			}
		}
		if (result == null) {
			throw new CompteNonTrouveException(CompteNonTrouveException.COMPTE_NON_TROUVE_EXCEPTION_MESSAGE);
		} else {
			return result;
		}
	}

	public Compte retournerCompteMap(String numero) throws CompteNonTrouveException {
		Compte compte = comptesMap.get(numero);
		if (compte == null) {
			throw new CompteNonTrouveException(CompteNonTrouveException.COMPTE_NON_TROUVE_EXCEPTION_MESSAGE);
		} else {
			return compte;
		}
	}

	public double calculerAvoirGlobal() {
		double avoirGlobal = 0.0d;
		for (Compte compte : comptesList) {
			avoirGlobal += compte.getSolde();
		}
		return avoirGlobal;
	}

	public double calculerAvoirGlobalMap() {
		double avoirGlobal = 0.0d;
		Collection<Compte> comptes = comptesMap.values();
		for (Compte compte : comptes) {
			avoirGlobal += compte.getSolde();
		}
		return avoirGlobal;
	}

	// @Override
	// public String toString() {
	// // Field[] fields = Client.class.getFields();
	// Field[] fields = Client.class.getDeclaredFields();
	// StringBuilder sb = new StringBuilder("Client [");
	//
	// for (Field field : fields) {
	// for (Annotation annotation : field.getAnnotations()) {
	// if (annotation.annotationType().equals(ToString.class)) {
	// ToString annotationToString = field.getAnnotation(ToString.class);
	// try {
	// if (annotationToString.uppercase() == true) {
	// sb.append(field.getName() + "=" +
	// field.get(this).toString().toUpperCase()+", ");
	// } else {
	// sb.append(field.getName() + "=" + field.get(this)+", ");
	// }
	// }
	// catch (IllegalArgumentException | IllegalAccessException e) {
	// e.printStackTrace();
	// }
	// // catch (IllegalArgumentException e) {
	// // e.printStackTrace();
	// // } catch (IllegalAccessException e) {
	// // e.printStackTrace();
	// // }
	// }
	// }
	// }
	// sb.setLength(sb.length() - 2); //pas propre
	// sb.append("]");
	//
	// return sb.toString();
	// // return String.format("Client [nom=%s, prenom=%s, identifiant=%s,
	// // civilite=%s, comptesList=%s, comptesMap=%s]",
	// // nom, prenom, identifiant, civilite, comptesList, comptesMap);
	// }

	@Override
	public String toString() {
		return String.format(
				"Client [nom=%s, prenom=%s, login=%s, nombreComptesCourants=%s, nombreComptesEpargnes=%s]",
				nom, prenom, login, comptesListComptesCourantsId.size(), comptesListComptesEpargneId.size());
	}

	public String obtenirFormatEnregistrementFichier() {
		StringBuilder sb = new StringBuilder();
		// Paul82=nom:Paul&prenom:Hugues&comptes_courant:CC001,CC002
		// ,CC003
		// sb.append(this.login+"=");
		sb.append("nom:" + this.nom + "&");
		sb.append("prenom:" + this.prenom + "&");
		sb.append("password:" + this.password);
		if(comptesListComptesCourantsId.size() > 0){
			sb.append("&comptes_courant:");
			for (String compteId : comptesListComptesCourantsId) {
				sb.append(compteId + ",");
			}
			sb.setLength(sb.length() - 1);
		}
		
		if(comptesListComptesEpargneId.size() > 0){
			sb.append("&comptes_epargne:");
			for (String compteId : comptesListComptesEpargneId) {
				sb.append(compteId + ",");
			}
		}
		
		return sb.toString();
	}

	public static Client creerClient(String login, String format) {
		// nom:Paul&prenom:Hugues&comptes_courant:CC001,CC002
		// ,CC003
		Client client = new Client();

		client.login = login;

		String[] attributes = format.split("&");
		for (String attribute : attributes) {
			String[] att = attribute.split(":");
			if (att[0].equals("comptes_courant") || att[0].equals("comptes_epargne")) {
				// r�cup�rer les comptes
				String[] comptes = att[1].split(",");
				for (String compte : comptes) { // TODO : voir pour impl�menter
					// la r�cup�ration du compte
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

	public List<String> getComptesListComptesCourantsId() {
		return comptesListComptesCourantsId;
	}

	public void setComptesListComptesCourantsId(List<String> comptesListComptesCourantsId) {
		this.comptesListComptesCourantsId = comptesListComptesCourantsId;
	}

	public List<String> getComptesListComptesEpargneId() {
		return comptesListComptesEpargneId;
	}

	public void setComptesListComptesEpargneId(List<String> comptesListComptesEpargneId) {
		this.comptesListComptesEpargneId = comptesListComptesEpargneId;
	}
}
