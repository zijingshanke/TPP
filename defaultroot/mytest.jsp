<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-html-el.tld" prefix="html" %>

<html>
<head>
<script src="_js/prototype.js" type="text/javascript"></script>
<script src="_js/certification.js" type="text/javascript"></script>
</head>

<body>



<a href="javascript:testajax('2766128@qq.com')" >到钱门去</a>

<a href="javascript:alert(validCert('27662k8@qq.com'));" >到钱门去2</a>

<script language="JavaScript">
document.domain = "qmpay.com";
 function validCert(email)
 { 
  var result=0;
     if(email.length>0 && email.indexOf('@')>0)
	  {
		 var myAjax = new Ajax.Request("https://qm.qmpay.com/security/certificate.do?thisAction=validAgent&email="+email,
		{ 
			thisAction:"get", 
			asynchronous:false,
			onComplete:function (originalRequest)
			{
			  result = originalRequest.responseText;			 
		    }, 
		    onException:function(originalRequest, ex)
	        {
	          alert("Exception:" + ex.message);
	          result=ex.message;	
	        }
	    }
	    );
	   }  
return result;
}

</script>

</body>

</html>



