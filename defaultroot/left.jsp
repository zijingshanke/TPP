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
    <title>left</title>
<link href="_css/reset.css" rel="stylesheet" type="text/css" />
<link href="_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="./_js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" language="javascript" src="./_js/goto.js"></script>
  </head>
  <body>
  <div id="mainContainer"> 
  <div class="fixedSideBar"></div>
    	<div id="sideBar">

            <div class="sideBarItem webAdmin">
            	<span class="title"><a href="transaction/transactionStatistic.do?thisAction=listStatistic" target="mainFrame">待办事项</a></span>
            	<span class="title"><a href="user/user.do?thisAction=editMyPassword&userId=<c:out value='${URI.user.userId}' />" target="mainFrame">修改密码</a></span>
     
			</div>
        </div>
        <div class="closeSiseBar"><span class="btn"></span></div>
        </div>
        
  </body>
</html>
