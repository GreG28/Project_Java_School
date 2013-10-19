package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ParseConversionEvent;

import Controler.Event_Page_Notation_Prof;
import Model.User;

public class Page_Notation_Prof extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[][] tableau_eleve_SID_CID;

	private JTable table;
	private Object[][] tableau_notes;

	public First_Window fenetre_principale;

	Page_Notation_Prof(First_Window fenetre, User utilisateur) {

		fenetre_principale = fenetre;

		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(0.0f);
		scrollPane.setAlignmentX(0.0f);
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Prénom", "Nom", "Note"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.SOUTH);

		JButton bouton_Revenir_Choix = new JButton("Revenir au choix");
		bouton_Revenir_Choix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bonjour = JOptionPane
						.showConfirmDialog(
								null,
								"Avez vous enregister votre travail ? sinon il sera perdu à jamais ! !",
								"ATTENTION", JOptionPane.YES_NO_OPTION);

				if (bonjour == 1) // NON
				{
					System.out.println("NON");
					// on ne fait rien !
				} else // OUI
				{
					System.out.println("OUI");
					// on doit repasser sur la page du choix !
					fenetre_principale
							.getCard_Layout_Panel_center_Page_Choix_Matière_Notation_Prof();
				}
			}
		});
		bouton_Revenir_Choix.setBackground(Color.LIGHT_GRAY);
		toolBar.add(bouton_Revenir_Choix);

		JButton bouton_Sauvegarde = new JButton("Sauver !");
		bouton_Sauvegarde.addActionListener(new Event_Page_Notation_Prof(
				fenetre, utilisateur, this));
		bouton_Sauvegarde.setBackground(Color.DARK_GRAY);
		toolBar.add(bouton_Sauvegarde);

	}

	public void setTableau(Object[][] tableau, int[][] eleve_SID_cours_CID) {
		tableau_notes = tableau;
		this.tableau_eleve_SID_CID = eleve_SID_cours_CID;
		System.out.println(tableau_eleve_SID_CID.length); 
		table.setModel(new DefaultTableModel(tableau_notes, new String[] {
				"Prénom", "Nom", "Note" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}	
		});
	}

	public double[] get_Marks() {
		double[] tableau_notes;
		tableau_notes = new double[table.getRowCount()];
		for (int cpt = 0; cpt < table.getRowCount(); cpt = cpt + 1) {
			tableau_notes[cpt] =  Double.parseDouble(table.getModel().getValueAt(cpt, 2).toString());
		}
		return tableau_notes;
	}

	public int[][] get_liste_SID_CID() {
		return tableau_eleve_SID_CID;
	}
}
