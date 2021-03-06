<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>Repertoar</title>
	<link rel="stylesheet" href="../css/style.css">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body style="background:   white ">

	<%@include file="korisnickiMeni.jsp" %>
	<c:if test="${not empty PorukaRezervacija}">
	    <script type="text/javascript">
	    	alert("${PorukaRezervacija}")
	    </script>
	</c:if>
	
	<div class="container">
	 <div class="col-lg-5" >
		<img src="/upravljanjeSlikama?putanjaDoSlike=${predstavaZaRezervaciju.slika}"  style="width: 200px; height: 200px;"></img>
		
		<h3>${predstavaZaRezervaciju.naziv}</h3> 
	
		<br>
		<label>Odaberite datum prikazivanja: </label>
		 <select id="dan" onchange="termini()">
			  <c:forEach var = "i" items= "${datumiPrikazivanja}">
        	  	<option>${i}</option>
     		  </c:forEach>
		</select> 

<br>
<br>
		<br>
		<h3>Termini za odabran datum </h3> 
		<table class="table">
                <tbody style="background:  white;" id="tabela">   
                <c:forEach var = "i" items= "${predstavaZaRezervaciju.termini}">
					<tr class="${i.datum}">
						<td>
						Vreme: ${i.vreme}<br> Sala: ${i.sala.oznakaSale}  <br> Cena: ${i.cena} rsd   
						</td>
						<td>
							<form action="/rezervacija/mesta" method="post" >
								<input type="hidden" name="id" value ="${i.id}" ></input>
								<button >Odaberi mesto</button>
							</form>
						</td>
					</tr>
     			 </c:forEach>
     			 </tbody>
        </table>               		
		</div>
	</div>
<script>
$(document).ready(function(){
	var x = document.getElementById("dan").value;
	var table = document.getElementById("tabela");
	for (var i = 0, row; row = table.rows[i]; i++) {
	   if(row.className ===  x){
		   row.style.display = "block";
	   } else{
		   row.style.display = "none";
	   }
	}
});
function termini() {
	var x = document.getElementById("dan").value;
	var table = document.getElementById("tabela");
	for (var i = 0, row; row = table.rows[i]; i++) {
	   if(row.className ===  x){
		   row.style.display = "block";
	   } else{
		   row.style.display = "none";
	   }
	}
}
</script>       
</body>
</html>