package com.bankonet.dao.compte;

import static com.bankonet.dto.CompteCourant.comptesCourantsMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.bankonet.CompteException;
import com.bankonet.dto.Compte;
import com.bankonet.dto.CompteCourant;

public class CompteDaoFile implements CompteDao{
	private Map<String, CompteCourant> comptesMap;
	
	@Override
	public Map<String, CompteCourant> findAllCC() throws CompteException {
		comptesMap = new HashMap<>();
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
				CompteCourant compteCourant = creerCompteCourant(numero, format);
				comptesCourantsMap.put(compteCourant.getNumero(), compteCourant);
			}

			return comptesMap;
		} 
		catch (IOException e) {
			throw new CompteException("Erreur lors de la récupération des comptes", e);
		}finally {
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
	public void saveCC(CompteCourant compteCourant) throws CompteException {
		Properties prop = new Properties();
		OutputStream output = null;
		findAll();
		
		try {

			output = new FileOutputStream("C:/Users/ETY/Desktop/Formation DTA/Java/Eclipse/bankonet-conseiller/comptes.properties");

			for (CompteCourant compte : comptesCourantsMap.values()) {
				prop.setProperty(compte.getNumero(), compte.obtenirFormatEnregistrementFichier());
			}

			// set the properties value
			prop.setProperty(compteCourant.getNumero(), obtenirFormatEnregistrementFichier(compteCourant));

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException e) {
			throw new CompteException("Erreur lors de la récupération des comptes", e);
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
	
	private CompteCourant creerCompteCourant(String numero, String format) {
		CompteCourant compteCourant = new CompteCourant();
		compteCourant.setNumero(numero);
		
		String[] attributes = format.split("&");
		for (String attribute : attributes) {
			String[] att = attribute.split(":");

			switch (att[0]) {
			case "intitule":
				compteCourant.setIntitule(att[1]);
				break;

			case "solde":
				compteCourant.setSolde(Double.parseDouble(att[1]));
				break;

			case "montantDecouvertAutorise":
				compteCourant.setMontantDecouvertAutorise(Double.parseDouble(att[1]));
				break;

			default:
				break;
			}

		}

		return compteCourant;
	}
	
	private String obtenirFormatEnregistrementFichier(CompteCourant compteCourant) {
		StringBuilder sb = new StringBuilder();
		// Paul82=nom:Paul&prenom:Hugues&comptes_courant:CC001,CC002
		// ,CC003
		sb.append("intitule:" + compteCourant.getIntitule() + "&");
		sb.append("solde:" + compteCourant.getSolde() + "&");
		sb.append("montantDecouvertAutorise:" + compteCourant.getMontantDecouvertAutorise());

		return sb.toString();
	}


	@Override
	public Map<String, Compte> findAll() throws CompteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(Compte compte) throws CompteException {
		// TODO Auto-generated method stub
		
	}
	
}
