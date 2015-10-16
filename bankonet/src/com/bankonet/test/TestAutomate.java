package com.bankonet.test;

import com.bankonet.CompteStat;

public class TestAutomate {

	public static void main(String[] args) {
		CompteStat[] comptes = DonneesTest.construitEchantillonComptes();
		
		double totalSoldes = 0;
		for (CompteStat compteStat : comptes) {
			totalSoldes += compteStat.getSolde();
		}
		
		System.out.println("Moyenne des soldes = "+totalSoldes/comptes.length);
		
		//byte num = (byte) 15666;
		byte num = 127;
		//long l = 2147483680000; //marche pas car par défaut int
		long l = 2147483680000L;
		System.out.println("Num = "+num);
	}

}
