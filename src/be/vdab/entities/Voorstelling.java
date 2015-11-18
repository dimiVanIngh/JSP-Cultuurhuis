package be.vdab.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Voorstelling {

	private int id;
	private String titel;
	private String uitvoerders;
	private BigDecimal prijs;
	private int vrijePlaatsen;
	private Genre genre;
	private Date datum;
	
	
	public Voorstelling(int id, String titel, String uitvoerders, Date datum, BigDecimal prijs, int vrijePlaatsen, Genre genre) {
		this.id = id;
		this.titel = titel;
		this.uitvoerders = uitvoerders;
		this.prijs = prijs;
		this.vrijePlaatsen = vrijePlaatsen;
		this.genre = genre;
		this.datum = datum;
	}
		
	public Voorstelling(int id, String titel, String uitvoerders, Date datum, BigDecimal prijs, int vrijePlaatsen) {

		this.id = id;
		this.titel = titel;
		this.uitvoerders = uitvoerders;
		this.prijs = prijs;
		this.vrijePlaatsen = vrijePlaatsen;
		this.datum = datum;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getUitvoerders() {
		return uitvoerders;
	}
	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}
	public int getVrijePlaatsen() {
		return vrijePlaatsen;
	}
	public void setVrijePlaatsen(int vrijePlaatsen) {
		this.vrijePlaatsen = vrijePlaatsen;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	
}
