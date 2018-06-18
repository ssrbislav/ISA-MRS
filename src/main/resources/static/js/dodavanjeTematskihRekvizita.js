var dodavanjeTematskihRekvizitaPutanja = "/fanzona/dodajTematskiRekvizit";

function formaNevalidna(){
	for(var i=0;i<arguments.length;i++){
		if(arguments[i]===undefined || arguments[i] ===null || arguments[i] === ""){
			return true;
		}
	}
	
	return false;
}


function rekvizitFormaUJSON(nazivRekvizita,opisRekvizita,cenaRekvizita,broj){
	return JSON.stringify({
		"nazivRekvizita":nazivRekvizita,
		"opisRekvizita":opisRekvizita,
		"cenaRekvizita":cenaRekvizita,
		"broj":broj
	});
}


$(document).ready(function(){
	$("#dodavanjeRekvizitaDugme").click(function(event){
		
		
		var nazivRekvizita = $("input[name=nazivRekvizita]").val();
		var opisRekvizita = $("textarea[name=opisRekvizita]").val();
		var cenaRekvizita = $("input[name=cenaRekvizita]").val();
		var broj = $("input[name=broj]").val();
		var slikaPodaci = $("input[name=file]")[0].files[0];
		
		if(broj <0){
			alert("Broj rekvizita ne moze biti manji od 0!");
			return;
		}
		
		if(formaNevalidna(nazivRekvizita,opisRekvizita,cenaRekvizita,broj)){
			alert("Niste popunili sva polja u formi za dodavanje.");
			return;
		}
		
		if($("input[name=file]").val() == ''){
			alert("Niste selektovali sliku ");
			return;
		}
		
		var allData = new FormData();
		allData.append("rekvizit",rekvizitFormaUJSON(nazivRekvizita,opisRekvizita,cenaRekvizita,broj));
		allData.append("file",slikaPodaci);
		
		$.ajax({
			url: dodavanjeTematskihRekvizitaPutanja,
			success: function(result){
				alert(result);
			},
			type: "POST",
			dataType: "text",
			data: allData,
			mimeType: "multipart/form-data",
			contentType: false,
			cache: false,
			processData: false,
			error : function(xhr, textStatus, errorThrown) {
				var err = JSON.parse(xhr["responseText"])["message"];
				alert(err);
			}
			
		});
		
	});
});