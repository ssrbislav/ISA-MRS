<html>
<head><title>Prikaz objave</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link href="http://code.jquery.com/ui/1.12.1/themes/blitzer/jquery-ui.css" rel="stylesheet" type="text/css" />
  <link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
</head>
<body>

<script type="text/javascript" src="js/prikazObjave.js"></script>

<%@include file="pametniMeni.jsp" %>
<div style="margin: 0 auto; width: 250px;">
   <label style="color:  #87837e  ;">Objava: </label><br/>
 	<br/>  
</div>
<div id="prikazObjave">
       <input type="hidden" name="idObjave"value="${idObjave}"/>
    	<div class="col-lg-5" >
			<b>Naziv objave</b><br/><p id="nazivObjave"></p>
			<b>Opis objave</b><br/><div class="vrlo_dugacak_tekst"><p id="opisObjave"></p></div>
			<b>Datum isteka</b><br/><p id="datumIsteka"></p>
			<b>Autor objave</b><br/><p id="autorObjave"></p>
			<b>Slika</b><br/><img height="150" width="300" id="slikaObjave"></img>
			
		</div>
		<div id="ponude" style="overflow:auto">
			<label style="color:  #87837e  ;">Ponude: </label><br/>
			  <div id="dodavanjePonude">
               		<input style="width:322.467px;" type="text" name="naslovPonude" placeholder="Naslov ponude" size=10 required/><br/>
			  		<input style="width:322.467px;" type="text" name="opisPonude" placeholder="Opis ponude" size=10 required/><br/>
			  		<input style="width:322.467px;" type="number" name="cena" placeholder="Cena" size=10 required/><br/>
			  		<input type="button" id="dodavanjePonudeDugme" value="Dodaj ponudu"/><br/><br/>
			  </div>          		
		</div>
   </div>
</body>
</html>