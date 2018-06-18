<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Odabir mesta</title>
	<link rel="stylesheet" href="../css/style.css">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
	
	<link rel="stylesheet" href="../css/jquery.seat-charts.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
	
	<script src="../js/jquery.seat-charts.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<nav class="navbar navbar-default" style= "background: #29927d;">
	   <div>
	    <ul class="nav navbar-nav">
	      <li><a href="/profilKorisnika" style="color: white;">Profil</a></li>
	      <li><a href="/pozorista" style="color: white;">Pozorista</a></li>
	      <li><a href="/bioskopi" style="color: white;">Bioskopi</a></li>
	      <li><a href="/prijatelji" style="color: white;">Prijatelji</a></li>
	      <li><a href="/rezervacije" style="color: white;">Rezervacije</a></li>
	      <li class="dropdown">
	        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Podesavanja
	        <span class="caret"></span></a>
	        <ul class="dropdown-menu">
	          <li><a href="/izmenaLozinke">Izmena lozinke</a></li>
	          <li><a href="/izmenaPodataka">Izmena licnih podataka</a></li>
	         
	        </ul>
	      </li>
	    </ul>
	    <ul class="nav navbar-nav pull-right">  
	       <li><a href="/odjava" style="color: white;">Odjava</a></li>
	    </ul>
	  </div>
	</nav>
	<input type = "hidden" id="cena-sedista" value="${rezervacija.termin.cena}"></input>
	
	<div class="wrapper">
		<div class="container">
			<div id="seat-map" class="seatCharts-container" tabindex="0">
			<div class="front-indicator">Front</div>
			</div>
			<div class="booking-details">
					<h3> Odabrana mesta <span id="counter" style="display:none;">0</span>:</h3>
					<ul id="selected-seats"></ul>
					
					Ukupno: <b><span id="total">0</span>RSD</b>
					<br/>
					<br/>
					<button class="checkout-button" id="checkout-button">Potvrdi rezervaciju</button>					
				</div>
		</div>
		<br/>
		<div id="legend" class="seatCharts-legend">
						<ul class="seatCharts-legendList">
						</ul>
		</div>
	</div>



</body>
<script type="text/javascript">
$(document).ready(function() {
	var firstSeatLabel = 1;
	var $cart = $('#selected-seats'),
	$counter = $('#counter'),
	$total = $('#total')
	var matrica = []
	$.get( "/rezervacija/matrica", function(data){
		for(i = 0; i < data.length; i++){
			var red="";
			for(j in data[i]){
				if(data[i][j]==true){
					red += "b"
				}else{
					red += "a"
				}
			}
			matrica.push(red)
		}
		var sc = $('#seat-map').seatCharts({
			map: matrica,
			seats: {
				a: {
					price   : parseFloat($("#cena-sedista").val()),
					classes : 'slobodno' //your custom CSS class
				},
				b: {
					price   : 0.0,
					classes : 'zauzeto' //your custom CSS class
				}
			
			},
			naming : {
				top : false,
				getLabel : function (character, row, column) {
					return firstSeatLabel++;
				},
			},
			legend : {
				node : $('#legend'),
			    items : [
					[ 'a', 'available',   'Slobodna' ],
					[ 'b', 'unavailable',   'Zauzeta']
			    ]					
			},
			click: function () {
				if (this.status() == 'available') {
					var option  = '&nbsp;&nbsp;Pozovi prijatelja:&nbsp;&nbsp;<select id="'+$('#selected-seats')[0].children.length+'">'
						+'<option></option>'
						+'<c:forEach  var = "i" items= "${korisnik.prijatelji}">'
		        	  	+'<option>${i.email}</option>'
		     			+'</c:forEach>'
						+'</select>';
					$('<li>Sediste  '+this.settings.label+': <b>'+this.data().price
							+'RSD</b>'
							+ option
							+'<br/></li>')
						.attr('id', 'cart-item-'+this.settings.id)
						.data('seatId', this.settings.id)
						.appendTo($cart);
					$counter.text(sc.find('selected').length+1);
					$total.text(recalculateTotal(sc)+this.data().price);
					return 'selected';
					
				} else if (this.status() == 'selected') {
					$counter.text(sc.find('selected').length-1)
					$total.text(recalculateTotal(sc)-this.data().price);
					$('#cart-item-'+this.settings.id).remove();
				
					//seat has been vacated
					return 'available';
				} else if (this.status() == 'unavailable') {
					return 'unavailable';
				} else {
					return this.style();
				}
			}
		});
		sc.find('b.available').status('unavailable');
		$('#selected-seats').on('click', '.cancel-cart-item', function () {
			//let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
			sc.get($(this).parents('li:first').data('seatId')).click();
		});
    });
	
	$("#checkout-button").click(function(event){
		var lista_sedista = [];
		var prijatelji = []
		var sedista = $('#selected-seats')[0].children;
		for(i = 0; i < sedista.length; i++){
			var prijatelj = $('#'+i).val();
			var sediste = sedista[i].id;
			var koordinate = sediste.split("-")[2];
			lista_sedista.push(koordinate+'-'+prijatelj);
			if(prijatelj != ""){
				prijatelji.push(prijatelj);
			}
		}
		
		console.log(lista_sedista);
		console.log(prijatelji);
		if(lista_sedista.length == 0){
			alert("Morate rezervisati barem jedno mesto.");
		} else{
			if(prijatelji.length < lista_sedista.length){
				var $form = $('<form action="zakazivanje" method="post"></form>');
		
		        $form.append('<input type="hidden" name="odabranaMesta" value="'+lista_sedista+'" />');
				$(document.body).append($form);
			    $form.submit();
			} else{
				alert("Ne mozete pozvati prijatelje na sva rezervisana mesta. Jedno mesto je namenjeno za vas.")
			}
		}
	});
});


function recalculateTotal(sc) {
	var total = 0;

	//basically find every selected seat and sum its price
	sc.find('selected').each(function () {
		total += this.data().price;
	});
	
	return total;
}




</script>
</html>