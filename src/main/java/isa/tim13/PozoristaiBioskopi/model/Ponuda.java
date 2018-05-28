package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ponuda", uniqueConstraints = { @UniqueConstraint(columnNames =  { "autor_id", "objava_id" })})
public class Ponuda {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Korisnik autor;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Objava objava;
	
	@Column(name="naslov")
	private String naslov;
	
	@Column(name="opis")
	private String opis;
	
	@Column(name="cena")
	private double cena;
	
	public Ponuda() {}
	
	public int getId() {
		return id;
	}
	
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	

	public Objava getObjava() {
		return objava;
	}

	public void setObjava(Objava objava) {
		this.objava = objava;
	}

	public Korisnik getAutor() {
		return autor;
	}

	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}
	
}
