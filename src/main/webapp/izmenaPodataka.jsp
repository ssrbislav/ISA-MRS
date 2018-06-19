<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Podesavanja</title>


	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
 <script type="text/javascript" src="js/izmenaLozinke.js"></script>
	
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body >

<%@include file="korisnickiMeni.jsp" %>

<form action="/profil/izmenaPodataka" method="post" id="izmenaPodataka" style="margin: 0 auto; width: 250px;">
		<br>
		<label style="color:  #87837e  ;"><b>Izmenite vase licne podatke: </b> </label>
		<br>
		<br>
		<input style="width:322.467px;"   placeholder="ime" name="ime" value="${korisnik.ime}" size=50></input>
		<input style="width:322.467px;"  placeholder="prezime" name="prezime" value="${korisnik.prezime}" size=50></input>
		<input style="width:322.467px;"  placeholder="grad" name="grad"  value="${korisnik.grad}" size=50></input>
		<input style="width:322.467px;"  placeholder="telefon" name="telefon"  value="${korisnik.telefon}" size=50></input>
		
		<button type="button" name="button" id="izmenaLozinke" style="width:322.467px;">Sacuvaj izmene</button><br/><br/>

	</form>
	
	<script>  
	
	$(document).ready(function(){
		  var form = document.getElementById('izmenaPodataka');
		  form.button.onclick = function (){
		    for(var i=0; i < form.elements.length; i++){
		      if(form.elements[i].value === '' && form.elements[i].tagName === "INPUT"){
		        alert('Niste popunili sva polja.');
		        return false;
		      }
		    }
		    form.submit();
		  }; 
		});
	
	</script>

</body>
</html>