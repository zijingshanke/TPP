 var MyWindow = Class.create();

    MyWindow.prototype = {

      initialize: function(_width,_height,_url) {  
     
      		
       },

      open:function(_width,_height,_url){ 
       var height=_height;
      	 var width=_width; 
         var url=_url;      
        left = (screen.width-width)/2;
        top = (screen.height-height)/2;
        window.open(url,'_blank','height='+height+',width='+width+',left='+left+',top='+top+',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=yes'); 
   	 } 

    };
    
  