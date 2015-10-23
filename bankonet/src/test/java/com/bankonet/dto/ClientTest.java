package com.bankonet.dto;

import org.junit.Assert;
import org.junit.Test;

import com.bankonet.CompteNonTrouveException;

public class ClientTest {
	
	@Test
	public void testRetournerCompteNumeroInconnu() {
		Client client = new Client();
		try {
			client.retournerCompte("xxxx");
			Assert.fail("CompteNonTrouveException attendu");
		} catch (CompteNonTrouveException e) {
			// ok
		}
	}
	
	@Test
	public void testRetournerCompteNumeroInconnuAvecCompte() {
		Client client = new Client();
		client.creerCompte(new CompteCourant("CC01", "blabla", 0, 0));
		try {
			client.retournerCompte("CC02");
			Assert.fail("CompteNonTrouveException attendu");
		} catch (CompteNonTrouveException e) {
			// ok
		}
	}
	
	@Test
	public void testRetournerCompteNumeroConnu() {
		Client client = new Client();
		client.creerCompte(new CompteCourant("CC01", "blabla", 0, 0));
		try {
			client.retournerCompte("CC01");
			//ok
		} catch (CompteNonTrouveException e) {
			Assert.fail("CompteNonTrouveException inattendu");
		}
	}

}
