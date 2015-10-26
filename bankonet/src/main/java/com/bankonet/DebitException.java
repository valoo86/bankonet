package com.bankonet;

public class DebitException extends CompteException {

	public static final String DEBIT_EXCEPTION_MESSAGE = "Montant est sup�rieur � l'avoir disponible sur le compte ! (m�thode effectuerVirement)";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; /* Un serialVersionUID est g�n�r� � partir de la structure de la classe
	 au moment de la s�rialisation. Quand on veut d�s�rialiser la g�n�ration d'un serialVersionUID est �galement faite et celui-ci 
	 va �tre compar� avec le serialVersionUID enregistr� lors de la serialisation. Si celui-ci est diff�rent il y aura un probl�me
	 lors de la r�cup�ration de l'objet s�rialis�. Si on fixe nous m�me le serialVersionUID (comme ici avec serialVersionUID = 1L;)
	 l'objet s�rialis� sera bien r�cup�r� par contre si entre la s�rialisation de l'objet et sa d�s�rialisation la structure de la classe
	 change il y aura des pertes de donn�es. */

	public DebitException() {
		super();
	}

	public DebitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DebitException(String message, Throwable cause) {
		super(message, cause);
	}

	public DebitException(String message) {
		super(message);
	}

	public DebitException(Throwable cause) {
		super(cause);
	}
	
}
