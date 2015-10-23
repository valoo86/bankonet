package com.bankonet.conseiller.command;

public abstract class IhmCommand implements Comparable<IhmCommand>{
	abstract public Integer getId();
	abstract public String getLibelleMenu();
	abstract public void execute();
	
	@Override
	public int compareTo(IhmCommand o) {
		return this.getId().compareTo(o.getId());
	}
}
