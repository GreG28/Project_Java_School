package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.User;
import View.First_Window;

public class Event_Page_Home_Voir_notes implements ActionListener{

	First_Window fenetre_principale;
	User utilisateur;

	public Event_Page_Home_Voir_notes(First_Window fenetre, User utilisateur) {
		fenetre_principale = fenetre;
		this.utilisateur = utilisateur;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (utilisateur.getType_user().equals("STUDENT")) {
			fenetre_principale.getCard_Layout_Panel_center_Page_Notation_Eleve();
		} else {
			JOptionPane.showMessageDialog(null, "Vous n'êtes pas un Elève !",
					"ATTENTION", JOptionPane.ERROR_MESSAGE);
		}
	}

}
