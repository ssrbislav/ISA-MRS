<%@ page language="java"%>
<html>
<head><title>Dodavanje rekvizita</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
</head>
<body>
<script type="text/javascript" src="js/dodavanjeObjava.js"></script>

<nav class="navbar navbar-default" style= "background: #29927d;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
         <li><a href="/profilKorisnika" style="color: white;">Profil</a></li>
        </ul>
        <ul class="nav navbar-nav pull-right">  
       <li><a href="/odjava" style="color: white;">Odjava</a></li>
    </ul>
  </div>
</nav>

<div style="margin: 0 auto; width: 250px;">
   <label style="color:  #87837e  ;">Popunite podatke za dodavanje nove objave: </label><br/>
	<input style="width:322.467px;" type="text" name="nazivObjave" placeholder="Naziv" size=20 required/><br/>
	<textarea style="width:322.467px;" name="opisObjave" maxlength=1000 placeholder="Opis" required></textarea><br/>
	<input style="width:322.467px;" type="date" name="datumIstekaObjave" size=20 required/><br/>
	<input type="file" name="file" style="width:322.467px;" value="Odaberi sliku..." id="slikaObjave" accept="image/*"><br/>
	<input type="button" id="dodavanjeObjavaDugme" value="Dodaj objavu"/><br/>
</div>
</body>
</html>