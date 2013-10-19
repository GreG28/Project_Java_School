package View;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controler.Event_Page_Assoc_Student_Cours;
import Model.BDD_exchange;
import Model.User;

public class Page_Assoc_Student_Cour extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	JComboBox<String> Jlist_Eleve;
	JComboBox<String> Jlist_Promotion;
	JComboBox<String> Jlist_Majeur;
	JComboBox<String> Jlist_Matieres;
	int[] liste_ID_Matiere;
	int[] liste_ID_Majeur;
	int[] liste_ID_Students;
	public Page_Assoc_Student_Cour page_assoc_student_cours;

	public Page_Assoc_Student_Cour(First_Window fenetre, User utilisateur) {

		fenetre_principale = fenetre;
		page_assoc_student_cours = this;

		JLabel lblEleve = new JLabel("Eleve");

		JLabel lblPageAssociation = new JLabel(
				"Page d'association Student <-> Cours");
		lblPageAssociation.setFont(new Font("Source Code Pro Semibold",
				Font.PLAIN, 17));

		JTextArea Label_Logs = new JTextArea();
		Label_Logs.setDragEnabled(true);
		Label_Logs.setAlignmentX(Component.LEFT_ALIGNMENT);
		Label_Logs.setWrapStyleWord(true);
		Label_Logs.setLineWrap(true);
		Label_Logs.setEditable(false);

		Jlist_Eleve = new JComboBox<String>();
		Jlist_Eleve.addItem("");

		Jlist_Majeur = new JComboBox<String>();
		Jlist_Majeur.addItem("");

		Jlist_Promotion = new JComboBox<String>();
		Jlist_Promotion.addItem("");

		Jlist_Matieres = new JComboBox<String>();
		Jlist_Matieres.addItem("");

		JLabel lblPromotion = new JLabel("Promotion");

		JButton bouton_Refresh_Promotions = new JButton(
				"Rafraichir liste promotions");
		bouton_Refresh_Promotions.setToolTipText("Rafraichir liste promotions");
		bouton_Refresh_Promotions.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Jlist_Promotion.removeAllItems();
				Object[][] result;
				try {
					result = BDD_exchange
							.getThings("SELECT PNAME FROM promotions ;");
					for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
						Jlist_Promotion.addItem((String) result[cpt][0]);
					}
				} catch (SQLException e_1) {
					e_1.printStackTrace();
				}
			}
		});

		JButton bouton_Selectionner_Promotion = new JButton(
				"Selectionner Promotion");
		bouton_Selectionner_Promotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jlist_Majeur.removeAllItems();
				Jlist_Eleve.removeAllItems();
				if (Jlist_Promotion.getSelectedItem().equals("M1")
						|| Jlist_Promotion.getSelectedItem().equals("M2")) {
					// on sort les majeurs !
					Object[][] result;
					try {
						result = BDD_exchange
								.getThings("SELECT MNAME, MID FROM majeurs ;");
						liste_ID_Majeur = new int[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Majeur.addItem((String) result[cpt][0]);
							liste_ID_Majeur[cpt] = (Integer) result[cpt][1];
						}
					} catch (SQLException e_1) {
						e_1.printStackTrace();
					}
				} else {
					// on laisse sans majeurs !
					// Mais on remplit les élèves !

					Jlist_Eleve.removeAllItems();
					Object[][] result;
					try {
						result = BDD_exchange
								.getThings("SELECT SNAME, SFNAME, SID FROM students WHERE SPROMO ='"
										+ Jlist_Promotion.getSelectedItem()
										+ "' ;");
						liste_ID_Students = new int[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Eleve.addItem((String) result[cpt][0] + "  "
									+ result[cpt][1]);
							liste_ID_Students[cpt] = (Integer) result[cpt][2];
						}
					} catch (SQLException e_1) {
						e_1.printStackTrace();
					}
				}
			}
		});
		bouton_Selectionner_Promotion.setToolTipText("Selectionner Promotion");

		JLabel lblMajeur = new JLabel("Majeur");

		JButton bouton_select_Majeur = new JButton("Selectionner Majeur");
		bouton_select_Majeur.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Jlist_Eleve.removeAllItems();
				if (Jlist_Promotion.getSelectedItem().equals("M1")
						|| Jlist_Promotion.getSelectedItem().equals("M2")) {
					// on sort les élèves de cette majeur !
					Object[][] result;
					try {
						result = BDD_exchange
								.getThings("SELECT SNAME, SFNAME, SID FROM students WHERE SPROMO ='"
										+ Jlist_Promotion.getSelectedItem()
										+ "' AND SMAJEUR ='"
										+ liste_ID_Majeur[Jlist_Majeur
												.getSelectedIndex()] + "' ;");
						liste_ID_Students = new int[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Eleve.addItem((String) result[cpt][0] + "  "
									+ (String) result[cpt][1]);
							liste_ID_Students[cpt] = (Integer) result[cpt][2];
						}
					} catch (SQLException e_1) {
						e_1.printStackTrace();
					}
				} else {
					// on remet les élèves !

					Jlist_Eleve.removeAllItems();
					Object[][] result;
					try {
						result = BDD_exchange
								.getThings("SELECT SNAME, SFNAME, SID FROM students WHERE SPROMO ='"
										+ Jlist_Promotion.getSelectedItem()
										+ "' ;");
						liste_ID_Students = new int[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Eleve.addItem((String) result[cpt][0] + "  "
									+ result[cpt][1]);
							liste_ID_Students[cpt] = (Integer) result[cpt][2];
						}
					} catch (SQLException e_1) {
						e_1.printStackTrace();
					}
				}
			}
		});

		JButton bouton_Rafraichir_Liste_Matieres = new JButton(
				"Rafraichir liste matieres");
		bouton_Rafraichir_Liste_Matieres
				.setToolTipText("Rafraichir liste matieres");
		bouton_Rafraichir_Liste_Matieres
				.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// si la liste des élèves n'est pas vide, on cherche
						// toutes les matières qui font partie de la promo
						// majeurs
						if (Jlist_Eleve.getSelectedItem().equals("")) {
							// on vide les cours !
							Jlist_Matieres.removeAllItems();
						} else {
							Jlist_Matieres.removeAllItems();
							if ((Jlist_Promotion.getSelectedItem().equals("M1") || Jlist_Promotion
									.getSelectedItem().equals("M2"))
									&& (!Jlist_Majeur.getSelectedItem().equals(
											""))) {
								// si M1 ou M2 et majeur connue !
								Object[][] result;
								try {
									result = BDD_exchange.getThings("SELECT CNAME, CID FROM courses WHERE CPROMO ='"
											+ Jlist_Promotion.getSelectedItem()
											+ "' AND CMAJEUR ='"
											+ liste_ID_Majeur[Jlist_Majeur
													.getSelectedIndex()] + "';");
									liste_ID_Matiere = new int[result.length];
									for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
										Jlist_Matieres
												.addItem((String) result[cpt][0]);
										liste_ID_Matiere[cpt] = (Integer) result[cpt][1];
									}
								} catch (SQLException e_1) {
									e_1.printStackTrace();
								}
							} else {
								Object[][] result;
								try {
									result = BDD_exchange
											.getThings("SELECT CNAME, CID FROM courses WHERE CPROMO ='"
													+ Jlist_Promotion
															.getSelectedItem()
													+ "' ;");
									liste_ID_Matiere = new int[result.length];
									for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
										Jlist_Matieres
												.addItem((String) result[cpt][0]);
										liste_ID_Matiere[cpt] = (Integer) result[cpt][1];
									}
								} catch (SQLException e_1) {
									e_1.printStackTrace();
								}
							}
						}
					}
				});

		JLabel lblMatieres = new JLabel("Matieres");
		lblMatieres.setToolTipText("Matieres");

		JButton bouton_Creer = new JButton("Associer la matière à l'élève");
		bouton_Creer.setToolTipText("Associer la matière à l'élève");
		bouton_Creer.addActionListener(new Event_Page_Assoc_Student_Cours(
				fenetre, utilisateur, Jlist_Eleve, Jlist_Promotion,
				Jlist_Majeur, Jlist_Matieres, Label_Logs,
				page_assoc_student_cours));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(27)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblMatieres,
																								GroupLayout.PREFERRED_SIZE,
																								91,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblEleve)
																						.addComponent(
																								lblMajeur)
																						.addComponent(
																								lblPromotion))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createParallelGroup(
																												Alignment.TRAILING,
																												false)
																										.addComponent(
																												Jlist_Matieres,
																												Alignment.LEADING,
																												0,
																												234,
																												Short.MAX_VALUE)
																										.addComponent(
																												Jlist_Eleve,
																												Alignment.LEADING,
																												0,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												bouton_Rafraichir_Liste_Matieres,
																												Alignment.LEADING,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												bouton_Creer,
																												Alignment.LEADING,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												Label_Logs,
																												Alignment.LEADING))
																						.addGroup(
																								groupLayout
																										.createParallelGroup(
																												Alignment.TRAILING,
																												false)
																										.addComponent(
																												Jlist_Promotion,
																												0,
																												234,
																												Short.MAX_VALUE)
																										.addComponent(
																												bouton_Selectionner_Promotion,
																												GroupLayout.DEFAULT_SIZE,
																												234,
																												Short.MAX_VALUE)
																										.addComponent(
																												Jlist_Majeur,
																												0,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												bouton_select_Majeur,
																												Alignment.LEADING,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												bouton_Refresh_Promotions,
																												Alignment.LEADING,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblPageAssociation,
																				GroupLayout.PREFERRED_SIZE,
																				453,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(36, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(lblPageAssociation,
												GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(41)
																		.addComponent(
																				lblPromotion)
																		.addGap(47)
																		.addComponent(
																				lblMajeur))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				bouton_Refresh_Promotions)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				Jlist_Promotion,
																				GroupLayout.PREFERRED_SIZE,
																				27,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				bouton_Selectionner_Promotion)
																		.addGap(4)
																		.addComponent(
																				Jlist_Majeur,
																				GroupLayout.PREFERRED_SIZE,
																				27,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(bouton_select_Majeur)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(lblEleve)
														.addComponent(
																Jlist_Eleve,
																GroupLayout.PREFERRED_SIZE,
																30,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(
												bouton_Rafraichir_Liste_Matieres)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																Jlist_Matieres,
																GroupLayout.PREFERRED_SIZE,
																29,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblMatieres))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(Label_Logs,
												GroupLayout.PREFERRED_SIZE, 47,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(bouton_Creer,
												GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addGap(163)));
		setLayout(groupLayout);
	}

	public int[] getListe_ID_Majeurs() {
		return liste_ID_Majeur;
	}

	public int[] getListe_ID_Matiere() {
		return liste_ID_Matiere;
	}

	public int[] getListe_ID_Students() {
		return liste_ID_Students;
	}

}
