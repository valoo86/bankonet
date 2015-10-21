package com.bankonet.conseiller.command;

import com.bankonet.ClientException;
import com.bankonet.common.Constantes;
import com.bankonet.metier.InitService;

public class BDDInitCommand extends IhmCommand {

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
		return "Initialiser la base de données";
	}

	@Override
	public void execute() {
		try {
			initService.init();
		} catch (ClientException e) {
			System.err.println(Constantes.ERROR_REKEYING);
		}
	}
}
