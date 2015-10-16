package com.bankonet;

import com.bankonet.dto.CompteCourant;
import com.bankonet.dto.CompteEpargne;

public class TestCompteCourant {

	public static void main(String[] args)
	{
		//En commentaire pour le TP6
		CompteCourant  compteCourant1 = new CompteCourant("numero1", "compte1", 1, 100);
//
		CompteCourant  compteCourant2 = new CompteCourant("numero2", "compte2", 2, 200);
//
//		CompteCourant  compteCourant3 = new CompteCourant("numero3", "compte3", 3, 300);

		System.out.println("nbComptesCourants ="+CompteCourant.nbComptesCourants);
		System.out.println("nbComptesCourants ="+CompteCourant.nbComptesCourants);
		System.out.println("nbComptesCourants ="+CompteCourant.nbComptesCourants);
		//
		//		System.out.println(compteCourant1);
		//		System.out.println(compteCourant2.toString());
		//		System.out.println(compteCourant3.toString());
		
		//En commentaire pour le TP6
//		compteCourant1.crediter(100.00);
//		System.out.println("Nouveau solde de compteCourant1: "+compteCourant1.solde);
//		compteCourant2.crediter(2500.00);
//		System.out.println("Nouveau solde de compteCourant2: "+compteCourant2.solde);
//		compteCourant3.debiter(200.00);
//		System.out.println("Nouveau solde de compteCourant3: "+compteCourant3.solde);

		//		String str = "test";
		//
		//		switch (str) {
		//		case "test":
		//			System.out.println("test1");
		//			break;
		//
		//		case "titi":
		//			System.out.println("titi1");
		//			break;
		//
		//		default:
		//			System.out.println("default1");
		//			break;
		//		}

		//En commentaire pour le TP6
//		CompteCourant[] comptesCourants = new CompteCourant[3];
//		comptesCourants[0] = compteCourant1;
//		comptesCourants[1] = compteCourant2;
//		comptesCourants[2] = compteCourant3;
//
//		for (CompteCourant compteCourant : comptesCourants) {
//			if(compteCourant.solde < 0) {
//				System.out.println("Attention le solde du compte "+compteCourant.intitule+" est négatif !");
//				compteCourant.solde = 0;
//			}
//		}
//
//		int solde = 1000;
//
//		//switch
//		switch (solde) {
//		case 0:
//			System.out.println("solde nul");
//			break;
//
//		case 10000:
//			System.out.println("solde créditeur");
//			break;
//
//		default:
//			System.out.println("ras");
//			break;
//		}
//		
//		//for
//		for (int i = 0; i < comptesCourants.length; i++) {
//			System.out.println("compteCourant"+i+" "+comptesCourants[i]);
//		}
		
		//Exemple d'une classe anonyme (à partir d'une classe abtraite)
//		Compte compte = new Compte("toto", "titi", 0) {
//			int anInt = 0;
//			
//			public int genAnInt() {
//				return anInt;
//			}
//		};
		
		//Exemple d'une classe anonyme (à partir d'un interface)
		CompteStat compteStat = new CompteStat() {
			
			@Override
			public double getSolde() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	
		
		//TP7
		CompteCourant compte1 = new CompteCourant("c2", "c2 name", 50, 100);
		CompteEpargne compte2 = new CompteEpargne("c3", "c3", 300, 3);
		
//		try {
//			compte1.debiter(300);
//			compte2.debiter(150);
//		} catch (DebitException e) {
//			System.out.println("DebitException :"+e.getMessage());
//		}
		
		try {
			compte1.effectuerVirement(compte2, 100);
			compte1.effectuerVirement(compte2, 201);
		}
		catch (DebitException | CreditException e1) {
			System.out.println("DebitException :"+e1.getMessage());
		}
		catch (CompteException e) {
			System.out.println("Coucou");
		}
		finally {
			System.out.println("solde compte1 = "+compte1.getSolde());	
			System.out.println("solde compte2 = "+compte2.getSolde());
		}
		
		
	}
}
