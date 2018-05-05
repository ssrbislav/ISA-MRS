package isa.tim13.PozoristaiBioskopi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Sala {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
	
	
	@Override
	public String toString() {
		return "Sala [id=" + id + ", oznaka_sale=" + oznaka_sale + ", broj_vrsta=" + broj_vrsta + ", broj_kolona="
				+ broj_kolona + "]";
	}


    @JsonProperty("oznaka_sale")
	@Column(name="oznaka_sale")
	private String oznaka_sale;
	
    @JsonProperty("broj_vrsta")
	@Column(name="broj_vrsta")
	private int broj_vrsta;
	
	public int getBrojVrsta() {
		return broj_vrsta;
	}


	public void setBrojVrsta(int brojVrsta) {
		this.broj_vrsta = brojVrsta;
	}


    
	@JsonProperty("broj_kolona")
	@Column(name="broj_kolona")
	private int broj_kolona;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getOznakaSale() {
		return oznaka_sale;
	}



	public void setOznakaSale(String oznakaSale) {
		this.oznaka_sale = oznakaSale;
	}





	public int getBrojKolona() {
		return broj_kolona;
	}



	public void setBrojKolona(int brojKolona) {
		this.broj_kolona = brojKolona;
	}



	public Sala() {}
	
	
	
}
