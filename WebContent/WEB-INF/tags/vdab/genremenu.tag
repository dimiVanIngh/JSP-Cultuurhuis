<%@tag description="menu cultuurhuis" pageEncoding='UTF-8'
	trimDirectiveWhitespaces='true'%>
<%@attribute name='genres' required='true' type='java.util.List'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<ul>
		<c:forEach var="genre" items="${genres}">
			<li><c:url value="/index.htm" var="voorstellingURL">
					<c:param name='id' value='${genre.id}' />
				</c:url> <a href="<c:out value="${voorstellingURL}"/>">${genre.naam}</a></li>
		</c:forEach>
	</ul>
</nav>