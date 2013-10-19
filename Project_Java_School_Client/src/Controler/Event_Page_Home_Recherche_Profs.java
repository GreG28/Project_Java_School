package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.User;
import View.First_Window;

public class Event_Page_Home_Recherche_Profs implements ActionListener {

	First_Window fenetre;
	User utilisateur;

	public Event_Page_Home_Recherche_Profs(First_Window fenetre, User utilisateur) {

		this.utilisateur = utilisateur;
		this.fenetre = fenetre;
	}

	public void actionPerformed(ActionEvent e) {
		fenetre.getCard_Layout_Panel_center_Page_Chercher_Profs();

	}

}
