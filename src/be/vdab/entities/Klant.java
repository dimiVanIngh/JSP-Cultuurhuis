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
		this.wachtwoord = wachtwoord;
	}
	
    public Klant(long id, String voornaam, String familienaam, String straat, String huisnr, String gemeente, String postcode, String gebruikersnaam, String wachtwoord) {
        super(voornaam, familienaam, new Adres(straat, huisnr, gemeente, postcode));
        this.id = id;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }
    
    public Klant(String voornaam, String familienaam, String straat, String huisnr, String gemeente, String postcode, String gebruikersnaam, String wachtwoord) {
        super(voornaam, familienaam, new Adres(straat, huisnr, gemeente, postcode));
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
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
	public void hashWachtwoord() {
		this.wachtwoord = BCrypt.hashpw(this.wachtwoord, BCrypt.gensalt(13));
	}
	public boolean checkWachtwoord(String wachtwoord){
		return BCrypt.checkpw(wachtwoord, this.wachtwoord);
	}	
}
