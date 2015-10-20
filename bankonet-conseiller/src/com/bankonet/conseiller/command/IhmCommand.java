package com.bankonet.conseiller.command;

public abstract class IhmCommand implements Comparable<IhmCommand>{
	abstract public int getId();
	abstract public String getLibelleMenu();
	abstract public void execute();
}
