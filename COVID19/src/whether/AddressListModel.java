package whether;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class AddressListModel extends AbstractListModel<String> {
	private ArrayList<WhetherVO> addresses;

	public AddressListModel(String strSido, String strGugun) throws ClassNotFoundException, SQLException {
		WhetherDAO dao = new WhetherDAO();
		addresses = dao.listAddress(strSido, strGugun);
	}

	@Override
	public int getSize() {
		return addresses.size();
	}

	@Override
	public String getElementAt(int index) {
		WhetherVO vo = addresses.get(index);
		String address = String.format("%s %s %s %s %s %s %s %s %s", vo.getSidoName(), vo.getStationName(),
				vo.getPm10Value(), vo.getPm25Value(), vo.getNo2Value(), vo.getO3Value(), vo.getCoValue(),
				vo.getSo2Value(), vo.getDataTime());
		return address;
	}
}