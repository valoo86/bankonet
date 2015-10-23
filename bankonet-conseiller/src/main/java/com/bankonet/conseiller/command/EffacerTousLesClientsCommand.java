package com.bankonet.conseiller.command;

import com.bankonet.conseiller.reader.InputReader;
import com.bankonet.metier.ClientService;

public class EffacerTousLesClientsCommand extends IhmCommand {
	
	final private String CONFIRM_DELETE = "Ête-vous sûr de vouloir supprimer tous les clients ?";
	
	private ClientService clientService;
	private InputReader inputReader;
	
	public EffacerTousLesClientsCommand(ClientService clientService, InputReader inputReader) {
		super();
		this.clientService = clientService;
		this.inputReader = inputReader;
	}
	
	@Override
	public Integer getId() {
		return 8;
	}

	@Override
	public String getLibelleMenu() {
		return "Supprimer tous les clients";
	}

	@Override
	public void execute() {
		deleteAllClients();
	}

	private void deleteAllClients() {
		switch (inputReader.readLine(CONFIRM_DELETE)) {
		case "Oui":
			clientService.deleteAllClients();
			break;

		case "Non":

			break;

		default:
			deleteAllClients();
			break;
		}
	}
}
