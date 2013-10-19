package View;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controler.Event_Page_Creer_Prof;
import Model.User;
import java.awt.Component;

public class Page_New_Prof extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	public JTextField champ_Nom_Prof;
	public JTextField champ_Prenom_Prof;
	public JLabel Label_logs;

	public Page_New_Prof(First_Window fenetre, User utilisateur) {
		
		fenetre_principale = fenetre;
				
		champ_Nom_Prof = new JTextField();
		champ_Nom_Prof.setText("Nom du professeur");
		champ_Nom_Prof.setColumns(10);
		
		champ_Prenom_Prof = new JTextField();
		champ_Prenom_Prof.setText(" Prénom du professeur");
		champ_Prenom_Prof.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Nom");
		
		JLabel lblNom = new JLabel("Prénom");
		
		JLabel lblPageDeCration = new JLabel("Page de création d'un professeur");
		lblPageDeCration.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 17));
		
		JTextArea Label_Logs = new JTextArea();
		Label_Logs.setDragEnabled(true);
		Label_Logs.setAlignmentX(Component.LEFT_ALIGNMENT);
		Label_Logs.setWrapStyleWord(true);
		Label_Logs.setLineWrap(true);
		Label_Logs.setEditable(false);
		
		JButton bouton_Creer = new JButton("Creer_Professeur");
		bouton_Creer.addActionListener(new Event_Page_Creer_Prof(
				fenetre, utilisateur, champ_Nom_Prof, champ_Prenom_Prof, Label_Logs));

		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addComponent(lblPageDeCration, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
					.addGap(57))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(107)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPrnom)
						.addComponent(lblNom))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(bouton_Creer, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(champ_Nom_Prof, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addComponent(champ_Prenom_Prof, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
								.addComponent(Label_Logs, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
							.addGap(33))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(lblPageDeCration, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(champ_Nom_Prof, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrnom))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(champ_Prenom_Prof, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Label_Logs, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(bouton_Creer, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
	}

	public void setLabel_logs(JLabel label_logs) {
		Label_logs = label_logs;
	}

	public JTextField getChamp_Nom_Prof() {
		return champ_Nom_Prof;
	}

	public JTextField getChamp_Prenom_Prof() {
		return champ_Prenom_Prof;
	}
}
