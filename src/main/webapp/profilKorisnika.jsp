
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Profil</title>
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
      <li><a href="#" style="color: white;">Pozorista</a></li>
      <li><a href="#" style="color: white;">Bioskopi</a></li>
      <li><a href="#" style="color: white;">Prijatelji</a></li>
      <li><a href="#" style="color: white;">Rezervacije</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Podesavanja
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/izmenaLozinke">Izmena lozinke</a></li>
          <li><a href="izmenaPodataka">Izmena licnih podataka</a></li>
         
        </ul>
      </li>
    </ul>
  </div>
</nav>
	<div class="container">
	 <div class="col-md-4" >
		<img src="profilne_slike" style="width: 250px; height: 300px;"></img>
		</br>
		<button id="promenaSlike">Promeni sliku</button>
	
		
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
			<table class="table" > 
			<thead style="background:   #ffffcc ;">
			 	
                       <tr>
                       <th scope="col"><center>Istorija poseta</center></th>
                       </tr>
                       </thead>
                        <tbody style="background:  white  ;">
                       
                  <c:forEach var = "i" items= "${korisnik.istorijatPoseta}">
         					
          						<tr style ="width: 250px; height: 20px;"><td>  ${i.naziv}</td></tr>
                      
     			 </c:forEach>
     			 </tbody>
     			
                </table>
                          		
		</div>
		
	</div>
	
                       
                   
</body>
</html>