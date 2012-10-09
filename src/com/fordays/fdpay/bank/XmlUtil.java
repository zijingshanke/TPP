package com.fordays.fdpay.bank;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
	public static void main(String[] args) {
		Document doc = load("E:\\AreaCodesConfig.xml");
		String city = "珠海";
		Element element = getElementByAttributeValue(doc,
				"/AreaCodeConf/state/City", city);
		System.out.println(element.attribute("AreaCode").getValue());
	}

	/**
	 * string2Document 将字符串转为Document
	 * 
	 * @return
	 * @param s
	 *            xml格式的字符串
	 */
	public static Document string2Document(String s) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(s);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return doc;
	}

	/**
	 * doc2XmlFile 将Document对象保存为一个xml文件到本地
	 * 
	 * @return true:保存成功 flase:失败
	 * @param filename
	 *            保存的文件名
	 * @param document
	 *            需要保存的document对象
	 */
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			/* 将document中的内容写入文件中 */
			// 默认为UTF-8格式，指定为"GB2312"
			OutputFormat format = OutputFormat.createPrettyPrint();
//			format.setEncoding("GB2312");
			XMLWriter writer = new XMLWriter(
					new FileWriter(new File(filename)), format);
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	public Document readResult(StringBuffer src) {
		Document document = null;
		StringReader sr = new StringReader(src.toString());
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(sr);
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("xml信息读取失败,原因是：" + e.getMessage());
		}
		return document;
	}

	/**
	 * 载入一个xml文档
	 */
	public static Document load(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * 根据其中一个属性值，读取文件某个节点，
	 */
	public static Element getElementByAttributeValue(Document doc, String path,
			String attributeValue) {
		/** 先用xpath查找所有节点 并输出它的name属性值 */
		List list = doc.selectNodes(path);
		Iterator it = list.iterator();
		Element element = null;
		while (it.hasNext()) {
			Element tempElement = (Element) it.next();
			String condition = tempElement.attribute("Name").getValue();
			if (attributeValue.equals(condition)) {
//				System.out.println("Name=" + condition);
				element = tempElement;
			}
		}
		return element;
	}

	/**
	 * 演示读取文件的具体某个节点的值
	 */
	public static void xmlReadDemo() {
		Document doc = load("classes/xmlWriteDemoByDocument.xml");
		// Element root = doc.getRootElement();
		/** 先用xpath查找所有ftp节点 并输出它的name属性值 */
		List list = doc.selectNodes("/config/ftp");
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element ftpElement = (Element) it.next();
			System.out.println("ftp_name="
					+ ftpElement.attribute("name").getValue());
		}
		/** 直接用属性path取得name值 */
		list = doc.selectNodes("/config/ftp/@name");
		it = list.iterator();
		while (it.hasNext()) {
			Attribute attribute = (Attribute) it.next();
			System.out.println("@name=" + attribute.getValue());
		}
		/** 直接取得DongDian ftp的 ftp-host 的值 */
		list = doc.selectNodes("/config/ftp/ftp-host");
		it = list.iterator();
		Element hostElement = (Element) it.next();
		System.out.println("DongDian's ftp_host=" + hostElement.getText());
	}
}
