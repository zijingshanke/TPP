<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		
		<script>				
			function addEditorValue(){
				 // document.forms[0].content.value="sssaasas";
				//alert("values is----"+document.forms[0].content.value);
				//alert("content_html---"+window.content_html.getHTML())
				document.forms[0].content.value= window.content_html.getHTML(); 		
			}
			
			function setEditorHTML(){
			//alert("aaa")
				window.content_html.setHTML(document.forms[0].content.value);
			}
						
			function addPatternEmail()
			{
				addEditorValue();
			    document.forms[0].submit();
			}						
		</script>
	</head>
	<body onLoad="setEditorHTML();">
		<div id="mainContainer" style="height:780px;">
			<div id="container">
				<html:form action="/system/patternEmail.do" >
					<html:hidden property="thisAction" name="patternEmail" />
					<html:hidden property="id" name="patternEmail" />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
								<div class="crumb">	
<c:import url="../_jsp/mainTitle.jsp?title1=邮件模板管理&title2=模板编辑" charEncoding="UTF-8" />
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td  class="lef">
											名称
										</td>
										<td style="text-align: left" >
											<html:text property="name" name="patternEmail" styleClass="colorblue2 p_5"/>
										</td>
									</tr>
									<tr>
										<td  class="lef">
											状态
										</td>
										<td style="text-align: left">											
											<html:radio property="status" value="1" name="patternEmail" >启用</html:radio>
											<html:radio property="status" value="0" name="patternEmail" >停用</html:radio>					
										</td>
									</tr>
									<tr>
										<td  class="lef">
											Code
										</td>
										<td style="text-align: left">
											<html:text property="code" name="patternEmail" styleClass="colorblue2 p_5" />
										</td>										
									</tr>
									<tr>
										<td  class="lef">
											描述
										</td>
										<td style="text-align: left">
											<html:textarea property="description" name="patternEmail" styleClass="colorblue4 p_5" style="width:100%;heigh:100%;" />
										</td>
									</tr>
									<tr>
										<td  class="lef">
											内容
											<html:hidden property="content" name="patternEmail" />
											
										</td>										
										<td style="text-align: left">
											<input name="label" type="button" class="button1" value="保存"
												onclick="addPatternEmail();">
											<input name="label" type="button" class="button1" value="重置"
												onclick="document.patternEmail.reset();">
										</td>
										</tr>
										<tr>
										<td colspan="2">							
											<div align="left">
													<iframe  ID="eWebEditor1" name="content_html" src="../ewebeditor/ewebeditor.htm?id=content&style=coolblue" frameborder="1" scrolling="auto" align="top" width="680" height="500" />	
											</div>
										</td>																									
									</tr>
									<c:check code="sg07,sg08">
									<tr>
									<td>
											<input name="label" type="button" class="button1" value="保存"
												onclick="addPatternEmail();">
												
											<input name="label" type="button" class="button1" value="重置"
												onclick="document.patternEmail.reset();">
										</td>
									</tr>
									</c:check>
							</table>
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
				</html:form>
			</div>
		</div>
	</body>
	<script>
	setEditorHTML();
	</script>
</html>
