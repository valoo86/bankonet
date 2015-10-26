package com.bankonet;

public class DebitException extends CompteException {

	public static final String DEBIT_EXCEPTION_MESSAGE = "Montant est supérieur à l'avoir disponible sur le compte ! (méthode effectuerVirement)";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; /* Un serialVersionUID est généré à partir de la structure de la classe
	 au moment de la sérialisation. Quand on veut désérialiser la génération d'un serialVersionUID est également faite et celui-ci 
	 va être comparé avec le serialVersionUID enregistré lors de la serialisation. Si celui-ci est différent il y aura un problème
	 lors de la récupération de l'objet sérialisé. Si on fixe nous même le serialVersionUID (comme ici avec serialVersionUID = 1L;)
	 l'objet sérialisé sera bien récupéré par contre si entre la sérialisation de l'objet et sa désérialisation la structure de la classe
	 change il y aura des pertes de données. */

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
