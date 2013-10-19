package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.BDD_exchange;
import Model.User;
import View.First_Window;

public class Event_disconnect implements ActionListener {

	private User utilisateur;
	private First_Window fenetre;
	private int need_confirm;

	public Event_disconnect(User utilisateur, First_Window fenetre,
			int need_confirm) {
		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
		this.need_confirm = need_confirm;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		if (need_confirm == 1) {
			int reponse = JOptionPane.showConfirmDialog(null,
					"Voulez-vous vraiment vous déconnecter ?", "Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (reponse == JOptionPane.YES_OPTION) {
				String query_mise_a_jour;
				query_mise_a_jour = "UPDATE connection SET CONNECTED = '0' WHERE LOGIN = '"
						+ utilisateur.getLogin() + "' ;";
				System.out.println(query_mise_a_jour);
				int result = BDD_exchange.update_Query(query_mise_a_jour);
				System.out.println("nombre d'updates -> " + result);
				utilisateur.RemiseZero();
				fenetre.setTextField("Personne n'est connectée ");
				fenetre.getCard_Layout_Panel_center_Connect();
				fenetre.setLoginandPwdNull();
				// Quit l'application
			}
		} else {
			String query_mise_a_jour;
			query_mise_a_jour = "UPDATE connection SET CONNECTED = '0' WHERE LOGIN = '"
					+ utilisateur.getLogin() + "' ;";
			System.out.println(query_mise_a_jour);
			int result = BDD_exchange.update_Query(query_mise_a_jour);
			System.out.println("nombre d'updates -> " + result);
			utilisateur.RemiseZero();
			fenetre.setTextField("Personne n'est connectée ");
			fenetre.getCard_Layout_Panel_center_Connect();
			// Quit l'application
		}
	}

}
