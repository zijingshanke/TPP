<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script type="text/javascript">
		function approval()
		{
		 if(confirm("确定批准通过吗？")){  
				document.forms[0].action+="?thisAction=approvalCharge";
				document.forms[0].submit();
			}
		}
		function audit()
		{
		if(confirm("确定核准通过吗？")){ 
			document.forms[0].action+="?thisAction=auditCharge";
			document.forms[0].submit();
			}
		}
		
		</script>
</head>
<body>
<div id="mainContainer">
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
<c:import url="../_jsp/mainTitle.jsp?title1=充值管理&title2=处理充值" charEncoding="UTF-8" /> 
		<hr>
		<html:form action="/transaction/charge.do">
			<html:hidden property="agentLoginName"
				value="${charge.agent.loginName}"></html:hidden>
			<html:hidden property="amount" value="${charge.amount}"></html:hidden>
			<html:hidden property="note" value="${charge.note}"></html:hidden>
			<html:hidden property="status" value="${charge.status}"></html:hidden>
			
			<input type="hidden" name="fromBank" value="<c:out value='${bank}'/>"/>
			<input type="hidden" name="fromStatus" value="<c:out value='${status}'/>"/>
			
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">商户名</td>
					<td style="text-align: left"><html:link
						page="/agent/agentlist.do?thisAction=view&agentId=${charge.agent.id}">
						<c:out value="${charge.agent.name}" />
					</html:link></td>
				</tr>
				<c:if test="${charge.status==3}">
					<tr>
						<td class="lef">批准人</td>
						<td style="text-align: left"><c:out
							value="${charge.sysUser.userName}" /></td>
					</tr>
					<tr>
						<td class="lef">批准时间</td>
						<td style="text-align: left"><c:out
							value="${charge.checkDate}" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="lef">申请金额</td>
					<td style="text-align: left"><fmt:formatNumber
						value="${charge.amount}" pattern="0.00" /></td>
				</tr>
				<tr>
					<td class="lef">备注</td>
					<td style="text-align: left"><html:text property="note1"
						styleClass="colorblue2 p_5" value=""></html:text></td>
				</tr>
				<c:if test="${charge.status==0}">
				<c:check code="sc09">
					<tr>
						<td colspan="2"><input type="button" value="批 准"
							class="button1" onclick="approval();" /> <input type="button"
							value="返 回" class="button1" onclick="window.history.back();" /></td>
					</tr>
					</c:check>
				</c:if>
				<c:if test="${charge.status==3}">
				<c:check code="sc10">
					<tr>
						<td colspan="2"><input type="button" value="核 准"
							class="button1" onclick="audit();" /> <input type="button"
							value="返 回" class="button1" onclick="window.history.back();" /></td>
					</tr>
					</c:check>
				</c:if>
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
