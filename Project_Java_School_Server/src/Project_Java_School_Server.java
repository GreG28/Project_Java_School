import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

public class Project_Java_School_Server {

	Server server;
	static BDD_exchange liaison_base;

	public Project_Java_School_Server() throws IOException {
		server = new Server();
		registerPackets();
		server.addListener(new NetworkListener());
		server.bind(6789);
		server.start();

		System.out
				.println(" Le serveur est en ligne ! on attend la connection ");
		
		// on doit le connecter à la bdd ! 
		
	}

	private void registerPackets() {
		Kryo kryo = server.getKryo();
		kryo.register(Requete_SQL.class);
		kryo.register(int.class);
		kryo.register(Object[][].class);
		kryo.register(Object[].class);
		kryo.register(java.math.BigDecimal.class);
		kryo.register(String.class);
	}

	public static void main(String[] args) {
		
		
		liaison_base = new BDD_exchange();
		
		try {
			new Project_Java_School_Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}