var razmatraneObjavePutanja = "fanzona/prikaziRazmatraneObjave";
var evaluirajObjavuPutanja = "fanzona/evaluirajObjavu";
var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";


	
function napraviId(text,id){
	return text + id;
}

function evaluirajObjavu(id,prihvacena){
	var podaci = {}
	podaci["id"] = id;
	podaci["prihvacena"] = prihvacena;
	$.ajax({ url:evaluirajObjavuPutanja,
		   type: "PUT",
		   data : podaci,
		   dataType: "text",
		   success: function(data){
			   $("#"+id).remove();
			  alert(data);
			  
		   },
		  error : function(xhr, textStatus, errorThrown) {
			  var err = xhr["responseText"];
				alert(err);
		}
		   
	});
	
}

function dodajPrikazObjave(objava){
	var id = objava["id"];
	var newDiv = $("<div id=\""+id+"\""+"></div>");
	
	var divHtml ="<b>Naziv objave: </b>"+"<p id=\""+napraviId("nazivObjave",id)+"\">"+objava["naziv"]+"</p><input type=\"button\" value=\"Prihvati\" onclick=\""+"evaluirajObjavu("+id+",true)\"></input><br/><input type=\"button\" value=\"Odbij\" onclick=\""+"evaluirajObjavu("+id+",false)\"></input><br/>";
	var divHtml = divHtml + "<b>Opis objave: <br/></b>"+"<p id=\""+napraviId("opisObjave",id)+"\">"+objava["opis"]+"</p>";
	var divHtml = divHtml + "<b>Autor objave: <br/></b>"+"<p id=\""+napraviId("autorObjave",id)+"\">"+objava["autor"]["ime"]+" "+objava["autor"]["prezime"]+"</br>"+objava["autor"]["email"]+"</p>";
	newDiv.html(divHtml);
	 newDiv.append("<b>Slika objave: </b><br/>"+"<img width=\"225\" height=\"225\" id=\""+napraviId("putanja",id)+"\"src=\""+putanjaDoSlika+objava["putanjaDoSlike"]+"\"/>");
	
	$("#prikazObjava").append(newDiv);
}
	
$(document).ready(function(){

	
	$.ajax({ url:razmatraneObjavePutanja,
		   type: "GET",
		   success: function(data){
			  for(var i=0;i<data.length;i++){
				  dodajPrikazObjave(data[i]);
			  }
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
});