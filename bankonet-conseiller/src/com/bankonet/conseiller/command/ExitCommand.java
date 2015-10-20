package com.bankonet.conseiller.command;

public class ExitCommand extends IhmCommand {

	final private String EXIT_APP = "Vous avez choisi d'arrêter le programme courant.\nArrêt de l’application";
	
	@Override
	public int getId() {
		return 0;
	}

	@Override
	public String getLibelleMenu() {
		return "Arrêter le programme";
	}

	@Override
	public void execute() {
		System.out.println(EXIT_APP);
		System.exit(0);
	}
}
