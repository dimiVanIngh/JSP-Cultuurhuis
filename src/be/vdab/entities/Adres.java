package be.vdab.entities;

public class Adres {
	private String straat;
	private String huisnummer;
	private String gemeente;
	private String postcode;
	
	//TODO checken algemene exceptions + custom error?
	public Adres(String straat, String huisnummer, String gemeente, String postcode) {
		this.straat = straat;
		this.huisnummer = huisnummer;
		this.gemeente = gemeente;
		this.postcode = postcode;
	}
	public String getStraat() {
		return straat;
	}
	public void setStraat(String straat) {
		this.straat = straat;
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
	@Override
	public String toString(){
		return straat + " " + huisnummer + " " + postcode + " " + gemeente;
	}
	
	
}
