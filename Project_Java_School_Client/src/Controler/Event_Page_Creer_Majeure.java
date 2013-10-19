package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;

public class Event_Page_Creer_Majeure implements ActionListener {

	User utilisateur;
	First_Window fenetre;
	JTextField champ_Nom_Majeur;
	JTextField champ_Description;
	JTextArea label_logs;

	public Event_Page_Creer_Majeure(First_Window fenetre, User utilisateur,
			JTextField champ_Nom_Majeur, JTextField champ_Descritption,
			JTextArea label_Logs) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.champ_Nom_Majeur = champ_Nom_Majeur;
		this.champ_Description = champ_Descritption;
		this.label_logs = label_Logs;

	}

	public void actionPerformed(ActionEvent arg0) {

		if (champ_Nom_Majeur.getText().length() != 0) {

			String query_majeur = "SELECT * FROM majeurs WHERE MNAME = '"
					+ champ_Nom_Majeur.getText().toUpperCase()+"' ;";
			System.out.println(" query_majeur -> " + query_majeur);
			Object[][] result = null;

			try {
				result = BDD_exchange.getThings(query_majeur);

				if (result.length != 0) {
					label_logs
							.setText(" Ajout de la majeur impossible -> existe déjà !! "
									+ label_logs.getText());
				} else {
					String query_number_MID = "SELECT MID FROM majeurs ORDER BY MID DESC LIMIT 0,1 ;"; // Il
																										// faut
																										// ordonner
																										// la
																										// liste
																										// en
																										// ne
																										// donnant
					// que le dernier et ensuite on le convertit en
					result = null;
					result = BDD_exchange.getThings(query_number_MID);

					if (result.length == 0) {

						String insert_majeur = "INSERT INTO majeurs (MID, MNAME, MDESCRITP)  VALUES ('1', '"
								+ champ_Nom_Majeur.getText().toUpperCase()
								+ "' , '"
								+ champ_Description.getText()
								+ "');";

						System.out.println(" insert_majeur -> " + insert_majeur);
						
						int nb_mise_a_jour = BDD_exchange
								.update_Query(insert_majeur);
						
						if (nb_mise_a_jour == 1) {
							label_logs.setText(" Ajout de la majeur : "
									+ champ_Nom_Majeur.getText().toUpperCase() +
									 "     " + label_logs.getText());

						}
					} else {
						int numero_tuple = Integer.parseInt(result[0][0]
								.toString()) + 1;
						
						String insert_majeur = "INSERT INTO majeurs (MID, MNAME, MDESCRITP)  VALUES ('" + numero_tuple + "', '"
								+ champ_Nom_Majeur.getText().toUpperCase()
								+ "','"
								+ champ_Description.getText()
								+ "');";

						int nb_mise_a_jour = BDD_exchange
								.update_Query(insert_majeur);

						if (nb_mise_a_jour == 1) {
							label_logs.setText(" Ajout de la majeur : "
									+ champ_Nom_Majeur.getText().toUpperCase()
									+ "              " + label_logs.getText());
							
							champ_Nom_Majeur.setText("");
							champ_Description.setText("");
						}

					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			label_logs.setText(label_logs.getText()
					+ "  Erreur l'un des champ est vide !!                  ");
		}

	}

}
