package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_Notation_Eleve;

public class Event_Page_Notation_Eleve implements ActionListener {

	First_Window fenetre;
	User utilisateur;
	int[][] liste_SID_CID;
	double[] liste_Marks;
	Page_Notation_Eleve page_notation_eleve;

	public Event_Page_Notation_Eleve(First_Window fenetre, User utilisateur,
			Page_Notation_Eleve page_notation_eleve) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.page_notation_eleve = page_notation_eleve;
	}

	public void actionPerformed(ActionEvent e) {
		// On cherche toutes ses notes et on les affiches !

		Object[][] result = null;
		try {
			String query_exams = "SELECT CNAME, MARK FROM exams join courses WHERE exams.SID = '"
					+ utilisateur.getId() + "' AND exams.CID = courses.CID ;";

			System.out.println("query_exams -> " + query_exams);
			result = BDD_exchange.getThings(query_exams);
			
			if(result.length != 0)
			{
				page_notation_eleve.setTableau(result);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
