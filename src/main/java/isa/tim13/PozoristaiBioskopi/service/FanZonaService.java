package isa.tim13.PozoristaiBioskopi.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import isa.tim13.PozoristaiBioskopi.dto.ObjavaDTO;
import isa.tim13.PozoristaiBioskopi.dto.ObjavaiStatusDTO;
import isa.tim13.PozoristaiBioskopi.dto.PonudaDTO;
import isa.tim13.PozoristaiBioskopi.dto.PonudaNotifikacijaDTO;
import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.dto.RezervacijaRekvizitaDTO;
import isa.tim13.PozoristaiBioskopi.dto.TematskiRekvizitiDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.DatumIstekaNevalidan;
import isa.tim13.PozoristaiBioskopi.exceptions.NemaViseRekvizita;
import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.exceptions.NotifikacijaNePostoji;
import isa.tim13.PozoristaiBioskopi.exceptions.ObjavaNePostoji;
import isa.tim13.PozoristaiBioskopi.exceptions.ObjavaNijeNeobjavljena;
import isa.tim13.PozoristaiBioskopi.exceptions.ObjavaNijeObjavljena;
import isa.tim13.PozoristaiBioskopi.exceptions.PonudaNePostoji;
import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitNePostoji;
import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Objava;
import isa.tim13.PozoristaiBioskopi.model.Ponuda;
import isa.tim13.PozoristaiBioskopi.model.PonudaNotifikacija;
import isa.tim13.PozoristaiBioskopi.model.RezervacijaRekvizita;
import isa.tim13.PozoristaiBioskopi.model.StatusObjave;
import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;
import isa.tim13.PozoristaiBioskopi.repository.AdministratoriRepository;
import isa.tim13.PozoristaiBioskopi.repository.ObjavaRepository;
import isa.tim13.PozoristaiBioskopi.repository.PonudaNotifikacijaRepository;
import isa.tim13.PozoristaiBioskopi.repository.PonudaRepository;
import isa.tim13.PozoristaiBioskopi.repository.TematskiRekvizitRepository;

@Service
public class FanZonaService {
	
	@Autowired
	TematskiRekvizitRepository rep;
	
	
	@Autowired
	ObjavaRepository objavaRep;
	
	@Autowired
	PonudaRepository ponudaRep;
	
	@Autowired
	AdministratoriRepository adminRep;
	
	@Autowired 
	SlikeService slikeServis;
	
	@Autowired
	PonudaNotifikacijaRepository notifikacijaRep;
	
	
	
	
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


	public TematskiRekvizitiDTO prikaziSveTematskeRekvizite(boolean b) {
		TematskiRekvizitiDTO povratnaVrednost = new TematskiRekvizitiDTO();
		povratnaVrednost.setAdminFanZone(b);
		povratnaVrednost.setRekviziti(rep.findAll());
		return povratnaVrednost;
	}

	public void modifikujRekvizit(int id, RekvizitDTO rekvizit) throws RekvizitNePostoji, RekvizitVecPostojiException {
		TematskiRekvizit rekvizitIzBaze = rep.findById(id);
		if(rekvizitIzBaze==null) {
			throw new RekvizitNePostoji();
		}
		TematskiRekvizit test = rep.findByNazivRekvizita(rekvizit.getNazivRekvizita());
		//ako postoji neki drugi rekvizit sa promenjenim nazivom baci izuzetak, dozvoljavamo ponovo upis starog naziva
		// u isti rekvizit
		if(test!=null && test.getId()!=id) {
			throw new RekvizitVecPostojiException();
		}
		rekvizitIzBaze.setCenaRekvizita(rekvizit.getCenaRekvizita());
		rekvizitIzBaze.setOpisRekvizita(rekvizit.getOpisRekvizita());
		rekvizitIzBaze.setNazivRekvizita(rekvizit.getNazivRekvizita());
		rekvizitIzBaze.setBroj(rekvizit.getBroj());
		rep.save(rekvizitIzBaze);
		
	}

