package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_Assoc_Student_Cour;

public class Event_Page_Assoc_Student_Cours implements ActionListener {

	First_Window fenetre;
	User utilisateur;
	JComboBox<String> jlist_Students;
	JComboBox<String> jlist_Promotion;
	JComboBox<String> jlist_Majeur;
	JComboBox<String> jlist_Matiere;
	JTextArea label_logs;
	Page_Assoc_Student_Cour page_Assoc_Student_Cour;

	public Event_Page_Assoc_Student_Cours(First_Window fenetre,
			User utilisateur, JComboBox<String> jlist_Students,
			JComboBox<String> jlist_Promotion, JComboBox<String> jlist_Majeur,
			JComboBox<String> jlist_Matiere, JTextArea label_Log,
			Page_Assoc_Student_Cour page_Assoc_Student_Cour) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.jlist_Students = jlist_Students;
		this.jlist_Promotion = jlist_Promotion;
		this.jlist_Majeur = jlist_Majeur;
		this.jlist_Matiere = jlist_Matiere;
		this.label_logs = label_Log;
		this.page_Assoc_Student_Cour = page_Assoc_Student_Cour;
	}

	public void actionPerformed(ActionEvent arg0) {

		if (jlist_Students.getSelectedItem().equals("")
				|| jlist_Promotion.getSelectedItem().equals("")
				|| jlist_Matiere.getSelectedItem().equals("")) {
			label_logs
					.setText(" Ajout de la matiere impossible champ(s) vide(s) ou non selectionnés !"
							+ label_logs.getText());

		} else {

			if ((jlist_Promotion.getSelectedItem().equals("M1") || jlist_Promotion
					.getSelectedItem().equals("M2"))
					&& (jlist_Majeur.getSelectedItem().equals(""))) {
				label_logs
						.setText(" Ajout de la matiere impossible majeure non selectionnée !"
								+ label_logs.getText());
			} else {
				// la majeur est selectionnée on verifie
				// que l'étudiant n'enseigne pas déjà cette matière !

				String query_learns = " SELECT * FROM learns WHERE STUDENT = '"
						+ page_Assoc_Student_Cour.getListe_ID_Students()[jlist_Students
								.getSelectedIndex()]
						+ "' AND COURSE = '"
						+ page_Assoc_Student_Cour.getListe_ID_Matiere()[jlist_Matiere
								.getSelectedIndex()] + "';";
				System.out.println(" query_learns -> " + query_learns);
				Object[][] result = null;

				try {
					result = BDD_exchange.getThings(query_learns);

					if (result.length != 0) {
						// on notifie que cette combinaison existe déjà dans le
						// Jlabel !
						label_logs
								.setText(" Ajout de l'association impossible -> existe déjà !"
										+ label_logs.getText());
					} else {
						// on l'ajoute dans la table relationnelle !
						String query_insert_assos = " INSERT INTO learns (STUDENT, COURSE) VALUES ('"
								+ page_Assoc_Student_Cour
										.getListe_ID_Students()[jlist_Students
										.getSelectedIndex()]
								+ "', '"
								+ page_Assoc_Student_Cour.getListe_ID_Matiere()[jlist_Matiere
										.getSelectedIndex()] + "');";

						System.out.println(" query_insert_assos -> "
								+ query_insert_assos);

						int nb_updates = 0;

						nb_updates = BDD_exchange
								.update_Query(query_insert_assos);
						if (nb_updates != 0) {
							label_logs
									.setText(" Ajout de l'association faite !        "
											+ label_logs.getText());
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}
}
