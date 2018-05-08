<%@ page language="java"%>
<html>
<head><title>Registracija administratora</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<script type="text/javascript" src="js/dodavanjeAdministratora.js"></script>


<nav class="navbar navbar-default" style= "background: #29927d;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
		<li><a href="/registracijaInstitucija" style="color: white;">Registracija Institucija</a></li>
         
        </ul>
  </div>
</nav>

<div style="margin: 0 auto; width: 250px;">
   <label style="color:  #87837e  ;">Popunite podatke za dodavanje novog administratora: </label><br/>
	Unesite ime <input  style="width:322.467px;" type="text" name="ime" size=50 required/><br/>
	Unesite prezime <input  style="width:322.467px;" type="text" name="prezime" size=50 required/><br/>
	Unesite lozinku <input style="width:322.467px;" type="password" name="lozinka" size=50 required/><br/>
	Unesite email <input  style="width:322.467px;" type="email" name="email" size=50 required/><br/>
	Odaberite tip <select  style="width:322.467px;" name="tip_administratora"></select><br/>
	<div id="prikazInstitucijaZaBiranje">
	 	Odaberite instituciju <select name="id_institucije"></select><br/>
	</div>
	<input type="button" id="registracijaDugme" value="Registruj administratora"/><br/>
	<div id="hint">Ne vidite opciju za registrovanje bioskopskog/pozorisnog admina?<br/>
	Kliknite <a href="/registracijaInstitucija">ovde</a> da prvo registrujete bioskop/pozoriste
	</div>
	<input type="button" id="prikazAdministratoraDugme" value="Prikaz postojecih administratora"/>
</div>
<div id="prikazAdministratora" style="margin: 0 auto; width: 250px;">
	
</div>
</body>
</html>