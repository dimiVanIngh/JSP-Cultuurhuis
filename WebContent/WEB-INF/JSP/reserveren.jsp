<%@page contentType='text/html' pageEncoding='UTF-8' session='false' trimDirectiveWhitespaces ='true'%> 
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%> 
<!doctype html>
<html lang='nl'> 
  <head>
  <c:choose>
  	<c:when test='${not empty voorstelling}'><vdab:head title="${voorstelling.titel}" /></c:when>
  	<c:otherwise><vdab:head title='Voorstelling niet gevonden' /></c:otherwise>
  </c:choose>   
  </head> 
  <body>
    <c:choose> 
      <c:when test='${not empty fout}'>
        <div class='fout'>${fout}</div>
      </c:when>
      <c:when test="${empty voorstelling}">
        <div class='fout'>Voorstelling niet gevonden</div>
      </c:when> 
      <c:otherwise>
        <h1>${voorstelling.naam}</h1>
        <dl>
          <dt>Voorstelling:</dt><dd>${voorstelling.titel}</dd>
          <dt>Uitvoerders</dt><dd>${voorstelling.uitvoerders}</dd>
          <dt>Prijs</dt><dd>${voorstelling.prijs}</dd>
        </dl>
      </c:otherwise>
    </c:choose>
  </body>
</html>