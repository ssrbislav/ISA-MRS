var prikazTematskihRekvizitaPutanja = "/fanzona/prikaziTematskeRekvizite";
var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";
var modifikovanjeTematskihRekvizitaInfoPutanja = "/fanzona/modifikujTematskiRekvizitInformacije";
var modifikovanjeTematskihRekvizitaSlikaPutanja = "/fanzona/modifikujSlikuTematskogRekvizita";
var brisanjeTematskihRekvizitaPutanja = "/fanzona/obrisiTematskiRekvizit";
var pretragaTematskihRekvizitaPutanja = "/fanzona/pretraziTematskeRekvizite";
var rezervacijaRekvizitaPutanja = "/fanzona/rezervisanjeRekvizita";
var fanZonaAdmin = false;

function formaNevalidna(){
	for(var i=0;i<arguments.length;i++){
		if(arguments[i]===undefined || arguments[i] ===null || arguments[i] === ""){
			return true;
		}
	}
	
	return false;
}

function prikaziDijalog(id){
	$("#izmenaDijalog").show();
    $("div[role=dialog]").show();
    
	$("#izmenaIdRekvizita").text(id);
	$("#izmenaNazivRekvizita").val($("#"+napraviId("nazivRekvizita",id)).text());
	$("#izmenaOpisRekvizita").text($("#"+napraviId("opisRekvizita",id)).text());
	$("#izmenaCenaRekvizita").val($("#"+napraviId("cenaRekvizita",id)).text());
	$("#izmenaBrojaRekvizita").val($("#"+napraviId("broj",id)).text());
	$("#izmenaDijalog").dialog({
		width : 400,
		resizable : false,
		position: { of: $("#"+id) }
	}).prev(".ui-dialog-titlebar").css("background","green");
	
}

function napraviId(text,id){
	return text + id;
}



function rezervisiRekvizit(id){
	allData = {};
	allData["id"] = parseInt(id);
	$.ajax({
		url: rezervacijaRekvizitaPutanja,
		success: function(result){
			alert(result["poruka"]);
			$("#"+napraviId("broj",id)).text(result["broj"]);
		},
		type: 'PUT',
		data: allData,
		error : function(xhr, textStatus, errorThrown) {
			var err = JSON.parse(xhr["responseText"])["message"];
			alert(err);
		}
		
	});
}


function obrisiRekvizit(id){
	allData = {};
	allData["id"] = parseInt(id);
	$.ajax({
		url: brisanjeTematskihRekvizitaPutanja,
		success: function(result){
			$("#"+id).remove();
		},
		type: 'PUT',
		dataType: "text",
		data: allData,
		error : function(xhr, textStatus, errorThrown) {
			var err = JSON.parse(xhr["responseText"])["message"];
			alert(err);
		}
		
	});
}

