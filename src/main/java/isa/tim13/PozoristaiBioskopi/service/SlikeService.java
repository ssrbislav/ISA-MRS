package isa.tim13.PozoristaiBioskopi.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SlikeService {

	public byte[] dobaviSliku(String putanjaDoSlike) throws IOException {
		Path putanja = Paths.get(putanjaDoSlike);
		byte[] podaci = Files.readAllBytes(putanja);
		return podaci;
	}
	
	public String pribaviEkstenziju(MultipartFile file) {
		return file.getOriginalFilename().split("\\.")[1];
	}

	public void sacuvajSliku(String putanjaDoSlikeRekvizita, MultipartFile file) throws IOException {
		
		FileOutputStream fos = new FileOutputStream(putanjaDoSlikeRekvizita);
		fos.write(file.getBytes());
		fos.close();
		
	}
	
	
	
}
