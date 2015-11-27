package be.vdab.entities;

public class Reservatie {

	private long id;
	private Klant klant;
	private Voorstelling voorstelling;
	private int aantalPlaatsen;
	
	public Reservatie(long id, Klant klant, Voorstelling voorstelling, int aantalPlaatsen) {
		this.id = id;
		this.klant = klant;
		this.voorstelling = voorstelling;
		this.aantalPlaatsen = aantalPlaatsen;
	}
	public Reservatie(Voorstelling voorstelling, int aantalPlaatsen){
		this.voorstelling = voorstelling;
		this.aantalPlaatsen = aantalPlaatsen;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Klant getKlant() {
		return klant;
	}
	public void setKlant(Klant klant) {
		this.klant = klant;
	}
	public Voorstelling getVoorstelling() {
		return voorstelling;
	}
	public void setVoorstelling(Voorstelling voorstelling) {
		this.voorstelling = voorstelling;
	}
	public int getAantalPlaatsen() {
		return aantalPlaatsen;
	}
	public void setAantalPlaatsen(int aantalPlaatsen) {
		this.aantalPlaatsen = aantalPlaatsen;
	}
	
	
	
}
