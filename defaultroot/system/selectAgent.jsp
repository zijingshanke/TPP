<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>发送邮件--选择商户</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script type="text/javascript"> 			
		function sendMail(){
			var values="";			
			var chklength=document.forms[0].selectedItems.length;
		
			for(var i=0;i<chklength;i++){
				var chk=document.forms[0].selectedItems[i];
				if(chk.checked){
					values += chk.value+",";
				}				
			}
			opener.addMail(values);
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
				<html:form action="/agent/agentlist.do">
					<html:hidden property="thisAction" name="alf"/>
					<html:hidden property="lastAction" name="alf"/>
					<html:hidden property="intPage" name="alf"/>
					<html:hidden property="pageCount" name="alf"/>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=发送邮件&title2=选择发送商户" charEncoding="UTF-8" />
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
												<html:text property="agentName" styleClass="colorblue2 p_5" />
											</td>
											<td>
												账号：
												<html:text property="agentEmail" styleClass="colorblue2 p_5" />
											</td>
											<td>
												类型：
												<html:select property="agentType">
													<html:option value="0">--请选择--</html:option>
													<html:option value="1">1</html:option>
													<html:option value="2">2</html:option>
												</html:select>
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
											<div
												style="height: 100%; width: 100%; vertical-align: center; padding-top: 7px;">
												<input type="checkbox"
													onclick="checkAll(this, 'selectedItems')" name="sele" />
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
									<c:forEach var="info" items="${alf.list}" varStatus="status">
										<tr>
											<td>
												<c:out
													value="${status.count+(alf.intPage-1)*alf.perPageNum}" />
												<html:hidden property="agentId" />
											</td>
											<td>
												<html:multibox property="selectedItems" style="height:5px;"
													value="${info.email}" onclick="checkItem(this, 'sele')">
												</html:multibox>
											</td>
											<td>
												<c:out value="${info.loginName}" />
											</td>
											<td>
												<c:out value="${info.name}" />
											</td>
											<td>
												<c:out value="${info.email}" />
											</td>
										</tr>
									</c:forEach>
								</table>

								<div class="splitLine"></div>
								<div class="">
									<table width="100%" style="margin-top: 5px;">
										<tr>
											<td>
												<input name="label" type="button" class="button1" value="确定"
													onclick="sendMail();">
											</td>
											<td align="right">
												<div>
													共有记录&nbsp;
													<c:out value="${alf.totalRowCount}"></c:out>
													&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [
													<a href="JavaScript:turnToPage(document.forms[0],0)">首页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],1)">上一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],2)">下一页</a>
													|
													<a href="JavaScript:turnToPage(document.forms[0],3)">
														末页</a>] 页数:
													<c:out value="${alf.intPage}" />
													/
													<c:out value="${alf.pageCount}" />
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
</html>
