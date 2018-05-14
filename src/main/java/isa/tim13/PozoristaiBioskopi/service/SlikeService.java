package isa.tim13.PozoristaiBioskopi.service;

import java.io.File;
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
	
	//da osiguramo da nema fajlova koji se isto zovu
	public String pribaviKonacnuPutanju(StringBuilder putanjaDoSlikeRekvizita,MultipartFile file) {
		String ekstenzija = pribaviEkstenziju(file);
		int count = 0;
		File f = new File(putanjaDoSlikeRekvizita.toString()+count+"."+ekstenzija);
		
		while(f.exists()) {
			count++;
			f = new File(putanjaDoSlikeRekvizita.toString()+count+"."+ekstenzija);
		}
		
		putanjaDoSlikeRekvizita.append(count+"."+ekstenzija);
		return putanjaDoSlikeRekvizita.toString();
	}
	

	public void sacuvajSliku(String putanjaDoSlikeRekvizita, MultipartFile file) throws IOException {
		
		FileOutputStream fos = new FileOutputStream(putanjaDoSlikeRekvizita);
		fos.write(file.getBytes());
		fos.close();
		
	}

	public void obrisiStaruSliku(String putanjaDoSlike) {
		File f = new File(putanjaDoSlike);
		f.delete();
		
	}

	
	
	
}
