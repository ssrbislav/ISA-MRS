<nav class="navbar navbar-default" style= "background: #29927d;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <ul class="nav navbar-nav">
     
      <li><a href="/profilKorisnika" style="color: white;">Profil</a></li>
      <li><a href="/pozorista" style="color: white;">Pozorista</a></li>
      <li><a href="/bioskopi" style="color: white;">Bioskopi</a></li>
      <li><a href="/prijatelji" style="color: white;">Prijatelji</a></li>
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
      	<a id="notifikacijeToggle" class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Obavestenja
        <span class="caret"></span></a>
        <ul id="notifikacije" class="dropdown-menu">
          
        </ul>
      </li>
       <li id="notifikacijeLi" class="dropdown">
      	<a id="notifikacijeToggle" class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Zahtevi za prijateljstvo
        <span class="caret"></span></a>
        <ul id="notifikacije" class="dropdown-menu">

                  <c:forEach var = "i" items= "${korisnik.zahtevi}">
         					
          					<li>${i.ime} ${i.prezime} <div class="col-lg-12"><form style="display: inline-block;" method="post" action="/prijatelji/prihvatiZahtev"><input type="hidden" name="id" value="${i.id} "/><input type="hidden" name="from" value="${pageContext.request.requestURI}"/><input type="submit" value="&#10003;"/> </form> <form style="display: inline-block;" method="post" action="/prijatelji/odbijZahtev"><input type="hidden" name="id" value="${i.id} "/><input type="hidden" name="from" value="${pageContext.request.requestURI}"/><input type="submit" value="&times;"/> </form></div> </li>
                      
     			 </c:forEach> 
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
