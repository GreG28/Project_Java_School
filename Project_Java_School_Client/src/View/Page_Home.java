package View;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controler.Event_Page_Home_Changer_Mdp;
import Controler.Event_Page_Home_Creer_Etudiant;
import Controler.Event_Page_Home_Creer_Majeure;
import Controler.Event_Page_Home_Creer_Professeur;
import Controler.Event_Page_Home_Creer_Promotion;
import Controler.Event_Page_Home_Creer_cours;
import Controler.Event_Page_Home_Inscrire_Etudiant_Cours;
import Controler.Event_Page_Home_Mettre_notes;
import Controler.Event_Page_Home_Prof_cours;
import Controler.Event_Page_Home_Recherche_Eleves;
import Controler.Event_Page_Home_Recherche_Profs;
import Controler.Event_Page_Home_Voir_notes;
import Controler.Event_Page_Home_Voir_toutes_notes;
import Model.User;

public class Page_Home extends JPanel {

	private static final long serialVersionUID = 1L;
	public First_Window fenetre_principale;

	public Page_Home(First_Window fenetre, User utilisateur) {
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setBounds(new Rectangle(0, 0, 350, 250));
		setAutoscrolls(true);

		fenetre_principale = fenetre;

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(10, 11, 68, 14);

		JButton bouton_inscrire_etudiant = new JButton("Inscrire un étudiant");
		bouton_inscrire_etudiant.setBounds(10, 27, 235, 35);
		bouton_inscrire_etudiant.setBackground(Color.CYAN);
		bouton_inscrire_etudiant.setForeground(Color.BLACK);
		bouton_inscrire_etudiant
				.addActionListener(new Event_Page_Home_Creer_Etudiant(fenetre,
						utilisateur));
		bouton_inscrire_etudiant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bouton_inscrire_etudiant.setToolTipText("Inscrire un étudiant");
		bouton_inscrire_etudiant.setMinimumSize(new Dimension(25, 23));
		bouton_inscrire_etudiant.setIconTextGap(5);
		bouton_inscrire_etudiant
				.setHorizontalTextPosition(SwingConstants.CENTER);
		bouton_inscrire_etudiant.setHideActionText(true);
		bouton_inscrire_etudiant.setAutoscrolls(true);

		JButton bouton_incrire_etudiant_cours = new JButton(
				"Inscrire un étudiant à un cours");
		bouton_incrire_etudiant_cours.setBounds(276, 119, 235, 35);
		bouton_incrire_etudiant_cours.setBackground(Color.CYAN);
		bouton_incrire_etudiant_cours
				.addActionListener(new Event_Page_Home_Inscrire_Etudiant_Cours(
						fenetre, utilisateur));
		bouton_incrire_etudiant_cours
				.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bouton_incrire_etudiant_cours
				.setHorizontalTextPosition(SwingConstants.CENTER);
		bouton_incrire_etudiant_cours
				.setToolTipText("Inscrire un étudiant à un cours");
		bouton_incrire_etudiant_cours.setMinimumSize(new Dimension(25, 23));

		JButton bouton_creer_nouveau_cours = new JButton(
				"Créer un nouveau cours");
		bouton_creer_nouveau_cours.setBounds(169, 73, 203, 35);
		bouton_creer_nouveau_cours.setForeground(Color.BLACK);
		bouton_creer_nouveau_cours.setBackground(Color.CYAN);
		bouton_creer_nouveau_cours
				.addActionListener(new Event_Page_Home_Creer_cours(fenetre,
						utilisateur));
		bouton_creer_nouveau_cours.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bouton_creer_nouveau_cours
				.setHorizontalTextPosition(SwingConstants.CENTER);
		bouton_creer_nouveau_cours.setToolTipText("Créer un nouveau cours");
		bouton_creer_nouveau_cours.setMinimumSize(new Dimension(25, 23));

		JButton bouton_associer_professeur_cours = new JButton(
				"Associer un professeur à un cours");
		bouton_associer_professeur_cours.setBounds(10, 119, 260, 35);
		bouton_associer_professeur_cours.setBackground(Color.CYAN);
		bouton_associer_professeur_cours
				.addActionListener(new Event_Page_Home_Prof_cours(fenetre,
						utilisateur));
		bouton_associer_professeur_cours
				.setToolTipText("Associer un professeur à un cours");
		bouton_associer_professeur_cours.setFont(new Font("Tahoma", Font.PLAIN,
				15));

		JLabel lblProfesseur = new JLabel("Professeur");
		lblProfesseur.setBounds(10, 165, 68, 14);

		JButton bouton_mettre_notes = new JButton("Mettre des notes");
		bouton_mettre_notes.setBounds(10, 180, 163, 35);
		bouton_mettre_notes.setBackground(Color.LIGHT_GRAY);
		bouton_mettre_notes.addActionListener(new Event_Page_Home_Mettre_notes(
				fenetre, utilisateur));
		bouton_mettre_notes.setToolTipText("Mettre des notes");
		bouton_mettre_notes.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblElve = new JLabel("Elève");
		lblElve.setBounds(10, 223, 68, 14);

		JButton bouton_voir_les_notes = new JButton("Voir les notes");
		bouton_voir_les_notes.setBounds(10, 243, 134, 35);
		bouton_voir_les_notes.setBackground(Color.GREEN);
		bouton_voir_les_notes.addActionListener(new Event_Page_Home_Voir_notes(
				fenetre, utilisateur));
		bouton_voir_les_notes.setToolTipText("Voir les notes");
		bouton_voir_les_notes.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton bouton_inscrire_professeur = new JButton(
				"Inscrire un professeur");
		bouton_inscrire_professeur.setBounds(255, 28, 189, 33);
		bouton_inscrire_professeur.setForeground(Color.BLACK);
		bouton_inscrire_professeur.setBackground(Color.CYAN);
		bouton_inscrire_professeur
				.addActionListener(new Event_Page_Home_Creer_Professeur(
						fenetre, utilisateur));
		bouton_inscrire_professeur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bouton_inscrire_professeur.setToolTipText("Inscrire un professeur");
		bouton_inscrire_professeur.setMinimumSize(new Dimension(25, 23));

		JButton bouton_creer_promotion = new JButton("Créer Promotion");
		bouton_creer_promotion.setBounds(378, 74, 153, 33);
		bouton_creer_promotion.setForeground(Color.BLACK);
		bouton_creer_promotion.setBackground(Color.CYAN);
		bouton_creer_promotion
				.addActionListener(new Event_Page_Home_Creer_Promotion(fenetre,
						utilisateur));
		bouton_creer_promotion.setToolTipText("Créer Promotion");
		bouton_creer_promotion.setMinimumSize(new Dimension(25, 23));
		bouton_creer_promotion.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton bouton_creer_majeure = new JButton("Créer Majeur");
		bouton_creer_majeure.setBounds(10, 74, 153, 33);
		bouton_creer_majeure.setForeground(Color.BLACK);
		bouton_creer_majeure.setBackground(Color.CYAN);
		bouton_creer_majeure
				.addActionListener(new Event_Page_Home_Creer_Majeure(fenetre,
						utilisateur));
		bouton_creer_majeure.setToolTipText("Créer Majeur");
		bouton_creer_majeure.setMinimumSize(new Dimension(25, 23));
		bouton_creer_majeure.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel label_Tout_Le_Monde = new JLabel("Tout le monde");
		label_Tout_Le_Monde.setBounds(10, 289, 110, 14);

		JButton bouton_Changer_MDP = new JButton("Changer MDP");
		bouton_Changer_MDP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bouton_Changer_MDP.setBounds(10, 309, 134, 33);
		bouton_Changer_MDP.addActionListener(new Event_Page_Home_Changer_Mdp(
				fenetre, utilisateur));
		bouton_Changer_MDP.setBackground(Color.ORANGE);

		JButton bouton_Recherche_students = new JButton("Recherche Etudiants");
		bouton_Recherche_students.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bouton_Recherche_students.setBounds(150, 309, 189, 33);
		bouton_Recherche_students
				.addActionListener(new Event_Page_Home_Recherche_Eleves(
						fenetre, utilisateur));
		bouton_Recherche_students.setBackground(Color.ORANGE);

		JButton bouton_recherche_professeurs = new JButton(
				"Recherche Professeur");
		bouton_recherche_professeurs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bouton_recherche_professeurs.setBounds(10, 353, 194, 33);
		bouton_recherche_professeurs
				.addActionListener(new Event_Page_Home_Recherche_Profs(fenetre,
						utilisateur));
		bouton_recherche_professeurs.setBackground(Color.ORANGE);
		setLayout(null);
		
		JButton bouton_toutes_notes_prof = new JButton("Voir les notes de vos matières");
		bouton_toutes_notes_prof.addActionListener(new Event_Page_Home_Voir_toutes_notes(fenetre, utilisateur));
		bouton_toutes_notes_prof.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bouton_toutes_notes_prof.setBounds(183, 180, 237, 35);
		bouton_toutes_notes_prof.setBackground(Color.LIGHT_GRAY);
		
		
		add(bouton_toutes_notes_prof);
		add(lblAdmin);
		add(bouton_creer_majeure);
		add(bouton_creer_nouveau_cours);
		add(bouton_creer_promotion);
		add(bouton_inscrire_etudiant);
		add(bouton_inscrire_professeur);
		add(bouton_associer_professeur_cours);
		add(bouton_incrire_etudiant_cours);
		add(lblElve);
		add(bouton_voir_les_notes);
		add(lblProfesseur);
		add(bouton_mettre_notes);
		add(label_Tout_Le_Monde);
		add(bouton_Changer_MDP);
		add(bouton_Recherche_students);
		add(bouton_recherche_professeurs);

	}
}
