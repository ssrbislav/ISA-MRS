var registrujInstitucijuPutanja = "/pozoristaibioskopi/registruj";


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