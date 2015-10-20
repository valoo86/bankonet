package com.bankonet.conseiller.command;

import com.bankonet.conseiller.reader.InputReader;
import com.bankonet.exception.BankonetException;
import com.bankonet.metier.ClientService;

public class OuvrirCompteCourantCommand extends IhmCommand {

	final private String CHOOSE_NAME = "Veuillez saisir votre nom.";
	final private String CHOOSE_FIRSTNAME = "Veuillez saisir votre prénom.";
	final private String CHOOSE_LOGIN = "Veuillez saisir votre login.";
	final private String COMMAND_CHOICE = "Vous avez choisi d'ouvrir un compte courant.";
	final private String ERROR_REKEYING = "Une erreur s'est produite merci de recommencer la saisie";
	
	private InputReader inputReader;
	private ClientService clientService;

	public OuvrirCompteCourantCommand(InputReader inputReader, ClientService clientService) {
		super();
		this.clientService = clientService;
		this.inputReader = inputReader;
	}

	@Override
	public int getId() {
		return 1;
	}

	@Override
	public String getLibelleMenu() {
		return "Ouvrir un compte courant";
	}

	@Override
	public void execute() {
		System.out.println(COMMAND_CHOICE);
		createClient();
	}
	
	private void createClient() {
		String name = inputReader.readLine(CHOOSE_NAME);
		String firstname = inputReader.readLine(CHOOSE_FIRSTNAME);
		String login = inputReader.readLine(CHOOSE_LOGIN);
		
		try {
			clientService.creerClient(name, firstname, login);
		} catch (BankonetException e) {
			System.err.println(ERROR_REKEYING);
			createClient();
		}
	}
}
