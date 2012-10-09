package com.fordays.fdpay.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fordays.fdpay.agent.*;
import com.ibm.misc.BASE64Encoder;
//import com.ibm.misc.BASE64Encoder;
 

import com.neza.encrypt.BASE64;
import com.neza.encrypt.MD5;
import com.neza.security.MyX509Certificate;
import com.neza.tool.CmdUtil;

public class Certification
{

	public static String CERT_ROOT = "/opt/IBM/CertRoot";
	public static String CERT_ROOT_PASSWORD = "123456";
	public static String CERT_ROOT_KDB = "key.kdb";
	public static String CERT_MD5_KEY = "dssduhoas8832=p-c";
	public static String SIGN_KEY="djsakdh3245sdasd";
	// private static String distingName =
	// "O=钱门,OU=钱门技术部,L=珠海,ST=广东省,C=CN,POSTALCODE=519000";

	public static boolean valid(HttpServletRequest request, Agent agent)
	{
		try
		{
			String certAttribute = "javax.servlet.request.X509Certificate";
			X509Certificate[] certificates = (X509Certificate[]) request
			    .getAttribute(certAttribute);
			if ("https".equals(request.getScheme()))
				if (certificates != null)
				{
					for (X509Certificate certificate : certificates)
					{
						if (certificate.getSerialNumber().equals(
						    Integer.parseInt(agent.getCertInfo().getSerialNo(), 16)))

						{ return true; }
					}
				}
				else
				{
					return false;
				}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return false;
	}

	/**
	 * 个人证书请求
	 * 
	 * @param certInfo
	 */
	public static String reqPersonCert(CertInfo certInfo)
	{
		String cmd = "ikeycmd -certreq -create -db " + Certification.CERT_ROOT
		    + "/key.kdb -pw " + Certification.CERT_ROOT_PASSWORD
		    + " -size 1024 -dn CN=" + certInfo.getCertName()
		    + ",O= 珠海市钱门网络科技有限公司,OU=技术部,L=珠海,ST=广东省,C=CN,POSTALCODE=519000,EMAIL="
		    + certInfo.getEmail() + " -file " + Certification.CERT_ROOT
		    + "/personal/" + certInfo.getEmail() + ".arm -label "
		    + certInfo.getEmail() + "";
		return CmdUtil.exec(cmd);
	}

	/**
	 * 签署个人证书
	 * 
	 * @param certInfo
	 */
	public static String signPersonCert(CertInfo certInfo)
	{
		String cmd = "ikeycmd -cert -sign -file " + Certification.CERT_ROOT
		    + "/personal/" + certInfo.getEmail()
		    + ".arm -db /cert/qm_qmpay_com.kdb -pw 123 -label QmCARoot -target "
		    + Certification.CERT_ROOT + "/personal/" + certInfo.getEmail()
		    + ".cer -expire 365";
		return CmdUtil.exec(cmd);
	}

	/**
	 * 接收个人证书
	 * 
	 * @param certInfo
	 */
	public static String receivePersonCert(CertInfo certInfo)
	{
		String cmd = "ikeycmd -cert -receive -file " + Certification.CERT_ROOT
		    + "/personal/" + certInfo.getEmail() + ".cer -db "
		    + Certification.CERT_ROOT + "/key.kdb -pw "
		    + Certification.CERT_ROOT_PASSWORD + " -default_cert no";
		return CmdUtil.exec(cmd);
	}

	/**
	 * 导出p12个人证书
	 * 
	 * @param certInfo
	 * @return
	 */
	public static File getP12Cert(CertInfo certInfo)
	{
		String cmd = " ikeycmd -cert -export -db " + Certification.CERT_ROOT
		    + "/key.kdb -pw " + Certification.CERT_ROOT_PASSWORD + " -label "
		    + certInfo.getEmail() + " -type CMS -target " + Certification.CERT_ROOT
		    + "/personal/" + certInfo.getEmail() + ".p12 -target_pw "
		    + certInfo.getPassword() + " -target_type pkcs12 -encryption strong";
		CmdUtil.exec(cmd);
		File file = new File(Certification.CERT_ROOT + "/personal/"
		    + certInfo.getEmail() + ".p12");
		return file;
	}

	/**
	 * 删除个人证书
	 * 
	 * @param certInfo
	 */
	public static String deletePersonCert(CertInfo certInfo)
	{

		String cmd = "ikeycmd -cert -delete -db " + Certification.CERT_ROOT
		    + "/key.kdb -pw " + Certification.CERT_ROOT_PASSWORD + " -label "
		    + certInfo.getEmail() + "";
		CmdUtil.exec(cmd);

		cmd = "ikeycmd -certreq -delete -db " + Certification.CERT_ROOT
		    + "/key.kdb -pw " + Certification.CERT_ROOT_PASSWORD + " -label "
		    + certInfo.getEmail() + "";
		CmdUtil.exec(cmd);

		cmd = "cd " + Certification.CERT_ROOT + "/personal/";
		CmdUtil.exec(cmd);

		cmd = "rm -rf " + certInfo.getEmail() + ".*";
		return CmdUtil.exec(cmd);
	}

	/**
	 * 查看证书列表
	 * 
	 * @param email
	 * @return
	 */
	public static boolean hasCertfication(String email)
	{

		// windows 查看指令
		String cmd = "cmd /c ikeycmd -cert -list -db " + Certification.CERT_ROOT
		    + "/key.kdb -pw " + Certification.CERT_ROOT_PASSWORD;

		// linux 查看指令
		// String cmd = "ikeycmd -cert -list -db /opt/IBM/CertRoot/key.kdb -pw
		// 123456";

		String result = CmdUtil.exec(cmd);
		if (result.indexOf(email) >= 0)
			return true;
		else
			return false;
	}

	public static X509Certificate getX509Certificate(CertInfo certInfo)
	{
		try
		{

			File file = new File(Certification.CERT_ROOT + "/personal/"
			    + certInfo.getEmail() + ".p12");

			System.out.println("-----------" + Certification.CERT_ROOT + "/personal/"
			    + certInfo.getEmail() + ".p12" + ",password="
			    + certInfo.getPassword() + ",name=" + certInfo.getCertName());
			
			if(!file.exists())
			{
				System.out.println(file.getPath()+file.getName()+"不存在");
				return null;
			}
			X509Certificate x509 = MyX509Certificate.getX509Certificate(file,
			    certInfo.getPassword(), certInfo.getCertName());

			return x509;
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
	}

	// 获得根证书内容
	public static String getRootCertInfo() throws Exception
	{
		String file=Certification.CERT_ROOT
    + File.separator + "root" + File.separator + "QmpayCA.cer";
		InputStream in = new FileInputStream(file);
		byte[] certByte = new byte[in.available()];
		System.out.println("from "+file+" get byte--" + certByte);
		in.read(certByte);
		String temp ="";
		temp = new BASE64Encoder().encode(certByte);
		System.out.println("from "+file+" get root:string--" + temp);
		in.close();
		temp = temp.replaceAll("[ \\r\\t\\n ]", "");
	
		return temp;
	}
//	
	public static boolean validateSignUrl(String urlQueryString){
		if(urlQueryString==null||urlQueryString==""){
			return false;
		}
		String[] items=urlQueryString.split("&sign=");
		if(items.length!=2){
			return false;
		}
		String queryString=items[0];
		String sign=items[1];
		String temp_sign=MD5.encrypt(queryString+Certification.SIGN_KEY);
		System.out.println(sign);
		System.out.println(temp_sign);
		if(sign.equals(temp_sign)){
			return true;
		}
		return false;
	}
	public static String getQueryString(Map map){
		List list = new ArrayList();
		StringBuffer sb = new StringBuffer();
		for(Iterator it=map.entrySet().iterator();it.hasNext();){
			Map.Entry<String,String> temp=(Map.Entry<String,String>)it.next();
			list.add(temp.getKey());
		}
		Collections.sort(list);
		StringBuffer sb2= new StringBuffer();
		for(int i=0;i<list.size();i++){
			String[] map_values=(String[])(map.get(list.get(i)));
			if(!list.get(i).equals("sign")){
			sb.append(list.get(i)).append("=").append(map_values[0].toString()).append("&");
			}else{
			sb2.append(list.get(i)).append("=").append(map_values[0].toString()).append("&");
			}
		}
		sb.append(sb2);
		System.out.println("后台url queryString:  "+sb.substring(0,sb.length()-1));
		return sb.substring(0,sb.length()-1);
	}
	public static String getSortQueryString(String queryString){
		if(queryString==null||queryString==""){
			return null;
		}
		String[] params = queryString.split("&");
		//循环出根据&分割出来的数
		for(int i=0;i<params.length;i++){
			System.out.println(i+"："+params[i]);
		}
		Map map = new HashMap();
		List list = new ArrayList();
		String[] element = null;
		StringBuffer sb= new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for (String temp : params) {
			if (temp == null) {
				continue;
			}
			//进一步分割
			
			element = temp.split("=");
			if (element == null || element.length != 2) {
				
				continue;
			}
			//循环出根据=分割出来的数
//			for(int i=0;i<element.length;i++){
//				System.out.println(i+"："+element[i]);
//			}
			if(element[0].equals("sign")){
			sb2.append(element[0]).append("=").append(element[1]);
			map.put(element[0],element[1]);
			}else{
			map.put(element[0],element[1]);
			list.add(element[0]);
			}
		}
		Collections.sort(list);
		
		for(int i=0;i<list.size();i++){
			sb.append(list.get(i)).append("=").append(map.get(list.get(i))).append("&");
		}
		sb.append(sb2);
		return sb.toString();
	}

	public static void main(String[] args) throws UnsupportedEncodingException
	{
//		String temp = "hitop520@qq.com";
//		try
//		{
//			Certification.getRootCertInfo();
//		}
//		catch (Exception ex)
//		{
//
//		}
		//ccbf4b16062f5e53f408e8b77b9e2257
		String url="thisAction=Mjc2NjI4QHFxLmNvbQ%3D%3D&test=test";
		String email = BASE64.encrypt("276628@qq.com","UTF-8");
		email = java.net.URLEncoder.encode(email, "UTF-8");
		System.out.println(email);
//		Certification.getQueryString(map)
		
	}
}
