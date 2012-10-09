<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-html-el.tld" prefix="html" %>

<html>
<head>

<script src="<%=request.getContextPath()%>/_js/prototype.js"></script>
<script src="<%=request.getContextPath()%>/_js/certification.js"></script>
<script   language="javascript">  
function displayDiv(flag)
{
  if(flag)
  {
    var div1=document.getElementById("div1");
    div1.style.displa=""
    //背景置成灰色，且层为项层。
  }




}

</script>


</head>


<body>

<div id="div1" style="display:none">
您的账户部分功能将不能正常使用。
 
请您进行以下操作：
由于本台电脑还未导入数字证书，您只能进行账户查询。 
如果您需要进行账户操作，请点此导入证书 
如果您的证书备份文件丢失、损坏、不匹配或忘记了备份密码，请申请注销证书 
如果您是工行U盾的证书用户，请在插入U盾后，重新登录支付宝账户。
</div>


</body>

</html>
