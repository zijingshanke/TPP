String.prototype.trim = function()
{
    return this.replace(/(^[\s]*)|([\s]*$)/g, "");
}
String.prototype.lTrim = function()
{
    return this.replace(/(^[\s]*)/g, "");
}
String.prototype.rTrim = function()
{
    return this.replace(/([\s]*$)/g, "");
}
String.prototype.replaceAll  = function(s1,s2){    
return this.replace(new RegExp(s1,"gm"),s2);    
}  

function returnHttps(url)
{
 var temp=url;//self.location.href;
 alert(temp);
 if(temp.indexOf("https")<0 && temp.indexOf("http")>=0 && temp.indexOf("fdpay")<0)
   return temp.replace("http","https");
 else
   return temp;  
   
}

function checkIsInteger(str)
{
    if(str == "")
        return true;
    if(/^(\-?)(\d+)$/.test(str))
        return true;
    else
        return false;
}
function extract_left(value)
{
  return value.substring(0, 1)
}

function extract_right(value)
{
  return value.substring(value.length - 1)
}

function convertStrToURLStr(str,findChar,replaceChar)
{
  var len=str.length;
 	for (var i=0;i<len;i++)
	{
	  if (str.charAt(i)==findChar)
		{
		  str=str.slice(0,i)+replaceChar+str.slice(i+1);
		  return str;
		}
	}
	return str;
}

function removeLetter(str)
{
  var len=str.length;
  for (var i=0;i<len;i++)
  {
   if(((str.charCodeAt(i)>=65)&&(str.charCodeAt(i)<=90))||((str.charCodeAt(i)>=97)&&(str.charCodeAt(i)<=122)))
   {
	  str=str.slice(i);
   }
  }
  return str;
  
}

 function checkIsChinese(str)
 {  
   var pattern = /^[\u4E00-\u9FA5]+$/;
   return pattern.test(str)
 }

 
 function is2Lettle(str)
 {  
   var pattern = /^[a-zA-Z]{2}$/;
   return pattern.test(str)
 }
 function is2Numm(str)
 {  
   var pattern = /^[0-9]{2}$/;
   return pattern.test(str)
 }
 
  function checkIsEnglish(str)
 {
    var pattern = /^(([A-Za-z]+)[\/]([A-Za-z]+))$/
    return pattern.test(str)
 }

 function isNumberLetter(str)
 {
    var pattern = /^([A-Za-z0-9]{6,20})$/
    return pattern.test(str)
 }
 
 function isValidName(str)
 {
    var pattern = /^([A-Za-z]+[\/][A-Za-z]+)|([a-zA-Z\u4E00-\u9FA5]+)$/
    return pattern.test(str)
 }
 
 
 function isPNR(str)
 {
    var pattern = /^([A-Za-z0-9]{5})$/
    return pattern.test(str)
 }
 
 
 function checkIsNumber(str)
 {
    //���ֵΪ�գ�ͨ��У��
    if (str == "")
        return true;
    var pattern = /^[\d]*$/
    return pattern.test(str);
 }
 function isNumber(str)
 {
	if (str=="")
	  return false;
  var pattern = /^[-]?[\d]*[\.]?[\d]*$/
    return pattern.test(str);
 }
  function isCustomerCode(str)
 {
	if (str=="")
	  return false;
    var pattern = /^[8](\d{7})$/
    return pattern.test(str);
 }
  function is4Time(str)
 {
	var pattern = /^[0-9]/
    return pattern.test(str);
 }
 function is8Date(str)
 {
    var pattern=/^(\d{8})$/
    return(pattern.test(str));   
 }
  function is4Time(str)
 {
    var pattern=/^([0-2][0-9][0-5][0-9])$/
    return(pattern.test(str));   
 }
  function is10Date(str)
 {
    var pattern=/^((\d{4})-(\d{2})-(\d{2}))$/
    return(pattern.test(str));   
 }
 
  function is3Code(str)
 {
    var pattern=/^([A-Z]{3})/
    return(pattern.test(str));   
 }
 
  function checkIsEMail(str)
  {
    if (str == "")
        return true;
    var pattern =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
    if (pattern.test(str))
    {
      return true;
    }    
    else
        return false; 
 }
  
  function checkIsMTel(str)
  {
    var pattern =/^([1][3|5]([0-9]{9}))$/
    return (pattern.test(str));
 }
 function isMultMTel(str)
 {
    if (str=="")
	  return false;
	var pattern =/^([1][3|5]([0-9]{9}))|(([1][3|5]([0-9]{9}))([,][1][3|5]([0-9]{9}))+)$/
    return (pattern.test(str));
 }
 
 function isMultTel(str)
 {
    if (str=="")
	  return false;
	var pattern =/^([0-9]+)|([0-9]+([,][0-9]+))+$/
    return (pattern.test(str));
 }
  
  function checkIsTel(str)
  {
    if (str == "")
        return true;
    var pattern =/^([0-9,-])/
    return pattern.test(str);
 }
 
  function isIdCorrect(src)
  {
      var pattern=/^((\d{18})|(\d{17}[xX])|(\d{15}))$/
    return(pattern.test(src));
  }
 
 String.prototype.trim = function()
 {
    return this.replace(/(^[\s]*)|([\s]*$)/g, "");
 }
 

