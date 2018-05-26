var putanjaDoSlika = "/upravljanjeSlikama?putanjaDoSlike=";

$(document).ready(function(){
	
	$.ajax({ url:"fanzona/pribaviObjavu",
		   type: "GET",
		   success: function(data){
			  podaci = JSON.parse(data);
			  $("#nazivObjave").text(podaci["naziv"]);
			  $("#opisObjave").text(podaci["opis"]);
			  $("#slikaObjave").attr("src",putanjaDoSlika+podaci["putanjaDoSlike"]);
			  $("#datumIsteka").text(JSON.stringify(new Date(podaci["datumIsteka"])));
		   },
		  error : function(xhr, textStatus, errorThrown) {
			  alert(xhr["responseText"]);
		}
		   
	});
});