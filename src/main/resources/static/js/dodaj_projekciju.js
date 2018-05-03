
 var dodajProjekciju = "/dodajProjekciju";

function regFormaUJSON(naziv, spisak_glumaca, reditelj, zanr, trajanje, naziv_slike, opis, cena, lista_sala, lista_termina) {
	return JSON.stringify({
		"naziv":naziv,
		"spisak_glumaca":spisak_glumaca,
		"ime_reditelja":reditelj,
		"zanr":zanr,
		"trajanje":trajanje,
		"slika":slika,
		"opis":opis,
		"cena":cena,
		"lista_sala": lista_sala,
		"lista_termina": lista_termina
}


function formaNevalidna(){
	for(var i=0;i<arguments.length;i++){
		if(arguments[i]===undefined || arguments[i] ===null || arguments[i] === ""){
			return true;
		}
	}
	return false;
}

function popuniStavke(items){
	
	$.each(items, function (i, item) {
		var newOption = $("<option></option>");
		newOption.attr("value",item).text(item);
	    $('select[name=lista_sala]').append(newOption);
   
	});
}


function prikaziListuSala(){
	var ls = $("select[id=lista_sala]");
		$("#izabrane_sale").show();
}


$(document).ready(function() {
	
	$("#dodaj_projekciju").click(function(event) {
		var naziv = $("input[name=naziv]").val();
		var spisak_glumaca = $("input[name=spisak_glumaca]").val();
		var ime_reditelja = $("input[name=reditelj]").val();
		var zanr = $("input[name=zanr]").val();
		var trajanje =  $("input[name=trajanje]").val();
		var slika = $("input[name=naziv_slike]").val();
		var opis = $("input[name=opis]").val();
		var cena = $("input[name=cena]").val();
		
		
		if(formaNevalidna(naziv, spisak_glumaca, reditelj, zanr, trajanje, opis, cena, lista_sala, lista_termina)){
			alert("Potrebno je popuniti sva polja! ");
			return;
		}
		
		
		$.ajax({
			type: "POST",
			url: dodajProjekciju,
			contentType : "application/json; charset=utf-8",
			dataType: "json",
			data: regFormaUJSON(naziv, spisak_glumaca, reditelj, zanr, trajanje, opis, cena, lista_sala, lista_termina),
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