function getTodayDate(c)
{
  var today=new Date();
  var year=today.getYear();
  var month=today.getMonth();
  var day=today.getDate();
  return year+c+getTwoNum(month+1)+c+getTwoNum(day)
	
}
function getTwoNum(num)
{
  if (num<10)
    return "0"+num;
  else
    return num;  

}
function isNo(src)
{
  var p= /^[A-Za-z\_0-9]{5,}$/;
  return(p.test(src));
}
function isTel(src)
{
  var p= /^[\d{6,}]*$/;
  return(p.test(src));
}

  
  
function  sumCheckedBox(obj)
{
  var checkedlength=0;
  var len;
  var obf;
  var objForm;
 // obf=obj.name
 // if (obf!="")
 // {
	//  alert(obf);
  //obf=obf.substring(0,obf.length-1);
 // objForm=obj.form.name
 // }
  if (obj==undefined || obj.length==undefined)
  { 

   if (obj.checked)
   {
    // if (eval("document."+objForm+"."+obf)!=undefined)
	//  eval("document."+objForm+"."+obf+".value=0")
	 return 1;   
   }
   else
   {
    // if (eval("document."+objForm+"."+obf)!=undefined)
	//  eval("document."+objForm+"."+obf+".value=-1")
	 return 0; 	 
   }
  }
  else
  { 
    len=obj.length;
    for(i=0;i<len;i++)
    {
      if (obj[i].checked)
        checkedlength++;
    }
	//if (checkedlength==0)
	//{
	// if (eval("document."+objForm+"."+obf)!=undefined)
	// {
	//  eval(document+"."+objForm+"."+obf+".value=-1")    
	// }
	// else
	// {
	 //  if (eval("document."+objForm+"."+obf)!=undefined)
	////  eval(document+"."+objForm+"."+obf+".value=1")   
	// }
	//}
	return checkedlength;
  }
}

