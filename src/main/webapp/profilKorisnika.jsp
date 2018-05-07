<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Profil</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="js/registracijaKorisnika.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>

	<nav class="navbar navbar-default" style= "background: #228061;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
     
      <li><a href="/profilKorisnika" style="color: white;">Profil</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Podesavanja
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/izmenaLozinke">Izmena lozinke</a></li>
          <li><a href="#">Izmena informacija</a></li>
         
        </ul>
      </li>
    </ul>
  </div>
</nav>
	<div class="page-header">
		<img src="profilne_slike" style="width: 250px; height: 300px;"></img>
		<br>
		<br>
		<button id="registracijaDugme">Promeni_sliku</button>
	</div>
</body>
</html>