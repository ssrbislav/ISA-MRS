var prikazTematskihRekvizitaPutanja = "/fanzona/prikaziTematskeRekvizite";
var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";


function dodajPrikazRekvizita(rekvizit){
	var newDiv = $("<div id=\""+rekvizit["id"]+"\""+"></div>");
	var divHtml = "<b>Naziv rekvizita: </b>"+rekvizit["nazivRekvizita"]+"<br/>";
	var divHtml = divHtml + "<b>Opis rekvizita: <br/></b>"+rekvizit["opisRekvizita"]+"<br/>";
	var divHtml = divHtml + "<b>Cena rekvizita: </b>"+rekvizit["cenaRekvizita"]+"<br/>";
	var divHtml = divHtml + "<b>Slika rekvizita: </b><br/>"+"<img src=\""+putanjaDoSlika+rekvizit["putanjaDoSlike"]+"\"/>";
	newDiv.html(divHtml);
	$("#prikazRekvizita").append(newDiv);
}


$(document).ready(function(){
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