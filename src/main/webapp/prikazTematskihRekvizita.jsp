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

<nav class="navbar navbar-default" style= "background: #29927d;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
		 <li><a href="/dodavanjeTematskihRekvizita" style="color: white;">Dodavanje tematskih rekvizita</a></li>
         
        </ul>
  </div>
  </nav>
<div style="margin: 0 auto; width: 250px;">
   <label style="color:  #87837e  ;">Tematski rekviziti: </label><br/>
 	<br/>
   <div id="izmenaDijalog">
   <label style="color:  #87837e  ;">Popunite podatke za izmenu rekvizita: </label><br/>
   		<b>Id: </b><p id="izmenaIdRekvizita"></p><br/>
		Naziv rekvizita <input style="width:322.467px;" type="text" id="izmenaNazivRekvizita" size=10 required/><br/>
		Opis rekvizita <textarea style="width:322.467px;" id="izmenaOpisRekvizita" maxlength=1000 required></textarea><br/>
		Cena <input type="number" style="width:322.467px;" id="izmenaCenaRekvizita" size=10 required/><br/>
		Broj <input type="number" style="width:322.467px;" id="izmenaBrojaRekvizita" size=10 required/><br/>
		<input type="button" id="izmenaRekvizitaDugme" value="Izmeni rekvizit"/><br/>
   		
   </div>
   <div id="pretragaTematskihRekvizita">
   		Naziv rekvizita sadrzi  <input style="width:322.467px;" type="text" id="nazivRekvizitaZaPretragu" size=10 required/><br/>
   		Donja cena (obavezno): <input type="number" style="width:322.467px;" id="donjaCenaRekvizita" size=10/><br/>
   		Gornja cena (obavezno): <input type="number" style="width:322.467px;" id="gornjaCenaRekvizita" size=10/><br/>
   		<input type="button" id="pretragaRekvizitaDugme" value="Pretrazi rekvizite" onclick="pretraziTematskeRekvizite()"/><br/>
   </div>
   
</div>
<div class ="rekvizitiGrid" id="prikazRekvizita">
    	
   </div>
</body>
</html>