package frame;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import utillclass.ConnDB;

public class SignupDAO {
	FirstFrame ff = new FirstFrame();
	ConnDB cd = new ConnDB();

	public ArrayList<SignupVo> list(String id, String pwd, String resi, String name, String sido, String dong) {
		ArrayList<SignupVo> list = new ArrayList<SignupVo>();
		try {
			String query = "select * from profile ";
			if (id != null && pwd != null && resi != null && name != null && sido != null && dong != null) {
				query += "where pf_id = '" + id.toLowerCase() + "'";
				query += "or pf_rsid = '" + resi.toLowerCase() + "'";
			}

			System.out.println("SQL : " + query);

			cd.rs = cd.stmt.executeQuery(query);

			cd.rs.last();
			System.out.println("rs.getRow() :" + cd.rs.getRow());
			if (cd.rs.getRow() == 0) {
				System.out.println("0 row selected....");
				cd.rs.previous();
				query = "insert into profile(pf_id,pf_pwd,pf_rsid,pf_name,pf_add1,pf_add2) ";
				query += "VALUES('" + id + "', '" + pwd + "', '" + resi + "', '" + name + "','" + sido + "','" + dong
						+ "')";
				System.out.println(query);
				cd.stmt.executeUpdate(query);
				System.out.println(query);
				System.out.println("Join complete");
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				String sid = id;
				String spwd = pwd;
				String sresi = resi;
				String sname = name;
				String ssido = sido;
				String sdong = dong;
				SignupVo data = new SignupVo(sid, spwd, sresi, sname, ssido, sdong);
				list.add(data);
			} else if (cd.rs.getRow() != 0) {
				System.out.println(cd.rs.getRow() + "rows selected...");
				System.out.println("can not join");
				JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다. 회원정보를 전부 입력하세요.", "ERROR_MESSAGE",
						JOptionPane.ERROR_MESSAGE);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
}
