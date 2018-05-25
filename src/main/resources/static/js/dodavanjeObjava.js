var dodavanjeObjavaPutanja = "/fanzona/dodajObjavu";

function formaNevalidna(){
	for(var i=0;i<arguments.length;i++){
		if(arguments[i]===undefined || arguments[i] ===null || arguments[i] === ""){
			return true;
		}
	}
	
	return false;
}


function objavaFormaUJSON(nazivObjave,opisObjave,datumIstekaObjave){
	return JSON.stringify({
		"naziv":nazivObjave,
		"opis":opisObjave,
		"datumIsteka":datumIstekaObjave,
	});
}

$(document).ready(function(){
	$("#dodavanjeObjavaDugme").click(function(event){
		
		
		var nazivObjave = $("input[name=nazivObjave]").val();
		var opisObjave = $("textarea[name=opisObjave]").val();
		var datumIstekaObjave = $("input[name=datumIstekaObjave]").val();
		var slikaPodaci = $("input[name=file]")[0].files[0];
		
		
		if(formaNevalidna(nazivObjave,opisObjave,datumIstekaObjave)){
			alert("Niste popunili sva polja u formi za dodavanje.");
			return;
		}
		
		
		var allData = new FormData();
		if($("input[name=file]").val() != ''){
			allData.append("file",slikaPodaci);
		}
		
		allData.append("objava",objavaFormaUJSON(nazivObjave,opisObjave,datumIstekaObjave));
		
		$.ajax({
			url: dodavanjeObjavaPutanja,
			success: function(result){
				alert(result);
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
		
	});
});