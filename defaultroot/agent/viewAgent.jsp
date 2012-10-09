<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>

<script type="text/javascript" language="javascript">
function searchUser()
{
   document.forms[0].thisAction.value="list";
   document.forms[0].submit();
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
<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=详细信息" charEncoding="UTF-8" />
								<hr>
								<html:form action="/agent/agentlist.do">
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">登录名</td><td style="text-align: left" >&nbsp;<c:out value="${agent.loginName}"/></td>
					<td class="lef">名字</td><td style="text-align: left" >&nbsp;<c:out value="${agent.name}"/></td>
				</tr>
				<tr>
					<td class="lef">账户类型</td><td style="text-align: left" >&nbsp;<c:if test="${agent.registerType==1}">企业用户</c:if><c:if test="${ab.agent.registerType==0}">个人用户</c:if> </td>
					<td class="lef">企业名称</td><td style="text-align: left" >&nbsp;<c:out value="${agent.companyName}"/></td>
                 </tr>
				<tr>
					<td class="lef">登陆次数</td><td style="text-align: left" >&nbsp;<c:out value="${agent.loginNum}"/></td>
					<td class="lef">地址</td><td style="text-align: left" >&nbsp;<c:out value="${agent.address}"/></td>
				</tr>
				<tr>
				   <td class="lef">电话</td><td style="text-align: left" >&nbsp;<c:out value="${agent.telephone}"/></td>
					<td class="lef">移动电话</td><td style="text-align: left" >&nbsp;<c:out value="${agent.mobile}"/></td>
				</tr>
				<tr>
					<td class="lef">证件类型</td><td style="text-align: left" >&nbsp;<c:out value="${agent.certTypeTo}"/></td>
					<td class="lef">证件号码</td><td style="text-align: left" >&nbsp;<c:out value="${agent.certNum}"/></td>
				</tr>
				<tr>
					<td class="lef">Email</td><td style="text-align: left" >&nbsp;<c:out value="${agent.email}"/></td>
					<td class="lef">商户状态</td><td style="text-align: left" >&nbsp;<c:out value="${agent.statusTo}"/></td>
				</tr>
				<tr>
					<td class="lef">登陆IP</td><td style="text-align: left" >&nbsp;<c:out value="${agent.loginIp}"/></td>
					<td class="lef">注册时间</td><td style="text-align: left" >&nbsp;<c:out value="${agent.registerDate}"/></td>
				</tr>
				<tr>
					<td class="lef">更新时间</td><td style="text-align: left" >&nbsp;<c:out value="${agent.updateDate}"/></td>
					<td class="lef">上次登陆时间</td><td style="text-align: left" >&nbsp;<c:out value="${agent.lastLoginDate}"/></td>
				</tr>
				<tr>
					<td class="lef">安全问题1</td><td style="text-align: left" >&nbsp;<c:out value="${agent.question}"/></td>
					<td class="lef">回答1</td><td style="text-align: left" >&nbsp;<c:out value="${agent.answer}"/></td>
				</tr>
				<tr>
					<td class="lef">安全问题2</td><td style="text-align: left" >&nbsp;<c:out value="${agent.question1}"/></td>
					<td class="lef">回答2</td><td style="text-align: left" >&nbsp;<c:out value="${agent.answer1}"/></td>
				</tr>
				<tr>
					<td class="lef">安全问题3</td><td style="text-align: left" >&nbsp;<c:out value="${agent.question2}"/></td>
					<td class="lef">回答3</td><td style="text-align: left" >&nbsp;<c:out value="${agent.answer2}"/></td>
				</tr>
				<tr>
					<!-- <td class="lef">积分</td><td style="text-align: left">&nbsp;<c:out value="${agent.score}"/></td> --> 
					<td class="lef">可用余额</td><td style="text-align: left" >&nbsp;
					 <fmt:formatNumber value="${balance}" pattern="0.00" />
                    &nbsp;元
					</td>
					<td class="lef">冻结余额</td><td style="text-align: left" >&nbsp; <fmt:formatNumber value="${notallowBalance}" pattern="0.00" />&nbsp;元</td>
				</tr>
				<tr>
                   <td class="lef">信用余额</td><td style="text-align: left">&nbsp;
					<fmt:formatNumber value="${creditBalance}" pattern="0.00" />&nbsp;元</td>
					 <td class="lef">总余额</td><td style="text-align: left" >&nbsp;
                    <fmt:formatNumber value="${statAllowBalance}" pattern="0.00" />
                    &nbsp;元</td>
                 </tr>
				
			</table>
			</html:form>
			<div>
			<input type="button" class="button2" value="返 回" onclick="javascript:history.back(-1);" />&nbsp;
			<input name="label" type="button" class="button2" value="银行卡信息" onclick="toAccountList('<c:out value="${agent.id}"/>');">&nbsp;
			<input type="button" class="button2" value="商户联系人" onclick="toAgentContactList('<c:out value="${agent.id}"/>');"/>&nbsp;
			<input type="button" class="button2" value="联系人地址" onclick="toAgentAddressList('<c:out value="${agent.id}"/>');"/>&nbsp;
			<!--  <input type="button" class="button2" value="帐户明细" onclick="toAccountDetail('<c:out value="${agent.id}"/>');"/>&nbsp; -->
			<input type="button" class="button2" value="帐户明细" onclick="toAccountDetail2('<c:out value="${agent.id}"/>');"/>&nbsp;
			<input type="button" class="button2" value="查看证书" onclick="showCertificate('<c:out value="${agent.id}"/>');"/>&nbsp;
			<input type="button" class="button2" value="更新余额" onclick="updateBalance('<c:out value="${agent.id}"/>');"/>&nbsp;
			<input type="button" class="button2" value="冻结资金" onclick="freezeMoney('<c:out value="${agent.id}"/>');"/>&nbsp;
			<input type="button" class="button2" value="解冻资金" onclick="thawyMoney('<c:out value="${agent.id}"/>');"/>&nbsp;
			<hr/>
			</div>
			<script type="text/javascript">
				function toAccountList(agentId){
					window.location.href="agent.do?thisAction=listAccount&agentId="+agentId;
				}
				function toAgentAddressList(agentId){
					window.location.href="agent.do?thisAction=listAgentAddress&agentId="+agentId;
				}
				function toAgentContactList(agentId){
					window.location.href="agent.do?thisAction=listAgentContact&agentId="+agentId;
				}
				function toAccountDetail(agentId){
					window.location.href="../transaction/transactionlist.do?thisAction=accountDetaillist&agentId="+agentId+"&selectDealDate=1";
				}
				function toAccountDetail2(agentId){
					window.location.href="../transaction/transactionlist.do?thisAction=accountDetaillist2&agentId="+agentId+"&selectDealDate=1";
				}
				function showCertificate(agentId){
					window.location.href="agent.do?thisAction=showCertificate&agentId="+agentId;
				}
				function updateBalance(agentId){
					window.location.href="agentlist.do?thisAction=updateBalance&agentId="+agentId;
				}
					function freezeMoney(agentId){
					window.location.href="agent.do?thisAction=fundsTreatment&type=1&agentId="+agentId;
					
				}
					function thawyMoney(agentId){
					window.location.href="agent.do?thisAction=fundsTreatment&type=2&agentId="+agentId;
				}
			</script>
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
