<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Prijava</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="js/login.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
	
</head>

<body style= "background:  #f4ffe1 ">
	<nav class="navbar navbar-default" style= "background: #29927d;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
     
      <li><a href="/registracija" style="color: white;">Registracija</a></li>
         
        </ul>
      </li>
    </ul>
  </div>
</nav>

	<c:if test="${not empty porukaGreske}">
	    <script type="text/javascript">
	    	alert("${porukaGreske}")
	    </script>
	</c:if>
	    
	<form action="/login" method="post" id="prijavnaForma" style="margin: 0 auto; width: 250px;">
		<br>
		<label style="color: #29927d;">Popunite podatke za prijavu: </label>
		<br>
		<br>
		<input style="width:322.467px;" name="email" placeholder="Email Adresa" size=50></input>
		<input style="width:322.467px;" type="password" name="lozinka" placeholder="Lozinka" size=50></input>
		
		<button type="button" name="button" id="prijavaDugme" style="width:322.467px;">Prijava</button><br/><br/>
	
	</form>
</body>
</html>