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

import Controler.Event_Page_Creer_Promotion;
import Model.User;

public class Page_New_Promotion extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	public JTextField champ_Nom_Promotion;
	public JLabel Label_logs;

	public Page_New_Promotion(First_Window fenetre, User utilisateur) {
		
		fenetre_principale = fenetre;
				
		champ_Nom_Promotion = new JTextField();
		champ_Nom_Promotion.setText("Nom de la promotion");
		champ_Nom_Promotion.setColumns(10);
		
		JLabel lblNomPromo = new JLabel("Nom de la promotion");
		
		JLabel lblPageDeCration = new JLabel("Page de création d'une promotion");
		lblPageDeCration.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 17));
		
		JTextArea Label_Logs = new JTextArea();
		Label_Logs.setDragEnabled(true);
		Label_Logs.setAlignmentX(Component.LEFT_ALIGNMENT);
		Label_Logs.setWrapStyleWord(true);
		Label_Logs.setLineWrap(true);
		Label_Logs.setEditable(false);
		
		JButton bouton_Creer = new JButton("Creer_Promotion");
		bouton_Creer.addActionListener(new Event_Page_Creer_Promotion(
				fenetre, utilisateur, champ_Nom_Promotion, Label_Logs));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addComponent(lblPageDeCration, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(lblNomPromo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(champ_Nom_Promotion, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
									.addGap(56))
								.addComponent(Label_Logs, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
								.addComponent(bouton_Creer, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(lblPageDeCration, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(champ_Nom_Promotion, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomPromo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Label_Logs, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bouton_Creer, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public void setLabel_logs(JLabel label_logs) {
		Label_logs = label_logs;
	}

	public JTextField getChamp_Nom_Prof() {
		return champ_Nom_Promotion;
	}
}
