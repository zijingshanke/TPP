<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<html>

<head>
<TITLE></TITLE>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="../_js/jquery-1.3.1.min.js"></script>
<script src="../_js/common.js" type="text/javascript"></script>
<SCRIPT src="../_js/stree/dtree.js"></SCRIPT>

<script>
<!--
  function saveRight()
  {
     if (confirm("您真的要修改这些权限吗？"))
	 {
	    document.forms[0].selectedRightItem.value=sumCheckedBox(document.forms[0].selectedRightItems)	
	   
		//alert(document.forms[0].selectedRightItem.value);
		
		
	    document.forms[0].thisAction.value="edituserright";
		//alert(document.forms[0].selectedRightItem.value);
	    document.forms[0].submit();
	 }
  }
  //用户角色
//--> 
</script>
</head>

<body class="body_m">
<c:import url="/_jsp/mainTitle.jsp?title=权限管理-->用户角色权限设置" charEncoding="UTF-8"/>
<table width="100%" height="45"  class="table_sc" >
          <tr>
            <td> <img src="../_img/btn_s.jpg" align="absmiddle" class="m_l_5 m_r_5" />
            	用户角色：<c:out value='${ulf.roleName}'/>
            </td>
          </tr>
        </table>
        
<html:form action="/right/rolelist.do"
	>
	<html:hidden name="ulf" property="thisAction" value="" />
	<html:hidden name="ulf" property="selectedRightItem" />
	<xml id="xmlfile">
	 <c:out value="${ulf.xml}" escapeXml="false"/>
	</xml>
	<table class="table_in">
		<tr>
		    <td valign="top">
            <script language="JavaScript">
<!--
function writeTree()
{
  var doc = new ActiveXObject("Microsoft.XMLDOM");
  var xmldoc;
  doc.async=false;
  doc=xmlfile.XMLDocument
  if (doc.parseError.errorCode!=0)
  {
	var myErr = doc.parseError;
	  alert("You have error " + myErr.reason);
	return;
  }
  xmldoc=doc.documentElement;
  var count=xmldoc.childNodes.length;

  var parentID=0;
  var treeId=0;
  var text="";
  var url="";
  var target="";
  var m=0;
  d = new dTree('d');
  d.add(0,-1,'功能项设定',"",' ?  ','','','');
  for (var j=0;j<count;j++)
  {   
    treeId=xmldoc.childNodes[j].attributes[4].value;
    text=xmldoc.childNodes[j].attributes[0].value;
    url=xmldoc.childNodes[j].attributes[3].value;
   // target=xmldoc.childNodes[j].attributes[2].value;
	d.add(treeId,0,text,url,text,target,'','');
	parentID=treeId
	mlen=xmldoc.childNodes[j].childNodes.length
	for (var m=0;m<mlen;m++)
	{	 
      treeId=xmldoc.childNodes[j].childNodes[m].attributes[4].value;
      text=xmldoc.childNodes[j].childNodes[m].attributes[0].value;
      url=xmldoc.childNodes[j].childNodes[m].attributes[3].value;
     // target=xmldoc.childNodes[j].childNodes[m].attributes[2].value;
	  d.add(treeId,parentID,text,url,text,target,'','');
	  mparentID=treeId
	  klen=xmldoc.childNodes[j].childNodes[m].childNodes.length
	  for (var k=0;k<klen;k++)
	  {	 
		treeId=xmldoc.childNodes[j].childNodes[m].childNodes[k].attributes[4].value;
		text=xmldoc.childNodes[j].childNodes[m].childNodes[k].attributes[0].value;
		url=xmldoc.childNodes[j].childNodes[m].childNodes[k].attributes[3].value;
		//target=xmldoc.childNodes[j].childNodes[m].childNodes[k].attributes[2].value;
		d.add(treeId,mparentID,text,url,text,target,'','');
	  }	
	}	
  }
  document.write(d);
} 
writeTree();
//-->
</script></td>
		</tr>
		<tr>
	        <td valign="top">
      
							<div  style="margin-top:5px;">
							 <input type="button" class="button1"
								onclick="saveRight();" value="保 存"> <input type="button"
								class="button1" onclick="document.forms[0].reset();" value="重 置">
								<input type="button"
								class="button1" onclick="window.history.back();" value="返 回"></div>
            </td>
		</tr>		
	</table>
</html:form>

<script language="JavaScript">
<!--
  var selectedRightItemsStr ='<c:out value="${ulf.selectedRightItemsStr}"/>';
  var items=selectedRightItemsStr.split(",");

  
  function fullCheckBox()
  {
    var obj=document.forms[0].selectedRightItems;   
   
    for(var i=0;i<obj.length;i++)
    {
       if (selectValue(obj[i].value))
         obj[i].checked=true;
      else  
         obj[i].checked=false;     
    }  
  }
  
  function selectValue(value)
  {    
    for(var j=0;j<items.length;j++)
    {
   
      if(items[j]==value)
        return true;
    }  
    return false;
  }
  
  fullCheckBox();
//-->
</script>
</body>
</html>