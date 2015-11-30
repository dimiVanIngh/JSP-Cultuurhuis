<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%> 
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%> 
<!doctype html>
<html lang='nl'> 
  <head>
	<vdab:head title='Reservatie mandje' />
  </head> 
  <body>
  <h1>Het cultuurhuis:reservatiemandje <img src="images/cultuur.jpg"alt="cultuur logo" id="cultuur" /></h1>
  <ul class="horizontal_menu">
  	<li><a href="<c:url value="/index.htm"/>">Voorstellingen</a></li>
  	<c:if test="${not empty reservaties}">
  		<li><a href="<c:url value="/bevestig.htm"/>">Bevestig reservatie</a></li>
  	</c:if>
  </ul>
    <c:choose> 
      <c:when test='${not empty fout}'>
        <div class='fout'>${fout}</div>        
      </c:when>
      <c:when test="${empty reservaties}">
        <div class='fout'>Geen reservaties gevonden</div>
      </c:when> 
      <c:otherwise>
		<table>
				<thead>
					<tr>
						<th>Datum</th>
						<th>Titel</th>
						<th>Uitvoerders</th>
						<th>Prijs</th>
						<th>Plaatsen</th>
						<th><form method="post" id="verwijderForm">
							<input type="submit" value="Verwijderen" id="verwijderKnop">
						</form></th>
					</tr>
				</thead>
				<c:forEach var="reservatie" items="${reservaties.values()}">
					<tr>
						<td><fmt:formatDate value='${reservatie.voorstelling.datum}' type='both' dateStyle='short' timeStyle="short" /></td>
						<td><c:out value='${reservatie.voorstelling.titel}' /></td>
						<td><c:out value='${reservatie.voorstelling.uitvoerders}' /></td>
						<td><fmt:formatNumber type="currency" value="${reservatie.voorstelling.prijs}" /></td>
						<td><fmt:formatNumber type="number" value="${reservatie.aantalPlaatsen}" /></td>
						<td><input type='checkbox' form="verwijderForm"  name='id' value='${reservatie.voorstelling.id}'></td>
					</tr>
				</c:forEach>
			</table>
			Te betalen: <fmt:formatNumber type="currency" value="${totalePrijs}" />
      </c:otherwise>
    </c:choose>
  </body>
</html>