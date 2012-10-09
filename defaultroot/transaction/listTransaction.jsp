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
<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
function smb(){
	
	var begin=document.getElementById("beginDate").value;
	var end=document.getElementById("endDate").value;
	if(begin!="" && end!="" && begin > end){
		alert("终止时间不能比起始时间小！");
		return false;
	}else{
	document.forms[0].thisAction.value="list";
	document.forms[0].submit();
	}

}
function download(){
	document.forms[0].thisAction.value="notPagingList";
	document.forms[0].submit();
	document.forms[0].thisAction.value="list";
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
			<c:if test="${tlf.mode==0}">
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=全部交易列表" charEncoding="UTF-8" /> 
			</c:if>
			<c:if test="${tlf.mode==1}">
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=进行中交易列表" charEncoding="UTF-8" /> 
			</c:if>
			<c:if test="${tlf.mode==3}">
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=成功交易列表" charEncoding="UTF-8" /> 
			</c:if>
			<c:if test="${tlf.mode==4}">
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=失败交易列表" charEncoding="UTF-8" /> 
			</c:if>
			<c:if test="${tlf.mode==11}">
<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=退款交易列表" charEncoding="UTF-8" /> 
			</c:if>
			<div class="searchBar">
			<p>搜索栏</p><c:out value="${statistBalance}"></c:out>
			<hr />
	<html:form action="/transaction/transactionlist.do" >
	<html:hidden property="thisAction" />
	<html:hidden property="lastAction" />
	<html:hidden property="intPage" />
	<html:hidden property="pageCount" />
	<html:hidden property="mode" />
			<table cellpadding="0" width="100%" cellspacing="0" border="0" class="searchPanel">
				<tr>
					<td>交易号：<html:text property="no" style="width:150px;" styleClass="colorblue2 p_5"  /> 
					订单号：<html:text property="orderDetailsNo" style="width:150px;" styleClass="colorblue2 p_5"  />
					外部订单号： <html:text property="orderNo" style="width:150px;" styleClass="colorblue2 p_5"  />
					交易名称：<html:text property="shopName" style="width:150px;" styleClass="colorblue2 p_5"  />  <br/>
					交易人：<html:text property="agentName" styleClass="colorblue2 p_5"  style="width:150px;"/>  
					时间段：<html:text styleId="beginDate" style="width:150px;" property="beginDate" styleClass="colorblue2 p_5" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" readonly="true" />
					&nbsp;&nbsp;&nbsp; —&nbsp;&nbsp;&nbsp;
					<html:text styleId="endDate" style="width:150px;" property="endDate" styleClass="colorblue2 p_5" onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" readonly="true" />
					&nbsp;&nbsp;
					交易类型：
						<html:select property="orderDetailsType" styleId="orderDetailsType">
						                    <html:option value="0">全部</html:option>
											<html:option value="1">可用→可用</html:option>
											<html:option value="2">信用→信用</html:option>
											<html:option value="3">可用→信用</html:option>
											<html:option value="4">信用→可用</html:option>
											<html:option value="5">可用→冻结</html:option>
											<html:option value="6">冻结→可用</html:option>
									</html:select>
					</td>
					<td><input type="button" name="button" id="button" value="查询"
						class="submit greenBtn" onclick="smb();"/></td>
				</tr>
			</table>
			
				</html:form>
			</div>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<th><div>&nbsp;</div></th>
					<th><div>创建时间</div></th>
					<th><div>交易号</div></th>
					<th><div>订单号</div></th>
					<th><div>外部订单号</div></th>
					<th><div>交易名称</div></th>
					<th><div>出帐人</div></th>
					<th><div>入帐人</div></th>
					<th><div>交易类型</div></th>
					<th><div>交易状态</div></th>
					<th><div>交易金额(元)</div></th>
				</tr>
				<c:forEach var="info" items="${tlf.list}" varStatus="status">
					<tr>
						<td><c:out value="${status.count+(tlf.intPage-1)*tlf.perPageNum}" /></td>
						
						<td>
						<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.accountDate}"/>
						</td>
					
						<td><c:out value="${info.no}" /></td>
						<td><c:out value="${info.orderDetails.orderDetailsNo}" /></td>
						<td><c:out value="${info.orderDetails.orderNo}" /></td>
						<td><html:link page="/transaction/transactionlist.do?thisAction=view&Id=${info.id}&order_id=${info.orderDetails.id}"><c:out value="${info.orderDetails.shopName}" /></html:link></td>
						
						<td>
						<html:link page="/agent/agentlist.do?thisAction=view&agentId=${info.fromAgent.id}">
						<c:out value="${info.fromAgent.name}" />
						</html:link>
						</td>
						<td>
						<html:link page="/agent/agentlist.do?thisAction=view&agentId=${info.toAgent.id}">
						<c:out value="${info.toAgent.name}" />
						</html:link></td>
						<td><c:out value="${info.typeCaption}" /></td>
						<td>	
							<c:choose>
			                	<c:when test="${info.status==10}">
			                		 买家已付款，等待卖家发货
			                	</c:when>
			                	<c:when test="${info.status==7}">
			                		 卖家已发货，等待买家确认
			                	</c:when>
				                <c:otherwise>
				                	<c:out value="${info.transactionStatus}" />
				                </c:otherwise>
				            </c:choose>
	            		</td>
						<td style="text-align: right;">
							<fmt:formatNumber value="${info.amount}" pattern="0.00"/>&nbsp;
						</td>
					</tr>
				</c:forEach>
				<tr>
				<td colspan="10"><div align="center"><font>合计</font> </div> </td>
				<td style="text-align: right;"><c:out value="${tlf.totalValue1}"/>&nbsp;</td>
				</tr>
			</table>

			<table width="100%" style="margin-top: 5px;">
				<tr>
					
					<td><input type="button" class="button2" value="下载交易报表" onclick="download();"/> </td>
					<td align="right">
					<div>共有记录&nbsp;<c:out value="${tlf.totalRowCount}"></c:out>&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;
					[<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a> | <a
						href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> | <a
						href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> | <a
						href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数:<c:out
						value="${tlf.intPage}" />/<c:out value="${tlf.pageCount}" />]</div>
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
							<td width="10" class="tbrb"></td>
						</tr>
					</table>
			</div>
		</div>
	</body>
</html>
