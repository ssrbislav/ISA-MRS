<%@ page language="java"%>
<html>
<head><title>Dodavanje rekvizita</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
</head>
<body>
<script type="text/javascript" src="js/dodavanjeObjava.js"></script>
<script type="text/javascript" src="js/bootstrap-filestyle.min.js"> </script>

<%@include file="korisnickiMeni.jsp" %>

<div style="margin: 0 auto; width: 250px;">
   <label style="color:  #87837e  ;">Popunite podatke za dodavanje nove objave: </label><br/>
	<input style="width:322.467px;" type="text" name="nazivObjave" placeholder="Naziv" size=20 required/><br/>
	<textarea style="width:322.467px;" name="opisObjave" maxlength=1500 placeholder="Opis" required></textarea><br/>
	<input style="width:322.467px;" type="date" name="datumIstekaObjave" id="datumIstekaObjave" size=20 required/><br/>
	<input style="width:322.467px;" type="time" name="vremeIstekaObjave" id="vremeIstekaObjave" size=20 required/><br/>
	<input type="file" name="file" class="filestyle" data-buttonText="Odaberi sliku..." style="width:322.467px;" id="slikaObjave" accept="image/*"><br/>
	<input type="button" id="dodavanjeObjavaDugme" value="Dodaj objavu"/><br/>
</div>
</body>
</html>