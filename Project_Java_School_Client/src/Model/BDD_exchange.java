package Model;

import java.sql.SQLException;

public class BDD_exchange {
	// c'est grace à cette classe que l'on va traiter
	// avec la base de donnée !
	static Project_Client_Java project_client;

	public BDD_exchange(Project_Client_Java project_client_java) {
		
		BDD_exchange.project_client = project_client_java;

		Thread t = new Thread(project_client);
		t.start();

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static Object[][] getThings(String query) throws SQLException {

		project_client
				.envoyer_requete_Select(query);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		return project_client.recuperer_Object();
	}

	public static int update_Query(String query) {
		project_client.envoyer_requete_Update(query);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return project_client.recuperer_Int();
	}

}
