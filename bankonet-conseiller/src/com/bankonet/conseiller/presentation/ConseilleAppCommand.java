package com.bankonet.conseiller.presentation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.bankonet.conseiller.command.ExitCommand;
import com.bankonet.conseiller.command.IhmCommand;
import com.bankonet.conseiller.command.ListerTousLesClientsCommand;
import com.bankonet.conseiller.command.OuvrirCompteCourantCommand;
import com.bankonet.conseiller.reader.ConsoleReader;
import com.bankonet.dao.DaoFactory;
import com.bankonet.dao.DaoFactoryFile;
import com.bankonet.metier.ClientService;
import com.bankonet.metier.ClientServiceImpl;
import com.bankonet.metier.CompteService;
import com.bankonet.metier.CompteServiceImpl;

public class ConseilleAppCommand {

	private  CompteService compteService;
	private ClientService clientService;

	private List<IhmCommand> commandes;

	
	private TreeSet<IhmCommand> commandesSet = new TreeSet<>
	(Arrays.asList(
			new OuvrirCompteCourantCommand(ConsoleReader.getInstance(), clientService),
			new ExitCommand(),
			new ListerTousLesClientsCommand(clientService))
			);
	

	public ConseilleAppCommand(DaoFactory factory) {
		super();
		this.compteService = new CompteServiceImpl(factory.getCompteDao());
		this.clientService = new ClientServiceImpl(compteService, factory.getClientDao());

		commandes = Arrays.asList(
				new OuvrirCompteCourantCommand(ConsoleReader.getInstance(), clientService),
				new ExitCommand(),
				new ListerTousLesClientsCommand(clientService)
				);
	}

	public void afficherMenu() {
		while(true) {
			System.out.println("*****	APPLICATION	CONSEILLER	BANCAIRE ******");
			
			//Tri pour être sûr d'avoir le bon ordre d'affichage des commandes
			Collections.sort(commandes, new Comparator<IhmCommand>() {
		        @Override
		        public int compare(IhmCommand  commande1, IhmCommand  commande2)
		        {

		            return commande1.getId() - commande2.getId();
		        }
		    });
			
//			for (IhmCommand ihmCommand : commandes) {
//				System.out.println(ihmCommand.getId() + ". " + ihmCommand.getLibelleMenu());
//			}
			
			for (IhmCommand ihmCommand : commandesSet) {
				System.out.println(ihmCommand.getId() + ". " + ihmCommand.getLibelleMenu());
			}

			String choice = ConsoleReader.getInstance().readLine("Saisissez votre choix.");
			for (IhmCommand ihmCommand : commandes) {
				if(String.valueOf(ihmCommand.getId()).equals(choice)) {
					ihmCommand.execute();
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		DaoFactory factory = new DaoFactoryFile();

		ConseilleAppCommand conseiller = new ConseilleAppCommand(factory);
		conseiller.afficherMenu();
	}

}
