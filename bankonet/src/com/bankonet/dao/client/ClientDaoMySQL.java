package com.bankonet.dao.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.bankonet.ClientException;
import com.bankonet.dto.Client;

public class ClientDaoMySQL implements ClientDao {

	private static String URL = "jdbc:mysql://localhost:3306/bankonet";
	private static String USER = "root";
	private static String PASSWORD = "";

	private Map<String, Client> clientsMap;

	@Override
	public Map<String, Client> findAll() throws ClientException {
		clientsMap = new HashMap<>();

		try {
			Connection connection = connect();

			Statement statementClient = connection.createStatement();

			ResultSet resultatsClient = statementClient.executeQuery("SELECT * FROM client");

			while (resultatsClient.next()) {

				Client client = new Client();

				client.nom = resultatsClient.getString("NAME");
				client.prenom = resultatsClient.getString("FIRSTNAME");
				client.login = resultatsClient.getString("LOGIN");
				client.password = resultatsClient.getString("PASSWORD");

				Statement statementCompte = connection.createStatement();

				ResultSet resultatsCompte = statementCompte
						.executeQuery("SELECT numero FROM compte WHERE client_id = '" + client.login + "'");

				while (resultatsCompte.next()) {
					client.comptesListComptesCourantsId.add(resultatsCompte.getString("NUMERO"));
				}

				clientsMap.put(client.login, client);
			}

			connection.close();
		} catch (SQLException e) {
			throw new ClientException("Erreur lors de la fermeture de la BDD", e);
		}
		return clientsMap;
	}

	@Override
	public void save(Client client) throws ClientException {
		Connection connection = connect();
		
		try {
			connection.setAutoCommit(false);
			Statement statementClient = connection.createStatement();
			
			int nbPizzaInsere = statementClient.executeUpdate("INSERT INTO client(ID,NAME,PRICE) VALUES(1,'Regina',12.0)");

			System.out.println(nbPizzaInsere + " pizza inséré");
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new ClientException("Erreur lors de la fermeture de la BDD", e);
			}
			throw new ClientException("Erreur lors de la fermeture de la BDD", e);
		}

	}

	private Connection connect() throws ClientException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new ClientException("Erreur lors de la connexion à la BDD", e);
		}
		return connection;
	}

}
