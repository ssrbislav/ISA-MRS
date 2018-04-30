
var administratoriPutanja = "/administratori"
var prikazSvihInstitucijaPutanja = "/pozoristaibioskopi"

var institucionalni = "INSTITUCIONALNI";
var sistemski = "SISTEMSKI";
var fanZona = "FAN_ZONA";
var tipoviAdministratora = [sistemski,fanZona];

var emailRegex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;



function adminFormaUJSON(ime,prezime,lozinka,email,tip_administratora,id_institucije){
	return JSON.stringify({
		"ime":ime,
		"prezime":prezime,
		"lozinka":lozinka,
		"email":email,
		"tip_administratora":tip_administratora,
		"id_institucije":id_institucije
	});
}



function formaNevalidna(){
	for(var i=0;i<arguments.length;i++){
		if(arguments[i]===undefined || arguments[i] ===null || arguments[i] === ""){
			return true;
		}
	}
	
	return false;
}


function dobaviId(){
	var pr = $("select[name=tip_administratora]");
	if(pr.val() != institucionalni){
		return 0;
	}
	else{
		return  $('select[name=id_institucije]').val();
	}
}


function prikaziIliSakrijInstitucijeZaBiranje(){
	var pr = $("select[name=tip_administratora]");
	if(pr.val() != institucionalni){
		$("#prikazInstitucijaZaBiranje").hide();
	}
	else{
		$("#prikazInstitucijaZaBiranje").show();
	}
}


function popuniInstitucije(items){
	
	$.each(items, function (i, item) {
		var newOption = $("<option></option>");
		newOption.attr("value",item["id"]).text(item["naziv"]);
	    $('select[name=id_institucije]').append(newOption);
	            
	});
}



function popuniStavke(items){
	
	$.each(items, function (i, item) {
		var newOption = $("<option></option>");
		newOption.attr("value",item).text(item);
	    $('select[name=tip_administratora]').append(newOption);
	            
	});
}

$(document).ready(function(){
	
	prikaziIliSakrijInstitucijeZaBiranje();
	
	$.ajax({ url:prikazSvihInstitucijaPutanja,
		   type: "GET",
		   success: function(data){
			  if(data.length !=0){
				  tipoviAdministratora.push(institucionalni);
				  $("#hint").hide();
			  }
			  popuniStavke(tipoviAdministratora);
			  popuniInstitucije(data);
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
		   
 });
	
	$("select[name=tip_administratora]").change(prikaziIliSakrijInstitucijeZaBiranje);
	
	
	$("#registracijaDugme").click(function(event){
		
		var ime = $("input[name=ime]").val();
		var prezime = $("input[name=prezime]").val();
		var lozinka = $("input[name=lozinka]").val();
		var email = $("input[name=email]").val();
		var tip_administratora =  $("select[name=tip_administratora]").val();
		var id_institucije = dobaviId();
	    
		
		
		
		if(formaNevalidna(ime,prezime,lozinka,email,tip_administratora,id_institucije)){
			alert("Niste popunili sva polja kako treba! ");
			return;
		}
		
		if(!emailRegex.test(String(email).toLowerCase())){
			alert("Neispravna email adresa!");
			return;
		}
		
		$.ajax({
			url: administratoriPutanja,
			contentType : "application/json",
			success: function(result){
				alert(result);
			},
			type: "POST",
			dataType: "text",
			data: adminFormaUJSON(ime,prezime,lozinka,email,tip_administratora,id_institucije),
			error : function(xhr, textStatus, errorThrown) {
				var err = xhr["responseText"];
				alert(err);
			}
			
		});
		
	});
	
	
});