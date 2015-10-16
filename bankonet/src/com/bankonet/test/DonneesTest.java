package com.bankonet.test;

import com.bankonet.CompteCourant;
import com.bankonet.CompteEpargne;
import com.bankonet.CompteStat;

public class DonneesTest {
	public static CompteStat[] construitEchantillonComptes(){
		CompteStat[] comptes = new CompteStat[3];
		
		comptes[0] = new CompteCourant("c1", "c1 name", 1, 100);
		comptes[1] = new CompteCourant("c2", "c2 name", 2, 200);
		comptes[2] = new CompteEpargne("c3", "c3", 300, 3);
		
		return comptes;
	}
}
