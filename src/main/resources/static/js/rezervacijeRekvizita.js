var rezervacijaRekvizitaPutanja = "/fanzona/pribaviRezervacijeRekvizita";
var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";
var ukupnaCena = 0;

function dodajPrikazRezervacijeRekvizita(kljuc,podaci){
	var noviElement = $("<div class = \"rezRekvizita\" id=\""+kljuc+"\" ></div>");
	var html = "<h3>"+podaci["nazivRekvizita"]+"</h3>";
	html = html +  "<p> Broj komada: "+podaci["brojRekvizita"]+"</p>";
	html = html +  "<p> Ukupna cena: "+podaci["ukupnaCena"]+"</p>";
	html = html +  "<img width=\"225\" height=\"225\" src=\""+putanjaDoSlika+podaci["putanjaDoSlike"]+"\"></img>";
	noviElement.html(html);
	$("#prikazRezervacija").append(noviElement);
	ukupnaCena = ukupnaCena + podaci["ukupnaCena"];
}

$(document).ready(function(){
	
	$.ajax({ url:rezervacijaRekvizitaPutanja,
		   type: "GET",
		   success: function(data){
			  var txt = $("<p id=\"ukCena\">Ukupna cena: " +ukupnaCena+"</p>")
			  $("#prikazRezervacija").append(txt);
			  for(var i in data){
				  dodajPrikazRezervacijeRekvizita(i,data[i]);
			  }
			 $("#ukCena").text("Ukupna cena: "+ukupnaCena);
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
});