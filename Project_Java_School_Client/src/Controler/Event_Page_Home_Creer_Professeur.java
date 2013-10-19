package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.User;
import View.First_Window;

public class Event_Page_Home_Creer_Professeur implements ActionListener {

	private First_Window fenetre_principale;
	private User utilisateur;

	public Event_Page_Home_Creer_Professeur(First_Window fenetre, 
			User utilisateur) {
		fenetre_principale = fenetre;
		this.utilisateur = utilisateur;
	}

	public void actionPerformed(ActionEvent arg0) {

		if (utilisateur.getType_user().equals("ADMIN")) {
			fenetre_principale.getCard_Layout_Panel_center_New_Prof();
		} else {
			JOptionPane.showMessageDialog(null, "Vous n'êtes pas un Admin !",
					"ATTENTION", JOptionPane.ERROR_MESSAGE);
		}

	}

}
