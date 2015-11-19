<%@page contentType='text/html' pageEncoding='UTF-8'
	trimDirectiveWhitespaces='true' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang="nl">
<head>
<vdab:head title='Cultuurhuis' />
</head>
<body>
	<h1>
		Het cultuurhuis:voorstellingen <img src="images/cultuur.jpg"
			alt="cultuur logo" id="cultuur" />
	</h1>
	<h2>Genres</h2>
	<vdab:menu genres="${genres}" />
	<c:if test="${not empty voorstellingen}">
		<table>
			<thead>
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Vrije plaatsen</th>
					<th>Reserveren</th>
				</tr>
			</thead>
			<c:forEach var="voorstelling" items="${voorstellingen}">
				<tr>
					<td><fmt:formatDate value='${voorstelling.datum}' type='both' dateStyle='short' timeStyle="short" /></td>
					<td><c:out value='${voorstelling.titel}' /></td>
					<td><c:out value='${voorstelling.uitvoerders}' /></td>
					<td><fmt:formatNumber type="currency" value="${voorstelling.prijs}" /></td>
					<td><fmt:formatNumber type="number" value="${voorstelling.vrijePlaatsen}" /></td>
					<td><c:if test="${voorstelling.vrijePlaatsen > 0}">
							<c:url value="/reserveren.htm" var="reserveringURL">
								<c:param name='id' value='${voorstelling.id}' />
							</c:url>
							<a href="<c:out value="${reserveringURL}"/>">Reserveren</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
