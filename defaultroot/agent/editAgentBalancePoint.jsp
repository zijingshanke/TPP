<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script src="../_js/calendar/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script>

function updateAmount()
{ 

	var fd=document.forms[0].balanceDate.value;
   var check=false;
 	

				 	
  if(fd==null||fd==""){
	   alert("请选择盘点时间！");
	    check=false;
	  return false;
	   }else{
		  check=true;
		}	

	
	if(check){
     	
	      if(confirm("您是否确定要开始盘点吗？")){
	         document.forms[0].thisAction.value="updateAgentBalancePoint";
	         document.getElementById("msg").innerHTML="数据加载中..........";
	  	 	document.forms[0].submit();
	  }
    }
}


</script>
	</head>
	<body>
	
				<html:form action="/agent/agentBalancePointlist.do">
						<html:hidden property="thisAction"/>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
				
                 <c:import url="../_jsp/mainTitle.jsp?title1=商户圈余额盘点&title2更新盘点" charEncoding="UTF-8" />
		
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
													
									<tr >
										<td class="lef" align="center">
											盘点时间
									
											<html:text  property="balanceDate"
												styleClass="colorblue2 p_5" 
												onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
												readonly="true" />
											<font>*</font>
										</td>
									</tr>
								<tr>
											<td align="center">

												<input name="label" type="button" class="button1"
													value="开始盘点" onclick="updateAmount();">
												<font id="msg"></font>
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
	
	</body>

</html>