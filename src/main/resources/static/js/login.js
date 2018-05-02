$(document).ready(function(){
  var form = document.getElementById('prijavnaForma');
  form.button.onclick = function (){
    for(var i=0; i < form.elements.length; i++){
      if(form.elements[i].value === '' && form.elements[i].tagName === "INPUT"){
        alert('Niste popunili sva polja.');
        return false;
      }
    }
    form.submit();
  }; 
});