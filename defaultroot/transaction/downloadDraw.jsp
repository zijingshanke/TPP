<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<!-- Jsp:transaction/downloadDraw -->
<html>
	<head>
		<title>下载提现文件</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script>
		var ids="";

		//查询
		function search(){
			document.forms[0].thisAction.value="download";
			document.forms[0].submit();
		}
		
		//提现列表
		function executeDraw(){
			var ids=document.forms[0].ids.value;
			
			if(ids!=null&&ids!=""){
				document.forms[0].action="../transaction/drawlist.do?thisAction=exportDrawFile";				
				document.forms[0].submit();
				document.forms[0].action="../transaction/drawlist.do?thisAction=download";
			}else{
				alert("请选择要提现的项目")
			}			
		}	
		
		 function getSelectAll(){
    		var temp="";    
    		for(i=0;i<document.forms[0].selectedItems.length;i++) {
      			 document.forms[0].selectedItems[i].checked=false;   
            }  
  		 }
		
		function removeSelected() {
    		var temp="";
   			for(i=0;i<document.forms[0].selectedItems.length;i++){
      		 document.forms[0].selectedItems[i].checked=false;      
    		}  
  		}
  
  		function clickSelectedItems(obj,code){
  		var tempIds=document.forms[0].ids.value;
    		if(obj.checked==true){
       			tempIds=tempIds+","+code;
   			}else{   			
			    tempIds=tempIds.replaceAll(code,"");  			     
			    tempIds=tempIds.replaceAll(",,",",");			    
    		} 
    		tempIds=tempIds.replaceAll(",,",",");	
    		document.forms[0].ids.value=tempIds;	
  		}
		
	
		function selectAll(id) {
		var tempIds=ids;
    	if(id==0){
      			tempIds="";
      		for(i=0;i<document.forms[0].selectedItems.length;i++){
        		document.forms[0].selectedItems[i].checked=false;  
     		}
      		document.forms[0].allSelected.value=0;
   		}else if(id==1) {   			
      		for(i=0;i<document.forms[0].selectedItems.length;i++){
        		document.forms[0].selectedItems[i].checked=true;   
        		tempIds=ids;          		    		
      		}
      		document.forms[0].allSelected.value=1;      		
   		 } else{   	
      		var cs=tempIds.split(","); 
      		
     		for(i=0;i<document.forms[0].selectedItems.length;i++) {
        		for(j=0;j<cs.length;j++){        
     			 // alert("title="+document.forms[0].selectedItems[i].title+",cs["+j+"]="+cs[j]);
         		 if(document.forms[0].selectedItems[i].title==cs[j]){
            		document.forms[0].selectedItems[i].checked=true;  
           			break;  
         		 }
       		   }        
      		}
    	}   
    	tempIds=tempIds.replaceAll(",,",",");	
    	document.forms[0].ids.value=tempIds;	
 	}
