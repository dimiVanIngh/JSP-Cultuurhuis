package be.vdab.entities;

import be.vdab.util.BCrypt;

public class Klant extends Persoon{
	
	private long id;
	private String wachtwoord;
	
	public Klant(long id,String voornaam, String familienaam, Adres adres, String wachtwoord) {
		super(voornaam, familienaam, adres);
		this.id = id;
		setWachtwoord(wachtwoord);
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
		this.wachtwoord = BCrypt.hashpw(wachtwoord, BCrypt.gensalt());
	}
	public boolean checkWachtwoord(String wachtwoord){
		return BCrypt.checkpw(wachtwoord, this.wachtwoord);
	}
	
	
}
