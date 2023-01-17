package hospital;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class HosAddressListModel extends AbstractListModel<String>  {
	private ArrayList<HosVO> addresses;

	public HosAddressListModel(String strSido) throws ClassNotFoundException, SQLException {
		HosDAO dao = new HosDAO();
		addresses = dao.listAddress(strSido);
	}

	@Override
	public int getSize() {
		return addresses.size();
	}

	@Override
	public String getElementAt(int index) {
		HosVO vo = addresses.get(index);
		String address = String.format("%s %s %s %s", vo.getSidonm(), vo.getSggunm(), vo.getYadmnm(), vo.getTelno());
		return address;
	}

}