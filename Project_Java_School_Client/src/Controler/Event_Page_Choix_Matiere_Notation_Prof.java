package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;
import View.Page_Choix_Matiere_Notation_Prof;

public class Event_Page_Choix_Matiere_Notation_Prof implements ActionListener {

	First_Window fenetre;
	User utilisateur;
	JComboBox<String> jlist_Promotion;
	JComboBox<String> jlist_Majeur;
	JComboBox<String> jlist_Matiere;
	Page_Choix_Matiere_Notation_Prof page_Choix_Matiere_Notation_Prof;

	public Event_Page_Choix_Matiere_Notation_Prof(First_Window fenetre,
			User utilisateur, JComboBox<String> jlist_Promotions,
			JComboBox<String> jlist_Majeurs, JComboBox<String> jlist_Matieres,
			Page_Choix_Matiere_Notation_Prof page_choix_notation_prof) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.jlist_Promotion = jlist_Promotions;
		this.jlist_Majeur = jlist_Majeurs;
		this.jlist_Matiere = jlist_Matieres;
		this.page_Choix_Matiere_Notation_Prof = page_choix_notation_prof;
	}

	public void actionPerformed(ActionEvent arg0) {

		if (jlist_Promotion.getSelectedItem().equals("")
				|| jlist_Matiere.getSelectedItem().equals("")) {
			// ne rien faire !
		} else {
			// on connait la promotion et la matière
			if (jlist_Promotion.getSelectedItem().equals("M1")
					|| jlist_Promotion.getSelectedItem().equals("M2")) {
				if (jlist_Majeur.getSelectedItem().equals("")) {
					// on ne fait rien !
				} else {
					// c'est pour M1 ou M2
					// la majeur et matière est connue

					/***
					 * Comment qu'on fait ? On doit essayer de ressortir toutes
					 * les personnes qui participent à cette matière ! mais en
					 * prenant soin de vérifier que certaines personnes n'ont
					 * pas déjà de notes dans la table marks !!
					 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					 * Ensuite on recopie le tableau d'objets dans un autre à
					 * une dimension de plus une colonne pour y mettre les notes
					 * ! et on l'enverra ensuite à la page de notation et on
					 * passera à celle là !
					 ***/

					// on creer le premier tableau avec toutes les personnes
					// faisant partie de cette matière !

					String query_sudents_learns = " SELECT SNAME, SFNAME, STUDENT, COURSE FROM learns JOIN students WHERE COURSE = '"
							+ page_Choix_Matiere_Notation_Prof
									.getListe_ID_Matieres()[jlist_Matiere
									.getSelectedIndex()]
							+ "' AND STUDENT = students.SID ;";
					System.out.println(" query_learns m1 ou m2 -> "
							+ query_sudents_learns);
					Object[][] result = null;

					try {
						result = BDD_exchange.getThings(query_sudents_learns);

						if (result.length == 0) {
							// on affiche qu'il n'y a personne qui étudie cette
							// matière !
							JOptionPane
									.showMessageDialog(
											null,
											"Personne n'étudie cette matière désolé vous ne pouvez mettre de notes !",
											"ATTENTION",
											JOptionPane.ERROR_MESSAGE);
						} else {
							// on va completer cette liste pour avoir les notes
							// déjà
							// entrée pour cette matière !

							String query_sudents_marks = " SELECT SNAME, SFNAME, MARK, SID, CID FROM exams NATURAL JOIN students WHERE CID = '"
									+ page_Choix_Matiere_Notation_Prof
											.getListe_ID_Matieres()[jlist_Matiere
											.getSelectedIndex()] + "';";
							System.out.println(" query_learns -> "
									+ query_sudents_marks);
							Object[][] result_notes_existantes = null;

							result_notes_existantes = BDD_exchange
									.getThings(query_sudents_marks);

							if (result_notes_existantes.length == 0) {
								// on recopie le tableau dans un tableau d'une
								// colonne plus grande !!

								int[][] eleve_SID_cours_CID = new int[result.length][2];
								Object[][] tableau_noms_notes = new Object[result.length][3];
								for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
									tableau_noms_notes[cpt][0] = result[cpt][0];
									tableau_noms_notes[cpt][1] = result[cpt][1];
									tableau_noms_notes[cpt][2] = 0.0;

									eleve_SID_cours_CID[cpt][0] = (Integer) result[cpt][2];
									eleve_SID_cours_CID[cpt][1] = (Integer) result[cpt][3];
								}

								for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
									System.out
											.println(tableau_noms_notes[cpt][0]
													+ " || "
													+ tableau_noms_notes[cpt][1]
													+ " || "
													+ tableau_noms_notes[cpt][2]);
								}

								// on modifie le tableau de la page de notation
								// et
								// ensuite on l'affiche !

								fenetre.getPage_notation()
										.setTableau(tableau_noms_notes,
												eleve_SID_cours_CID);
								fenetre.getCard_Layout_Panel_center_Notation_Prof();

							} else {
								System.out
										.println(" Il y a déjà des notes pour cette matières");
								// on recopie le tableau dans un plus grand en
								// recopiant les notes !
								// pour chaque ligne du tableau
								// result_notes_existantes on fais un test sur
								// tout

								int[][] eleve_SID_cours_CID = new int[result.length][2];
								Object[][] tableau_noms_notes = new Object[result.length][3];
								for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
									tableau_noms_notes[cpt][0] = result[cpt][0];
									tableau_noms_notes[cpt][1] = result[cpt][1];
									tableau_noms_notes[cpt][2] = 0.0;

									eleve_SID_cours_CID[cpt][0] = (Integer) result[cpt][2];
									eleve_SID_cours_CID[cpt][1] = (Integer) result[cpt][3];
								}

								for (int cpt2 = 0; cpt2 < result_notes_existantes.length; cpt2 = cpt2 + 1) {
									for (int cpt3 = 0; cpt3 < result.length; cpt3 = cpt3 + 1) {
										if (result_notes_existantes[cpt2][0]
												.equals(result[cpt3][0])
												&& result_notes_existantes[cpt2][1]
														.equals(result[cpt3][1])) {
											tableau_noms_notes[cpt3][2] = result_notes_existantes[cpt2][2];
										}
									}
								}

								for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
									System.out
											.println(tableau_noms_notes[cpt][0]
													+ " || "
													+ tableau_noms_notes[cpt][1]
													+ " || "
													+ tableau_noms_notes[cpt][2]);
								}

								fenetre.getPage_notation()
										.setTableau(tableau_noms_notes,
												eleve_SID_cours_CID);
								fenetre.getCard_Layout_Panel_center_Notation_Prof();

							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			} else {
				// ce n'est pas pour des M1 ni pour des M2

				String query_sudents_learns = " SELECT SNAME, SFNAME, STUDENT, COURSE FROM learns JOIN students WHERE COURSE = '"
						+ page_Choix_Matiere_Notation_Prof
								.getListe_ID_Matieres()[jlist_Matiere
								.getSelectedIndex()]
						+ "' AND STUDENT = students.SID ;";
				System.out.println(" query_learns ni m1 ni m2 -> "
						+ query_sudents_learns);
				Object[][] result = null;

				try {
					result = BDD_exchange.getThings(query_sudents_learns);

					if (result.length == 0) {
						// on affiche qu'il n'y a personne qui étudie cette
						// matière !
						JOptionPane
								.showMessageDialog(
										null,
										"Personne n'étudie cette matière désolé vous ne pouvez mettre de notes !",
										"ATTENTION", JOptionPane.ERROR_MESSAGE);
					} else {
						// on va completer cette liste pour avoir les notes déjà
						// entrée pour cette matière !

						String query_sudents_marks = " SELECT SNAME, SFNAME, MARK, SID, CID FROM exams NATURAL JOIN students WHERE CID = '"
								+ page_Choix_Matiere_Notation_Prof
										.getListe_ID_Matieres()[jlist_Matiere
										.getSelectedIndex()] + "';";
						System.out.println(" query_learns -> "
								+ query_sudents_marks);
						Object[][] result_notes_existantes = null;

						result_notes_existantes = BDD_exchange
								.getThings(query_sudents_marks);

						if (result_notes_existantes.length == 0) {
							// on recopie le tableau dans un tableau d'une
							// colonne plus grande !!

							int[][] eleve_SID_cours_CID = new int[result.length][2];
							Object[][] tableau_noms_notes = new Object[result.length][3];
							for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
								tableau_noms_notes[cpt][0] = result[cpt][0];
								tableau_noms_notes[cpt][1] = result[cpt][1];
								tableau_noms_notes[cpt][2] = 0.0;

								eleve_SID_cours_CID[cpt][0] = (Integer) result[cpt][2];
								eleve_SID_cours_CID[cpt][1] = (Integer) result[cpt][3];
							}

							for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
								System.out.println(tableau_noms_notes[cpt][0]
										+ " || " + tableau_noms_notes[cpt][1]
										+ " || " + tableau_noms_notes[cpt][2]);
							}

							// on modifie le tableau de la page de notation et
							// ensuite on l'affiche !

							fenetre.getPage_notation().setTableau(
									tableau_noms_notes, eleve_SID_cours_CID);
							fenetre.getCard_Layout_Panel_center_Notation_Prof();

						} else {
							System.out
									.println(" Il y a déjà des notes pour cette matières");
							// on recopie le tableau dans un plus grand en
							// recopiant les notes !
							// pour chaque ligne du tableau
							// result_notes_existantes on fais un test sur tout

							int[][] eleve_SID_cours_CID = new int[result.length][2];
							Object[][] tableau_noms_notes = new Object[result.length][3];
							for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
								tableau_noms_notes[cpt][0] = result[cpt][0];
								tableau_noms_notes[cpt][1] = result[cpt][1];
								tableau_noms_notes[cpt][2] = 0.0;

								eleve_SID_cours_CID[cpt][0] = (Integer) result[cpt][2];
								eleve_SID_cours_CID[cpt][1] = (Integer) result[cpt][3];
							}

							for (int cpt2 = 0; cpt2 < result_notes_existantes.length; cpt2 = cpt2 + 1) {
								for (int cpt3 = 0; cpt3 < result.length; cpt3 = cpt3 + 1) {
									if (result_notes_existantes[cpt2][0]
											.equals(result[cpt3][0])
											&& result_notes_existantes[cpt2][1]
													.equals(result[cpt3][1])) {
										tableau_noms_notes[cpt3][2] = result_notes_existantes[cpt2][2];
									}
								}
							}

							for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
								System.out.println(tableau_noms_notes[cpt][0]
										+ " || " + tableau_noms_notes[cpt][1]
										+ " || " + tableau_noms_notes[cpt][2]);
							}

							fenetre.getPage_notation().setTableau(
									tableau_noms_notes, eleve_SID_cours_CID);
							fenetre.getCard_Layout_Panel_center_Notation_Prof();

						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}
}
