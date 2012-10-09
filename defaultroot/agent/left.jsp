<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>left</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript"
			src="../_js/goto.js"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title"><a href="#">商户管理</a>
					</span>
					<ul class="contents">
						<c:check code="sb01">
						<li><a href="agentlist.do?thisAction=listAgent&sort=2" target="mainFrame">商户列表</a></li>
						<li><a href="agentlist.do?thisAction=listAgentBalance&sort=3" target="mainFrame">商户余额列表</a></li>
						</c:check>
						<c:check code="sb11">
						<li><a href="agentBalancePointlist.do?thisAction=listAgentBalancePoint&sort=3" target="mainFrame">商户余额盘点</a></li>
						</c:check>
						<c:check code="sb04">
						<li>
						<a href="coterielist.do?thisAction=list" target="mainFrame">商户圈列表</a>
						</li>
						<li>
						<a href="agentlist.do?thisAction=listAgentRelation" target="mainFrame">上下级列表</a>
						</li>
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
