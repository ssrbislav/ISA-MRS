package isa.tim13.PozoristaiBioskopi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Objava {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Korisnik autor;
	
	@Column(name="naziv")
	private String naziv;
	@Column(name="opis")
	private String opis;
	
	@Column(name="putanja_do_slike")
	private String putanjaDoSlike;
	@Column(name="datum_isteka")
	private Date datumIsteka;
	
	@Column(name="status")
	private StatusObjave status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private FanZonaAdministrator admin;
	
	@Version
	private int version;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objava other = (Objava) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public FanZonaAdministrator getAdmin() {
		return (FanZonaAdministrator)admin;
	}
	public void setAdmin(FanZonaAdministrator admin) {
		this.admin = admin;
	}
	public int getId() {
		return id;
	}
	public Korisnik getAutor() {
		return autor;
	}
	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getPutanjaDoSlike() {
		return putanjaDoSlike;
	}
	public void setPutanjaDoSlike(String putanjaDoSlike) {
		this.putanjaDoSlike = putanjaDoSlike;
	}
	public Date getDatumIsteka() {
		return datumIsteka;
	}
	public void setDatumIsteka(Date datumIsteka) {
		this.datumIsteka = datumIsteka;
	}
	
	public StatusObjave getStatus() {
		return status;
	}
	public void setStatus(StatusObjave status) {
		this.status = status;
	}
	
}
