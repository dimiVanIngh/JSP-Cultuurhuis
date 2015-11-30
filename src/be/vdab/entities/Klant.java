package be.vdab.entities;

import be.vdab.util.BCrypt;

public class Klant extends Persoon{
	
	private long id;
	private String gebruikersnaam;
	private String wachtwoord;
	
	public Klant(long id,String voornaam, String familienaam, Adres adres,String gebruikersnaam, String wachtwoord) {
		super(voornaam, familienaam, adres);
		this.id = id;
		this.gebruikersnaam = gebruikersnaam;
		setWachtwoord(wachtwoord);
	}
	
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = BCrypt.hashpw(wachtwoord, BCrypt.gensalt(13));
	}
	public boolean checkWachtwoord(String wachtwoord){
		return BCrypt.checkpw(wachtwoord, this.wachtwoord);
	}
	
	
}
