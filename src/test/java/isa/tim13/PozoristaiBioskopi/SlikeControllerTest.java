package isa.tim13.PozoristaiBioskopi;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SlikeControllerTest {
	private static final String URL_PREFIX = "/upravljanjeSlikama";
	
	@Autowired
	private MockMvc mockMvc;
	
	private String putanjaDoSlike = "";
	private File f;
	
	@PostConstruct
	public void setup() throws IOException {
		//kontroler dobavlja slike, u stvari fajlove, pa to i testiramo na principu praznog fajla
		putanjaDoSlike = "testSlikaController.jpg";
		f = new File(putanjaDoSlike);
		f.createNewFile();
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void dobavljanjeSlikeTest() throws Exception {
		mockMvc.perform(get(URL_PREFIX).param("putanjaDoSlike", putanjaDoSlike)).andExpect(status().isOk());
		f.delete();
	}
}
