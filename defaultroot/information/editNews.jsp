<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script src="../_js/xheditor.js" type="text/javascript"></script>
<script src="../_js/xheditor_plugins/ubb.js" type="text/javascript"></script>
		<script>				
			function addEditorValue(){
				 // document.forms[0].content.value="sssaasas";
				//alert("values is----"+document.forms[0].content.value);
				//alert("content_html---"+window.content_html.getHTML())
				document.forms[0].content.value= window.content1.getHTML(); 		
			}
			
			function setEditorHTML(){
			//alert("aaa")
				window.content1.setHTML(document.forms[0].content.value);
			}
						
			function add()
			{
				addEditorValue();
			    document.forms[0].submit();
			}						
		</script>
</head>
<body onLoad="setEditorHTML();">
<div id="mainContainer" style="height:1000px;">
<div id="container">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="10" height="10" class="tblt"></td>
			<td height="10" class="tbtt"></td>
			<td width="10" height="10" class="tbrt"></td>
		</tr>
		<tr>
			<td width="10" class="tbll"></td>
			<td valign="top" class="body">
			<c:if test="${news.thisAction eq 'update'}">
<c:import url="../_jsp/mainTitle.jsp?title1=新闻发布&title2=修改新闻" charEncoding="UTF-8" />
			</c:if>
			<c:if test="${news.thisAction eq 'insert'}">
<c:import url="../_jsp/mainTitle.jsp?title1=新闻发布&title2=添加新闻" charEncoding="UTF-8" />
			</c:if>
			<hr>
			
			<html:form action="/information/news.do" >
			<html:hidden property="id" value="${news.id}"></html:hidden>
			<c:if test="${news.thisAction eq 'update'}">
			<html:hidden property="thisAction" value="update"></html:hidden>
			</c:if>
			<c:if test="${news.thisAction eq 'insert'}">
			<html:hidden property="thisAction" value="insert"></html:hidden>
			</c:if>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
				<td>标题</td>
				<td><html:text property="title" name="news" styleClass="colorblue2 p_5" style="width:300px;"></html:text></td>
				</tr>
				<tr>
				<td>排名</td>
				<td><html:text property="rank" name="news" styleClass="colorblue2 p_5" ></html:text></td>
				</tr>
				<tr>
				<td>是否显示</td>
				<td>
					<html:radio property="status" value="0" name="news" >不显示</html:radio>
					<html:radio property="status" value="1" name="news">显示</html:radio>
				</td>
				</tr>
				<tr>
				<td>&nbsp;</td>
					<td>
					 <input name="label" type="button" class="button1" value="提 交" onclick="add();"/>
						<input name="label" type="reset" class="button1" value="重 置" onclick="document.patternEmail.reset();" />
						 <input type="button" value="返 回" class="button1" onclick="window.history.back();"/>
					 </td>
					  
				</tr>
				<tr>
				<td>内容<html:hidden property="content" name="news"  /></td>
				<td>
					<iframe  ID="eWebEditor1" name="content1" src="../ewebeditor/ewebeditor.htm?id=content1&style=coolblue" frameborder="1" scrolling="auto" align="top" width="680" height="500"/>	
				</td>
				</tr>
				</table>
			</html:form>
			<div class="clear"></div>
			</td>
							<td width="10" class="tbrr"></td>
						</tr>
						<tr>
							<td width="10" class="tblb"></td>
							<td class="tbbb"></td>
							<td width="10" class="tbrb"></td>
						</tr>
	</table>
</div>
</div>
</body>
</html>