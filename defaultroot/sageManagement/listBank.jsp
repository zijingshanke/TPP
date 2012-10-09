<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'listBank.jsp' starting page</title>
    <link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.2.min.js"></script>
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script type="text/javascript">
function check(bk)
	{
		var ccb=document.getElementById("ccb");
       	var bcm=document.getElementById("bcm");
       	var icbc=document.getElementById("icbc");
       	var cmbc=document.getElementById("cmbc");
		var bank=document.getElementsByName("bank");
			 for(i=0;i<bank.length;i++) 
		 	{  
        		if(bank[i].value==bk)
          		 bank[i].checked=true;  
       		}  
       	if(bk=="CCB"){
	       	bcm.style.display="none";
			ccb.style.display="";
			icbc.style.display="none";
			cmbc.style.display="none";
		}else if(bk=="ICBC"){
	       	bcm.style.display="none";
			ccb.style.display="none";
			cmbc.style.display="none";
			icbc.style.display="";
		}else if(bk=="BCM"){
	       	bcm.style.display="";
			ccb.style.display="none";
			cmbc.style.display="none";
			icbc.style.display="none";
		}else if(bk=="CMBC"){
			cmbc.style.display="";
			bcm.style.display="none";
			ccb.style.display="none";
			icbc.style.display="none";
		}
	}
	
function sib(bank,key)
{
	document.forms[0].bankName.value=bank;
	document.forms[0].keyType.value=key;
	document.forms[0].action+="?thisAction=readKey";
	document.forms[0].submit();
}

</script>
  </head>
  
  <body>
 <html:form action="/system/bankKey.do">
 
 <html:hidden property="bankName"  ></html:hidden>
 <html:hidden property="keyType" ></html:hidden>
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
<c:import url="../_jsp/mainTitle.jsp?title1=安全管理&title2=银行列表" charEncoding="UTF-8" />
    <hr/>
	<!-- 开始 -->							
			<table align="center">
			<tr><td colspan="3">请选择银行 <hr/></td></tr>
				<tr>
					<td>
					<input type="radio" name="bank" value="CCB" onclick="check('CCB');"/><img src="../_img/bank/icon_ccb_s.gif" onclick="check('CCB');"/> 
					</td><td>
					<input type="radio" name="bank" value="ICBC" onclick="check('ICBC');"/><img src="../_img/bank/icon_icbc_s.gif" onclick="check('ICBC');"/>
					</td><td>
					<input type="radio" name="bank" value="BCM" onclick="check('BCM');"/><img src="../_img/bank/bankLogo_10.gif" onclick="check('BCM');"/>
					</td>
				</tr><tr>	
					<td>
					<input type="radio" name="bank" value="CMBC" onclick="check('CMBC');"/><img src="../_img/bank/bank_04.gif" onclick="check('CMBC');"/>
					</td><td>
					<input type="radio" name="bank" value="b"/><img src="../_img/bank/bank_03.gif"/> 
					</td><td>
					<input type="radio" name="bank" value="d"/><img src="../_img/bank/bank_05.gif"/>
					</td>
				</tr><tr>
					<td>
					<input type="radio" name="bank" value="e"/><img src="../_img/bank/bank_06.gif"/> 
					</td><td>
					<input type="radio" name="bank" value="f"/><img src="../_img/bank/bank_09.gif"/>
					</td><td>
					<input type="radio" name="bank" value="g"/><img src="../_img/bank/bank_11.gif"/>
					</td>
				</tr>
				<tr><td colspan="3" align="center"> <font color="red"><c:out value="${msUpdate}"></c:out></font> </td></tr>
			</table>
			<hr/>
			<div align="left" id="ccb" style="display: none;">
			<table align="center">
			<tr><td align="center"><img src="../_img/bank/ccb.jpg" /></td></tr> 
			<tr>
			<td align="center">
			
			<c:check code="sf02">
			<input class="button2" type="button" value="设置公钥" onclick="sib('CCB','pub');"/>
			</c:check>
			
			&nbsp;
			
			<c:check code="sf03">
			<input class="button2" type="button" value="设置私钥" onclick="sib('CCB','pri');" />
			</c:check>
			</td>
			</tr>
			</table>
			</div>
			<div align="left" id="icbc" style="display: none;">
			<table align="center">
			<tr><td align="center"><img src="../_img/bank/icbc.jpg" /></td></tr> 
			<tr><td align="center">
			<c:check code="sf02">
			<input class="button2" type="button" value="设置公钥" onclick="sib('ICBC','pub');"/>
			</c:check>
			&nbsp;
			<c:check code="sf03">
			<input class="button2" type="button" value="设置私钥" onclick="sib('ICBC','pri');"/>
			</c:check>
			</td></tr>
			</table>
			</div>						
			<div align="left" id="bcm" style="display: none;">
			<table align="center">
			<tr><td align="center"><img src="../_img/bank/bcm.jpg" /></td></tr> 
			<tr><td align="center">
			<c:check code="sf02">
			<input class="button2" type="button" value="设置公钥" onclick="sib('BCM','pub');"/>
			</c:check>
			&nbsp;
			<c:check code="sf03">
			<input class="button2" type="button" value="设置私钥" onclick="sib('BCM','pri');"/>
			</c:check>
			</td></tr>
			</table>
			</div>						
			<div align="left" id="cmbc" style="display: none;">
			<table align="center">
			<tr><td align="center"><img src="../_img/bank/cmbc.jpg" /></td></tr> 
			<tr><td align="center">
			<c:check code="sf02">
			<input class="button2" type="button" value="设置公钥" onclick="sib('CMBC','pub');"/>
			</c:check>
			&nbsp;
			<c:check code="sf03">
			<input class="button2" type="button" value="设置私钥" onclick="sib('CMBC','pri');"/>
			</c:check>
			</td></tr>
			</table>
			</div>						
	<!-- 结束 -->				
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
</html:form>
</body>
</html>
