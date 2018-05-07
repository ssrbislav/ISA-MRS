<%@ page language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Registracija</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="js/registracijaKorisnika.js"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
	
</head>
<br>
<body>

<nav class="navbar navbar-default" style= "background: #228061;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
     
      <li><a href="/prijava" style="color: white;">Prijava</a></li>
         
        </ul>
      </li>
    </ul>
  </div>
</nav>
	<div style="margin: 0 auto; width: 250px;">
		<br>
		<label style="color: #228061;">Popunite podatke za registraciju: </label>
		<br>
		<br>
		<input style="width:322.467px;" name="email" placeholder="Email Adresa" size=50></input>
		<input style="width:322.467px;" type="password" name="lozinka1" placeholder="Lozinka" size=50></input>
		<input style="width:322.467px;" type="password" name="lozinka2" placeholder="Ponovi lozinku" size=50></input>
		<input style="width:322.467px;" name="ime" placeholder="Ime" size=50></input>
		<input style="width:322.467px;" name="prezime" placeholder="Prezime" size=50></input>
		<input style="width:322.467px;" name="grad" placeholder="Grad" size=50></input>
		<input style="width:322.467px;" name="telefon" placeholder="Broj telefona" size=50></input>
		<button id="registracijaDugme"  style="width:326.467px;">Registracija</button><br/><br/>
	
	</div>
</body>
</html>