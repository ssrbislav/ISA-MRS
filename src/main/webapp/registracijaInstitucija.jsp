<%@ page language="java"%>
<html>
<head><title>Registracija bioskopa/pozorista</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
</head>
<body>
<script type="text/javascript" src="js/registracijaInstitucija.js"></script>
<div style="margin: 0 auto; width: 250px;">
   <label style="color: #000066;">Popunite podatke za registraciju: </label>
	Unesite naziv <input type="text" name="naziv" size=50/><br/>
	Unesite adresu <input type="text" name="adresa" size=50/><br/>
	Unesite telefon <input type="tel" name="telefon" size=50/><br/>
	Unesite opis <input type="text" name="opis" size=50/><br/>
	Odaberite tip <select name="tip"><option>BIOSKOP</option><option>POZORISTE</option></select><br/>
	<input type="button" id="registracijaDugme" value="Registruj instituciju"/><br/>
	<input type="button" id="prikazInstitucijaDugme" value="Prikaz postojece institucije"/>
</div>
<div id="prikaz" style="margin: 0 auto; width: 250px;">
	
</div>
<br/>
<div style="margin: 0 auto; width: 250px;">
	Naziv sale: <br/><input type="text" name="nazivSale" size=20/><br/>
	Broj vrsta sale: <input type="number" name="brojVrstaSale" size=20/><br/>
	Broj kolona sale: <input type="number" name="brojKolonaSale" size=20/><br/>
	<input type="button" id="dodavanjeSaleDugme" value="Dodaj salu"/><br/>
</div>

<div id="prikazDodatihSala" style="margin: 0 auto; width: 250px;">
  
</div>
</body>
</html>