<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<head>
	<title>Prijava</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript" src="js/login.js"></script>
</head>
<br>
<body>
	<c:if test="${not empty porukaGreske}">
	    <script type="text/javascript">
	    	alert("${porukaGreske}")
	    </script>
	</c:if>
	    
	<form action="/login" method="post" id="prijavnaForma" style="margin: 0 auto; width: 250px;">
		<img src="images/index.png" style="width: 330px; height: 270px;"></img>
		<br>
		<label style="color: #AFBAE7;">Popunite podatke za prijavu: </label>
		<br>
		<br>
		<input style="width:322.467px;" name="email" placeholder="Email Adresa" size=50></input>
		<input style="width:322.467px;" type="password" name="lozinka" placeholder="Lozinka" size=50></input>
		
		<button type="button" name="button" id="prijavaDugme" style="width:322.467px;">Prijava</button><br/><br/>
		<a href="/registracija">Registracija</a>
	</form>
</body>
</html>