/*
*######################################
* eWebEditor v6.2 - Advanced online web based WYSIWYG HTML editor.
* Copyright (c) 2003-2009 eWebSoft.com
*
* For further information go to http://www.ewebsoft.com/
* This copyright notice MUST stay intact for use.
*######################################
*/

function BaseTrim(str){
	lIdx=0;
	rIdx=str.length;
	if (BaseTrim.arguments.length==2){
		act=BaseTrim.arguments[1].toLowerCase();
	}else{
		act="all";
	}

	for(var i=0;i<str.length;i++){
		thelStr=str.substring(lIdx,lIdx+1);
		therStr=str.substring(rIdx,rIdx-1);
		if ((act=="all" || act=="left") && thelStr==" "){
			lIdx++;
		}
		if ((act=="all" || act=="right") && therStr==" "){
			rIdx--;
		}
	}
	str=str.slice(lIdx,rIdx);
	return str;
}

function BaseAlert(theText,notice){
	alert(notice);
	theText.focus();
	theText.select();
	return false;
}

function isNotFloat(theFloat){
	len=theFloat.length;
	dotNum=0;
	if (len==0){
		return true;
	}
	for(var i=0;i<len;i++){
	    oneNum=theFloat.substring(i,i+1);
		if (oneNum=="."){
			dotNum++;
		}
        if ( ((oneNum<"0" || oneNum>"9") && oneNum!=".") || dotNum>1){
          return true;
		}
    }
	if (len>1 && theFloat.substring(0,1)=="0"){
		if (theFloat.substring(1,2)!="."){
			return true;
		}
	}
	return false;
}

function isNotNum(theNum){
	if (BaseTrim(theNum)==""){
		return true;
	}
	for(var i=0;i<theNum.length;i++){
	    oneNum=theNum.substring(i,i+1);
        if (oneNum<"0" || oneNum>"9"){
          return true;
		}
    }
	return false;
}

function isNotInt(theInt){
	theInt=BaseTrim(theInt);
	if ((theInt.length>1 && theInt.substring(0,1)=="0") || isNotNum(theInt)){
		return true;
	}
	return false;
}


function HighLightOver(){
	HighLightList("#E0E6F7");
}
function HighLightOut(){
	HighLightList("");
}

function HighLightList(color){
	var el=event.srcElement;
	var b=false;
	var tabElement=null;
	while (!b){
		el=GetParentElement(el, "TR");
		if (el){
			tabElement=GetParentElement(el, "TABLE");
			if (tabElement!=null && tabElement.className.toUpperCase()=="LIST"){
				break;
			}
			el=tabElement;
		}else{
			return;
		}
	}
	
	for (var i=0;i<el.children.length;i++){
		if (el.children[i].tagName=="TD"){
			el.children[i].style.backgroundColor=color;
		}
	}
}

function GetParentElement(obj, tag){
	while(obj!=null && obj.tagName!=tag){
		obj=obj.parentElement;
	}
	return obj;
}

function doCheckWH(flag){
	var oForm = document.myform;
	if (flag==1){
		tdPreview.innerHTML="<span style='font-size:"+oForm.d_syfontsize.value+";font-family:"+oForm.d_syfontname.value+"'>"+oForm.d_sytext.value+"</span>";
		oForm.d_sywztextwidth.value=tdPreview.offsetWidth;
		oForm.d_sywztextheight.value=tdPreview.offsetHeight;
	}else{
		var url=oForm.d_sypicpath.value;
		if (url==""){
			oForm.d_sytpimagewidth.value="0";
			oForm.d_sytpimageheight.value="0";
			tdPreview.innerHTML="";
		}else{
			if ((url.substring(0,1)!=".")&&(url.substring(0,1)!="/")){
				url="../"+getAppExt()+"/"+url;
			}
			tdPreview.innerHTML="<img border=0 src='"+url+"' onload='setCheckWH()' onerror='ErrorCheckWH()'>";
		}
	}
}

function getAppExt(){
	var p = location.pathname;
	var n = p.lastIndexOf(".");
	return p.substr(n+1).toLowerCase();
}

function setCheckWH(){
	document.myform.d_sytpimagewidth.value=tdPreview.offsetWidth;
	document.myform.d_sytpimageheight.value=tdPreview.offsetHeight;
}

function ErrorCheckWH(){
	BaseAlert(document.myform.d_sypicpath,"无效的图片水印图片路径！");
}

function doCheckAll(obj){
	var form = obj.form;
	for (var i=0;i<form.elements.length;i++){
		var e = form.elements[i];
		e.checked = obj.checked;
	}
}

