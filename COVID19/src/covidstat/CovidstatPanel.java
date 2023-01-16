package covidstat;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import utillclass.ConnDB;

public class CovidstatPanel {
	public JPanel covidstatp;
	public JTable jt;

	ConnDB cd = new ConnDB();
	String sum[] = new String[6];
	String contents[][] = new String[19][6];
	String str[] = { "gubun", "incdec", "localocccnt", "overflowcnt", "qurrate", "stdday" };
	String header[] = { "시/도", "확진자", "지역 발생수", "해외 유입수", "10만명당 확진", "기준 일자" };

	public CovidstatPanel() {
		dataInp();
		covidstatp = new JPanel();
		covidstatp.setLayout(null);
		covidstatp.setBackground(Color.pink);

		jt = new JTable(contents, header);
		jt.setRowHeight(36);
		jt.setShowVerticalLines(false);
		jt.setShowHorizontalLines(false);
		jt.setFont(new Font("f", Font.BOLD, 15));
		jt.getTableHeader().setFont(new Font("f", Font.BOLD, 13));
		tableCellCenter(jt);
		JScrollPane jscp1 = new JScrollPane(jt);

		jscp1.setLocation(0, 0);
		jscp1.setSize(600, 680);

		covidstatp.add(jscp1);
	}

	public void dataInp() {
		try {

			String sql = "SELECT gubun, incdec, localocccnt , overflowcnt, qurrate, stdday FROM covidstat order by incdec desc";

			cd.rs = cd.stmt.executeQuery(sql);

			for (int i = 0; i < contents.length; i++) {
				cd.rs.next();
				for (int j = 0; j < contents[i].length; j++) {
					contents[i][j] = cd.rs.getString(str[j]);
				}

			}

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	private void tableCellCenter(JTable t) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = t.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(centerRenderer);
		}
	}

}
