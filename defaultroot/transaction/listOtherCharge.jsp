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
<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript">
function smb()
{
	var minq=document.forms[0].minAmount.value;
	var maxq=document.forms[0].maxAmount.value;
	var begin=document.getElementById("beginDate").value;
	var end=document.getElementById("endDate").value;
	if(begin!="" && end!="" && begin > end){
		alert("终止时间不能比起始时间小！");
		return false;
	}else if(minq!=""&&!(/^([0-9]\d+|[0-9])(\.\d\d?)*$/.test(minq))){
		     alert("最小金额输入错误");
		     return false;
	}else if(maxq!="" &&!(/^([0-9]\d+|[0-9])(\.\d\d?)*$/.test(maxq))){
			alert("最大金额输入错误");
			return false;
	}else if( minq!="" && maxq!="" && parseFloat(minq*100) > parseFloat(maxq*100)){
			alert("最小金额不能大于最大金额");
			return false;
	} 
	if(minq==""){
	 document.forms[0].minAmount.value=0; 
	 }
	if(maxq==""){
	 document.forms[0].maxAmount.value=1000000;
	 }
			document.forms[0].submit();
}

function ratify(id)
{
var bank=document.forms[0].bank.value;
var status=document.forms[0].status.value;
		window.location.href="../transaction/charge.do?thisAction=editCharge&id="+id+"&bank="+bank+"&status="+status;
}
function revocation(id){
	if(confirm("您确定撤销这些提现记录吗？")){ 
		if(confirm("点击确定后，您所选中的将会被撤销，您确认吗？")){
			window.location.href="../transaction/charge.do?thisAction=revocation&id="+id;
			}
		}
}

