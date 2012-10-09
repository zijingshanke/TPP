<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" session="true"%>
<%request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="eWebEditor" class="ewebeditor.admin.default_jsp" scope="page"/>
<%
eWebEditor.Load(pageContext);
%>

<html>
<head>
<title>eWebEditor在线编辑器 - 后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="JavaScript">
window.self.focus();
</script>
</head>
<frameset cols="150,*" framespacing="0" border="0" frameborder="0">
  <frame name="menu" src="menu.jsp" scrolling="yes">
  <frame name="main" src="main.jsp" scrolling="yes">
  <noframes>
    <body topmargin="0" leftmargin="0">
    <p>此网页使用了框架，但您的浏览器不支持框架</p>
    </body>
  </noframes>
</frameset>
</html>
