package be.vdab.entities;

public class Klant extends Persoon{
	
	private long id;
	private String wachtwoord;
	
	public Klant(String voornaam, String familienaam, Adres adres, long id, String wachtwoord) {
		super(voornaam, familienaam, adres);
		this.id = id;
		this.wachtwoord = wachtwoord;
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
		this.wachtwoord = wachtwoord;
	}
	
	
}
