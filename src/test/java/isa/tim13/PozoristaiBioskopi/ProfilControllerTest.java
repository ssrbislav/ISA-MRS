package isa.tim13.PozoristaiBioskopi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

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

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfilControllerTest {

	private static final String URL_PREFIX = "/profil";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private KorisnikRepository repozitorijum;
	
	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Before
	public void before() {
		session.setAttribute("korisnik", noviKorisnik());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeSlikeKorisnikuTest() throws Exception{
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajSliku")
                .file(mockFile)
                .session(session))
            .andExpect(status().isMovedTemporarily());
		Korisnik testKorisnik = (Korisnik)session.getAttribute("korisnik");
		assertNotEquals("", testKorisnik.getLokacijaSlike());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void nepostojecaSlikaTest() throws Exception{
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
		MockMultipartFile mockFile = new MockMultipartFile("file", "", "image/jpg", inputStream);
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajSliku")
                .file(mockFile)
                .session(session))
            .andExpect(status().isMovedTemporarily());
		Korisnik testKorisnik = (Korisnik)session.getAttribute("korisnik");
		assertEquals("", testKorisnik.getLokacijaSlike());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void promenaLozinkeTest() throws Exception{	
		Korisnik testKorisnik = (Korisnik)session.getAttribute("korisnik");
		repozitorijum.save(testKorisnik);
		mockMvc.perform(post(URL_PREFIX+"/izmenaLozinke").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
				.content(buildUrlEncodedFormEntity("lozinka1", "novaLozinka")))
				.andExpect(status().isMovedTemporarily());
		testKorisnik = (Korisnik)session.getAttribute("korisnik");
		assertEquals("novaLozinka", testKorisnik.getLozinka());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void promenaPodatakaTest() throws Exception{	
		Korisnik testKorisnik = (Korisnik)session.getAttribute("korisnik");
		repozitorijum.save(testKorisnik);
		mockMvc.perform(post(URL_PREFIX+"/izmenaPodataka").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
				.content(buildUrlEncodedFormEntity("ime", "novoIme", "prezime", "novoPrezime", "grad", "noviGrad", "telefon", "noviTelefon")))
				.andExpect(status().isMovedTemporarily());
		testKorisnik = (Korisnik)session.getAttribute("korisnik");

		assertEquals("novoIme", testKorisnik.getIme());
		assertEquals("novoPrezime", testKorisnik.getPrezime());
		assertEquals("noviGrad", testKorisnik.getGrad());
		assertEquals("noviTelefon", testKorisnik.getTelefon());
	}
	
	
	
	private Korisnik noviKorisnik() {
		Korisnik noviKorisnik = new Korisnik();
		noviKorisnik.setEmail("test");
		noviKorisnik.setIme("test");
		noviKorisnik.setPrezime("test");
		noviKorisnik.setTelefon("test");
		noviKorisnik.setGrad("test");
		noviKorisnik.setLozinka("test");
		
		noviKorisnik.setRegistracioniLink("test");
		noviKorisnik.setAktivan(false);
		noviKorisnik.setLokacijaSlike("");
		noviKorisnik.setBrojBodova(0.0);
		noviKorisnik.setIstorijatPoseta(new ArrayList<PredstavaProjekcija>());
		noviKorisnik.setPrijatelji(new ArrayList<Korisnik>());
		noviKorisnik.setZahtevi(new ArrayList<Korisnik>());
		return noviKorisnik;
	}
	
	public String buildUrlEncodedFormEntity(String... params) {
		if ((params.length % 2) > 0) {
			throw new IllegalArgumentException("Need to give an even number of parameters");
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < params.length; i += 2) {
			if (i > 0)
				result.append('&');
			result.append(URLEncoder.encode(params[i])).append('=').append(URLEncoder.encode(params[i + 1]));
		}
		return result.toString();
	}
}
