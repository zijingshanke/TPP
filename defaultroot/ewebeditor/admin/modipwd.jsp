<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" session="true"%>
<%request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="eWebEditor" class="ewebeditor.admin.modipwd_jsp" scope="page"/>
<%
eWebEditor.Load(pageContext);
%>
