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
<title>top�ޱ����ĵ�</title>
<link href="_css/reset.css" rel="stylesheet" type="text/css" />
<link href="_css/global.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript"
	src="./_js/jquery-1.3.2.min.js"></script>
</head>
<body>
<div id="header">
<div class="logo">Logo</div>
<div class="mainNav">
<ul class="navContent">
	<li><a href="left.jsp" target="leftFrame">首页</a></li>
	
	<c:check code="sb01,sb02,sb03,sb04,sb05,sb06,sb07,sb08,sb09,sb10">
	<li><a href="agent/left.jsp" target="leftFrame">商户管理</a></li>
	</c:check>

	<c:check code="sc01,sc02,sc03,sc04,sc05,sc06,sc07">
	<li><a href="transaction/left.jsp" target="leftFrame">交易管理</a></li>
	</c:check>
	<c:check code="sj01,sj02">
	<li><a href="finance/left.jsp" target="leftFrame">财务管理</a></li>
	</c:check>
		
<c:check code="sf01,sf02,sf03">
	<li><a href="sageManagement/left.jsp" target="leftFrame">安全管理</a></li>
</c:check>

<c:check code="sa02,sa03,sa04,as05,sa06">
    <li><a href="user/left.jsp" target="leftFrame">用户管理</a></li>
    </c:check>
<c:check code="se01,se02">
	<li><a href="right/left.jsp" target="leftFrame">权限管理</a></li>
</c:check>

<c:check code="sg01,sg02,sg03,sg04,sg05,sg06,sg07,sg08,sg09,sg10,sg11,sg12,sg13">
	<li><a href="system/left.jsp" target="leftFrame">系统管理</a></li>
</c:check>
<c:check code="sh01">
	<li><a href="information/left.jsp" target="leftFrame">新闻发布</a></li>
	</c:check>
</ul>
<ul class="userPanel">
	<li>管理员：<FONT color="red"><c:out value="${URI.user.userName}" /></FONT></li>
	<li><a href="user/user.do?thisAction=exit">退出</a></li>
</ul>
</div>
</div>
</body>
</html>
