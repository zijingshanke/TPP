<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
					<span class="title"><a href="#">财务管理</a></span>
					<ul class="contents">
						<c:check code="sj01">
						<li><a href="../transaction/debitlist.do?thisAction=listDebit" target="mainFrame">预支列表</a></li>
						</c:check>
						<c:check code="sj01">
						<li><a href="../transaction/debitLoglist.do?thisAction=listDebitLog" target="mainFrame">预支日志列表</a></li>
						</c:check>
						<c:check code="sj02">
						<li><a href="../transaction/expenselist.do?thisAction=listExpense" target="mainFrame">报销列表</a></li>
						</c:check>
						<c:check code="sj02">
						<li><a href="../transaction/expenseLoglist.do?thisAction=listExpenseLog" target="mainFrame">报销日志列表</a></li>
						</c:check>
						
						
			</div>
        </div>
        <div class="closeSiseBar"><span class="btn"></span></div>
        </div>
        
  </body>
</html>
