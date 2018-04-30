<%@ page language="java"%>
<html>
<head><title>Registracija administratora</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
</head>
<body>
<script type="text/javascript" src="js/dodavanjeAdministratora.js"></script>
<div style="margin: 0 auto; width: 250px;">
   <label style="color: #000066;">Popunite podatke za dodavanje novog administratora: </label><br/>
	Unesite ime <input type="text" name="ime" size=50 required/><br/>
	Unesite prezime <input type="text" name="prezime" size=50 required/><br/>
	Unesite lozinku <input type="password" name="lozinka" size=50 required/><br/>
	Unesite email <input type="email" name="email" size=50 required/><br/>
	Odaberite tip <select name="tip_administratora"></select><br/>
	<div id="prikazInstitucijaZaBiranje">
	 	Odaberite instituciju <select name="id_institucije"></select><br/>
	</div>
	<input type="button" id="registracijaDugme" value="Registruj administratora"/><br/>
	<div id="hint">Ne vidite opciju za registrovanje bioskopskog/pozorisnog admina?<br/>
	Kliknite <a href="/registracijaInstitucija">ovde</a> da prvo registrujete bioskop/pozoriste
	</div>
	<input type="button" id="prikazAdministratoraDugme" value="Prikaz postojecih administratora"/>
</div>
<div id="prikaz" style="margin: 0 auto; width: 250px;">
	
</div>
</body>
</html>