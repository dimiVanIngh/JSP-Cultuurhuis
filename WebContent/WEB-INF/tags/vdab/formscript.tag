<%@ tag description="script to avoid dubble clicken a submit button" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces ='true'%> 
<%@attribute name='formID' required='true' type='java.lang.String'%>
<%@attribute name='buttonID' required='true' type='java.lang.String'%>
    <script>
 		document.getElementById('${formID}').onsubmit = function(){
    		document.getElementById('${buttonID}').disabled = true;
    	};
	</script>