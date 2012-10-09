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
					<span class="title"><a href="#">系统管理</a> </span>
					<ul class="contents">
						<c:check code="sg01"><li>
						
							<a href="sysConfigList.do?thisAction=list" target="mainFrame">系统参数</a>
							
						</li></c:check>
						<c:check code="sg04"><li>
						
							<a href="loginloglist.do?thisAction=list&locate=2" target="mainFrame">后台登录日志</a>
						
						</li></c:check>
						<c:check code="sg05"><li>
						
							<a href="loginloglist.do?thisAction=list&locate=1" target="mainFrame">前台登录日志</a>
						
						</li></c:check>
						<c:check code="sg06"><li>
						
							<a href="patternEmaillist.do?thisAction=listPatternEmails" target="mainFrame">邮件模板</a>
						
						</li></c:check>
						<c:check code="sg10"><li>
						
							<a href="patternShortMessagelist.do?thisAction=list"
								target="mainFrame">短信模板</a>
								
						</li></c:check>
						<c:check code="sg10"><li>
						
							<a href="banklist.do?thisAction=list"
								target="mainFrame">银行列表</a>
								
						</li></c:check>
					</ul>
				</div>
			</div>
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
		</div>

	</body>
</html>
