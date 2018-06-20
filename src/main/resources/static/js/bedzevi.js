var dobaviBedzPutanja = "/profil/dobaviBedz";
$(document).ready(function(){
		$.ajax({ url:dobaviBedzPutanja,
		   type: "GET",
		   success: function(data){
			 if(data!=""){
				 var img = $("<img></img>");
				 img.attr("src",putanjaDoSlika+data);
				 $(".col-lg-5").append(img);
			 }
			 
		   },
		  error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
		
});