</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/transaction/drawlist.do?thisAction=download">
					<html:hidden property="thisAction" />
					<html:hidden property="lastAction" />
					<html:hidden property="intPage" />
					<html:hidden property="pageCount" />
					<html:hidden property="ids" />
					<html:hidden property="allSelected" />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
								<c:import url="../_jsp/mainTitle.jsp?title1=交易管理&title2=待提现列表"
									charEncoding="UTF-8" />
								<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />

									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												商户：
												<html:text property="agentName" styleClass="colorblue2 p_5"
													style="width:150px;"></html:text>
											</td>
											<td>
												起始申请时间：
												<html:text property="beginDate" styleClass="colorblue2 p_5"
													style="width:150px;"
													onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
													readonly="true" />
											</td>
											<td>
												终止申请时间：
												<html:text property="endDate" styleClass="colorblue2 p_5"
													style="width:150px;"
													onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
													readonly="true" />
											</td>
											<td>
												<html:select property="bank">
													<html:option value="99">全部</html:option>
													<html:option value="ICBC">工商银行</html:option>
													<html:option value="CCB">建设银行</html:option>
													<html:option value="CMBC">民生银行</html:option>
													<html:option value="ABC">农业银行</html:option>
													<html:option value="BCM">交通银行</html:option>
													<html:option value="CITIC">中信银行</html:option>
												</html:select>
											</td>
											<td>
												<html:select property="type">
													<html:option value="99">全部</html:option>
													<html:option value="0">普通提现</html:option>
													<html:option value="1">实名认证</html:option>
												</html:select>
											</td>
											<td>
												<html:select property="perPageNum">
													<html:option value="99">显示全部</html:option>
													<html:option value="5">5条</html:option>
													<html:option value="10">10条</html:option>
													<html:option value="20">20条</html:option>
												</html:select>
											</td>
											<td>
												<input type="button" name="button" id="button" value="查询"
													class="submit greenBtn" onclick="search();" />
											</td>
										</tr>
									</table>
								</div>


								<table id="tableObj" width="100%" cellpadding="0"
									cellspacing="0" border="0" class="dataList">
									<tr>
										<th>
											<div>
												序号
											</div>
										</th>
										<th>
											<div>
												选择
											</div>
										</th>
										<th>
											<div>
												交易号
											</div>
										</th>
										<th>
											<div>
												商户名
											</div>
										</th>
										<th>
											<div>
												申请时间
											</div>
										</th>
										<th>
											<div>
												提现银行
											</div>
										</th>
										<th>
											<div>
												提取时间
											</div>
										</th>
										<th>
											<div>
												批准人
											</div>
										</th>
										<th>
											<div>
												批准时间
											</div>
										</th>
										<th>
											<div>
												核准人
											</div>
										</th>
										<th>
											<div>
												核准时间
											</div>
										</th>
										<th>
											<div>
												类型
											</div>
										</th>
										<th>
											<div>
												金额(元)
											</div>
										</th>

									</tr>
									<c:forEach var="info" items="${dlf.list}" varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(dlf.intPage-1)*dlf.perPageNum}" />
											</td>
											<td>
												<html:multibox property="selectedItems" style="height:5px;"
													value="${info.id}" title="${info.id}"
													onclick="clickSelectedItems(this,'${info.id}')">
												</html:multibox>
											</td>
											<td>
												<html:link
													page="/transaction/drawLoglist.do?thisAction=listDrawLogByContent&orderNo=${info.orderNo}">
													<c:out value="${info.orderNo}" />
												</html:link>
											</td>
											<td>
												<html:link
													page="/agent/agentlist.do?thisAction=view&agentId=${info.agent.id}">
													<c:out value="${info.agent.name}" />
												</html:link>
											</td>
											<td>
												<c:out value="${info.requestDate}" />
											</td>
											<td>
												<c:out value="${info.bankTo}" />
											</td>
											<td>
												<c:out value="${info.drawDate}" />
											</td>
											<td>
												<c:out value="${info.sysUser.userName}" />
											</td>
											<td>
												<c:out value="${info.checkDate}" />
											</td>
											<td>
												<c:out value="${info.sysUser1.userName}" />
											</td>
											<td>
												<c:out value="${info.check1Date}" />
											</td>
											<td>
												<c:out value="${info.typeTo}" />
											</td>
											<td style="text-align: right;">
												<fmt:formatNumber value="${info.amount}" pattern="0.00" />
												&nbsp;
											</td>

										</tr>
									</c:forEach>
									<tr>
										<td colspan="12">
											<div align="center">
												<font>合计</font>
										<td style="text-align: right;">
											<c:out value="${dlf.totalValue1}" />
											&nbsp;
										</td>
									</tr>
								</table>
								<c:if test="${dlf.totalValue1!='0.00'}">
									<c:if test="${dlf.bank=='ICBC'}">
										<input name="label" type="button" class="button4"
											value="下载工商银行批量支付文件" onclick="executeDraw()" />
									</c:if>
									<c:if test="${dlf.bank=='CCB'}">
										<input name="label" type="button" class="button4"
											value="下载建设银行批量支付文件" onclick="executeDraw()" />
									</c:if>
									<c:if test="${dlf.bank=='ABC'}">
										<input name="label" type="button" class="button4"
											value="下载农业银行批量支付文件" onclick="executeDraw()" />
									</c:if>
									<c:if test="${dlf.bank=='BCM'}">
										<input name="label" type="button" class="button4"
											value="下载交通银行批量支付文件" onclick="executeDraw()" />
									</c:if>
									<c:if test="${dlf.bank=='CMBC'}">
										<input name="label" type="button" class="button4"
											value="下载民生银行批量支付文件" onclick="executeDraw()" />
									</c:if>
											<c:if test="${dlf.bank=='CITIC'}">
										<input name="label" type="button" class="button4"
											value="下载中信银行批量支付文件" onclick="executeDraw()" />
									</c:if>
								</c:if>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="left">
											<div>
												<input type="button" class="button1" onclick="selectAll(1)"
													value="全  选">
												<input type="button" class="button1" onclick="selectAll(0)"
													value="全不选">
											</div>
										</td>
										<td align="right">
											<div>
												共有记录&nbsp;
												<c:out value="${dlf.totalRowCount}"></c:out>
												&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
												<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
												|
												<a href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>]
												页数:
												<c:out value="${dlf.intPage}" />
												/
												<c:out value="${dlf.pageCount}" />
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
	<script type="text/javascript">	
	ids=document.forms[0].ids.value;	
	
	function process(){
  		if(!document.forms[0].selectedItems)
    		return;
  		if(document.forms[0].allSelected.value==1)
    		selectAll(3);
  		else
    		selectAll(0);    	
	}
	process();	
	</script>
</html>













