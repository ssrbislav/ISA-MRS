package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RekvizitDTO {
	
	@JsonProperty("nazivRekvizita")
	private String nazivRekvizita;
	
	@JsonProperty("opisRekvizita")
	private String opisRekvizita;
	
	@JsonProperty("cenaRekvizita")
	private double cenaRekvizita;
	
	@JsonProperty("broj")
	private int broj;
	
	
	public String getNazivRekvizita() {
		return nazivRekvizita;
	}
	public void setNazivRekvizita(String nazivRekvizita) {
		this.nazivRekvizita = nazivRekvizita;
	}
	public String getOpisRekvizita() {
		return opisRekvizita;
	}
	public void setOpisRekvizita(String opisRekvizita) {
		this.opisRekvizita = opisRekvizita;
	}
	public double getCenaRekvizita() {
		return cenaRekvizita;
	}
	public void setCenaRekvizita(double cenaRekvizita) {
		this.cenaRekvizita = cenaRekvizita;
	}
	
	
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public RekvizitDTO() {}
	
	
}
