
 var dodajProjekciju = "/dodajProjekciju";


function regFormaUJSON(naziv, spisak_glumaca, ime_reditelja, zanr, trajanje, slika, opis, cena, lista_sala) {
	return JSON.stringify({
		"naziv":naziv,
		"spisak_glumaca":spisak_glumaca,
		"ime_reditelja":ime_reditelja,
		"zanr":zanr,
		"trajanje":trajanje,
		"slika":slika,
		"opis":opis,
		"cena":cena,
		"lista_sala": lista_sala
	});
}


function formaNevalidna(){
	for(var i=0;i<arguments.length;i++){
		if(arguments[i]===undefined || arguments[i] ===null || arguments[i] === ""){
			return true;
		}
	}
	return false;
}

$(document).ready(function() {
	$("#dodaj_salu").click(function(event) {
		lista_sala.push();
	})
	$("#dodaj_termin").click(function(event) {
		lista_termina.push();
	})
	$("#dodaj_projekciju").click(function(event) {
		var naziv = $("input[name=naziv]").val();
		var spisak_glumaca = $("input[name=spisak_glumaca]").val();
		var ime_reditelja = $("input[name=ime_reditelja]").val();
		var zanr = $("input[name=zanr]").val();
		var trajanje =  $("input[name=trajanje]").val();
		var slika = $("input[name=slika]").val();
		var opis = $("input[name=opis]").val();
		var cena = $("input[name=cena]").val();
		
		
		if(formaNevalidna(naziv, spisak_glumaca, ime_reditelja, zanr, trajanje, opis, cena, lista_sala, lista_projekcija)){
			alert("Potrebno je popuniti sva polja! ");
			return;
		}
		
		
		$.ajax({
			type: "POST",
			url: dodajProjekciju,
			contentType : "application/json; charset=utf-8",
			dataType: "json",
			data: regFormaUJSON(naziv, spisak_glumaca, ime_reditelja, zanr, trajanje, opis, cena, lista_sala, lista_projekcija),
			complete: function(data,status){
				if(status!="error"){
					window.location = '/';
				}else{
					alert("Projekcija vec postoji!" );
				}
			},
			failure : function(error) {
				alert("Desila se greska!" );
			}
		});
	});
});
