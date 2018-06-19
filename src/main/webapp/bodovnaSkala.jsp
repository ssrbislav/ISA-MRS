<%@ page language="java"%>
<html>
<head><title>Podesavanje bodovne skale</title>
<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<script type="text/javascript" src="js/podesavanjeBodovneSkale.js"></script>


<%@include file="adminOsnovniMeni.jsp" %>

<div style="margin: 0 auto; width: 250px;">
   <label style="color:  #87837e  ;">Podesite skalu: </label><br/>
	Bronza: <input  style="width:322.467px;" type="number" name="bronza" size=50 placeholder="bronzani bodovi" required/><br/>
	Srebro: <input  style="width:322.467px;" type="number" name="srebro" size=50 placeholder="srebrni bodovi" required/><br/>
	Zlato: <input style="width:322.467px;" type="number" name="zlato" size=50 placeholder="zlatni bodovi" required/><br/>
	<input type="button" id="podesavanjeSkaleDugme" value="Podesi skalu"/><br/>
</div>
</body>
</html>