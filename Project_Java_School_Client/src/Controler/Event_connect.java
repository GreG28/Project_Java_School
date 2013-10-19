package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.User;
import View.First_Window;

public class Event_connect implements ActionListener {

	private User utilisateur;
	private First_Window fenetre;

	public Event_connect(User utilisateur, First_Window first_Window) {
		this.utilisateur = utilisateur;
		this.fenetre = first_Window;
	}

	public void actionPerformed(ActionEvent arg0) {
		if(utilisateur.getLogin().equals(""))
		{
			fenetre.getCard_Layout_Panel_center_Connect();
		}
		else
		{
			JOptionPane.showMessageDialog(fenetre,
					" Vous êtes déjà connecté ! ");
		}
	}

}
