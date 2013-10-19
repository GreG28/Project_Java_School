package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import Controler.Event_Page_Principale;
import Controler.Event_connect;
import Controler.Event_disconnect;
import Model.User;

public class First_Window extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	private CardLayout card_Layout_Panel_center;
	private JPanel panel;
	private User utilisateur;
	private First_Window fenetre_first;
	private Page_Notation_Prof page_notation_prof;
	private Page_Notation_Eleve page_notation_eleve;
	private Page_Notes_Prof pages_notes_prof;
	private Page_Connect page_connect;

	public First_Window(User utilisateur) {

		this.utilisateur = utilisateur;
		this.fenetre_first = this;
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // JFrame.EXIT_ON_CLOSE
		this.addWindowListener(new WindowListener() {

			public void windowClosing(WindowEvent arg0) {
				// Ici on demande la confirmation de fermeture de l'application
				int reponse = JOptionPane
						.showConfirmDialog(
								null,
								"Voulez-vous vraiment quitter toutes les données non sauvguardé seront perdu ?",
								"Confirmation", JOptionPane.YES_NO_OPTION);
				if (reponse == JOptionPane.YES_OPTION) {
					// On met le l
					Event_disconnect disconnect = new Event_disconnect(
							fenetre_first.utilisateur, fenetre_first, 0);
					disconnect.actionPerformed(null);
					// Quit l'application
					System.exit(0);
				}
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowOpened(WindowEvent e) {

			}
		});

		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);

		JButton button_connect = new JButton("Connect");
		button_connect.addActionListener(new Event_connect(utilisateur, this));
		toolBar.add(button_connect);

		JButton button_disconnect = new JButton("Disconnect");
		button_disconnect.addActionListener(new Event_disconnect(utilisateur,
				this, 1));
		toolBar.add(button_disconnect);

		JButton button_principal = new JButton("Page principale");
		button_principal.addActionListener(new Event_Page_Principale(
				fenetre_first, utilisateur));
		toolBar.add(button_principal);

		textField = new JTextField(" Personne n'est connecté ");
		textField.setEditable(false);
		textField.setColumns(5);
		toolBar.add(textField);

		panel = new JPanel();
		panel.setAutoscrolls(true);
		contentPane.add(panel, BorderLayout.CENTER);
		card_Layout_Panel_center = new CardLayout(0, 0);
		panel.setLayout(card_Layout_Panel_center);

		page_connect = new Page_Connect(this, utilisateur);
		page_connect.setAutoscrolls(true);
		panel.add(page_connect, "Page_Connect");

		Page_Home page_Home = new Page_Home(this, utilisateur);
		page_Home.setAutoscrolls(true);
		panel.add(page_Home, "Page_Home");
		Page_Marks page_Marks = new Page_Marks(this, utilisateur);
		page_Marks.setAutoscrolls(true);
		panel.add(page_Marks, "Page_Marks");
		Page_New_Prof page_New_Prof = new Page_New_Prof(this, utilisateur);
		page_New_Prof.setAutoscrolls(true);
		panel.add(page_New_Prof, "Page_New_Prof");
		Page_New_Promotion page_New_Promotion = new Page_New_Promotion(this, utilisateur);
		page_New_Promotion.setAutoscrolls(true);
		panel.add(page_New_Promotion,
				"Page_New_Promotion");
		Page_New_Majeure page_New_Majeure = new Page_New_Majeure(this, utilisateur);
		page_New_Majeure.setAutoscrolls(true);
		panel.add(page_New_Majeure, "Page_New_Majeure");
		Page_New_Matiere page_New_Matiere = new Page_New_Matiere(this, utilisateur);
		page_New_Matiere.setAutoscrolls(true);
		panel.add(page_New_Matiere, "Page_New_Matiere");
		Page_Assoc_Prof_Cour page_Assoc_Prof_Cour = new Page_Assoc_Prof_Cour(this, utilisateur);
		page_Assoc_Prof_Cour.setAutoscrolls(true);
		panel.add(page_Assoc_Prof_Cour,
				"Page_Assoc_Prof_Cour");
		Page_New_Student page_New_Student = new Page_New_Student(this, utilisateur);
		page_New_Student.setAutoscrolls(true);
		panel.add(page_New_Student, "Page_New_Student");
		Page_Assoc_Student_Cour page_Assoc_Student_Cour = new Page_Assoc_Student_Cour(this, utilisateur);
		page_Assoc_Student_Cour.setAutoscrolls(true);
		panel.add(page_Assoc_Student_Cour,
				"Page_Assoc_Student_Cour");
		/**/
		page_notation_prof = new Page_Notation_Prof(this, utilisateur);
		page_notation_prof.setAutoscrolls(true);
		/**/
		panel.add(page_notation_prof, "Page_Notation_Prof");
		Page_Choix_Matiere_Notation_Prof page_Choix_Matiere_Notation_Prof = new Page_Choix_Matiere_Notation_Prof(this, utilisateur);
		page_Choix_Matiere_Notation_Prof.setAutoscrolls(true);
		panel.add(page_Choix_Matiere_Notation_Prof,
				"Page_Choix_Matière_Notation_Prof");

		/**/
		page_notation_eleve = new Page_Notation_Eleve(this, utilisateur);
		page_notation_eleve.setAutoscrolls(true);
		/**/
		panel.add(page_notation_eleve, "Page_Notation_Eleve");
		Page_Change_Pwd page_Change_Pwd = new Page_Change_Pwd(this, utilisateur);
		page_Change_Pwd.setAutoscrolls(true);
		panel.add(page_Change_Pwd, "Page_Changer_MDP");
		Page_Rechercher_Professeur page_Rechercher_Professeur = new Page_Rechercher_Professeur(this,utilisateur);
		page_Rechercher_Professeur.setAutoscrolls(true);
		panel.add(page_Rechercher_Professeur, "Page_Recherche_profs");
		Page_Rechercher_Eleve page_Rechercher_Eleve = new Page_Rechercher_Eleve(this, utilisateur);
		page_Rechercher_Eleve.setAutoscrolls(true);
		panel.add(page_Rechercher_Eleve,"Page_Recherche_eleves");
		
		pages_notes_prof = new Page_Notes_Prof(this, utilisateur);
		pages_notes_prof.setAutoscrolls(true);
		panel.add(pages_notes_prof, "Page_Note_Prof");
		
		panel.setVisible(true);
		card_Layout_Panel_center.show(panel, "Page_Connect");

	}

	public String getTextField() {
		return textField.getText();
	}

	public void setTextField(String message) {
		this.textField.setText(message);
	}

	public void getCard_Layout_Panel_center_Connect() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Connect");
	}

	public void getCard_Layout_Panel_center_Home() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Home");
	}

	public void getCard_Layout_Panel_center_Marks() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Marks");
	}

	public void getCard_Layout_Panel_center_New_Prof() {
		this.card_Layout_Panel_center.show(this.panel, "Page_New_Prof");
	}

	public void getCard_Layout_Panel_center_New_Promotion() {
		this.card_Layout_Panel_center.show(this.panel, "Page_New_Promotion");
	}

	public void getCard_Layout_Panel_center_New_Majeure() {
		this.card_Layout_Panel_center.show(this.panel, "Page_New_Majeure");
	}

	public void getCard_Layout_Panel_center_New_Matiere() {
		this.card_Layout_Panel_center.show(this.panel, "Page_New_Matiere");
	}

	public void getCard_Layout_Panel_center_Assoc_Prof_Cour() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Assoc_Prof_Cour");
	}

	public void getCard_Layout_Panel_center_New_Etudiant() {
		this.card_Layout_Panel_center.show(this.panel, "Page_New_Student");
	}

	public void getCard_Layout_Panel_center_Assoc_Student_Cour() {
		this.card_Layout_Panel_center.show(this.panel,
				"Page_Assoc_Student_Cour");
	}

	public void getCard_Layout_Panel_center_Notation_Prof() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Notation_Prof");
	}

	public void getCard_Layout_Panel_center_Page_Choix_Matière_Notation_Prof() {
		this.card_Layout_Panel_center.show(this.panel,
				"Page_Choix_Matière_Notation_Prof");
	}

	public void getCard_Layout_Panel_center_Page_Notation_Eleve() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Notation_Eleve");
	}

	public void getCard_Layout_Panel_center_Page_Changer_Mdp() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Changer_MDP");
	}
	
	public void getCard_Layout_Panel_center_Page_Chercher_Profs() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Recherche_profs");
	}

	public void getCard_Layout_Panel_center_Page_Chercher_Eleves() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Recherche_eleves");
	}
	
	public void getCard_Layout_Panel_center_Page_Notes_Profs() {
		this.card_Layout_Panel_center.show(this.panel, "Page_Note_Prof");
	}
	
	public Page_Notation_Prof getPage_notation() {
		return page_notation_prof;
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// Ici on demande la confirmation de fermeture de l'application
		System.out.println("MERDE !!");
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("SALUT !!");
	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	public void setLoginandPwdNull() {
		this.page_connect.setLoginPwd();
	}

	

	

}