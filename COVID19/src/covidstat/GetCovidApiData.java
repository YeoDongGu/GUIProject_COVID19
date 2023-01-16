package covidstat;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetCovidApiData {
	protected ArrayList<CovidStat> covidstat = new ArrayList<>();
	private LocalDate now = LocalDate.now();
	private LocalDate date = now;

	public String getTagvalue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}

	public void getApiData() {

//		int i = 0;
//		String resion[] = { "합계", "서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종", "경기", "강원", "충북", "충남", "전북", "전남",
//				"경북", "경남", "제주", "검역" };
		while (true) {
//			if (i == 19) {
//				break;
//			}
			try {
				StringBuilder urlBuilder = new StringBuilder(
						"http://apis.data.go.kr/1352000/ODMS_COVID_04/callCovid04Api"); /* URL */
				urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
						+ "=BK%2Fq%2FKetGZQvENMQxBjyMnWtD4RLFYTFkQ0UP8Mn7HGqUxWvD0bJauhPejs5ILQRhOlNzsBn5QTn1dXfkFS%2BZw%3D%3D");
				urlBuilder.append(
						"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
				urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
						+ URLEncoder.encode("500", "UTF-8")); /* 한 페이지 결과 수 */
				urlBuilder.append("&" + URLEncoder.encode("apiType", "UTF-8") + "="
						+ URLEncoder.encode("xml", "UTF-8")); /* 결과형식(xml/json) */
//				urlBuilder.append(
//						"&" + URLEncoder.encode("gubun", "UTF-8") + "=" + URLEncoder.encode("전국", "UTF-8")); /* 시도명 */
				urlBuilder.append("&" + URLEncoder.encode("std_day", "UTF-8") + "="
						+ URLEncoder.encode(date + "", "UTF-8")); /* 기준일자 */
				String url = urlBuilder.toString();

				System.out.println(url);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(url);

				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("item");
				if (nList.getLength() == 0) {
					date = date.minusDays(1);
					continue;
				}
				System.out.println(nList.getLength() + "개의 데이터 발견");
				System.out.println(date);

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					CovidStat cs = null;

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						cs = new CovidStat(getTagvalue("gubun", eElement), getTagvalue("incDec", eElement),
								getTagvalue("localOccCnt", eElement), getTagvalue("overFlowCnt", eElement),
								getTagvalue("qurRate", eElement), getTagvalue("stdDay", eElement));
						covidstat.add(cs);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
//			i++;
		}
	}
}