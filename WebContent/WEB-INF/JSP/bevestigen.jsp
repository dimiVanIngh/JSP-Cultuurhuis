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
        <form method="post" name="wieForm">
       		<label>Gebruikersnaam:
      		<input type="text" name='gebruikersnaam' autofocus required /></label>
      		<label>Paswoord:
      		<input name='wachtwoord' type="password" required /></label>
        	<input type="submit" <c:if test='${not empty user}'>disabled</c:if> value="Zoek me op" name="zoekmeop" id="zoekmeopKnop" />
        </form>
        <form action="registreren.htm" name="ikbennieuwForm">
        	<input type="submit" <c:if test='${not empty user}'>disabled</c:if> value="Ik ben nieuw" name="ikbennieuw" id="ikbennieuwKnop" />
        </form>
        <c:if test="${not empty user}">
        	<div><c:out value='${user}' /></div>  
        </c:if>
        <h2>Stap 2: bevestigen</h2>
        <form method="post" name="bevestigingsForm" action=bevestig.htm?bevestig>
        <input type="submit" <c:if test='${empty user}'>disabled</c:if> value="Bevestigen" name="bevestig" id="bevestigingsKnop" />
        </form>
      </c:otherwise>
    </c:choose>
	<vdab:formscript formName="wieForm" buttonID="zoekmeopKnop"/>
	<vdab:formscript formName="ikbennieuwForm" buttonID="ikbennieuwKnop"/>
	<vdab:formscript formName="bevestigingsForm" buttonID="bevestigingsKnop"/>
  </body>
</html>