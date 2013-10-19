package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.BDD_exchange;
import Model.Codage;
import Model.User;
import View.First_Window;

public class Event_Page_Creer_Prof implements ActionListener {

	User utilisateur;
	First_Window fenetre;
	JTextField champ_Nom_Prof;
	JTextField champ_Prenom_Prof;
	JTextArea label_logs;

	public Event_Page_Creer_Prof(First_Window fenetre, User utilisateur,
			JTextField champ_Nom_Prof, JTextField champ_Prenom_Prof,
			JTextArea label_Logs) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.champ_Nom_Prof = champ_Nom_Prof;
		this.champ_Prenom_Prof = champ_Prenom_Prof;
		this.label_logs = label_Logs;

	}

	public void actionPerformed(ActionEvent arg0) {

		if (champ_Nom_Prof.getText().length() != 0
				&& champ_Prenom_Prof.getText().length() != 0) {

			String query_login = "SELECT * FROM teachers WHERE TNAME = '"
					+ champ_Nom_Prof.getText().toUpperCase()
					+ "' AND TFNAME = '"
					+ champ_Prenom_Prof.getText().toUpperCase() + "' ;";
			System.out.println(" query_login -> " + query_login);
			Object[][] result = null;

			try {
				result = BDD_exchange.getThings(query_login);

				if (result.length != 0) {
					label_logs
							.setText(" Ajout du professeur impossible -> existe déjà !! "
									+ label_logs.getText());
				} else {
					System.out.println("result.length -> " + result.length);
					String query_number_TID = "SELECT TID FROM teachers ORDER BY TID DESC LIMIT 0,1 ;"; // Il
																										// faut
																										// ordonner
																										// la
																										// liste
																										// en
																										// ne
																										// donnant
					// que le dernier et ensuite on le convertit en
					result = null;
					result = BDD_exchange.getThings(query_number_TID);

					if (result.length == 0) {

						String insert_teacher = "INSERT INTO teachers  VALUES ('1', '"
								+ champ_Nom_Prof.getText().toUpperCase()
								+ "','"
								+ champ_Prenom_Prof.getText().toUpperCase()
								+ "');";

						int nb_mise_a_jour = BDD_exchange
								.update_Query(insert_teacher);
						
						if (nb_mise_a_jour == 1) {
							
							String insert_teacher_Connection = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
									+ champ_Nom_Prof.getText().toLowerCase()
									+ champ_Prenom_Prof.getText().toLowerCase()
									+ "' , '"
									+ Codage.MD5(champ_Nom_Prof.getText()
											.toLowerCase()
											+ champ_Prenom_Prof.getText()
													.toLowerCase())
									+ "', '1' , 'TEACHER', '0');";
							
							int cpt = 0;

							do {
								String insert_teacher_Connection_try;
								
								if ( cpt == 0 )
								{
									insert_teacher_Connection_try = insert_teacher_Connection;
								}
								else
								{
									insert_teacher_Connection_try = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
											+ champ_Nom_Prof.getText().toLowerCase()
											+ champ_Prenom_Prof.getText().toLowerCase()
											+ "_" + cpt + "' , '"
											+ Codage.MD5(champ_Nom_Prof.getText()
													.toLowerCase()
													+ champ_Prenom_Prof.getText()
															.toLowerCase()+"_" + cpt)
											+ "', '1' , 'TEACHER', '0');";
									
								}
								
								nb_mise_a_jour = 0;
								nb_mise_a_jour = BDD_exchange
										.update_Query(insert_teacher_Connection_try);

								System.out.println( " nb_mise_a_jour -> " + nb_mise_a_jour);
								if(nb_mise_a_jour != 0)
								{
									label_logs.setText(" Ajout du professeur : "
											+ champ_Prenom_Prof.getText().toUpperCase()
											+ "  "
											+ champ_Nom_Prof.getText().toUpperCase() + " Login -> "
											+ champ_Prenom_Prof.getText().toLowerCase()
											+ "_" + cpt
											+ "" + label_logs.getText());
								}
								
								cpt = cpt+1;
							} while (nb_mise_a_jour == 0);
						}
					} else {
						int numero_tuple = Integer.parseInt(result[0][0]
								.toString()) + 1;
						String insert_teacher = "INSERT INTO teachers  VALUES ('"
								+ numero_tuple
								+ "', '"
								+ champ_Prenom_Prof.getText().toUpperCase()
								+ "','"
								+ champ_Nom_Prof.getText().toUpperCase()
								+ "');";

						int nb_mise_a_jour = BDD_exchange
								.update_Query(insert_teacher);

						if (nb_mise_a_jour == 1) {

							String insert_teacher_Connection = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
									+ champ_Nom_Prof.getText().toLowerCase()
									+ champ_Prenom_Prof.getText().toLowerCase()
									+ "' , '"
									+ Codage.MD5(champ_Nom_Prof.getText()
											.toLowerCase()
											+ champ_Prenom_Prof.getText()
													.toLowerCase())
									+ "', '"
									+ numero_tuple + "' , 'TEACHER', '0');";
							
							int cpt = 0;

							do {
								String insert_teacher_Connection_try;
								
								if ( cpt == 0 )
								{
									insert_teacher_Connection_try = insert_teacher_Connection;
								}
								else
								{
									insert_teacher_Connection_try = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
											+ champ_Nom_Prof.getText().toLowerCase()
											+ champ_Prenom_Prof.getText().toLowerCase()
											+ "_" + cpt + "' , '"
											+ Codage.MD5(champ_Nom_Prof.getText()
													.toLowerCase()
													+ champ_Prenom_Prof.getText()
															.toLowerCase()+"_" + cpt)
											+ "', '"
											+ numero_tuple + "' , 'TEACHER', '0');";
									
								}
								
								nb_mise_a_jour = 0;
								nb_mise_a_jour = BDD_exchange
										.update_Query(insert_teacher_Connection_try);

								System.out.println( " nb_mise_a_jour -> " + nb_mise_a_jour);
								if(nb_mise_a_jour != 0)
								{
									label_logs.setText(" Ajout du professeur : "
											+ champ_Prenom_Prof.getText().toUpperCase()
											+ "  "
											+ champ_Nom_Prof.getText().toUpperCase() + " Login -> "
											+ champ_Prenom_Prof.getText().toLowerCase()
											+ "_" + cpt
											+ "" + label_logs.getText());
								}
								
								cpt = cpt+1;
							} while (nb_mise_a_jour == 0);
							
							champ_Prenom_Prof.setText("");
							champ_Nom_Prof.setText("");
						}

					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			label_logs.setText(label_logs.getText()
					+ "  Erreur l'un des champ est vide !!                  ");
		}

	}

}
