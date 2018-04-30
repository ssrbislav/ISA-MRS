package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;

public class AdministratorDTO {
	
	@JsonProperty(value = "ime")
	private String ime;
	@JsonProperty(value = "prezime")
	private String prezime;
	
	@JsonProperty(value = "lozinka")
	private String lozinka;
	@JsonProperty(value = "email")
	private String email;
	
	@JsonProperty(value = "tip_administratora")
	private TipAdministratora tip;
	@JsonProperty(value = "id_institucije")
	private int idInstitucije;
	
	public AdministratorDTO() {
		
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipAdministratora getTip() {
		return tip;
	}

	public void setTip(TipAdministratora tip) {
		this.tip = tip;
	}

	public int getIdInstitucije() {
		return idInstitucije;
	}

	public void setIdInstitucije(int idInstitucije) {
		this.idInstitucije = idInstitucije;
	}

	
}
