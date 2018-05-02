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
public class Sala {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
	
	
	@Column(name="oznaka_sale")
	private String oznaka_sale;
	
	@Column(name="broj_sedista")
	private int broj_sedista;
	
	@Column(name="matrica_sala")
	private int[][] matrica_sala;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<Termin> lista_termina;
	
	public int[][] getMatrica_sala() {
		return matrica_sala;
	}

	public void setMatrica_sala(int[][] matrica_sala) {
		this.matrica_sala = matrica_sala;
	}

	public String getOznaka_sale() {
		return oznaka_sale;
	}

	public void setOznaka_sale(String oznaka_sale) {
		this.oznaka_sale = oznaka_sale;
	}

	public int getBroj_sedista() {
		return broj_sedista;
	}

	public void setBroj_sedista(int broj_sedista) {
		this.broj_sedista = broj_sedista;
	}

	public int getId() {
		return id;
	}
	
	public List<Termin> getLista_termina() {
		return lista_termina;
	}

	public void setLista_termina(List<Termin> lista_termina) {
		this.lista_termina = lista_termina;
	}
	
	
	
	public Sala() {}
	
	
	
}
