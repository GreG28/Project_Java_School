package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.BDD_exchange;
import Model.Codage;
import Model.User;
import View.First_Window;
import View.Page_New_Student;

public class Event_Page_Creer_Student implements ActionListener {

	User utilisateur;
	First_Window fenetre;
	JTextField champ_nom;
	JTextField champ_prenom;
	JComboBox<String> Jlist_Promotions;
	JComboBox<String> Jlist_Majeurs;
	JTextArea label_logs;
	int[] liste_majeure;
	Page_New_Student page_New_Student;

	public Event_Page_Creer_Student(First_Window fenetre, User utilisateur,
			JTextField champ_nom, JTextField champ_prenom,
			JComboBox<String> Jlist_Promotions,
			JComboBox<String> Jlist_Majeurs, JTextArea label_Logs,
			Page_New_Student page_New_student) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.Jlist_Majeurs = Jlist_Majeurs;
		this.champ_nom = champ_nom;
		this.champ_prenom = champ_prenom;
		this.Jlist_Promotions = Jlist_Promotions;
		this.label_logs = label_Logs;
		this.page_New_Student = page_New_student;

	}

	public void actionPerformed(ActionEvent arg0) {

		System.out.println("");
		System.out.println("");
		System.out.println("");

		if (champ_nom.getText().equals("")
				|| champ_prenom.getText().equals("")
				|| Jlist_Promotions.getSelectedItem().equals("")
				|| (Jlist_Promotions.getSelectedItem().equals("M1") && Jlist_Majeurs
						.getSelectedItem().equals(""))
				|| (Jlist_Promotions.getSelectedItem().equals("M2") && Jlist_Majeurs
						.getSelectedItem().equals(""))) {
			label_logs
					.setText(" Ajout de l'étudiant impossible champ vide ou promotion et/ou majeur !"
							+ label_logs.getText());
		} else {

			if (Jlist_Promotions.getSelectedItem().equals("M1")
					|| Jlist_Promotions.getSelectedItem().equals("M2")) {
				// c'est un M1 ou M2
				// on test s'il n'existe pas déjà !

				System.out.println(" C'est un M1 ou M2 ! '"
						+ Jlist_Promotions.getSelectedItem()+"'");

				/* DEBUT */

				String query_etudiant = "SELECT * FROM students WHERE SNAME = '"
						+ champ_nom.getText().toUpperCase()
						+ "' AND SFNAME ='"
						+ champ_prenom.getText()
						+ "' AND SPROMO ='"
						+ Jlist_Promotions.getSelectedItem()
						+ "' AND SMAJEUR='"
						+ page_New_Student.getListe_ID_Majeurs()[Jlist_Majeurs
								.getSelectedIndex()] + "' ;";
				System.out.println(" query_etudiants -> " + query_etudiant);

				Object[][] result = null;

				try {
					result = BDD_exchange.getThings(query_etudiant);

					if (result.length != 0) {
						// on notifie qu'elle existe déjà dans le Jlabel !
						label_logs
								.setText(" Ajout de l'étudiant impossible -> existe déjà !"
										+ label_logs.getText());
					} else {
						// On insert cette nouvel étudiant dans la table
						System.out
								.println("On insert ce nouvel étudiant dans la table M1 et M2");

						String query_number_SID = "SELECT SID FROM students ORDER BY SID DESC LIMIT 0,1 ;"; // Il
						// faut
						// ordonner
						// la
						// liste
						// en
						// ne
						// donnant
						// que le dernier et ensuite on le convertit en
						result = null;
						result = BDD_exchange.getThings(query_number_SID);

						if (result.length == 0) {

							String insert_etudiant = "INSERT INTO students (SID, SNAME, SFNAME, SPROMO, SMAJEUR)  VALUES ('1', '"
									+ champ_nom.getText().toUpperCase()
									+ "' , '"
									+ champ_prenom.getText().toUpperCase()
									+ "' , '"
									+ Jlist_Promotions.getSelectedItem()
									+ "' , '"
									+ page_New_Student.getListe_ID_Majeurs()[Jlist_Majeurs
											.getSelectedIndex()] + "');";

							System.out.println(" insert_etudiant -> "
									+ insert_etudiant);
							int nb_mise_a_jour = BDD_exchange
									.update_Query(insert_etudiant);

							if (nb_mise_a_jour == 1) {
								// on crer son login !
								String insert_student_Connection = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
										+ champ_nom.getText().toLowerCase()
										+ champ_prenom.getText().toLowerCase()
										+ "' , '"
										+ Codage.MD5(champ_nom.getText()
												.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase())
										+ "', '1' , 'STUDENT', '0');";

								int cpt = 0;

								do {
									String insert_student_Connection_try;

									if (cpt == 0) {
										insert_student_Connection_try = insert_student_Connection;
									} else {
										insert_student_Connection_try = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
												+ champ_nom.getText()
														.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase()
												+ "_"
												+ cpt
												+ "' , '"
												+ Codage.MD5(champ_nom
														.getText()
														.toLowerCase()
														+ champ_prenom
																.getText()
																.toLowerCase()
														+ "_" + cpt)
												+ "', '1' , 'STUDENT', '0');";

									}

									nb_mise_a_jour = 0;
									nb_mise_a_jour = BDD_exchange
											.update_Query(insert_student_Connection_try);

									System.out.println(" nb_mise_a_jour -> "
											+ nb_mise_a_jour);
									if (nb_mise_a_jour != 0) {
										label_logs
												.setText(" Ajout de l'étudiant : "
														+ champ_nom.getText()
																.toUpperCase()
														+ " "
														+ champ_prenom
																.getText()
																.toUpperCase()
														+ "  au login ->" + champ_nom.getText()
														.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase()
												+ "_"
												+ cpt
														+ label_logs.getText());

										champ_nom.setText("");
										champ_prenom.setText("");
									}

									cpt = cpt + 1;
								} while (nb_mise_a_jour == 0);
							}
						} else {
							// toujours M1 ou M2
							// Il y a déjà un élève dans la table !
							int numero_tuple = Integer.parseInt(result[0][0]
									.toString()) + 1;

							String insert_etudiant = "INSERT INTO students (SID, SNAME, SFNAME, SPROMO, SMAJEUR)  VALUES ('"
									+ numero_tuple
									+ "', '"
									+ champ_nom.getText().toUpperCase()
									+ "' , '"
									+ champ_prenom.getText().toUpperCase()
									+ "' , '"
									+ Jlist_Promotions.getSelectedItem()
									+ "' , '"
									+ page_New_Student.getListe_ID_Majeurs()[Jlist_Majeurs
											.getSelectedIndex()] + "');";

							System.out.println(" insert_etudiant -> "
									+ insert_etudiant);

							int nb_mise_a_jour = BDD_exchange
									.update_Query(insert_etudiant);

							if (nb_mise_a_jour == 1) {
								// on crer son login !
								String insert_student_Connection = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
										+ champ_nom.getText().toLowerCase()
										+ champ_prenom.getText().toLowerCase()
										+ "' , '"
										+ Codage.MD5(champ_nom.getText()
												.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase())
										+ "', '"
										+ numero_tuple
										+ "' , 'STUDENT', '0');";

								int cpt = 0;

								do {
									String insert_student_Connection_try;

									if (cpt == 0) {
										insert_student_Connection_try = insert_student_Connection;
									} else {
										insert_student_Connection_try = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
												+ champ_nom.getText()
														.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase()
												+ "_"
												+ cpt
												+ "' , '"
												+ Codage.MD5(champ_nom
														.getText()
														.toLowerCase()
														+ champ_prenom
																.getText()
																.toLowerCase()
														+ "_" + cpt)
												+ "', '"
												+ numero_tuple
												+ "' , 'STUDENT', '0');";

									}

									nb_mise_a_jour = 0;
									nb_mise_a_jour = BDD_exchange
											.update_Query(insert_student_Connection_try);

									System.out.println(" nb_mise_a_jour -> "
											+ nb_mise_a_jour);
									if (nb_mise_a_jour != 0) {
										label_logs
												.setText(" Ajout de l'étudiant : "
														+ champ_nom.getText()
																.toUpperCase()
														+ " "
														+ champ_prenom
																.getText()
																.toUpperCase()
														+ "  au login ->" + champ_nom.getText()
														.toLowerCase() + champ_prenom
														.getText()
														.toLowerCase() + "  "
														+ label_logs.getText());

										champ_nom.setText("");
										champ_prenom.setText("");
									}

									cpt = cpt + 1;
								} while (nb_mise_a_jour == 0);

							}
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				/* FIN */

			} else {
				// ce n'est pas un M1 ni un M2
				// on test s'il n'existe pas déjà !

				System.out.println(" Ce n'est pas un M1 ou M2 "
						+ Jlist_Promotions.getSelectedItem());

				/* DEBUT */

				String query_etudiant = "SELECT * FROM students WHERE SNAME = '"
						+ champ_nom.getText().toUpperCase()
						+ "' AND SFNAME ='"
						+ champ_prenom.getText().toUpperCase()
						+ "' AND SPROMO ='"
						+ Jlist_Promotions.getSelectedItem() + "' ;";
				System.out.println(" query_etudiants -> " + query_etudiant);

				Object[][] result = null;

				try {
					result = BDD_exchange.getThings(query_etudiant);

					if (result.length != 0) {
						// on notifie qu'elle existe déjà dans le Jlabel !
						label_logs
								.setText(" Ajout de l'étudiant impossible -> existe déjà !"
										+ label_logs.getText());
					} else {
						// On insert cette nouvel étudiant dans la table
						System.out
								.println("On insert ce nouvel étudiant dans la table ni M1 ni M2 ");

						String query_number_SID = "SELECT SID FROM students ORDER BY SID DESC LIMIT 0,1 ;"; // Il
						// faut
						// ordonner
						// la
						// liste
						// en
						// ne
						// donnant
						// que le dernier et ensuite on le convertit en
						result = null;
						result = BDD_exchange.getThings(query_number_SID);

						if (result.length == 0) {

							String insert_etudiant = "INSERT INTO students (SID, SNAME, SFNAME, SPROMO)  VALUES ('1', '"
									+ champ_nom.getText().toUpperCase()
									+ "' , '"
									+ champ_prenom.getText().toUpperCase()
									+ "' , '"
									+ Jlist_Promotions.getSelectedItem()
									+ "');";

							System.out.println(" insert_etudiant -> "
									+ insert_etudiant);
							int nb_mise_a_jour = BDD_exchange
									.update_Query(insert_etudiant);

							if (nb_mise_a_jour == 1) {
								// on crer son login !
								String insert_student_Connection = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
										+ champ_nom.getText().toLowerCase()
										+ champ_prenom.getText().toLowerCase()
										+ "' , '"
										+ Codage.MD5(champ_nom.getText()
												.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase())
										+ "', '1' , 'STUDENT', '0');";

								int cpt = 0;

								do {
									String insert_student_Connection_try;

									if (cpt == 0) {
										insert_student_Connection_try = insert_student_Connection;
									} else {
										insert_student_Connection_try = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
												+ champ_nom.getText()
														.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase()
												+ "_"
												+ cpt
												+ "' , '"
												+ Codage.MD5(champ_nom
														.getText()
														.toLowerCase()
														+ champ_prenom
																.getText()
																.toLowerCase()
														+ "_" + cpt)
												+ "', '1' , 'STUDENT', '0');";

									}

									nb_mise_a_jour = 0;
									nb_mise_a_jour = BDD_exchange
											.update_Query(insert_student_Connection_try);

									System.out.println(" nb_mise_a_jour -> "
											+ nb_mise_a_jour);
									if (nb_mise_a_jour != 0) {
										label_logs
												.setText(" Ajout de l'étudiant : "
														+ champ_nom.getText()
																.toUpperCase()
														+ " "
														+ champ_prenom
																.getText()
																.toUpperCase()
														+ "  au login ->" + champ_nom.getText()
														.toLowerCase() +champ_prenom.getText()
														.toLowerCase()
														+ label_logs.getText());

										champ_nom.setText("");
										champ_prenom.setText("");
									}

									cpt = cpt + 1;
								} while (nb_mise_a_jour == 0);
							}
						} else {
							// toujours M1 ou M2
							// Il y a déjà un élève dans la table !
							int numero_tuple = Integer.parseInt(result[0][0]
									.toString()) + 1;

							String insert_etudiant = "INSERT INTO students (SID, SNAME, SFNAME, SPROMO)  VALUES ('"
									+ numero_tuple
									+ "', '"
									+ champ_nom.getText().toUpperCase()
									+ "' , '"
									+ champ_prenom.getText().toUpperCase()
									+ "' , '"
									+ Jlist_Promotions.getSelectedItem()
									+ "');";

							System.out.println(" insert_etudiant -> "
									+ insert_etudiant);

							int nb_mise_a_jour = BDD_exchange
									.update_Query(insert_etudiant);

							if (nb_mise_a_jour == 1) {
								// on crer son login !
								String insert_student_Connection = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
										+ champ_nom.getText().toLowerCase()
										+ champ_prenom.getText().toLowerCase()
										+ "' , '"
										+ Codage.MD5(champ_nom.getText()
												.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase())
										+ "', '"
										+ numero_tuple
										+ "' , 'STUDENT', '0');";

								int cpt = 0;

								do {
									String insert_student_Connection_try;

									if (cpt == 0) {
										insert_student_Connection_try = insert_student_Connection;
									} else {
										insert_student_Connection_try = "INSERT INTO connection (LOGIN, PASSWORD, USER, USER_TYPE, CONNECTED) VALUES ('"
												+ champ_nom.getText()
														.toLowerCase()
												+ champ_prenom.getText()
														.toLowerCase()
												+ "_"
												+ cpt
												+ "' , '"
												+ Codage.MD5(champ_nom
														.getText()
														.toLowerCase()
														+ champ_prenom
																.getText()
																.toLowerCase()
														+ "_" + cpt)
												+ "', '"
												+ numero_tuple
												+ "' , 'STUDENT', '0');";

									}

									nb_mise_a_jour = 0;
									nb_mise_a_jour = BDD_exchange
											.update_Query(insert_student_Connection_try);

									System.out.println(" nb_mise_a_jour -> "
											+ nb_mise_a_jour);
									if (nb_mise_a_jour != 0) {
										label_logs
												.setText(" Ajout de l'étudiant : "
														+ champ_nom.getText()
																.toUpperCase()
														+ " "
														+ champ_prenom
																.getText()
																.toUpperCase()
														+ "  au login ->" + champ_nom.getText()
														.toLowerCase() +champ_prenom.getText()
														.toLowerCase()
														+ label_logs.getText());

										champ_nom.setText("");
										champ_prenom.setText("");
									}

									cpt = cpt + 1;
								} while (nb_mise_a_jour == 0);

							}
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
		}

	}

}
