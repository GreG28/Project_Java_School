package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controler.Event_Page_Rechercher_Eleve;
import Model.BDD_exchange;
import Model.User;

public class Page_Rechercher_Eleve extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Object[][] tableau_profs;

	public First_Window fenetre_principale;
	public User utilisateur;
	private JTextField nom_eleve_recherche;
	private JComboBox<String> liste_Promotions;
	private JButton bouton_select_promotion;
	private JComboBox<String> liste_majeurs;
	private int[] tableau_Majeurs;

	Page_Rechercher_Eleve(First_Window fenetre, User utilisateur) {

		fenetre_principale = fenetre;
		this.utilisateur = utilisateur;

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(150, 150));
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setBounds(new Rectangle(0, 0, 45, 150));
		panel.setAutoscrolls(true);
		add(panel, BorderLayout.WEST);
		panel.setMinimumSize(new Dimension(45, 150));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton bouton_rafraichir_promotions = new JButton(
				"Rafraichier Promotions");
		bouton_rafraichir_promotions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[][] result = null;
				try {
					String query_promotions = "SELECT PNAME FROM promotions ;";

					System.out.println("query_promotions -> "
							+ query_promotions);
					result = BDD_exchange.getThings(query_promotions);

					if (result.length != 0) {
						liste_Promotions.removeAllItems();
						for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
							liste_Promotions.addItem((String) result[cpt][0]);
						}
						liste_Promotions.addItem("");
					} else {
						liste_Promotions.removeAllItems();
						liste_Promotions.addItem("");
					}

				} catch (SQLException e1) {
				}
			}
		});
		panel.add(bouton_rafraichir_promotions);

		liste_Promotions = new JComboBox<String>();
		liste_Promotions.addItem("");
		panel.add(liste_Promotions);

		bouton_select_promotion = new JButton("Selectionner Promotion");
		panel.add(bouton_select_promotion);
		bouton_select_promotion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (liste_Promotions.getSelectedItem().equals("M1")
						|| liste_Promotions.getSelectedItem().equals("M2")) {
					Object[][] result = null;
					try {
						String query_majeurs = "SELECT MNAME, MID FROM majeurs ;";

						System.out.println("query_majeurs -> " + query_majeurs);
						result = BDD_exchange.getThings(query_majeurs);
						tableau_Majeurs = new int[result.length];
						if (result.length != 0) {
							liste_majeurs.removeAllItems();
							for (int cpt = 0; cpt < result.length; cpt = cpt + 1) {
								liste_majeurs.addItem((String) result[cpt][0]);
								tableau_Majeurs[cpt] = (Integer) result[cpt][1];
							}
							liste_majeurs.addItem("");
						} else {
							liste_majeurs.removeAllItems();
							liste_majeurs.addItem("");
						}

					} catch (SQLException e1) {
					}

				} else {
					liste_majeurs.removeAllItems();
				}
			}
		});

		liste_majeurs = new JComboBox<String>();
		liste_majeurs.addItem("");
		panel.add(liste_majeurs);

		nom_eleve_recherche = new JTextField();
		panel.add(nom_eleve_recherche);
		nom_eleve_recherche.setText("nom élève");
		nom_eleve_recherche.setColumns(10);

		JButton bouton_Rafraichir_notes = new JButton("Rafraichir Eleves");
		panel.add(bouton_Rafraichir_notes);
		bouton_Rafraichir_notes
				.addActionListener(new Event_Page_Rechercher_Eleve(fenetre,
						this.utilisateur, this, liste_Promotions,
						liste_majeurs, tableau_Majeurs));
		bouton_Rafraichir_notes.setBackground(Color.LIGHT_GRAY);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(0.0f);
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"SID", "Nom", "Pr\u00E9nom", "Promotion", "Majeur" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true,
					true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

	}

	public void setTableau(Object[][] tableau) {
		tableau_profs = tableau;
		table.setModel(new DefaultTableModel(tableau_profs, new String[] {
				"SID", "Nom", "Prénom", "Promotion", "id - Majeur" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

	public String getNom_eleves_recherche_String() {
		return nom_eleve_recherche.getText().toUpperCase();
	}

	public void setNom_eleves_recherche(String nom_prof_recherche_String) {
		this.nom_eleve_recherche.setText(nom_prof_recherche_String
				.toUpperCase());
	}

	public int[] getListSID() {
		return tableau_Majeurs;
	}
}
