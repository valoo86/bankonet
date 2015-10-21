package com.bankonet.dto;

import java.util.HashMap;
import java.util.Map;

public class CompteEpargne extends Compte{

	private double tauxInteret;
	static final double PLAFOND = 2000;
	
	public static Map<String, CompteEpargne> comptesEpargnesMap = new HashMap<>();
	
	public CompteEpargne() {
		super();
	}

	public CompteEpargne(String numero, String intitule, double solde, double tauxInteret) {
		super(numero, intitule, solde);
		this.tauxInteret = tauxInteret;
	}

	@Override
	public String toString() {
		return "CompteEpargne [tauxInteret=" + tauxInteret + ", numero=" + super.getNumero() + ", intitule=" + super.getIntitule()
				+ ", solde=" + super.getSolde() + "]";
	}

	@Override
	public double getDebit() {
		return getSolde();
	}
	
	public String obtenirFormatEnregistrementFichier() {
		StringBuilder sb = new StringBuilder();
		// Paul82=nom:Paul&prenom:Hugues&comptes_courant:CC001,CC002
		// ,CC003
		sb.append("intitule:" + this.getIntitule() + "&");
		sb.append("solde:" + this.getSolde() + "&");
		sb.append("tauxInteret:" + this.tauxInteret);

		return sb.toString();
	}
	
	public static CompteEpargne creerCompteEpargne(String numero, String format) {
		CompteEpargne compteEpargne = new CompteEpargne();
		compteEpargne.setNumero(numero);
		
		String[] attributes = format.split("&");
		for (String attribute : attributes) {
			String[] att = attribute.split(":");

			switch (att[0]) {
			case "intitule":
				compteEpargne.setIntitule(att[1]);
				break;

			case "solde":
				compteEpargne.setSolde(Double.parseDouble(att[1]));
				break;

			case "tauxInteret":
				compteEpargne.tauxInteret = Double.parseDouble(att[1]);
				break;

			default:
				break;
			}

		}

		return compteEpargne;
	}

	public double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}
	
	//CompteStat
//	@Override
//	public double getSolde() {
//		return super.getSolde();
//		
//	}
}
