
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
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body style="background:   white ">
	<%@include file="korisnickiMeni.jsp" %>
	<script src="js/bedzevi.js"></script>
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
