
var registrujInstitucijuPutanja = "/pozoristaibioskopi/registruj";
var prikazSvihInstitucijaPutanja = "/pozoristaibioskopi"

sale = [];

function regFormaUJSON(naziv,adresa,telefon,opis,tip,sale){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"telefon":telefon,
		"opis":opis,
		"tip":tip,
		"sale": sale
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


function dodajSaluUListu(){
	var oznaka_sale = $("input[name=nazivSale]").val();
	var broj_vrsta = $("input[name=brojVrstaSale]").val();
	var broj_kolona = $("input[name=brojKolonaSale]").val();
	
	if(formaNevalidna(oznaka_sale,broj_vrsta,broj_kolona)){
		alert("Morate popuniti sva polja za unos sale.");
		return;
	}
	var novaSala = {};
	novaSala.oznaka_sale = oznaka_sale;
	novaSala.broj_vrsta = broj_vrsta;
	novaSala.broj_kolona = broj_kolona;
	sale.push(novaSala);
	prikazSala();
}

function obrisiSalu(index){
	sale.splice(index,1);
	prikazSala();
}


function prikazSala() {
	var resultHtml = "<table border=\"1\"><tr bgcolor=\"lightgrey\">";
    resultHtml += "<tr><th>Oznaka sale</th><th>Broj vrsta</th><th>Broj kolona</th><th>Opcija</th></tr>";	   
	   for ( i=0;i < sale.length;i++){
		   
		   resultHtml += "<tr><td>"+sale[i]["oznaka_sale"]+"</td>"+"<td>"+sale[i]["broj_vrsta"]+"</td>"+"<td>"+sale[i]["broj_kolona"]+"</td>"+"<td><input type=\"button\" value=\"obrisi\" onclick=\"obrisiSalu("+i+")\">"+"</td>"+"</tr>";
	   }
	   resultHtml += "</table>"
	
    $("#prikazDodatihSala").html(resultHtml);
	  
}


$(document).ready(function(){
	
	
	$("#dodavanjeSaleDugme").click(dodajSaluUListu);
	
	$("#prikazInstitucijaDugme").click(function(event){
		$.ajax({ url:prikazSvihInstitucijaPutanja,
			   type: "GET",
			   success: function(data){
				   var resultHtml = "<table border=\"1\"><tr bgcolor=\"lightgrey\">";
				   resultHtml += "<tr><th>id</th><th>Naziv</th><th>Adresa</th><th>Telefon</th><th>Opis</th><th>Tip</th></tr>";
				   
				   
				   for ( i=0;i < data.length;i++){
					   
					   resultHtml += "<tr><td>"+data[i]["id"]+"</td>"+"<td>"+data[i]["naziv"]+"</td><td>"+data[i]["adresa"]+"</td><td>"+data[i]["telefon"]+"</td><td>"+data[i]["opis"]+"</td><td>"+data[i]["tip"]+"</td></tr>";
				   }
				   resultHtml += "</table>"
				   $("#prikaz").html(resultHtml);
			   },
			   error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("Desila se greska: " + errorThrown);
				}
			   
	    });
	});
	
	$("#registracijaDugme").click(function(event){
		
		var naziv = $("input[name=naziv]").val();
		var adresa = $("input[name=adresa]").val();
		var telefon = $("input[name=telefon]").val();
		var opis = $("input[name=opis]").val();
		var tip =  $("select[name=tip]").val();;
		
		if(formaNevalidna(naziv,adresa,telefon,opis,tip)){
			alert("Niste popunili sva polja kako treba! ");
			return;
		}
		
		if(sale.length==0){
			alert("Morate dodati bar jednu salu!");
			return;
		}
		
		$.ajax({
			url: registrujInstitucijuPutanja,
			contentType : "application/json",
			success: function(result){
				alert("Uspesno ste registrovali pozoriste ili bioskop");
			},
			type: "POST",
			dataType: "text",
			data: regFormaUJSON(naziv,adresa,telefon,opis,tip,sale),
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("Desila se greska: " + errorThrown);
			}
			
		});
		
	});
	
	
});