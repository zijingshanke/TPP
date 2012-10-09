<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


	<head>
	<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
	 function download(){
  		 document.forms[0].action="../transaction/transactionlist.do?thisAction=getAllTransationListByAgent";
		 document.forms[0].submit();
		 document.forms[0].action="../transaction/transactionlist.do?thisAction=accountDetaillist";
  }
  function custom(){
  		var cda=document.getElementById("selectDealDate");
  		cda.value="0";
  }
  function gaibian(){
  		var cda=document.getElementById("selectDealDate");
  		var today   =   new   Date()
  		var YY = today.getYear();//获取年份
	    var MM = today.getMonth()+1;//获取月份
	    var DD = today.getDate();//获取日
	    var HH = today.getHours();//获得时
	    var NN = today.getMinutes();//获得分
	    var SS = today.getSeconds();//获得秒
	    YY = (YY<1900?(1900+YY):YY);//处理火狐浏览器年份乱码问题
	    if(MM<10){
	    	MM="0"+MM;
	    }
	    if(DD<10){
	    	DD="0"+DD;
	    }
	    if(HH<10){
	    	HH="0"+HH;
	    }
	    if(NN<10){
	    	NN="0"+NN;
	    }
	    if(SS<10){
	    	SS="0"+SS;
	    }
	      if(cda.value=="1"){
	    	document.getElementById("beginDate").value=YY+"-"+MM+"-"+DD+" 00:00:00";
	    	document.getElementById("endDate").value=YY+"-"+MM+"-"+DD+" 23:59:59";
	    }
	    if(cda.value=="2"){
	    	document.getElementById("beginDate").value=YY+"-"+MM+"-01 00:00:00";
	    	document.getElementById("endDate").value=YY+"-"+MM+"-"+DD+" 23:59:59";
	    }
	     if(cda.value=="3"){
	    	NM=today.getMonth();
	    	if(NM<10){
	    		NM="0"+NM;
	    	}
	    	document.getElementById("beginDate").value=YY+"-"+NM+"-01 00:00:00";
	    	document.getElementById("endDate").value=YY+"-"+MM+"-01 00:00:00";
	    }
	    if(cda.value=="4"){
	    	document.getElementById("beginDate").value="";
	    	document.getElementById("endDate").value="";
	    }
  }
	</script>
	<body>
		<div id="mainContainer">
			<div id="container">
			<html:form action="/transaction/transactionlist.do?thisAction=accountDetaillist" >
							<html:hidden property="thisAction"/>
							<html:hidden property="lastAction" />
							<html:hidden property="intPage" />
							<html:hidden property="pageCount" />
							<html:hidden property="agentId" value="${talf.agent.id}"/>
							<html:hidden property="hql" />
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
	<c:import url="../_jsp/mainTitle.jsp?title1=商户管理&title2=交易详细" charEncoding="UTF-8" />
					<div class="searchBar">
									<p>商户名：<font color="red;"><c:out value="${agent.name}"></c:out></font> &nbsp;&nbsp;&nbsp;&nbsp;商户账号：<font color="red;"><c:out value="${agent.loginName}"></c:out></font></p><hr/>
			<table cellpadding="0" cellspacing="0" border="0" class="searchPanel">
				<tr>
				<td>
				创建时间：
						<html:text styleId="beginDate" property="beginDate" styleClass="colorblue2 p_5"   style="width:150px;" onclick="custom();"
					 onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" ></html:text>
					-
						<html:text styleId="endDate" property="endDate" styleClass="colorblue2 p_5" style="width:150px;" onclick="custom();"
						onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"></html:text>
						查询时间：
					<html:select property="selectDealDate"  styleId="selectDealDate" onclick="gaibian();">
					<html:option value="0">自选</html:option>
					<html:option value="1">当日</html:option>
					<html:option value="2">当月</html:option>
					<html:option value="3">上月</html:option>
					<html:option value="4">全部</html:option>
					</html:select>
					
					<input type="submit" value="查 询" class="submit greenBtn" />
				</td>
				</tr>
			</table>
					</div>
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th><div align="center">创建时间</div></th>
										<th><div align="center">商户订单号</div></th>
										<th><div align="center">业务流水号</div></th>
										<th><div align="center">类型</div></th>
										<th><div align="center">收入（元）</div></th>
										<th><div align="center">支出（元）</div></th>
										<th><div align="center">账户余额（元）</div></th>
										<th width="20%"><div align="center">备注</div></th>
									</tr>
									<c:forEach var="transaction" items="${talf.list}">
										<tr>
											<td width="15%">
											<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${transaction.accountDate}"/>
											</td>
											<td>
												<c:out value="${transaction.orderDetails.orderDetailsNo}"></c:out>
												<br />
											</td>
											<td>
												<c:out value="${transaction.orderDetails.orderNo}"></c:out>
												<br />
											</td>
											<td>
												<c:out value="${transaction.typeCatption}"></c:out>
												<br />
											</td>

											<td style="text-align: right;">
												<c:if test="${transaction.toAgent.id==talf.agent.id}">
													<fmt:formatNumber value="${transaction.amount}" pattern="0.00"/>
												</c:if>
												<br />
											</td>
											<td style="text-align: right;">
												<c:if test="${transaction.fromAgent.id==talf.agent.id}">
													<c:if test="${transaction.amount>=0}">
													-<fmt:formatNumber value="${transaction.amount}" pattern="0.00"/>
													</c:if>
												</c:if>
												<br />
											</td>
											<td style="text-align: right;">
												<fmt:formatNumber value="${transaction.currentBalance}" pattern="0.00"/>
												<br />
											</td>
											<td>
												<c:out value="${transaction.mark}"></c:out>
											</td>

										</tr>
									</c:forEach>
								</table>
										<table width="100%">
										<tr>
											<td>
											<input name="label" type="button" class="button2" value="返 回" onclick="window.history.back();" />
											<input name="label" type="button" class="button2" value="下载账户明细" onclick="download();" />
											</td>
											<td align="right">
												<div style="text-align: right;">
													共有记录&nbsp;
													<c:out value="${talf.totalRowCount}"></c:out>
													&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
													<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
													页数:
													<c:out value="${talf.intPage}" />
													/
													<c:out value="${talf.pageCount}" />
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
							<td width="10" class="tbrb"></td>
						</tr>
					</table>
					</html:form>
			</div>
		</div>
		
	</body>
</html>