document.onmouseover=HighLightOver;
document.onmouseout=HighLightOut;



function checkStyleSetForm(f){
	var o, v, re;

	o = f.d_name;
	v = trimObj(o);
	if (!v){
		return BaseAlert(o, "样式名：不能为空！");
	}else{
		re = new RegExp("[^a-zA-Z0-9_\-]+","gi");
		if (re.test(v)){
			return BaseAlert(o, "样式名：只能由英文字母、下划线、中划线组成！");
		}
	}

	o = f.d_fixwidth;
	v = trimObj(o);
	if (v){
		if (isNaN(parseInt(v))||v.substr(0,1)=="-"){
			return BaseAlert(o, "限宽模式宽度：值无效，如果不启用，请留空！");
		}
	}

	o = f.d_skin;
	v = trimObj(o);
	if (!v){
		return BaseAlert(o, "界面皮肤目录名：不能为空！");
	}else{
		re = new RegExp("[^a-zA-Z0-9_\-]+","gi");
		if (re.test(v)){
			return BaseAlert(o, "界面皮肤目录名：只能由英文字母、下划线、中划线组成！");
		}
	}

	o = f.d_width;
	v = trimObj(o);
	if (isNaN(parseInt(v))){
		return BaseAlert(o, "最佳引用宽度：不能为空，且必须是数字！");
	}

	o = f.d_height;
	v = trimObj(o);
	if (isNaN(parseInt(v))){
		return BaseAlert(o, "最佳引用高度：不能为空，且必须是数字！");
	}

	o = f.d_memo;
	v = trimObj(o);
	if (v.indexOf("|")>0){
		return BaseAlert(o, "备注说明：不能含有特殊字符“|”！");
	}


	//上传相关
	o = f.d_uploaddir;
	v = trimObj(o);
	if (!v || hasSpecialChar(v)){
		return BaseAlert(o, "上传路径：不能为空，且不能包含特殊字符！");
	}

	switch(f.d_baseurl.value){
	case "0":
		v = v.replace(/\\/g, "/");
		if (v.substr(v.length-1)!="/"){
			v += "/";
		}
		o.value = v;

		o = f.d_basehref;
		v = trimObj(o);
		if (!v || v.substr(0,1)!="/" || hasSpecialChar(v)){
			return BaseAlert(o, "显示路径：当使用相对路径模式时，不能为空，且不能包含特殊字符，且必须以“/”开头！");
		}
		v = v.replace(/\\/g, "/");
		if (v.substr(v.length-1)!="/"){
			v += "/";
		}
		o.value = v;

		o = f.d_contentpath;
		v = trimObj(o);
		if (!v || v.substr(0,1)=="/" || hasSpecialChar(v)){
			return BaseAlert(o, "内容路径：当使用相对路径模式时，不能为空，且不能包含特殊字符，且不能以“/”开头！");
		}
		v = v.replace(/\\/g, "/");
		if (v.substr(v.length-1)!="/"){
			v += "/";
		}
		o.value = v;

		break;
	case "1":
	case "2":
		f.d_basehref.value = "";
		f.d_contentpath.value = "";
		break;
	case "3":
		var s = v.substr(v.length-1);
		if ((s!="/") && (s!="\\")){
			if (v.indexOf("/")>0){
				v+="/";
			}else{
				v+="\\";
			}
			o.value=v;
		}

		f.d_basehref.value = "";

		o = f.d_contentpath;
		v = trimObj(o);
		if (!v || v.substr(0,4).toLowerCase()!="http" || hasSpecialChar(v)){
			return BaseAlert(o, "内容路径：当使用站外绝对全路径模式时，不能为空，且不能包含特殊字符，且必须以“http”开头！");
		}
		v = v.replace(/\\/g, "/");
		if (v.substr(v.length-1)!="/"){
			v += "/";
		}
		o.value = v;

		break;
	}


	// 上传文件类型及大小

	o = f.d_imageext;
	v = trimObj(o);
	if (!isValidExtSet(v)){
		return BaseAlert(o, "图片类型：不能为空，且格式为“ext1|ext2”！");
	}

	o = f.d_imagesize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "图片限制：不能为空，且必须是数字！");
	}

	o = f.d_flashext;
	v = trimObj(o);
	if (!isValidExtSet(v)){
		return BaseAlert(o, "Flash类型：不能为空，且格式为“ext1|ext2”！");
	}

	o = f.d_flashsize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "Flash限制：不能为空，且必须是数字！");
	}

	o = f.d_mediaext;
	v = trimObj(o);
	if (!isValidExtSet(v)){
		return BaseAlert(o, "媒体类型：不能为空，且格式为“ext1|ext2”！");
	}

	o = f.d_mediasize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "媒体限制：不能为空，且必须是数字！");
	}

	o = f.d_fileext;
	v = trimObj(o);
	if (!isValidExtSet(v)){
		return BaseAlert(o, "附件类型：不能为空，且格式为“ext1|ext2”！");
	}

	o = f.d_filesize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "附件限制：不能为空，且必须是数字！");
	}

	o = f.d_remoteext;
	v = trimObj(o);
	if (!isValidExtSet(v)){
		return BaseAlert(o, "远程文件类型：不能为空，且格式为“ext1|ext2”！");
	}

	o = f.d_remotesize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "远程文件限制：不能为空，且必须是数字！");
	}

	o = f.d_localext;
	v = trimObj(o);
	if (!isValidExtSet(v)){
		return BaseAlert(o, "本地文件类型：不能为空，且格式为“ext1|ext2”！");
	}

	o = f.d_localsize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "本地文件限制：不能为空，且必须是数字！");
	}


	// 缩略图及水印相关
	o = f.d_sltsyext;
	v = trimObj(o);
	if (!isValidExtSet(v)){
		return BaseAlert(o, "处理图形扩展名：不能为空，且格式为“ext1|ext2”！");
	}

	o = f.d_sltminsize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "缩略图使用最小长度条件：不能为空，且必须是数字！");
	}

	o = f.d_sltoksize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "缩略图生成长度：不能为空，且必须是数字！");
	}

	o = f.d_sywzminwidth;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "文字水印启用的最小宽度条件：不能为空，且必须是数字！");
	}

	o = f.d_sywzminheight;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "文字水印启用的最小高度条件：不能为空，且必须是数字！");
	}

	o = f.d_sytext;
	v = trimObj(o);
	if ((!v) || (v.indexOf("|")>0)){
		return BaseAlert(o, "水印文字内容：不能为空，且不能含有特殊字符“|”！");
	}

	o = f.d_syfontcolor;
	v = trimObj(o);
	if (!isValidColor(v)){
		return BaseAlert(o, "文字水印字体颜色：必须是16进制颜色代码，6位长度，如黑色：“000000”！");
	}

	o = f.d_syshadowcolor;
	v = trimObj(o);
	if (!isValidColor(v)){
		return BaseAlert(o, "文字水印阴影颜色：必须是16进制颜色代码，6位长度，如白色：“FFFFFF”！");
	}

	o = f.d_syshadowoffset;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "文字水印阴影大小：不能为空，且必须是数字！");
	}

	o = f.d_syfontsize;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "文字水印字体大小：不能为空，且必须是数字！");
	}

	o = f.d_syfontname;
	v = trimObj(o);
	if ((!v) || (v.indexOf("|")>0)){
		return BaseAlert(o, "文字水印字体名称：不能为空，且不能含有特殊字符“|”！");
	}

	o = f.d_sywzpaddingh;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "文字水印左右边距：不能为空，且必须是数字！");
	}

	o = f.d_sywzpaddingv;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "文字水印上下边距：不能为空，且必须是数字！");
	}

	o = f.d_sywztextwidth;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "文字水印文字宽占位：不能为空，且必须是数字！");
	}

	o = f.d_sywztextheight;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "文字水印文字高占位：不能为空，且必须是数字！");
	}

	o = f.d_sytpminwidth;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "图片水印启用的最小宽度条件：不能为空，且必须是数字！");
	}

	o = f.d_sytpminheight;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "图片水印启用的最小高度条件：不能为空，且必须是数字！");
	}

	o = f.d_sytppaddingh;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "图片水印左右边距：不能为空，且必须是数字！");
	}

	o = f.d_sytppaddingv;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "图片水印上下边距：不能为空，且必须是数字！");
	}




	o = f.d_sypicpath;
	v = trimObj(o);
	if (v){
		if (hasSpecialChar(v)){
			return BaseAlert(o, "图片水印图片路径：不能包含特殊字符！");
		}
	}


	o = f.d_sytpopacity;
	v = trimObj(o);
	if (isNotFloat(v) || parseFloat(v)>1 || parseFloat(v)<0){
		return BaseAlert(o, "图片水印上下边距：不能为空，且必须是0至1之间的数字！");
	}

	o = f.d_sytpimagewidth;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "图片水印文字宽占位：不能为空，且必须是数字！");
	}

	o = f.d_sytpimageheight;
	v = trimObj(o);
	if (isNotInt(v)){
		return BaseAlert(o, "图片水印文字高占位：不能为空，且必须是数字！");
	}

	return true;
}

