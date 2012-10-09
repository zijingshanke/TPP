 var Category = Class.create();

    Category.prototype = {

      initialize: function() {
      	var resXmlDoc;
       },
   
      removeAll:function(obj){
      	
        while(obj.hasChildNodes())
        {
          obj.removeChild(obj.firstChild);
        }
        opt=document.createElement("OPTION");
        opt.value="";
        txt=document.createTextNode("...");
        opt.appendChild(txt);        
        obj.appendChild(opt);	      	
      },
      
      appendOptionBxId:function(obj,aId){
      	var count,countb,countc,countd,counte;
      	var tempObj;
        var tempObjb;
        while(obj.hasChildNodes())
        {
          obj.removeChild(obj.firstChild);
        }
        opt=document.createElement("OPTION");
        opt.value="";
        txt=document.createTextNode("...");
        opt.appendChild(txt);        
        obj.appendChild(opt);
        
          count=this.resXmlDoc.childNodes.length;
          tempObj=this.resXmlDoc.childNodes;
         
          for(var i=0;i<count;i++)
          {
            if (tempObj[i].attributes[0].value==aId) 
            {
              tempObjb=tempObj[i]
              countb=tempObjb.childNodes.length;              
              for(var j=0;j<countb;j++)
              {
                id=tempObjb.childNodes[j].attributes[0].value;
		        code=tempObjb.childNodes[j].attributes[2].value;
    		    name=tempObjb.childNodes[j].attributes[3].value;
    		    opt=document.createElement("OPTION");
                opt.value=id;
		        txt=document.createTextNode(code+'--'+name);
		        opt.appendChild(txt);
		        obj.appendChild(opt);
              }
            }
          }

      },
        
        
      appendOptionCxId:function(obj,aId,bId){
      	var count,countb,countc,countd,counte;
      	var tempObj;
        var tempObjb;
        while(obj.hasChildNodes())
        {
          obj.removeChild(obj.firstChild);
        }
        opt=document.createElement("OPTION");
        opt.value="";
        txt=document.createTextNode("...");
        opt.appendChild(txt);        
        obj.appendChild(opt);

          count=this.resXmlDoc.childNodes.length;
          tempObj=this.resXmlDoc.childNodes;

          for(var i=0;i<count;i++)
          {
            if (tempObj[i].attributes[0].value==aId) 
            {
              tempObjb=tempObj[i].childNodes;
              countb=tempObjb.length;              
              for(var j=0;j<countb;j++)
              {
            
              	if (tempObjb[j].attributes[0].value==bId) 
                {
                  tempObjc=tempObjb[j];
                  countc=tempObjc.childNodes.length;            
                  for(var k=0;k<countc;k++)
	              {
	                id=tempObjc.childNodes[k].attributes[0].value;
			        code=tempObjc.childNodes[k].attributes[2].value;
	    		    name=tempObjc.childNodes[k].attributes[3].value;
	    		    opt=document.createElement("OPTION");
	                opt.value=id;
			        txt=document.createTextNode(code+'--'+name);
			        opt.appendChild(txt);
			        obj.appendChild(opt);
	              }
	            }
              }
            }
          }
      },  
      
      appendOptionDxId:function(obj,aId,bId,cId){
      	var count,countb,countc,countd,counte;
      	var tempObj;
        var tempObjb;
        while(obj.hasChildNodes())
        {
          obj.removeChild(obj.firstChild);
        }
        opt=document.createElement("OPTION");
        opt.value="";
        txt=document.createTextNode("...");
        opt.appendChild(txt);        
        obj.appendChild(opt);

          count=this.resXmlDoc.childNodes.length;
          tempObj=this.resXmlDoc.childNodes;

          for(var i=0;i<count;i++)
          {
            if (tempObj[i].attributes[0].value==aId) 
            {
              tempObjb=tempObj[i].childNodes;
              countb=tempObjb.length;              
              for(var j=0;j<countb;j++)
              {            
              	if (tempObjb[j].attributes[0].value==bId) 
                {
                  tempObjc=tempObjb[j];
                  countc=tempObjc.childNodes.length;            
                  for(var k=0;k<countc;k++)
	              {
                    if (tempObjc.childNodes[k].attributes[0].value==cId) 
                    {
                      tempObjd=tempObjc.childNodes[k];
	                  countd=tempObjd.childNodes.length; 
	                  for(var l=0;l<countd;l++)
		              {
		                id=tempObjd.childNodes[l].attributes[0].value;
				        code=tempObjd.childNodes[l].attributes[2].value;
		    		    name=tempObjd.childNodes[l].attributes[3].value;
		    		    opt=document.createElement("OPTION");
		                opt.value=id;
				        txt=document.createTextNode(code+'--'+name);
				        opt.appendChild(txt);
				        obj.appendChild(opt);
		              }
		            }
	              }
	            }
              }
            }
          }
      },  
      
      
       appendOptionExId:function(obj,aId,bId,cId,dId){
      	var count,countb,countc,countd,counte;
      	var tempObj;
        var tempObjb;
        while(obj.hasChildNodes())
        {
          obj.removeChild(obj.firstChild);
        }
        opt=document.createElement("OPTION");
        opt.value="";
        txt=document.createTextNode("...");
        opt.appendChild(txt);        
        obj.appendChild(opt);

          count=this.resXmlDoc.childNodes.length;
          tempObj=this.resXmlDoc.childNodes;

          for(var i=0;i<count;i++)
          {
            if (tempObj[i].attributes[0].value==aId) 
            {
              tempObjb=tempObj[i].childNodes;
              countb=tempObjb.length;              
              for(var j=0;j<countb;j++)
              {            
              	if (tempObjb[j].attributes[0].value==bId) 
                {
                  tempObjc=tempObjb[j];
                  countc=tempObjc.childNodes.length;            
                  for(var k=0;k<countc;k++)
	              {
                    if (tempObjc.childNodes[k].attributes[0].value==cId) 
                    {
                      tempObjd=tempObjc.childNodes[k];
	                  countd=tempObjd.childNodes.length; 
	                  for(var l=0;l<countd;l++)
		              {		    
	                   if (tempObjd.childNodes[l].attributes[0].value==dId) 
	                    {
	                      tempObje=tempObjd.childNodes[l];
		                  counte=tempObje.childNodes.length; 
		                  for(var m=0;m<counte;m++)
			              {
			                id=tempObje.childNodes[m].attributes[0].value;
					        code=tempObje.childNodes[m].attributes[2].value;
			    		    name=tempObje.childNodes[m].attributes[3].value;
			    		    opt=document.createElement("OPTION");
			                opt.value=id;
					        txt=document.createTextNode(code+'--'+name);
					        opt.appendChild(txt);
					        obj.appendChild(opt);
			              }
			            }
		              }
		            }
	              }
	            }
              }
            }
          }
      },  
     /* 
      appendOptionFxId:function(obj,aId,bId,cId,dId,eId){
      	alert('eId='+eId);
      	var count,countb,countc,countd,counte;
      	var tempObj;
        var tempObjb;
        while(obj.hasChildNodes())
        {
          obj.removeChild(obj.firstChild);
        }
        opt=document.createElement("OPTION");
        opt.value="";
        txt=document.createTextNode("...");
        opt.appendChild(txt);        
        obj.appendChild(opt);

          count=this.resXmlDoc.childNodes.length;
          tempObj=this.resXmlDoc.childNodes;

          for(var i=0;i<count;i++)
          {
            if (tempObj[i].attributes[0].value==aId) 
            {
              tempObjb=tempObj[i].childNodes;
              countb=tempObjb.length;              
              for(var j=0;j<countb;j++)
              {            
              	if (tempObjb[j].attributes[0].value==bId) 
                {
                  tempObjc=tempObjb[j];
                  countc=tempObjc.childNodes.length;            
                  for(var k=0;k<countc;k++)
	              {
                    if (tempObjc.childNodes[k].attributes[0].value==cId) 
                    {
                      tempObjd=tempObjc.childNodes[k];
	                  countd=tempObjd.childNodes.length; 
	                  for(var l=0;l<countd;l++)
		              {		                         
	                    if (tempObjd.attributes[0].value==dId) 
	                    {
	                    	alert("0000000000000000000000000000000000");	
	                      tempObje=tempObjd.childNodes[l];
		                  counte=tempObje.childNodes.length; 
		                  
		                  for(var m=0;m<counte;m++)
			              {		
			              	alert("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");	 
			              	            alert("tempObje.childNodes[m].attributes[0].value="+tempObje.childNodes[m].attributes[0].value); 				              	
                           if (tempObje.childNodes[m].attributes[0].value==eId) 
		                   {
		                    	alert("aaaaaaaa");
		                      tempObjf=tempObje.childNodes[m];
			                  countf=tempObjf.childNodes.length; 
			                  	 alert('counte='+counte);
			                  for(var n=0;n<countf;n++)
				              {
				              	 alert('n='+n);	
				                id=tempObjfe.childNodes[n].attributes[0].value;
						        code=tempObjf.childNodes[n].attributes[2].value;
				    		    name=tempObjf.childNodes[n].attributes[3].value;
				    		    opt=document.createElement("OPTION");
				                opt.value=id;
						        txt=document.createTextNode(code+'--'+name);
						        opt.appendChild(txt);
						        obj.appendChild(opt);
				              }
				            }
			              }
			            }
		              }
		            }
	              }
	            }
              }
            }
          }
      },  
      
      */
     load:function(){ 
       var doc = new ActiveXObject("Microsoft.XMLDOM");           
       doc.async=false;
       doc.load("../_xml/category.xml");
       this.resXmlDoc=doc.documentElement;  
     } 

    };
     