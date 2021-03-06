package View;

import java.awt.Component;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controler.Event_Page_Creer_Majeure;
import Model.User;

public class Page_New_Majeure extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	public JTextField champ_Nom_Majeur;
	public JTextField champ_Description;
	public JLabel Label_logs;

	public Page_New_Majeure(First_Window fenetre, User utilisateur) {
		
		fenetre_principale = fenetre;
				
		champ_Nom_Majeur = new JTextField();
		champ_Nom_Majeur.setText("Nom de la majeur");
		champ_Nom_Majeur.setColumns(10);
		
		champ_Description = new JTextField();
		champ_Description.setText("Description de la majeur");
		champ_Description.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Nom de la majeure");
		
		JLabel lblNom = new JLabel("Description de la majeur");
		
		JLabel lblPageDeCration = new JLabel("Page de cr�ation d'une majeure");
		lblPageDeCration.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 17));
		
		JTextArea Label_Logs = new JTextArea();
		Label_Logs.setDragEnabled(true);
		Label_Logs.setAlignmentX(Component.LEFT_ALIGNMENT);
		Label_Logs.setWrapStyleWord(true);
		Label_Logs.setLineWrap(true);
		Label_Logs.setEditable(false);
		
		JButton bouton_Creer = new JButton("Creer_Majeur");
		bouton_Creer.addActionListener(new Event_Page_Creer_Majeure(
				fenetre, utilisateur, champ_Nom_Majeur, champ_Description, Label_Logs));

		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addComponent(lblPageDeCration, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPrnom)
								.addComponent(lblNom))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(champ_Nom_Majeur, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addComponent(champ_Description, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addComponent(Label_Logs, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
								.addComponent(bouton_Creer, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(4, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(lblPageDeCration, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(champ_Nom_Majeur, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrnom))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(champ_Description, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Label_Logs, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bouton_Creer, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public void setLabel_logs(JLabel label_logs) {
		Label_logs = label_logs;
	}

	public JTextField getChamp_Nom_Prof() {
		return champ_Nom_Majeur;
	}

	public JTextField getChamp_Prenom_Prof() {
		return champ_Description;
	}
}
