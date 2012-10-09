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
<script type="text/javascript">
function sel()
{
	var email=document.getElementById("email").value;
	var amount=document.getElementById("amount").value;
	var note=document.getElementById("note").value;
	var re=/^([1-9]|[0.])[0-9]{0,}(([.]*\d{1,2})|[0-9]{0,})$/;
	var regm = /^([a-zA-Z0-9]+[-|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!email.match(regm))
	{
	alert("账户为空、出现空格或者Email格式不正确！");
		return false;
	}
	else if(!re.test(amount) || amount<0 || amount>1000000){
		alert("金额必须为数字，最多为两位小数并且不能大于1000000！");
		return false;
	}else if(note=="")
	{
	alert("备注不能为空");
	return false;
	}
	 if(confirm("确认此次充值吗？")){
	   var  bt= document.getElementsByName("balanceType").value;

       for(var i=0;i<document.getElementsByName("balanceType").length;i++)
         {
          
           if(document.getElementsByName("balanceType")[i].checked==true)
           {
              bt = document.getElementsByName("balanceType")[i].value;
           }
       }
	   
	   if(bt==0){
	  
	       document.forms[0].action="../transaction/charge.do?thisAction=otherCharge";
	   	   document.forms[0].submit();
	   }else if(bt==1){
	 
	     document.forms[0].action="../transaction/charge.do?thisAction=creditCharge";
	       document.forms[0].submit();
	   }
	      
	
		}
}

function checkBankDiv1(){
 document.getElementById("bankDiv1").style.display="none";
 document.getElementById("bankDiv2").style.display="none";
}
function checkBankDiv2(){
 document.getElementById("bankDiv1").style.display="";
 document.getElementById("bankDiv2").style.display="";
}



</script>
  </head>
  
  <body>
		<div id="mainContainer">
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
<c:import url="../_jsp/mainTitle.jsp?title1=充值管理&title2=线下充值" charEncoding="UTF-8" />
							<hr />
							<html:form action="/transaction/charge.do?thisAction=otherCharge">
							<table cellpadding="0" cellspacing="0" border="0" align="center">
							<tr><td style="text-align: right">充值账户：</td><td> <html:text property="agentLoginName" styleClass="colorblue2 p_5" styleId="email" value=""/> </td></tr>
							<tr><td style="text-align: right">充值金额：</td><td> <html:text property="amount" styleClass="colorblue2 p_5" styleId="amount" value=""/> <font color="red;"> 元</font> </td></tr>
							
							
							<tr><td style="text-align: right"><div id="bankDiv1">银行：</div></td>
							<td> <div id="bankDiv2"> <html:select property="bank">
									<html:option value="OTHER">其他</html:option>
									<html:option value="ICBC">工商银行</html:option> 
									<html:option value="CCB">建设银行</html:option>
									<html:option value="BCM">交通银行</html:option>
									<html:option value="CMBC">民生银行</html:option>
									<html:option value="CITIC">中信银行</html:option>
									<html:option value="ABC">农业银行</html:option>
								</html:select></div>
							<td>
							<tr>
						<td>金额类型：</td>
						<td><input type="radio" value="0" name="balanceType"  checked="checked" onclick="checkBankDiv2();">可用金额 
						    <input type="radio" value="1" name="balanceType" onclick="checkBankDiv1();"/>信用金额 </td>
						    <tr><td style="text-align: right">备注：</td><td> <html:textarea property="note" styleClass="colorblue2 p_5" style="width:350px; height:52px;" styleId="note" value=""/> </td></tr>
							<tr><td colspan="2" align="center"> <input type="button"  class="submit greenBtn" value="确认充值" onclick="sel();"/> </td></tr>
							</table>
							</html:form>
							<hr />
													
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
			</div>
		</div>
	</body>
</html>
