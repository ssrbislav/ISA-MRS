package isa.tim13.PozoristaiBioskopi.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.exceptions.ObjavaNePostoji;
import isa.tim13.PozoristaiBioskopi.exceptions.ObjavaNijeNeobjavljena;
import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitNePostoji;
import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.Objava;
import isa.tim13.PozoristaiBioskopi.model.StatusObjave;
import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;
import isa.tim13.PozoristaiBioskopi.repository.AdministratoriRepository;
import isa.tim13.PozoristaiBioskopi.repository.ObjavaRepository;
import isa.tim13.PozoristaiBioskopi.repository.TematskiRekvizitRepository;

@Service
public class FanZonaService {
	
	@Autowired
	TematskiRekvizitRepository rep;
	
	
	@Autowired
	ObjavaRepository objavaRep;
	
	@Autowired
	AdministratoriRepository adminRep;
	
	@Autowired 
	SlikeService slikeServis;
	
	
	
	
	private static final String PUTANJA_PREFIKS = "slike/";

	public void dodajTematskiRekvizit(RekvizitDTO rekvizit, MultipartFile file) throws IOException, RekvizitVecPostojiException {
		TematskiRekvizit tematskiRekvizit = new TematskiRekvizit();
		
		tematskiRekvizit.setNazivRekvizita(rekvizit.getNazivRekvizita());
		tematskiRekvizit.setOpisRekvizita(rekvizit.getOpisRekvizita());
		tematskiRekvizit.setCenaRekvizita(rekvizit.getCenaRekvizita());
		tematskiRekvizit.setBroj(rekvizit.getBroj());
		StringBuilder putanjaDoSlikeRekvizita = new StringBuilder(PUTANJA_PREFIKS+(rekvizit.getNazivRekvizita().replaceAll("\\W", "_")));
		
		File direktorijum = new File(PUTANJA_PREFIKS);
		if(!direktorijum.exists()) {
			direktorijum.mkdir();
		}
		
		
		
		TematskiRekvizit test = rep.findByNazivRekvizita(rekvizit.getNazivRekvizita());
		if(test!=null) {
			throw new RekvizitVecPostojiException();
		}
		
		String konacnaPutanja = slikeServis.pribaviKonacnuPutanju(putanjaDoSlikeRekvizita, file);
		slikeServis.sacuvajSliku(konacnaPutanja,file);
		
		tematskiRekvizit.setPutanjaDoSlike(konacnaPutanja);
		rep.save(tematskiRekvizit);
	}


	public Iterable<TematskiRekvizit> prikaziSveTematskeRekvizite() {
		return rep.findAll();
	}

	public void modifikujRekvizit(int id, RekvizitDTO rekvizit) throws RekvizitNePostoji, RekvizitVecPostojiException {
		Optional<TematskiRekvizit> rekvizitIzBaze = rep.findById(id);
		if(rekvizitIzBaze.isPresent()==false) {
			throw new RekvizitNePostoji();
		}
		TematskiRekvizit test = rep.findByNazivRekvizita(rekvizit.getNazivRekvizita());
		//ako postoji neki drugi rekvizit sa promenjenim nazivom baci izuzetak, dozvoljavamo ponovo upis starog naziva
		// u isti rekvizit
		if(test!=null && test.getId()!=id) {
			throw new RekvizitVecPostojiException();
		}
		rekvizitIzBaze.get().setCenaRekvizita(rekvizit.getCenaRekvizita());
		rekvizitIzBaze.get().setOpisRekvizita(rekvizit.getOpisRekvizita());
		rekvizitIzBaze.get().setNazivRekvizita(rekvizit.getNazivRekvizita());
		rekvizitIzBaze.get().setBroj(rekvizit.getBroj());
		rep.save(rekvizitIzBaze.get());
		
	}

	public String modifikujSlikuRekvizita(int id, MultipartFile file) throws RekvizitNePostoji, IOException {
		Optional<TematskiRekvizit> rekvizitIzBaze = rep.findById(id);
		if(rekvizitIzBaze.isPresent()==false) {
			throw new RekvizitNePostoji();
		}
		
		TematskiRekvizit rek = rekvizitIzBaze.get();
		
		StringBuilder putanjaDoSlikeRekvizita = new StringBuilder(PUTANJA_PREFIKS+(rek.getNazivRekvizita().replaceAll("\\W", "_")));
		String novaPutanja = slikeServis.pribaviKonacnuPutanju(putanjaDoSlikeRekvizita, file);
		
		slikeServis.obrisiStaruSliku(rek.getPutanjaDoSlike());
		slikeServis.sacuvajSliku(novaPutanja,file);
		
		
		rek.setPutanjaDoSlike(novaPutanja);
		rep.save(rek);
		return novaPutanja;
		
	}

	public void brisanjeTematskogRekvizita(int id) throws RekvizitNePostoji {
		Optional<TematskiRekvizit> rekvizitIzBaze = rep.findById(id);
		if(rekvizitIzBaze.isPresent()==false) {
			throw new RekvizitNePostoji();
		}
		
		slikeServis.obrisiStaruSliku(rekvizitIzBaze.get().getPutanjaDoSlike());
		rep.deleteById(id);
		
	}

	public Iterable<TematskiRekvizit> pretraziTematskeRekvizite(String nazivRekvizita, double donjaCena,
			double gornjaCena) {
		return rep.findByRazniKriterijumi(nazivRekvizita,donjaCena,gornjaCena);
	}

	public Iterable<Objava> prikaziObjave(StatusObjave status) {
		return objavaRep.findByStatus(status);
	}
	
	//REQUIRES_NEW - metoda uvek pokrece novu transakciju, ako postoji tekuca transakcija ona se suspenduje
	//@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void preuzmiObjavu(FanZonaAdministrator admin, int id) throws ObjavaNePostoji, ObjavaNijeNeobjavljena {
		Objava obj = objavaRep.findById(id);
		if(obj==null) {
			throw new ObjavaNePostoji();
		}
		if(obj.getStatus()!=StatusObjave.NEOBJAVLJEN) {
			throw new ObjavaNijeNeobjavljena();
		}
		
		
		obj.setStatus(StatusObjave.U_RAZMATRANJU);
		obj.setAdmin(admin);
		adminRep.save(admin);
		objavaRep.save(obj);
		
		
		
	}

	public Iterable<Objava> prikaziRazmatraneObjave(FanZonaAdministrator admin) {
		
		return objavaRep.dobaviRazmatraneObjave(admin);
	}
	
	//@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void evaluirajObjavu(FanZonaAdministrator admin, int id, boolean prihvacena) throws NeovlascenPristupException {
		Objava obj = objavaRep.findById(id);
		if(obj.getAdmin().getId()!=admin.getId() || obj.getStatus()!=StatusObjave.U_RAZMATRANJU) {
			throw new NeovlascenPristupException();
		}
		
		
		if(prihvacena) {
			obj.setStatus(StatusObjave.OBJAVLJEN);
			objavaRep.save(obj);
		}
		else {
			objavaRep.delete(obj);
		}
	}

}
