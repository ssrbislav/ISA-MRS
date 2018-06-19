package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrijateljDTO {
	
		@JsonProperty(value="ime")
		private String ime;
		
		@JsonProperty(value="prezime")
		private String prezime;
		
		@JsonProperty(value="email")
		private String email;

		@JsonProperty(value="lokacijaSlike")
		private String lokacijaSlike;	
		
		@JsonProperty(value="id")
		private int id;

		public String getIme() {
			return ime;
		}

		public void setIme(String ime) {
			this.ime = ime;
		}

		public String getPrezime() {
			return prezime;
		}

		public void setPrezime(String prezime) {
			this.prezime = prezime;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLokacijaSlike() {
			return lokacijaSlike;
		}

		public void setLokacijaSlike(String lokacijaSlike) {
			this.lokacijaSlike = lokacijaSlike;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		

}
