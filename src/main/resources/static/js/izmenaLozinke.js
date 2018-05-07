$(document).ready(function(){
  var form = document.getElementById('izmenaLozinke');
  form.button.onclick = function (){
    for(var i=0; i < form.elements.length; i++){
      if(form.elements[i].value === '' && form.elements[i].tagName === "INPUT"){
        alert('Niste popunili sva polja.');
        return false;
      }
    }
    var trenutna = $("input[name=pravaLozinka]").val();
    var trenutnaLozinka =  $("input[name=trenutnaLozinka]").val();
    var lozinka1 =  $("input[name=lozinka1]").val();
    var lozinka2 =  $("input[name=lozinka2]").val();
    if (trenutna!=trenutnaLozinka){
    	
    	alert('Niste uneli ispravnu trenutu lozinku!');
    	return false;
    }
    if (lozinka1.length <8){
     	alert('Lozinka mora da ima minimalno 8 karaktera.');
    	return false;
   
    	
    }
    if (lozinka1 != lozinka2){
    	
    	alert('Niste ispravno uneli dva puta lozinku.');
    	return false;
    	
    }
    form.submit();
  }; 
});