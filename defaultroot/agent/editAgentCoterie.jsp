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

function add()
{ 

	var email=document.forms[0].agentEmail.value;
	//var ca=document.forms[0].creditAmount.value;
	var fd=document.forms[0].fromDate.value;
	var ed=document.forms[0].expireDate.value;
	var ld=document.forms[0].leaveDays.value; 
	var ma=document.forms[0].minAmount.value; 
   var check=false;
 	
	var regm = /^([a-zA-Z0-9]+[-|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if( !email.match(regm) || email.value=="" )
		{
			alert("Email格式不对！并且不能为空！");
			 check=false;
	         return false;
		}else{
		  check=true;
		}
//	if(ca==null||ca==""){
//	   alert("授信金额不能为空！");
//	    check=false;
//	  return false;
//	   }else{
//		  check=true;
	//	}	
				 	
  if(fd==null||fd==""){
	   alert("请选择开始时间！");
	    check=false;
	  return false;
	   }else{
		  check=true;
		}	
	if(ed==null||ed==""){
	 alert("请选择结束时间！");
	  check=false;
	  return false;
	}else{
		  check=true;
	}
	
	 if(Date.parse(fd.replace(/-/g, "/")) > Date.parse(ed.replace(/-/g, "/")))
	 {
	     alert("开始时间不能比结束时间大！");
	     check=false;
	      return false;
	 } else{
	      check=true;
	 }
	
	
	
  if(ld==null||ld==""){
	 alert("还款天数不能为空！");
	  check=false;
	  return false;
	}else{
		  check=true;
	}
	
 if(ma==null||ma==""){
	 alert("最低信用金额不能为空！");
	  check=false;
	  return false;
	}else{
		  check=true;
	}
	
	
	
 //if( ma*1 > ca*1){
 
	// alert("最低信用金额不能大于授信金额！");
	//  check=false;
	//  return false;
	//}else{
//		  check=true;
	//}

	
	if(check){
	

	     if(document.forms[0].thisAction.value == "update"){
	     	
	        document.forms[0].action="../agent/agentcoterie.do?thisAction=update";
	  	 	document.forms[0].submit();
	      }else
	      if(document.forms[0].thisAction.value=="insert"){
	        document.forms[0].action="../agent/agentcoterie.do?thisAction=insert";
	  	 	document.forms[0].submit();
	     }
    }
}

function reset()
{
	document.forms[0].reset();
}
</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/agent/agentcoterie.do">
					<html:hidden property="thisAction" name="agentCoterie" />
					<html:hidden property="id" name="agentCoterie" />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
							<c:out value="${agentCoterie.thisAction}"></c:out>
							<c:if test="${agentCoterie.thisAction eq 'update'}">
<c:import url="../_jsp/mainTitle.jsp?title1=商户圈用户管理&title2=修改圈内商户列表" charEncoding="UTF-8" />
			</c:if>
			<c:if test="${agentCoterie.thisAction eq 'insert'}">
<c:import url="../_jsp/mainTitle.jsp?title1=商户圈用户管理&title2=添加圈内商户列表" charEncoding="UTF-8" />
			</c:if>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											商户圈名
										</td>
										<td style="text-align: left">
											<c:out value="${coterie.name}" />
											<input type="hidden" name="coterieId"
												value="<c:out value="${coterie.id}"></c:out>" />
										</td>
									</tr>
									<tr>
									
										<td class="lef">
											商户Email
										</td>
										
										<td style="text-align: left">
										<c:if test="${agentCoterie.thisAction eq 'insert'}">
											<html:text styleId="agentEmail" property="agentEmail"
												 name="agentCoterie" 
												styleClass="colorblue2 p_5" />
											<font>*</font>
											</c:if>
										<c:if test="${agentCoterie.thisAction eq 'update'}">
											<html:text styleId="agentEmail" property="agentEmail"
												 name="agentCoterie" value="${agentCoterie.agent.loginName}" readonly="true"
												styleClass="colorblue2 p_5"/>
											</c:if>
										</td>
										
									</tr>
								<!--  	<tr>
										<td class="lef">
											授信金额
										</td>
										<td style="text-align: left">
											<html:text styleId="creditAmount" property="creditAmount"
												name="agentCoterie"
												styleClass="colorblue2 p_5"
												onkeyup="if(isNaN(value))execCommand('undo')" />
											<font>*</font>
										</td>
									</tr>  -->
									<tr>
										<td class="lef">
											开始时间
										</td>
										<td style="text-align: left">
											<html:text styleId="fromDate" property="fromDate"
												styleClass="colorblue2 p_5" name="agentCoterie"
												onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
												readonly="true" />
											<font>*</font>
										</td>
									</tr>
									<tr>
										<td class="lef">
											结束时间
										</td>
										<td style="text-align: left">
											<html:text styleId="expireDate" property="expireDate"
												styleClass="colorblue2 p_5"
												name="agentCoterie"
												onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"
												readonly="true" />
											<font>*</font>
										</td>
									</tr>
									<tr>
										<td class="lef">
											还款天数
										</td>
										<td style="text-align: left">
											<html:text styleId="leaveDays" property="leaveDays"
												name="agentCoterie"
												styleClass="colorblue2 p_5"
												onkeyup="this.value=this.value.replace(/\D/g,'')" />
											<font>*</font>
										</td>
									</tr>

									<tr>
										<td class="lef">
											最低信用金额
										</td>
										<td style="text-align: left">
											<html:text styleId="minAmount" property="minAmount"
												name="agentCoterie"
												styleClass="colorblue2 p_5"
												onkeyup="if(isNaN(value))execCommand('undo')" />
											<font>*</font>
										</td>
									</tr>

									<tr>
										<td class="lef">
											交易限制
										</td>
										<td style="text-align: left">
											<html:radio property="transactionScope" value="0" name="agentCoterie"> 内买</html:radio>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio property="transactionScope" value="1" name="agentCoterie">外买</html:radio>
											<font>*</font>
										</td>
									</tr>
									<tr>
										<td class="lef">
											还款方式
										</td>
										<td style="text-align: left">
											<html:radio property="repaymentType" value="1" name="agentCoterie">逐笔还</html:radio>
											<html:radio property="repaymentType" value="2" name="agentCoterie"> 多笔还</html:radio>

											<font>*</font>
										</td>
									</tr>
									<tr>
										<td class="lef">
											状态
										</td>
										<td style="text-align: left">
											<html:radio property="status" value="0" name="agentCoterie"> 不可用</html:radio>
											<html:radio property="status" value="1" name="agentCoterie">可用</html:radio>
											<font>*</font>
										</td>
									</tr>
								</table>
								<table width="100%" style="margin-top: 5px;">
									<c:check code="sb09">
										<tr>
											<td>

												<input name="label" type="button" class="button1"
													value="提 交" onclick="add();">

												<input name="label" type="reset" class="button1" value="重 置"
													onclick="reset();">

											</td>
										</tr>
									</c:check>
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