package isa.tim13.PozoristaiBioskopi.service;


import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.dto.AdministratorDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.InstitucijaNePostojiException;
import isa.tim13.PozoristaiBioskopi.exceptions.OsobaVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.repository.AdministratoriRepository;
import isa.tim13.PozoristaiBioskopi.repository.InstitucijaKultureRepository;

@Service
public class AdministratoriService {
    
	@Autowired
	AdministratoriRepository rep;
	
	@Autowired
	InstitucijaKultureRepository irep;
	
	private LinkedHashMap<String,String> fanZonaOpcije;
	private LinkedHashMap<String,String> sistemskiOpcije;
	private LinkedHashMap<String,String> institucionalniOpcije;
	
	public AdministratoriService() {
		fanZonaOpcije = new LinkedHashMap<String,String>();
		fanZonaOpcije.put("Dodavanje rekvizita", "/dodavanjeTematskihRekvizita");
		fanZonaOpcije.put("Prikaz rekvizita","/prikazTematskihRekvizita");
		
		sistemskiOpcije = new LinkedHashMap<String,String>();
		sistemskiOpcije.put("Registracija institucija", "/registracijaInstitucija");
		sistemskiOpcije.put("Dodavanje admina", "/dodavanjeAdministratora");
		
		institucionalniOpcije = new LinkedHashMap<String,String>();
	}
	
	public void registrujAdministratora(AdministratorDTO dto) throws InstitucijaNePostojiException, OsobaVecPostojiException {
		Administrator a = new Administrator();
		a.setIme(dto.getIme());
		a.setPrezime(dto.getPrezime());
		a.setEmail(dto.getEmail());
		a.setLozinka(dto.getLozinka());
		a.setTip(dto.getTip());
		a.setAktivan(true);
		if(a.getTip()==TipAdministratora.INSTITUCIONALNI) {
			InstitucijaKulture k = irep.findById(dto.getIdInstitucije()).orElse(null);
			if(k==null) {
				throw new InstitucijaNePostojiException();
			}
			a.setInst(k);
		}
		
		if(rep.findByEmail(a.getEmail())!=null) {
			throw new OsobaVecPostojiException();
		}
		
		rep.save(a);
	}

	public Iterable<Administrator> prikaziAdministratore() {
		return rep.findAll();
	}

	public LinkedHashMap<String, String> pribaviOpcije(Administrator a) {
		if(a.getTip().equals(TipAdministratora.FAN_ZONA)) {
			return fanZonaOpcije;
		}
		else if(a.getTip().equals(TipAdministratora.SISTEMSKI)) {
			return sistemskiOpcije;
		}
		
		return institucionalniOpcije;
		
	}
	
}
