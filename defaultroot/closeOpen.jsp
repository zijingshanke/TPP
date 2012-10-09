<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<c:if test="${URI==null}">
	<script language="JavaScript">
   	top.location="login.jsp" 
	</script>
</c:if>
<html>
	<head>
		<title>closeOpen</title>
		<script type="text/javascript" language="javascript" src="../_js/table2.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>
	<body>
		<div style="float: left;">
			<img src="./_img/closeSideBarPointer.gif" alt="Go"
				style="position: absolute; top: 40%; float: left;"
				onclick="closeOpen();">
		</div>
	</body>
	<script type="text/javascript">
		function closeOpen(){
			var leftWidth=parent.document.getElementById('middle');
			if(leftWidth.cols=="15%,1%,*"){				
				leftWidth.cols="1%,1%,*";
			}else{
				leftWidth.cols="15%,1%,*";
			}
	}
	
</script>
</html>
