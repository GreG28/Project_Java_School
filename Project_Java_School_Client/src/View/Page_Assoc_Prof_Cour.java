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

import Controler.Event_Page_Assoc_Prof_Cours;
import Model.BDD_exchange;
import Model.User;

public class Page_Assoc_Prof_Cour extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	public JLabel Label_logs;
	JComboBox<String> Jlist_Matiere;
	JComboBox<String> Jlist_Professeur;
	JComboBox<String> Jlist_Promotion;
	JComboBox<String> Jlist_Majeur;
	int[] liste_ID_Matiere;
	int[] liste_ID_Majeur;
	int[] liste_ID_Teachers;

	public Page_Assoc_Prof_Cour(First_Window fenetre, User utilisateur) {

		fenetre_principale = fenetre;

		JLabel Label_Prof = new JLabel("Nom du professeur");

		JLabel lblMatière = new JLabel("Matière");

		JLabel lblPageAssociation = new JLabel(
				"Page d'association Prof <-> Cours");
		lblPageAssociation.setFont(new Font("Source Code Pro Semibold",
				Font.PLAIN, 17));

		JTextArea Label_Logs = new JTextArea();
		Label_Logs.setDragEnabled(true);
		Label_Logs.setAlignmentX(Component.LEFT_ALIGNMENT);
		Label_Logs.setWrapStyleWord(true);
		Label_Logs.setLineWrap(true);
		Label_Logs.setEditable(false);

		Jlist_Matiere = new JComboBox<String>();
		Jlist_Matiere.addItem("");

		Jlist_Professeur = new JComboBox<String>();
		Jlist_Professeur.addItem("");

		Jlist_Majeur = new JComboBox<String>();
		Jlist_Majeur.addItem("");

		Jlist_Promotion = new JComboBox<String>();
		Jlist_Promotion.addItem("");

		JButton bouton_selection_Prof = new JButton("Selectionner Professeur");
		bouton_selection_Prof.setToolTipText("Selectionner Professeur");
		bouton_selection_Prof.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
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
				// on recherche toutes les promotions dans la table promotions !
			}
		});

		JButton bouton_refresh_professeurs = new JButton(
				"Rafraichir liste professeurs");
		bouton_refresh_professeurs
				.setToolTipText("Rafraichir liste professeurs");
		bouton_refresh_professeurs.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Jlist_Professeur.removeAllItems();
				Object[][] result;
				try {
					result = BDD_exchange
							.getThings("SELECT TNAME, TFNAME, TID FROM teachers ;");
					liste_ID_Teachers = new int[result.length];
					for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
						Jlist_Professeur.addItem((String) result[cpt][0] + "  " + (String) result[cpt][1] );
						liste_ID_Teachers[cpt] = (Integer) result[cpt][2];
					}
				} catch (SQLException e_1) {
					e_1.printStackTrace();
				}
			}
		});

		JLabel lblPromotion = new JLabel("Promotion");

		JButton btnSelectionnerPromotion = new JButton("Selectionner Promotion");
		btnSelectionnerPromotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jlist_Majeur.removeAllItems();
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
					// Mais on remplit les matières dispos !

					Jlist_Matiere.removeAllItems();
					Object[][] result;
					try {
						result = BDD_exchange
								.getThings("SELECT CNAME, CID FROM courses WHERE CPROMO ='"
										+ Jlist_Promotion.getSelectedItem()
										+ "' ;");
						liste_ID_Matiere = new int[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Matiere.addItem((String) result[cpt][0]);
							liste_ID_Matiere[cpt] = (Integer) result[cpt][1];
						}
					} catch (SQLException e_1) {
						e_1.printStackTrace();
					}
				}
			}
		});
		btnSelectionnerPromotion.setToolTipText("Selectionner Promotion");

		JLabel lblMajeur = new JLabel("Majeur");

		JButton bouton_select_Majeur = new JButton("Selectionner Majeur");
		bouton_select_Majeur.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// on regarde si l'item selectionner est M1 ou M2
				Jlist_Matiere.removeAllItems();
				Object[][] result;
				try {
					result = BDD_exchange
							.getThings("SELECT CNAME, CID FROM courses WHERE CPROMO = '"
									+ Jlist_Promotion.getSelectedItem()
									+ "' AND CMAJEUR = '"
									+ liste_ID_Majeur[Jlist_Majeur
											.getSelectedIndex()] + "';");
					System.out
							.println("SELECT CNAME, CID FROM courses WHERE CPROMO '"
									+ Jlist_Promotion.getSelectedItem()
									+ "' AND CMAJEUR = '"
									+ liste_ID_Majeur[Jlist_Majeur
											.getSelectedIndex()] + "';");
					liste_ID_Matiere = new int[result.length];
					for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
						Jlist_Matiere.addItem((String) result[cpt][0]);
						liste_ID_Matiere[cpt] = (Integer) result[cpt][1];
					}
				} catch (SQLException e_1) {
					e_1.printStackTrace();
				}
			}
		});

		JButton bouton_Creer = new JButton("Associer la matière au professeur");
		bouton_Creer.setToolTipText("Associer la matière au professeur");
		bouton_Creer.addActionListener(new Event_Page_Assoc_Prof_Cours(fenetre,
				utilisateur, Jlist_Professeur, Jlist_Promotion, Jlist_Majeur,
				Jlist_Matiere, liste_ID_Matiere, liste_ID_Majeur, Label_Logs,
				this));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(52)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																lblPageAssociation,
																GroupLayout.PREFERRED_SIZE,
																357,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblMatière)
																						.addComponent(
																								lblMajeur)
																						.addComponent(
																								lblPromotion)
																						.addComponent(
																								Label_Prof))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								Label_Logs,
																								Alignment.TRAILING,
																								GroupLayout.DEFAULT_SIZE,
																								234,
																								Short.MAX_VALUE)
																						.addComponent(
																								Jlist_Matiere,
																								0,
																								234,
																								Short.MAX_VALUE)
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
																												Jlist_Professeur,
																												0,
																												234,
																												Short.MAX_VALUE)
																										.addComponent(
																												bouton_refresh_professeurs,
																												GroupLayout.DEFAULT_SIZE,
																												234,
																												Short.MAX_VALUE)
																										.addComponent(
																												bouton_selection_Prof,
																												GroupLayout.DEFAULT_SIZE,
																												234,
																												Short.MAX_VALUE)
																										.addComponent(
																												btnSelectionnerPromotion,
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
																												Short.MAX_VALUE))
																						.addComponent(
																								bouton_Creer,
																								GroupLayout.DEFAULT_SIZE,
																								234,
																								Short.MAX_VALUE))
																		.addGap(40)))
										.addGap(36)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(lblPageAssociation,
												GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addGap(5)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				bouton_refresh_professeurs)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				Jlist_Professeur,
																				GroupLayout.PREFERRED_SIZE,
																				29,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																Label_Prof))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(bouton_selection_Prof)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(18)
																		.addComponent(
																				lblPromotion))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				Jlist_Promotion,
																				GroupLayout.PREFERRED_SIZE,
																				27,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnSelectionnerPromotion)
																		.addGap(4)
																		.addComponent(
																				Jlist_Majeur,
																				GroupLayout.PREFERRED_SIZE,
																				27,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(lblMajeur))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				bouton_select_Majeur)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				Jlist_Matiere,
																				GroupLayout.PREFERRED_SIZE,
																				30,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																lblMatière))
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
										.addGap(28)));
		setLayout(groupLayout);
	}

	public void setLabel_logs(JLabel label_logs) {
		Label_logs = label_logs;
	}

	public int[] getListe_ID_Majeurs() {
		return liste_ID_Majeur;
	}

	public int[] getListe_ID_Matiere() {
		return liste_ID_Matiere;
	}

	public int[] getListe_ID_Teachers() {
		return liste_ID_Teachers;
	}

}
