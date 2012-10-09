<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>发送邮件--商户详细清单</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script type="text/javascript"> 	
		var tempMails="";		
 		function deleteMail(){
 			document.forms[0].thisAction.value="delete";
 			document.forms[0].submit();
 		}
 		
 		function confirmMail(){ 	
			var emails=document.forms[0].currentMails.value;
			opener.setMail(emails);
    		window.close();
 		}
 				
		function searchAgent(){
	   		document.forms[0].thisAction.value="list";
	   		document.forms[0].submit();
		}
		
	    function checkAll(e, itemName){
		   var aa = document.getElementsByName(itemName);
		   for (var i=0; i<aa.length; i++)
		    	aa[i].checked = e.checked;
		}
		
		function checkItem(e, allName){
		   var all = document.getElementsByName(allName)[0];
		   if(!e.checked)
		   		 all.checked = false;
		   else{
		     var aa = document.getElementsByName(e.name);
		     for (var i=0; i<aa.length; i++)
		      if(!aa[i].checked) 
		      		return;
		     all.checked = true;
		   }
		}
        </script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/mailagentlist.do">
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
<c:import url="../_jsp/mainTitle.jsp?title1=发送邮件&title2=商户详细清单" charEncoding="UTF-8" />
								<div class="searchBar">
									<p>
										搜索栏
									</p>
									<hr />
									<table cellpadding="0" cellspacing="0" border="0"
										class="searchPanel">
										<tr>
											<td>
												真实姓名：
												<html:text property="agentName" styleClass="colorblue2 p_5" style="width:150px;"></html:text>
											</td>
											<td>
												账号：
												<html:text property="agentEmail" styleClass="colorblue2 p_5" style="width:150px;"></html:text>
											</td>
											<td>
												类型：
											</td>
											<td>
												<input type="submit" name="button" id="button" value="查询"
													class="submit greenBtn" />
											</td>
										</tr>
									</table>
								</div>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<th>
											<div>
												序号
											</div>
										</th>
										<th>
											<div>
												<html:multibox property="selectedItems" value="${malf.id}"
													onclick="checkAll(this, 'selectedItems')"></html:multibox>
												全选
											</div>
										</th>
										<th>
											<div>
												登陆名
											</div>
										</th>
										<th>
											<div>
												名字
											</div>
										</th>
										<th>
											<div>
												帐号
											</div>
										</th>
									</tr>
									<c:forEach var="info" items="${malf.list}" varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(malf.intPage-1)*malf.perPageNum}" />
											</td>
											<td>
												<html:multibox property="selectedItems" style="height:5px;"
													value="${info.id}" onclick="checkItem(this, 'sele')">
												</html:multibox>
											</td>
											<td>
												<c:out value="${info.agent.loginName}" />
											</td>
											<td>
												<c:out value="${info.agent.name}" />
											</td>
											<td>
												<c:out value="${info.agent.email}" />
											</td>
										</tr>
									</c:forEach>
								</table>

								<div class="splitLine"></div>
								<div class="">
									<table width="100%" style="margin-top: 5px;">
										<tr>
											<td>
												<input name="label" type="button" class="button1" value="全选"
													onclick="selectAll();" />
												<input name="label" type="button" class="button1"
													value="全不选" onclick="selectNon();" />
												<input name="label" type="button" class="button1" value="删除"
													onclick="deleteMail();" />
												<html:hidden property="currentMails"
													value="${malf.currentMails}" />
												<input name="label" type="button" class="button1" value="确定"
													onclick="confirmMail();" />
											</td>
											<td align="right">
												<div>
													共有记录&nbsp;
													<c:out value="${malf.totalRowCount}"></c:out>
													&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
													<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],3)">
														末页</a>] 页数:
													<c:out value="${malf.intPage}" />
													/
													<c:out value="${malf.pageCount}" />
													]
												</div>
											</td>
										</tr>
									</table>
									<div class="clear"></div>
								</div>
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
 var tempMails="";	
 tempMails=document.forms[0].currentMails.value;
 
 function selectAll()
 {
   tempMails=document.forms[0].currentMails.value;
   
 }
 
 function removeAll()
 {
   tempMails="";
 }
</script>
</html>
