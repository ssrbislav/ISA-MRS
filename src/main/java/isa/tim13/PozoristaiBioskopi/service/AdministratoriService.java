package isa.tim13.PozoristaiBioskopi.service;


import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.dto.AdministratorDTO;
import isa.tim13.PozoristaiBioskopi.dto.BodovnaSkalaDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.BodovnaSkalaNevalidna;
import isa.tim13.PozoristaiBioskopi.exceptions.InstitucijaNePostojiException;
import isa.tim13.PozoristaiBioskopi.exceptions.OsobaVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.BodovnaSkala;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.InstitucionalniAdministrator;
import isa.tim13.PozoristaiBioskopi.model.SistemskiAdministrator;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.repository.AdministratoriRepository;
import isa.tim13.PozoristaiBioskopi.repository.BodovnaSkalaRepository;
import isa.tim13.PozoristaiBioskopi.repository.InstitucijaKultureRepository;

@Service
public class AdministratoriService {
    
	@Autowired
	AdministratoriRepository rep;
	
	@Autowired
	InstitucijaKultureRepository irep;
	
	@Autowired
	BodovnaSkalaRepository bodSkalaRep;
	
	private LinkedHashMap<String,String> fanZonaOpcije;
	private LinkedHashMap<String,String> sistemskiOpcije;
	private LinkedHashMap<String,String> institucionalniOpcije;
	
	public AdministratoriService() {
		fanZonaOpcije = new LinkedHashMap<String,String>();
		fanZonaOpcije.put("Razmatrane objave", "/pregledRazmatranihObjava");
		fanZonaOpcije.put("Neobjavljene objave", "/pregledNeobjavljenihObjava");
		fanZonaOpcije.put("Dodavanje rekvizita", "/dodavanjeTematskihRekvizita");
		fanZonaOpcije.put("Prikaz rekvizita","/prikazTematskihRekvizita");
		
		
		sistemskiOpcije = new LinkedHashMap<String,String>();
		sistemskiOpcije.put("Registracija institucija", "/registracijaInstitucija");
		sistemskiOpcije.put("Dodavanje admina", "/dodavanjeAdministratora");
		sistemskiOpcije.put("Bodovna skala", "/bodovnaSkala");
		
		institucionalniOpcije = new LinkedHashMap<String,String>();
	}
	
	public void registrujAdministratora(AdministratorDTO dto) throws InstitucijaNePostojiException, OsobaVecPostojiException {
		Administrator a = napraviNovogAdministratora(dto.getTip());
		a.setIme(dto.getIme());
		a.setPrezime(dto.getPrezime());
		a.setEmail(dto.getEmail());
		a.setLozinka(dto.getLozinka());
		a.setAktivan(true);
		if(a instanceof InstitucionalniAdministrator) {
			InstitucijaKulture k = irep.findById(dto.getIdInstitucije()).orElse(null);
			if(k==null) {
				throw new InstitucijaNePostojiException();
			}
			((InstitucionalniAdministrator)a).setInst(k);
		}
		
		if(rep.findByEmail(a.getEmail())!=null) {
			throw new OsobaVecPostojiException();
		}
		
		rep.save(a);
	}

	private Administrator napraviNovogAdministratora(TipAdministratora tip) {
		if(tip.equals(TipAdministratora.FAN_ZONA)) {
			return new FanZonaAdministrator();
		}
		else if(tip.equals(TipAdministratora.SISTEMSKI)) {
			return new SistemskiAdministrator();
		}
		
		return new InstitucionalniAdministrator();
	}

	public Iterable<Administrator> prikaziAdministratore() {
		return rep.findAll();
	}

	public LinkedHashMap<String, String> pribaviOpcije(Administrator a) {
		if(a instanceof FanZonaAdministrator) {
			return fanZonaOpcije;
		}
		else if(a instanceof SistemskiAdministrator) {
			return sistemskiOpcije;
		}
		
		return institucionalniOpcije;
		
	}

	public void promeniBodovnuSkalu(BodovnaSkalaDTO bDTO) throws BodovnaSkalaNevalidna {
		
		BodovnaSkala bodSkala = bodSkalaRep.dobaviSkalu();
		if(bodSkala==null) {
			bodSkala = new BodovnaSkala();
		}
		int bronzaBodovi = bDTO.getBronzaBodovi();
		int srebroBodovi = bDTO.getSrebroBodovi();
		int zlatoBodovi = bDTO.getZlatoBodovi();
		boolean skalaValidna = (bronzaBodovi < srebroBodovi) && (srebroBodovi < zlatoBodovi);
		if(!skalaValidna || bronzaBodovi<0 || srebroBodovi<0 || zlatoBodovi<0) {
			throw new BodovnaSkalaNevalidna();
		}
		bodSkala.setBronzaBodovi(bronzaBodovi);
		bodSkala.setSrebroBodovi(srebroBodovi);
		bodSkala.setZlatoBodovi(zlatoBodovi);
		bodSkalaRep.save(bodSkala);
		
	}

	public BodovnaSkalaDTO dobaviBodovnuSkalu() {
		BodovnaSkala bodSkala = bodSkalaRep.dobaviSkalu();
		if(bodSkala==null) {
			return new BodovnaSkalaDTO();
		}
		
		BodovnaSkalaDTO povratnaVrednost = new BodovnaSkalaDTO();
		povratnaVrednost.setBronzaBodovi(bodSkala.getBronzaBodovi());
		povratnaVrednost.setSrebroBodovi(bodSkala.getSrebroBodovi());
		povratnaVrednost.setZlatoBodovi(bodSkala.getZlatoBodovi());
		
		return povratnaVrednost;
	}
	
}
