package be.vdab.entities;

public abstract class Persoon {
	private String voornaam;
	private String familienaam;
	private Adres adres;
		
	public Persoon(String voornaam, String familienaam, Adres adres) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.adres = adres;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	@Override
	public String toString(){
		return voornaam + " " + familienaam + " " + adres;
	}
}