	public String modifikujSlikuRekvizita(int id, MultipartFile file) throws RekvizitNePostoji, IOException {
		TematskiRekvizit rekvizitIzBaze = rep.findById(id);
		if(rekvizitIzBaze==null) {
			throw new RekvizitNePostoji();
		}
		
		TematskiRekvizit rek = rekvizitIzBaze;
		
		StringBuilder putanjaDoSlikeRekvizita = new StringBuilder(PUTANJA_PREFIKS+(rek.getNazivRekvizita().replaceAll("\\W", "_")));
		String novaPutanja = slikeServis.pribaviKonacnuPutanju(putanjaDoSlikeRekvizita, file);
		
		slikeServis.obrisiStaruSliku(rek.getPutanjaDoSlike());
		slikeServis.sacuvajSliku(novaPutanja,file);
		
		
		rek.setPutanjaDoSlike(novaPutanja);
		rep.save(rek);
		return novaPutanja;
		
	}

	public void brisanjeTematskogRekvizita(int id) throws RekvizitNePostoji {
		TematskiRekvizit rekvizitIzBaze = rep.findById(id);
		if(rekvizitIzBaze==null) {
			throw new RekvizitNePostoji();
		}
		
		slikeServis.obrisiStaruSliku(rekvizitIzBaze.getPutanjaDoSlike());
		rep.deleteById(id);
		
	}

	public TematskiRekvizitiDTO pretraziTematskeRekvizite(String nazivRekvizita,
			double gornjaCena,boolean b) {
		
		TematskiRekvizitiDTO povratnaVrednost = new TematskiRekvizitiDTO();
		povratnaVrednost.setRekviziti(rep.findByRazniKriterijumi(nazivRekvizita,gornjaCena));
		povratnaVrednost.setAdminFanZone(b);
		return povratnaVrednost;
	}

	public Iterable<ObjavaDTO> prikaziObjave(StatusObjave status) {
		return vratiDTOObjave(objavaRep.findByStatus(status));
	}
	
