package Model;

public class User {
	String type_user;
	String id;
	String login;
	String name;
	String fname;
	String promo;
	String majeur;
	String tuteur;

	public User(String type_user, String id, String login, String name,
			String fname, String promo, String majeur, String tuteur) {
		super();
		this.type_user = type_user;
		this.id = id;
		this.login = login;
		this.name = name;
		this.fname = fname;
		this.promo = promo;
		this.majeur = majeur;
		this.tuteur = tuteur;
	}

	public String getType_user() {
		return type_user;
	}

	public void setType_user(String type_user) {
		this.type_user = type_user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getMajeur() {
		return majeur;
	}

	public void setMajeur(String majeur) {
		if (majeur == null) {
			this.majeur = "aucune";
		} else {
			this.majeur = majeur;
		}

	}

	public String getTuteur() {
		return tuteur;
	}

	public void setTuteur(String tuteur) {
		this.tuteur = tuteur;
	}

	public void RemiseZero() {
		this.login = "";
		this.id = "";
		this.majeur = "";
		this.promo = "";
		this.fname = "";
		this.name = "";
		this.type_user = "";
		this.tuteur = "";
	}

}
