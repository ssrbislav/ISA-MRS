package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Osoba {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

	@Column(name = "ime")
	private String ime;
	
	@Column(name = "prezime")
	private String prezime;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "lozinka")
	private String lozinka;
	
	@Column(name = "lokacijaSlike")
	private String lokacijaSlike;
	
	@Column(name = "telefon")
	private String telefon;
	
	@Column(name = "grad")
	private String grad;
	
	@Column(name = "aktivan")
	private Boolean aktivan;
	
	@Column(name = "registracioniLink")
	private String registracioniLink;
	
	//za admina fan zone(ili institucije ako Srbislav odluci, mora ovde da se stavi inace kod registracije korisnika puca)
	@Column
	private boolean promenioSifru;
	
	public boolean isPromenioSifru() {
		return promenioSifru;
	}

	public void setPromenioSifru(boolean promenioSifru) {
		this.promenioSifru = promenioSifru;
	}

	public Integer getId() {
		return id;
	}
	
	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getEmail() {
		return email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public String getLokacijaSlike() {
		return lokacijaSlike;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getGrad() {
		return grad;
	}

	public Boolean getAktivan() {
		return aktivan;
	}

	public String getRegistracioniLink() {
		return registracioniLink;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public void setLokacijaSlike(String lokacijaSlike) {
		this.lokacijaSlike = lokacijaSlike;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}

	public void setRegistracioniLink(String registracioniLink) {
		this.registracioniLink = registracioniLink;
	}

	public Osoba() {
		super();
	}
	
	

}