	//REQUIRES_NEW - metoda uvek pokrece novu transakciju, ako postoji tekuca transakcija ona se suspenduje
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW ,rollbackFor=Exception.class)
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
		objavaRep.save(obj);
		
		
		
	}
	
	public Iterable<ObjavaDTO> vratiDTOObjave(Iterable<Objava> objave){
		ObjavaDTO objavica = null;
		ArrayList<ObjavaDTO> objaveZaVracanje = new ArrayList<ObjavaDTO>();
		for(Objava objava:objave) {
			objavica = new ObjavaDTO();
			objavica.setNaziv(objava.getNaziv());
			objavica.setDatumIsteka(objava.getDatumIsteka());
			
			if(objava.getAutor()!=null) {
				objavica.setAutor(objava.getAutor().getIme()+" "+objava.getAutor().getPrezime()+"<br/>"+objava.getAutor().getEmail());
			}
			else {
				objavica.setAutor("Nepoznat");
			}
			
			objavica.setOpis(objava.getOpis());
			objavica.setPutanjaDoSlike(objava.getPutanjaDoSlike());
			objavica.setId(objava.getId());
			objaveZaVracanje.add(objavica);
		}
		return objaveZaVracanje;
	}

	public Iterable<ObjavaDTO> prikaziRazmatraneObjave(FanZonaAdministrator admin) {
		
		Iterable<Objava> objave =  objavaRep.dobaviRazmatraneObjave(admin);
		return vratiDTOObjave(objave);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
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


	public void dodajObjavu(Korisnik kor, ObjavaDTO objava, MultipartFile file) throws IOException, DatumIstekaNevalidan {
		Objava punaObjava = new Objava();
		punaObjava.setAutor(kor);
		punaObjava.setDatumIsteka(objava.getDatumIsteka());
		punaObjava.setNaziv(objava.getNaziv());
		punaObjava.setOpis(objava.getOpis());
		punaObjava.setStatus(StatusObjave.NEOBJAVLJEN);
		
		if(objava.getDatumIsteka().before(new Date())) {
			throw new DatumIstekaNevalidan();
		}
		
		if(file!=null) {
			String konacnaPutanja = slikeServis.pribaviKonacnuPutanju(new StringBuilder(PUTANJA_PREFIKS+"/"+(punaObjava.getNaziv().replaceAll("\\W", "_"))), file);
			slikeServis.sacuvajSliku(konacnaPutanja, file);
			punaObjava.setPutanjaDoSlike(konacnaPutanja);
		}
		
		objavaRep.save(punaObjava);
		
	}
	//izolacija Isolation.READ_COMMITTED ne moze da cita podatke koji nisu komitovani od strane drugih
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW ,rollbackFor=Exception.class,isolation=Isolation.READ_COMMITTED)
	public String dodajPonuduNaObjavu(ObjectMapper objMapper,Korisnik kor, PonudaDTO ponuda) throws ObjavaNePostoji, NeovlascenPristupException, JsonProcessingException {
		Objava objava = objavaRep.findById(ponuda.getIdObjave());
		
		if(objava==null) {
			throw new ObjavaNePostoji();
		}
		
		if(kor.getId()==objava.getAutor().getId() || objava.getStatus()==StatusObjave.ARHIVIRAN) {
			throw new NeovlascenPristupException();
		}
		
		Ponuda pravaPonuda = pronadjiPonudu(kor.getId(),objava.getId());
		if(pravaPonuda==null) {
			pravaPonuda = new Ponuda();
			podesiPonudu(kor,pravaPonuda,ponuda,objava);
			objava.getPonude().add(pravaPonuda);
			objavaRep.save(objava);
			pravaPonuda = pronadjiPonudu(kor.getId(),objava.getId()); //da bih mogao dobiti id ponude :(
		}
		else {
			podesiPonudu(kor,pravaPonuda,ponuda,objava);
			ponudaRep.save(pravaPonuda);
		}
		
		LinkedHashMap<String,String> retVal = new LinkedHashMap<String,String>();
		retVal.put("autor", pravaPonuda.getAutor().getIme()+" "+pravaPonuda.getAutor().getPrezime()+"\n"+pravaPonuda.getAutor().getEmail());
		retVal.put("idPonude",""+pravaPonuda.getId());
		return objMapper.writeValueAsString(retVal);
	}


	private void podesiPonudu(Korisnik kor,Ponuda pravaPonuda,PonudaDTO ponuda,Objava objava) {
		pravaPonuda.setAutor(kor);
		pravaPonuda.setObjava(objava);
		pravaPonuda.setNaslov(ponuda.getNaslov());
		pravaPonuda.setOpis(ponuda.getOpis());
		pravaPonuda.setCena(ponuda.getCena());
		
	}


	private Ponuda pronadjiPonudu(int idKorisnika, int idObjave) {
		return ponudaRep.pronadjiPonudu(idKorisnika,idObjave);
	}


	public String pribaviObjavu(Korisnik kor,ObjectMapper objMapper, int idObjave) throws ObjavaNePostoji, NeovlascenPristupException, JsonProcessingException {
		Objava obj = objavaRep.findById(idObjave);
		if(obj==null) {
			throw new ObjavaNePostoji();
		}
		//dozvoljavamo pregled arhiviranih objava
		if(obj.getStatus()!=StatusObjave.OBJAVLJEN && obj.getStatus()!=StatusObjave.ARHIVIRAN) {
			throw new NeovlascenPristupException();
		}
		
		ObjavaDTO retVal = new ObjavaDTO();
		retVal.setDatumIsteka(obj.getDatumIsteka());
		retVal.setNaziv(obj.getNaziv());
		retVal.setOpis(obj.getOpis());
		retVal.setPutanjaDoSlike(obj.getPutanjaDoSlike());
		
		PonudaDTO korisnikovaPonuda = pribaviPonude(retVal,obj,kor);
		
		ObjavaiStatusDTO celaVrednost = new ObjavaiStatusDTO();
		celaVrednost.setObjava(retVal);
		celaVrednost.setKorisnikovaPonuda(korisnikovaPonuda);
		
		boolean jeAutor = obj.getAutor().getId()==kor.getId();
		
		if(jeAutor) {
			celaVrednost.setDodavanjePonudeVidljivo(false);
			if(obj.getStatus()!=StatusObjave.ARHIVIRAN) {
				celaVrednost.setPrihvatanjePonudeVidljivo(true);
			}
		}
		else {
			if(obj.getStatus()!=StatusObjave.ARHIVIRAN) {
				celaVrednost.setDodavanjePonudeVidljivo(true);
			}
		}
		
		
		
		return objMapper.writeValueAsString(celaVrednost);
		
	}

	
	//Pribavlja sve ponude za objavu(DTO) i vraca i DTO objekat koji predstavlja ponudu koju je korisnik ponudio
	//Ako nije nista ponudio vraca null
	private PonudaDTO pribaviPonude(ObjavaDTO retVal, Objava obj,Korisnik kor) {
		PonudaDTO korisnikovaPonuda = null;
		retVal.setPonude(new ArrayList<PonudaDTO>());
		for(Ponuda pon:obj.getPonude()) {
			PonudaDTO ponudaDTO = new PonudaDTO();
			
			ponudaDTO.setCena(pon.getCena());
			ponudaDTO.setNaslov(pon.getNaslov());
			ponudaDTO.setOpis(pon.getOpis());
			ponudaDTO.setIdPonude(pon.getId());
			ponudaDTO.setAutor(pon.getAutor().getIme()+" "+pon.getAutor().getPrezime()+"\n"+pon.getAutor().getEmail());
			retVal.getPonude().add(ponudaDTO);
			//ako postoji njegova ponuda u ovoj objavi, kacicemo je za DTO kao takvu
			if(pon.getAutor().getId()==kor.getId()) {
				korisnikovaPonuda = ponudaDTO;
			}
		}
		return korisnikovaPonuda;
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW ,rollbackFor=Exception.class)
	public LinkedHashMap<String, String> rezervisanjeRekvizita(Korisnik kor, int id) throws RekvizitNePostoji, NemaViseRekvizita {
		
		TematskiRekvizit rek = rep.findById(id);
		if(rek==null) {
			throw new RekvizitNePostoji();
		}
		int broj = rek.getBroj();
		if(broj==0) {
			throw new NemaViseRekvizita();
		}
		
		RezervacijaRekvizita rezervacija = new RezervacijaRekvizita();
		rezervacija.setNarucilac(kor);
		rezervacija.setRekvizit(rek);
		rek.setBroj(broj-1);
		
		rep.save(rek);
		rep.save(rezervacija);
		
		LinkedHashMap<String,String> povratnaVrednost = new LinkedHashMap<String,String>();
		povratnaVrednost.put("broj",""+rek.getBroj());
		povratnaVrednost.put("poruka","Rekvizit uspesno rezervisan. ");
		return povratnaVrednost;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED ,rollbackFor=Exception.class)
	public void obavestiKorisnikaOPonudi(Ponuda pon,boolean prihvacena) {
		PonudaNotifikacija notifikacija = new PonudaNotifikacija();
		notifikacija.setPrihvacena(prihvacena);
		notifikacija.setPonuda(pon);
		notifikacija.setDatum(new Date());
		notifikacijaRep.save(notifikacija);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW ,rollbackFor=Exception.class)
	public void prihvatiPonudu(Korisnik kor, int id) throws NeovlascenPristupException, ObjavaNijeObjavljena, PonudaNePostoji {
		
		Ponuda prihvacenaPonuda = ponudaRep.findById(id);
		
		if(prihvacenaPonuda==null) {
			throw new PonudaNePostoji();
		}
		
		Objava obj = prihvacenaPonuda.getObjava();
		//ako nije autor objave ne moze da prihvati ponudu
		if(kor.getId()!=obj.getAutor().getId()) {
			throw new NeovlascenPristupException();
		}
		
		if(obj.getStatus()!=StatusObjave.OBJAVLJEN) {
			throw new ObjavaNijeObjavljena();
		}
		
		for(Ponuda p:obj.getPonude()) {
			if(p.getId()==prihvacenaPonuda.getId()) {
				obavestiKorisnikaOPonudi(p,true);
			}
			else {
				obavestiKorisnikaOPonudi(p,false);
			}
		}
		obj.setStatus(StatusObjave.ARHIVIRAN);
		objavaRep.save(obj);
		
	}


	public Iterable<PonudaNotifikacijaDTO> pribaviObavestenja(Korisnik kor) {
		ArrayList<PonudaNotifikacijaDTO> povratnaVrednost = new ArrayList<PonudaNotifikacijaDTO>();
		Iterable<PonudaNotifikacija> obavestenja = notifikacijaRep.dobaviObavestenjaKorisnika(kor.getId());
		PonudaNotifikacijaDTO nDTO;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. hh:mm");
		for(PonudaNotifikacija not:obavestenja) {
			nDTO = new PonudaNotifikacijaDTO();
			nDTO.setId(not.getId());
			nDTO.setImeObjave(not.getPonuda().getObjava().getNaziv());
			nDTO.setImePonude(not.getPonuda().getNaslov());
			nDTO.setPrihvacena(not.isPrihvacena());
			nDTO.setDatum(sdf.format(not.getDatum()));
			povratnaVrednost.add(nDTO);
		}
		return povratnaVrednost;
	}


	public void obrisiObavestenje(Korisnik kor, int id) throws NotifikacijaNePostoji, NeovlascenPristupException {
		PonudaNotifikacija notifikacija = notifikacijaRep.findById(id);
		if(notifikacija==null) {
			throw new NotifikacijaNePostoji();
		}
		
		if(kor.getId()!=notifikacija.getPonuda().getAutor().getId()) {
			throw new NeovlascenPristupException();
		}
		
		notifikacijaRep.delete(notifikacija);
		
	}


	public LinkedHashMap<Integer,RezervacijaRekvizitaDTO> pribaviRezervacijeRekvizita(Korisnik kor) {
		
		Iterable<RezervacijaRekvizita> rezervacije =  rep.pribaviRezervacijeRekvizita(kor.getId());
		LinkedHashMap<Integer,RezervacijaRekvizitaDTO> povratnaVrednost = new LinkedHashMap<Integer,RezervacijaRekvizitaDTO>();
		for(RezervacijaRekvizita rez:rezervacije) {
			
			if(povratnaVrednost.containsKey(rez.getRekvizit().getId())) {
				povratnaVrednost.get(rez.getRekvizit().getId()).uvecajBrojRekvizita();
				povratnaVrednost.get(rez.getRekvizit().getId()).uvecajUkupnuCenu(rez.getRekvizit().getCenaRekvizita());
			}
			else {
				RezervacijaRekvizitaDTO rezervacijaDTO = new RezervacijaRekvizitaDTO();
				rezervacijaDTO.setBrojRekvizita(1);
				rezervacijaDTO.setNazivRekvizita(rez.getRekvizit().getNazivRekvizita());
				rezervacijaDTO.setPutanjaDoSlike(rez.getRekvizit().getPutanjaDoSlike());
				rezervacijaDTO.setUkupnaCena(rez.getRekvizit().getCenaRekvizita());
				povratnaVrednost.put(rez.getRekvizit().getId(), rezervacijaDTO);
			}
			
		}
		return povratnaVrednost;
	}

}
