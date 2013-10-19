package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;

public class Event_Page_Creer_Promotion implements ActionListener {

	User utilisateur;
	First_Window fenetre;
	JTextField champ_Nom_Promotion;
	JTextArea label_logs;

	public Event_Page_Creer_Promotion(First_Window fenetre, User utilisateur,
			JTextField champ_Nom_Promotion, JTextArea label_Logs) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.champ_Nom_Promotion = champ_Nom_Promotion;
		this.label_logs = label_Logs;

	}

	public void actionPerformed(ActionEvent arg0) {

		if (champ_Nom_Promotion.getText().length() != 0) {

			String query_promotion = "SELECT * FROM promotions WHERE PNAME = '"
					+ champ_Nom_Promotion.getText().toUpperCase() + "';";
			System.out.println(" query_promotion -> " + query_promotion);
			Object[][] result = null;

			try {
				result = BDD_exchange.getThings(query_promotion);

				if (result.length != 0) {
					label_logs
							.setText(" Ajout de le promotion impossible -> existe déjà !! "
									+ label_logs.getText());
				} else {
					String insert_promotion = " INSERT INTO promotions (PNAME) VALUES ('"
							+ champ_Nom_Promotion.getText().toUpperCase()
							+ "');";
					int nb_promotion_ajouts = 0;
					nb_promotion_ajouts = BDD_exchange
							.update_Query(insert_promotion);

					if (nb_promotion_ajouts != 0) {
						label_logs.setText(" Ajout de le promotion : "
								+ champ_Nom_Promotion.getText().toUpperCase()
								+ "     " + label_logs.getText());
						champ_Nom_Promotion.setText("");
					} else {
						System.out
								.println(" Erreur lors de la création de la promotion ");
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
