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
	src="../_js/jquery-1.3.2.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>

<script>
String.prototype.Trim   =   function()   
  {   
  return   this.replace(/(^\s*)|(\s*$)/g,   "");   
  }
function add()
{
  
    var province =document.forms[0].provinceId.value;
    var city=document.forms[0].cityId.value;
    if(province==0){
    	alert("请选择省份");
    }else if(city==0){
    	alert("请选择城市");
     
    }else{
    	document.forms[0].thisAction.value="add";
    	document.forms[0].submit();
    }
}
function searchUser()
{
   document.forms[0].thisAction.value="viewBank";
   document.forms[0].submit();
}


</script>
</head>
<body>
<div id="mainContainer"><!-- 滑动 
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
			  -->
<div id="container"><html:form action="/system/banklist.do" >
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
<c:import url="../_jsp/mainTitle.jsp?title1=银行管理&title2=添加银行" charEncoding="UTF-8" />
				<hr>					
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList" >
				<tr>
					<td class="lef">省</td>
					<td style="text-align: left"><html:select property="provinceId" onchange="searchUser();">
												<html:option value="0">请选择</html:option>													
												<html:options collection="provinces" property="id" labelProperty="cname"/>
												</html:select>
												<a href="../system/provincelist.do?thisAction=list">新增</a>	<font color="red">*</font></td>
				</tr>
				<tr>
					<td class="lef">市</td>
					<td style="text-align: left"><html:select property="cityId" onchange="searchUser();">
												<html:option value="0">请选择</html:option>												
												<html:options collection="citys" property="id" labelProperty="cname"/>
												</html:select>	
													<a href="../system/citylist.do?thisAction=list">新增</a>	<font color="red">*</font></td>
				</tr>
				<tr>
					<td class="lef">银行(中文)</td>
					<td style="text-align: left"><html:text property="bank.cname"  styleClass="colorblue2 p_5"/></td>
				</tr>
				<tr>
					<td class="lef">银行(英文)</td>
					<td style="text-align: left"><html:text property="bank.ename"  styleClass="colorblue2 p_5"/><font color="red">*</font></td>
				</tr>
				
			<tr>
					<td class="lef">缩写</td>
					<td style="text-align: left"><html:text property="bank.sname"  styleClass="colorblue2 p_5"/><font color="red">*</font></td>
				</tr>
			
			</table>
			<table width="100%" style="margin-top: 5px;">
				<check code="sa05,sa02"><tr>
					<td><input name="label" type="button" class="button1"
						value="提 交" onclick="add();">
						
		 <input name="label" type="button" class="button1" value="取消" onclick="javascript:history.back();">
					</td>
				</tr></check> 
			</table>
			</td>
		</tr>
	</table>

</html:form></div>
</div>
</body>
</html>
