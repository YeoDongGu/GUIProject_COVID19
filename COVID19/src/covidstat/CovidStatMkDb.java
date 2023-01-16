package covidstat;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import utillclass.ConnDB;

public class CovidStatMkDb {
	ConnDB cd = new ConnDB();
	GetCovidApiData gad = new GetCovidApiData();

	public CovidStatMkDb() throws SQLException {

		gad.getApiData();
		ArrayList<CovidStat> cs = gad.covidstat;
		try {
			cd.stmt.executeUpdate("drop table covidstat");

			cd.stmt.executeUpdate(
					"create table covidstat(" + "gubun varchar2(100), " + "incDec int, " + "localOccCnt varchar2(100), "
							+ "overFlowCnt varchar2(100), " + "qurRate varchar2(100)," + "stdDay varchar2(100)" + ")");

			for (int i = 0; i < cs.size(); i++) {
				String gubun = cs.get(i).gubun;
				String incDec = cs.get(i).incDec;
				String localOccCnt = cs.get(i).localOccCnt;
				String overFlowCnt = cs.get(i).overFlowCnt;
				String qurRate = cs.get(i).qurRate;
				String stdDay = cs.get(i).stdDay;
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