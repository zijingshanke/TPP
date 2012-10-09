if(window.Event && navigator.userAgent.indexOf("Opera") == -1){
 window.constructor.prototype.__defineGetter__("event", function(){
  var o = arguments.callee;
  while((o = o.caller) != null){
   var e = o.arguments[0];
   if(e && (e.constructor == Event || e.constructor == MouseEvent)) return e;
  }
  return null;
 });
 Event.prototype.__defineGetter__("srcElement",function(){
  var node=this.target;
  while(node.nodeType!=1)node=node.parentNode;
  return node;
 });
 Event.prototype.__defineGetter__("offsetX",function(){
  return this.layerX;
 });
 Event.prototype.__defineGetter__("offsetY",function(){
  return this.layerY;
 });
}
if(window.HTMLElement && navigator.userAgent.indexOf("Opera") == -1){
  HTMLElement.prototype.insertAdjacentElement=function(where,parsedNode){
    switch(where.toLowerCase()){
      case "beforebegin":
        this.parentNode.insertBefore(parsedNode,this);
        break;
      case "afterbegin":
        this.insertBefore(parsedNode,this.firstChild);
        break;
      case "beforeend":
        this.appendChild(parsedNode);
        break;
      case "afterend":
        if(this.nextSibling)
          this.parentNode.insertBefore(parsedNode,this.nextSibling);
        else
          this.parentNode.appendChild(parsedNode);
        break;
      }
    }
  HTMLElement.prototype.insertAdjacentHTML=function(where,htmlStr){
    var r=this.ownerDocument.createRange();
    r.setStartBefore(this);
    var parsedHTML=r.createContextualFragment(htmlStr);
    this.insertAdjacentElement(where,parsedHTML);
    }
}
var Dialog = {
 Instances : 0,
 IDialog : function (){
  this.Width = 400;
  this.Height = 280;
  this.Left = 0;
  this.Top = 0;
  this.Right = 0;
  this.Bottom = 0;
  this.Window = "Dialog";
  this.Title = "Dialog";
  this.Content = "";
  this.IsModal = true;
  this.Moveable = true;
  this.Buttons = "<input type=\"button\" value=\"确定\" onclick=\"Dialog.Event.Close()\" \/> <input type=\"button\" value=\"取消\" onclick=\"Dialog.Event.Close()\" \/>";
  this.Render = function(){
   var shtml = "<div id=\"DialogWindow\" style=\"position:absolute;left:" + this.Left + "px;top:" + this.Top + "px;width:" + this.Width + "px;height:" + this.Height + "px;z-index:10000;\" oncontextmenu=\"return false;\">" +"\n"+
 " <div style=\"background:green;height:20px;padding:2px;line-height:20px;font-weight:bold;cursor:move;color:#fff\" onselectstart=\"return false;\" onmousedown=\"Dialog.Event.MouseDown()\" onmousemove=\"Dialog.Event.MouseMove()\" onmouseup=\"Dialog.Event.MouseUp()\">" +"\n"+
 "  <span style=\"float:right;border:1px solid #fff;font:9px verdana;width:12px;height:12px;text-align:center;margin:3px;cursor:default\" onmouseover=\"this.style.borderColor=\'black\';this.style.color=\'black\'\" onmouseout=\"this.style.borderColor=\'white\';this.style.color=\'white\'\" onclick=\"Dialog.Event.Close()\">X<\/span>" +"\n"+
 "  <span style=\"width:" + (this.Width-12) + "px;\">" + this.Title + "<\/span>" +"\n"+
 " <\/div>" +"\n"+
 " <div style=\"border:1px solid #dedede;background:white;height:" + (this.Height - 22) + "px;padding:2px;cursor:move\">" +"\n"+
 "  <div style=\"width:100%;height:100%;margin:0 1px 1px;cursor:default\">" + this.Content + "<\/div>" +"\n"+
 " <\/div>" +"\n"+
 " <div style=\"text-align:center;background-color:#fff\">" +this.Buttons + "<\/div>" +"\n"+
 "<\/div>";
   document.body.insertAdjacentHTML("BeforeEnd", shtml);
  }
  this.Open = function(){
   if(arguments > 0){
    this.Parse(arguments[0]);
   }
   if(arguments > 1 && typeof(arguments[1]) == "string") this.Window = arguments[1];
  }
  this.Close = function(){
  }
  this.Parse = function(featrues){
   if(features){
    if(typeof(featrues) == "string" && /:=,/g.test(featrues)){
     var params = featrues.split(/,\s*/);
     var len = params.length;
     var nameValue;
     for(var i=0;i<len;i++){
      nameValue = params[i].split(/\s*[:=]\s*/);
      if(nameValue.length != 2 || nameValue[0].length < 3)continue;
      nameValue[0] = nameValue[0].substr(0, 1).toUpperCase() + nameValue[0].substr(1).toLowerCase();
      this[nameValue[0]] = nameValue[1];
     }
    } else if(typeof(featrues) == "object"){
     var e;
     for(e in featrues){
      if(e.length > 2) this[e[0].substr(0, 1).toUpperCase() + e.substr(1).toLowerCase()] = featrues[e];
     }
    }
    
   }
  }
  this.Mask = function(){
   if(!document.getElementById("__Dialog__Mask_Layer")){
    var mask = document.createElement("div");
    with(mask){
     id = "__Dialog__Mask_Layer";
     with(style){
      position = "absolute";
      left = "0px";
      top = "0px";
      width = "100%";
      height = (document.all&&navigator.appVersion.replace(/^.+?MSIE\s+(\d).+$/, "$1") == "6")?Dialog.Util.Document().clientHeight:"100%";
      background = "green";
      if(document.all && navigator.userAgent.indexOf("Opera") == -1) filter = "alpha(opacity=20)";
      else if(navigator.userAgent.indexOf("Opera") == -1) ōpacity = ".2";
      else background = "transparent url(alpha80.png) left top repeat";
      zIndex = "9999";
     }
     oncontextmenu = function(){return false;};
    }
    document.body.appendChild(mask);
   }
  }
  this.toString = function(){
   return "[Object Dialog]";
  }
  Dialog.IDialog.prototype.Show = function(){
   if(this.IsModal) this.Mask();
   document.body.style.overflow = "hidden";
   document.documentElement.style.overflow = "hidden";
   this.Left = (parseInt(Dialog.Util.Document().clientWidth) - this.Width)/2;
   this.Top = (parseInt(this.IsModal?document.getElementById("__Dialog__Mask_Layer").clientHeight:Dialog.Util.Document().clientHeight) - this.Height)/2;
   this.Render();
  }
  Dialog.IDialog.prototype.Move = function(){//for override test
   this.Left +=10;
  }
 },
 ModalDialog : function(){
  Dialog.IDialog.apply(this, arguments);
  this.Move = function(){//for override test
   Dialog.IDialog.prototype.Move.apply(this, arguments);
   this.Left ++;
  }
  this.Show = function(){
   Dialog.IDialog.prototype.Show.apply(this, arguments);
  }
  this.toString = function(){
   return "[Object ModalDialog]";
  }
 },
 ModelessDialog : function(){
  Dialog.IDialog.apply(this, arguments);
  this.IsModal = false;
  this.Show = function(){
   Dialog.IDialog.prototype.Show.apply(this, arguments);
  }
  this.toString = function(){
   return "[Object ModelessDialog]";
  }
 },
 Alert : function(){
  Dialog.IDialog.apply(this, arguments);
  this.Title = "消息提示框";
  this.Buttons = "<input type=\"button\" value=\"确定\" onclick=\"Dialog.Event.Close()\" \/>";
  this.Show = function(){
   Dialog.IDialog.prototype.Show.apply(this, arguments);
  }
  this.toString = function(){
   return "[Object AlertDialog]";
  }
 },
 Confirm : function(){
  Dialog.IDialog.apply(this, arguments);
  this.Title = "消息确认框";
  this.Show = function(){
   Dialog.IDialog.prototype.Show.apply(this, arguments);
  }
  this.toString = function(){
   return "[Object ConfirmDialog]";
  }
 },
 Prompt : function(){
  Dialog.IDialog.apply(this, arguments);
  this.Height = 80;
  this.Title = "消息输入框";
  this.Content = "请输入×××：<br /><input type='text' id='__DialogWindow__Confirm__' value='abc' style='width:98%' />";
  this.Show = function(){
   Dialog.IDialog.prototype.Show.apply(this, arguments);
  }
  this.toString = function(){
   return "[Object PromptDialog]";
  }
 },
 Event : {
  offsetX : 0,
  offsetY : 0,
  isMouseDown : false,
  MouseDown : function(){
   Dialog.Event.isMouseDown = true;
   Dialog.Event.offsetX = event.offsetX;
   Dialog.Event.offsetY = event.offsetY;
   window.status = [event.offsetX,event.offsetY];
   Dialog.Util.SetCapture(event.srcElement);
  },
  MouseMove : function(){
   if(Dialog.Event.isMouseDown == true){
    var o = Dialog.Util.CaptureObject(event.srcElement);
    if(o == document.getElementById("__Dialog__Mask_Layer"))return false;
    with(o.style){
     try{
      left = (parseInt(event.clientX?event.clientX:event.pageX) - parseInt(Dialog.Event.offsetX)) + "px";
      top = (parseInt(event.clientY?event.clientY:event.pageY) - parseInt(Dialog.Event.offsetY)) + "px";
     }catch(e){}
    }
   }
  },
  MouseUp : function(){
   Dialog.Event.isMouseDown = false;
   Dialog.Util.ReleaseCapture(event.srcElement);
  },
  Close : function(){
   document.getElementById("__Dialog__Mask_Layer").style.display = "none";
   document.getElementById("DialogWindow").style.display = "none";
   document.body.style.overflow = "auto";
   document.documentElement.style.overflow = "auto";
  },
  Click : function(){
  }
 },
 Util : {
  CaptureObject : function(o){
   //while(o.parentNode && o.tagName.toLowerCase() != "body") o = o.parentNode;
   return document.getElementById("DialogWindow");
  },
  SetCapture : function(o){
   if(o.setCapture) o.setCapture();
   else if(o.captureEvents)o.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
  },
  ReleaseCapture : function(o){
   if(o.releaseCapture) o.releaseCapture();
   else if(o.releaseEvents)o.releaseEvents(Event.MOUSEMOVE|Event.MOUSEUP);
  },
  Document : function(){
   return document.documentElement.clientHeight > 0?document.documentElement : document.body;
  }
 },
 toString : function(){return "[Object Dialog]\nby wfsr@msn.com";}
}