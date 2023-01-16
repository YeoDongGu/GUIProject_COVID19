package hospital;

import java.sql.SQLException;
import java.util.ArrayList;

import frame.LoginDAO;
import utillclass.ConnDB;

public class HosDAO extends LoginDAO {
	ConnDB cd = new ConnDB();

	public ArrayList<HosVO> listSido() throws SQLException, ClassNotFoundException {
		ArrayList<HosVO> sidos = new ArrayList<HosVO>();

		// sql실행 및 sql결과 받아오기
		String sql = "select distinct sidonm from covid";
		cd.pstmt = cd.con.prepareStatement(sql);
		cd.rs = cd.pstmt.executeQuery();
		while (cd.rs.next()) {
			HosVO to = new HosVO();
			to.setSidonm(cd.rs.getString("sidonm"));
			sidos.add(to);
		}
		if (cd.rs != null)
			cd.rs.close();
		if (cd.pstmt != null)
			cd.pstmt.close();
		if (cd.con != null)
			cd.con.close();

		return sidos;
	}

	public ArrayList<HosVO> listAddress(String strSido) throws SQLException, ClassNotFoundException {
		ArrayList<HosVO> addresses = new ArrayList<HosVO>();

		// sql실행 및 sql결과 받아오기
		String sql = "select sidonm, sggunm, yadmnm, telno from covid where sidonm = ?";
		cd.pstmt = cd.con.prepareStatement(sql);
		cd.pstmt.setString(1, strSido);
		cd.rs = cd.pstmt.executeQuery();
		while (cd.rs.next()) {
			HosVO vo = new HosVO();
			vo.setSidonm(cd.rs.getString("sidonm"));
			vo.setSggunm(cd.rs.getString("sggunm"));
			vo.setYadmnm(cd.rs.getString("yadmnm"));
			vo.setTelno(cd.rs.getString("telno"));
			addresses.add(vo);
		}
		if (cd.rs != null)
			cd.rs.close();
		if (cd.pstmt != null)
			cd.pstmt.close();
		if (cd.con != null)
			cd.con.close();

		return addresses;
	}

	public ArrayList<FavTO> listFavorites(String id) throws SQLException, ClassNotFoundException {
		ArrayList<FavTO> favorites = new ArrayList<FavTO>();

		// sql실행 및 sql결과 받아오기
		String sql = "select id, hos_inf from favorites where id = ?";
		cd.pstmt = cd.con.prepareStatement(sql);
		cd.pstmt.setString(1, id);
		cd.rs = cd.pstmt.executeQuery();
		while (cd.rs.next()) {
			FavTO to = new FavTO();
			to.setId(cd.rs.getString("id"));
			to.setHos_inf(cd.rs.getString("hos_inf"));
			favorites.add(to);
		}
		if (cd.rs != null)
			cd.rs.close();
		if (cd.pstmt != null)
			cd.pstmt.close();
		if (cd.con != null)
			cd.con.close();

		return favorites;
	}

}