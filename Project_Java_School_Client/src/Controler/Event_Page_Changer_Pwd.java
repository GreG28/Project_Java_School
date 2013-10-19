package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import Model.BDD_exchange;
import Model.Codage;
import Model.User;
import View.First_Window;

public class Event_Page_Changer_Pwd implements ActionListener {

	private String old_mdp_string;
	private String new_mdp_string;
	private String confirm_mdp_string;
	First_Window fenetre_principale;
	private JPasswordField old_pwd;
	private JPasswordField new_pwd;
	private JPasswordField confirmation_pwd;
	private User utilisateur;

	public Event_Page_Changer_Pwd(First_Window fenetre, JPasswordField old_pwd,
			JPasswordField new_pwd, JPasswordField confirmation_pwd,
			User utilisateur) {

		fenetre_principale = fenetre;
		this.old_pwd = old_pwd;
		this.new_pwd = new_pwd;
		this.confirmation_pwd = confirmation_pwd;
		this.utilisateur = utilisateur;

	}

	public void actionPerformed(ActionEvent arg0) {

		old_mdp_string = new String(old_pwd.getPassword());
		new_mdp_string = new String(new_pwd.getPassword());
		confirm_mdp_string = new String(confirmation_pwd.getPassword());

		try {
			old_mdp_string = Codage.MD5(old_mdp_string);
			new_mdp_string = Codage.MD5(new_mdp_string);
			confirm_mdp_string = Codage.MD5(confirm_mdp_string);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (new_mdp_string.equals(confirm_mdp_string)) {
			String query_login;
			query_login = "SELECT USER_TYPE FROM connection WHERE LOGIN = '"
					+ utilisateur.getLogin() + "' AND PASSWORD ='"
					+ old_mdp_string + "';";
			Object[][] result = null;
			try {
				result = BDD_exchange.getThings(query_login);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (result.length != 0) {
				String query_mise_a_jour;
				query_mise_a_jour = "UPDATE connection SET PASSWORD = '"
						+ new_mdp_string + "' WHERE LOGIN = '"
						+ utilisateur.getLogin() + "' ;";

				if (BDD_exchange.update_Query(query_mise_a_jour) != 0) {
					JOptionPane.showMessageDialog(fenetre_principale,
							" Nouveau mot de passe enregistré ! ");
					fenetre_principale.getCard_Layout_Panel_center_Home();

				}
			} else {
				JOptionPane.showMessageDialog(fenetre_principale,
						" Mauvais mot de passe ...");
			}
		} else {
			JOptionPane.showMessageDialog(fenetre_principale,
					" Mauvaise Confirmation ...");
		}

	}
}
