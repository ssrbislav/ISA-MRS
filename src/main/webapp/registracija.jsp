<%@ page language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Registracija</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="js/registracijaKorisnika.js"></script>
</head>
<br>
<body>
	<div style="margin: 0 auto; width: 250px;">
		<img src="images/reg.png" style="width: 330px; height: 230px;"></img>
		<br>
		<label style="color: #000066;">Popunite podatke zaregistraciju: </label>
		<br>
		<br>
		<input name="email" placeholder="Email Adresa" size=50></input>
		<input type="password" name="lozinka1" placeholder="Lozinka" size=50></input>
		<input type="password" name="lozinka2" placeholder="Ponovi lozinku" size=50></input>
		<input name="ime" placeholder="Ime" size=50></input>
		<input name="prezime" placeholder="Prezime" size=50></input>
		<input name="grad" placeholder="Grad" size=50></input>
		<input name="telefon" placeholder="Broj telefona" size=50></input>
		<br>
		<br>
		<button id="registracijaDugme">Registracija</button>
	</div>
</body>
</html>