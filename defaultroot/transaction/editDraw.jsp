<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<html>
	<head>
		<title>main</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="../_js/jquery-1.3.1.min.js"></script>
		<script src="../_js/common.js" type="text/javascript"></script>
		<script>

 function approval()
		{
		 	with(document.forms[0]){
		 		 if(confirm("确定批准通过吗？")){   
						document.forms[0].thisAction.value="approval";
					 	document.forms[0].submit();  
				  }
		 	}
		}
 function approvalSubtract()
		{
		 	with(document.forms[0]){
		 		 if(confirm("确定批准通过吗？")){   
						document.forms[0].thisAction.value="approvalSubtract";
					 	document.forms[0].submit();  
				  }
		 	}
		}
 function audit()
		{
		 	with(document.forms[0]){
		 		 if(confirm("确定核准通过吗？")){   
						document.forms[0].thisAction.value="audit";
					 	document.forms[0].submit();  
				  }
		 	}
		}
function achieve(){
   	if(confirm("确认后，您所选中的将会扣款，您确认吗？")){
	    document.forms[0].action="../transaction/drawlist.do?thisAction=auditSubtract";
	    document.forms[0].submit();
	    }
}
</script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/transaction/drawlist.do">
					<html:hidden property="thisAction" />
					<html:hidden property="type" value="${type}"/>
					<html:hidden property="bank" value="${bank}"/>
					<html:hidden property="status" value="${statu}"/>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
<c:import url="../_jsp/mainTitle.jsp?title1=提现管理&title2=处理提现" charEncoding="UTF-8" />

                             <hr>
				<table id="tableObj"  width="100%" cellpadding="0" cellspacing="0" border="0"
				class="dataList" >
				<tr>
				    <th><div>序号</div></th>
					<th><div>交易号</div></th>
					<th><div>商户名</div></th>
					<th><div>申请时间</div></th>
					<th><div>提现银行</div></th>
					<th><div>银行卡号</div></th>
					<th><div>提取时间</div></th>
					<th><div>金额(元)</div></th>
					<th><div>类型</div></th>
					<th><div>状态</div></th>
					
					
				</tr>
				<c:forEach var="info" items="${drawlist}" varStatus="status" >
					<tr >
						<td ><c:out value="${status.count+(dlf.intPage-1)*dlf.perPageNum}" /></td>
						 <td> 
						<html:link page="/transaction/drawLoglist.do?thisAction=listDrawLogByContent&orderNo=${info.orderNo}"><c:out value="${info.orderNo}" /></html:link>
						</td>
						<td>
							<html:link page="/agent/agentlist.do?thisAction=view&agentId=${info.agent.id}"><c:out value="${info.agent.name}" /></html:link>
						</td>
						<td><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.requestDate}"/></td>
						<td><c:out value="${info.bankTo}" /></td>
						<td><c:out value="${info.cardNo}" /></td>
						<td><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${info.drawDate}"/></td>
						<html:hidden property="bank" value="${info.bank}"/>
						<td style="text-align: right;"><fmt:formatNumber value="${info.amount}" pattern="0.00"/>&nbsp;</td>
						<td><c:out value="${info.typeTo}" /></td>
						<td><FONT color="red"><c:out value="${info.state}"></c:out></FONT> 
						<html:hidden property="selectedItems" value="${info.id}" />
						</td>
						</tr>
						</c:forEach>
					</table>
					<table>
						<c:if test="${statu==1}">
									<tr>
										<td class="lef">
											核准人备注：
										</td>
										<td style="text-align: left">
											<html:text property="note1" name="draw" styleClass="colorblue2 p_5" value="" />
										</td>
									</tr>
							</c:if>
					<c:if test="${statu==0}">
									<tr>
										<td class="lef">
											备注：
										</td>
										<td style="text-align: left">
											<html:text property="note" name="draw" styleClass="colorblue2 p_5"  value=""/>
											
										</td>
									</tr>
							</c:if>
					
					</table>
						<table width="100%" style="margin-top: 5px;">
							<tr>
								<td>
								<c:if test="${statu==1 && type!=2}">
								<c:check code="sc06">
									<input name="label" type="button" class="button1"
										value="核准提现" onclick="audit();">
										</c:check>
								</c:if>
								<c:if test="${statu==1 && type==2}">
								<c:check code="sc06">
									<input name="label" type="button" class="button2"
										value="核准并扣款" onclick="achieve();">
										</c:check>
								</c:if>
								<c:if test="${statu==0 && type!=2}" >
								<c:check code="sc05">
									<input name="label" type="button" class="button1"
										value="批准提现" onclick="approval();">
										</c:check>
								</c:if>
								<c:if test="${statu==0 && type==2}" >
								<c:check code="sc05">
									<input name="label" type="button" class="button1"
										value="批准扣款" onclick="approvalSubtract();">
										</c:check>
								</c:if>
								
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
		</table></html:form>
			</div>
			
		</div>							
  </body>
</html>
