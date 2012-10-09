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
					<span class="title"><a href="#">交易管理</a></span>
					<ul class="contents">
						<c:check code="sc01">
						<li><a href="transactionlist.do?thisAction=list" target="mainFrame">全部交易列表</a></li>
						<li><a href="transactionlist.do?thisAction=list&mode=1" target="mainFrame">进行中交易列表</a></li>
						<li><a href="transactionlist.do?thisAction=list&mode=3" target="mainFrame">成功交易列表</a></li>
						<li><a href="transactionlist.do?thisAction=list&mode=4" target="mainFrame">失败交易列表</a></li>
						<li><a href="transactionlist.do?thisAction=list&mode=11" target="mainFrame">退款交易列表</a></li>
					    </c:check>
					    <c:check code="sc02">
						<li><a href="chargelist.do?thisAction=listAllCharge" target="mainFrame">充值统计</a></li>
						</c:check>
						<c:check code="sc02">
						<li><a href="chargelist.do?thisAction=listGeneralCharge&status=1&bank=0" target="mainFrame">普通充值列表</a></li>
						</c:check>
						
						<li><a href="chargeLoglist.do?thisAction=listChargeLog" target="mainFrame">充值日志</a></li>
						<li><a href="chargelist.do?thisAction=listOtherCharge" target="mainFrame">线下充值列表</a></li>
						<li><a href="otherCharge.jsp" target="mainFrame">线下充值</a></li>
						<li><a href="drawlist.do?thisAction=listSubtract" target="mainFrame">系统扣款列表</a></li>
						<li><a href="subtract.jsp" target="mainFrame">系统扣款</a></li>
						<c:check code="sc04">
						<li><a href="drawlist.do?thisAction=listDraw" target="mainFrame">提现列表</a></li>
						</c:check>
						<c:check code="sc07">
						<li><a href="drawLoglist.do?thisAction=listDrawLog" target="mainFrame">提现日志</a></li>
						</c:check>
						<li><a href="drawlist.do?thisAction=download&bank=99" target="mainFrame">下载提现文件</a></li>
						<li><a href="../cooperate/actionLoglist.do?thisAction=list" target="mainFrame">操作日志</a></li>
						
					</ul>
			</div>
			</div>
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
		</div>

	</body>
</html>
