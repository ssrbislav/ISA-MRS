package isa.tim13.PozoristaiBioskopi;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import isa.tim13.PozoristaiBioskopi.dto.ObjavaDTO;
import isa.tim13.PozoristaiBioskopi.dto.PonudaDTO;
import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Objava;
import isa.tim13.PozoristaiBioskopi.model.Ponuda;
import isa.tim13.PozoristaiBioskopi.model.PonudaNotifikacija;
import isa.tim13.PozoristaiBioskopi.model.StatusObjave;
import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;
import isa.tim13.PozoristaiBioskopi.repository.AdministratoriRepository;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;
import isa.tim13.PozoristaiBioskopi.repository.ObjavaRepository;
import isa.tim13.PozoristaiBioskopi.repository.PonudaNotifikacijaRepository;
import isa.tim13.PozoristaiBioskopi.repository.PonudaRepository;
import isa.tim13.PozoristaiBioskopi.repository.TematskiRekvizitRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FanZonaControllerTest {
	
	private static final String URL_PREFIX = "/fanzona";
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TematskiRekvizitRepository rep;
	
	@Autowired
	private ObjavaRepository objavaRep;
	
	@Autowired
	private AdministratoriRepository adminRep;
	
	@Autowired
	private KorisnikRepository korRep;
	
	@Autowired
	PonudaRepository ponudaRep;
	
	@Autowired
	PonudaNotifikacijaRepository notifikacijaRep;
	
	@Autowired
    MockHttpSession session;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private RekvizitDTO rekvizit;
	private Objava objava;
	
	private String korEmail;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Administrator a = new FanZonaAdministrator();
		a.setAktivan(true);
		a.setIme("Test");
		a.setPrezime("Testic");
		a.setEmail("majic@majic.com");
		adminRep.save(a);
		session.setAttribute("korisnik", a);
		rekvizit = new RekvizitDTO();
		objava = new Objava();
		objava.setDatumIsteka(new Date());
		objava.setNaziv("Objava1");
		objava.setStatus(StatusObjave.NEOBJAVLJEN);
		objava.setOpis("Ovo je neki opis objave");
		
		Korisnik kor = new Korisnik();
		korEmail = "kor@kor.com";
		kor.setEmail(korEmail);
		kor.setAktivan(true);
		kor.setIme("Kor");
		kor.setPrezime("Koric");
		korRep.save(kor);
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void prikaziObjaveTest() throws Exception {
		mockMvc.perform(get(URL_PREFIX+"/prikaziObjave")
                .session(session)
                .param("status","NEOBJAVLJEN"))
            .andExpect(status().isOk());
		
		mockMvc.perform(get(URL_PREFIX+"/prikaziObjave")
                .param("status","OBJAVLJEN"))
            .andExpect(status().isOk());
	}
	
	@Before
	public void podesiAdmina() {
		session.setAttribute("korisnik",adminRep.findById(1).get());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void prikaziRazmatranebjaveTest() throws Exception {
		objava.setNaziv("NazivObjave1");
		objava.setAdmin((FanZonaAdministrator)session.getAttribute("korisnik"));
		objava.setStatus(StatusObjave.U_RAZMATRANJU);
		objavaRep.save(objava);
		mockMvc.perform(get(URL_PREFIX+"/prikaziRazmatraneObjave")
                .session(session)).andExpect(status().isOk());
	}
	
	@Test
	@Rollback(true)
	public void preuzmiObjavuTest() throws Exception {
		objava.setNaziv("NazivObjave2");
		objava.setAdmin(null);
		objava.setStatus(StatusObjave.NEOBJAVLJEN);
		objavaRep.save(objava);
		int id = objavaRep.findByNaziv(objava.getNaziv()).getId();
		mockMvc.perform(put(URL_PREFIX+"/preuzmiObjavu")
                .session(session).param("id", ""+id)).andExpect(status().isOk());
		
		//posto je vec preuzeta ocekujemo fail
		mockMvc.perform(put(URL_PREFIX+"/preuzmiObjavu")
                .session(session).param("id", ""+id)).andExpect(status().isBadRequest());
	}
	
	@Test
	@Rollback(true)
	public void evaluirajObjavuTest() throws Exception {
		objava.setNaziv("NazivObjave3");
		objava.setAdmin((FanZonaAdministrator)session.getAttribute("korisnik"));
		objava.setStatus(StatusObjave.U_RAZMATRANJU);
		objavaRep.save(objava);
		int id = objavaRep.findByNaziv(objava.getNaziv()).getId();
		mockMvc.perform(put(URL_PREFIX+"/evaluirajObjavu")
                .session(session).param("id", ""+id).param("prihvacena", "true")).andExpect(status().isOk());
		
		//vec prihvacenu objavu pokusavamo da odbijemo, ocekujemo fail(forbidden, posto je neovlasceni pristup u pitanju)
		mockMvc.perform(put(URL_PREFIX+"/evaluirajObjavu")
                .session(session).param("id", ""+id).param("prihvacena", "false")).andExpect(status().isForbidden());
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeTematskogRekvizitaTest1() throws JsonProcessingException, Exception {
		rekvizit.setNazivRekvizita("Test1");
		rekvizit.setCenaRekvizita(50);
		rekvizit.setBroj(5);
		rekvizit.setOpisRekvizita("Opis rekvizita test 1");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit)))
            .andExpect(status().isCreated());
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeTematskogRekvizitaTest2() throws JsonProcessingException, Exception {
		rekvizit.setNazivRekvizita("Test2");
		rekvizit.setCenaRekvizita(50);
		rekvizit.setBroj(5);
		rekvizit.setOpisRekvizita("Opis rekvizita test 1");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit)));
		
		rekvizit.setCenaRekvizita(60);
		rekvizit.setOpisRekvizita("Novi opis");
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit))).andExpect(status().isBadRequest());
       
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeTematskogRekvizitaTest3() throws JsonProcessingException, Exception {
		rekvizit.setNazivRekvizita("Test3");
		rekvizit.setCenaRekvizita(50);
		rekvizit.setBroj(5);
		rekvizit.setOpisRekvizita("Opis rekvizita test 1");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit))).andExpect(status().isCreated());
		
		rekvizit.setNazivRekvizita("Test4");
		rekvizit.setCenaRekvizita(60);
		rekvizit.setOpisRekvizita("Novi opis");
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit))).andExpect(status().isCreated());
       
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void prikazRekvizitaTest() throws Exception {
		mockMvc.perform(get(URL_PREFIX+"/prikaziTematskeRekvizite").session(session)).andExpect(status().isOk());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void modifikovanjeRekvizitaInfoTest1() throws JsonProcessingException, Exception {
		String naziv = "Test5";
		rekvizit.setNazivRekvizita(naziv);
		rekvizit.setCenaRekvizita(50);
		rekvizit.setBroj(5);
		rekvizit.setOpisRekvizita("Opis rekvizita test 5");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit)));
		
		TematskiRekvizit tRekvizit = rep.findByNazivRekvizita(naziv);
		rekvizit.setCenaRekvizita(60);
		rekvizit.setBroj(4);
		rekvizit.setOpisRekvizita("Promenjen opis testa 5");
		
		mockMvc.perform(put(URL_PREFIX+"/modifikujTematskiRekvizitInformacije")
				.param("id", tRekvizit.getId()+"")
				.session(session)
				.param("rekvizit", TestUtil.toJson(rekvizit))).andExpect(status().isOk());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void modifikovanjeRekvizitaInfoTest2() throws JsonProcessingException, Exception {
		String naziv = "Test6";
		rekvizit.setNazivRekvizita(naziv);
		rekvizit.setCenaRekvizita(50);
		rekvizit.setBroj(5);
		rekvizit.setOpisRekvizita("Opis rekvizita test 6");

		
		rekvizit.setCenaRekvizita(60);
		rekvizit.setBroj(4);
		rekvizit.setOpisRekvizita("Promenjen opis testa 5");
		
		mockMvc.perform(put(URL_PREFIX+"/modifikujTematskiRekvizitInformacije")
				.param("id", 0+"") //nepostojeci id, trebalo bi da baci bad request
				.session(session)
				.param("rekvizit", TestUtil.toJson(rekvizit))).andExpect(status().isBadRequest());
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void modifikovanjeRekvizitaSlikaTest() throws JsonProcessingException, Exception {
		String naziv = "Test6";
		rekvizit.setNazivRekvizita(naziv);
		rekvizit.setCenaRekvizita(50);
		rekvizit.setBroj(5);
		rekvizit.setOpisRekvizita("Opis rekvizita test 6");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit)));
		
		TematskiRekvizit tRekvizit = rep.findByNazivRekvizita(naziv);
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/modifikujSlikuTematskogRekvizita")
				.file(mockFile) //"menjamo" sliku, nema potrebe praviti drugi fajl za testiranje koncepta
				.param("id", tRekvizit.getId()+"")
				.session(session))
				.andExpect(status().isOk());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void brisanjeTematskogRekvizita() throws JsonProcessingException, Exception {
		String naziv = "Test7";
		rekvizit.setNazivRekvizita(naziv);
		rekvizit.setOpisRekvizita("Opis rekvizita test 7");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit)));
		
		TematskiRekvizit tRekvizit = rep.findByNazivRekvizita(naziv);
		
		mockMvc.perform(put(URL_PREFIX+"/obrisiTematskiRekvizit")
				.session(session)
				.param("id", tRekvizit.getId()+"")).andExpect(status().isOk());
		
		//brisanje nepostojeceg bad request
		mockMvc.perform(put(URL_PREFIX+"/obrisiTematskiRekvizit")
				.session(session)
				.param("id", 0+"")).andExpect(status().isBadRequest());
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void pretrazivanjeTematskogRekvizita() throws Exception {
		mockMvc.perform(get(URL_PREFIX+"/pretraziTematskeRekvizite")
				.session(session)
				.param("nazivRekvizita", "Test")
				.param("donjaCena", 0.0+"")
				.param("gornjaCena", 1000.0+"")).andExpect(status().isOk());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Rollback(true)
	public void dodajObjavuTest() throws JsonProcessingException, Exception {
		
		Object prethodniUlogovani = session.getAttribute("korisnik");
		
		session.setAttribute("korisnik", korRep.findById(2).get());
		ObjavaDTO objavaDTO = new ObjavaDTO();
		objavaDTO.setDatumIsteka(sdf.parse("24.04.2019."));
		objavaDTO.setNaziv("Objava1");
		objavaDTO.setOpis("Opis najjaci");
		objavaDTO.setPutanjaDoSlike("");
		objavaDTO.setPonude(new ArrayList<PonudaDTO>());
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajObjavu")
				.session(session).param("objava",TestUtil.toJson(objavaDTO))).andExpect(status().isOk());
		
		//sad ce da padne jer cemo da podesimo datum koji je istekao
		objavaDTO.setDatumIsteka(sdf.parse("24.04.2017."));
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajObjavu")
				.session(session).param("objava",TestUtil.toJson(objavaDTO))).andExpect(status().isBadRequest());
		
		session.setAttribute("korisnik", prethodniUlogovani);
	}
	
	
	@Test
	@Rollback(true)
	public void pribaviObjavuTest() throws JsonProcessingException, Exception {
		
		Object prethodniUlogovani = session.getAttribute("korisnik");
		
		session.setAttribute("korisnik", korRep.findById(2).get());
		
		objava.setNaziv("KorisnikObjava");
		objava.setAutor((Korisnik)session.getAttribute("korisnik"));
		objava.setStatus(StatusObjave.OBJAVLJEN);
		objavaRep.save(objava);
		
		mockMvc.perform(get(URL_PREFIX+"/pribaviObjavu")
		.session(session)
		.param("id", ""+(objavaRep.findByNaziv("KorisnikObjava").getId())))
		.andExpect(status().isOk());
		
		mockMvc.perform(get(URL_PREFIX+"/pribaviObjavu")
				.session(session)
				.param("id", ""+(0))) //id koji sigurno ne postoji
				.andExpect(status().isBadRequest());
		
		session.setAttribute("korisnik", prethodniUlogovani);
	}
	
	@Test
	@Rollback(true)
	public void dodajPonuduNaObjavuTest() throws JsonProcessingException, Exception {
		
		Object prethodniUlogovani = session.getAttribute("korisnik");
		
		session.setAttribute("korisnik", korRep.findById(2).get());		
		objava.setNaziv("KorisnikObjava2");
		objava.setAutor((Korisnik)session.getAttribute("korisnik"));
		objava.setDatumIsteka(sdf.parse("21.12.2026."));
		objavaRep.save(objava);
		
		int idObjave = objavaRep.findByNaziv("KorisnikObjava2").getId();
		PonudaDTO ponudaDTO = new PonudaDTO();
		ponudaDTO.setCena(56);
		ponudaDTO.setIdObjave(idObjave);
		ponudaDTO.setNaslov("Ponudica");
		ponudaDTO.setOpis("Ovo je neki opis");
		//objava i ponuda ne smeju imati istog autora
		mockMvc.perform(post(URL_PREFIX+"/dodajPonudu")
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(ponudaDTO))).andExpect(status().isForbidden());
		
		ponudaDTO.setIdObjave(0);//nepostojeca objava na koju treba da se nakaci ponuda
		mockMvc.perform(post(URL_PREFIX+"/dodajPonudu")
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(ponudaDTO))).andExpect(status().isBadRequest());
		
		//pravimo novog korisnika koji ce postaviti ponudu na objavu prvog i to uspesno
		Korisnik kor2 = new Korisnik();
		korEmail = "kor2@kor2.com";
		kor2.setEmail(korEmail);
		kor2.setAktivan(true);
		kor2.setIme("Kor2");
		kor2.setPrezime("Koric2");
		korRep.save(kor2);
		
		//prebacujemo da je sad novi korisnik kor2 ulogovan
		session.setAttribute("korisnik", korRep.findById(3).get());
		
		ponudaDTO.setIdObjave(idObjave);
		mockMvc.perform(post(URL_PREFIX+"/dodajPonudu")
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(ponudaDTO))).andExpect(status().isOk());
		
		
		session.setAttribute("korisnik", prethodniUlogovani);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Rollback(true)
	public void rezervisanjeRekvizitaTest() throws JsonProcessingException, Exception {
		Object prethodniUlogovani = session.getAttribute("korisnik");
		//logujemo se kao admin
		session.setAttribute("korisnik", adminRep.findById(1).get());
		
		String naziv = "Test8";
		rekvizit.setNazivRekvizita(naziv);
		rekvizit.setCenaRekvizita(50);
		rekvizit.setBroj(1);
		rekvizit.setOpisRekvizita("Opis rekvizita test 8");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit)));
		
		TematskiRekvizit tRekvizit = rep.findByNazivRekvizita(naziv);
		//prebacujemo se na korisnika
		session.setAttribute("korisnik", korRep.findById(2).get());
		
		mockMvc.perform(put(URL_PREFIX+"/rezervisanjeRekvizita")
				.session(session)
				.param("id", ""+tRekvizit.getId())).andExpect(status().isOk());
		
		//rezervisanje nepostojeceg id ne bi trebalo da uspe
		mockMvc.perform(put(URL_PREFIX+"/rezervisanjeRekvizita")
				.session(session)
				.param("id", ""+0)).andExpect(status().isBadRequest());
		
		//da vidimo hoce li uspeti da rezervise rekvizite ako vise nisu dostupni
		mockMvc.perform(put(URL_PREFIX+"/rezervisanjeRekvizita")
				.session(session)
				.param("id", ""+tRekvizit.getId())).andExpect(status().isBadRequest());
		
		session.setAttribute("korisnik", prethodniUlogovani);
		
	}
	
	@Test
	@Rollback(true)
	public void prihvatiPonuduTest() throws JsonProcessingException, Exception {
		Object prethodniUlogovani = session.getAttribute("korisnik");
		
		String nazivObjave = "KorisnikObjava3";
		session.setAttribute("korisnik", korRep.findById(2).get());
		
		objava.setNaziv(nazivObjave);
		objava.setStatus(StatusObjave.OBJAVLJEN);
		objava.setAutor((Korisnik)session.getAttribute("korisnik"));
		objava.setDatumIsteka(sdf.parse("21.12.2026."));
		objavaRep.save(objava);
		
		int idObjave = objavaRep.findByNaziv(nazivObjave).getId();
		PonudaDTO ponudaDTO = new PonudaDTO();
		ponudaDTO.setCena(56);
		ponudaDTO.setIdObjave(idObjave);
		ponudaDTO.setNaslov("Ponudica hehe");
		ponudaDTO.setOpis("Ovo je neki opis");
		
		//pravimo novog korisnika koji ce postaviti ponudu na objavu prvog i to uspesno
		Korisnik kor2 = new Korisnik();
		korEmail = "kor3@kor3.com";
		kor2.setEmail(korEmail);
		kor2.setAktivan(true);
		kor2.setIme("Kor2");
		kor2.setPrezime("Koric2");
		korRep.save(kor2);
		
		//prebacujemo da je sad novi korisnik kor2 ulogovan
		session.setAttribute("korisnik", korRep.findByEmail(korEmail));
		
		ponudaDTO.setIdObjave(idObjave);
		mockMvc.perform(post(URL_PREFIX+"/dodajPonudu")
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(ponudaDTO)));
		
		session.setAttribute("korisnik", korRep.findById(2).get());
		Ponuda p = ponudaRep.pronadjiPonudu(korRep.findByEmail(korEmail).getId(),idObjave);
		mockMvc.perform(put(URL_PREFIX+"/prihvatiPonudu")
				.session(session)
				.param("id",""+p.getId())).andExpect(status().isOk());
		
		mockMvc.perform(put(URL_PREFIX+"/prihvatiPonudu")
				.session(session)
				.param("id",""+0)).andExpect(status().isBadRequest());
		
		session.setAttribute("korisnik", prethodniUlogovani);
	}
	
	@Test
	@Rollback(true)
	public void pribaviObavestenjaTest() throws Exception {
		Object prethodniUlogovani = session.getAttribute("korisnik");
		mockMvc.perform(get(URL_PREFIX+"/pribaviObavestenja")
				.session(session)).andExpect(status().isForbidden());
		
		session.setAttribute("korisnik", korRep.findById(2).get());
		
		mockMvc.perform(get(URL_PREFIX+"/pribaviObavestenja")
				.session(session)).andExpect(status().isOk());
		session.setAttribute("korisnik", prethodniUlogovani);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void obrisiObavestenjeTest() throws Exception {
		Object prethodniUlogovani = session.getAttribute("korisnik");
		session.setAttribute("korisnik", korRep.findById(2).get());
		mockMvc.perform(put(URL_PREFIX+"/obrisiObavestenje")
				.session(session).param("id", ""+0)).andExpect(status().isBadRequest());
		
		for(PonudaNotifikacija not:notifikacijaRep.findAll()) {
			session.setAttribute("korisnik", not.getPonuda().getAutor());
			mockMvc.perform(put(URL_PREFIX+"/obrisiObavestenje")
				.session(session).param("id", ""+not.getId())).andExpect(status().isOk());
		}
		
		session.setAttribute("korisnik", prethodniUlogovani);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void pribaviRezervacijeRekvizitaTest() throws Exception {
		
		Object prethodniUlogovani = session.getAttribute("korisnik");
		
		mockMvc.perform(get(URL_PREFIX+"/pribaviRezervacijeRekvizita")
				.session(session)).andExpect(status().isForbidden());
		session.setAttribute("korisnik", korRep.findById(2).get());
		
		mockMvc.perform(get(URL_PREFIX+"/pribaviRezervacijeRekvizita")
				.session(session)).andExpect(status().isOk());
		
		session.setAttribute("korisnik", prethodniUlogovani);
	}
	
	
	
	@After
	public void obrisiSveTestFajlove() {
		File folder = new File("slike/");
		File[] listaFajlova = folder.listFiles();

	    for (int i = 0; i < listaFajlova.length; i++) {
	      if (listaFajlova[i].isFile() && listaFajlova[i].getName().startsWith("Test")) {
	       listaFajlova[i].delete();
	      }
	    }
	}
	
	
}
