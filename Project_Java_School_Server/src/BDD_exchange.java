import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD_exchange {
	// c'est grace à cette classe que l'on va traiter
	// avec la base de donnée !

	private static Connection conn;

	public BDD_exchange() {
		conn = null;
		try {

			String url = "jdbc:mysql://localhost:3306/java_ecole"; // chemin
																	// vers la
																	// bdd
			String user = "root"; // login de la bdd
			String passwd = ""; // mot de passe de la bdd

			conn = DriverManager.getConnection(url, user, passwd);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn != null) {
			// JOptionPane.showMessageDialog(null,
			// "CONNECTION FAITE AVEC LA BASE DE DONNEE !");
			System.out.println("/***/");
			System.out.println("CONNECTION FAITE AVEC LA BASE DE DONNEE !");
			System.out.println("/***/");
		} else {
			// JOptionPane.showMessageDialog(null,
			// "CONNECTION IMPOSSIBLE A LA BASE DE DONNEE !");
			System.out.println("/***/");
			System.out.println("CONNECTION IMPOSSIBLE A LA BASE DE DONNEE !");
			System.out.println("/***/");
		}

	}

	public static Object[][] getThings(String query) throws SQLException {

		// Création d'un objet Statement
		Statement state = null;
		ResultSet result = null;
		Object[][] objet_result = null;

		try {
			state = conn.createStatement();
			// L'objet ResultSet contient le résultat de la requête SQL
			result = state.executeQuery(query);
			// On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();

			int cpt = 0;
			while (result.next()) {
				cpt = cpt + 1;
			}

			int cpt2 = cpt;
			while (result.previous()) {
				cpt2 = cpt2 - 1;
			}

			objet_result = new Object[cpt][resultMeta.getColumnCount()];

			int i = 0;
			while (result.next()) {
				for (int j = 1; j <= resultMeta.getColumnCount(); j++) {
					objet_result[i][j - 1] = result.getObject(j);
				}

				i = i + 1;
			}

			result.close();
			state.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (objet_result.length != 0) {
			for (int cpt = 0; cpt < objet_result.length; cpt = cpt + 1) {
				for (int cpt2 = 0; cpt2 < objet_result[cpt].length; cpt2 = cpt2 + 1) {
					System.out.print(" | " + objet_result[cpt][cpt2] + " ");
				}
				System.out.println("  ");
			}
			System.out.println("  ");
		}

		return objet_result;
	}

	public static int update_Query(String query) {
		Statement state = null;
		int nb = 0;
		try {
			state = conn.createStatement();
			nb = state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nb;

	}

}
