import java.sql.SQLException;

import Model.BDD_exchange;
import Model.Project_Client_Java;
import Model.User;
import View.First_Window;

public class Main {

	public static BDD_exchange liaison_base;
	public static First_Window fenetre_principale;
	public static User utilisateur;
	public static Project_Client_Java project_client_java;

	public static void main(String[] args) throws SQLException {

		utilisateur = new User("", "", "", "", "", "", "", "");
		fenetre_principale = new First_Window(utilisateur);
		project_client_java = new Project_Client_Java(fenetre_principale);
		liaison_base = new BDD_exchange(project_client_java); // ceci créé la
																// connection
		// à la base de donnée !

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
