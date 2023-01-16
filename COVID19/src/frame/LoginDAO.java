package frame;

import java.util.ArrayList;

import utillclass.ConnDB;

public class LoginDAO {
	ConnDB cd = new ConnDB();

	public ArrayList<LoginVo> list(String id, String pwd) {
		ArrayList<LoginVo> list = new ArrayList<LoginVo>();
		try {

			String query = "select *from profile ";
			if (id != null && pwd != null) {
				query += "where pf_id = '" + id.toLowerCase() + "'";
				query += "and pf_pwd = '" + pwd.toLowerCase() + "'";

			}
			System.out.println("SQL : " + query);

			cd.rs = cd.stmt.executeQuery(query);
			cd.rs.last();

			if (cd.rs.getRow() == 0) {
				System.out.println("0 row selected.....");
			} else {
				System.out.println(cd.rs.getRow() + "rows selected.....");
				cd.rs.previous();
				while (cd.rs.next()) {
					String pid = cd.rs.getString("pf_id");
					String ppwd = cd.rs.getString("pf_pwd");
					String pname = cd.rs.getString("pf_name");
					String prsid = cd.rs.getString("pf_rsid");
					String psido = cd.rs.getString("pf_add1");
					String pdong = cd.rs.getString("pf_add2");
					LoginVo data = new LoginVo(pid, ppwd, pname,prsid,psido, pdong);
					list.add(data);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
