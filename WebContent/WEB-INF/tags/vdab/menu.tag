<%@tag description="menu frituur frida" pageEncoding='UTF-8' trimDirectiveWhitespaces ='true'%> 
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<header>
<nav><ul>
<li><a href="<c:url value='/'/>">Welkom</a></li>
<li><a href="<c:url value='/sauzen.htm'/>">Sauzen</a></li>
<li><a href="<c:url value='/ingredienten.htm'/>">Ingrediënten</a></li>
<li><a href="<c:url value='/meisjesjongens.htm'/>">Meisje of Jongen</a></li>
<li><a href="<c:url value='/zoekdefriet.htm'/>">Zoek de friet</a></li>
<li><a href="<c:url value='/sausraden.htm'/>">Raad de saus</a></li>
<li><a href="<c:url value='/statistiek.htm'/>">Statistieken</a></li>
<li><a href="<c:url value='/gastenboek.htm'/>">Gastenboek</a></li>
<c:choose>
	<c:when test="${empty ingelogd}"><li><a href="<c:url value='/inloggen.htm'/>">Inloggen</a></li></c:when>
	<c:otherwise><li><a href="<c:url value='/gastenboek.htm?uitloggen=true'/>">Uitloggen</a></li></c:otherwise>
</c:choose>
</ul></nav>
</header>