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

@Entity
public class InstitucijaKulture {
	
	
	

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private int id;
    
    
    @Column(name="tip")
    private TipInstitucijeKulture tip;
    
    @Column(name="naziv")
	private String naziv;
    
    @Column(name="adresa")
	private String adresa;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<Sala> sale;
    
    public List<Sala> getSale() {
		return sale;
	}

	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}

	public TipInstitucijeKulture getTip() {
		return tip;
	}

	public void setTip(TipInstitucijeKulture tip) {
		this.tip = tip;
	}




	@Column(name="telefon")
	private String telefon;
    
    @Column(name="opis")
	private String opis;
    
	
	
	public int getId() {
		return id;
	}
	
	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}




	public InstitucijaKulture() {
		
	}
	
	
}
