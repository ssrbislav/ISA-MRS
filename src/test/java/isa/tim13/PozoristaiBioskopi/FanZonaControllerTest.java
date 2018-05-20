package isa.tim13.PozoristaiBioskopi;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.After;
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
import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;
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
    MockHttpSession session;
	
	

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private RekvizitDTO rekvizit;
	
	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Administrator a = new FanZonaAdministrator();
		a.setAktivan(true);
		a.setIme("Test");
		a.setPrezime("Testic");
		a.setEmail("majic@majic.com");
		session.setAttribute("korisnik", a);
		rekvizit = new RekvizitDTO();
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
