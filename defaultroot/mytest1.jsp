<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-html-el.tld" prefix="html" %>

<html>
<head>

<script src="<%=request.getContextPath()%>/_js/prototype.js"></script>
<script src="<%=request.getContextPath()%>/_js/certification.js"></script>
<script   language="javascript">   
function test()
{

  alert(window.frames['iframe1'].email);
  //$('iframe1').testValid();

}
</script>
</head>

<body>

<iframe id="iframe1" name="iframe1" src="http://qm.qmpay.com/mytest.jsp" ></iframe> 


<a href="#" onClick="test()">到钱门去</a>



</body>
</html>



