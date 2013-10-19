package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.User;
import View.First_Window;

public class Event_Page_Principale implements ActionListener {

	private User utilisateur;
	private First_Window fenetre;

	public Event_Page_Principale(First_Window fenetre, User utilisateur) {
		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
	}

	public void actionPerformed(ActionEvent e) {

		if (utilisateur.getLogin().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Vous n'êtes pas encore connécté !", "ATTENTION",
					JOptionPane.ERROR_MESSAGE);
		} else {
			int reponse = JOptionPane.showConfirmDialog(null,
					"Voulez-vous vraiment quitter la page en cours ?",
					"Confirmation", JOptionPane.YES_NO_OPTION);
			if (reponse == JOptionPane.YES_OPTION) {
				fenetre.getCard_Layout_Panel_center_Home();
			}
		}
	}
}
