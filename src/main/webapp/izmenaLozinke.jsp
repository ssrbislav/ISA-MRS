<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Podesavanja</title>

	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 
 <script type="text/javascript" src="js/izmenaLozinke.js"></script>
	
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body >

<%@include file="pametniMeni.jsp" %>

<form action="/profil/izmenaLozinke" method="post" id="izmenaLozinke" style="margin: 0 auto; width: 250px;">
		<br>
		<label style="color:  #87837e  ;"><b>Izmena lozinke</b> </label>
		<br>
		<br>
		<input type="hidden" name="pravaLozinka" value="${korisnik.lozinka}" />
		<input style="width:322.467px;" type="password"  name="trenutnaLozinka" placeholder="Trenutna lozinka" size=50></input>
		<input style="width:322.467px;" type="password" name="lozinka1" placeholder="Nova lozinka" size=50></input>
		<input style="width:322.467px;" type="password" name="lozinka2" placeholder="Ponovite novu lozinku" size=50></input>
		
		<button type="button" name="button" id="izmenaLozinke" style="width:322.467px;">Sacuvaj izmene</button><br/><br/>

	</form>

</body>
</html>