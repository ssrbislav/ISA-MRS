package isa.tim13.PozoristaiBioskopi.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

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
		
		tematskiRekvizit.setPutanjaDoSlike(putanjaDoSlikeRekvizita);
		
		TematskiRekvizit test = rep.findByNazivRekvizita(rekvizit.getNazivRekvizita());
		if(test!=null) {
			throw new RekvizitVecPostojiException();
		}
		
		FileOutputStream fos = new FileOutputStream(putanjaDoSlikeRekvizita);
		fos.write(file.getBytes());
		fos.close();
		rep.save(tematskiRekvizit);
	}

}
