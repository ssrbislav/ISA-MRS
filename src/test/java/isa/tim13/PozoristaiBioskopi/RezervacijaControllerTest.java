package isa.tim13.PozoristaiBioskopi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.hibernate.type.LocalDateTimeType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import isa.tim13.PozoristaiBioskopi.controllers.RegistracioniController;
import isa.tim13.PozoristaiBioskopi.controllers.RezervacijaController;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.model.Rezervacija;
import isa.tim13.PozoristaiBioskopi.model.Sala;
import isa.tim13.PozoristaiBioskopi.model.Termin;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;
import isa.tim13.PozoristaiBioskopi.repository.PredstavaProjekcijaRepository;
import isa.tim13.PozoristaiBioskopi.repository.SalaRepository;
import isa.tim13.PozoristaiBioskopi.repository.TerminRepository;
import isa.tim13.PozoristaiBioskopi.service.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RezervacijaControllerTest {

	private static final String URL_PREFIX = "/rezervacija";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	PredstavaProjekcijaRepository predstavaProjekcijaRep;
	
	@Autowired
	TerminRepository terminRep;
	
	@Autowired
	SalaRepository salaRep;
	
	@Autowired
	private KorisnikRepository korisnikRep;

	@InjectMocks
	@Autowired
	private RezervacijaController rezervacijaController;
	
	@Mock
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Mock
	private EmailService emailThread;
	
	private PredstavaProjekcija pp;
	private Termin termin1;
	private Termin termin2;
	private Sala sala1;
	private Korisnik korisnik;

	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		doNothing().when(taskExecutor).execute(emailThread);
	}
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);

		predstavaProjekcijaRep.deleteAll();
		terminRep.deleteAll();
		korisnikRep.deleteAll();
		
		korisnik = noviKorisnik();
		korisnikRep.save(korisnik);
		pp = new PredstavaProjekcija();
		sala1 = new Sala();
		termin1 = new Termin();
		termin2 = new Termin();
		
		
		sala1.setBrojKolona(3);
		sala1.setBrojVrsta(4);
		salaRep.save(sala1);
		
		termin1.setVreme(LocalTime.parse("12:30"));
		termin2.setVreme(LocalTime.now());
		
		termin1.setDatum(LocalDate.parse("9999-11-12"));
		termin2.setDatum(LocalDate.now());
		termin1.setSala(sala1);
		termin2.setSala(sala1);
		termin1.setpredProj(pp);
		termin2.setpredProj(pp);
		
		ArrayList<Termin> termini = new ArrayList<Termin>();
		termini.add(termin1);
		termini.add(termin2);
		pp.setTermini(termini);
		pp.setIme_reditelja("TEST");
		predstavaProjekcijaRep.save(pp);
	}

	@After
	public void after() {
		predstavaProjekcijaRep.deleteAll();
		terminRep.deleteAll();
		korisnikRep.deleteAll();
	}
	
	@Test
	public void terminiTest() throws Exception {
		mockMvc.perform(post(URL_PREFIX+"/termin").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("id", ""+pp.getId()))
				.session(session))
				.andExpect(status().isMovedTemporarily());
		assertNotNull(session.getAttribute("predstavaZaRezervaciju"));
		assertEquals(1, ((ArrayList<String>)session.getAttribute("datumiPrikazivanja")).size());
	}
	
	@Test
	public void odabirMestaTest() throws Exception {
		mockMvc.perform(post(URL_PREFIX+"/mesta").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("id", ""+pp.getId()))
				.session(session))
				.andExpect(status().isOk());
		assertNotNull(session.getAttribute("rezervacija"));
	}
	
	@Test
	public void zakazivanjeTest() throws Exception {
		Rezervacija r = new Rezervacija();
		r.setTermin(termin1);
		session.setAttribute("rezervacija", r);
		session.setAttribute("korisnik", korisnik);
		mockMvc.perform(post(URL_PREFIX+"/zakazivanje").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("odabranaMesta", "1_2-"))
				.session(session))
				.andExpect(status().isOk());
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
	
	private Korisnik noviKorisnik() {
		Korisnik noviKorisnik = new Korisnik();
		noviKorisnik.setEmail("test");
		noviKorisnik.setIme("test");
		noviKorisnik.setPrezime("test");
		noviKorisnik.setTelefon("test");
		noviKorisnik.setGrad("test");
		noviKorisnik.setLozinka("test");

		noviKorisnik.setRegistracioniLink("test");
		noviKorisnik.setAktivan(true);
		noviKorisnik.setLokacijaSlike("");
		noviKorisnik.setBrojBodova(0);
		noviKorisnik.setIstorijatPoseta(new ArrayList<PredstavaProjekcija>());
		noviKorisnik.setPrijatelji(new ArrayList<Korisnik>());
		noviKorisnik.setZahtevi(new ArrayList<Korisnik>());
		return noviKorisnik;
	}

}
