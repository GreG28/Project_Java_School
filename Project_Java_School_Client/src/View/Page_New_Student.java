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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import Controler.Event_Page_Creer_Student;
import Model.BDD_exchange;
import Model.User;

public class Page_New_Student extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	public JLabel Label_logs;
	JComboBox<String> Jlist_Promotion;
	JComboBox<String> Jlist_Majeur;
	int[] liste_ID_Majeur;
	private JTextField champ_nom;
	private JTextField champ_prenom;
	public Page_New_Student page_new_student;

	public Page_New_Student(First_Window fenetre, User utilisateur) {

		fenetre_principale = fenetre;
		page_new_student = this;

		JLabel lblNomDeLtudiant = new JLabel("Nom de l'étudiant");

		JLabel lblPage_Creer_Student = new JLabel("Page de création d'étudiant");
		lblPage_Creer_Student.setHorizontalAlignment(SwingConstants.CENTER);
		lblPage_Creer_Student.setFont(new Font("Source Code Pro Semibold",
				Font.PLAIN, 17));

		JTextArea Label_Logs = new JTextArea();
		Label_Logs.setDragEnabled(true);
		Label_Logs.setAlignmentX(Component.LEFT_ALIGNMENT);
		Label_Logs.setWrapStyleWord(true);
		Label_Logs.setLineWrap(true);
		Label_Logs.setEditable(false);

		Jlist_Majeur = new JComboBox<String>();
		Jlist_Majeur.addItem("");

		Jlist_Promotion = new JComboBox<String>();
		Jlist_Promotion.addItem("");

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
					Jlist_Majeur.addItem("");
				}
			}
		});
		btnSelectionnerPromotion.setToolTipText("Selectionner Promotion");

		JLabel lblMajeur = new JLabel("Majeur");

		champ_nom = new JTextField();
		champ_nom.setText("nom de l'eleve ");
		champ_nom.setColumns(10);

		champ_prenom = new JTextField();
		champ_prenom.setText("prenom de l'eleve ");
		champ_prenom.setColumns(10);

		JLabel lblPrnomDeLtudiant = new JLabel("Prénom de l'étudiant");

		JButton bouton_refresh_promotions = new JButton(
				"Rafraichir les promotions");
		bouton_refresh_promotions.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Jlist_Promotion.removeAllItems();
				// on ressort les promotions !!
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

		JButton bouton_Creer = new JButton("Créer l'étudiant");
		bouton_Creer.setToolTipText("Créer l'étudiant");
		bouton_Creer.addActionListener(new Event_Page_Creer_Student(fenetre,
				utilisateur, champ_nom, champ_prenom, Jlist_Promotion,
				Jlist_Majeur,Label_Logs,
				page_new_student));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(47)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
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
																										.addGap(47)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING)
																														.addComponent(
																																lblNomDeLtudiant)
																														.addComponent(
																																lblPrnomDeLtudiant))
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																champ_nom)
																														.addComponent(
																																champ_prenom,
																																GroupLayout.DEFAULT_SIZE,
																																225,
																																Short.MAX_VALUE)))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(102)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING)
																														.addComponent(
																																lblMajeur)
																														.addComponent(
																																lblPromotion))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING,
																																false)
																														.addComponent(
																																Jlist_Promotion,
																																GroupLayout.PREFERRED_SIZE,
																																226,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																btnSelectionnerPromotion,
																																GroupLayout.PREFERRED_SIZE,
																																226,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																Jlist_Majeur,
																																GroupLayout.PREFERRED_SIZE,
																																226,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																bouton_Creer,
																																GroupLayout.PREFERRED_SIZE,
																																226,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																bouton_refresh_promotions,
																																GroupLayout.PREFERRED_SIZE,
																																226,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																Label_Logs,
																																GroupLayout.PREFERRED_SIZE,
																																226,
																																GroupLayout.PREFERRED_SIZE))))
																		.addGap(76,
																				76,
																				Short.MAX_VALUE))
														.addComponent(
																lblPage_Creer_Student,
																GroupLayout.DEFAULT_SIZE,
																456,
																Short.MAX_VALUE))));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(lblPage_Creer_Student,
												GroupLayout.PREFERRED_SIZE, 31,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblNomDeLtudiant)
														.addComponent(
																champ_nom,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE))
										.addGap(10)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblPrnomDeLtudiant)
														.addComponent(
																champ_prenom,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				bouton_refresh_promotions)
																		.addGap(3)
																		.addComponent(
																				Jlist_Promotion,
																				GroupLayout.PREFERRED_SIZE,
																				27,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																lblPromotion))
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
										.addComponent(Label_Logs,
												GroupLayout.PREFERRED_SIZE, 67,
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

}
