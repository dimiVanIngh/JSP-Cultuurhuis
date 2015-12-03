<%@ tag description="script to avoid dubble click on a form submit button" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces ='true'%> 
<%@attribute name='formName' required='true' type='java.lang.String'%>
<%@attribute name='buttonID' required='true' type='java.lang.String'%>
    <script>
 		document.getElementsByName('${formName}').onsubmit = function(){
    		document.getElementById('${buttonID}').disabled = true;
    	};
	</script>