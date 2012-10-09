<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
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

function add()
{
    document.forms[0].thisAction.value="add";
    document.forms[0].submit();
}

function edit()
{
 if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
  if (sumCheckedBox(document.forms[0].selectedItems)<1)
    alert("您还没有选择新闻！");
  else if (sumCheckedBox(document.forms[0].selectedItems)>1)
    alert("您一次只能选择一个新闻！");
  else
  {
    document.forms[0].thisAction.value="edit";
    document.forms[0].submit();
  }
}

function del()
{
 if(document.forms[0].selectedItems==null){
		   	alert("没有数据，无法操作！");
		   }
		  else
  if (sumCheckedBox(document.forms[0].selectedItems)<1)
    alert("您还没有选择新闻！");
  else if(confirm("您真的要删除选择的这些新闻吗？"))
  {
    document.forms[0].thisAction.value="delete";
    document.forms[0].submit();
  }
}



function search()
{
   document.forms[0].thisAction.value="list";
   document.forms[0].submit();
}

function searchAgent()
	{
	   document.forms[0].thisAction.value="list";
	   document.forms[0].submit();
	}
	
     function checkAll(e, itemName)
{
   var aa = document.getElementsByName(itemName);
   for (var i=0; i<aa.length; i++)
    aa[i].checked = e.checked;
}
		
function checkItem(e, allName)
{
   var all = document.getElementsByName(allName)[0];
   if(!e.checked) all.checked = false;
   else
   {
     var aa = document.getElementsByName(e.name);
     for (var i=0; i<aa.length; i++)
      if(!aa[i].checked) return;
     all.checked = true;
   }
}
</script>
</head>
<body>
<div id="mainContainer">
<div id="container"><html:form action="/information/newslist.do" >
	<html:hidden property="thisAction" />
	<html:hidden property="lastAction" />
	<html:hidden property="intPage" />
	<html:hidden property="pageCount" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="10" height="10" class="tblt"></td>
			<td height="10" class="tbtt"></td>
			<td width="10" height="10" class="tbrt"></td>
		</tr>
		<tr>
			<td width="10" class="tbll"></td>
			<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=新闻发布&title2=新闻列表" charEncoding="UTF-8" />
			<div class="searchBar">
			<p>搜索栏</p>
			<hr />
			<table cellpadding="0" cellspacing="0" border="0" class="searchPanel">
				<tr>
					<td>标题： <html:text property="title" style="width:150px;"
						styleClass="colorblue2 p_5" /></td>
					<td>发布人： <html:text property="userName" style="width:150px;"
						styleClass="colorblue2 p_5" /></td>
					<td><input type="submit" name="button" id="button" value="查询"
						class="submit greenBtn" /></td>
				</tr>
			</table>
			</div>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList">
				<tr>
					<th>
					<div>序号</div>
					</th>
					<th>
					<div
						style="height: 100%; width: 100%; vertical-align: center; padding-top: 4px;">
					<input type="checkbox" onclick="checkAll(this, 'selectedItems')"
						name="sele" /> 全选</div>
					</th>
					<th>
					<div>标题</div>
					</th>
					<th>
					<div>更新时间</div>
					</th>
					<th>
					<div>状态</div>
					</th>
					<th>
					<div>阅读数</div>
					</th>
					<th>
					<div>排名</div>
					</th>
					<th>
					<div>发布人</div>
					</th>
				</tr>
				<c:forEach var="info" items="${nlf.list}" varStatus="status">
					<tr>
						<td><c:out
							value="${status.count+(nlf.intPage-1)*nlf.perPageNum}" /></td>
						<td><html:multibox property="selectedItems"
							style="height:5px;" value="${info.id}"
							onclick="checkItem(this, 'sele')">
						</html:multibox></td>
						<td><html:link
							page="/information/newslist.do?thisAction=view&id=${info.id}">
							<c:out value="${info.title}" />
						</html:link></td>
						<td><c:out value="${info.createDate}" /></td>
						<td><c:if test="${info.status==1}">
											显示
											</c:if> <c:if test="${info.status==0}">
											不显示
											</c:if></td>
						<td><c:out value="${info.readNum}" /></td>
						<td><c:out value="${info.rank}" /></td>
						<td><c:out value="${info.userName}" /></td>

					</tr>
				</c:forEach>

			</table>

			<table width="100%" style="margin-top: 5px;">
				<tr>
					<td><c:check code="sh01">
						<input name="label" type="button" class="button1" value="新 增"
							onclick="add();" />

						<input name="label" type="button" class="button1" value="修 改"
							onclick="edit();" />

						<input name="label" type="button" class="button1" value="删 除"
							onclick="del();" />
					</c:check></td>
					<td align="right">
					<div>共有记录&nbsp; <c:out value="${nlf.totalRowCount}"></c:out>
					&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp; [ <a
						href="JavaScript:turnToPage(document.forms[0],0)">首页</a> | <a
						href="JavaScript:turnToPage(document.forms[0],1)">上一页</a> | <a
						href="JavaScript:turnToPage(document.forms[0],2)">下一页</a> | <a
						href="JavaScript:turnToPage(document.forms[0],3)"> 末页</a>] 页数: <c:out
						value="${nlf.intPage}" /> / <c:out value="${nlf.pageCount}" /> ]
					</div>
					</td>
				</tr>
			</table>
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
</html:form></div>
</div>
</body>
</html>
