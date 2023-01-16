package news;

import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetApiData {
	static ArrayList<NewsVO> newsvo = new ArrayList<>();

	public static String getTagvalue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}

	public static void getApiData() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		Document doc = dBuilder.parse("C:/Users/Administrator/git/COVID_Information/COVID19/newsapi.xml");
		Document doc = dBuilder.parse("newsapi.xml");

		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("item");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			NewsVO n = null;

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				n = new NewsVO(getTagvalue("title", eElement), getTagvalue("originallink", eElement),
						getTagvalue("link", eElement), getTagvalue("description", eElement),
						getTagvalue("pubDate", eElement));
				newsvo.add(n);
			}
		}
	}

}