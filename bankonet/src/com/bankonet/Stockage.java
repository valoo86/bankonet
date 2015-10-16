package com.bankonet;

import java.util.HashMap;
import java.util.Map;

public class Stockage<T, E> {

	Map<T, E> stock = new HashMap<T, E>();

	public E retournerElement(T cle) {
		E element = stock.get(cle);
		
		return element;
	}
	
	public void ajouter(T cle, E element) {
		stock.put(cle, element);
	}
	
	public void supprimer(T	cle) {
		stock.remove(cle);
	}
}
