package isa.tim13.PozoristaiBioskopi.service;


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
	
	public void registrujAdministratora(AdministratorDTO dto) throws InstitucijaNePostojiException, OsobaVecPostojiException {
		Administrator a = new Administrator();
		a.setIme(dto.getIme());
		a.setPrezime(dto.getPrezime());
		a.setEmail(dto.getEmail());
		a.setLozinka(dto.getLozinka());
		a.setTip(dto.getTip());
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
	
}
