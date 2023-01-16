package hospital;

import java.sql.SQLException;
import java.util.ArrayList;

import utillclass.ConnDB;

public class HosMkDb {
	ConnDB cd = new ConnDB();
	GetApiData gad = new GetApiData();

	public HosMkDb() throws SQLException {

		gad.getApiData();
		ArrayList<HosVO> hv = gad.hosvo;
		try {
			cd.stmt.executeUpdate("drop table Covid");
			cd.stmt.executeUpdate("create table Covid(" + "sidoNm varchar2(100), " + "sgguNm varchar2(100), "
					+ "yadmNm varchar2(100), " + "telno varchar2(100) " + ")");

			for (int i = 0; i < hv.size(); i++) {
				String sidoNm = hv.get(i).getSidonm();
				String sgguNm = hv.get(i).getSggunm();
				String yadmNm = hv.get(i).getYadmnm();
				String telno = hv.get(i).getTelno();
				String cvInsert = "insert into Covid values(" + "'" + sidoNm + "','" + sgguNm + "','" + yadmNm + "','"
						+ telno + "'" + ")";
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
		System.out.println("코로나병원정보 업데이트 완료");
	}

}