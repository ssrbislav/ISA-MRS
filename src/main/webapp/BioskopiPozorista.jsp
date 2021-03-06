<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	
	<title>Bioskopi</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body style="background:   white ">

	<%@include file="korisnickiMeni.jsp" %>

		</div>
		<div class="col-lg-5 " >
		<br>
		<br>
		<input class="form-control" id="pretraga" type="text" placeholder="Pretrazite"></input>
		<table class="table table-striped" data-toggle="table" data-sort-name="ime" data-sort-order="desc"> 
			<thead style="background:    #eae5e0  ;">
			 	
                       <tr>
                       <th scope="col" data-field="ime" data-sortable="true"><center>Ime</center></th>
                       <th scope="col" data-field="grad" data-sortable="true"><center>Grad</center></th>
                       <th scope="col" data-field="rejting" data-sortable="true"><center>Rejting (0-5)</center></th>
                       <th scope="col" data-field="repertoar" data-sortable="true"><center>Repertoar</center></th>
                       
                       </tr>
                       </thead>
                        <tbody style="background:  white;" id="tabela">
                       
                  <c:forEach var = "i" items= "${institucijePrikaz}">
         					
          						<tr style ="width: 250px; height: 20px;">
          						<td>  ${i.naziv}</td> 
          						<td>  ${i.grad}</td> 
          						<td>  ${i.rejting}</td>
          						<td>  
          						<form  action = "/repertoarC"  method="post"> 
	          						<input type ="hidden" name ="id" value="${i.id}" ></input>
	          						<button style="align:center;">Repertoar</button>
          						</form>
          						
          						</td>
          						</tr>
          						
                      
     			 </c:forEach>
     			 </tbody>
     		</table>
                          		
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