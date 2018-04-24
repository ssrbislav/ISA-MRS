package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDTO {
	
	@JsonProperty(value = "ime")
	private String ime;
	@JsonProperty(value = "prezime")
	private String prezime;
	@JsonProperty(value = "email")
	private String email;
	@JsonProperty(value = "lozinka1")
	private String lozika1;
	@JsonProperty(value = "lozinka2")
	private String lozinka2;
	@JsonProperty(value = "telefon")	
	private String telefon;
	@JsonProperty(value = "grad")
	private String grad;

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getEmail() {
		return email;
	}

	public String getLozika1() {
		return lozika1;
	}

	public String getLozinka2() {
		return lozinka2;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getGrad() {
		return grad;
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

	public void setLozika1(String lozika) {
		this.lozika1 = lozika;
	}

	public void setLozinka2(String lozinka2) {
		this.lozinka2 = lozinka2;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public RegisterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
