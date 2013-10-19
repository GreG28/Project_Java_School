package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_Rechercher_Professeur;

public class Event_Page_Rechercher_Professeur implements ActionListener {

	First_Window fenetre;
	User utilisateur;
	int[][] liste_SID_CID;
	double[] liste_Marks;
	Page_Rechercher_Professeur page_rechercher_professeurs;

	public Event_Page_Rechercher_Professeur(First_Window fenetre,
			User utilisateur,
			Page_Rechercher_Professeur page_rechercher_professeurs) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.page_rechercher_professeurs = page_rechercher_professeurs;
	}

	public void actionPerformed(ActionEvent e) {
		// On cherche toutes ses notes et on les affiches !

		if (this.page_rechercher_professeurs.getNom_prof_recherche_String()
				.equals("")
				|| this.page_rechercher_professeurs
						.getNom_prof_recherche_String()
						.equals("nom professeur")) {
			Object[][] result = null;
			try {
				String query_exams = "SELECT TNAME, TFNAME FROM teachers ;";

				System.out.println("query_teachers -> " + query_exams);
				result = BDD_exchange.getThings(query_exams);

				if (result.length != 0) {
					page_rechercher_professeurs.setTableau(result);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			Object[][] result = null;
			try {
				String query_exams = "SELECT TNAME, TFNAME FROM teachers WHERE TNAME = '" + this.page_rechercher_professeurs.getNom_prof_recherche_String() + "';";

				System.out.println("query_teachers -> " + query_exams);
				result = BDD_exchange.getThings(query_exams);

				if (result.length != 0) {
					page_rechercher_professeurs.setTableau(result);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
}
