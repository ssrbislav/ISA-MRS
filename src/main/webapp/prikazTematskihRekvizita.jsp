<html>
<head><title>Prikaz rekvizita</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link href="http://code.jquery.com/ui/1.12.1/themes/blitzer/jquery-ui.css" rel="stylesheet" type="text/css" />
  <link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
</head>
<body>

<script type="text/javascript" src="js/prikazTematskihRekvizita.js"></script>
<script type="text/javascript" src="js/bootstrap-filestyle.min.js"> </script>

<nav class="navbar navbar-default" style= "background: #29927d;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
		 <li id="dodavanjeRekvizitaOpcija"><a href="/dodavanjeTematskihRekvizita" style="color: white;">Dodavanje tematskih rekvizita</a></li>
         <li id="profilOpcija"><a href="/profilKorisnika" style="color: white;">Profil</a></li>
        </ul>
    <ul class="nav navbar-nav pull-right">  
       <li><a href="/odjava" style="color: white;">Odjava</a></li>
    </ul>
  </div>
  </nav>
<div style="margin: 0 auto; width: 250px;">
   <label style="color:  #87837e  ;">Tematski rekviziti: </label><br/>
 	<br/>
   <div id="izmenaDijalog">
   <label style="color:  #87837e  ;">Popunite podatke za izmenu rekvizita: </label><br/>
   		<b>Id: </b><p id="izmenaIdRekvizita"></p><br/>
		<input style="width:322.467px;" type="text" id="izmenaNazivRekvizita" placeholder="naziv" size=10 required/><br/>
		<textarea style="width:322.467px;" id="izmenaOpisRekvizita" maxlength=1500 placeholder="opis" required></textarea><br/>
		<input type="number" style="width:322.467px;" id="izmenaCenaRekvizita" placeholder="cena" size=10 required/><br/>
		<input type="number" style="width:322.467px;" id="izmenaBrojaRekvizita" placeholder="broj" size=10 required/><br/>
		<input type="button" id="izmenaRekvizitaDugme" value="Izmeni rekvizit"/><br/>
   		
   </div>
   <div id="pretragaTematskihRekvizita">
   		<input style="width:322.467px;" type="text" id="nazivRekvizitaZaPretragu" placeholder="naziv" size=10 required/><br/>
   		<input type="number" style="width:322.467px;" id="gornjaCenaRekvizita" placeholder="gornja cena" size=10/><br/>
   		<input type="button" id="pretragaRekvizitaDugme" value="Pretrazi rekvizite" onclick="pretraziTematskeRekvizite()"/><br/>
   </div>
   
</div>
<div class ="rekvizitiGrid" id="prikazRekvizita">
    	
   </div>
</body>
</html>