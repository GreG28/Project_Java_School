package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_New_Matiere;

public class Event_Page_Creer_Matiere implements ActionListener {

	User utilisateur;
	First_Window fenetre;
	JTextField champ_Nom_Matiere;
	JComboBox<String> Jlist_Promotions;
	JComboBox<String> Jlist_Majeurs;
	JTextArea label_logs;
	int[] liste_majeure; 
	Page_New_Matiere page_New_Matiere;

	public Event_Page_Creer_Matiere(First_Window fenetre, User utilisateur,
			JTextField champ_Nom_Matiere, JComboBox<String> Jlist_Promotions,
			JComboBox<String> Jlist_Majeurs, JTextArea label_Logs, Page_New_Matiere page_New_Matiere) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.champ_Nom_Matiere = champ_Nom_Matiere;
		this.Jlist_Majeurs = Jlist_Majeurs;
		this.Jlist_Promotions = Jlist_Promotions;
		this.label_logs = label_Logs;
		this.page_New_Matiere = page_New_Matiere;

	}

	public void actionPerformed(ActionEvent arg0) {

		if (champ_Nom_Matiere.getText().length() == 0
				|| Jlist_Promotions.getSelectedItem().equals("")) {
			label_logs
					.setText(" Ajout de la matiere impossible champ vide ou promotion non selectionnée !"
							+ label_logs.getText());

		} else {
			if (Jlist_Promotions.getSelectedItem().equals("M1")
					|| Jlist_Promotions.getSelectedItem().equals("M2")) {
				if (Jlist_Majeurs.getSelectedItem().equals("")) {
					label_logs
							.setText(" Ajout de la matiere impossible majeure non selectionnée !"
									+ label_logs.getText());
				} else {
					// le nom de la matiere est connue et la promotion
					// selectionnée est M1 ou M2
					// maintenant on verifie qu'elle n'existe pas déjà dans
					// cette promotion et Majeur
					String query_matiere = "SELECT * FROM courses WHERE CNAME = '"
							+ champ_Nom_Matiere.getText().toUpperCase()
							+ "' AND CPROMO ='"
							+ Jlist_Promotions.getSelectedItem()
							+ "' AND CMAJEUR ='"
							+ page_New_Matiere.getListe_ID_Majeurs()[Jlist_Majeurs.getSelectedIndex()] + "' ;";
					System.out.println(" query_matiere -> " + query_matiere);
					Object[][] result = null;

					try {
						result = BDD_exchange.getThings(query_matiere);

						if (result.length != 0) {
							// on notifie qu'elle existe déjà dans le Jlabel !
							label_logs
									.setText(" Ajout de la matiere impossible -> existe déjà !"
											+ label_logs.getText());
						} else {
							// On insert cette nouvelle matiere dans la table
							System.out
									.println("On insert cette nouvelle matiere dans la table M1 et M2");

							/* DEBUT */

							String query_number_CID = "SELECT CID FROM courses ORDER BY CID DESC LIMIT 0,1 ;"; // Il
							// faut
							// ordonner
							// la
							// liste
							// en
							// ne
							// donnant
							// que le dernier et ensuite on le convertit en
							result = null;
							result = BDD_exchange.getThings(query_number_CID);

							if (result.length == 0) {

								String insert_matiere = "INSERT INTO courses (CID, CNAME, CPROMO, CMAJEUR)  VALUES ('1', '"
										+ champ_Nom_Matiere.getText()
												.toUpperCase()
										+ "' , '"
										+ Jlist_Promotions.getSelectedItem()
										+ "' , '"
										+ page_New_Matiere.getListe_ID_Majeurs()[Jlist_Majeurs.getSelectedIndex()]
										+ "');";

								System.out.println(" insert_matiere -> "
										+ insert_matiere);

								int nb_mise_a_jour = BDD_exchange
										.update_Query(insert_matiere);

								if (nb_mise_a_jour == 1) {
									label_logs.setText(" Ajout de la majeur : "
											+ champ_Nom_Matiere.getText()
													.toUpperCase() + "     "
											+ label_logs.getText());

									champ_Nom_Matiere.setText("");
								}
							} else {
								int numero_tuple = Integer
										.parseInt(result[0][0].toString()) + 1;
								
								System.out.println(" Jlist_Majeurs.getSelectedIndex() -> " + Jlist_Majeurs.getSelectedIndex());
								System.out.println(" liste_ID_Majeurs ->" +  page_New_Matiere.getListe_ID_Majeurs().length );

								String insert_matiere = "INSERT INTO courses (CID, CNAME, CPROMO, CMAJEUR)  VALUES ('"
										+ numero_tuple
										+ "', '"
										+ champ_Nom_Matiere.getText()
												.toUpperCase()
										+ "' , '"
										+ Jlist_Promotions.getSelectedItem()
										+ "', '"
										+ page_New_Matiere.getListe_ID_Majeurs()[Jlist_Majeurs.getSelectedIndex()]
										+ "');";

								System.out.println(" insert_matiere -> "
										+ insert_matiere);

								int nb_mise_a_jour = BDD_exchange
										.update_Query(insert_matiere);

								if (nb_mise_a_jour == 1) {
									label_logs.setText(" Ajout de la majeur : "
											+ champ_Nom_Matiere.getText()
													.toUpperCase() + "     "
											+ label_logs.getText());

									champ_Nom_Matiere.setText("");

								}

							}

							/* FIN ESSAI */
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} else {
				// la promotion n'est pas M1 ou M2
				String query_matiere = "SELECT * FROM courses WHERE CNAME = '"
						+ champ_Nom_Matiere.getText().toUpperCase()
						+ "' AND CPROMO ='"
						+ Jlist_Promotions.getSelectedItem() + "' ;";
				System.out.println(" query_matiere -> " + query_matiere);
				Object[][] result = null;

				try {
					result = BDD_exchange.getThings(query_matiere);

					if (result.length != 0) {
						// on notifie qu'elle existe déjà dans le Jlabel !
						label_logs
								.setText(" Ajout de la matiere impossible -> existe déjà !"
										+ label_logs.getText());
					} else {
						// On insert cette nouvelle matiere dans la table
						System.out
								.println("On insert cette nouvelle matiere dans la table non M1 et M2 ");

						/* Debut */

						String query_number_CID = "SELECT CID FROM courses ORDER BY CID DESC LIMIT 0,1 ;"; // Il
						// faut
						// ordonner
						// la
						// liste
						// en
						// ne
						// donnant
						// que le dernier et ensuite on le convertit en
						result = null;
						result = BDD_exchange.getThings(query_number_CID);

						if (result.length == 0) {

							String insert_matiere = "INSERT INTO courses (CID, CNAME, CPROMO)  VALUES ('1', '"
									+ champ_Nom_Matiere.getText().toUpperCase()
									+ "' , '"
									+ Jlist_Promotions.getSelectedItem()
									+ "');";

							System.out.println(" insert_matiere -> "
									+ insert_matiere);

							int nb_mise_a_jour = BDD_exchange
									.update_Query(insert_matiere);

							if (nb_mise_a_jour == 1) {
								label_logs.setText(" Ajout de la majeur : "
										+ champ_Nom_Matiere.getText()
												.toUpperCase() + "     "
										+ label_logs.getText());

								champ_Nom_Matiere.setText("");
							}
						} else {
							int numero_tuple = Integer.parseInt(result[0][0]
									.toString()) + 1;

							String insert_matiere = "INSERT INTO courses (CID, CNAME, CPROMO)  VALUES ('"
									+ numero_tuple
									+ "', '"
									+ champ_Nom_Matiere.getText().toUpperCase()
									+ "' , '"
									+ Jlist_Promotions.getSelectedItem()
									+ "');";

							System.out.println(" insert_matiere -> "
									+ insert_matiere);

							int nb_mise_a_jour = BDD_exchange
									.update_Query(insert_matiere);

							if (nb_mise_a_jour == 1) {
								label_logs.setText(" Ajout de la majeur : "
										+ champ_Nom_Matiere.getText()
												.toUpperCase() + "     "
										+ label_logs.getText());

								champ_Nom_Matiere.setText("");

							}

						}

						/* fin */
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
