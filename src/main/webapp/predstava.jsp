<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Predstava</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="js/projekcija_predstava.js"></script>
</head>
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
                        <form id="signup" method="post" action="/signup" >
                            <h1>Dodaj novu predstavu</h1>
                            <input name="naziv" placeholder="Naziv predstave" required="required" class="input pass"></input>
                            <input name="spisak_glumaca" placeholder="Spisak glumaca" required="required" class="input pass"></input>
                            <input name="ime_reditelja" placeholder="Reditelj" required="required" class="input pass"></input>
                            <input name="zanr" placeholder="Zanr" required="required" class="input pass"></input>
                            <input name="trajanje" placeholder="Trajanje" required="required" class="input pass"></input>
                            <input name="slika" placeholder="Naziv slike" required="required" class="input pass"></input>
                            <input name="opis" placeholder="Opis" required="required" class="input pass"></input>
                            <input name="cena" placeholder="Cena karte" required="required" class="input pass"></input>
                            <select>
                                <option value="" disabled="disabled" selected="selected">Izaberite Termin</option>
                                <option value="1">Termin 1</option>
                                <option value="2">Termin 2</option>
                                <option value="3">Termin 3</option>
                                <option value="4">Termin 4</option>
                                <option value="5">Termin 5</option>
                            </select>
                            <button id="dodaj_termin">Dodaj termin</button>
                            <br>
                            <select>
                                <option value="" disabled="disabled" selected="selected">Izaberite Salu</option>
                                <option value="1">Sala 1</option>
                                <option value="2">Sala 2</option>
                                <option value="3">Sala 3</option>
                                <option value="4">Sala 4</option>
                                <option value="5">Sala 5</option>
                            </select>
                            <button id="dodaj_salu">Dodaj salu</button>
                            <input id="dodaj_predstavu" type="submit" value="Dodaj predstavu" class="inputButton"/>

                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>