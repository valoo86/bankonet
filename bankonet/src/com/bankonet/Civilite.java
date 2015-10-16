package com.bankonet;

public enum Civilite {
	MONSIEUR("Monsieur"), MADAME("Madame"), MADEMOISELLE("Mademoiselle");

	private String civilite;

	private Civilite(String civilite) {
		this.civilite = civilite;
	}

	public String getCivilite() {
		return civilite;
	}
}
