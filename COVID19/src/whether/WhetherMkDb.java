package whether;

import java.sql.SQLException;
import java.util.ArrayList;
import frame.ConnDB;

public class WhetherMkDb {
	ConnDB cd = new ConnDB();

	public WhetherMkDb() throws SQLException {
		GetApiData.getApiData();
		ArrayList<WhetherVO> wh = GetApiData.whethervo;
		try {

			cd.stmt.executeUpdate("drop table Whether");
			cd.stmt.executeUpdate("create table Whether(" + "sidoNm varchar2(100), " + "stationName varchar2(100), "
					+ "pm10Value varchar2(100), " + "pm25Value varchar2(100)," + "no2Value varchar2(100), "
					+ "o3Value varchar2(100), " + "coValue varchar2(100), " + "so2Value varchar2(100),"
					+ "pm10Grade varchar2(100)," + "pm25Grade varchar2(100)," + "no2Grade varchar2(100),"
					+ "o3Grade varchar2(100)," + "coGrade varchar2(100)," + "so2Grade varchar2(100),"
					+ "datatime varchar2(100)" + ")");

			for (int i = 0; i < wh.size(); i++) {
				String sidoNm = wh.get(i).sidoName;
				String stationName = wh.get(i).stationName;
				String pm10Value = wh.get(i).pm10Value;
				String pm25Value = wh.get(i).pm25Value;
				String no2Value = wh.get(i).no2Value;
				String o3Value = wh.get(i).o3Value;
				String coValue = wh.get(i).coValue;
				String so2Value = wh.get(i).so2Value;
				String pm10Grade = wh.get(i).pm10Grade;
				String pm25Grade = wh.get(i).pm25Grade;
				String no2Grade = wh.get(i).no2Grade;
				String o3Grade = wh.get(i).o3Grade;
				String coGrade = wh.get(i).coGrade;
				String so2Grade = wh.get(i).so2Grade;
				String dataTime = wh.get(i).dataTime;
				String cvInsert = "insert into Whether values(" + "'" + sidoNm + "','" + stationName + "','" + pm10Value
						+ "','" + pm25Value + "','" + no2Value + "','" + o3Value + "','" + coValue + "','" + so2Value
						+ "','" + pm10Grade + "','" + pm25Grade + "','" + no2Grade + "','" + o3Grade + "','" + coGrade
						+ "','" + so2Grade + "','" + dataTime + "')";
//				System.out.println(cvInsert);
				cd.stmt.executeUpdate(cvInsert);
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
		System.out.println("대기오염정보 업데이트 완료");
//		JOptionPane.showMessageDialog(null, "미세먼지 업데이트가 완료되었습니다.");
	}

}