package news;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import utillclass.ConnDB;

public class NewsMkDb {
	ConnDB cd = new ConnDB();
	InputNewsData Ind = new InputNewsData();

	public NewsMkDb() throws SQLException {
		new NewsAPIget();
		try {
			Ind.getApiData();
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<NewsVO> n = Ind.newsvo;
		try {
			cd.stmt.executeUpdate("drop table news");
			cd.stmt.executeUpdate("create table news(" + "title varchar2(1000), " + "originallink varchar2(1000), "
					+ "link varchar2(3000), " + "description varchar2(1000), " + "pubdate varchar2(1000)" + ")");

			for (int i = 0; i < n.size(); i++) {
				String title = n.get(i).title;
				String newtitle = title.replaceAll("[<b>/]", "");
				String originallink = n.get(i).originallink;
				String link = n.get(i).link;
				String description = n.get(i).description;
				String pubdate = n.get(i).pubDate;
				String newsInsert = "insert into news values(" + "'" + newtitle + "','" + originallink + "','" + link
						+ "','" + description + "','" + pubdate + "'" + ")";
//				System.out.println(newsInsert);
				cd.stmt.executeUpdate(newsInsert);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cd.stmt != null) {
					cd.stmt.close();
				}
				if (cd.con != null) {
					cd.con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("뉴스 업데이트 완료");
	}

}