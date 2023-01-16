package hospital;

import java.net.URLEncoder;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class GetApiData {
	protected ArrayList<HosVO> hosvo = new ArrayList<>();

	public static String getTagvalue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}

	public void getApiData() {

		try {
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=BK%2Fq%2FKetGZQvENMQxBjyMnWtD4RLFYTFkQ0UP8Mn7HGqUxWvD0bJauhPejs5ILQRhOlNzsBn5QTn1dXfkFS%2BZw%3D%3D");
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("200", "UTF-8")); /* 한 페이지 결과 수 */
			String url = urlBuilder.toString();
			System.out.println(url);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");
			System.out.println(nList.getLength() + "개의 데이터 발견");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				HosVO ht = null;

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					ht = new HosVO(getTagvalue("sidoNm", eElement), getTagvalue("sgguNm", eElement),
							getTagvalue("yadmNm", eElement), getTagvalue("telno", eElement));
					hosvo.add(ht);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}