<%@ page contentType="text/html;charset=utf-8"%>
<%request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="eWebEditor" class="ewebeditor.server.i_jsp" scope="page"/>

<%
eWebEditor.Load(pageContext);
%>