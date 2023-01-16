package whether;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class SidoComboBoxModel extends DefaultComboBoxModel<String> {
	private ArrayList<WhetherVO> sidos;

	
	public SidoComboBoxModel() throws ClassNotFoundException, SQLException {
		WhetherDAO dao = new WhetherDAO();
		sidos = dao.listSido();
	}
	
	@Override
	public int getSize() {
		return sidos.size();
	}
	
	@Override
	public String getElementAt(int index) {
		WhetherVO vo = sidos.get(index);
		return vo.getSidoName();
	}
}