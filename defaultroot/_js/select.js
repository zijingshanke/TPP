////////////////////////////////////////////
// 包含对select元素的操作
//
if(typeof(js) == "undefined")
    js = function(){ alert("js") }

js.select = function(){ alert("select.js"); }

function _get_select(elmId){
    var elm = elmId;
    if(typeof(elm) == 'string'){
        elm = document.getElementById(elmId);
    }
    if(!elm){
        alert("missing element :: "+elmId);
        return;
    }
    if(elm.tagName != 'SELECT'){
        alert("not select element :: "+ elmId);
    }
    return elm;
}

function _ary_contains(ary, value){
    if(!ary)
        return false;
    var i,n=ary.length;
    for(i=0;i<n;i++){
        if(ary[i] == value)
            return true;
    }
    return false;
}

js.select.contains = function(elmId, value){
    var elm = _get_select(elmId);
    var opts = elm.options;
    var i,n=opts.length;
    for(i=0;i<n;i++){
        if(opts[i].value == value)
            return true;
    }
    return false;
}

function _select_get(elmId, value){
    var elm = _get_select(elmId);
    var opts = elm.options;
    var i,n=opts.length;
    for(i=0;i<n;i++){
        if(opts[i].value == value)
            return opt[i];
    }
    return null;	
}

function _select_add(elm, value,text){
    var o = _select_get(elm,value);
    if(o)
    	return o;
    var opts = elm.options;
    var opt = new Option(text,value);
    opts[opts.length] = opt;
    return opt;
}

function _select_clone(opt){
		var extOpt = new Option(opt.text,opt.value);
		extOpt.setAttribute("fieldName",opt.getAttribute("fieldName"));
		extOpt.setAttribute("op",opt.getAttribute("op"));
		return extOpt;
}

///////////////////////////////////////////////////
//将fromList中的所有选择项选择到toList中
//
// 函数说明: moveAll(源列表的id, 目的列表的id)
//
js.select.moveAll = function(fromList,toList){	
	var allfrom = _get_select(fromList);
	var allto = _get_select(toList);

	var fromOption = allfrom.options;
	var toOption = allto.options;
	
	if(allfrom.tagName.toLowerCase() != 'select')
		alert("copy from items which is not a select");

    var i;
	for(i=0;i<fromOption.length;i++){
		if(fromOption[i].text!=null){
			toOption[toOption.length] = _select_clone(fromOption[i]);
			//var text=fromOption[i].text;
			//var value=fromOption[i].value;
       //     _select_add(allto,value,text);
		}
	}
	while(fromOption.length>0)
		fromOption[0]=null;
}

///////////////////////////////////////////////////
//将fromList中的选中的选项选择到toList中
//
// 函数说明: moveSelected(源列表的id, 目的列表的id)
//
js.select.moveSelected = function(fromList,toList){	
	var allfrom = _get_select(fromList);
	var allto = _get_select(toList);

    var fromOption = allfrom.options;
	var toOption = allto.options;

    var i;
    for(i=0;i<fromOption.length;i++){
        if(fromOption[i].text!=null){
            if(fromOption[i].selected){
            	  toOption[toOption.length] = _select_clone(fromOption[i]);
                //var text=fromOption[i].text;
                //var value=fromOption[i].value;
                //_select_add(allto,value,text);
                fromOption[i]=null;
                i --;
            }
        }
    }
}


js.select.bind = function(elmId,allData,selected){
	var elm = _get_select(elmId);

    var opts = elm.options;
    var i,n=allData.length;
    for(i=0;i<n;i++){
        var item = allData[i];
        var value,text;
        value = item[0];
        text = item[1];

        if(js.select.contains(elm,value))
            continue;

        var opt = new Option(text,value);

        if(_ary_contains(selected,value))
            opt.selected = "selected";

        opts[opts.length] = opt;
    }
}

js.select.bindTwo = function(fromId,toId,allData,selected){
    var fromList = _get_select(fromId);
    var toList = _get_select(toId);
    var i,n=allData.length;
    for(i=0;i<n;i++){
        var item = allData[i];
        var value = item[0];
        var text = item[1];
        if(_ary_contains(selected,value)){
            _select_add(toList,value,text);
        }else{
            _select_add(fromList,value,text);
        }
    }
}

