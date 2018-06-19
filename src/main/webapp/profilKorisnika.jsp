
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Profil</title>
	<link rel="stylesheet" href="css/style.css">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
	<script type="text/javascript" src="/js/profilKorisnika.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body style="background:   white ">

	<nav class="navbar navbar-default" style= "background: #29927d;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
     
      <li><a href="/profilKorisnika" style="color: white;">Profil</a></li>
      <li><a href="/pozorista" style="color: white;">Pozorista</a></li>
      <li><a href="/bioskopi" style="color: white;">Bioskopi</a></li>
      <li><a href="prijatelji" style="color: white;">Prijatelji</a></li>
      <li><a href="/rezervacije" style="color: white;">Rezervacije</a></li>
      
       <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Fan Zona
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/dodavanjeObjava">Dodaj objavu</a></li>
          <li><a href="/pregledObjavljenihObjava">Prikaz objava</a></li>
         <li><a href="/prikazTematskihRekvizita">Prikaz zvanicnih rekvizita</a></li>
          <li><a href="/rezervacijeRekvizita">Moje rezervacije</a></li>
        </ul>
      </li>
      <li id="notifikacijeLi" class="dropdown">
      	<a id="notifikacijeToggle" class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Notifikacije
        <span class="caret"></span></a>
        <ul id="notifikacije" class="dropdown-menu">
          
        </ul>
      </li>
      
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Podesavanja
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/izmenaLozinke">Izmena lozinke</a></li>
          <li><a href="/izmenaPodataka">Izmena licnih podataka</a></li>
         
        </ul>
      </li>
      
    </ul>
    <ul class="nav navbar-nav pull-right">  
       <li><a href="/odjava" style="color: white;">Odjava</a></li>
    </ul>
  </div>
</nav>
	<div class="container">
	 <div class="col-md-3" >
		<img src="/upravljanjeSlikama?putanjaDoSlike=${korisnik.lokacijaSlike}"  style="width: 250px; height: 300px;"></img>
		</br>
		<form method="POST" enctype="multipart/form-data" action="/profil/dodajSliku">
				<input type="file" name="file" id="selekcija" style="display: none;" />
				<input type="button" value="Odaberite sliku" onclick="document.getElementById('selekcija').click();" />
				<input type="submit" value="Promeni sliku" />
		</form>
	
		
	</div>
		<div class="col-lg-5" >
			<h3>${korisnik.ime}</h3> 
			<h3>${korisnik.prezime}</h3>  
			<h3>${korisnik.email}</h3> 
			<h3>${korisnik.telefon}</h3> 
			<h3>${korisnik.grad}</h3>
			
			
			
		
		</div>
		<div class="col-lg-4" >
		<br>
		<br>
			<input class="form-control" id="pretraga" type="text" placeholder="Pretrazite"/>
			<table class="table table-striped " data-toggle="table" data-sort-name="naziv" data-sort-order="desc"> 
			<thead style="background:   #eae5e0   ;">
			 	
                       <tr>
                       <th scope="col" data-field="naziv" data-sortable="true"><center>Istorija poseta</center></th>
                       </tr>
                       </thead>
                        <tbody style="background:  white;" id="tabela">
                       
                  <c:forEach var = "i" items= "${korisnik.istorijatPoseta}">
         					
          						<tr style ="width: 250px; height: 20px;"><td>  ${i.naziv}</td></tr>
                      
     			 </c:forEach>
     			 </tbody>
     			
                </table>
                          		
		</div>
		
	</div>

<script>
 $(document).ready(function(){
	  $("#pretraga").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#tabela tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
</script>               
</body>
</html>