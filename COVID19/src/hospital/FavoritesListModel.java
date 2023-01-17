package hospital;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class FavoritesListModel extends AbstractListModel<String> {
	private ArrayList<FavVO> favorites;

	public FavoritesListModel(String favid) throws ClassNotFoundException, SQLException {
		HosDAO dao = new HosDAO();
		favorites = dao.listFavorites(favid);
	}

	@Override
	public int getSize() {
		return favorites.size();
	}

	@Override
	public String getElementAt(int index) {
		FavVO vo = favorites.get(index);
		String favorites = String.format("%s", vo.getHos_inf());
		return favorites;
	}

}