package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import Controler.Event_Page_Rechercher_Professeur;
import Model.User;

public class Page_Rechercher_Professeur extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Object[][] tableau_profs;

	public First_Window fenetre_principale;
	public User utilisateur;
	private JTextField nom_prof_recherche;

	Page_Rechercher_Professeur(First_Window fenetre, User utilisateur) {

		fenetre_principale = fenetre;
		this.utilisateur = utilisateur;

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
				"Nom", "Pr\u00E9nom"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.SOUTH);

		JButton bouton_Rafraichir_notes = new JButton("Rafraichir Professeur");
		bouton_Rafraichir_notes
				.addActionListener(new Event_Page_Rechercher_Professeur(fenetre,
						this.utilisateur, this));
		
		nom_prof_recherche = new JTextField();
		nom_prof_recherche.setText("nom professeur");
		toolBar.add(nom_prof_recherche);
		nom_prof_recherche.setColumns(10);
		bouton_Rafraichir_notes.setBackground(Color.LIGHT_GRAY);
		toolBar.add(bouton_Rafraichir_notes);

	}

	public void setTableau(Object[][] tableau) {
		tableau_profs = tableau;
		table.setModel(new DefaultTableModel(
				tableau_profs,
				new String[] {
					"Nom", "Prénom"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
	}
	
	public String getNom_prof_recherche_String() {
		return nom_prof_recherche.getText();
	}

	public void setNom_prof_recherche(String nom_prof_recherche_String) {
		this.nom_prof_recherche.setText(nom_prof_recherche_String);
	}



}
