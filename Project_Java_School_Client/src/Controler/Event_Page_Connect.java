package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.BDD_exchange;
import Model.Codage;
import Model.User;
import View.First_Window;

public class Event_Page_Connect implements ActionListener {

	String Login;
	String Mdp;
	String Mdp_MD5;
	First_Window fenetre_principale;
	JTextField login;
	JPasswordField mot_de_passe;
	User utilisateur;

	public Event_Page_Connect(First_Window fenetre, JTextField login,
			JPasswordField mot_de_passe, User utilisateur) {

		fenetre_principale = fenetre;
		this.login = login;
		this.mot_de_passe = mot_de_passe;
		this.utilisateur = utilisateur;

	}

	public void actionPerformed(ActionEvent arg0) {

		Login = login.getText();
		Mdp = new String(mot_de_passe.getPassword());
		try {
			Mdp_MD5 = Codage.MD5(Mdp);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query_login;
		query_login = "SELECT USER_TYPE , LOGIN FROM connection WHERE LOGIN = '"
				+ Login + "';";
		Object[][] result = null;
		try {
			result = BDD_exchange.getThings(query_login);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result.length != 0) {
			String user_type = (String) result[0][0];

			if (user_type.equals("STUDENT")) {
				query_login = "SELECT * FROM connection NATURAL JOIN students WHERE LOGIN = '"
						+ Login + "'AND USER = students.SID ;";
				System.out.println(" fouillardernest -> " + query_login + "  -> |" + Mdp_MD5 + "|");
				result = null;
				try {
					result = BDD_exchange.getThings(query_login);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (user_type.equals("TEACHER")) {
				query_login = "SELECT * FROM connection NATURAL JOIN teachers WHERE LOGIN = '"
						+ Login + "' AND USER = teachers.TID ;";
				result = null;
				try {
					result = BDD_exchange.getThings(query_login);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				query_login = "SELECT * FROM connection WHERE LOGIN = '"
						+ Login + "';";
				result = null;
				try {
					result = BDD_exchange.getThings(query_login);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// donc on a matcher un login correspondant
			// on verifie donc le mot de passe !

			if (Mdp_MD5.equals((String) result[0][1])) {
				if (result[0][4].toString().equals("0")) {
					JOptionPane.showMessageDialog(fenetre_principale,
							" Bon mot de passe ! ");
					fenetre_principale.setTextField(Login + " est connectée ");
					fenetre_principale.getCard_Layout_Panel_center_Home();

					String query_mise_a_jour;
					query_mise_a_jour = "UPDATE connection SET CONNECTED = '1' WHERE LOGIN = '"
							+ Login + "' ;";
					BDD_exchange.update_Query(query_mise_a_jour);
					// on change les donnée du user !

					utilisateur.setLogin(Login);
					utilisateur.setType_user(user_type);
					utilisateur.setId(result[0][2].toString());

					if (user_type.equals("STUDENT")) {

						utilisateur.setName(result[0][6].toString());
						utilisateur.setFname(result[0][7].toString());
						utilisateur.setPromo(result[0][8].toString());
						if (result[0][9] != null) {
							utilisateur.setMajeur(result[0][9].toString());
						} else {
							utilisateur.setMajeur("null");
						}
						if (result[0][10] != null) {
							utilisateur.setTuteur(result[0][10].toString());
						} else {
							utilisateur.setMajeur("null");
						}
						fenetre_principale.setTextField(utilisateur.getName()
								+ " " + utilisateur.getFname()
								+ " est connectée ");

					} else if (user_type.equals("TEACHER")) {
						utilisateur.setName(result[0][6].toString());
						utilisateur.setFname(result[0][7].toString());

						fenetre_principale.setTextField(utilisateur.getName()
								+ " " + utilisateur.getFname()
								+ " est connectée ");

					}
				} else {
					JOptionPane.showMessageDialog(fenetre_principale,
							"Déjà connecté sur une autre instance ! ",
							"ERREUR", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(fenetre_principale,
						" Mauvais login ou mot de passe ! ");
				fenetre_principale.setTextField("Personne n'est connecté ");
			}
		} else {
			JOptionPane.showMessageDialog(fenetre_principale,
					" Mauvais login ou mot de passe ! ");
			fenetre_principale.setTextField("Personne n'est connecté ");
		}
	}

}
