<%@ page language="java"%>
<html>
<head><title>Dodavanje rekvizita</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
</head>
<body>
<script type="text/javascript" src="js/dodavanjeTematskihRekvizita.js"></script>
<div style="margin: 0 auto; width: 250px;">
   <label style="color: #000066;">Popunite podatke za dodavanje novog rekvizita: </label><br/>
	Unesite naziv rekvizita <input type="text" name="nazivRekvizita" size=20 required/><br/>
	Unesite opis rekvizita <textarea name="opisRekvizita" maxlength=1000 required></textarea><br/>
	Unesite cenu <input type="number" name="cenaRekvizita" size=20 required/><br/>
	<input type="file" name="file" value="Odaberi sliku..." id="slikaRekvizita"><br/>
	<input type="button" id="dodavanjeRekvizitaDugme" value="Dodaj rekvizit"/><br/>
</div>
</body>
</html>