function download(){

	document.forms[0].thisAction.value="downloadOtherCharge";
	document.forms[0].submit();
	document.forms[0].thisAction.value="listOtherCharge";
}
</script>
  </head>
	<body>
		<div id="mainContainer">
			<div id="container">
			<html:form action="/transaction/chargelist.do" >
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="10" height="10" class="tblt"></td>
				<td height="10" class="tbtt"></td>
				<td width="10" height="10" class="tbrt"></td>
			</tr>
			<tr>
				<td width="10" class="tbll"></td>
				<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=充值管理&title2=线下充值列表" charEncoding="UTF-8" />
					<div class="searchBar">
						<p>
							搜索栏
						</p>
						<hr />
			<table cellpadding="0" cellspacing="0" border="0" 	class="searchPanel">
				<tr>
					<td>
						商户：<html:text property="agentName" styleClass="colorblue2 p_5" style="width:150px;"/>
						交易号：<html:text property="orderNo" styleClass="colorblue2 p_5" style="width:150px;"/>
						时间：<html:text styleId="beginDate" property="beginDate" styleClass="colorblue2 p_5"  style="width:150px;"
							onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" readonly="true" />
						--
						<html:text styleId="endDate" property="endDate" styleClass="colorblue2 p_5" style="width:150px;"
							onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" readonly="true" />
						金额：<html:text property="minAmount" styleClass="colorblue2 p_5" style="width:80px;"></html:text>
						--
						<html:text property="maxAmount" styleClass="colorblue2 p_5" style="width:80px;"></html:text>
						<br/>
						状态：<html:select property="status">
						<html:option value="99">全部</html:option>
						<html:option value="0">待批准</html:option>
						<html:option value="3">待核准</html:option>
						<html:option value="1">已核准</html:option>
						<html:option value="5">已撤销</html:option>
						</html:select>
						
						银行：
						<html:select property="bank">
							<html:option value="0">全部银行</html:option>
							<html:option value="ICBC">工商银行</html:option> 
							<html:option value="CCB">建设银行</html:option>
							<html:option value="BCM">交通银行</html:option>
							<html:option value="CMBC">民生银行</html:option>
							<html:option value="ABC">农业银行</html:option>
							<html:option value="CITIC">中信银行</html:option>
							<html:option value="OTHER">其他</html:option>
						</html:select>
					</td>
					<td>
						<input type="button" name="button" id="button" value="查询"
							class="submit greenBtn" onclick="smb();" />
					</td>
				</tr>
			</table>
		</div>
		
		<table width="100%" cellpadding="0" cellspacing="0" border="0" 	class="dataList">
			<tr>
				<th><div>&nbsp;</div></th>
				<th><div>商户名</div></th>
				<th><div>交易号</div></th>
				<th><div>充值时间</div></th>
				<th><div>充值银行</div></th>
				<th><div>批准人</div></th>
				<th><div>批准时间</div></th>
				<th><div>核准人</div></th>
				<th><div>核准时间</div></th>
				<th><div>状态</div></th>
				<th><div>充值金额(元)</div></th>
				
				<th><div>操作</div></th>
			</tr>
			<c:forEach var="info" items="${clf.list}" varStatus="status">
				<tr>
					<td>
						<c:out value="${status.count+(clf.intPage-1)*clf.perPageNum}" />
					</td>
					<td>
						<html:link page="/agent/agentlist.do?thisAction=view&agentId=${info.agent.id}">
							<c:out value="${info.agent.name}" />
						</html:link>
					</td>
					<td>
					<html:link page="/transaction/chargeLoglist.do?thisAction=listChargeByContent&orderNo=${info.orderNo}">
						<c:out value="${info.orderNo}" />
						</html:link>
					</td>
					<td>
						<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.chargeDate}"/>
					</td>
					<td>
						<c:out value="${info.bankTo}" />
					</td>
					
					<td>
						<c:out value="${info.sysUser.userName}" />
					</td>
					<td>
						<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.checkDate}"/>
					</td>
					<td>
						<c:out value="${info.sysUser1.userName}" />
					</td>
					<td>
						<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.check1Date}"/>
					</td>
					<td>
						<font color="#004E98"><c:out value="${info.statusByOther}" /></font>
					</td>
					<td style="text-align: right;">
						<fmt:formatNumber value="${info.amount}" pattern="0.00" />&nbsp;
					</td>
					
					<td>
					<c:if test="${info.status==0}">
						<input type="button" value="批 准" class="button1" onclick="ratify('<c:out value="${info.id}" />');"/>
						 / <input type="button" value="撤 销" class="button1" onclick="revocation('<c:out value="${info.id}" />');"/>
					</c:if>
					<c:if test="${info.status==3}">
						<input type="button" value="核 准" class="button1" onclick="ratify('<c:out value="${info.id}" />');"/>
						 / <input type="button" value="撤 销" class="button1" onclick="revocation('<c:out value="${info.id}" />');"/>
					</c:if>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="9">
					<div align="center">
						<font>合计</font>
					</div>
				</td>
				<td style="text-align: right;">
				<c:out value="${clf.totalValue1}"></c:out>&nbsp;
				</td><td></td>
			</tr>
		</table>
		<table width="100%" style="margin-top: 5px;">
			<tr>
				<td><input type="button" value="下载线下充值报表" class="button3" onclick="download();"/></td>
				<td align="right">
					<div>
						共有记录&nbsp;
						<c:out value="${clf.totalRowCount}"></c:out>
						&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
						<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> |
						<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> |
						<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> |
						<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:
						<c:out value="${clf.intPage}" />
						/
						<c:out value="${clf.pageCount}" />
						]
					</div>
				</td>
			</tr>
		</table>
					<div class="clear"></div>
				
				</td>
				<td width="10" class="tbrr"></td>
			</tr>
			<tr>
				<td width="10" class="tblb"></td>
				<td class="tbbb"></td>
				<td width="10" class="tbrb">&nbsp;</td>
			</tr>
		</table>
		</html:form>
			</div>
		</div>							
  </body>
</html>
					