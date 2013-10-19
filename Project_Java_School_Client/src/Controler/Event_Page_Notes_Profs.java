package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_Notes_Prof;

public class Event_Page_Notes_Profs implements ActionListener {

	First_Window fenetre;
	User utilisateur;
	int[][] liste_SID_CID;
	double[] liste_Marks;
	Page_Notes_Prof page_notes_prof;

	public Event_Page_Notes_Profs(First_Window fenetre, User utilisateur,
			Page_Notes_Prof page_notes_prof) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.page_notes_prof = page_notes_prof;
	}

	public void actionPerformed(ActionEvent e) {
		// On cherche toutes ses notes et on les affiches !

		Object[][] result = null;
		try {
			String query_exams = "SELECT CNAME, SNAME, SFNAME, SPROMO, SMAJEUR, MARK FROM (SELECT exams.CID, CNAME, SNAME, SFNAME, SPROMO, SMAJEUR, TID, MARK FROM exams JOIN courses, students, teaches WHERE exams.SID = students. SID AND exams.CID = courses.CID AND exams.CID = teaches.CID) AS all_notes WHERE TID = '"
					+ utilisateur.getId() + "' ORDER BY SPROMO ;";

			System.out.println("query_exams -> " + query_exams);
			result = BDD_exchange.getThings(query_exams);

			if (result.length != 0) {
				page_notes_prof.setTableau(result);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
