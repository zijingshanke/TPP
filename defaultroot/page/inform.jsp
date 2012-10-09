<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<HTML>
<HEAD>
<TITLE></TITLE>
<SCRIPT language=JavaScript src="../_js/common.js"></SCRIPT>
<script language="JavaScript">
<!--
  function closeme()
  {
    var myurl=document.getElementById("myurl").href;
    var forwardPage='<c:out value="${inf.forwardPage}"/>';
    
    if(window.opener!= null && forwardPage!="")
	  {
	   // openNewUrl();
	    window.opener.location.replace(myurl+'&ram='+Math.random);
	  }
	  window.opener=null;
	  window.close();     
  }
  

  function addForwardPageParam()
  {
    var myurl=document.getElementById("myurl").href;
    document.form1.action=myurl;
    document.form1.submit();      
  }
  
-->
</script>
<html:base />
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=信息提示" charEncoding="UTF-8" />

<table width="100%" border="0">
	<tr>
		<td height="27">&nbsp;</td>
	</tr>
</table>

<form name="form1" action="" method="post">
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="25" height="18"><img src="../_img/cg_bg1.gif"
			width="25" height="18"></td>
		<td background="../_img/cg_bg2.gif">&nbsp;</td>
		<td width="25" height="18"><img src="../_img/cg_bg3.gif"
			width="25" height="18"></td>
	</tr>
	<tr>
		<td background="../_img/cg_bg4.gif">&nbsp;</td>
		<td>
		<table height="100" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td><image src="../_img/ok_info.gif" /></td>
				<td valign="middle">&nbsp;&nbsp;&nbsp;
				<div align="left"><c:out value="${inf.message}"
					escapeXml="false" /></div>
				</td>
			</tr>
			<c:if test="${listRadio!=null}">
				<tr>
					<td colspan="2"><c:forEach var="tvv" items="${listRadio}"
						varStatus="status">
						<input type="radio" name="selectRadio"
							value="<c:out value='${tvv.value1}'/>"
							<c:out value='${tvv.value2}'/>>
						<c:out value='${tvv.title}' />
						<br>
					</c:forEach></td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="center"><c:choose>
					<c:when test="${inf.close==true}">
						<c:if test="${inf.paramId=='' and params==null}">
							<html:link page="${inf.forwardPage}" styleId="myurl"
								onclick="openNewUrl()" />
						</c:if>
						<c:if test="${inf.paramId!='' and params==null}">
							<html:link page="${inf.forwardPage}" paramId="${inf.paramId}"
								paramProperty="paramValue" paramName="inf" styleId="myurl"
								onclick="openNewUrl()" />
						</c:if>
						<c:if test="${inf.paramId=='' and params!=null}">
							<html:link page="${inf.forwardPage}" name="params"
								styleId="myurl" onclick="openNewUrl()" />
						</c:if>
						<c:if test="${inf.paramId=='' and params!=null}">
							<html:link page="${inf.forwardPage}" paramId="${inf.paramId}"
								paramProperty="paramValue" paramName="inf" name="params"
								styleId="myurl" onclick="openNewUrl()" />
						</c:if>
						<a href="JavaScript:closeme()"> <c:if
							test="${listRadio!=null}">
							<html:image page="/_img/confirm.gif" border="0"
								onclick="addForwardPageParam()" />
						</c:if> <c:if test="${listRadio==null}">
							<html:img page="/_img/confirm.gif" border="0" />
						</c:if></a>

					</c:when>
					<c:when
						test="${inf.back==false and inf.paramId=='' and params==null}">
						<html:link page="${inf.forwardPage}" styleId="myurl"
							onclick="openNewUrl()">
							<c:if test="${listRadio!=null}">
								<html:image page="/_img/confirm.gif" border="0"
									onclick="addForwardPageParam()" />
							</c:if>
							<c:if test="${listRadio==null}">
								<html:img page="/_img/confirm.gif" border="0" />
							</c:if>
						</html:link>
					</c:when>
					<c:when
						test="${inf.back==false and inf.paramId!='' and params==null}">
						<html:link page="${inf.forwardPage}" styleId="myurl"
							paramId="${inf.paramId}" paramProperty="paramValue"
							paramName="inf" onclick="openNewUrl()">
							<c:if test="${listRadio!=null}">
								<html:image page="/_img/confirm.gif" border="0"
									onclick="addForwardPageParam()" />
							</c:if>
							<c:if test="${listRadio==null}">
								<html:img page="/_img/confirm.gif" border="0" />
							</c:if>
						</html:link>
					</c:when>
					<c:when
						test="${inf.back==false and inf.paramId=='' and params!=null}">
						<html:link page="${inf.forwardPage}" styleId="myurl" name="params"
							onclick="openNewUrl()">
							<c:if test="${listRadio!=null}">
								<html:image page="/_img/confirm.gif" border="0"
									onclick="addForwardPageParam()" />
							</c:if>
							<c:if test="${listRadio==null}">
								<html:img page="/_img/confirm.gif" border="0" />
							</c:if>
						</html:link>
					</c:when>
					<c:when
						test="${inf.back==false and inf.paramId!='' and params!=null}">
						<html:link page="${inf.forwardPage}" styleId="myurl"
							paramId="${inf.paramId}" paramProperty="paramValue"
							paramName="inf" name="params" onclick="openNewUrl()">
							<c:if test="${listRadio!=null}">
								<html:image page="/_img/confirm.gif" border="0"
									onclick="addForwardPageParam()" />
							</c:if>
							<c:if test="${listRadio==null}">
								<html:img page="/_img/confirm.gif" border="0" />
							</c:if>
						</html:link>
					</c:when>
					<c:when test="${inf.back==true}">
						<a href="JavaScript:window.history.back()"> <c:if
							test="${listRadio!=null}">
							<html:image page="/_img/confirm.gif" border="0"
								onclick="addForwardPageParam()" />
						</c:if> <c:if test="${listRadio==null}">
							<html:img page="/_img/confirm.gif" border="0" />
						</c:if> </a>
					</c:when>
					<c:otherwise>
						<html:link page="${inf.forwardPage}" styleId="myurl"
							paramId="${inf.paramId}" paramProperty="paramValue"
							paramName="inf" name="params" onclick="openNewUrl()">
							<c:if test="${listRadio!=null}">
								<html:image page="/_img/confirm.gif" border="0"
									onclick="addForwardPageParam()" />
							</c:if>
							<c:if test="${listRadio==null}">
								<html:img page="/_img/confirm.gif" border="0" />
							</c:if>
						</html:link>
					</c:otherwise>
				</c:choose></td>
			</tr>
			<c:if test="${inf.blank!=''}">
				<tr>
					<td colspan="2" align="center"><html:link
						page="${inf.blankForwardPage}" target="_blank">
						<c:out value="${inf.blank}" />
					</html:link></td>
				</tr>
			</c:if>
		</table>
		</td>
		<td background="../_img/cg_bg5.gif">&nbsp;</td>
	</tr>
	<tr>
		<td><img src="../_img/cg_bg6.gif" width="25" height="27"></td>
		<td background="../_img/cg_bg7.gif">&nbsp;</td>
		<td><img src="../_img/cg_bg8.gif" width="25" height="27"></td>
	</tr>
</table>
</form>
<script>  
   var isPay=false;
   var blank='<c:out value="${inf.blank}"/>';
   var height=400;
   var width=700;
   var left = (screen.width-width)/2;
   var top = (screen.height-height)/2;
  
   function clickPay(blankForwardPage)
   {
     isPay=true;  
     if(blank!='')
       window.open(blankForwardPage,'_blank','height='+height+',width='+width+',left='+left+',top='+top+',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no'); 
      
   }
   
   function openNewUrl()
   {
     var blankForwardPage='<c:out value="${inf.blankForwardPage}"/>'; 
    
     if(blank!='' && !isPay)
       window.open(blankForwardPage,'_blank','height='+height+',width='+width+',left='+left+',top='+top+',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no'); 
   }

  var isClose='<c:out value="${inf.close}"/>'
  if(isClose=='true')  
    setTimeout("closeme()",10000);
</script>

</body>
</html>