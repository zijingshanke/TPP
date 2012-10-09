package com.fordays.fdpay.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import com.neza.exception.AppException;
import CCBSign.RSASig;

/**
 * @银行接口工具类
 */

public class BankUtil {
	public static void main(String[] args) {

	}

	public static void testLoadProperties() {
		String res = "com.fordays.fdpay.bank.bankReturnMsg";
		ResourceBundle reb = getResourceBundle(res);
		String paraName = "1001";
		String paraValue = getParameterByName(reb, paraName);
		try {
			System.out.println(paraName + "--"
					+ new String(paraValue.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String setHtmlTag(String src) {
		StringBuffer buf = new StringBuffer();
		// 对下面五个字符需要进行编码，空格、>、<、&、“、'，空格编码成&nbsp;，<编码成&lt;，>编码成&gt;，&编码成&amp;，""编码成&quot;，'编码成&apos;
		for (int i = 0; i < src.length(); i++) {
			if (src.charAt(i) == ' ') {
				buf.append("&nbsp;");
			} else if (src.charAt(i) == '<') {
				buf.append("&lt;");
			} else if (src.charAt(i) == '>') {
				buf.append("&gt;");
			} else if (src.charAt(i) == '&') {
				buf.append("&amp;");
			} else if (src.charAt(i) == '“') {
				buf.append("&quot;");
			} else if (src.charAt(i) == '‘') {
				buf.append("&apos;");
			} else {
				buf.append(src.charAt(i));
			}
		}
		System.out.println("set html Tag:" + buf.toString());
		return src;
	}

	/**
	 * @description:创建Socket
	 * @param String
	 *            host
	 * @param int
	 *            port
	 * @return Socket
	 */
	public static Socket initSocket(String host, int port) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			return socket;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socket;
	}

	/**
	 * @param Socket
	 *            socket
	 * @param String
	 *            cmd
	 * @description:建立Socket连接,发送指令报文,获得反馈结果
	 * @remark:建设银行外联平台
	 */
	public static String getResultAsSocket(Socket socket, String strCmd)
			throws AppException {
		String ebsResult = "";// 服务端反馈结果
		try {
			System.out.println("===向服务端发送数据报===");
			OutputStream ops = socket.getOutputStream();
			OutputStreamWriter opsw = new OutputStreamWriter(ops);
			BufferedWriter bw = new BufferedWriter(opsw);
			bw.write(strCmd);
			bw.flush();

			System.out.println("===从服务端接收数据===");
			InputStream ips = socket.getInputStream();
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String brResult = "";
			while ((brResult = br.readLine()) != null) {
				ebsResult += brResult;
			}
			socket.close();
			return ebsResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// =======================================================================================
	/**
	 * @description:载入一个xml文档
	 * @return 成功返回Document对象，失败返回null
	 * @param uri
	 *            文件路径
	 */
	public static Document loadXml(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	//
	public static ResourceBundle getResourceBundle(String resource) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(resource);
		return resourceBundle;
	}

	//
	public static String getParameterByName(ResourceBundle resourceBundle,
			String paraName) {
		if (resourceBundle == null)
			System.out.println("资源不存在");
		String tValue = null;
		try {
			tValue = resourceBundle.getString(paraName).trim();
			if (tValue.equals(""))
				System.out.println("读取资源文件,值为空," + paraName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tValue;
	}

	/**
	 * 金额进制转换，元转分
	 * @example：工商银行
	 * @param:BigDecimal dollarAmount
	 * @return:String
	 */
	public static String getCentAmount(BigDecimal dollarAmount) {
		String tempAmount = (dollarAmount.multiply(new BigDecimal(100)))
				.toString();
		int isDecimal = tempAmount.indexOf(".");
		if (isDecimal > 0) {
			tempAmount = tempAmount.substring(0, isDecimal);
		}
		return tempAmount;
	}

	/**
	 * @description：金额进制转换，分转元
	 * @example：工商银行
	 * @param:String
	 * @return:BigDecimal dollarAmount
	 */
	public static BigDecimal getDollarAmount(String centAmount) {
		BigDecimal tempAmount = new BigDecimal(0);
		Double doubleAmount = Double.valueOf(centAmount);
		doubleAmount = doubleAmount / 100;
		tempAmount = BigDecimal.valueOf(doubleAmount);
		// System.out.println(tempAmount);
		return tempAmount;
	}

	/**
	 * 数据类型转换
	 * 
	 * @param:String strAmount
	 * @return:BigDecimal dollarAmount
	 */
	public static BigDecimal getBigDecimalByString(String strAmount) {
		BigDecimal tempAmount = new BigDecimal(0);
		Double doubleAmount = Double.valueOf(strAmount);
		tempAmount = BigDecimal.valueOf(doubleAmount);
		return tempAmount;
	}

	/**
	 * 将BigDecimal转成双精度字符串
	 * @param:
	 */
	public static String getDoubleStringByDecimal(BigDecimal decimal) {
		String amount = new DecimalFormat("0.00").format(decimal.doubleValue());
		return amount;
	}

	/**
	 * @description：将字符串按分隔符转成Vector @ 支持 |
	 * @param String
	 *            strSrc
	 * @param String
	 *            strKen
	 * @return Vector<String>
	 */
	public static Vector<String> getVectorString(String strSrc, String strKen) {
		StringTokenizer toKen = new StringTokenizer(strSrc, strKen);
		Vector<String> vec = new Vector<String>();
		int i = 0;
		while (toKen.hasMoreElements()) {
			String value = (String) toKen.nextElement();
			if (value.equals(""))
				value = "&nbsp;";
			vec.add(i++, value);
		}
		for (int j = 0; j < vec.size(); j++) {
			System.out.println(j + "---" + vec.get(j));
		}
		return vec;
	}

	/**
	 * @description：将字符串按分隔符转成字符数组
	 * @支持的分隔符: , / @ #
	 * @param String
	 *            strSrc
	 * @param String
	 *            splitStr
	 * @return String[]
	 */
	public static String[] getSplitString(String strSrc, String splitStr) {
		String splitString[] = strSrc.split(splitStr);
		return splitString;
	}

	/**
	 * @description：将字符串按分隔符转成整形数组
	 * @支持的分隔符: , / @ #
	 * @param String
	 *            strSrc
	 * @param String
	 *            splitStr
	 * @return int[]
	 */
	public static int[] getSplitInt(String strSrc, String splitStr) {
		int[] array = null;
		String splitString[] = getSplitString(strSrc, splitStr);
		int arrayLength = splitString.length;
		array = new int[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			array[i] = Integer.parseInt(splitString[i]);
		}
		return array;
	}

	/**
	 * @description：读取证书（base64编码格式）
	 * @example：民生银行/中信银行
	 * @param String
	 *            url
	 * @return ByteArrayOutputStream
	 */
	public static ByteArrayOutputStream readFileAsByteArray(String url) {
		try {
			FileInputStream fileInStream = new FileInputStream(url);
			ByteArrayOutputStream fileByteStream = new ByteArrayOutputStream();
			int i = 0;
			while ((i = fileInStream.read()) != -1) {
				fileByteStream.write(i);
			}
			fileInStream.close();
			return fileByteStream;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * @description：从文件（公钥/证书）中获取字节数组
	 * @param:String url
	 * @return byte[]
	 */
	public static byte[] getByteFromFile(String url) {
		byte[] pubKey = null;
		try {
			FileInputStream in = new FileInputStream(url);
			pubKey = new byte[in.available()];
			in.read(pubKey);
			in.close();
			return pubKey;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * @description：读取文本文件的内容
	 * @example：建设银行读取公钥
	 * @param:String url
	 * @return String
	 */
	public static String readStrFromTxt(String url) {
		String str = "";
		try {
			char data[] = new char[600];
			FileReader reader = new FileReader(url);

			int num = reader.read(data);
			System.out.println("num is--" + num);

			str = new String(data, 0, num);

			System.out.println("读取的字符是----");
			System.out.println(str);
		} catch (Exception e) {
			System.out.println("读取文件失败，原因是：----------");
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * @description:建设银行 验证签名（公钥）
	 * @String strSrc
	 * @String strSign
	 * @String strPubKey
	 * @return void
	 */
	public static void verityCCBSigature(String strSrc, String strSign,
			String strPubKey) {
		RSASig rsa = new RSASig();
		rsa.setPublicKey(strPubKey);
		System.out.println("验证签名结果=" + rsa.verifySigature(strSign, strSrc));
	}

	// URLEncode
	@SuppressWarnings("deprecation")
	public static String getURLEncode(String src) {
		return URLEncoder.encode(src);
	}

	// URLDecode
	@SuppressWarnings("deprecation")
	public static String getURLDecode(String code) {
		return URLDecoder.decode(code);
	}
}