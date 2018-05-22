<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Repertoar</title>
	<link rel="stylesheet" href="css/style.css">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body style="background:   white ">

	<nav class="navbar navbar-default" style= "background: #29927d;">
   </div>
    <ul class="nav navbar-nav">
     
      <li><a href="/profilKorisnika" style="color: white;">Profil</a></li>
      <li><a href="/pozorista" style="color: white;">Pozorista</a></li>
      <li><a href="/bioskopi" style="color: white;">Bioskopi</a></li>
      <li><a href="#" style="color: white;">Prijatelji</a></li>
      <li><a href="#" style="color: white;">Rezervacije</a></li>
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
	<div class="container">
	 <div class="col-lg-5" >
		<img src="/upravljanjeSlikama?putanjaDoSlike=${predstavaZaRezervaciju.slika}"  style="width: 200px; height: 200px;"></img>
		
		<h3>${predstavaZaRezervaciju.naziv}</h3> 
	
		<br>
		<label>Odaberite datum prikazivanja: </label>
		 <select id="dan" onchange="termini()">
			  <c:forEach var = "i" items= "${datumiPrikazivanja}">
        	  	<option>${i}</option>
     		  </c:forEach>
		</select> 

<br>
<br>
		<br>
		<h3>Termini za odabran datum </h3> 
		<table class="table">
                <tbody style="background:  white;"" id="tabela">   
                <c:forEach var = "i" items= "${predstavaZaRezervaciju.termini}">
					<tr class="${i.datum}">
						<td>
						Vreme: ${i.vreme} <br> Sala: ${i.sala.oznakaSale}  <br> Cena: ${i.cena} rsd   
						</td>
						<td>
							<form action="/rezervacija/mesta" method="post" >
								<input type="hidden" name="id" value ="${i.id}" ></input>
								<button >Rezerviši</button>
							</form>
						</td>
					</tr>
     			 </c:forEach>
     			 </tbody>
        </table>               		
		</div>
	</div>
<script>
$(document).ready(function(){
	var x = document.getElementById("dan").value;
	var table = document.getElementById("tabela");
	for (var i = 0, row; row = table.rows[i]; i++) {
	   if(row.className ===  x){
		   row.style.display = "block";
	   } else{
		   row.style.display = "none";
	   }
	}
});
function termini() {
	var x = document.getElementById("dan").value;
	var table = document.getElementById("tabela");
	for (var i = 0, row; row = table.rows[i]; i++) {
	   if(row.className ===  x){
		   row.style.display = "block";
	   } else{
		   row.style.display = "none";
	   }
	}
}
</script>       
</body>
</html>