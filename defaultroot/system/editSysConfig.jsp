<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script>
String.prototype.Trim   =   function()   
  {   
  return   this.replace(/(^\s*)|(\s*$)/g,   "");   
  }
function addUser()
{
	var name = document.getElementById("name").value.Trim();
	var code = document.getElementById("code").value.Trim();
  	var active = document.getElementById("active").value.Trim();
    var value = document.getElementById("value").value.Trim(); 
	var description = document.getElementById("description").value.Trim();
	if(name==""){
		alert("请输入名称");
		return;
	}
		if(code==""){
		alert("请输入编码");
		return;
	}
		if(active==""){
		alert("请输入激活");
		return;
	}
		if(value==""){
		alert("请输入值");
		return;
	}
		if(description==""){
		alert("请输入描述");
		return;
	}
	document.forms[0].submit();
	
}

function editPassword()
{

	document.forms[0].action="user.do?thisAction=editPassword";
    document.forms[0].submit();
}

function clearUser(){
	document.forms[0].userName.value="";
	document.forms[0].userNo.value="";
	document.forms[0].userType.value="";	
}

</script>
</head>
<body>
<div id="mainContainer"><!-- 滑动 
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
			  -->
<div id="container"><html:form action="/system/sysConfigList.do">
	<html:hidden property="thisAction" value="${thisaction}"/>
	<html:hidden property="sysConfigId" value="${sysconfigId}"></html:hidden>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="10" height="10" class="tblt"></td>
			<td height="10" class="tbtt"></td>
			<td width="10" height="10" class="tbrt"></td>
		</tr>
		<tr>
			<td width="10" class="tbll"></td>
			<td valign="top" class="body">
			
			<c:if test="${thisaction eq 'update'}">
<c:import url="../_jsp/mainTitle.jsp?title1=系统参数&title2=编辑参数信息" charEncoding="UTF-8" />
			</c:if>
			<c:if test="${thisaction eq 'insert'}">
<c:import url="../_jsp/mainTitle.jsp?title1=系统参数&title2=新增参数" charEncoding="UTF-8" />
			</c:if>
					<hr>				
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">名称</td>
					<td style="text-align: left" ><html:text property="sysConfig.name" value="${sysconfig.name}"  styleId="name" styleClass="colorblue2 p_5"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="lef">编码</td>
					<td style="text-align: left"><html:text property="sysConfig.code" value="${sysconfig.code}" styleId="code" styleClass="colorblue2 p_5"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="lef">激活</td>
					<td style="text-align: left">
					<html:text property="sysConfig.active1" value="${sysconfig.active1}"  styleId="active" styleClass="colorblue2 p_5"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="lef">值</td>
					<td style="text-align: left"><html:text property="sysConfig.value" value="${sysconfig.value}" styleId="value" styleClass="colorblue2 p_5" onkeyup="value=value.replace(/[^\d]/g,'')"/><font color="red">*</font></td>
				</tr>
								<tr>
					<td class="lef">描述</td>
					<td style="text-align: left"><html:text property="sysConfig.description" value="${sysconfig.description}" styleId="description" styleClass="colorblue2 p_5" /><font color="red">*</font></td>
				</tr>
			</table>
			<table width="100%" style="margin-top: 5px;">
				<c:check code="sg02,sg03">
				<tr>
					<td>
					<input name="label" type="button" class="button1"
						value="提 交" onclick="addUser();">
						
						 <input name="label"
						type="button" class="button1" value="重 置" onclick="clearUser();">
		 <input name="label" type="button" class="button1" value="取消" onclick="javascript:history.back();">
					</td>
				</tr>
				</c:check>
			</table>
			</td>
		</tr>
	</table>

</html:form></div>
</div>
</body>
</html>
