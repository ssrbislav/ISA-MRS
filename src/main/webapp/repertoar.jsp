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

	<%@include file="korisnickiMeni.jsp" %>
	<div class="container">
	 <div class="col-lg-5" >
		<img src="/upravljanjeSlikama?putanjaDoSlike=${institucija.lokacijaSlike}"  style="width: 200px; height: 200px;"></img>
		<br>
		<h3>${institucija.naziv}</h3>
		<div id="map" style="width:300px;height:200px"> 
			
		</div>
		
		<script>
			var address = "${institucija.adresa},${institucija.grad}"
		</script>
		<script src="js/googleMape.js">
	      </script>
		<script async defer
    		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDoUahpwSrzAa1feDRvcETVcdmqB_LbN3Q&callback=prikaziLokaciju">
    </script>
    
	</div>
	<div class="col-lg-4" >
		<br>
	
		<table class="table">
				<p><h3>Repertoar</h3></p>
                <tbody style="background:  white;">
                     
                <c:forEach var = "i" items= "${institucija.repertoar}">
        						<tr>
        						<td> ${i.naziv}<br> <img src="/upravljanjeSlikama?putanjaDoSlike=${i.slika}" height= "150px" width= "150px"> </img></td>
        						<td>Režija: ${i.ime_reditelja} <br> Glumci: ${i.spisak_glumaca} <br>Žanr: ${i.zanr} <br> Trajanje:${i.trajanje } min <br></td>
        						
        						<td style= " width:150px">${i.opis}</td>
        						
        						
        						<td>
        						<form action="/rezervacija/termin" method="post">
        						<input type="hidden" name="id" value="${i.id}"/>
        						<button >Rezerviši</button>
        						</td>
        						</tr>
     			 </c:forEach>
     			 </tbody>
                </table>
                          		
		</div>
	</div>
              
</body>
</html>