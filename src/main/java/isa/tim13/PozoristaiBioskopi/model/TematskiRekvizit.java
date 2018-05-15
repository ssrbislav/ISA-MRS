package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TematskiRekvizit {
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	public int getId() {
		return id;
	}
	
	@Column(name="naziv")
	private String nazivRekvizita;
	
	@Column(name="putanja_do_slike")
	private String putanjaDoSlike;
	
	@Column(name="opis")
	private String opisRekvizita;
	
	@Column(name="cena")
	private double cenaRekvizita;
	
	@Column(name="broj")
	private int broj;
	
	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public TematskiRekvizit() {
		
	}
	
	public String getNazivRekvizita() {
		return nazivRekvizita;
	}
	public void setNazivRekvizita(String nazivRekvizita) {
		this.nazivRekvizita = nazivRekvizita;
	}
	public String getPutanjaDoSlike() {
		return putanjaDoSlike;
	}
	public void setPutanjaDoSlike(String putanjaDoSlike) {
		this.putanjaDoSlike = putanjaDoSlike;
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
	

}
