var prikazObjavaPutanja = "fanzona/prikaziObjave";
var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";


function napraviId(naziv,id){
	return naziv+id;
}

function dodajPrikazObjave(objava){
	var id = objava["id"];
	var newDiv = $("<div id=\""+id+"\""+"></div>");
	var divHtml ="<b>Naziv objave: </b>"+"<p id=\""+napraviId("nazivObjave",id)+"\">"+objava["naziv"]+"<br/>";
	var divHtml = divHtml + "<b>Opis objave: <br/></b>"+"<p id=\""+napraviId("opisObjave",id)+"\">"+objava["opis"]+"</p>";
	var divHtml = divHtml + "<b>Autor objave: <br/></b>"+"<p id=\""+napraviId("autorObjave",id)+"\">"+objava["autor"]+"</p>";
	var divHtml = divHtml + "<a href= \""+"/prikazObjave?id="+id+"\" >"+"Detalji i ponude"+"</a><br/>";
	newDiv.html(divHtml);
	 newDiv.append("<b>Slika objave: </b><br/>"+"<img width=\"225\" height=\"225\" id=\""+napraviId("putanja",id)+"\"src=\""+putanjaDoSlika+objava["putanjaDoSlike"]+"\"/>");
	
	$("#prikazObjava").append(newDiv);
}
	
$(document).ready(function(){
	var podaci = {}
	podaci["status"] = "OBJAVLJEN";
	
	$.ajax({ url:prikazObjavaPutanja,
		   type: "GET",
		   data : podaci,
		   success: function(data){
			  for(var i=0;i<data.length;i++){
				  dodajPrikazObjave(data[i]);
			  }
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
});