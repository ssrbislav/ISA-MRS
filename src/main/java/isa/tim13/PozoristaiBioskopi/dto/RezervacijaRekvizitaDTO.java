package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RezervacijaRekvizitaDTO {
	
	@JsonProperty(value="brojRekvizita")
	private int brojRekvizita;
	
	@JsonProperty(value="nazivRekvizita")
	private String nazivRekvizita;
	
	@JsonProperty(value="ukupnaCena")
	private double ukupnaCena;
	
	@JsonProperty(value="putanjaDoSlike")
	private String putanjaDoSlike;

	public int getBrojRekvizita() {
		return brojRekvizita;
	}
	
	public void uvecajBrojRekvizita() {
		this.brojRekvizita++;
	}

	public void setBrojRekvizita(int brojRekvizita) {
		this.brojRekvizita = brojRekvizita;
	}

	public String getNazivRekvizita() {
		return nazivRekvizita;
	}

	public void setNazivRekvizita(String nazivRekvizita) {
		this.nazivRekvizita = nazivRekvizita;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public String getPutanjaDoSlike() {
		return putanjaDoSlike;
	}

	public void setPutanjaDoSlike(String putanjaDoSlike) {
		this.putanjaDoSlike = putanjaDoSlike;
	}

	public void uvecajUkupnuCenu(double cenaRekvizita) {
		this.ukupnaCena += cenaRekvizita;
	}
	
	
}
