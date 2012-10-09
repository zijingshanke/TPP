<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<html>
	<head>
		<title>left</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript" src="../_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript" src="../_js/goto.js"></script>
</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title"><a href="userlist.do?thisAction=list"
						target="mainFrame">用户管理</a>
					</span>
					<ul class="contents">
						<c:check code="sa06">
						<li><a href="userlist.do?thisAction=list" target="mainFrame">用户列表</a></li>
						</c:check>
					</ul>
			</div>
			</div>
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
		</div>

	</body>
</html>
