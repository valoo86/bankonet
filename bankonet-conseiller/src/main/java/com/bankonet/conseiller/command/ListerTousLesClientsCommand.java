package com.bankonet.conseiller.command;

import com.bankonet.ClientException;
import com.bankonet.common.Constantes;
import com.bankonet.dto.Client;
import com.bankonet.metier.ClientService;

public class ListerTousLesClientsCommand extends IhmCommand {

	private ClientService clientService;

	public ListerTousLesClientsCommand(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@Override
	public Integer getId() {
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
			System.err.println(Constantes.ERROR_REKEYING);
		}
	}
}