function promeniSliku(id){
	var slikaPodaci = $("#file"+id)[0].files[0];
	if($("#file"+id).val() == ''){
		alert("Niste selektovali sliku.");
		return;
	}
	
	var allData = new FormData();
	allData.append("id",id+"");
	allData.append("file",slikaPodaci);
	
	$.ajax({
		url: modifikovanjeTematskihRekvizitaSlikaPutanja,
		success: function(result){
			$("#putanja"+id).attr("src",putanjaDoSlika+result);
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
}

function prikaziBrisanjeIliRezervisanje(id,adminFanZone){
	if(adminFanZone){
		return "</p><input type=\"button\" value=\"Obrisi\" onclick=\""+"obrisiRekvizit("+id+")\"></input><br/>";
	}
		return "</p><input type=\"button\" value=\"Rezervisi\" onclick=\""+"rezervisiRekvizit("+id+")\"></input><br/>";
}

function dodajPrikazRekvizita(rekvizit,adminFanZone){
	var id = rekvizit["id"];
	var newDiv = $("<div class=\"elemOkvir\" id=\""+id+"\""+"></div>");
	
	var divHtml ="<b>Naziv rekvizita: </b>"+"<p id=\""+napraviId("nazivRekvizita",id)+"\">"+rekvizit["nazivRekvizita"]+prikaziBrisanjeIliRezervisanje(id,adminFanZone);
	var divHtml = divHtml + "<b>Opis rekvizita: <br/></b>"+"<div class=\"dugacak_tekst\"><p id=\""+napraviId("opisRekvizita",id)+"\">"+rekvizit["opisRekvizita"]+"</p></div>";
	var divHtml = divHtml + "<b>Cena rekvizita: </b>"+"<p id=\""+napraviId("cenaRekvizita",id)+"\">"+rekvizit["cenaRekvizita"]+"</p>";
	var divHtml = divHtml + "<b>Broj artikala: </b>"+"<p id=\""+napraviId("broj",id)+"\">"+rekvizit["broj"]+"</p>";
	newDiv.html(divHtml);
	if(adminFanZone){
		newDiv.append($("<input type=\"button\" value=\"Izmeni podatke o rekvizitu\"></input><br/>").click(function(data){
		prikaziDijalog(rekvizit["id"]);
	}));
	}
	
	 newDiv.append("<b>Slika rekvizita: </b><br/>"+"<img class =\"centrirana_slika\"width=\"225\" height=\"225\" id=\""+napraviId("putanja",id)+"\"src=\""+putanjaDoSlika+rekvizit["putanjaDoSlike"]+"\"/>");
	 
	 
	 if(adminFanZone){
		 newDiv.append("<br/><input type=\"file\" class=\"filestyle\" data-buttonText=\"Odaberi sliku...\" id = \""+napraviId("file",id)+"\"name=\"file\" accept=\"image/*\"></input>");
		 newDiv.append("<input type=\"button\" value=\"Promeni sliku\"id=\""+napraviId("promenaSlikeDugme",id)+"\" onclick=\""+"promeniSliku("+id+")\"></input><br/>");
	 }

	 $("#prikazRekvizita").append(newDiv);
}

function rekvizitFormaUJSON(nazivRekvizita,opisRekvizita,cenaRekvizita,broj){
	return JSON.stringify({
		"nazivRekvizita":nazivRekvizita,
		"opisRekvizita":opisRekvizita,
		"cenaRekvizita":cenaRekvizita,
		"broj":broj
	});
}

function sakrijDijalog(){
	$("#izmenaDijalog").hide();
    $("div[role=dialog]").hide();
}



function inicijalizujDijalog(){
	$("#izmenaDijalog").hide();
	$("#izmenaRekvizitaDugme").click(
			function(){
				var rekvizitId = parseInt($("#izmenaIdRekvizita").text());
				var nazivRekvizita = $("#izmenaNazivRekvizita").val();
				var opisRekvizita = $("#izmenaOpisRekvizita").val();
				var cenaRekvizita = $("#izmenaCenaRekvizita").val();
				var broj = $("#izmenaBrojaRekvizita").val();
				
				if(broj <0){
					alert("Broj rekvizita ne moze biti manji od 0!");
					return;
				}
		
				if(formaNevalidna(nazivRekvizita,opisRekvizita,cenaRekvizita,broj)){
					alert("Niste popunili sve podatke kako treba!");
					return;
				}
				var allData = {}
				allData["rekvizit"] = rekvizitFormaUJSON(nazivRekvizita,opisRekvizita,cenaRekvizita,broj);
				allData["id"] = rekvizitId;
				
				$.ajax({
					 url:modifikovanjeTematskihRekvizitaInfoPutanja,
					type: "PUT",
					dataType: "text",
					data: allData,
					success: function(data){
						$("#nazivRekvizita"+rekvizitId).text(nazivRekvizita);
						$("#opisRekvizita"+rekvizitId).text(opisRekvizita);
						$("#cenaRekvizita"+rekvizitId).text(cenaRekvizita);
						$("#broj"+rekvizitId).text(broj);
						sakrijDijalog();
					},		   
					error : function(xhr, textStatus, errorThrown) {
							var err = JSON.parse(xhr["responseText"])["message"];
							alert(err);
						}
					
				});
				
			}
		
	);
	
}

function pretraziTematskeRekvizite(){
	var nazivRekvizita = $("#nazivRekvizitaZaPretragu").val();
	var gornjaCena = $("#gornjaCenaRekvizita").val();
	
	
	
	
	
	var allData = {}
	allData["nazivRekvizita"] = nazivRekvizita;
	if(!formaNevalidna(gornjaCena)){
		allData["gornjaCena"] = gornjaCena; //ukoliko je korisnik uneo cenu, posalji. U suprotnom nemoj.
	}
	
	
	$.ajax({
		 url:pretragaTematskihRekvizitaPutanja,
		type: "GET",
		data: allData,
		success: function(data){
			$("#prikazRekvizita").empty();
			fanZonaAdmin = data["adminFanZone"];
			 for(var i=0;i<data["rekviziti"].length;i++){
				 dodajPrikazRekvizita(data["rekviziti"][i],fanZonaAdmin);
			  }
		},		   
		error : function(xhr, textStatus, errorThrown) {
				var err = JSON.parse(xhr["responseText"])["message"];
				alert(err);
			}
		
	});
}


$(document).ready(function(){
	inicijalizujDijalog();
	
	$.ajax({ url:prikazTematskihRekvizitaPutanja,
		   type: "GET",
		   success: function(data){
			 fanZonaAdmin = data["adminFanZone"];
			  for(var i=0;i<data["rekviziti"].length;i++){
				  dodajPrikazRekvizita(data["rekviziti"][i],fanZonaAdmin);
			  }
			  jQuery.getScript("js/bootstrap-filestyle.min.js");
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
});