package news;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import frame.ConnDB;

public class NewsPanel {
	ConnDB cd = new ConnDB();
	public JPanel np;
	public JPanel np2;
	private JLabel[][] contentlabel = new JLabel[10][4];
	private JLabel[] empty = new JLabel[9];

	String contents[][] = new String[10][4];
	String str[] = { "title", "description", "link", "pubdate" };

	public JScrollPane scrollPane;
	private Font font1 = new Font("HY견고딕", Font.PLAIN, 20);
	private Font font2 = new Font("f", Font.BOLD, 15);

	public NewsPanel() {
		getNews();
		np = new JPanel();
		np.setBackground(Color.white);
		np.setLayout(null);
		np.setBorder(new EmptyBorder(5, 5, 5, 5));

		np2 = new JPanel();
		np2.setBackground(Color.white);
		np2.setLayout(new GridLayout(49, 0));

		scrollPane = new JScrollPane(np2);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // 스크롤바가 언제생기는지 설정 . 항상
		scrollPane.setBounds(0, 0, 590, 680);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도
		np.add(scrollPane);

		for (int i = 0; i < empty.length; i++) {
			empty[i] = new JLabel("");
		}

		for (int i = 0; i < contentlabel.length; i++) {
			for (int j = 0; j < contentlabel[i].length; j++) {
				if (j == 0) {
					String str = contents[i][j].replaceAll("[&quotapos;]", "");
					contentlabel[i][j] = new JLabel(str);
					contentlabel[i][j].setFont(font1);
				} else if (j == 1) {
					String str = contents[i][j].replaceAll("[<b>/]", "");
					contentlabel[i][j] = new JLabel(str);
					contentlabel[i][j].setFont(font2);
				} else if (j == 2) {
					contentlabel[i][j] = new JLabel(contents[i][j]);
					contentlabel[i][j].setForeground(Color.blue);
					contentlabel[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
					contentlabel[i][j].setFont(font2);
					goWebsite(contentlabel[i][j]);
				} else {
					contentlabel[i][j] = new JLabel(contents[i][j]);
					contentlabel[i][j].setFont(font2);
				}
			}
		}

		//
		for (int i = 0; i < contentlabel.length; i++) {
			for (int j = 0; j < contentlabel[i].length; j++) {
				np2.add(contentlabel[i][j]);
			}
			if (i < 9) {
				np2.add(empty[i]);
			}
		}

	}

	public void getNews() {
		String sql = "select title,link,description, pubdate from news";
		try {
			cd.rs = cd.stmt.executeQuery(sql);
			for (int i = 0; i < contents.length; i++) {
				cd.rs.next();
				for (int j = 0; j < contents[i].length; j++) {
					contents[i][j] = cd.rs.getString(str[j]);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void goWebsite(JLabel website) {
		website.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(website.getText()));
				} catch (URISyntaxException | IOException ex) {
					// It looks like there's a problem
				}
			}
		});
	}

}
