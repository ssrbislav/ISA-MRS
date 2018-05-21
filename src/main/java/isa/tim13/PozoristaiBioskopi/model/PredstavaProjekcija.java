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
public class PredstavaProjekcija {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private int id;
    
    @Column(name="naziv")
    private String naziv;
    
    @Column(name="spisak_glumaca")
    private String spisak_glumaca;
    
    @Column(name="ime_reditelja")
    private String ime_reditelja;
    
    @Column(name="popust")
    private int popust;
    
    @Column(name="zanr")
    private String zanr;
    
    @Column(name="trajanje")
    private double trajanje;
    
    @Column(name="slika")
    private String slika;
    
    @Column(name="opis")
    private String opis;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<Sala> lista_sala;
   
    
    @Column(name="cena_karte")
    private double cena_karte;

	public int getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSpisak_glumaca() {
		return spisak_glumaca;
	}

	public void setSpisak_glumaca(String spisak_glumaca) {
		this.spisak_glumaca = spisak_glumaca;
	}

	public String getIme_reditelja() {
		return ime_reditelja;
	}

	public void setIme_reditelja(String ime_reditelja) {
		this.ime_reditelja = ime_reditelja;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public List<Sala> getLista_sala() {
		return lista_sala;
	}

	public void setLista_sala(List<Sala> lista_sala) {
		this.lista_sala = lista_sala;
	}
	

	public double getCena_karte() {
		return cena_karte;
	}

	public void setCena_karte(double cena_karte) {
		this.cena_karte = cena_karte;
	}
	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust=  popust;
	}
    public PredstavaProjekcija() {}
    
    
}
