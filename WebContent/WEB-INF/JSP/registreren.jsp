<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang="nl">
<head>
<vdab:head title='Registreren' />
</head>
<body>
	<h1>Het cultuurhuis: nieuwe klant <img src="images/cultuur.jpg"alt="cultuur logo" id="cultuur" /></h1>
	<ul class="horizontal_menu">
		<li><a href="<c:url value='/index.htm'/>">Voorstellingen</a></li>
		<c:if test="${not empty reservaties}">
			<li><a href="<c:url value="/winkelmandje.htm"/>">Reservatiemandje</a></li>
			<li><a href="<c:url value="/bevestig.htm"/>">Bevestig reservatie</a></li>
		</c:if>
	</ul>
	<form method="post" name="registratieForm">
		<label>Voornaam: 
		<input name='voornaam' value="<c:out value='${param.voornaam}'/>" type="text" autofocus required></label>
		<label>Familienaam: 
		<input name='familienaam' value="<c:out value='${param.familienaam}'/>" type="text" required></label>
		<label>Straat: 
		<input name='straat' value="<c:out value='${param.straat}'/>" type="text" required></label>
		<label>Huisnr: 
		<input name='huisnr' value="<c:out value='${param.huisnr}'/>" type="text" required></label>
		<label>Postcode: 
		<input name='postcode' value="<c:out value='${param.postcode}'/>" type="text" required></label>
		<label>Gemeente: 
		<input name='gemeente' value="<c:out value='${param.gemeente}'/>" type="text" required></label>
		<label>Gebruikersnaam: 
		<input name='gebruikersnaam' value="<c:out value='${param.gebruikersnaam}'/>"type="text" required></label>
		<label>Paswoord: 
		<input name='wachtwoord' type="password" required></label>
		<label>Herhaal paswoord: 
		<input name='confirmwachtwoord' type="password" required></label> 
		<input type="submit" value="Ok" id="bevestigKnop">
	</form>
	<c:if test="${not empty fouten}"></c:if>
	<ul>
		<c:forEach var='fout' items="${fouten}">
		<li class="error_list">${fout}</li>
		</c:forEach>
	</ul>
	<vdab:formscript formName="registratieForm" buttonID="bevestigKnop"/>
</body>
</html>
