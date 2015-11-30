<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%> 
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%> 
<!doctype html>
<html lang='nl'> 
  <head>
  <c:choose>
  	<c:when test='${not empty reservaties}'><vdab:head title="Bevestig reservaties" /></c:when>
  	<c:otherwise><vdab:head title='Geen reservaties gevonden' /></c:otherwise>
  </c:choose>   
  </head> 
  <body>
  <h1>Het cultuurhuis: bevestiging reservaties <img src="images/cultuur.jpg"alt="cultuur logo" id="cultuur" /></h1>
  <ul class="horizontal_menu">
  	<li><a href="<c:url value="/index.htm"/>">Voorstellingen</a></li>
  	<c:if test="${not empty reservaties}">
  		<li><a href="<c:url value="/winkelmandje.htm"/>">Reservatiemandje</a></li>
  	</c:if>
  </ul>
    <c:choose> 
      <c:when test="${empty reservaties}">
        <div class='fout'>Geen reservaties gevonden.</div>
      </c:when> 
      <c:otherwise>
		<h2>Stap 1: wie ben je?</h2>
        <form method="post" id="wieForm">
       		<label>Gebruikersnaam:
      		<input name='gebruikersnaam' autofocus required /></label>
      		<label>Paswoord:
      		<input name='wachtwoord' type="password" required /></label>
        	<input type="submit" <c:if test='${not empty gevonden}'>disabled</c:if> value="Zoek me op" name="zoekmeop" id="zoekmeopKnop" />
        </form>
        <form action="registreren.htm" id="ikbennieuwForm" >
        	<input type="submit" <c:if test='${not empty gevonden}'>disabled</c:if> value="Ik ben nieuw" name="ikbennieuw" id="ikbennieuwKnop" />
        </form>
        <div>${user}</div>  
        <h2>Stap 2: bevestigen</h2>
        <form method="post" id="bevestigingsForm" action="commit">
        <input type="submit" <c:if test='${empty gevonden}'>disabled</c:if> value="Bevestigen" name="bevestig" id="bevestigingsKnop" />
        </form>
      </c:otherwise>
    </c:choose>
	<vdab:formscript formID="wieForm" buttonID="zoekmeopKnop"/>
	<vdab:formscript formID="ikbennieuwForm" buttonID="ikbennieuwKnop"/>
	<vdab:formscript formID="bevestigingsForm" buttonID="bevestigingsKnop"/>
  </body>
</html>