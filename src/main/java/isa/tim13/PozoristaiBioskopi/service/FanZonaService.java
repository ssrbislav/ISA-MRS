package isa.tim13.PozoristaiBioskopi.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;
import isa.tim13.PozoristaiBioskopi.repository.FanZonaRepository;

@Service
public class FanZonaService {
	
	@Autowired
	FanZonaRepository rep;
	
	@Autowired 
	SlikeService slikeServis;
	
	private static final String PUTANJA_PREFIKS = "slike/";

	public void dodajTematskiRekvizit(RekvizitDTO rekvizit, MultipartFile file) throws IOException, RekvizitVecPostojiException {
		TematskiRekvizit tematskiRekvizit = new TematskiRekvizit();
		
		tematskiRekvizit.setNazivRekvizita(rekvizit.getNazivRekvizita());
		tematskiRekvizit.setOpisRekvizita(rekvizit.getOpisRekvizita());
		tematskiRekvizit.setCenaRekvizita(rekvizit.getCenaRekvizita());
		String putanjaDoSlikeRekvizita = PUTANJA_PREFIKS+rekvizit.getNazivRekvizita();
		
		File direktorijum = new File(PUTANJA_PREFIKS);
		if(!direktorijum.exists()) {
			direktorijum.mkdir();
		}
		
		
		
		TematskiRekvizit test = rep.findByNazivRekvizita(rekvizit.getNazivRekvizita());
		if(test!=null) {
			throw new RekvizitVecPostojiException();
		}
		
		putanjaDoSlikeRekvizita = putanjaDoSlikeRekvizita.replace(' ', '_');
		putanjaDoSlikeRekvizita += "."+slikeServis.pribaviEkstenziju(file);
		slikeServis.sacuvajSliku(putanjaDoSlikeRekvizita,file);
		
		tematskiRekvizit.setPutanjaDoSlike(putanjaDoSlikeRekvizita);
		rep.save(tematskiRekvizit);
	}

	public Iterable<TematskiRekvizit> prikaziSveTematskeRekvizite() {
		return rep.findAll();
	}

}
