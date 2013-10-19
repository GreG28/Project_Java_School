import java.sql.SQLException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {

	public void connected(Connection arg0) {
		System.out.println("[SERVER] Someone is trying to connect");
	}

	public void disconnected(Connection arg0) {
		System.out.println("[SERVER] Someone is trying to disconnect ");
	}

	public void received(Connection connection, Object arg1) {
		if (arg1 instanceof Requete_SQL) {
			Requete_SQL requette_sql = (Requete_SQL) arg1;
			System.out.println("la requete est du type -> "
					+ requette_sql.typeofRequest);
			System.out.println("la requete est -> " + requette_sql.request);

			if (requette_sql.typeofRequest.equals("GETTHINGS")) {
				// on fait appell à la fonction get things de BDD_Echange
				System.out.println("on fait un getthings");
				try {
					Object objet_resultat = BDD_exchange
							.getThings(requette_sql.request);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					connection.sendTCP(objet_resultat);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {
				System.out.println(" on fait un update_query");
				int int_resultat = BDD_exchange
						.update_Query(requette_sql.request);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connection.sendTCP(int_resultat);
			}
		}
	}
}
