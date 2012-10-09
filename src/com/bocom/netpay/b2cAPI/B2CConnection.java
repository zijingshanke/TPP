package com.bocom.netpay.b2cAPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import com.fordays.fdpay.bank.LogUtil;

public class B2CConnection {
	private LogUtil myLog;
	int bufLen;
	byte[] buffer;

	public B2CConnection(boolean useSSL) {
		this.bufLen = 4194304;

		this.buffer = new byte[this.bufLen];
		try {
			if (useSSL) {
				Security.addProvider(new com.ibm.jsse.IBMJSSEProvider());
				System.setProperty("java.protocol.handler.pkgs",
						"com.ibm.net.ssl.internal.www.protocol");
				printSSLInfo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author Yan Rui
	 */
	public static void trustAllHttpsCertificates() {
		// Create a trust manager that does not validate certificate chains:
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];

		javax.net.ssl.TrustManager tm = new MyTrustManager();
		trustAllCerts[0] = tm;

		javax.net.ssl.SSLContext context;
		try {
			context = javax.net.ssl.SSLContext.getInstance("SSL");
			context.init(null, trustAllCerts, null);

			// System.out.println(context.getProtocol());

			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(context
					.getSocketFactory());

			// System.out.println(HttpsURLConnection.getDefaultSSLSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}

		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				System.out.println("Warning: URL Host: " + urlHostName
						+ " vs. " + session.getPeerHost());
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}

	public String sendAndReceive(String srcUrl) {
		myLog = new LogUtil(false, false, B2CConnection.class);
		myLog.info("sendAndReceive() srcUrl=" + srcUrl);

		trustAllHttpsCertificates();// -------------------

		myLog.info("after trustAllHttpsCertificates()。。。。");

		HttpURLConnection connection = null;
		InputStream in = null;
		try {
			URL url = new URL(srcUrl);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestProperty("User-Agent", "Mozilla/MSIE");
			connection.setReadTimeout(10000);// ------------
			connection.connect();

			myLog.info("responseCode:" + connection.getResponseCode());

			int contentLen = connection.getContentLength();
			in = connection.getInputStream();

			if (contentLen <= 0) {
				contentLen = bufLen;
				int offset = 0;
				do {
					int len = in.read(buffer, offset, contentLen - offset);
					if (len <= 0)
						break;
					offset += len;
				} while (true);
				contentLen = offset;
			} else {
				if (contentLen > bufLen) {
					buffer = new byte[contentLen];
					bufLen = contentLen;
				}
				int offset = 0;
				do {
					int len = in.read(buffer, offset, contentLen - offset);
					if (len <= 0)
						break;
					offset += len;
				} while (true);
				contentLen = offset;
			}
			String resMsg = connection.getHeaderField(0);
			if (resMsg.toLowerCase().indexOf("ok") < 0) {
				return null;
			} else {
				connection.disconnect();
				in.close();
				String content = new String(buffer, 0, contentLen);
				return content;
			}
		} catch (Exception e) {
			if (connection != null)
				connection.disconnect();
			if (in != null)
				try {
					in.close();
				} catch (IOException ioexception) {
				}
				
			myLog.error("sendAndReceive() 错误："+e.getMessage());
			e.printStackTrace();

			return null;
		}
	}	

	public void printSSLInfo() {
		myLog = new LogUtil(false, false, B2CConnection.class);
//		myLog.info("printSSLInfo()--------------------------");
		// myLog.info("Security Provider:" + Security.getProviders());
		// myLog.info("System Property:" + System.getProperties());
	}
	
	public static String TestQuery(){
		String srcUrl = "https://ebank.95559.com.cn/corporbank/NsTrans?dse_operationName=cb2202_queryOrderOp&reqData=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22gb2312%22%3F%3E%3CBOCOMB2C%3E%3CopName%3Ecb2202_queryOrderOp%3C%2FopName%3E%3CreqParam%3E%3CmerchantID%3E301440360129520%3C%2FmerchantID%3E%3Cnumber%3E1%3C%2Fnumber%3E%3Cdetail%3E1%3C%2Fdetail%3E%3Corders%3EC20091030000001%3C%2Forders%3E%3C%2FreqParam%3E%3C%2FBOCOMB2C%3E&signData=MIIE%2FgYJKoZIhvcNAQcCoIIE7zCCBOsCAQExCzAJBgUrDgMCGgUAMAsGCSqGSIb3DQEHAaCCA%2BUwggPhMIICyaADAgECAgRMXdLhMA0GCSqGSIb3DQEBBQUAMDUxCzAJBgNVBAYTAkNOMRQwEgYDVQQKEwtCQU5LQ09NTSBDQTEQMA4GA1UEAxMHQk9DT01DQTAeFw0wOTA5MjIwNzMxMzhaFw0xMTA5MjIwNzMxMzhaMIGAMQswCQYDVQQGEwJDTjEUMBIGA1UEChMLQkFOS0NPTU0gQ0ExETAPBgNVBAsTCEJBTktDT01NMRIwEAYDVQQLEwlNZXJjaGFudHMxNDAyBgNVBAMTKzA0MEAwMTQ0MDQwMDAwMDE1MjcxNkBbMzAxNDQwMzYwMTI5NTIwXUAwMDAwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBALglwOl2sLwGJ8JiaHp4DtjVygLat2b6ZEvk7qD2heCbpB1CVwhFRvXD8v7Ly%2FQgxjG3G4Z6RuT51f2DLuDACIDTreFTxCvRQIxQA3OYGVnqyq3CC3Qc5kYy6olGGpPYAnoODADchG7jiAFDqU%2FPpvEUuMmhUENoxzIllxQMgSwRAgMBAAGjggEvMIIBKzAfBgNVHSMEGDAWgBTSs9GxOUepN3l34yRNY7X4QsuZczBEBgkqhkiG9w0BCQ8ENzA1MA4GCCqGSIb3DQMCAgIAgDAOBggqhkiG9w0DBAICAIAwBwYFKw4DAgcwCgYIKoZIhvcNAwcwPQYDVR0gBDYwNDAyBgRVHSAAMCowKAYIKwYBBQUHAgEWHGh0dHA6Ly8xOTIuMTY4LjMuMTEwL2Nwcy5odG0wVgYDVR0fBE8wTTBLoEmgR6RFMEMxCzAJBgNVBAYTAkNOMRQwEgYDVQQKEwtCQU5LQ09NTSBDQTEMMAoGA1UECxMDY3JsMRAwDgYDVQQDEwdjcmwzODc1MAwGA1UdDwQFAwMH%2BYAwHQYDVR0OBBYEFAf4PbwEuZqX6aIT4iXipwUfSsMEMA0GCSqGSIb3DQEBBQUAA4IBAQB1lgHmpF6591tUOWmOa61DkJd8ZoV0yk5P2Fl6heGXowH0SUEtIU38m4TTYEEtNYnylbo5XlhhBc0k99lMEXh0gzNhUvmKhnbviHDYzVLNNsF1idRNfqc9rQhrPDoFgJXKqI0cdHAY4%2B27fahfwmiRGVB3SmVekvBloAQFdD4FGEpwI3aB2ITbIisFZbSL70loX9PYj57OLxv4SMm5j1Sx58ZWrOcndOm4cx6TMh6wFK4hB0PqR1h05n45geJlhbTifkewBcOLcIvoK4S8cbrfVMAURHDJDLTcDfIcFUPMxlSaI4JPM3sbtM9VMLngupGv%2BvSKWh8SuL1EPPjgtDhEMYHiMIHfAgEBMD0wNTELMAkGA1UEBhMCQ04xFDASBgNVBAoTC0JBTktDT01NIENBMRAwDgYDVQQDEwdCT0NPTUNBAgRMXdLhMAkGBSsOAwIaBQAwDQYJKoZIhvcNAQEBBQAEgYAUEnRCOKov%2FkGrYni6fUQaMgZX%2BYCXkLMwGwG%2F0vzmr%2B%2FYPYCc78pkRgI8v%2FFskIXmtSjAeyG3VlytV%2Bq15ZWK1c3RcXWO6DDQGo7MJYAqZ37CwHsx%2Fgy%2B9bp6AizZFW9StGSAw0UEsR5kEEyr8urpr56phWM9vWjxFPIqaurY1w%3D%3D";
		
		System.out.println(srcUrl);
		
		B2CConnection test = new B2CConnection(true);
		String repXML = test.sendAndReceive(srcUrl);
		System.out.println(repXML);
		return repXML;
	}

	public static void main(String[] args) {
		TestQuery();
	}
}


