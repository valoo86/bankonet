package com.bankonet_conseiller.presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.bankonet.ClientException;
import com.bankonet.dao.DaoFactory;
import com.bankonet.dao.DaoFactoryFile;
import com.bankonet.dao.DaoFactoryMySQL;
import com.bankonet.dto.Client;
import com.bankonet.exception.BankonetException;
import com.bankonet.metier.ClientService;
import com.bankonet.metier.ClientServiceImpl;
import com.bankonet.metier.CompteService;
import com.bankonet.metier.CompteServiceImpl;

public class ConseillerApp {

	private DaoFactory factory;
	private  CompteService compteService;
	private ClientService clientService;

	private Scanner sc = new Scanner(System.in);

	private Map<String, String> consoleMessagesMap = new HashMap<>();

	private enum MessageType {
		CONSOLE,
		ERROR;
	}

	private enum ActionType {
		MENU_CHOICE,
		ERROR;
	}

	public ConseillerApp(DaoFactory factory) {
		this.factory = factory;
		this.compteService = new CompteServiceImpl(factory.getCompteDao());
		this.clientService = new ClientServiceImpl(compteService, factory.getClientDao());
		this.consoleMessagesMap.put("MENU_MESSAGE", "*****	APPLICATION	CONSEILLER	BANCAIRE ******\n0.	Arrêter	le programme\n1.	Ouvrir un compte courant\n2.	Lister tous les	clients\n3.	Ajouter un compte courant\nVeuillez choisir une action.");
		this.consoleMessagesMap.put("QUESTION_CLIENT", "Choisissez un client");
		this.consoleMessagesMap.put("QUESTION_NAME", "Veuillez saisir votre nom.");
		this.consoleMessagesMap.put("QUESTION_FIRSTNAME", "Veuillez saisir votre prénom.");
		this.consoleMessagesMap.put("QUESTION_LOGIN", "Veuillez saisir votre login.");
		this.consoleMessagesMap.put("ANSWER_EXIT_APP", "Vous avez choisi d'arrêter le programme courant.\nArrêt de l’application");
		this.consoleMessagesMap.put("ANSWER_OPEN_ACCOUNT", "Vous avez choisi d'ouvrir un compte courant.");
		this.consoleMessagesMap.put("ANSWER_LIST_CLIENTS", "Vous avez choisi de lister tous les clients.");
		this.consoleMessagesMap.put("ANSWER_OPEN_CURRENT_ACCOUNT", "Vous avez choisi d'ajouter un compte courant.");
		this.consoleMessagesMap.put("ERROR_UNKNOWN_TEXT", "Vous n'avez pas saisi une option valide !\nVeuillez resaisir votre réponse.");
		this.consoleMessagesMap.put("ERROR_REKEYING", "Une erreur s'est produite merci de recommencer la saisie");
	}

	public void start() {

		//		try {
		//			
		//			clientService.findAll();
		//			
		//
		//		} catch (ClientException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		// logique IHM
		handleMenuChoice(askEnterTextForQuestion(consoleMessagesMap.get("MENU_MESSAGE"), MessageType.CONSOLE));
	}

	private void handleMenuChoice(String choice) {
		switch (choice) {
		case "0":
			exitApp();
			break;

		case "1":
			createClient();
			break;

		case "2":
			displayClients();
			break;
		case "3":
			createACurrentAccount();
			break;

		default:
			handleMenuChoice(askEnterTextForQuestion(consoleMessagesMap.get("ERROR_UNKNOWN_TEXT"), MessageType.ERROR));
			break;
		}
	}

	private void exitApp() {
		displayText(consoleMessagesMap.get("ANSWER_EXIT_APP"), MessageType.CONSOLE);
		System.exit(0);
	}

	private void createClient() {
		String name, firstname, login;

		displayText(consoleMessagesMap.get("ANSWER_OPEN_ACCOUNT"), MessageType.CONSOLE);
		name = askEnterTextForQuestion(consoleMessagesMap.get("QUESTION_NAME"), MessageType.CONSOLE);
		firstname = askEnterTextForQuestion(consoleMessagesMap.get("QUESTION_FIRSTNAME"), MessageType.CONSOLE);
		login = askEnterTextForQuestion(consoleMessagesMap.get("QUESTION_LOGIN"), MessageType.CONSOLE);
		try {
			clientService.creerClient(name, firstname, login);
			handleMenuChoice(askEnterTextForQuestion(consoleMessagesMap.get("MENU_MESSAGE"), MessageType.CONSOLE));
		} catch (BankonetException e) {
			displayText(consoleMessagesMap.get("ERROR_REKEYING"), MessageType.ERROR);
			createClient();
		}
	}

	private void displayClients() {
		try {
			for (Client client : clientService.findAll().values()) {
				displayText(client.toString(), MessageType.CONSOLE);
			}
			handleMenuChoice(askEnterTextForQuestion(consoleMessagesMap.get("MENU_MESSAGE"), MessageType.CONSOLE));
		} catch (ClientException e) {
			handleMenuChoice(askEnterTextForQuestion(consoleMessagesMap.get("ERROR_REKEYING"), MessageType.ERROR));
		}
	}

	private void createACurrentAccount() {
		try {
			Map<String,Client> clientsMap = clientService.findAll();
			for (Client client : clientsMap.values()) {
				displayText(client.toString(), MessageType.CONSOLE);
			}
		} catch (ClientException e) {
			handleMenuChoice(askEnterTextForQuestion(consoleMessagesMap.get("ERROR_REKEYING"), MessageType.ERROR));
		}
	}

	private Client chooseAClient() {
		return null;
	}

	private String askEnterTextForQuestion(String question, MessageType messageType) {
		String answer;

		displayText(question, messageType);
		answer = sc.nextLine();
		return answer;
	}

	private void displayText(String text, MessageType messageType) {
		switch (messageType) {
		case CONSOLE:
			System.out.println(text);
			break;

		case ERROR:
			System.err.println(text);
			break;

		default:
			System.out.println(text);
			break;
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		//DaoFactory factory = new DaoFactoryFile();
		DaoFactory factory = new DaoFactoryMySQL();
		Class.forName("com.mysql.jdbc.Driver");

		ConseillerApp conseiller = new ConseillerApp(factory);
		conseiller.start();
	}

}
