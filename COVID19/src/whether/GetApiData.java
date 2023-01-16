package whether;

import java.net.URLEncoder;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class GetApiData {
	protected ArrayList<WhetherVO> whethervo = new ArrayList<>();

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
					"http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=BK%2Fq%2FKetGZQvENMQxBjyMnWtD4RLFYTFkQ0UP8Mn7HGqUxWvD0bJauhPejs5ILQRhOlNzsBn5QTn1dXfkFS%2BZw%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("1000", "UTF-8")); /* 한 페이지 결과 수(조회 날짜로 검색 시 사용 안함) */
			urlBuilder.append("&" + URLEncoder.encode("sidoName", "UTF-8") + "=" + URLEncoder.encode("전국", "UTF-8")); // 시도
																														// 이름
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
					+ URLEncoder.encode("1", "UTF-8")); /* 페이지번호(조회 날짜로 검색 시 사용 안함) */
			urlBuilder.append("&" + "ver=1.0");
			String url = urlBuilder.toString();
			System.out.println(url);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");
			System.out.println(nList.getLength() +"개의 데이터 발견");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				WhetherVO wh = null;

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					wh = new WhetherVO(getTagvalue("sidoName", eElement), getTagvalue("stationName", eElement),
							getTagvalue("pm10Value", eElement), getTagvalue("pm25Value", eElement),
							getTagvalue("no2Value", eElement), getTagvalue("o3Value", eElement),
							getTagvalue("coValue", eElement), getTagvalue("so2Value", eElement),
							getTagvalue("pm10Grade", eElement), getTagvalue("pm25Grade", eElement),
							getTagvalue("no2Grade", eElement), getTagvalue("o3Grade", eElement),
							getTagvalue("coGrade", eElement), getTagvalue("so2Grade", eElement),
							getTagvalue("dataTime", eElement));
					whethervo.add(wh);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}