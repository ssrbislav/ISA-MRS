package isa.tim13.PozoristaiBioskopi;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.InputStream;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.Objava;
import isa.tim13.PozoristaiBioskopi.model.StatusObjave;
import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;
import isa.tim13.PozoristaiBioskopi.repository.AdministratoriRepository;
import isa.tim13.PozoristaiBioskopi.repository.ObjavaRepository;
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
    MockHttpSession session;
	
	

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private RekvizitDTO rekvizit;
	private Objava objava;
	
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
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
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
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
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
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
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
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
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
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
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
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
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
