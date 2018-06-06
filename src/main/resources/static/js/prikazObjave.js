var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";
var putanjaDodajPonudu = "/fanzona/dodajPonudu";
var putanjaPrihvatiPonudu = "/fanzona/prihvatiPonudu";
var prihvatanjePonudeVidljivo = false;

function napraviId(text,id){
	return text+id;
}

function prihvati(id){
	podaci = {};
	podaci["id"] = id;
	$.ajax({ url: putanjaPrihvatiPonudu,
		   type: "PUT",
		   data: podaci,
		   success: function(data){
			  alert(data);
			  $(".prihvatanje").remove();
		   },
		  error : function(xhr, textStatus, errorThrown) {
			  alert(xhr["responseText"]["message"]);
		}
		   
	});
}

function dodajPrihvatanje(prihvatanjePonudeVidljivo,id){
	if(prihvatanjePonudeVidljivo){
		return "<button class=\"prihvatanje\" onclick=\""+"prihvati("+id+")"+"\">Prihvati</button><br/>"
	}
	return "";
}

function dodajPonudu(ponuda,prihvatanjePonudeVidljivo){
	var id = ponuda["idPonude"];
	var newDiv = $("<div id="+"\""+napraviId("ponuda",id)+"\""+"></div>");
	var divHtml = "<b>"+ponuda["naslov"]+"</b><br/>"+dodajPrihvatanje(prihvatanjePonudeVidljivo,id);
	var divHtml = divHtml + ponuda["opis"]+"<br/>";
	var divHtml = divHtml + "<b>Autor:</b><br/>"+ponuda["autor"]+"<br/>";
	var divHtml = divHtml + "<b>Cena:</b><br/>"+ponuda["cena"]+"<br/><br/><br/>";
	newDiv.html(divHtml);	
	$("#ponude").append(newDiv);
}

function formaNevalidna(){
	for(var i=0;i<arguments.length;i++){
		if(arguments[i]===undefined || arguments[i] ===null || arguments[i] === ""){
			return true;
		}
	}
	
	return false;
}


function ponudaFormaUJSON(idObjave,naslov,opis,cena){
	return JSON.stringify({
		"idObjave" : idObjave,
		"naslov":naslov,
		"opis":opis,
		"cena":cena,
	});
}

$(document).ready(function(){
	podaci = {}
	podaci["id"] = $("input[name=idObjave]").val();
	$.ajax({ url:"fanzona/pribaviObjavu",
		   type: "GET",
		   data: podaci,
		   success: function(data){
			  sviPodaci = JSON.parse(data);
			  podaci = sviPodaci["objava"];
			  if(sviPodaci["dodavanjePonudeVidljivo"]!=true){
				  $("#dodavanjePonude").attr("style","display: none;");
			  }
			  prihvatanjePonudeVidljivo = sviPodaci["prihvatanjePonudeVidljivo"];
			  $("#nazivObjave").text(podaci["naziv"]);
			  $("#opisObjave").text(podaci["opis"]);
			  $("#slikaObjave").attr("src",putanjaDoSlika+podaci["putanjaDoSlike"]);
			  $("#datumIsteka").text(new Date(podaci["datumIsteka"]));
			  for(var i=0;i<podaci["ponude"].length;i++){
				  dodajPonudu(podaci["ponude"][i],prihvatanjePonudeVidljivo);
			  }
		   },
		  error : function(xhr, textStatus, errorThrown) {
			  alert(xhr["responseText"]);
		}
		   
	});
	
	$("#dodavanjePonudeDugme").click(function(event){
		
		var naslov = $("input[name=naslovPonude]").val();
		var opis = $("input[name=opisPonude]").val();
		var cena = $("input[name=cena]").val();
		var idObjave = $("input[name=idObjave]").val();
		
		if(formaNevalidna(naslov,opis,cena)){
			alert("Niste uneli sve neophodne podatke.");
			return;
		}
		
		$.ajax({ url: putanjaDodajPonudu,
			   type: "POST",
			   data: ponudaFormaUJSON(idObjave,naslov,opis,cena),
			   contentType : "application/json",
			   success: function(data){
				   var podaci = JSON.parse(data);
				   var autor = podaci["autor"];
				   var idPonude = podaci["idPonude"];
				   ponuda = {"naslov":naslov,"opis":opis,"cena":cena,"idObjave":idObjave,"idPonude":idPonude,"autor":autor}
				   
				  $("#"+napraviId("ponuda",idPonude)).remove();
					
				   dodajPonudu(ponuda,prihvatanjePonudeVidljivo);
			   },
			  error : function(xhr, textStatus, errorThrown) {
				  alert(xhr["responseText"]["message"]);
			}
			   
		});
	});
});