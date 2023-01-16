package whether;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class GugunComboBoxModel extends DefaultComboBoxModel<String> {
	private ArrayList<WhetherVO> guguns;

	public GugunComboBoxModel(String strSido) throws ClassNotFoundException, SQLException {
		WhetherDAO dao = new WhetherDAO();
		guguns = dao.listGugun(strSido);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return guguns.size();
	}

	@Override
	public String getElementAt(int index) {
		WhetherVO vo = guguns.get(index);
		return vo.getStationName();
	}
}