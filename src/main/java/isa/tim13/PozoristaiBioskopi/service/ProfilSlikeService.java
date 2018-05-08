package isa.tim13.PozoristaiBioskopi.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;

@Service
public class ProfilSlikeService {
	
	@Autowired
	KorisnikRepository rep;
	
	@Autowired 
	SlikeService slikeServis;
	
	private static final String PUTANJA_PREFIKS = "profil-slike/";
	
	public void dodajSlikuOsobi(Osoba osoba, MultipartFile file) throws IOException{

		String putanjaDoSlike = PUTANJA_PREFIKS+osoba.getId();
		
		File direktorijum = new File(PUTANJA_PREFIKS);
		if(!direktorijum.exists()) {
			direktorijum.mkdir();
		}
		
		putanjaDoSlike += "."+slikeServis.pribaviEkstenziju(file);
		slikeServis.sacuvajSliku(putanjaDoSlike,file);
		
		osoba.setLokacijaSlike(putanjaDoSlike);
		rep.save(osoba);
	}
}
