<%@page contentType='text/html' pageEncoding='UTF-8'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<fmt:setBundle basename='resourceBundles.teksten' />
<!doctype html>
<html lang="nl">
<head>
<vdab:head title='Cultuurhuis' />
</head>
<body>
	<h1>Het cultuurhuis:voorstellingen</h1>
	<h2>Genres</h2>
	<nav>
		<c:forEach var="genre" items="${genres}">
			<li><c:url value='/genres/voorstellingen.htm' var='voorstellingURL'>
					<c:param name='id' value='${genre.id}' />
				</c:url> <a href='<c:out value='${voorstellingURL}'/>'>${genre.naam}</a>
			</li>
		</c:forEach>
	</nav>
</body>
</html>
