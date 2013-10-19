package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_Notation_Prof;

public class Event_Page_Notation_Prof implements ActionListener {

	First_Window fenetre;
	User utilisateur;
	int[][] liste_SID_CID;
	double[] liste_Marks;
	Page_Notation_Prof page_notation_prof;

	public Event_Page_Notation_Prof(First_Window fenetre, User utilisateur,
			Page_Notation_Prof page_notation_prof) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.page_notation_prof = page_notation_prof;
	}

	public void actionPerformed(ActionEvent arg0) {
		String query_marks;
		this.liste_Marks = page_notation_prof.get_Marks();
		this.liste_SID_CID = page_notation_prof.get_liste_SID_CID();

		for (int cpt = 0; cpt < liste_Marks.length; cpt = cpt + 1) {
			query_marks = " SELECT * FROM exams WHERE CID = '"
					+ liste_SID_CID[cpt][1] + "' AND SID = '"
					+ liste_SID_CID[cpt][0] + "' ;";
			System.out.println("query_marks -> " + query_marks);

			Object[][] result = null;
			try {
				result = BDD_exchange.getThings(query_marks);

				if (result.length != 0) {
					// on l'update !
					String query_insert = "UPDATE exams SET MARK = '"+liste_Marks[cpt]+"' WHERE SID ='" + liste_SID_CID[cpt][0] + "' AND CID ='" + liste_SID_CID[cpt][1] + "';";
					System.out.println("query_insert (update) -> " + query_insert);
					BDD_exchange.update_Query(query_insert);
				} else {
					// on l'insert !
					String query_insert = "INSERT INTO exams (SID, CID, MARK) VALUES ('"
							+ liste_SID_CID[cpt][0]
							+ "', '"
							+ liste_SID_CID[cpt][1]
							+ "' , '"
							+ liste_Marks[cpt] + "');";
					System.out.println("query_insert -> " + query_insert);
					BDD_exchange.update_Query(query_insert);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
