var notifikacijePutanja = "/fanzona/pribaviObavestenja";
var obrisiNotifikacijuPutanja = "/fanzona/obrisiObavestenje";
var dobaviBedzPutanja = "/profil/dobaviBedz";
var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";
var brojNotifikacija = 0;

function vratiPrihvacena(prihvacena){
	if(prihvacena){
		return "prihvacena";
	}
	return "odbijena";
}

function obrisiNotifikaciju(id){
	podaci = {}
	podaci["id"] = id;
	$.ajax({ url:obrisiNotifikacijuPutanja,
		   type: "PUT",
		   data: podaci,
		   success: function(data){
			  $("#"+id).remove();
			  brojNotifikacija--;
			  if(brojNotifikacija<=0){
				  $("#notifikacijeToggle").text("Notifikacije");
			  }
			  else{
				  $("#notifikacijeToggle").text("Notifikacije ("+brojNotifikacija+")");
			  }
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
}


function dodajNotifikaciju(notifikacija){
	
	var novaNotifikacija = $("<li id=\""+notifikacija["id"]+"\"></li>");
	var novaNotifikacijaSadrzaj = "<div class=\"notifikacijaPonude\">";
	novaNotifikacijaSadrzaj = novaNotifikacijaSadrzaj + 
	"<p>"+"Vasa ponuda <b>"+notifikacija["imePonude"] +
	"</b> na objavu <b>"+notifikacija["imeObjave"]+"</b> je <b>"+vratiPrihvacena(notifikacija["prihvacena"])+"</b></p>";
	novaNotifikacijaSadrzaj = novaNotifikacijaSadrzaj +"<i>"+notifikacija["datum"]+"</i>";
	novaNotifikacijaSadrzaj = novaNotifikacijaSadrzaj + "&nbsp<button class = \"notifikacijaObrisiDugme\" text=\"Obrisi\" onclick=\""+"obrisiNotifikaciju("+notifikacija["id"]+")\">Obrisi</button>";
	novaNotifikacijaSadrzaj = novaNotifikacijaSadrzaj + "</div>";
	novaNotifikacija.html(novaNotifikacijaSadrzaj);
	$("#notifikacije").append(novaNotifikacija);
}

function podesiNotifikacioniMeni(){
	
	$(document).click(function(){
		  $("#notifikacije").hide();
	});
	
    $("#notifikacijeLi").click(function(e){
    	$("#notifikacije").toggle();
		  e.stopPropagation();
	});
}

$(document).ready(function(){
	
	podesiNotifikacioniMeni();
		
	$.ajax({ url:notifikacijePutanja,
		   type: "GET",
		   success: function(data){
			  for(var i=0;i<data.length;i++){
				  dodajNotifikaciju(data[i]);
				  brojNotifikacija++;
			  }
			  if(brojNotifikacija>0){
				  var notifikacijeText = $("#notifikacijeToggle").text();
				  $("#notifikacijeToggle").text(notifikacijeText+"("+brojNotifikacija+")");
			  }
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
	
	$.ajax({ url:dobaviBedzPutanja,
		   type: "GET",
		   success: function(data){
			 if(data!=""){
				 var img = $("<img></img>");
				 img.attr("src",putanjaDoSlika+data);
				 $(".col-lg-5").append(img);
			 }
			 
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
	
});