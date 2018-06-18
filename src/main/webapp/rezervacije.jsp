<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	
	<title>Bioskopi</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body style="background:   white ">

	<nav class="navbar navbar-default" style= "background: #29927d;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
     
      <li><a href="/profilKorisnika" style="color: white;">Profil</a></li>
      <li><a href="/pozorista" style="color: white;">Pozorista</a></li>
      <li><a href="/bioskopi" style="color: white;">Bioskopi</a></li>
      <li><a href="#" style="color: white;">Prijatelji</a></li>
      <li><a href="rezervacije" style="color: white;">Rezervacije</a></li>
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
<c:if test="${not empty Poruka}">
	    <script type="text/javascript">
	    	alert("${Poruka}")
	    </script>
	</c:if>
<h3>Vase rezervacije</h3>

		</div>
		<div class="col-lg-10" >
		<br>
		<br>
		<input class="form-control" id="pretraga" type="text" placeholder="Pretrazite"></input>
		<table class="table table-striped" data-toggle="table" data-sort-name="ime" data-sort-order="desc"> 
			<thead style="background:    #eae5e0  ;">
            <tr>
	            <th scope="col" data-field="institucija" data-sortable="true"><center>Institucija</center></th>
	            <th scope="col" data-field="predstava/projekcija" data-sortable="true"><center>Predstava/Projekcija</center></th>
	            <th scope="col" data-field="datum" data-sortable="true"><center>Datum</center></th>
	            <th scope="col" data-field="vreme" data-sortable="true"><center>Vreme</center></th>
	                 <th scope="col" data-field="bodovi" data-sortable="true"><center>Bodovi</center></th>
	                      <th scope="col" data-field="cena" data-sortable="true"><center>Ukupna cena</center></th>
	                      <th scope="col" data-field="sala" data-sortable="true"><center>Sala</center></th>
	                      
	            <th scope="col" data-field="sedista" data-sortable="true"><center>Sedista</center></th>
	            <th scope="col" data-field="prijatelji" data-sortable="true"><center>Prijatelji</center></th>
	            <th scope="col"></th>
            </tr>
            </thead>
            <tbody style="background:  white;" id="tabela">
                  <c:forEach var = "i" items= "${korisnik.rezervacije}">
					<tr style ="width: 250px; height: 20px;">
						<td>  ${i.termin.predProj.institucija.naziv}</td> 
						<td>  ${i.termin.predProj.naziv}</td> 
						<td>  ${i.termin.datum}</td>
						<td>  ${i.termin.vreme}</td>
						<td>  ${i.bodovi}</td>
						<td>  ${i.ukupnaCena}</td>
						<td>${i.termin.sala.oznakaSale }</td>
						
						<td>
							<c:forEach var ="j" items = "${i.karte}">
								${j.sediste[0]}|${j.sediste[1]}  
							</c:forEach>
						</td>
						<td>
							<c:forEach var ="j" items = "${i.karte}">
								${j.osoba.ime} ${j.osoba.prezime}&nbsp;
							</c:forEach>
						</td>
						
						
						<td>  
							<form  action = "/otkazivanje"  method="post"> 
	 						<input type ="hidden" name ="id" value="${i.id}" ></input>
	 						<button style="align:center;">Otkazi</button>
							</form>
						</td>
						
					</tr>
     			 </c:forEach>
     			 </tbody>
     		</table>
              </br>            		
                          		
          <h3>Karte koje su prijatelji rezervisali za Vas</h3></br>
          <input class="form-control" id="pretraga1" type="text" placeholder="Pretrazite"></input>
         <table class="table table-striped" data-toggle="table" data-sort-name="ime" data-sort-order="desc"> 
			<thead style="background:    #eae5e0  ;">
            <tr>
            
	            <th scope="col" data-field="prijatelj" data-sortable="true"><center>Vlasnik rezervacije</center></th>
	            <th scope="col" data-field="institucija" data-sortable="true"><center>Institucija</center></th>
	            <th scope="col" data-field="predstava/projekcija" data-sortable="true"><center>Predstava/Projekcija</center></th>
	            <th scope="col" data-field="datum" data-sortable="true"><center>Datum</center></th>
	            <th scope="col" data-field="vreme" data-sortable="true"><center>Vreme</center></th>
	             <th scope="col" data-field="sala" data-sortable="true"><center>Sala</center></th>
	            
	            <th scope="col" data-field="sediste" data-sortable="true"><center>Sediste</center></th>
	           
            </tr>
            </thead>
            <tbody style="background:  white;" id="tabela1">
                  <c:forEach var = "i" items= "${korisnik.karte}">
                  <c:if test="${i.rezervacija.korisnik.email ne korisnik.email}">
					<tr style ="width: 250px; height: 20px;">
						<td>  ${i.rezervacija.korisnik.ime} ${i.rezervacija.korisnik.prezime} </td>
						<td>  ${i.rezervacija.termin.predProj.institucija.naziv}</td> 
						<td>  ${i.rezervacija.termin.predProj.naziv}</td> 
						<td>  ${i.rezervacija.termin.datum}</td>
						<td>  ${i.rezervacija.termin.vreme}</td>
						<td>${i.rezervacija.termin.sala.oznakaSale }</td>
						<td>  ${i.sediste[0]}|${i.sediste[1]}</td>
					</tr>
					</c:if>
     			 </c:forEach>
     			 </tbody>
     		</table>
		</div>
		
		
<script>
 $(document).ready(function(){
	  $("#pretraga").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#tabela tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	  
	  
	  
	  $("#pretraga1").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#tabela1 tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
	});


</script>
</body>
</html>