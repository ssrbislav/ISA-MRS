var pribavljanjeOpcijaPutanja = "administratori/pribaviOpcije";

function dodajOpciju(navbar,imeOpcije,link){
	navbar.prepend('<li><a href="'+link+'" style="color: white;">'+imeOpcije+'</a></li>');
}


$(document).ready(function(){

	
	$.ajax({ url:pribavljanjeOpcijaPutanja,
		   type: "GET",
		   success: function(data){
			   var navbar = $("#opcijeAdmina");
			  for(var imeOpcije  in data){
				  dodajOpciju(navbar,imeOpcije,data[imeOpcije]);
			  }
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
	});
});