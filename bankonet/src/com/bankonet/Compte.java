package com.bankonet;

public abstract class Compte implements CompteStat{
	
	enum CompteType {
		COURANT,
		EPARGNE;
		}
	
	private String numero;
	private String intitule;
	
	private double solde;
	
	public Compte() {
		super();
	}

	public Compte(String numero, String intitule, double solde) {
		super();
		this.numero = numero;
		this.intitule = intitule;
		this.solde = solde;
	}

	public void crediter(double montantACrediter) {
		this.solde += montantACrediter;
	}

	public void debiter(double montantADebiter) throws CompteException {
		if(montantADebiter > getDebit()) {
			throw new CompteException(DebitException.DEBIT_EXCEPTION_MESSAGE);
		}
		
		this.solde -= montantADebiter;
	}
	
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", intitule=" + intitule + ", solde=" + solde + "]";
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	//public void effectuerVirement(Compte compte, double montant) throws DebitException, CreditException {
	public void effectuerVirement(Compte compte, double montant) throws CompteException{
		if(montant > CompteEpargne.PLAFOND) {
			throw new CreditException(CreditException.CREDIT_EXCEPTION_MESSAGE);
		} 
		else if(montant > compte.getDebit()) {
			throw new DebitException(DebitException.DEBIT_EXCEPTION_MESSAGE);
		}
		else {
			compte.debiter(montant);
			crediter(montant);
		}
	}
	
	//méthodes abtraites
	public abstract double getDebit();
	
	public abstract String obtenirFormatEnregistrementFichier();
}
