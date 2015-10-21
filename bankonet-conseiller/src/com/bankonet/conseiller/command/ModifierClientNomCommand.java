package com.bankonet.conseiller.command;

import com.bankonet.common.Constantes;
import com.bankonet.conseiller.reader.InputReader;
import com.bankonet.dto.Client;
import com.bankonet.metier.ClientService;

public class ModifierClientNomCommand extends IhmCommand {

	final private String ENTER_CLIENT_ID = "Veuillez saisir l'identifiant du client recherché";
	final private String ENTER_NEW_CLIENT_NAME = "Veuillez saisir le nouveau nom que vous souhaitez affecter au client";
	
	private ClientService clientService;
	private InputReader inputReader;
	
	@Override
	public Integer getId() {
		return 6;
	}

	public ModifierClientNomCommand(InputReader inputReader, ClientService clientService) {
		super();
		this.clientService = clientService;
		this.inputReader = inputReader;
	}



	@Override
	public String getLibelleMenu() {
		return "Modifier le nom d’un client";
	}

	@Override
	public void execute() {
		Client client = clientService.findClientById(inputReader.readLine(ENTER_CLIENT_ID));

		if(client == null){
			System.err.println(Constantes.CLIENT_NOT_FOUND);
			return;
		}
		
		client.setNom(inputReader.readLine(ENTER_NEW_CLIENT_NAME));
		clientService.updateClient(client);
	}

}
