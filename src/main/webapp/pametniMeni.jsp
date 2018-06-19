<%@ page import="isa.tim13.PozoristaiBioskopi.model.Korisnik" %>
<%@ page import="isa.tim13.PozoristaiBioskopi.model.Administrator" %>

<% if (session.getAttribute("korisnik") instanceof Korisnik) { %>
    <%@include file="korisnickiMeni.jsp" %>
<% } else if (session.getAttribute("korisnik") instanceof Administrator){ %>
    <%@include file="adminOsnovniMeni.jsp" %>
<% } %>