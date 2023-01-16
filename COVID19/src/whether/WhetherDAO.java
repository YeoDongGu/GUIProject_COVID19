package whether;


import java.sql.SQLException;
import java.util.ArrayList;

import utillclass.ConnDB;

public class WhetherDAO {
	ConnDB cd = new ConnDB();

	public ArrayList<WhetherVO> listSido() throws SQLException, ClassNotFoundException {
		ArrayList<WhetherVO> sidos = new ArrayList<WhetherVO>();

		// sql실행 및 sql결과 받아오기
		String sql = "select distinct sidonm from whether";
		cd.pstmt = cd.con.prepareStatement(sql);
		cd.rs = cd.pstmt.executeQuery();
		while (cd.rs.next()) {
			WhetherVO vo = new WhetherVO();
			vo.setSidoName(cd.rs.getString("sidonm"));
			sidos.add(vo);
		}
		if (cd.rs != null)
			cd.rs.close();
		if (cd.pstmt != null)
			cd.pstmt.close();
		if (cd.con != null)
			cd.con.close();

		return sidos;
	}

	public ArrayList<WhetherVO> listGugun(String strSido) throws SQLException, ClassNotFoundException {
		ArrayList<WhetherVO> guguns = new ArrayList<WhetherVO>();

		// sql실행 및 sql결과 받아오기
		String sql = "select distinct stationname from whether where sidonm = ?";
		cd.pstmt = cd.con.prepareStatement(sql);
		cd.pstmt.setString(1, strSido);
		cd.rs = cd.pstmt.executeQuery();
		while (cd.rs.next()) {
			WhetherVO vo = new WhetherVO();
			vo.setStationName(cd.rs.getString("stationname"));
			guguns.add(vo);
		}
		if (cd.rs != null)
			cd.rs.close();
		if (cd.pstmt != null)
			cd.pstmt.close();
		if (cd.con != null)
			cd.con.close();

		return guguns;
	}

	public ArrayList<WhetherVO> listAddress(String strSido, String strGugun)
			throws SQLException, ClassNotFoundException {
		ArrayList<WhetherVO> addresses = new ArrayList<WhetherVO>();

		// sql실행 및 sql결과 받아오기
		String sql = "select sidonm, stationname, pm10value,pm25value, no2value, o3value, coValue, so2Value, pm10Grade, pm25Grade, no2Grade, o3Grade, coGrade, so2Grade, dataTime from whether where sidonm = ? and stationname = ?";
		cd.pstmt = cd.con.prepareStatement(sql);
		cd.pstmt.setString(1, strSido);
		cd.pstmt.setString(2, strGugun);
		cd.rs = cd.pstmt.executeQuery();
		while (cd.rs.next()) {
			WhetherVO to = new WhetherVO();
			to.setSidoName(cd.rs.getString("sidonm"));
			to.setStationName(cd.rs.getString("stationname"));
			to.setPm10Value(cd.rs.getString("pm10value"));
			to.setPm25Value(cd.rs.getString("pm25value"));
			to.setNo2Value(cd.rs.getString("no2value"));
			to.setO3Value(cd.rs.getString("o3value"));
			to.setCoValue(cd.rs.getString("coValue"));
			to.setSo2Value(cd.rs.getString("so2Value"));
			to.setPm10Grade(cd.rs.getString("pm10Grade"));
			to.setPm25Grade(cd.rs.getString("pm25Grade"));
			to.setNo2Grade(cd.rs.getString("no2Grade"));
			to.setO3Grade(cd.rs.getString("o3Grade"));
			to.setCoGrade(cd.rs.getString("coGrade"));
			to.setSo2Grade(cd.rs.getString("so2Grade"));
			to.setDataTime(cd.rs.getString("dataTime"));
			addresses.add(to);
		}
		if (cd.rs != null)
			cd.rs.close();
		if (cd.pstmt != null)
			cd.pstmt.close();
		if (cd.con != null)
			cd.con.close();

		return addresses;
	}

}