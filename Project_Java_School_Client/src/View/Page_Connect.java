package View;

import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controler.Event_Page_Connect;
import Model.User;

public class Page_Connect extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField login;
	private JPasswordField mot_de_passe;
	First_Window fenetre_principale;

	public Page_Connect(First_Window fenetre, User utilisateur) {
		
		fenetre_principale = fenetre;
		setLayout(null);
		
		JLabel Label_MDP_Login = new JLabel("Login");
		Label_MDP_Login.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_MDP_Login.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Label_MDP_Login.setBounds(149, 78, 51, 30);
		add(Label_MDP_Login);
		
		login = new JTextField();
		//login.setText("Login");
		login.setText("Login");
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setDropMode(DropMode.INSERT);
		Label_MDP_Login.setLabelFor(login);
		login.setBounds(205, 75, 152, 33);
		add(login);
		login.setColumns(10);
		
		JLabel Label_MDP = new JLabel("Mot de passe");
		Label_MDP.setHorizontalAlignment(SwingConstants.RIGHT);
		Label_MDP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Label_MDP.setBounds(48, 130, 152, 30);
		add(Label_MDP);
		
		mot_de_passe = new JPasswordField();
		mot_de_passe.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//mot_de_passe.setText("root");
		mot_de_passe.setText("motdepasse");
		mot_de_passe.setHorizontalAlignment(SwingConstants.CENTER);
		Label_MDP.setLabelFor(mot_de_passe);
		mot_de_passe.setBounds(205, 131, 152, 33);
		add(mot_de_passe);
		
		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.addActionListener(new Event_Page_Connect(fenetre_principale, login, mot_de_passe, utilisateur)); 
		btnNewButton.setBounds(205, 218, 152, 47);
		add(btnNewButton);
	}
	
	public void setLoginPwd()
	{
		this.login.setText("Login");
		this.mot_de_passe.setText("motdepasse");
	}
}
