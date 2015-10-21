package com.bankonet.dto;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
	// En commentaire pour le TP6
	// String numero;
	// String intitule;
	//
	// double solde;
	private Double montantDecouvertAutorise;

	public static int nbComptesCourants = 0;
	
	public static Map<String, CompteCourant> comptesCourantsMap = new HashMap<>();
	
	// En commentaire pour le TP6
	// public CompteCourant() {
	// super();
	// nbComptesCourants++;
	// }

	// En commentaire pour le TP6
	// public CompteCourant(String numero, String intitule, double solde, double
	// montantDecouvertAutorise) {
	// this();
	// this.numero = numero;
	// this.intitule = intitule;
	// this.solde = solde;
	// this.montantDecouvertAutorise = montantDecouvertAutorise;
	//
	//// nbComptesCourants++;
	// }
	//
	// @Override
	// public String toString() {
	// return "CompteCourant [numero=" + numero + ", intitule=" + intitule + ",
	// solde=" + solde
	// + ", montantDecouvertAutorise=" + montantDecouvertAutorise + "]";
	// }
	//
	// public void crediter(double montantACrediter) {
	// this.solde += montantACrediter;
	// }
	//
	// public void debiter(double montantADebiter) {
	// this.solde -= montantADebiter;
	// }

	/*
	 * 
	 * TP6
	 * 
	 * 
	 */

	public CompteCourant(String numero, String intitule, double solde, double montantDecouvertAutorise) {
		super(numero, intitule, solde);
		this.montantDecouvertAutorise = montantDecouvertAutorise;
	}

	public CompteCourant() {
		super();
	}

	@Override
	public String toString() {
		// mieux que le toString() du dessous car moins d'objets String de créés
		// (str1 + str2 + str3 => 2 String de créés)
		return String.format(
				"CompteCourant [montantDecouvertAutorise=%s, getNumero()=%s, getIntitule()=%s, getSolde()=%s]",
				montantDecouvertAutorise, getNumero(), getIntitule(), getSolde());
	}

	@Override
	public double getDebit() {
		return getSolde() + montantDecouvertAutorise;
	}

	// @Override
	// public String toString() {
	// return "CompteCourant [montantDecouvertAutorise=" +
	// montantDecouvertAutorise + ", numero=" + super.getNumero()
	// + ", intitule=" + super.getIntitule() + ", solde=" + super.getSolde() +
	// "]";
	// }

	// CompteStat
	// getSolde() est déjà implémenté dans la classe Compte (les getters et
	// setters ont été générés sur tous les attribu
	// @Override
	// public double getSolde() {
	// return super.getSolde();
	//
	// }

	public String obtenirFormatEnregistrementFichier() {
		StringBuilder sb = new StringBuilder();
		// Paul82=nom:Paul&prenom:Hugues&comptes_courant:CC001,CC002
		// ,CC003
		sb.append("intitule:" + this.getIntitule() + "&");
		sb.append("solde:" + this.getSolde() + "&");
		sb.append("montantDecouvertAutorise:" + this.montantDecouvertAutorise);

		return sb.toString();
	}

	public static CompteCourant creerCompteCourant(String numero, String format) {
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
				compteCourant.montantDecouvertAutorise = Double.parseDouble(att[1]);
				break;

			default:
				break;
			}

		}

		return compteCourant;
	}

	public double getMontantDecouvertAutorise() {
		return montantDecouvertAutorise;
	}

	public void setMontantDecouvertAutorise(double montantDecouvertAutorise) {
		this.montantDecouvertAutorise = montantDecouvertAutorise;
	}
}
