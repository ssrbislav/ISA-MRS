<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Predstava</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="js/projekcija_predstava.js"></script>
</head>
<body>

<div style="margin: 0 auto; width: 300px;">
	<label style="color: #000066;">Popunite podatke o predstavi</label>
		<br>
		<br>
		<input name="naziv" placeholder="Naziv predstave" size=50></input>
		<input name="spisak_glumaca" placeholder="Spisak glumaca" size=50></input>
		<input name="ime_reditelja" placeholder="Reditelj" size=50></input>
		<input name="zanr" placeholder="Zanr" size=50></input>
		<input name="trajanje" placeholder="Trajanje" size=50></input>
		<input name="slika" placeholder="Naziv slike" size=50></input>
		<input name="opis" placeholder="Opis" size=50></input>
		<input name="cena" placeholder="Cena karte" size=50></input>
		<select>
		    <option value="" disabled="disabled" selected="selected">Izaberite Termin</option>
		    <option value="1">Termin 1</option>
		    <option value="2">Termin 2</option>
		    <option value="3">Termin 3</option>
		    <option value="4">Termin 4</option>
		    <option value="5">Termin 5</option>
		</select>
		<button id="dodaj_termin">Dodaj termin</button>
		<br>
		<select>
		    <option value="" disabled="disabled" selected="selected">Izaberite Salu</option>
		    <option value="1">Sala 1</option>
		    <option value="2">Sala 2</option>
		    <option value="3">Sala 3</option>
		    <option value="4">Sala 4</option>
		    <option value="5">Sala 5</option>
		</select>
		<button id="dodaj_salu">Dodaj salu</button>
		<br>
		<br>
		<button id="dodaj_predstavu">Dodaj Predstavu</button>
</div>

</body>
</html>