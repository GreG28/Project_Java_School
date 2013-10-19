package Model;

import java.io.IOException;

import View.First_Window;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class Project_Client_Java implements Runnable {

	public Client client;
	Object[][] objet_resultat;
	int int_resultat;
	private First_Window fenetre;

	public Project_Client_Java(First_Window fenetre) {

		this.fenetre = fenetre;
		client = new Client();
		register();

		NetworkListener nl = new NetworkListener();
		nl.init(this, fenetre);
		client.addListener(nl);

		client.start();

		try {
			client.connect(900000000, "127.0.0.1", 6789);
			//client.connect(900000000, "192.168.1.13", 54555); tests pour valider si wamp online0
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void register() {
		Kryo kryo = client.getKryo();
		kryo.register(Requete_SQL.class);
		kryo.register(int.class);
		kryo.register(Object[][].class);
		kryo.register(Object[].class);
		kryo.register(java.math.BigDecimal.class);
		kryo.register(String.class);
	}

	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void envoyer_requete_Select(String string_requete) {
		Requete_SQL request = new Requete_SQL();
		request.request = string_requete;
		request.typeofRequest = "GETTHINGS";

		client.sendTCP(request);

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object[][] recuperer_Object()
	{
		return objet_resultat;
	}

	public void envoyer_requete_Update(String string_requete) {
		Requete_SQL request = new Requete_SQL();
		request.request = string_requete;
		request.typeofRequest = "UPDATE_INSERT";

		client.sendTCP(request);

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int recuperer_Int()
	{
		return int_resultat;
	}

}
