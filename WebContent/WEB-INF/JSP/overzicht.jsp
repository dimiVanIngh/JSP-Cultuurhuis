<%@page contentType='text/html' pageEncoding='UTF-8'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Overzicht reservaties' />
</head>
<body>
	<h1>
		Het cultuurhuis: overzicht <img src="images/cultuur.jpg"
			alt="cultuur logo" id="cultuur" />
	</h1>
	<ul class="horizontal_menu">
		<li><a href="<c:url value="/index.htm"/>">Voorstellingen</a></li>
	</ul>
	<c:if test="${not empty gelukteReservaties}">
	<h2>Gelukte reserveringen</h2>
		<table>
			<thead>
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Plaatsen</th>
				</tr>
			</thead>
			<c:forEach var="gelukteReservatie" items="${gelukteReservaties}">
				<tr>
					<td><fmt:formatDate value='${gelukteReservatie.voorstelling.datum}' type='both'
							dateStyle='short' timeStyle="short" /></td>
					<td><c:out value='${gelukteReservatie.voorstelling.titel}' /></td>
					<td><c:out value='${gelukteReservatie.voorstelling.uitvoerders}' /></td>
					<td><fmt:formatNumber type="currency"
							value="${gelukteReservatie.voorstelling.prijs}" /></td>
					<td><fmt:formatNumber type="number"
							value="${gelukteReservatie.aantalPlaatsen}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty mislukteReservaties}">
	<h2>Mislukte reserveringen</h2>
		<table>
			<thead>
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Vrije plaatsen</th>
					<th>Plaatsen</th>
				</tr>
			</thead>
			<% int counter = 0; %>
			<c:forEach var="mislukteReservatie" items="${mislukteReservaties}">
				<tr>
					<td><fmt:formatDate value='${mislukteReservatie.voorstelling.datum}' type='both'
							dateStyle='short' timeStyle="short" /></td>
					<td><c:out value='${mislukteReservatie.voorstelling.titel}' /></td>
					<td><c:out value='${mislukteReservatie.voorstelling.uitvoerders}' /></td>
					<td><fmt:formatNumber type="currency"
							value="${mislukteReservatie.voorstelling.prijs}" /></td>
					<td><c:out value="${vrijePlaatsen.get(counter)}" /></td>
					<td><fmt:formatNumber type="number"
							value="${mislukteReservatie.aantalPlaatsen}" /></td>
				</tr>
				<% counter++; %>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>