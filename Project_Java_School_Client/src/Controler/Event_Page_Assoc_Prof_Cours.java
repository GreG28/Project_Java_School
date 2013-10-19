package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_Assoc_Prof_Cour;

public class Event_Page_Assoc_Prof_Cours implements ActionListener {

	First_Window fenetre;
	User utilisateur;
	JComboBox<String> jlist_Professeur;
	JComboBox<String> jlist_Promotion;
	JComboBox<String> jlist_Majeur;
	JComboBox<String> jlist_Matiere;
	int[] liste_ID_Majeur;
	int[] liste_ID_Matiere;
	JTextArea label_logs;
	Page_Assoc_Prof_Cour page_Assoc_Prof_Cour;

	public Event_Page_Assoc_Prof_Cours(First_Window fenetre, User utilisateur,
			JComboBox<String> jlist_Professeur,
			JComboBox<String> jlist_Promotion, JComboBox<String> jlist_Majeur,
			JComboBox<String> jlist_Matiere, int[] liste_ID_Matiere,
			int[] liste_ID_Majeur, JTextArea label_Log,
			Page_Assoc_Prof_Cour page_Assoc_Prof_Cour) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.jlist_Professeur = jlist_Professeur;
		this.jlist_Promotion = jlist_Promotion;
		this.jlist_Majeur = jlist_Majeur;
		this.jlist_Matiere = jlist_Matiere;
		this.liste_ID_Matiere = liste_ID_Matiere;
		this.liste_ID_Majeur = liste_ID_Majeur;
		this.label_logs = label_Log;
		this.page_Assoc_Prof_Cour = page_Assoc_Prof_Cour;
	}

	public void actionPerformed(ActionEvent arg0) {

		System.out.println(" Event_Page_Assoc_Prof_Cours à IMPLEMENTER !!! ");

		if (jlist_Professeur.getSelectedItem().equals("")
				|| jlist_Promotion.getSelectedItem().equals("")
				|| jlist_Matiere.getSelectedItem().equals("")) {
			label_logs
					.setText(" Ajout de la matiere impossible champ(s) vide(s) ou non selectionnés !"
							+ label_logs.getText());

		} else {
			// le nom du professeur est connue,
			// la promotion aussi
			// et la matière aussi !
			// on doit verifier que si c'est M1 ou M2 la majeur n'est pas vide !

			if (jlist_Promotion.getSelectedItem().equals("M1")
					|| jlist_Promotion.getSelectedItem().equals("M2")) {
				if (jlist_Majeur.getSelectedItem().equals("")) {
					label_logs
							.setText(" Ajout de la matiere impossible majeure non selectionnée !"
									+ label_logs.getText());
				} else {
					// la majeur est selectionnée on verifie
					// que le professeur n'enseigne pas déjà cette matière !

					String query_teaches = " SELECT * FROM teaches WHERE TID = '"
							+ page_Assoc_Prof_Cour.getListe_ID_Teachers()[jlist_Professeur
									.getSelectedIndex()]
							+ "' AND CID = '"
							+ page_Assoc_Prof_Cour.getListe_ID_Matiere()[jlist_Matiere
									.getSelectedIndex()] + "';";
					System.out.println(" query_teaches -> " + query_teaches);
					Object[][] result = null;

					try {
						result = BDD_exchange.getThings(query_teaches);

						if (result.length != 0) {
							// on notifie qu'elle existe déjà dans le Jlabel !
							label_logs
									.setText(" Ajout de l'association impossible -> existe déjà !"
											+ label_logs.getText());
						} else {
							// on l'ajoute dans la table relationnelle !
							String query_insert_assos = " INSERT INTO teaches (CID, TID) VALUES ('"
									+ page_Assoc_Prof_Cour
											.getListe_ID_Matiere()[jlist_Matiere
											.getSelectedIndex()]
									+ "', '"
									+ page_Assoc_Prof_Cour
											.getListe_ID_Teachers()[jlist_Professeur
											.getSelectedIndex()] + "');";

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
			} else {
				// Ce n'est pas un M1 ou M2
				// on verifie que le prof n'enseigne pas déjà cette matière !

				String query_teaches = " SELECT * FROM teaches WHERE TID = '"
						+ page_Assoc_Prof_Cour.getListe_ID_Teachers()[jlist_Professeur
								.getSelectedIndex()]
						+ "' AND CID = '"
						+ page_Assoc_Prof_Cour.getListe_ID_Matiere()[jlist_Matiere
								.getSelectedIndex()] + "';";
				System.out.println(" query_teaches -> " + query_teaches);
				Object[][] result = null;

				try {
					result = BDD_exchange.getThings(query_teaches);

					if (result.length != 0) {
						// on notifie qu'elle existe déjà dans le Jlabel !
						label_logs
								.setText(" Ajout de l'association impossible -> existe déjà !"
										+ label_logs.getText());
					} else {
						// on l'ajoute dans la table relationnelle !
						String query_insert_assos = " INSERT INTO teaches (CID, TID) VALUES ('"
								+ page_Assoc_Prof_Cour.getListe_ID_Matiere()[jlist_Matiere
										.getSelectedIndex()]
								+ "', '"
								+ page_Assoc_Prof_Cour.getListe_ID_Teachers()[jlist_Professeur
										.getSelectedIndex()] + "');";

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
