$(document).ready(function(){
	$("#pretragaDugme").click(function(event){
		var zaPretragu = $("#zaPretragu").val();
	

		$.ajax({
			type: "POST",
			url: "/prijatelji/pretragaPrijatelja",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data: jQuery.param(   { stringZaPretragu :zaPretragu } ),
			complete: function(data,status){
				if(status=="success"){
					if (data.responseJSON.length>0){
						$("#tabelaPrijatelji").empty();
						for (i=0; i< data.responseJSON.length; i++){
							var korinsik="<tr> <td>  <img src=\"/upravljanjeSlikama?putanjaDoSlike="+data.responseJSON[i].lokacijaSlike+"\" height= \"50px\" width= \"50px\"> </img></td><td>"+data.responseJSON[i].ime+"</td><td>"+data.responseJSON[i].prezime+"</td><td>"+data.responseJSON[i].email+"</td>" +
									"<td>"+ 
		          						"<form  action = \"/prijatelji/dodavanje\"  method=\"post\">"+ 
			          						"<input type =\"hidden\" name =\"id\" value=\""+data.responseJSON[i].id+"\"></input>"+
			          						"<button style=\"align:center;\">Dodaj prijatelja</button>"+
		          						"</form>"+
		          					"</td>" +
									"</tr>";
							$("#tabelaPrijatelji").append(korinsik);
						}
						
					}else{
						
						alert("Nije pronadjen niti jedan korisnik." );
					}
				} else {
					alert("Doslo je do greske!" );
				}
			}
		});
	});
});