js.select.moveSelectedUp = function(elmId){
    var elm = _get_select(elmId);
    var opts = elm.options;
    var i,n=opts.length;

    // no need to move if only one item
    if(n <= 1)
        return;

    // skip selected items at the head
    for(i=0;i<n;i++){
        if(!opts[i].selected)
            break;
    }
    for(;i<n;i++){
        var opt = opts[i];
        if(opt.selected){
            // swap with the previous item
            var prev = opts[i-1];
            opts[i-1] = null;
            opts.add(prev,i);
        }
    }
}

js.select.moveSelectedDown = function(elmId){
    var elm = _get_select(elmId);
    var opts = elm.options;
    var i,n=opts.length;

    // no need to move if only one item
    if(n <= 1)
        return;

    // skip selected items at the head
    for(i=n-1;i>=0;i--){
        if(!opts[i].selected)
            break;
    }
    for(;i>=0;i--){
        var opt = opts[i];
        if(opt.selected){
            // swap with the next item
            var next = opts[i+1];
            opts[i+1] = null;
            opts.add(next,i);
        }
    }
}

js.select.removeSelected = function(elmId){
    var elm = _get_select(elmId);
    var opts = elm.options;

    var i,n=opts.length;
    for(i=0;i<n;i++){
        if(opts[i].selected){
            opts[i] = null;
            i --;
            n --;
        }
    }
}

js.select.add = function(elmId, value, text){
    var elm = _get_select(elmId);
    var opts = elm.options;
    opts[opts.length] = new Option(text,value);
}

js.select.sort = function(elmId){
    var elm = _get_select(elmId);
    var ary = elm.options;

    var temp;
    var exchange;
    var i,n=ary.length;
    for(i=0; i<n; i++) {
        exchange = false;
        for(var j=ary.length-2; j>=i; j--) {
            if(ary[j+1].text < ary[j].text) {
                temp = ary[j+1];
                ary[j+1] = null;
                ary.add(temp,j);
                exchange = true;
            }
        }
        if(!exchange) break;
    }
}

js.select.selectAll = function(elmId){
	var elm = _get_select(elmId);
	var opts = elm.options;
	var i,n=opts.length;
	var b = false;
	for(i=0;i<n;i++){
		opts[i].selected = true;
  }	
}

js.select.markSelected = function(elmId, value){
	var elm = _get_select(elmId);
	var opts = elm.options;
	var i,n=opts.length;
	var b = false;
	for(i=0;i<n;i++){
		if(opts[i].value == value){
			opts[i].selected = true;
			b = true;
		}
	}
	return b;
}

js.select.getSelectedValues = function(elmId){
	var elm = _get_select(elmId);
	var opts = elm.options;
	var i,n=opts.length;
  var ret = [];
	for(i=0;i<n;i++){
		if(opts[i].selected)
			ret[ret.length] = opts[i].value;
	}
	return ret;
}

js.select.getAllValues = function(elmId){
	var elm = _get_select(elmId);
	var opts = elm.options;
	var i,n=opts.length;
	var ret = [];
	for(i=0;i<n;i++){
		ret[ret.length] = opt[i].value;
	}
	return ret;
}

js.select.removeAll = function(elmId){
	var elm = _get_select(elmId);
  var opts = elm.options;

    var i,n=opts.length;
    for(i=0;i<n;i++){
       opts[i] = null;
       i --;
       n --;
    }	
}

js.select.remove = function(elmId, value){
    var elm = _get_select(elmId);

    var opts = elm.options;
    var i,n=opts.length;
    for(i=0;i<n;i++){
        var opt = opts[i];
        if(opt.value == value){
            opts[i] = null;
            i --;
            n --;
        }
    }
}

js.select.removeContained = function(elmId, extElmId){
    var elm = _get_select(elmId);
    var extElm = _get_select(extElmId);

    var opts = elm.options;
    var i,n=opts.length;
    for(i=0;i<n;i++){
        var opt = opts[i];
        if(js.select.contains(extElm,opt.value)){
            opts[i] = null;
            i --;
            n --;
        }
    }
}

js.select.addNotContained = function(elmId, extElmId){
    var elm = _get_select(elmId);
    var extElm = _get_select(extElmId);

    var opts = extElm.options;
    var i,n=opts.length;
    for(i=0;i<n;i++){
        var opt = opts[i];
        if(js.select.contains(elm,opt.value)){
            continue;
        }
        // in Firefox cloneNode(false) does not really add node
        // in IE cloneNode(true) throw error, while cloneNode(false) does not really add node
        elm.options[elm.options.length] = _select_clone(opt);
    }
}