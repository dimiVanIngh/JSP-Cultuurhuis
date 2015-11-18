<%@page contentType='text/html' pageEncoding='UTF-8' trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang="nl">
<head>
<vdab:head title='Cultuurhuis' />
</head>
<body>
	<h1>Het cultuurhuis:voorstellingen <img src="images/cultuur.jpg" alt="cultuur logo" id="cultuur" /> </h1>
	<h2>Genres</h2>
	<vdab:menu genres="${genres}"/>
	<c:if test="${not empty voorstellingen}">
		bla
	</c:if>
</body>
</html>
