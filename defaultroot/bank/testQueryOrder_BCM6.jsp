<%@page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.fordays.fdpay.bank.LogUtil"%>
<%@page import="com.fordays.fdpay.bank.bcm.biz.BcmBankBizImp"%>
<%@page import="com.fordays.fdpay.bank.ResultFromBank"%>
<%
	String orders = request.getParameter("orders");
	String version = request.getParameter("version");

	LogUtil myLog = new LogUtil(false, true, this.getClass());
	myLog.info("orders=" + orders);

	BcmBankBizImp imp = new BcmBankBizImp();
	ResultFromBank result = imp.initiativeQueryOrder(orders, version);

	out.clear();
	out.print("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>");
	out.print("<bankorder>");
	out.print("<orderNo>" + result.getROrderNo() + "</orderNo>");
	out.print("<amount>" + result.getRAmount() + "</amount>");
	out.print("<chargeStatus>" + result.getRChargeStatus()+ "</chargeStatus>");
	out.print("<requestHost>" + result.getRRequestHost()+ "</requestHost>");
	out.print("<url>" + "API-Query" + "</url>");
	out.print("</bankorder>");
%>
