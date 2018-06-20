package isa.tim13.PozoristaiBioskopi;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.util.Optional;

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

import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.repository.InstitucijaKultureRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RepertoarControllerTest {

	private static final String URL_PREFIX = "/repertoarC";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private InstitucijaKultureRepository repozitorijum;

	private InstitucijaKulture institucija = new InstitucijaKulture();

	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Before
	public void before() {
		Optional<InstitucijaKulture> zaBrisanje = repozitorijum.findById(institucija.getId());
		if (zaBrisanje.isPresent()) {
			repozitorijum.delete(zaBrisanje.get());
			zaBrisanje = null;
		}
	}

	@After
	public void after() {
		Optional<InstitucijaKulture> zaBrisanje = repozitorijum.findById(institucija.getId());
		if (zaBrisanje.isPresent()) {
			repozitorijum.delete(zaBrisanje.get());
			zaBrisanje = null;
		}
	}
	
	@Test
	public void repertoarTest() throws Exception {
		repozitorijum.save(institucija);
		mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("id", institucija.getId()+""))
				.session(session))
				.andExpect(status().isMovedTemporarily());
		assertNotNull(session.getAttribute("institucija"));
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
