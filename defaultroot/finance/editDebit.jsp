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
		function  audit()
		{
		 if(confirm("确定审核通过吗？")){  
				document.forms[0].action+="?thisAction=auditDebit";
				document.forms[0].submit();
			}
		}
		function approval()
		{
		if(confirm("确定批准通过吗？")){ 
			document.forms[0].action+="?thisAction=approvalDebit";
			document.forms[0].submit();
			}
		}
		function advance()
		{
		if(confirm("确定预支这笔款项吗？")){ 
			document.forms[0].action+="?thisAction=advanceDebit";
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
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=处理预支" charEncoding="UTF-8" /> 
		<hr>
		<html:form action="/transaction/debit.do">
			<html:hidden property="agentLoginName"
				value="${debit.agent.loginName}"></html:hidden>
			<html:hidden property="amount" value="${debit.amount}"></html:hidden>
			<html:hidden property="note" value="${debit.note}"></html:hidden>
			<html:hidden property="status" value="${debit.status}"></html:hidden>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">商户名</td>
					<td style="text-align: left"><html:link
						page="/agent/agentlist.do?thisAction=view&agentId=${debit.agent.id}">
						<c:out value="${debit.agent.name}" />
					</html:link></td>
				</tr>
				<c:if test="${debit.status==1}">
					<tr>
						<td class="lef">审核人</td>
						<td style="text-align: left"><c:out
							value="${debit.checkName}" /></td>
					</tr>
					<tr>
						<td class="lef">审核时间</td>
						<td style="text-align: left"><c:out
							value="${debit.checkDate}" /></td>
					</tr>
				</c:if>
				<c:if test="${debit.status==2}">
					<tr>
						<td class="lef">审核人</td>
						<td style="text-align: left"><c:out
							value="${debit.checkName}" /></td>
					</tr>
					<tr>
						<td class="lef">审核时间</td>
						<td style="text-align: left"><c:out
							value="${debit.checkDate}" /></td>
					</tr>
					<tr>
						<td class="lef">批准人</td>
						<td style="text-align: left"><c:out
							value="${debit.user1Name}" /></td>
					</tr>
					<tr>
						<td class="lef">批准时间</td>
						<td style="text-align: left"><c:out
							value="${debit.check1Date}" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="lef">申请金额</td>
					<td style="text-align: left"><fmt:formatNumber
						value="${debit.amount}" pattern="0.00" /></td>
				</tr>
				<tr>
					<td class="lef">备注</td>
					<td style="text-align: left"><html:text property="note1"
						styleClass="colorblue2 p_5" value=""></html:text></td>
				</tr>
				<c:if test="${debit.status==0}">
				<c:check code="sj01">
					<tr>
						<td colspan="2"><input type="button" value="审 核"
							class="button1" onclick="audit();" /> <input type="button"
							value="返 回" class="button1" onclick="window.history.back();" /></td>
					</tr>
					</c:check>
				</c:if>
				<c:if test="${debit.status==1}">
				<c:check code="sj01">
					<tr>
						<td colspan="2"><input type="button" value="批 准"
							class="button1" onclick="approval();" /> <input type="button"
							value="返 回" class="button1" onclick="window.history.back();" /></td>
					</tr>
					</c:check>
				</c:if>
				<c:if test="${debit.status==2}">
				<c:check code="sj01">
					<tr>
						<td colspan="2"><input type="button" value="转 账"
							class="button1" onclick="advance();" /> <input type="button"
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
