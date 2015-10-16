package com.bankonet_conseiller.presentation;

import com.bankonet.dao.DaoFactory;
import com.bankonet.dao.DaoFactoryFile;

public class Conseiller {
	
	private DaoFactory factory;
	
	public static void main(String[] args) {
		
		DaoFactory factory = new DaoFactoryFile();
		
		Conseiller conseiller = new Conseiller(factory);
		conseiller.start();
	}
	
	
	
	public Conseiller(DaoFactory factory) {
		super();
		this.factory = factory;
	}



	public void start() {
		
	}
	
}
