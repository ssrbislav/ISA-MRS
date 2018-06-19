<%@ page language="java"%>
<html>
<head><title>Registracija bioskopa/pozorista</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
</head>
<body>
<script type="text/javascript" src="js/registracijaInstitucija.js"></script>
<script type="text/javascript" src="js/bootstrap-filestyle.min.js"> </script>

<%@include file="adminOsnovniMeni.jsp" %>

<div style="margin: 0 auto; width: 250px;">
   <label style="color:  #87837e  ;">Popunite podatke za registraciju: </label>
	 <input style="width:322.467px;" type="text" name="naziv" placeholder="naziv" size=50/><br/>
	 <input style="width:322.467px;" type="text" name="adresa" placeholder="adresa" size=50/><br/>
	 <input style="width:322.467px;" type="text" name="grad" placeholder="grad" size=50/><br/>
	 <input style="width:322.467px;" type="tel" name="telefon" placeholder="telefon" size=50/><br/>
	 <input style="width:322.467px;" type="text" name="opis" placeholder="opis" size=50/><br/>
	 <select style="width:322.467px;" name="tip"><option>BIOSKOP</option><option>POZORISTE</option></select><br/>
	 <input type="file" name="file" style="width:322.467px;" class="filestyle" data-buttonText="Odaberi sliku..." id="slikaRekvizita" accept="image/*"><br/>
	<input type="button" id="registracijaDugme" value="Registruj instituciju"/><br/>
	<input type="button" id="prikazInstitucijaDugme" value="Prikaz postojece institucije"/>
</div>
<div id="prikaz" style="margin: 0 auto; width: 250px;">
	
</div>
<br/>
<div style="margin: 0 auto; width: 250px;">
	 <input type="text" name="nazivSale" placeholder="Naziv sale" size=20/><br/>
	<input type="number" name="brojVrstaSale" placeholder="Broj vrsta sale" size=20/><br/>
	<input type="number" name="brojKolonaSale" placeholder="Broj kolona sale" size=20/><br/>
	<input type="button" id="dodavanjeSaleDugme" value="Dodaj salu"/><br/>
</div>

<div id="prikazDodatihSala" style="margin: 0 auto; width: 250px;">
  
</div>
</body>
</html>