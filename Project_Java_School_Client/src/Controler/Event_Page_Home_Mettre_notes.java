package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.User;
import View.First_Window;

public class Event_Page_Home_Mettre_notes implements ActionListener{

	private First_Window fenetre_principale;
	private User utilisateur;

	public Event_Page_Home_Mettre_notes(First_Window fenetre, 
			User utilisateur) {
		fenetre_principale = fenetre;
		this.utilisateur = utilisateur;
	}

	public void actionPerformed(ActionEvent arg0) {

		if (utilisateur.getType_user().equals("TEACHER")) {
			fenetre_principale.getCard_Layout_Panel_center_Page_Choix_Matière_Notation_Prof();
		} else {
			JOptionPane.showMessageDialog(null, "Vous n'êtes pas un Professeur !",
					"ATTENTION", JOptionPane.ERROR_MESSAGE);
		}

	}

}
