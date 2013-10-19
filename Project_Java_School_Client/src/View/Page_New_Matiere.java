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

import Controler.Event_Page_Creer_Matiere;
import Model.BDD_exchange;
import Model.User;

public class Page_New_Matiere extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	public JTextField champ_Nom_Matiere;
	public JLabel Label_logs;
	JComboBox<String> Jlist_Promotions;
	JComboBox<String> Jlist_Majeurs;
	String[] liste_ID_Majeurs;

	public Page_New_Matiere(First_Window fenetre, User utilisateur) {

		fenetre_principale = fenetre;

		champ_Nom_Matiere = new JTextField();
		champ_Nom_Matiere.setText("Nom de la matière");
		champ_Nom_Matiere.setColumns(10);

		JLabel lblNom = new JLabel("Nom du cours");

		JLabel lblPromotion = new JLabel("Promotion");

		JLabel lblPageDeCration = new JLabel("Page de création d'une matière");
		lblPageDeCration.setFont(new Font("Source Code Pro Semibold",
				Font.PLAIN, 17));

		JTextArea Label_Logs = new JTextArea();
		Label_Logs.setDragEnabled(true);
		Label_Logs.setAlignmentX(Component.LEFT_ALIGNMENT);
		Label_Logs.setWrapStyleWord(true);
		Label_Logs.setLineWrap(true);
		Label_Logs.setEditable(false);

		Jlist_Promotions = new JComboBox<String>();
		Jlist_Promotions.addItem("");

		Jlist_Majeurs = new JComboBox<String>();
		Jlist_Majeurs.addItem("");

		JLabel lblMajeursiM = new JLabel("Majeur (si M1 ou M2)");

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
						liste_ID_Majeurs = new String[result.length];
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							Jlist_Majeurs.addItem((String) result[cpt][1]);
							liste_ID_Majeurs[cpt] = result[cpt][0].toString();
						}
						System.out.println(" liste_ID_Majeurs.lenght ->" + liste_ID_Majeurs.length);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
				{
					Jlist_Majeurs.removeAllItems();
					Jlist_Majeurs.addItem("");
				}

				// on recherche les majeurs si la promotion est M1 ou M2
			}
		});

		JButton bouton_Rafraichir_Promotions = new JButton(
				"Rafraichir promotions ...");
		bouton_Rafraichir_Promotions.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Jlist_Promotions.removeAllItems();
				Object[][] result;
				try {
					result = BDD_exchange
							.getThings("SELECT PNAME FROM promotions ;");
					for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
						Jlist_Promotions.addItem((String) result[cpt][0]);
					}
				} catch (SQLException e_1) {
					e_1.printStackTrace();
				}
				// on recherche toutes les promotions dans la table promotions !
			}
		});

		JButton bouton_Creer = new JButton("Creer_Matière");
		bouton_Creer.addActionListener(new Event_Page_Creer_Matiere(fenetre, utilisateur, champ_Nom_Matiere, Jlist_Promotions, Jlist_Majeurs, Label_Logs, this));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(lblPageDeCration, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNom)
								.addComponent(lblPromotion))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(bouton_Rafraichir_Promotions)
								.addComponent(champ_Nom_Matiere)
								.addComponent(Jlist_Promotions, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
								.addComponent(bouton_valider_promotion, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(64)
					.addComponent(lblMajeursiM)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Label_Logs, Alignment.TRAILING)
						.addComponent(Jlist_Majeurs, 0, 482, Short.MAX_VALUE)
						.addComponent(bouton_Creer, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
					.addGap(85))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPageDeCration, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(champ_Nom_Matiere, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(bouton_Rafraichir_Promotions)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Jlist_Promotions, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPromotion))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bouton_valider_promotion)
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(Jlist_Majeurs, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMajeursiM))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Label_Logs, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(bouton_Creer, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	public void setLabel_logs(JLabel label_logs) {
		Label_logs = label_logs;
	}

	public JTextField getChamp_Nom_Prof() {
		return champ_Nom_Matiere;
	}
	
	public String[] getListe_ID_Majeurs() {
		return liste_ID_Majeurs;
	}

}