function  selCheckedBoxValue(obj)
{
  var checkedlength=0;
  var len=obj.length;
  if (obj==undefined || obj.length==undefined)
  {
   if (obj.checked)
   {
	 return obj.value;
   }
   else
   {
     return "-1";   
   }
  }
  else
  { 
    for(i=0;i<len;i++)
    {
      if (obj[i].checked)
        return obj[i].value;
    }
    return "";
  }
}
 
 function getCheckBoxStr(obj)
 {

  var len;
  var str="";
  if (obj==undefined || obj.length==undefined)
  { 
    return "";
  }
  else
  { 
    len=obj.length;
    for(i=0;i<len;i++)
    {
      if (obj[i].checked)
	  {
		if (i==len-1)
          str=str+obj[i].value
	    else
		  str=str+obj[i].value+",";
	  }
    }
	return str;
  }
 }
 
 
 function getCheckBoxArray(obj)
 {
 	var arr=new Array();
  if (obj==undefined || obj.length==undefined)
  {   
  	arr[0]=0;
 // 	alert("arrrrrrr");
    return arr;
  }
  
  var len;
  var k=0;
  if (obj.length>0)
  { 
    len=obj.length;
    for(i=0;i<len;i++)
    {
      if (obj[i].checked)
	    {
         arr[k++]=i;
	    }
    }	

  }
  else
  {
  	arr[0]=0;

  }

    return arr;
 }
 
 function setCheckBox(obj)
 {
   var b=false;
 // var obf;
  //var objForm;
 // obf=obj.name
 // obf=obf.substring(0,obf.length-1);
 // objForm=obj.form.name
  
  if (obj==undefined || obj.length==undefined)
  {
  //  if (eval("document."+objForm+"."+obf)!=undefined)
   //   eval("document."+objForm+"."+obf+".value=0")
    return;
  }
   for (i=0;i<obj.length;i++)
   {
	 b=b|obj[i].checked
   }
   if (!b)
   {
	 obj[0].value="-1";
	 obj[0].checked=true
   }

 }
 
 
  function isSelRadio(objRadio)
  {
  	if(objRadio && objRadio.checked)
  	{
  		return true
  	}
	  for (i=0; i<objRadio.length; i++)
	  {
	   if (objRadio[i].checked)
	   {
	     return true;
		 }
   }
	 return false;
  } 
  
  function SelRadioObj(objRadio)
  {
	  for (i=0; i<objRadio.length; i++)
	  {
	   if (objRadio[i].checked)
	   {
	     return objRadio[i];
		 }
   }
	 return null;
  }
  
  function selRadio(objRadio)
  {

    if (!objRadio)
	{
	  if (objRadio.checked)
	    return 1
	  else	
	   return -1;
	}
	  
	for (i=0; i<objRadio.length; i++)
	{
	  // alert(objRadio[i].checked);
	  if (objRadio[i].checked)
	  {
	   return i;
	  }
   }
   return 0;
  }
 function selRadioValue(objRadio)
  {
    if (objRadio[0]==null)
	  return objRadio.value;//һ��radio��ʱ����ô�죿
	for (i=0; i<objRadio.length; i++)
	{
	   
       if (objRadio[i].checked)
	   {
	     return objRadio[i].value;
		}
    }
	return "";
  }

function addID(objOrderNum,objSelectedItems)
{

  if (objSelectedItems[0]==null || objSelectedItems[0]=="undefined")
  {
    objOrderNum.value=objSelectedItems.value+","+objOrderNum.value
    return;
  }
  for (i=0;i<objOrderNum.length;i++)
  {
    objOrderNum[i].value=objSelectedItems[i].value+","+objOrderNum[i].value
  }
}
function turnToPage(objForm,id)
{
 //alert("start...="+objForm.intPage.value);
  var p=0;
  if (id==0)
    p=1;
  else if (id==1)	
    p=parseInt(objForm.intPage.value)-1;
  else if (id==2)	
     p=parseInt(objForm.intPage.value)+1;
  else if (id==3)	 
   p=objForm.pageCount.value;
  else
   p=id;
  objForm.intPage.value=p;
 // alert(objForm.intPage.value);
 // if (objForm.thisAction.value.substring(0,4)!="list")
 if (objForm.thisAction.value.substring(0,4)!="list" && objForm.lastAction.value!="")
    objForm.thisAction.value=objForm.lastAction.value;
 // alert(objForm.thisAction.value);
  objForm.submit();
}

function turnToPage2(objForm,id)
{
  var p=0;
  if (id==0)
    p=1;
  else if (id==1)	
    p=parseInt(objForm.intPage.value)-1;
  else if (id==2)	
     p=parseInt(objForm.intPage.value)+1;
  else if (id==3)	 
   p=objForm.pageCount.value;
  else
   p=id;
  objForm.intPage.value=p;
 // alert(objForm.intPage.value);
 // if (objForm.thisAction.value.substring(0,4)!="list" && objForm.lastAction.value!="")
 //   objForm.thisAction.value=objForm.lastAction.value;

  objForm.submit();

}

