package com.bankonet.conseiller.command;

import com.bankonet.common.Constantes;
import com.bankonet.conseiller.reader.InputReader;
import com.bankonet.dto.Client;
import com.bankonet.metier.ClientService;

public class EffacerClientCommand extends IhmCommand {

	final private String ENTER_CLIENT_ID = "Veuillez saisir l'identifiant du client à supprimer";
	final private String CONFIRM_DELETE = "Ête-vous sûr de vouloir supprimer le client ";

	private ClientService clientService;
	private InputReader inputReader;

	public EffacerClientCommand(ClientService clientService, InputReader inputReader) {
		super();
		this.clientService = clientService;
		this.inputReader = inputReader;
	}

	@Override
	public Integer getId() {
		return 7;
	}

	@Override
	public String getLibelleMenu() {
		return "Supprimer un client";
	}

	@Override
	public void execute() {
		Client client = clientService.findClientById(inputReader.readLine(ENTER_CLIENT_ID));

		if (client == null) {
			System.err.println(Constantes.CLIENT_NOT_FOUND);
		} else {
			deleteClient(client);
		}

	}

	private void deleteClient(Client client) {
		switch (inputReader.readLine(CONFIRM_DELETE + client.getPrenom() + " " + client.getNom() + " ?")) {
		case "Oui":
			clientService.deleteClient(client);
			break;

		case "Non":

			break;

		default:
			deleteClient(client);
			break;
		}
	}
}
