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
    
    @Column(name="lokacijaSlike")
  	private String lokacijaSlike;
    
    
    @Column(name="adresa")
	private String adresa;
    
    @Column(name="grad")
  	private String grad;
    
    @Column(name="rejting")
    private double rejting;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<Sala> sale;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<PredstavaProjekcija> repertoar;
    
    public List<Sala> getSale() {
		return sale;
	}

	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}

	 
    public List<PredstavaProjekcija> getRepertoar() {
		return repertoar;
	}

	public void setRepertoar(List<PredstavaProjekcija> repertoar) {
		this.repertoar = repertoar;
	}

	public TipInstitucijeKulture getTip() {
		return tip;
	}

	
	public void setTip(TipInstitucijeKulture tip) {
		this.tip = tip;
	}

	public double getRejting() {
		return rejting;
	}
	
	public void setRejting(double rejting) {
		this.rejting = rejting;
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

	public String getLokacijaSlike() {
		return lokacijaSlike;
	}


	public void setLokacijaSlike(String lokacijaSlike) {
		this.lokacijaSlike = lokacijaSlike;
	}

	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getGrad() {
		return grad;
	}


	public void setGrad(String grad) {
		this.grad = grad;
	}




	public InstitucijaKulture() {
		this.lokacijaSlike = "inicijalne-slike/nedostupna.png";
	}
	
	
}
