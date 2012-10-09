<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>查看邮件模板内容</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>

		<script type="text/javascript">
		function listAgent(){			
			var id=document.forms[0].id.value;
			var mails=document.forms[0].mails.value;
			openWindow(800,600,'../system/patternEmail.do?thisAction=listAgent&mails='+mails+'&id='+id);	
		}		
		
		function  isExistEmail(destArray,email) {
         	var flag = false;
         	for (var i = 0; i < destArray.length; i++) {
            	 if (email==destArray[i]) {
                	 flag = true;
             	}
         	}
         	return flag;
     	}
		
		function addMail(confirmMail){
			var initMail=document.forms[0].mails.value;

			var initMailArray = initMail.substring(0, initMail.length - 1).split(",");
			var confirmMailArray=confirmMail.substring(0, confirmMail.length - 1).split(",");
			//alert("1---initMailArray--"+initMailArray);	
			//alert("1---confirmMailArray--"+confirmMailArray);			
			
			for(var j=0;j<confirmMailArray.length;j++){
					if(isExistEmail(initMailArray,confirmMailArray[j])){
						return;
					}
				initMail+=confirmMailArray[j]+",";	
			}		
			//alert(initMail)
			document.forms[0].mails.value=initMail;
			setMailsCount();//--
		}
		
		function setMailsCount(){
			var initMail=document.forms[0].mails.value;
			var mailsCount=initMail.substring(0, initMail.length - 1).split(",").length;
			document.getElementById("mailsCount").innerText=mailsCount;
		}
		
		function setMail(confirmMail){
			document.forms[0].mails.value=confirmMail;
			setMailsCount();//-----
		}	
				
		function sendEmail(){
			var mails=document.forms[0].mails;	

			if(mails.value==""){
				alert("请选择要发送邮件的商户!")
			}else{
			    document.forms[0].thisAction.value="sendMail";				 
		    	document.forms[0].submit();
			}					    	
		}
			
		function selectAgent(){
			//document.forms[0].mailsCount.value="12"		
			openWindow(800,600,'../system/patternEmaillist.do?thisAction=selectAgent');		
			//openWindow(800,600,'/system/patternEmaillist.do?thisAction=selectAgent');		//错误		
		}
		</script>
	</head>
	<body onload="setMailsCount();">
		<div id="mainContainer" style="height:1500px;">
			<div id="container">
				<html:form action="/system/patternEmail.do">
					<html:hidden property="thisAction"></html:hidden>
					<html:hidden property="id" value="${patternEmail.id}"></html:hidden>
					<html:hidden property="content" value="${patternEmail.content}"></html:hidden>
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
<c:import url="../_jsp/mainTitle.jsp?title1=邮件管理&title2=查看邮件模板" charEncoding="UTF-8" />
									
								</div><hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											ID
										</td>
										<td style="text-align: left">
											<c:out value="${patternEmail.id}" />
										</td>
									</tr>
									<tr>
										<td  class="lef">
											名称
										</td>
										<td style="text-align: left">
											<c:out value="${patternEmail.name}" />
										</td>
									</tr>
									<tr>
										<td  class="lef">
											状态
										</td>
										<td style="text-align: left">
											<c:out value="${patternEmail.status}" />
										</td>
									</tr>
									<tr>
										<td  class="lef">
											Code
										</td>
						            <td style="text-align: left">
											<c:out value="${patternEmail.code}" />
										</td>
									</tr>
									<tr>
										<td  class="lef">
											描述
										</td>
										<td style="text-align: left">
											<c:out value="${patternEmail.description}" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											内容
										</td>
										<td style="text-align: left">
											<c:out value="${patternEmail.content}" escapeXml="false" />
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td>
											收件商户： 共
											<span id="mailsCount">0</span>人
											<a href="#" onclick="listAgent();" id="viewAgents">点击查看详细</a>
											<html:textarea style="display: block" property="mails"
												cols="60" rows="3"></html:textarea>
										</td>
										<td>
											<input name="label" type="button" class="button1"
												value="选择商户" onclick="selectAgent();" />
										</td>
									</tr>
									<tr>
										<td>
											<input name="label" type="button" class="button1" value="返    回"
												onclick="window.history.back();">

											<input name="label" type="button" class="button1"
												value="发送邮件" onclick="sendEmail();" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
	</body>
</html>
