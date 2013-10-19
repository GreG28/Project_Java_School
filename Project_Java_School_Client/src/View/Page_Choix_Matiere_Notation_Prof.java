package View;

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
import javax.swing.LayoutStyle.ComponentPlacement;

import Controler.Event_Page_Choix_Matiere_Notation_Prof;
import Model.BDD_exchange;
import Model.User;

public class Page_Choix_Matiere_Notation_Prof extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	public User utilisateur;
	public JLabel Label_logs;
	JComboBox<String> Jlist_Promotions;
	JComboBox<String> Jlist_Majeurs;
	JComboBox<String> Jlist_Matieres;
	int[] liste_ID_Majeurs;
	int[] liste_ID_Matieres;
	public Page_Choix_Matiere_Notation_Prof page_choix_notation_prof;

	public Page_Choix_Matiere_Notation_Prof(First_Window fenetre,
			User utilisateur) {

		this.fenetre_principale = fenetre;
		this.utilisateur = utilisateur;
		page_choix_notation_prof = this;

		JLabel lblPromotion = new JLabel("Promotion");

		JLabel lblPageDeChoix = new JLabel(
				"Page du choix de la matière à noter !");
		lblPageDeChoix.setFont(new Font("Source Code Pro Semibold", Font.PLAIN,
				17));

		Jlist_Promotions = new JComboBox<String>();
		Jlist_Promotions.addItem("");

		Jlist_Majeurs = new JComboBox<String>();
		Jlist_Majeurs.addItem("");

		Jlist_Matieres = new JComboBox<String>();
		Jlist_Matieres.addItem("");

		JLabel lblMajeursiM = new JLabel("Majeur (si M1 ou M2)");

		JButton bouton_Rafraichir_Promotions = new JButton(
				"Rafraichir promotions");
		bouton_Rafraichir_Promotions.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Jlist_Promotions.removeAllItems();
				Object[][] result;
				try {
					result = BDD_exchange
							.getThings("SELECT PNAME FROM promotions;");
					for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
						Jlist_Promotions.addItem((String) result[cpt][0]);
					}
				} catch (SQLException e_1) {
					e_1.printStackTrace();
				}
			}
		});

		JButton bouton_valider_promotion = new JButton("Valider Promotion");
		bouton_valider_promotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Jlist_Promotions.getSelectedItem().equals("M1")
						|| Jlist_Promotions.getSelectedItem().equals("M2")) {
					Jlist_Majeurs.removeAllItems();
					Object[][] result;
					try {
						result = BDD_exchange
								.getThings("SELECT MID, MNAME FROM majeurs ;");
						liste_ID_Majeurs = new int[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Majeurs.addItem((String) result[cpt][1]);
							liste_ID_Majeurs[cpt] = (Integer) result[cpt][0];
						}
						System.out.println(" liste_ID_Majeurs.lenght ->"
								+ liste_ID_Majeurs.length);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					Jlist_Majeurs.removeAllItems();
					Jlist_Majeurs.addItem("");
					// on sort les matières qu'il enseigne !!
					Jlist_Matieres.removeAllItems();
					Object[][] result;
					try {

						/*
						 * SELECT CNAME FROM teaches NATURAL JOIN teachers,
						 * courses WHERE TID = '1'
						 */

						String query_essai = "SELECT CNAME, CID FROM teaches NATURAL JOIN courses WHERE TID ='"
								+ page_choix_notation_prof.utilisateur.getId()
								+ "' AND CPROMO ='"
								+ Jlist_Promotions.getSelectedItem() + "' ;";
						
						System.out.println(" query essai ->" + query_essai);
						
						result = BDD_exchange.getThings(query_essai
								);
						liste_ID_Matieres = new int[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Matieres.addItem((String) result[cpt][0]);
							liste_ID_Matieres[cpt] = (Integer) result[cpt][1];
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

				// on recherche les majeurs si la promotion est M1 ou M2
			}
		});

		JButton btnNewButton = new JButton("Valider majeur");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// on sort les matières qu'il enseigne !

				Jlist_Matieres.removeAllItems();
				if (Jlist_Majeurs.getSelectedItem().equals("")) {
					Jlist_Matieres.addItem("");
				} else {

					Object[][] result;
					try {

						/*
						 * SELECT CNAME FROM teaches NATURAL JOIN teachers,
						 * courses WHERE TID = '1'
						 */

						result = BDD_exchange.getThings("SELECT CNAME, CID FROM teaches NATURAL JOIN courses WHERE TID ='"
								+ page_choix_notation_prof.utilisateur.getId()
								+ "' AND CPROMO ='"
								+ Jlist_Promotions.getSelectedItem() + "' AND CMAJEUR = '" + liste_ID_Majeurs[Jlist_Majeurs.getSelectedIndex()] + "' ;");
						liste_ID_Matieres = new int[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Matieres.addItem((String) result[cpt][0]);
							liste_ID_Matieres[cpt] = (Integer) result[cpt][1];
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});

		JLabel label_matieres = new JLabel("Matières");

		JButton bouton_Noter = new JButton("Notez !");
		bouton_Noter.addActionListener(new Event_Page_Choix_Matiere_Notation_Prof(fenetre, utilisateur, Jlist_Promotions, Jlist_Majeurs, Jlist_Matieres, page_choix_notation_prof));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(62)
										.addComponent(lblPageDeChoix,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addGap(63))
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(19)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblPromotion)
														.addComponent(
																lblMajeursiM)
														.addComponent(
																label_matieres))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																bouton_Rafraichir_Promotions)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.LEADING,
																				false)
																		.addComponent(
																				Jlist_Matieres,
																				0,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				bouton_Noter,
																				GroupLayout.PREFERRED_SIZE,
																				152,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				Jlist_Promotions,
																				0,
																				274,
																				Short.MAX_VALUE)
																		.addComponent(
																				bouton_valider_promotion,
																				GroupLayout.PREFERRED_SIZE,
																				224,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				Jlist_Majeurs,
																				0,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnNewButton,
																				GroupLayout.PREFERRED_SIZE,
																				225,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(45, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(19)
										.addComponent(lblPageDeChoix,
												GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(
												bouton_Rafraichir_Promotions)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																Jlist_Promotions,
																GroupLayout.PREFERRED_SIZE,
																30,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblPromotion))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(bouton_valider_promotion)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblMajeursiM)
														.addComponent(
																Jlist_Majeurs,
																GroupLayout.PREFERRED_SIZE,
																29,
																GroupLayout.PREFERRED_SIZE))
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
																				btnNewButton)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				Jlist_Matieres,
																				GroupLayout.PREFERRED_SIZE,
																				27,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																label_matieres))
										.addGap(18)
										.addComponent(bouton_Noter,
												GroupLayout.PREFERRED_SIZE, 47,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(143, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}

	public void setLabel_logs(JLabel label_logs) {
		Label_logs = label_logs;
	}

	public int[] getListe_ID_Majeurs() {
		return liste_ID_Majeurs;
	}

	public int[] getListe_ID_Matieres() {
		return liste_ID_Matieres;
	}
}
