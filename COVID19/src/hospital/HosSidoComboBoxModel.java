package hospital;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class HosSidoComboBoxModel extends DefaultComboBoxModel<String> {
	private ArrayList<HosVO> sidos;

	public HosSidoComboBoxModel() throws ClassNotFoundException, SQLException {
		HosDAO dao = new HosDAO();
		sidos = dao.listSido();
	}

	@Override
	public int getSize() {
		return sidos.size();
	}

	@Override
	public String getElementAt(int index) {
		HosVO vo = sidos.get(index);
		return vo.getSidonm();
	}
}