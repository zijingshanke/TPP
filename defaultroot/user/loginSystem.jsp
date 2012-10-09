<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" href="../_css/login.css" type="text/css"></link>
</head>

<body style="text-align: center">
  <div id="warp">
    <p class="top_p">欢迎登录钱门账户</p>
    <html:form action="/user/user.do?thisAction=login">
   
    <table>
    <tr height="40px"><td>账 户 名:</td><td><html:text property="userNo" name="user"/></td></tr>
    <tr height="50px"><td>登录密码：</td><td><html:text property="userPassword" name="user"/></td></tr>
       <tr height="40px"><td style="width: 80%">验证码:</td><td><html:text property="rand" size="8" name="user"/><html:img
	border="0" page="/servlet/com.fordays.common.NumberImage" align="absmiddle"
	height="21" width="64" /></td></tr>
    </table>
      
    <p class="btn_p"><input type="submit" value="登录" class="btn1" /></p>

  </html:form>
  </div>
</body>