function trimObj(obj){
	obj.value = BaseTrim(obj.value);
	return obj.value;
}

function hasSpecialChar(str){
	var re = /[\' 　\&\<\>\?\%\,\;\(\)\`\~\!\@\#\$\^\*\{\}\[\]\|\"\t\n]+/gi;
	return re.test(str);
}

function isValidExtSet(str){
	if (str==""){
		return false;
	}
	if (str.substr(0,1)=="|"){
		return false;
	}
	if (str.substr(str.length-1)=="|"){
		return false;
	}
	if (str.indexOf("||")>0){
		return false;
	}
	return true;
}

function isValidColor(str){
	if (str.length!=6){
		return false;
	}
	var re = new RegExp("[A-Fa-f0-9]{6}", "gi");
	return re.test(str);
}


function Add() {
	var sel = "";
	var els = div1.getElementsByTagName("DIV");
	for (var i=0; i<els.length; i++){
		if (els[i].className=="Node2"){
			sel += "|" + els[i].getAttribute("code",2);
		}
	}
	if (sel==""){
		alert("请选择一个待选按钮！");
		return;
	}

	var v = document.myform.d_button.value;
	if (v){
		v = v + sel;
	}else{
		v = sel.substr(1);
	}
	document.myform.d_button.value = v;
	reloadSelectedButtons();
	div2.scrollTop = div2.scrollHeight-div2.clientHeight;
}

function Del() {
	var sel = "";
	var els = div2.getElementsByTagName("DIV");
	for (var i=0; i<els.length; i++){
		if (els[i].className=="Node2"){
			sel += "|" + els[i].getAttribute("id",2).substr(5);
		}
	}
	if (sel==""){
		alert("请选择一个已选按钮！");
		return;
	}

	sel = sel.substr(1);
	var a1 = sel.split("|");
	var a2 = a1.reverse();
	var s_btn = document.myform.d_button.value;
	var b = s_btn.split("|");
	for (var i=0; i<a2.length; i++){
		b.splice(parseInt(a2[i]), 1);
	}

	document.myform.d_button.value = b.join("|");
	var t = div2.scrollTop;
	reloadSelectedButtons();
	div2.scrollTop = t;

}

function Up() {
	var sel = "";
	var els = div2.getElementsByTagName("DIV");
	for (var i=0; i<els.length; i++){
		if (els[i].className=="Node2"){
			sel += "|" + els[i].getAttribute("id",2).substr(5);
		}
	}
	if (sel==""){
		alert("请至少选择一个要移动的已选按钮！");
		return;
	}

	sel = sel.substr(1);
	var a = sel.split("|");
	if (a[0]=="0"){
		alert("选定按钮已在最顶，不能再往上移！");
		return;
	}

	var s_btn = document.myform.d_button.value;
	var b = s_btn.split("|");
	var j,s;
	for (var i=0; i<a.length; i++){
		j = parseInt(a[i]);
		s = b[j];
		b[j] = b[j-1];
		b[j-1] = s;
	}
	
	document.myform.d_button.value = b.join("|");
	var t = div2.scrollTop;
	reloadSelectedButtons();
	div2.scrollTop = t;

	for (var i=0; i<a.length; i++){
		j = (parseInt(a[i])-1);
		var e = document.getElementById("div2_" + j);
		e.className = "Node2";
	}
}

function Down() {
	var sel = "";
	var els = div2.getElementsByTagName("DIV");
	for (var i=0; i<els.length; i++){
		if (els[i].className=="Node2"){
			sel += "|" + els[i].getAttribute("id",2).substr(5);
		}
	}
	if (sel==""){
		alert("请至少选择一个要移动的已选按钮！");
		return;
	}

	sel = sel.substr(1);
	var a1 = sel.split("|");
	var a = a1.reverse();
	var s_btn = document.myform.d_button.value;
	var b = s_btn.split("|");

	if (parseInt(a[0])==(b.length-1)){
		alert("选定按钮已在最底，不能再往下移！");
		return;
	}

	var j,s;
	for (var i=0; i<a.length; i++){
		j = parseInt(a[i]);
		s = b[j];
		b[j] = b[j+1];
		b[j+1] = s;
	}
	
	document.myform.d_button.value = b.join("|");
	var t = div2.scrollTop;
	reloadSelectedButtons();
	div2.scrollTop = t;

	for (var i=0; i<a.length; i++){
		j = (parseInt(a[i])+1);
		var e = document.getElementById("div2_" + j);
		e.className = "Node2";
	}

}




var divLastIndex = {"div1":-1, "div2":-1};
function doClickNode(el){
	var s_ID = el.getAttribute("id",2);
	var s_PID = s_ID.substr(0,4);
	var n_SelIndex = parseInt(s_ID.substr(5));
	var el_P = document.getElementById(s_PID);

	if (event.shiftKey){
		if (divLastIndex[s_PID]==-1){
			el.className = "Node2";
		}else{
			var n_Max, n_Min;
			if (divLastIndex[s_PID]>n_SelIndex){
				n_Max = divLastIndex[s_PID];
				n_Min = n_SelIndex;
			}else{
				n_Max = n_SelIndex;
				n_Min = divLastIndex[s_PID];
			}
			for (var i=n_Min; i<=n_Max; i++){
				var e = document.getElementById(s_PID+"_"+i);
				e.className = "Node2";
			}
		}
	}else if(event.ctrlKey){
		if (el.className=="Node2"){
			el.className = "Node1";
		}else{
			el.className = "Node2";
		}
	}else{
		var els = el_P.getElementsByTagName("DIV");
		for (var i=0; i<els.length; i++){
			if (els[i].className=="Node2"){
				els[i].className = "Node1";
			}
		}
		el.className = "Node2";
	}

	divLastIndex[s_PID] = n_SelIndex;
}

function doDblClickNode(el){
	var s_ID = el.getAttribute("id",2);
	var s_PID = s_ID.substr(0,4);
	if (s_PID=="div1"){
		Add();
	}else{
		Del();
	}
}


var sSkin = "";
function initButtonOptions(s_Skin){
	sSkin = s_Skin;
	var html1 = "";
	var s_Key = "";
	var i=0;
	for (s_Key in Buttons){
		html1 += getBtnImgHTML(s_Key, "div1_"+i);
		i++;
	}
	div1.innerHTML = html1;

	reloadSelectedButtons();
}

function reloadSelectedButtons(){
	var html2 = "";
	var s_SelBtn = document.myform.d_button.value;
	a_Btns = s_SelBtn.split("|");
	for (var i=0; i<a_Btns.length; i++){
		if (a_Btns[i]!=""){
			html2 += getBtnImgHTML(a_Btns[i], "div2_"+i);
		}
	}
	div2.innerHTML = html2;
}

function getBtnImgHTML(s_Code, s_ID){
	var a_Btn = Buttons[s_Code];
	var html = "<div id='"+s_ID+"' class='Node1' code='"+s_Code+"' onclick='doClickNode(this)' ondblclick='doDblClickNode(this)'><table border=0 cellpadding=0 cellspacing=1 width='100%' height=20><tr>";
	if (a_Btn[3]==0){
		html += "<td width=20 align=center unselectable=on>";
		if (typeof(a_Btn[0])=="number"){
			var s_Img = "../skin/" + sSkin + "/buttons.gif";
			var n_Top = 16-a_Btn[0]*16;
			html += "<div class='TB_Btn_Image'><img src='"+s_Img+"' style='top:"+n_Top+"px'></div>";
		}else{
			var s_Img = "../skin/" + sSkin + "/" + a_Btn[0];
			html += "<img class='TB_Btn_Image' src='"+s_Img+"'>";
		}
		html += "</td><td width='*' unselectable=on>" + lang[s_Code] + "</td>";
	}else if (a_Btn[3]==1){
		html += "<td width=20 align=center unselectable=on>-</td><td width='*' unselectable=on>下拉框："+lang[s_Code]+"</td>";
	}else{
		var s_Desc = "";
		switch(s_Code){
		case "TBSep":
			s_Desc = "分隔线";
			break;
		case "TBHandle":
			s_Desc = "工具栏头";
			break;
		case "Space":
			s_Desc = "空格";
			break;
		}
		html += "<td width=20 align=center unselectable=on>-</td><td width='*' unselectable=on>"+s_Desc+"</td>";
	}
	html += "</tr></table></div>";
	return html;
}


function checkModipwdForm() {
	var obj;
	obj=document.myform.newusr;
	obj.value=BaseTrim(obj.value);
	if (obj.value=="") {
		BaseAlert(obj, "新用户名不能为空！");
		return false;
	}
	obj=document.myform.newpwd1;
	obj.value=BaseTrim(obj.value);
	if (obj.value=="") {
		BaseAlert(obj, "新密码不能为空！");
		return false;
	}
	if (document.myform.newpwd1.value!=document.myform.newpwd2.value){
		BaseAlert(document.myform.newpwd1, "新密码和确认密码不相同！");
		return false;
	}
	return true;
}

function submitLicense(){
	var f=document.formLicense;
	f.d_url.value=location.href;
	f.submit();
}

var lang = new Object();