function getDayNum(endDate,startDate)
{
  //endDate,startDate��2002-12-18��ʽ  
       var  aDate,  oDate1,  oDate2,  iDays  
       aDate  =  endDate.split("-")  
       oDate1  =  new  Date(aDate[1]+ '-'  +  aDate[2]  +  '-'  +  aDate[0])  //ת��Ϊ12-18-2002��ʽ  
       aDate  =  startDate.split("-")  
       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
 
	 iDays  =  parseInt((oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //�����ĺ�����ת��Ϊ����  
   //  iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //�����ĺ�����ת��Ϊ����  
	 return  iDays  	
}

function getNextDate(startDate,sDay)
{
  var aDate  =  startDate.split("-")  
  oDate2= new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
  oDate2.setDate(oDate2.getDate()+sDay);
  var year=oDate2.getYear();
  var month=oDate2.getMonth();
  var day=oDate2.getDate();
  return year+'-'+getTwoNum(month+1)+'-'+getTwoNum(day)
}

function numOfChar(str,char1)
{
  if (str.substr(str.length-1,str.length)==char1)
    str=str.substr(0,str.length-1);
  var arry=str.split(char1);   
  return arry.length ;
}

  function selectCurrent(selc,cid)
  {
    var councc;
    if (cid.trim()=="" || cid=="" || cid == undefined) 
	  {
      selc.selectedIndex=0; 
	    return;
  	}

    councc=selc.options.length;

    for (var n=0;n<councc;n++)
    {
	  if (isNaN(cid) && selc.options(n).value==cid)
	  {
		selc.selectedIndex=n; 
        return;
	  }
	  else if (selc.options(n).value==cid || selc.options(n).value.toLowerCase()==cid.toLowerCase())
      {
		selc.selectedIndex=n; 
        return;
      }  
    }
  }
function addOptions(select, javascript)
{    
    if(select)
    {      
        var myOptions = "";
     //   alert(javascript);
        eval(javascript);  //执行从服务端返回的javascript代码
        removeAllofSelect(select);
        for(var i = 0; i < values.length ; i++)  // 从options数组中取数据
        {             
            var s = "";
            if(isIE()) 
            {
              select.options[select.options.length] = new Option(texts[i],values[i]);
            }
            else
            {
           
                myOptions += "<option value='" + values[i] + "'>" ;
                myOptions += texts[i];
                myOptions += "</option>"
            }
        }
         
    }
       
    var id = select.id;
    if(!isIE())    
        select.innerHTML =  myOptions;    
     
}
function isIE()
{
  return true;
}

  function removeAllofSelect(_select)
  {
     while(_select.hasChildNodes())
      _select.removeChild(_select.firstChild);
  
  }
  
  function openModalDialog(_width,_height,_url,par)
  { 
    var height=_height;
    var width=_width;
    window.showModalDialog(_url,par,"dialogwidth:"+_width+"px;dialogheight:"+_height+"px;center:yes;status:no;scroll:no;help:no");
  } 
  
  function openWindow(_width,_height,_url)
  { 
    var height=_height;
    var width=_width;
    var left=0;
    var top=0;
    left = (screen.width-_width)/2;
    top = (screen.height-_height)/2;
    window.open(_url,'_blank','height='+_height+',width='+_width+',left='+left+',top='+top+',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no,help:no'); 
  } 
  
  function selectCurrent(selc,cid)
  {
    if (cid.trim()=="" || cid=="" || cid == undefined) 
	  {
	  
      selc.selectedIndex=0; 
	    return;
  	}
    var councc;
    councc=selc.options.length;

    for (var n=0;n<councc;n++)
    {
	  if (isNaN(cid) && selc.options(n).value==cid)
	  {
		selc.selectedIndex=n; 
        return;
	  }
	  else if (selc.options(n).value==cid || selc.options(n).value.toLowerCase()==cid.toLowerCase())
      {
		selc.selectedIndex=n; 
        return;
      }  
    }
  }