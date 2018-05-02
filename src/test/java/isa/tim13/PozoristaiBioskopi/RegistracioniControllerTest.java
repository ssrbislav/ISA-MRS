package isa.tim13.PozoristaiBioskopi;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import isa.tim13.PozoristaiBioskopi.controllers.RegistracioniController;
import isa.tim13.PozoristaiBioskopi.dto.RegisterDTO;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;
import isa.tim13.PozoristaiBioskopi.service.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistracioniControllerTest {

private static final String URL_PREFIX = "/registerUser";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Mock
	private EmailService emailService;
	
	@InjectMocks
	@Autowired
	private RegistracioniController registracioniController;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private KorisnikRepository repozitorijum;
	
	private RegisterDTO registracija;
	

	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		registracija = testKorisnik();
	}
	
	@Before
	public void before() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(emailService).sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Korisnik zaBrisanje = repozitorijum.findByEmail(registracija.getEmail());
        if(zaBrisanje != null) {
        	repozitorijum.delete(zaBrisanje);
        	zaBrisanje = null;
        }
	}
	
	@After
	public void after() {
        Korisnik zaBrisanje = repozitorijum.findByEmail(registracija.getEmail());
        if(zaBrisanje != null) {
        	repozitorijum.delete(zaBrisanje);
        	zaBrisanje = null;
        }
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void registracioniTest() throws JsonProcessingException, Exception {
		mockMvc.perform(post(URL_PREFIX)
		.contentType(contentType)
		.content(TestUtil.toJson(registracija))).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void registracioniTestKorisnikVecPostoji() throws JsonProcessingException, Exception {
		mockMvc.perform(post(URL_PREFIX)
				.contentType(contentType)
				.content(TestUtil.toJson(registracija))).andExpect(status().isOk());

		mockMvc.perform(post(URL_PREFIX)
				.contentType(contentType)
				.content(TestUtil.toJson(registracija))).andExpect(status().isNotModified());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void registracioniTestLozinkaSeNePoklapa() throws JsonProcessingException, Exception {
		registracija.setLozinka2("neSlazeSe");
		mockMvc.perform(post(URL_PREFIX)
				.contentType(contentType)
				.content(TestUtil.toJson(registracija))).andExpect(status().isNotModified());
		registracija.setLozinka2(registracija.getLozinka1());
	}
	
	private RegisterDTO testKorisnik() {
		RegisterDTO registracija = new RegisterDTO();
		registracija.setEmail("tpozoristaibioskopi@gmail.com");
		registracija.setIme("test");
		registracija.setPrezime("test");
		registracija.setTelefon("test");
		registracija.setGrad("test");
		registracija.setLozinka1("test");
		registracija.setLozinka2("test");
		return registracija;
	}
}
