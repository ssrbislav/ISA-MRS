var prikazTematskihRekvizitaPutanja = "/fanzona/prikaziTematskeRekvizite";
var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";
var modifikovanjeTematskihRekvizitaInfoPutanja = "/fanzona/modifikujTematskiRekvizitInformacije";
var modifikovanjeTematskihRekvizitaSlikaPutanja = "/fanzona/modifikujSlikuTematskogRekvizita";
var brisanjeTematskihRekvizitaPutanja = "/fanzona/obrisiTematskiRekvizit"


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
	$("#izmenaDijalog").dialog({
		
	});
}

function napraviId(text,id){
	return text + id;
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
			var err = xhr["responseText"];
			alert(err);
		}
		
	});
}

function promeniSliku(id){
	var slikaPodaci = $("#file"+id)[0].files[0];
	if($("#file"+id).val() == ''){
		alert("Niste selektovali sliku ");
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
			var err = xhr["responseText"];
			alert(err);
		}
		
	});
}

function dodajPrikazRekvizita(rekvizit){
	var id = rekvizit["id"];
	var newDiv = $("<div id=\""+id+"\""+"></div>");
	
	var divHtml ="<b>Naziv rekvizita: </b>"+"<p id=\""+napraviId("nazivRekvizita",id)+"\">"+rekvizit["nazivRekvizita"]+"</p><input type=\"button\" value=\"Obrisi\" onclick=\""+"obrisiRekvizit("+id+")\"></input><br/>";
	var divHtml = divHtml + "<b>Opis rekvizita: <br/></b>"+"<p id=\""+napraviId("opisRekvizita",id)+"\">"+rekvizit["opisRekvizita"]+"</p><br/>";
	var divHtml = divHtml + "<b>Cena rekvizita: </b>"+"<p id=\""+napraviId("cenaRekvizita",id)+"\">"+rekvizit["cenaRekvizita"]+"</p><br/>";
	newDiv.html(divHtml);
	newDiv.append($("<input type=\"button\" value=\"Izmeni podatke o rekvizitu\"></input><br/>").click(function(data){
		prikaziDijalog(rekvizit["id"]);
	}));
	 newDiv.append("<b>Slika rekvizita: </b><br/>"+"<img id=\""+napraviId("putanja",id)+"\"src=\""+putanjaDoSlika+rekvizit["putanjaDoSlike"]+"\"/>");
	 
	 
	 newDiv.append("<br/><input type=\"file\" id = \""+napraviId("file",id)+"\"name=\"file\" accept=\"image/*\"></input>");
	 newDiv.append("<input type=\"button\" value = \"Promeni sliku...\"id=\""+napraviId("promenaSlikeDugme",id)+"\" onclick=\""+"promeniSliku("+id+")\"></input><br/>");

	 $("#prikazRekvizita").append(newDiv);
}

function rekvizitFormaUJSON(nazivRekvizita,opisRekvizita,cenaRekvizita){
	return JSON.stringify({
		"nazivRekvizita":nazivRekvizita,
		"opisRekvizita":opisRekvizita,
		"cenaRekvizita":cenaRekvizita
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
				
				
		
				if(formaNevalidna(nazivRekvizita,opisRekvizita,cenaRekvizita)){
					alert("Niste popunili sve podatke kako treba!");
					return;
				}
				var allData = {}
				allData["rekvizit"] = rekvizitFormaUJSON(nazivRekvizita,opisRekvizita,cenaRekvizita);
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
						sakrijDijalog();
					},		   
					error : function(xhr, textStatus, errorThrown) {
							var err = xhr["responseText"];
							alert(err);
						}
					
				});
				
			}
		
	);
	
}


$(document).ready(function(){
	inicijalizujDijalog();
	
	$.ajax({ url:prikazTematskihRekvizitaPutanja,
		   type: "GET",
		   success: function(data){
			  for(var i=0;i<data.length;i++){
				  dodajPrikazRekvizita(data[i]);
			  }
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
});