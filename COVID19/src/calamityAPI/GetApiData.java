package calamityAPI;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class GetApiData {
	static ArrayList<Calamity> covid = new ArrayList<>();
	static LocalDate now = LocalDate.now();
	static LocalDate date = now;

	public static String getTagvalue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}

	public static void getApiData() {
		int page = 1;
		try {
			while (true) {
				String ppage = Integer.toString(page);
				StringBuilder urlBuilder = new StringBuilder(
						"http://apis.data.go.kr/1741000/DisasterMsg4/getDisasterMsg2List"); /* URL */
				urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
						+ "=BK%2Fq%2FKetGZQvENMQxBjyMnWtD4RLFYTFkQ0UP8Mn7HGqUxWvD0bJauhPejs5ILQRhOlNzsBn5QTn1dXfkFS%2BZw%3D%3D");
				urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
						+ URLEncoder.encode(ppage, "UTF-8")); /* 페이지번호 */
				urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
						+ URLEncoder.encode("1000", "UTF-8")); /* 한 페이지 결과 수 */
				urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "="
						+ URLEncoder.encode("xml", "UTF-8")); /* 호출문서 형식 */
				urlBuilder.append("&" + URLEncoder.encode("create_date", "UTF-8") + "="
						+ URLEncoder.encode(date + "", "UTF-8")); /* 생성일시(포함하여 큰 데이터 조회) */
				String url = urlBuilder.toString();
				System.out.println(url);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(url);

				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("row");
				System.out.println(nList.getLength());
				if (nList.getLength() == 0) {
					break;
				}
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					Calamity cv = null;

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						cv = new Calamity(getTagvalue("create_date", eElement), getTagvalue("location_name", eElement),
								getTagvalue("msg", eElement));
						covid.add(cv);
					}
				}

				page += 1;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}