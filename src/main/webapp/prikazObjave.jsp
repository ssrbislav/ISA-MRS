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
   <label style="color:  #87837e  ;">Objava: </label><br/>
 	<br/>  
</div>
<div id="prikazObjave">
    	<div class="col-lg-5" >
			<b>Naziv objave</b><br/><p id="nazivObjave"></p>
			<b>Opis objave</b><br/><p id="opisObjave"></p>
			<b>Datum isteka</b><br/><p id="datumIsteka"></p>
			<b>Slika</b><br/><img height="300" width="400" id="slikaObjave"></img>
			
		</div>
		<div id="ponude" >
			<label style="color:  #87837e  ;">Ponude: </label><br/>
                          		
		</div>
   </div>
</body>
</html>