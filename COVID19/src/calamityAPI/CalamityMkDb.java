package calamityAPI;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import utillclass.ConnDB;

public class CalamityMkDb {
	ConnDB cd = new ConnDB();

	public CalamityMkDb() throws SQLException {
		GetApiData.getApiData();
		ArrayList<Calamity> cl = GetApiData.covid;
		try {
			cd.stmt.executeUpdate("drop table calamity");
			cd.stmt.executeUpdate("create table calamity(" + "create_date varchar2(100), "
					+ "location_name varchar2(2000), " + "msg varchar2(2000) )");

			for (int i = 0; i < cl.size(); i++) {
				String create_date = cl.get(i).create_date;
				String location_name = cl.get(i).location_name;
				String msg = cl.get(i).msg;
				String newmsg = msg.replaceAll("[']", " ");
				String cvInsert = "insert into calamity values(" + "'" + create_date + "','" + location_name + "','"
						+ newmsg + "')";
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
//		JOptionPane.showMessageDialog(null, "재난문자 업데이트가 완료되었습니다.");
	}

}