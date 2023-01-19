package covidstat;

import java.sql.*;
import java.util.*;
import utillclass.ConnDB;

public class CovidStatMkDb {
	private ConnDB cd = new ConnDB();
	private GetCovidApiData gad = new GetCovidApiData();

	public CovidStatMkDb() throws SQLException {

		gad.getApiData();
		ArrayList<CovidStat> cs = gad.covidstat;
		try {
			cd.stmt.executeUpdate("delete covidstat");

//			cd.stmt.executeUpdate(
//					"create table covidstat(" + "gubun varchar2(100), " + "incDec int, " + "localOccCnt varchar2(100), "
//							+ "overFlowCnt varchar2(100), " + "qurRate varchar2(100)," + "stdDay varchar2(100)" + ")"); // 테이블 생성

			for (int i = 0; i < cs.size(); i++) {
				String gubun = cs.get(i).getGubun();
				String incDec = cs.get(i).getIncDec();
				String localOccCnt = cs.get(i).getLocalOccCnt();
				String overFlowCnt = cs.get(i).getOverFlowCnt();
				String qurRate = cs.get(i).getQurRate();
				String stdDay = cs.get(i).getStdDay();
				String cvInsert = "insert into covidstat values(" + "'" + gubun + "','" + incDec + "','" + localOccCnt
						+ "','" + overFlowCnt + "','" + qurRate + "','" + stdDay + "'" + ")";
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
		System.out.println("코로나 현황 업데이트 완료");
//		JOptionPane.showMessageDialog(null, "코로나 현황 업데이트가 완료되었습니다.");
	}

}