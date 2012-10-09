<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html" %>  
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>


<HTML>
<HEAD>
<title></title>
<script language="JavaScript">
<!--
  function returnPage()
  {
    window.history.back();
  }
-->
</script>
<link href="../_css/css.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head> 
 
<body>
<c:import url="/_jsp/mainTitle.jsp?title=信息提示" charEncoding="UTF-8"/>
<table width="100%" border="0">
  <tr>
    <td height="27">&nbsp;</td>
  </tr>
</table>

<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="25" height="18"><img src="../_img/cg_bg1.gif" width="25" height="18"></td>
    <td background="../_img/cg_bg2.gif">&nbsp;</td>
    <td width="25" height="18"><img src="../_img/cg_bg3.gif" width="25" height="18"></td>
  </tr>
  <tr>
    <td background="../_img/cg_bg4.gif">&nbsp;</td>
    <td><table height="100" border="0" align="center" cellpadding="0" cellspacing="0" >
        <tr> 
		  <td><image src="../_img/alert_info.gif" />
			 对不起! 您没有相应权限,请询问管理员!<br>				 
		  </td>
		</tr>
		<tr height="20"> 
		  <td colspan="2">
		  </td>
		</tr>
		<tr> 
		  <td colspan="2">
			<div align="center">
			    <html:img page="/_img/confirm.gif" onclick="returnPage();" />
			</div>
		  </td>
		</tr>
	  </table></td>
    <td background="../_img/cg_bg5.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="../_img/cg_bg6.gif" width="25" height="27"></td>
    <td background="../_img/cg_bg7.gif">&nbsp;</td>
    <td><img src="../_img/cg_bg8.gif" width="25" height="27"></td>
  </tr>
</table>
</body>
</html>