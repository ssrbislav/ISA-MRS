var bodovnaSkalaPutanja = "/administratori/dobaviBodovnuSkalu";
var skalaIzmenaPutanja = "/administratori/promeniBodovnuSkalu";

function formaNevalidna(){
	for(var i=0;i<arguments.length;i++){
		if(arguments[i]===undefined || arguments[i] ===null || arguments[i] === ""){
			return true;
		}
	}
	
	return false;
}

function skalaFormaUJSON(bronza,srebro,zlato){
	return JSON.stringify({
		"bronzaBodovi":bronza,
		"srebroBodovi":srebro,
		"zlatoBodovi":zlato,
		
	});
}

$(document).ready(function(){
	
	
	$.ajax({ url:bodovnaSkalaPutanja,
		   type: "GET",
		   success: function(data){
			 $("input[name=bronza]").val(data["bronzaBodovi"]);
			 $("input[name=srebro]").val(data["srebroBodovi"]);
			 $("input[name=zlato]").val(data["zlatoBodovi"]);
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   });
	
$("#podesavanjeSkaleDugme").click(function(event){
		
		var bronza = $("input[name=bronza]").val();
		var srebro = $("input[name=srebro]").val();
		var zlato = $("input[name=zlato]").val();
		
		
		if(formaNevalidna(bronza,srebro,zlato)){
			alert("Niste popunili sva polja kako treba! ");
			return;
		}
		
		$.ajax({
			url: skalaIzmenaPutanja,
			contentType : "application/json",
			success: function(result){
				alert(result);
			},
			type: "PUT",
			dataType : "text",
			data: skalaFormaUJSON(bronza,srebro,zlato),
			error : function(xhr, textStatus, errorThrown) {

				var err = JSON.parse(xhr["responseText"])["message"];
				alert(err);
			}
			
		});
	});
	
		   
 });