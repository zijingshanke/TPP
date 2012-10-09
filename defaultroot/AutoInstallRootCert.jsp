<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
	<head>

		<title>Auto Install Root Cert</title>

	</head>

	<body>
		<!--  AutoInstallRootCert  -->
		<p>
			Auto Install Root Cert
		</p>

		<object id="XEnroll"
			classid="clsid:127698e4-e730-4e5c-a2b1-21490a70c8a1"
			codebase="xenroll.dll"></object>
		<OBJECT id="OBJ1" height="0" width="0"
			classid="clsid:B524677D-10C3-4B77-A049-90B1264520A0"
			codeBase="cspex.cab#Version=1,0,3,1" VIEWASTEXT></OBJECT>
		<input type="button" name="installCert" value="安装证书"
			onclick="install_root();" />
	</body>
	<script language="javascript">   
	function install_root()           
	{   
        // pkcs7 1   
        var sPKCS7 ="-----BEGIN CERTIFICATE-----MIIC1TCCAj6gAwIBAgIESqcaczANBgkqhkiG9w0BAQUFADCBrjEPMA0GA1UEERMGNTE5MDAwMR4wHAYJKoZIhvcNAQkBFg9xbXBheUBxbXBheS5jb20xCzAJBgNVBAYTAkNOMRIwEAYDVQQIDAnlub/kuJznnIExDzANBgNVBAcMBuePoOa1tzEYMBYGA1UECwwP6ZKx6Zeo5oqA5pyv6YOoMQ8wDQYDVQQKDAbpkrHpl6gxHjAcBgNVBAMTFXd3dy5xbXBheS5jb20gQ0EgUm9vdDAeFw0wOTA5MDkwMzAxMDdaFw0yOTA5MDQwMzAxMDdaMIGuMQ8wDQYDVQQREwY1MTkwMDAxHjAcBgkqhkiG9w0BCQEWD3FtcGF5QHFtcGF5LmNvbTELMAkGA1UEBhMCQ04xEjAQBgNVBAgMCeW5v+S4nOecgTEPMA0GA1UEBwwG54+g5rW3MRgwFgYDVQQLDA/pkrHpl6jmioDmnK/pg6gxDzANBgNVBAoMBumSsemXqDEeMBwGA1UEAxMVd3d3LnFtcGF5LmNvbSBDQSBSb290MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI/lbxXu0n6Z9Y7XxbGhnix7H+cL9U+raxDd5C46h8fNDYAL/NTTLjEIKv2n+lgu2wg6TqO2Ko9MKkpfUanpqVBTIfyqRHptTpr1uxU97b12IF5QLRGbkRag0LbS22Zf3dMSS3DH8+vW1at5yJcNCE0kS4S5jpQ7FJ/UoUJZyscQIDAQABMA0GCSqGSIb3DQEBBQUAA4GBAHfn5TWbmMCVZu57Na1pRQN5OGOYP/sUGR2eGTYeh/1BkqP25NMANPCMRCHmT1V4Z9MqbwQtjYRP+Iq69cbeAjnh+Mnl0q3jbiKm1yxveuy3+mWSrlJNoJIFIARdLwaYPiVJC2l3HjmzXhpmkgwnoPiSeqtz48Io4HMLTRBVJDQ3-----END CERTIFICATE-----";

          
        try{   
                new ActiveXObject("CSPEx.ClientUI") ; 
                  
        }catch(e)   
        {   
                try{   
                           
                        XEnroll.InstallPKCS7(sPKCS7); 
                        
                           
                }catch(ex)   
                {   
                        alert("安装证书失败 :\n描述: " + ex.description + "\n代码:" + ex.number );   
                }   
        }             
	}   
  
</script>


</html>
