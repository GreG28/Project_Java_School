package View;

import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import Controler.Event_Page_Changer_Pwd;
import Model.User;

public class Page_Change_Pwd extends JPanel {

	private static final long serialVersionUID = 1L;
	First_Window fenetre_principale;
	private JPasswordField old_mdp;
	private JPasswordField new_pwd;
	private JPasswordField confirmation_mdp;

	public Page_Change_Pwd(First_Window fenetre, User utilisateur) {

		fenetre_principale = fenetre;
		setLayout(null);

		JLabel label_ancien_mot_de_passe = new JLabel("Ancien mot de passe");
		label_ancien_mot_de_passe.setHorizontalAlignment(SwingConstants.RIGHT);
		label_ancien_mot_de_passe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_ancien_mot_de_passe.setBounds(48, 54, 152, 30);
		add(label_ancien_mot_de_passe);

		JLabel lblNouveauMotDe = new JLabel("Nouveau mot de passe");
		lblNouveauMotDe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNouveauMotDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNouveauMotDe.setBounds(10, 97, 190, 30);
		add(lblNouveauMotDe);

		JLabel lblConfirmationMotDe = new JLabel("Confirmation mot de passe");
		lblConfirmationMotDe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmationMotDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmationMotDe.setBounds(20, 142, 180, 30);
		add(lblConfirmationMotDe);

		old_mdp = new JPasswordField();
		old_mdp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		// mot_de_passe.setText("root");
		old_mdp.setText("motdepasse");
		old_mdp.setHorizontalAlignment(SwingConstants.CENTER);
		label_ancien_mot_de_passe.setLabelFor(old_mdp);
		old_mdp.setBounds(205, 55, 152, 33);
		add(old_mdp);

		new_pwd = new JPasswordField();
		new_pwd.setText("motdepasse");
		new_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		new_pwd.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		new_pwd.setBounds(205, 98, 152, 33);
		add(new_pwd);

		confirmation_mdp = new JPasswordField();
		confirmation_mdp.setText("motdepasse");
		confirmation_mdp.setHorizontalAlignment(SwingConstants.CENTER);
		confirmation_mdp
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		confirmation_mdp.setBounds(205, 143, 152, 33);
		add(confirmation_mdp);

		JButton bouton_changer = new JButton("Changer de mot de passe");
		bouton_changer.setBounds(184, 186, 189, 47);
		add(bouton_changer);
		bouton_changer.addActionListener(new Event_Page_Changer_Pwd(fenetre, old_mdp, new_pwd, confirmation_mdp, utilisateur));
	}
}
