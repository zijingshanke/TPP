<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
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
    document.forms[0].submit();
}

function updatePassword(){
	var password=document.forms[0].userPassword.value.Trim();
	var password1=document.forms[0].userPassword1.value.Trim();
	var password2=document.forms[0].userPassword2.value.Trim();
	if(password.length==0){
		alert("原密码不能为空");
	}else if(password1.length<6){
		alert("新密码长度要大于5！");
	}
	else if(password1==password2){
		document.forms[0].submit();
	}else{
		alert("2次输入密码不一致!修改失败!");
		document.forms[0].userPassword2.value="";
		document.forms[0].userPassword2.focus();
	}
}



</script>
</head>
<body>
<div id="mainContainer"><!-- 滑动 
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
			  -->
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
<c:import url="../_jsp/mainTitle.jsp?title1=用户管理&title2=修改用户密码" charEncoding="UTF-8" />
<hr>
<html:form action="/user/user.do?thisAction=updateMyPassword">
	<html:hidden property="userId" name="user" />
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">名字</td>
					<td style="text-align: left" ><c:out  value="${user.userName}"/></td>
				</tr>
				<tr>
					<td class="lef">登录帐号</td>
					<td style="text-align: left" ><c:out  value="${user.userNo}"/></td>
				</tr>
				<tr>
					<td class="lef">请输入原密码</td>
					<td style="text-align: left" ><html:password property="userPassword" value="" styleClass="colorblue2 p_5"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="lef">请输入密码</td>
					<td style="text-align: left" ><html:password property="userPassword1" value="" styleClass="colorblue2 p_5"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td class="lef">请再此输入密码</td>
					<td style="text-align: left" ><html:password property="userPassword2"  value="" styleClass="colorblue2 p_5"/><font color="red">*</font></td>
				</tr>
				<tr><td colspan="2">				<input name="label"
						type="button" class="button1" value="提交" onclick="updatePassword();">
						
						<input name="label"
						type="button" class="button1" value="取消" onclick="javascript :history.back(-1)"></td></tr>
			</td>
		</tr>
	</table>
</html:form>
</div>
</div>
</body>
</html>
