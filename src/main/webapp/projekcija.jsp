<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Predstava</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.9/angular.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/angular_logika.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="js/projekcija_predstava.js"></script>
</head>
<body>
	<div style="margin: 0 auto; width: 250px;">
		
		<label style="color: #000066;">Unesite projekciju: </label>
		<br>
		<br>
		<input name="naziv" placeholder="Email Adresa" size=50></input>
		<input name="spisak_glumaca" placeholder="Lozinka" size=50></input>
		<input name="reditelj" placeholder="Ponovi lozinku" size=50></input>
		<input name="zanr" placeholder="Ime" size=50></input>
		<input name="trajanje" placeholder="Prezime" size=50></input>
		<input name="naziv_slike" placeholder="Grad" size=50></input>
		<input name="opis" placeholder="Broj telefona" size=50></input>
		<input name="cena_karte" placeholder="Broj telefona" size=50></input>
		<select id="lista_sala">
			
		</select>
		<div>
		
		</div>

		<br>
		<br>
		<button id="registracijaDugme">Registracija</button>
	</div>
</body>
<!-- 
 <body>
        <header>
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">ISA-MRS</a>
                    </div> 
                    <ul class="nav navbar-nav navbar-right">
                            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Registracija</a></li>
                            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Prijava</a></li>
                        </ul>       
                </div>
            </nav>
        </header>
        <div>
            <div class="container" >
                <div class="col-md-6" >
                    <div id="logbox"  >
                        <form id="projekcija" method="post" action="#" >
                            <h1>Dodaj novu projekciju</h1>
                            <input name="naziv" placeholder="Naziv projekcije" required="required" class="input pass"></input>
                            <input name="spisak_glumaca" placeholder="Spisak glumaca" required="required" class="input pass"></input>
                            <input name="ime_reditelja" placeholder="Reditelj" required="required" class="input pass"></input>
                            <input name="zanr" placeholder="Zanr" required="required" class="input pass"></input>
                            <input name="trajanje" placeholder="Trajanje" required="required" class="input pass"></input>
                            <input name="slika" placeholder="Naziv slike" required="required" class="input pass"></input>
                            <input name="opis" placeholder="Opis" required="required" class="input pass"></input>
                            <input name="cena" placeholder="Cena karte" required="required" class="input pass"></input>
                            <select multiple name="sale" id="sale" style="width:200px; height:100px; margin-left:50px">
                                <option value="" disabled="disabled" selected="selected" style="margin-bottom:10px; font-size:16px">Izaberite Sale</option>
                                <option value="1">Sala 1</option>
                                <option value="2">Sala 2</option>
                                <option value="3">Sala 3</option>
                                <option value="4">Sala 4</option>
                                <option value="5">Sala 5</option>
                            </select>
                            <input id="dodaj_predstavu" type="submit" value="Dodaj projekciju" class="inputButton"/>
							<div ng-app="MyApp" ng-controller="MyController">
								<input type="button" value="Dodaj salu" ng-click="ShowHide()" />
        						<br />
						        <br />
						        <div ng-show = "IsVisible">prikazalo se</div>
							</div>
                        </form>
                        <input id="prikazi_predstavu" type="button" value="Prikazi projekciju" class="inputButton"/>
                        
                    </div>
                    <div id="prikaz" style="margin: 0 auto; width: 250px; height:200px; border:solid black 1px">
                    
                </div>
            </div>
        </div>

    </body>
    -->
</html>