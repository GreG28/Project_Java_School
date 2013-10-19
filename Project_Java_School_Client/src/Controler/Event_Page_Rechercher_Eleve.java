package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_Rechercher_Eleve;

public class Event_Page_Rechercher_Eleve implements ActionListener {

	private First_Window fenetre;
	private User utilisateur;
	private int[] liste_SID;
	private Page_Rechercher_Eleve page_rechercher_eleves;
	private JComboBox<String> liste_Promotions;
	private JComboBox<String> liste_majeurs;

	public Event_Page_Rechercher_Eleve(First_Window fenetre, User utilisateur,
			Page_Rechercher_Eleve page_rechercher_eleves,
			JComboBox<String> liste_Promotions,
			JComboBox<String> liste_majeurs, int[] tableau_Majeurs) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.liste_Promotions = liste_Promotions;
		this.liste_majeurs = liste_majeurs;
		this.liste_SID = tableau_Majeurs;
		this.page_rechercher_eleves = page_rechercher_eleves;
	}

	public void actionPerformed(ActionEvent e) {
		// On cherche toutes ses notes et on les affiches !

		String query_eleves = null;
		liste_SID = this.page_rechercher_eleves.getListSID();

		if (this.page_rechercher_eleves.getNom_eleves_recherche_String()
				.equals("")
				|| this.page_rechercher_eleves.getNom_eleves_recherche_String()
						.equals("nom élève".toUpperCase())) {

			System.out.println(" Pas de texte de recherche !! ");
			if (liste_Promotions.getSelectedItem().equals("")
					&& liste_majeurs.getSelectedItem().equals("")) {
				// il n'y a aucun critère !
				query_eleves = "SELECT SID,SNAME, SFNAME, SPROMO, SMAJEUR FROM students;";
			} else if (!liste_Promotions.getSelectedItem().equals("")) {
				// il y a un critère de Promotion
				if (((liste_Promotions.getSelectedItem().equals("M1") || liste_Promotions
						.getSelectedItem().equals("M2")) && !liste_majeurs
						.getSelectedItem().equals(""))
						|| (!liste_Promotions.getSelectedItem().equals("M1") && !liste_Promotions
								.getSelectedItem().equals("M2"))) {
					// il y un critere de promotion a M1 ou M2 et une majeur est
					// selectionnée !
					query_eleves = "SELECT SID, SNAME, SFNAME, SPROMO, SMAJEUR FROM students WHERE SPROMO = '"
							+ liste_Promotions.getSelectedItem() + "' ;";
				} else {
					// il n'y a pas de majeurs
					query_eleves = "SELECT SID, SNAME, SFNAME, SPROMO, MNAME FROM students NATURAL JOIN majeurs WHERE students.SMAJEUR = majeurs.MID AND SPROMO = '"
							+ liste_Promotions.getSelectedItem() + "' ;";
				}

			} else if (liste_Promotions.getSelectedItem().equals("")
					&& !liste_majeurs.getSelectedItem().equals("")) {
				// il y a un critère de majeur uniquement !
				query_eleves = "SELECT SID, SNAME, SFNAME, SPROMO, MNAME FROM students NATURAL JOIN majeurs WHERE SMAJEUR = majeurs.MID AND SMAJEUR = '"
						+ liste_SID[liste_majeurs.getSelectedIndex()] + "' ;";

			}
		} else {
			System.out.println(" Texte de recherche !! ");

			if (liste_Promotions.getSelectedItem().equals("")
					&& liste_majeurs.getSelectedItem().equals("")) {
				// il n'y a aucun critère !
				query_eleves = "SELECT SID,SNAME, SFNAME, SPROMO, SMAJEUR FROM students SNAME = '"
						+ this.page_rechercher_eleves
								.getNom_eleves_recherche_String() + "' ;";
			} else if (!liste_Promotions.getSelectedItem().equals("")) {
				// il y a un critère de Promotion
				if (((liste_Promotions.getSelectedItem().equals("M1") || liste_Promotions
						.getSelectedItem().equals("M2")) && !liste_majeurs
						.getSelectedItem().equals(""))
						|| (!liste_Promotions.getSelectedItem().equals("M1") && !liste_Promotions
								.getSelectedItem().equals("M2"))) {
					// il y un critere de promotion a M1 ou M2 et une majeur est
					// selectionnée !
					query_eleves = "SELECT SID, SNAME, SFNAME, SPROMO, SMAJEUR FROM students WHERE SPROMO = '"
							+ liste_Promotions.getSelectedItem()
							+ "' AND SNAME = '"
							+ this.page_rechercher_eleves
									.getNom_eleves_recherche_String() + "';";
				} else {
					// il n'y a pas de majeurs
					query_eleves = "SELECT SID, SNAME, SFNAME, SPROMO, MNAME FROM students NATURAL JOIN majeurs WHERE students.SMAJEUR = majeurs.MID AND SPROMO = '"
							+ liste_Promotions.getSelectedItem()
							+ "' AND SNAME = '"
							+ this.page_rechercher_eleves
									.getNom_eleves_recherche_String() + "' ;";
				}

			} else if (liste_Promotions.getSelectedItem().equals("")
					&& !liste_majeurs.getSelectedItem().equals("")) {
				// il y a un critère de majeur uniquement !
				query_eleves = "SELECT SID, SNAME, SFNAME, SPROMO, MNAME FROM students NATURAL JOIN majeurs WHERE SMAJEUR = majeurs.MID AND SMAJEUR = '"
						+ liste_SID[liste_majeurs.getSelectedIndex()]
						+ "' AND SNAME = '"
						+ this.page_rechercher_eleves
								.getNom_eleves_recherche_String() + "' ;";
			}

		}

		Object[][] result = null;
		try {

			System.out.println("query_eleves -> " + query_eleves);
			result = BDD_exchange.getThings(query_eleves);

			if (result.length != 0) {
				page_rechercher_eleves.setTableau(result);
			} else {
				page_rechercher_eleves.setTableau(new Object[0][5]);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
