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
  <ul id="horizontal_menu">
  	<li><a href="<c:url value="/index.htm"/>">Voorstellingen</a></li>
  </ul>
    <c:choose> 
      <c:when test="${empty reservaties}">
        <div class='fout'>Geen reservaties gevonden.</div>
      </c:when> 
      <c:otherwise>
        <dl>
          <dt>Voorstelling:</dt><dd><c:out value='${voorstelling.titel}' /></dd>
          <dt>Uitvoerders</dt><dd><c:out value='${voorstelling.uitvoerders}' /></dd>
          <dt>Datum</dt><dd><fmt:formatDate value='${voorstelling.datum}' type='both' dateStyle='short' timeStyle="short" /></dd>
          <dt>Prijs</dt><dd><fmt:formatNumber type="currency" value="${voorstelling.prijs}"/></dd>
          <dt>Vrije plaatsen</dt><dd><fmt:formatNumber type="number" value="${voorstelling.vrijePlaatsen}" /></dd>
        </dl>
        <form method="post" id="reservatieForm">
       		<label>Plaatsen:<span>${fouten.van}</span>
      		<input name='plaatsen' type="number" min="1" max="${voorstelling.vrijePlaatsen}" value="${aantalGereserveerdePlaatsen}" autofocus required /></label>
      		<input name="maxplaatsen" type="hidden" type="number" value="${voorstelling.vrijePlaatsen}" />
        	<input type="submit" value="Reserveren" id="reservatieKnop" />
        </form>
        <div class='fout'>${fout}</div>  
      </c:otherwise>
    </c:choose>
	<vdab:formscript formID="reservatieForm" buttonID="reservatieKnop"/>
  </body>
</html>