package isa.tim13.PozoristaiBioskopi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.model.Sala;
import isa.tim13.PozoristaiBioskopi.model.Termin;
import isa.tim13.PozoristaiBioskopi.repository.PredstavaProjekcijaRepository;
import isa.tim13.PozoristaiBioskopi.repository.TerminRepository;

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
	PredstavaProjekcijaRepository ppRep;
	
	@Autowired
	TerminRepository terminRep;
	
	private PredstavaProjekcija pp = new PredstavaProjekcija();
	private Termin termin1 = new Termin();
	private Termin termin2 = new Termin();
	private Sala sala1 = new Sala();

	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}
	
	@Before
	public void before() {
		ppRep.deleteAll();
		terminRep.deleteAll();
		sala1.setBrojKolona(3);
		sala1.setBrojVrsta(4);
		termin1.setDatum("1");
		termin2.setDatum("1");
		termin1.setSala(sala1);
		termin2.setSala(sala1);
		ArrayList<Termin> termini = new ArrayList<Termin>();
		termini.add(termin1);
		termini.add(termin2);
		pp.setTermini(termini);
		ppRep.save(pp);
	}

	@After
	public void after() {
		ppRep.deleteAll();
		terminRep.deleteAll();
		
	}
	
	@Test
	public void terminiTest() throws Exception {
		mockMvc.perform(post(URL_PREFIX+"/termin").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("id", ""+pp.getId()))
				.session(session))
				.andExpect(status().isOk());
		assertNotNull(session.getAttribute("predstavaZaRezervaciju"));
		assertEquals(1, ((ArrayList<String>)session.getAttribute("datumiPrikazivanja")).size());
	}
	
	@Test
	public void rezervacijaTest() throws Exception {
		mockMvc.perform(post(URL_PREFIX+"/mesta").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("id", ""+pp.getId()))
				.session(session))
				.andExpect(status().isOk());
		assertNotNull(session.getAttribute("rezervacija"));
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
