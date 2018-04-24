var registrujKorisnika = "/registerUser";

var emailRegex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;


function regFormaUJSON(email,lozinka1,lozinka2,ime,prezime,grad,telefon){
	return JSON.stringify({
		"email":email,
		"lozinka1":lozinka1,
		"lozinka2":lozinka2,
		"ime":ime,
		"prezime":prezime,
		"grad":grad,
		"telefon":telefon
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

$(document).ready(function(){
	$("#registracijaDugme").click(function(event){
		var email = $("input[name=email]").val();
		var lozinka1 = $("input[name=lozinka1]").val();
		var lozinka2 = $("input[name=lozinka2]").val();
		var ime = $("input[name=ime]").val();
		var prezime =  $("input[name=prezime]").val();
		var grad = $("input[name=grad]").val();
		var telefon = $("input[name=telefon]").val();
		
		if(formaNevalidna(email,lozinka1,lozinka2,ime,prezime,grad,telefon)){
			alert("Potrebno je popuniti sva polja! ");
			return;
		}
		
		if(!emailRegex.test(String(email).toLowerCase())){
			alert("Neispravna email adresa!");
			return;
		}
		
		if(lozinka1.length < 8){
			alert("Lozinka mora da ima minimalno 8 karaktera!");
			return;
		}
		if(lozinka1 != lozinka2){
			alert("Niste uneli istu sifru!");
			return;
		}
		
		$.ajax({
			type: "POST",
			url: registrujKorisnika,
			contentType : "application/json; charset=utf-8",
			dataType: "json",
			data: regFormaUJSON(email,lozinka1,lozinka2,ime,prezime,grad,telefon),
			complete: function(data,status){
				if(status!="error"){
					window.location = '/prijava';
				}else{
					alert("Korisnik vec postoji!" );
				}
			},
			failure : function(error) {
				alert("Desila se greska!" );
			}
		});
		
	});
	
	
});