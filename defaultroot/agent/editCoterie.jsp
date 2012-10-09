<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<script>

function add()
{	
		var emailObj=document.getElementById("agentEmail");
		if(emailObj!=null && typeof(emailObj)!="undefined")//判断Email是否输入
		{
			var email=emailObj.value;
		}else
		{
   			document.forms[0].submit();
		}
		var regm = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		var reg =/^(([1-9]\d+|[1-9])|0)(\.\d\d?)*$/;
		var rat=document.forms[0].rate.value;
	if(document.forms[0].name.value=="")
	{
		alert("商户圈名不能为空！");
		return false;
		
	}
	if( !email.match(regm) || email.value=="" )
		{
			alert("中间帐户Email格式不对！并且不能为空！");
			return false;
		} 
	if(!(document.forms[0].tempAgentEmail.value).match(regm) ||document.forms[0].tempAgentEmail.value=="")
		{
			alert("临时帐户Email格式不对！并且不能为空！");
			return false;
		}
	if(document.forms[0].tempAgentEmail.value==email)
	{
			alert("临时帐户不能和中间帐户相同!");
			return false;
	}
	if( !reg.test(rat))
		{
			alert("支付费率只能输入数字！并且不能为空！");
			return false;
		}
	if(document.forms[0].partner.value=="")
	{
		alert("合作伙伴不能为空！");
		return false;
	}
	if(document.forms[0].signKey.value=="")
	{
		alert("签名密钥不能为空！");
		return false;
	}
	if(document.forms[0].signType.value=="")
	{
		alert("签名方式不能为空！");
		return false;
	}
	 
    	document.forms[0].submit();
    
}

</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/agent/coterie.do">
	<html:hidden property="thisAction" name="coterie" />
	<html:hidden property="id" name="coterie" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="10" height="10" class="tblt"></td>
			<td height="10" class="tbtt"></td>
			<td width="10" height="10" class="tbrt"></td>
		</tr>
		<tr>
			<td width="10" class="tbll"></td>
			<td valign="top" class="body"><c:choose>
				<c:when test="${coterie.thisAction=='update'}">
<c:import url="../_jsp/mainTitle.jsp?title1=商户圈管理&title2=修改商户圈信息" charEncoding="UTF-8" />
				</c:when>
				<c:when test="${coterie.thisAction=='insert'}">
<c:import url="../_jsp/mainTitle.jsp?title1=商户圈管理&title2=添加新商户圈" charEncoding="UTF-8" />
				</c:when>
			</c:choose>
			<hr>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<td class="lef">商户圈名</td>
					<td style="text-align: left"><html:text styleId="name"
						property="name" name="coterie" styleClass="colorblue2 p_5" /><font>*</font>
					</td>
				</tr>
				<tr>
					<td class="lef">中间帐号</td>
					<td style="text-align: left">
							<html:text styleId="agentEmail" property="agentEmail"
								name="coterie" styleClass="colorblue2 p_5" />
							<font>*</font>
					</td>
				</tr>
				<tr>
					<td class="lef">临时帐号</td>
					<td style="text-align: left">
						
							<html:text styleId="tempAgentEmail" property="tempAgentEmail"
								name="coterie" styleClass="colorblue2 p_5" />
							<font>*</font>
					</td>
				</tr>
				<tr>
					<td class="lef">支付费率</td>
					<td style="text-align: left"><html:text styleId="rate"
						property="rate" name="coterie" styleClass="colorblue2 p_5" /><font>*</font></td>
				</tr>
				<tr>
					<td class="lef">合作伙伴</td>
					<td style="text-align: left"><html:text styleId="partner"
						property="partner" name="coterie" styleClass="colorblue2 p_5" /><font>*</font></td>
				</tr>
				<tr>
					<td class="lef">签名密钥</td>
					<td style="text-align: left"><html:text styleId="signKey"
						property="signKey" name="coterie" styleClass="colorblue2 p_5" /><font>*</font></td>
				</tr>
				<tr>
					<td class="lef">签名方式</td>
					<td style="text-align: left"><html:text styleId="signType"
						property="signType" name="coterie" styleClass="colorblue2 p_5" /><font>*</font></td>
				</tr>
				<tr>
					<td class="lef">状态</td>
					<td style="text-align: left">
				<html:radio property="status" value="1" name="coterie">可用</html:radio>	
	           <html:radio property="status" value="0" name="coterie"> 不可用</html:radio>
					</td>
				</tr>
			  <c:if test="${coterie.thisAction eq 'insert'}">
               	<tr>
					<td class="lef">是否允许加入多个圈</td>
					<td style="text-align: left">
						<html:radio property="allowMultcoterie" value="1" name="coterie">允许</html:radio>
						<html:radio property="allowMultcoterie" value="0" name="coterie">不允许</html:radio>
					</td>
				</tr>
              </c:if>
              	  <c:if test="${count<2}">
               	<tr>
					<td class="lef">是否允许加入多个圈</td>
					<td style="text-align: left">
						<html:radio property="allowMultcoterie" value="1" name="coterie">允许</html:radio>
						<html:radio property="allowMultcoterie" value="0" name="coterie">不允许</html:radio>
					</td>
				</tr>
              </c:if>
             <c:if test="${count>1}">
               	<tr>
					<td class="lef">是否允许加入多个圈</td>
					<td style="text-align: left">
					<c:if test="${coterie.allowMultcoterie eq 1}">允许</c:if>
					<c:if test="${coterie.allowMultcoterie eq 0}">不允许</c:if>
						<html:hidden property="allowMultcoterie" name="coterie" />
					</td>
				</tr>
              </c:if>        
			</table>
			<table width="100%" style="margin-top: 5px;">
				<c:check code="sb05,sb06">
				<tr>
					<td>
						<input name="label" type="button" class="button1" value="提 交"
							onclick="add();" />
					 <input name="label" type="reset" class="button1" value="重 置" /></td>

				</tr>
				</c:check>
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
					
				</table></html:form>
			</div>
		</div>
	</body>
</html>
