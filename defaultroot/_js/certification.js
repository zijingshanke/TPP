 var SSLValid = Class.create();

    SSLValid.prototype = {

      initialize: function() {        		
       },

      setEmail:function(email)
      {
      	 this.email=email;
      },
      valid:function()
      { 
      
      if(this.email.length>0 && this.email.indexOf('@')>0)
	  {
	alert("valid.............https://qm.qmpay.com/security/certificate.do?thisAction=validAgent&email="+this.email);
		var myAjax = new Ajax.Request("https://qm.qmpay.com/security/certificate.do?thisAction=validAgent&email="+this.email,
		{ 
			thisAction:"get", 
			onComplete:function (originalRequest)
			{
			  var result = originalRequest.responseText;
			  if(result==1)
			  {
				return true;
		      }
			  else
			  {
				return false;	
		      }
		    }, 
		    onException:function(originalRequest, ex)
	        {
	          alert("Exception:" + ex.message);
	          return  ex.message;	
	        }
	    }
	    );
	 }   	
     }
    };
    
  
  
 
