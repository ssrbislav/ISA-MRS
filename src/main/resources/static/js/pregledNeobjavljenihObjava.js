var prikazObjavaPutanja = "fanzona/prikaziObjave";
var preuzmiObjavuPutanja = "fanzona/preuzmiObjavu";
var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";


	
function napraviId(text,id){
	return text + id;
}

function preuzmiObjavu(id){
	var podaci = {}
	podaci["id"] = id;
	$.ajax({ url:preuzmiObjavuPutanja,
		   type: "PUT",
		   data : podaci,
		   dataType: "text",
		   success: function(data){
			   $("#"+id).remove();
			  alert(data);
			  
		   },
		  error : function(xhr, textStatus, errorThrown) {
			  try{
				  var msg = JSON.parse(xhr["responseText"])["message"];
				  alert(msg);
			  }
			  catch(err){
				  alert(xhr["responseText"]);
			  }
			  
			
			  
			  
		}
		   
	});
	
}

function dodajPrikazObjave(objava){
	var id = objava["id"];
	var newDiv = $("<div class=\"elemOkvir\" id=\""+id+"\""+"></div>");
	
	var divHtml ="<b>Naziv objave: </b>"+"<p id=\""+napraviId("nazivObjave",id)+"\">"+objava["naziv"]+"</p><input type=\"button\" value=\"Preuzmi objavu na razmatranje\" onclick=\""+"preuzmiObjavu("+id+")\"></input><br/>";
	var divHtml = divHtml + "<b>Opis objave: <br/></b>"+"<div class=\"dugacak_tekst\"><p id=\""+napraviId("opisObjave",id)+"\">"+objava["opis"]+"</p></div>";
	var divHtml = divHtml + "<b>Autor objave: <br/></b>"+"<p id=\""+napraviId("autorObjave",id)+"\">"+objava["autor"]+"</p>";
	newDiv.html(divHtml);
	 newDiv.append("<b>Slika objave: </b><br/>"+"<img class=\"centrirana_slika\" width=\"225\" height=\"225\" id=\""+napraviId("putanja",id)+"\"src=\""+putanjaDoSlika+objava["putanjaDoSlike"]+"\"/>");
	
	$("#prikazObjava").append(newDiv);
}
	
$(document).ready(function(){
	var podaci = {}
	podaci["status"] = "NEOBJAVLJEN";
	
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