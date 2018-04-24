var registrujInstitucijuPutanja = "/pozoristaibioskopi/registruj";
var prikazSvihInstitucijaPutanja = "/pozoristaibioskopi"


function regFormaUJSON(naziv,adresa,telefon,opis,tip){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"telefon":telefon,
		"opis":opis,
		"tip":tip
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



$(document).ready(function(){
	
	
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
		
		$.ajax({
			url: registrujInstitucijuPutanja,
			contentType : "application/json",
			success: function(result){
				alert("Uspesno ste registrovali pozoriste ili bioskop");
			},
			type: "POST",
			dataType: "text",
			data: regFormaUJSON(naziv,adresa,telefon,opis,tip),
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("Desila se greska: " + errorThrown);
			}
			
		});
		
	});
	
	
});