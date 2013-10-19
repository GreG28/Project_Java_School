package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import Controler.Event_Page_Notes_Profs;
import Model.User;

public class Page_Notes_Prof extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Object[][] tableau_notes;

	public First_Window fenetre_principale;
	public User utilisateur;

	Page_Notes_Prof(First_Window fenetre, User utilisateur) {

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
				"Mati\u00E8re", "Nom Eleve", "Pr\u00E9nom Eleve", "Promotion", "Majeur", "Note"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.SOUTH);

		JButton bouton_Rafraichir_notes = new JButton("Rafraichir Notes");
		bouton_Rafraichir_notes
				.addActionListener(new Event_Page_Notes_Profs(fenetre,
						this.utilisateur, this));
		bouton_Rafraichir_notes.setBackground(Color.LIGHT_GRAY);
		toolBar.add(bouton_Rafraichir_notes);

	}

	public void setTableau(Object[][] tableau) {
		tableau_notes = tableau;
		table.setModel(new DefaultTableModel(
				tableau_notes,
				new String[] {
					"Matière", "Nom Eleve", "Prénom Eleve", "Promotion", "id - Majeur", "Note"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
	}
}
