package frame;

import java.sql.SQLException;
import javax.swing.JOptionPane;

import covidstat.CovidStatMkDb;
import hospital.HosMkDb;
import news.NewsMkDb;
import whether.WhetherMkDb;

public class Start {

	public static void main(String[] args) throws SQLException {
		FirstFrame ff = new FirstFrame();
		ff.startframe();
		System.out.println("뉴스 업데이트중...");
		new NewsMkDb();

		System.out.println("대기오염정보 업데이트중...");
		new WhetherMkDb();

		System.out.println("코로나병원정보 업데이트중...");
		new HosMkDb();

		System.out.println("코로나 현황 업데이트중...");
		new CovidStatMkDb();

		JOptionPane.showMessageDialog(null, "업데이트 완료.");

	}

}
