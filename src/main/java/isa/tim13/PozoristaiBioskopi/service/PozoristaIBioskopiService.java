package isa.tim13.PozoristaiBioskopi.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import isa.tim13.PozoristaiBioskopi.dto.InstitucijaDTO;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.repository.InstitucijaKultureRepository;

@Service
public class PozoristaIBioskopiService {
	
	@Autowired
	InstitucijaKultureRepository rep;
	
	@Autowired
	SlikeService slikeServis;
	
	private static String PREFIX = "institucije-slike";
	
	public void dodajInstitucijuKulture(InstitucijaDTO k, MultipartFile file) throws IOException {
		InstitucijaKulture inst = new InstitucijaKulture();
		inst.setAdresa(k.getAdresa());
		inst.setGrad(k.getGrad());
		inst.setNaziv(k.getNaziv());
		inst.setOpis(k.getOpis());
		inst.setTelefon(k.getTelefon());
		inst.setSale(k.getSale());
		inst.setTip(k.getTip());
		if(file!=null) {
			String konacnaPutanja = slikeServis.pribaviKonacnuPutanju(new StringBuilder(PREFIX+"/"+(inst.getNaziv().replaceAll("\\W", "_"))), file);
			slikeServis.sacuvajSliku(konacnaPutanja, file);
			inst.setLokacijaSlike(konacnaPutanja);
		}
		rep.save(inst);
		
	}

	public Collection<InstitucijaKulture> prikaziInstitucije() {
		return (Collection<InstitucijaKulture>) rep.findAll();
	}
	
	public Optional<InstitucijaKulture> findById( int id) {
		
		return rep.findById(id);
	}
}
