package com.bocom.netpay.b2cAPI;

import com.ibm.xml.parsers.DOMParser;
import com.neza.base.Constant;
import java.io.*;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Referenced classes of package com.bocom.netpay.b2cAPI:
//            B2CConnection, BOCOMB2CClient

public class BOCOMSetting {

	public static String ApiURL;
	public static boolean UseSSL;
	private boolean ApiURL_node_exsist;
	public static String OrderURL;
	private boolean OrderURL_node_exsist;
	public static String MerchantID;
	public static boolean EnableLog;
	private boolean EnableLog_node_exsist;
	public static String LogPath;
	private boolean LogPath_node_exsist;
	public static String SettlementFilePath;
	private boolean SettlementFilePath_node_exsist;
	public static String MerchantCertDN;
	private String lastErr;
	public static String MerchantCertFilePath;
	public static String MerchantCertFileName;
	private boolean MerchantCertFile_node_exsist;
	public static String MerchantCertPassword;
	private boolean MerchantCertPassword_node_exsist;
	public static String RootCertFile;
	public static boolean isAPIInitialize;
	private boolean RootCertFile_node_exsist;
	public static OutputStreamWriter logWriter;

	public BOCOMSetting() {
	}

	public int parseXML(String xmlFile) {
		
//		System.out.println("------parseXml(xmlFile)-----" + xmlFile);
		
		DOMParser parser = null;
		try {
			if (xmlFile == null || xmlFile.length() == 0) {
				lastErr = "传送配置文件路径参数不能为空";
				return -1;
			}
			if (!(new File(xmlFile)).isFile()) {
//				System.out.println(xmlFile);
				lastErr = xmlFile + "该配置文件不存在，请检查路径和文件名称是否正确";
				return -1;
			}
			parser = new DOMParser();
			org.w3c.dom.Document doc = null;
			try {
				parser.parse(xmlFile);
				doc = parser.getDocument();
			} catch (Exception xcp) {
				lastErr = "配置文件" + xmlFile + "解析错误，请检查配置文件";
				return -1;
			}
			if (doc == null) {
				lastErr = "配置文件" + xmlFile + "解析错误";
				return -1;
			}
			NodeList nodeList = doc.getChildNodes();
			Node node = nodeList.item(0);
			String name = node.getNodeName();
			if (!"BOCOMB2C".equals(name)) {
				lastErr = "配置文件格式错，找不到节点BOCOMB2C";
				return -1;
			}
			nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node aNode = nodeList.item(i);
				if (aNode.getNodeType() == 1)
					if (aNode.getNodeName().equals("ApiURL")) {
						ApiURL_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						try {
							ApiURL = cnode.getNodeValue().trim();
							
//							System.out.println("ApiURL>>"+ApiURL);
						} catch (Exception e1) {
							ApiURL = null;
						}
						if (ApiURL == null || ApiURL.trim().length() == 0) {
							lastErr = "配置文件中&lt;ApiURL&gt;&lt;/ApiURL&gt;设置错误，设置参数不能为空";
							return -1;
						}
						if (ApiURL.toLowerCase().indexOf("https") != -1)
							UseSSL = true;
						try {
							B2CConnection connection = new B2CConnection(UseSSL);
							URL url = new URL(ApiURL);
						} catch (Exception xcp) {
							lastErr = "配置文件中&lt;ApiURL&gt;&lt;/ApiURL&gt;设置错误，无效的地址或地址无法访问。Exception:"
									+ xcp.toString();
							return -1;
						}
					} else if (aNode.getNodeName().equals("OrderURL")) {
						OrderURL_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						try {
							OrderURL = cnode.getNodeValue().trim();
							
//							System.out.println("OrderURL>>"+OrderURL);
						} catch (Exception e1) {
							OrderURL = null;
						}
						if (OrderURL == null || OrderURL.trim().length() == 0) {
							lastErr = "配置文件中&lt;OrderURL&gt;&lt;/OrderURL&gt;设置错误，设置参数不能为空";
							return -1;
						}
						try {
							B2CConnection connection = new B2CConnection(true);
							URL url = new URL(OrderURL);
						} catch (Exception xcp) {
							lastErr = "配置文件中&lt;OrderURL&gt;&lt;/OrderURL&gt;设置错误，无效的地址或地址无法访问;exception："
									+ xcp.toString();
							return -1;
						}
					} else if (aNode.getNodeName().equals("EnableLog")) {
						EnableLog_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						String value = "";
						try {
							value = cnode.getNodeValue().trim();
//							System.out.println("EnableLog>>"+value);
						} catch (Exception e1) {
							value = null;
						}
						if (value != null
								&& "true".equals(value.toLowerCase().trim()))
							EnableLog = true;
						else if (value != null
								&& "false".equals(value.toLowerCase().trim())) {
							EnableLog = false;
						} else {
							lastErr = "配置文件中&lt;ConnectMethod&gt;&lt;/ConnectMethod&gt;设置错误，设置参数为true/false";
							return -1;
						}
					} else if (aNode.getNodeName().equals("LogPath")) {
						LogPath_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						try {
							LogPath = Constant.WEB_INFO_PATH
									+ cnode.getNodeValue().trim();
//							System.out.println("LogPath>>" + LogPath);
						} catch (Exception e1) {
							LogPath = null;
						}
					} else if (aNode.getNodeName().equals("SettlementFilePath")) {
						Node cnode = aNode.getFirstChild();
						try {
							SettlementFilePath = Constant.WEB_INFO_PATH
									+ cnode.getNodeValue();
//							System.out.println("SettlementFilePath>>"
//									+ SettlementFilePath);
						} catch (Exception e1) {
							SettlementFilePath = null;
						}

						if (SettlementFilePath != null
								&& SettlementFilePath.trim().length() != 0)
							try {
								(new File(SettlementFilePath)).mkdirs();
								SettlementFilePath = (new File(
										SettlementFilePath)).getAbsolutePath();
							} catch (Exception xcp) {
								lastErr = "配置文件中&lt;SettlementFilePath&gt;&lt;/SettlementFilePath&gt;设置错误，请确定路径是否配置正确";
								return -1;
							}
					} else if (aNode.getNodeName().equals("MerchantCertFile")) {
						MerchantCertFile_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						String MerchantCertFile = null;
						try {
							MerchantCertFile = Constant.WEB_INFO_PATH
									+ cnode.getNodeValue().trim();
//							System.out.println("MerchantCertFile>>"
//									+ MerchantCertFile);
						} catch (Exception e1) {
							MerchantCertFile = null;
						}
						if (MerchantCertFile == null
								|| MerchantCertFile.trim().length() == 0) {
							lastErr = "配置文件中&lt;MerchantCertFile&gt;&lt;/MerchantCertFile&gt;设置错误，设置参数不能为空";
							return -1;
						}
						if (!(new File(MerchantCertFile)).isFile()) {
							lastErr = "配置文件中&lt;MerchantCertFile&gt;&lt;/MerchantCertFile&gt;设置错误，请确定路径及文件名是否配置正确";
							return -1;
						}
						MerchantCertFile = (new File(MerchantCertFile))
								.getAbsolutePath();
						MerchantCertFilePath = (new File(MerchantCertFile))
								.getParent();
						MerchantCertFileName = (new File(MerchantCertFile))
								.getName();
					} else if (aNode.getNodeName().equals(
							"MerchantCertPassword")) {
						MerchantCertPassword_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						String MerchantCertFile = null;
						try {
							MerchantCertPassword = cnode.getNodeValue();
//							System.out.println("Password>>"+MerchantCertPassword);
						} catch (Exception e1) {
							MerchantCertPassword = null;
						}
						if (MerchantCertPassword == null
								|| MerchantCertPassword.trim().length() == 0) {
							lastErr = "配置文件中&lt;MerchantCertPassword&gt;&lt;/MerchantCertPassword&gt;设置错误，设置参数不能为空";
							return -1;
						}
					} else if (aNode.getNodeName().equals("RootCertFile")) {
						RootCertFile_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						try {
							RootCertFile = Constant.WEB_INFO_PATH
									+ cnode.getNodeValue().trim();
//							System.out.println("RootCertFile>>" + RootCertFile);
						} catch (Exception e1) {
							RootCertFile = null;
						}

						if (RootCertFile == null
								|| RootCertFile.trim().length() == 0) {
							lastErr = "配置文件中&lt;RootCertFile&gt;&lt;/RootCertFile&gt;设置错误，设置参数不能为空";
							return -1;
						}
						if (!(new File(RootCertFile)).isFile()) {
							lastErr = "配置文件中&lt;RootCertFile&gt;&lt;/RootCertFile&gt;设置错误，请确定路径及文件名是否配置正确";
							return -1;
						}
					}
			}

			if (!ApiURL_node_exsist || !OrderURL_node_exsist
					|| !EnableLog_node_exsist || !MerchantCertFile_node_exsist
					|| !MerchantCertPassword_node_exsist
					|| !RootCertFile_node_exsist) {
				lastErr = "配置文件中缺少必需节点，请检查配置文件或者重新下载配置文件";
				return -1;
			}
			if (EnableLog) {
				if (!LogPath_node_exsist) {
					lastErr = "配置文件中缺少节点&lt;LogPath&gt;&lt;/LogPath&gt;,请增加和设置该节点";
					return -1;
				}
				if (LogPath == null || LogPath.trim().length() == 0) {
					lastErr = "日志开关设置为true,配置文件中&lt;LogPath&gt;&lt;/LogPath&gt;设置参数不能为空";
					return -1;
				}
				try {
					(new File(LogPath)).mkdirs();
					LogPath = (new File(LogPath)).getAbsolutePath();
				} catch (Exception xcp) {
					lastErr = "配置文件中&lt;LogPath&gt;&lt;/LogPath&gt;设置错误，请确定路径是否配置正确";
					return -1;
				}
			}
			parser = null;
		} catch (Exception e) {
			parser = null;
			System.out.println(e.toString());
			lastErr = "配置文件解析错误";
			return -1;
		}
		return 0;
	}

	public int parseXML(InputStream in) {
		DOMParser parser = null;
		DocumentBuilderFactory domfac = null;
		org.w3c.dom.Document doc = null;
		try {
			if (in == null) {
				lastErr = "XML解析失败，输入流为NULL";
				return -1;
			}
			try {
				domfac = DocumentBuilderFactory.newInstance();
				DocumentBuilder dombuilder = domfac.newDocumentBuilder();
				doc = dombuilder.parse(in);
			} catch (Exception xcp) {
				lastErr = "XML解析错误，请检查配置文件";
				return -1;
			}
			if (doc == null) {
				lastErr = "XML解析错误";
				return -1;
			}
			NodeList nodeList = doc.getChildNodes();
			Node node = nodeList.item(0);
			String name = node.getNodeName();
			if (!"BOCOMB2C".equals(name)) {
				lastErr = "XML解析错误，找不到节点BOCOMB2C";
				return -1;
			}
			nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node aNode = nodeList.item(i);
				if (aNode.getNodeType() == 1)
					if (aNode.getNodeName().equals("ApiURL")) {
						ApiURL_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						try {
							ApiURL = cnode.getNodeValue().trim();							
						} catch (Exception e1) {
							ApiURL = null;
						}
						if (ApiURL == null || ApiURL.trim().length() == 0) {
							lastErr = "XML节点&lt;ApiURL&gt;&lt;/ApiURL&gt;设置错误，设置参数不能为空";
							return -1;
						}
						if (ApiURL.toLowerCase().indexOf("https") != -1)
							UseSSL = true;
						try {
							B2CConnection connection = new B2CConnection(UseSSL);
							URL url = new URL(ApiURL);
						} catch (Exception xcp) {
							lastErr = "XML节点&lt;ApiURL&gt;&lt;/ApiURL&gt;设置错误，无效的地址或地址无法访问。Exception:"
									+ xcp.toString();
							return -1;
						}
					} else if (aNode.getNodeName().equals("OrderURL")) {
						OrderURL_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						try {
							OrderURL = cnode.getNodeValue().trim();
						} catch (Exception e1) {
							OrderURL = null;
						}
						if (OrderURL == null || OrderURL.trim().length() == 0) {
							lastErr = "XML节点&lt;OrderURL&gt;&lt;/OrderURL&gt;设置错误，设置参数不能为空";
							return -1;
						}
						try {
							B2CConnection connection = new B2CConnection(true);
							URL url = new URL(OrderURL);
						} catch (Exception xcp) {
							lastErr = "XML节点&lt;OrderURL&gt;&lt;/OrderURL&gt;设置错误，无效的地址或地址无法访问;exception："
									+ xcp.toString();
							return -1;
						}
					} else if (aNode.getNodeName().equals("EnableLog")) {
						EnableLog_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						String value = "";
						try {
							value = cnode.getNodeValue().trim();
						} catch (Exception e1) {
							value = null;
						}
						if (value != null
								&& "true".equals(value.toLowerCase().trim()))
							EnableLog = true;
						else if (value != null
								&& "false".equals(value.toLowerCase().trim())) {
							EnableLog = false;
						} else {
							lastErr = "XML节点&lt;ConnectMethod&gt;&lt;/ConnectMethod&gt;设置错误，设置参数为true/false";
							return -1;
						}
					} else if (aNode.getNodeName().equals("LogPath")) {
						LogPath_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						try {
							LogPath = cnode.getNodeValue().trim();
						} catch (Exception e1) {
							LogPath = null;
						}
					} else if (aNode.getNodeName().equals("SettlementFilePath")) {
						Node cnode = aNode.getFirstChild();
						try {
							SettlementFilePath = cnode.getNodeValue();
						} catch (Exception e1) {
							SettlementFilePath = null;
						}
						if (SettlementFilePath != null
								&& SettlementFilePath.trim().length() != 0)
							try {
								(new File(SettlementFilePath)).mkdirs();
								SettlementFilePath = (new File(
										SettlementFilePath)).getAbsolutePath();
							} catch (Exception xcp) {
								lastErr = "XML节点&lt;SettlementFilePath&gt;&lt;/SettlementFilePath&gt;设置错误，请确定路径是否配置正确";
								return -1;
							}
					} else if (aNode.getNodeName().equals("MerchantCertFile")) {
						MerchantCertFile_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						String MerchantCertFile = null;
						try {
							MerchantCertFile = cnode.getNodeValue();
						} catch (Exception e1) {
							MerchantCertFile = null;
						}
						if (MerchantCertFile == null
								|| MerchantCertFile.trim().length() == 0) {
							lastErr = "XML节点&lt;MerchantCertFile&gt;&lt;/MerchantCertFile&gt;设置错误，设置参数不能为空";
							return -1;
						}
						if (!(new File(MerchantCertFile)).isFile()) {
							lastErr = "XML节点&lt;MerchantCertFile&gt;&lt;/MerchantCertFile&gt;设置错误，请确定路径及文件名是否配置正确";
							return -1;
						}
						MerchantCertFile = (new File(MerchantCertFile))
								.getAbsolutePath();
						MerchantCertFilePath = (new File(MerchantCertFile))
								.getParent();
						MerchantCertFileName = (new File(MerchantCertFile))
								.getName();
					} else if (aNode.getNodeName().equals(
							"MerchantCertPassword")) {
						MerchantCertPassword_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						String MerchantCertFile = null;
						try {
							MerchantCertPassword = cnode.getNodeValue();
						} catch (Exception e1) {
							MerchantCertPassword = null;
						}
						if (MerchantCertPassword == null
								|| MerchantCertPassword.trim().length() == 0) {
							lastErr = "XML节点&lt;MerchantCertPassword&gt;&lt;/MerchantCertPassword&gt;设置错误，设置参数不能为空";
							return -1;
						}
					} else if (aNode.getNodeName().equals("RootCertFile")) {
						RootCertFile_node_exsist = true;
						Node cnode = aNode.getFirstChild();
						try {
							RootCertFile = cnode.getNodeValue();
						} catch (Exception e1) {
							RootCertFile = null;
						}
						if (RootCertFile == null
								|| RootCertFile.trim().length() == 0) {
							lastErr = "XML节点&lt;RootCertFile&gt;&lt;/RootCertFile&gt;设置错误，设置参数不能为空";
							return -1;
						}
						if (!(new File(RootCertFile)).isFile()) {
							lastErr = "XML节点&lt;RootCertFile&gt;&lt;/RootCertFile&gt;设置错误，请确定路径及文件名是否配置正确";
							return -1;
						}
					}
			}

			if (!ApiURL_node_exsist || !OrderURL_node_exsist
					|| !EnableLog_node_exsist || !MerchantCertFile_node_exsist
					|| !MerchantCertPassword_node_exsist
					|| !RootCertFile_node_exsist) {
				lastErr = "XML中缺少必需节点，请检查配置文件或者重新下载配置文件";
				return -1;
			}
			if (EnableLog) {
				if (!LogPath_node_exsist) {
					lastErr = "XML缺少节点&lt;LogPath&gt;&lt;/LogPath&gt;,请增加和设置该节点";
					return -1;
				}
				if (LogPath == null || LogPath.trim().length() == 0) {
					lastErr = "日志开关设置为true,XML节点中&lt;LogPath&gt;&lt;/LogPath&gt;设置参数不能为空";
					return -1;
				}
				try {
					(new File(LogPath)).mkdirs();
					LogPath = (new File(LogPath)).getAbsolutePath();
				} catch (Exception xcp) {
					lastErr = "XML节点中&lt;LogPath&gt;&lt;/LogPath&gt;设置错误，请确定路径是否配置正确";
					return -1;
				}
			}
			parser = null;
			domfac = null;
			doc = null;
			in.close();
		} catch (Exception e) {
			parser = null;
			domfac = null;
			doc = null;
			try {
				in.close();
			} catch (IOException e1) {
				in = null;
			}
			System.out.println(e.toString());
			lastErr = "XML解析错误";
			return -1;
		}
		return 0;
	}

	public String GetLastErr() {
		return lastErr;
	}

	public static void main(String args[]) {
		InputStream in = null;
		try {
			in = new FileInputStream("C://bocommjava//ini//B2CMerchant.xml");
		} catch (FileNotFoundException filenotfoundexception) {
		}
		(new BOCOMB2CClient()).initialize(in);
	}
}
