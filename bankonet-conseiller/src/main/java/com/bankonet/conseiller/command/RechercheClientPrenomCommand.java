package com.bankonet.conseiller.command;

import java.util.List;

import com.bankonet.ClientException;
import com.bankonet.common.Constantes;
import com.bankonet.conseiller.reader.InputReader;
import com.bankonet.dto.Client;
import com.bankonet.metier.ClientService;

public class RechercheClientPrenomCommand extends IhmCommand {

	final private String ENTER_CLIENT_FIRSTNAME = "Veuillez saisir le prénom du client recherché.";
	
	private ClientService clientService;
	private InputReader inputReader;
	
	public RechercheClientPrenomCommand(InputReader inputReader, ClientService clientService) {
		super();
		this.clientService = clientService;
		this.inputReader = inputReader;
	}
	
	@Override
	public Integer getId() {
		return 5;
	}

	@Override
	public String getLibelleMenu() {
		return "Rechercher une personne suivant son prénom";
	}

	@Override
	public void execute() {
		String firstname = inputReader.readLine(ENTER_CLIENT_FIRSTNAME);
		try {
			List<Client> clients = clientService.findClientByFirstname(firstname);
			for (Client client : clients) {
				System.out.println(client); //même chose que client.toString()
			}
		} catch (ClientException e) {
			System.err.println(Constantes.ERROR_REKEYING);
			execute();
		}
	}

}
