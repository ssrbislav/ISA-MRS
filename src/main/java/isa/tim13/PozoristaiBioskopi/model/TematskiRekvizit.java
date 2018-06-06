package isa.tim13.PozoristaiBioskopi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY,mappedBy="rekvizit")
	private List<RezervacijaRekvizita> rezervacije;
	
	public List<RezervacijaRekvizita> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<RezervacijaRekvizita> rezervacije) {
		this.rezervacije = rezervacije;
	}

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
