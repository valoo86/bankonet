package com.bankonet.conseiller.command;

import com.bankonet.ClientException;
import com.bankonet.metier.InitService;

public class BDDInitCommand extends IhmCommand {

	final private String ERROR_REKEYING = "Une erreur s'est produite merci de recommencer la saisie";

	private InitService initService;

	public BDDInitCommand(InitService initService) {
		super();
		this.initService = initService;
	}

	@Override
	public Integer getId() {
		return 3;
	}

	@Override
	public String getLibelleMenu() {
		return "Initialiser la base de donn�es";
	}

	@Override
	public void execute() {
		try {
			initService.init();
		} catch (ClientException e) {
			System.err.println(ERROR_REKEYING);
		}
	}
}
