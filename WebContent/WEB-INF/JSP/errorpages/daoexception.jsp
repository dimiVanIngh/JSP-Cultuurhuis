<%@page contentType='text/html' pageEncoding='UTF-8' session='false' trimDirectiveWhitespaces ='true'%> 
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%> 
<!doctype html>
<html lang='nl'> 
  <head>
    <vdab:head title='Problemen met het ophalen van data'/>
  </head> 
  <body>
    <h1>Problemen bij het ophalen van de data</h1>
    <img src='<c:url value="/images/datafout.jpg"/>' alt='data fout'>
    <p>We kunnen de gevraagde data niet ophalen 
       wegens een technische storing.<br>
       Gelieve de helpdesk te contacteren.</p>
  </body>
</html>