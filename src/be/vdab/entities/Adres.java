package be.vdab.entities;

public class Adres {
	private String Straat;
	private String huisnummer;
	private String gemeente;
	private String postcode;
	
	//TODO checken algemene exceptions + custom error?
	public Adres(String straat, String huisnummer, String gemeente, String postcode) {
		Straat = straat;
		this.huisnummer = huisnummer;
		this.gemeente = gemeente;
		this.postcode = postcode;
	}
	public String getStraat() {
		return Straat;
	}
	public void setStraat(String straat) {
		Straat = straat;
	}
	public String getHuisnummer() {
		return huisnummer;
	}
	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}
	public String getGemeente() {
		return gemeente;
	}
	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
}
