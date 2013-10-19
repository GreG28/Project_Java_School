package Model;

import javax.swing.JOptionPane;

import View.First_Window;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {

	private Project_Client_Java project_client_java;
	private First_Window fenetre;

	public void init(Project_Client_Java project_client_java, First_Window fenetre) {
		this.project_client_java = project_client_java;
		this.fenetre= fenetre;
	}

	public void connected(Connection arg0) {
		System.out.println("[CLIENT] You are connected");
		JOptionPane.showMessageDialog(fenetre,
				" Connecté au serveur ! ");
	}

	public void disconnected(Connection arg0) {
		System.out.println("[CLIENT] You are disconnected ");
	}

	public void received(Connection connection, Object arg1) {
		if (arg1 instanceof Object[][]) {
			System.out.println("on a reçu un tableau d'objets ! ");
			Object[][] tableau = (Object[][]) arg1;
			
			for( int cpt = 0 ; cpt < tableau.length ; cpt = cpt+1)
			{
				for( int cpt2 = 0 ; cpt2 < tableau[cpt].length ; cpt2 = cpt2+1)
				{
					System.out.print( tableau[cpt][cpt2] + "  ||  ");
				}
				System.out.println("");
			}
			
			project_client_java.objet_resultat = tableau;
			
		}
		else if (arg1 instanceof Integer)
		{
			System.out.println("on a reçu un int ! ");
			project_client_java.int_resultat = (Integer) arg1;
		}
	}
}
