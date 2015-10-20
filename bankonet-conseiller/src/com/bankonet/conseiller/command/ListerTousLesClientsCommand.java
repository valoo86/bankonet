package com.bankonet.conseiller.command;

import com.bankonet.ClientException;
import com.bankonet.conseiller.reader.InputReader;
import com.bankonet.dto.Client;
import com.bankonet.metier.ClientService;

public class ListerTousLesClientsCommand extends IhmCommand {

	final private String ERROR_REKEYING = "Une erreur s'est produite merci de recommencer la saisie";

	private ClientService clientService;

	public ListerTousLesClientsCommand(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@Override
	public int getId() {
		return 2;
	}

	@Override
	public String getLibelleMenu() {
		return "Lister tous les clients";
	}

	@Override
	public void execute() {
		try {
			for (Client client : clientService.findAll().values()) {
				System.out.println(client.toString());
			}
		} catch (ClientException e) {
			System.err.println(ERROR_REKEYING);
		}
	}
}
