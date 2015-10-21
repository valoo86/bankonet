package com.bankonet.conseiller.command;

import java.util.List;

import com.bankonet.ClientException;
import com.bankonet.common.Constantes;
import com.bankonet.conseiller.reader.InputReader;
import com.bankonet.dto.Client;
import com.bankonet.metier.ClientService;

public class RechercheClientNomCommand extends IhmCommand {

	final private String ENTER_CLIENT_NAME = "Veuillez saisir le nom du client recherché.";
	
	private ClientService clientService;
	private InputReader inputReader;

	public RechercheClientNomCommand(InputReader inputReader, ClientService clientService) {
		super();
		this.clientService = clientService;
		this.inputReader = inputReader;
	}
	
	@Override
	public Integer getId() {
		return 4;
	}

	@Override
	public String getLibelleMenu() {
		return "Rechercher une personne suivant son nom";
	}

	@Override
	public void execute() {
		String name = inputReader.readLine(ENTER_CLIENT_NAME);
		try {
			List<Client> clients = clientService.findClientByName(name);
			for (Client client : clients) {
				System.out.println(client); //même chose que client.toString()
			}
		} catch (ClientException e) {
			System.err.println(Constantes.ERROR_REKEYING);
			execute();
		}
	}
}
