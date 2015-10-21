package com.bankonet.conseiller.command;

public class ExitCommand extends IhmCommand {

	final private String EXIT_APP = "Vous avez choisi d'arr�ter le programme courant.\nArr�t de l�application";
	
	@Override
	public int getId() {
		return 0;
	}

	@Override
	public String getLibelleMenu() {
		return "Arr�ter le programme";
	}

	@Override
	public void execute() {
		System.out.println(EXIT_APP);
		System.exit(0);
	}
	
	@Override
	public int compareTo(IhmCommand o) {
		return this.getId() - o.getId();
	}
}