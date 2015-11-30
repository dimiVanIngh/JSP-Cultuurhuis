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
	<ul id="horizontal_menu">
		<li><a href="<c:url value='/index.htm'/>">Voorstellingen</a></li>
		<li><a href="<c:url value="/index.htm"/>">Reservatiemandje</a></li>
		<li><a href="<c:url value="/index.htm"/>">Bevestig reservatie</a></li>
	</ul>
	<form method="post" id="registratieForm">
		<label>Voornaam: 
		<input name='voornaam' value="${param.voornaam}" type="text" autofocus required></label>
		<label>Familienaam: 
		<input name='familienaam' value="${param.familienaam}" type="text" required></label>
		<label>Straat: 
		<input name='straat' value="${param.straat}" type="text" required></label>
		<label>Huisnr: 
		<input name='huisnr' value="${param.huisnr}" type="text" required></label>
		<label>Postcode: 
		<input name='postcode' value="${param.postcode}" type="text" required></label>
		<label>Gemeente: 
		<input name='gemeente' value="${param.gemeente}" type="text" required></label>
		<label>Gebruikersnaam: 
		<input name='gebruikersnaam' type="text" required></label>
		<label>Paswoord: 
		<input name='wachtwoord' type="password" required></label>
		<label>Herhaal paswoord: 
		<input name='confirmwachtwoord' type="password" required></label> 
		<input type="submit" value="Ok" id="bevestigKnop">
	</form>
	<c:if test="${not empty fouten}"></c:if>
	<ul>
		<c:forEach var='fout' items="${fouten}">
		<span class ="fout">${fout}</span>
		</c:forEach>
	</ul>
	<vdab:formscript formID="registratieForm" buttonID="bevestigKnop"/>
</body>
</html>
