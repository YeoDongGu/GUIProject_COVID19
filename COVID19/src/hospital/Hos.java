package hospital;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import frame.ConnDB;
import frame.MainFrame;
import utillclass.GetMap;
/*
 코로나 병원 정보 패널
 */
public class Hos implements MouseListener, ActionListener {
	MainFrame mf = new MainFrame();
	private JList<String> list;
	public JPanel hp;
	public JPanel ctp;
	public JPanel panel;

	private Font font1;
	public JComboBox<String> comboBox1;
	private JButton Fvadd;
	private JButton Fvdel;
	private JButton Favlist;
	ConnDB cd = new ConnDB();

	public String id;
	public String pwd;
	public String sido;
	public String dong;

	public Hos() throws ClassNotFoundException, SQLException {
		hp = new JPanel();
		hp.setBackground(Color.white);
		hp.setLayout(null);
		hp.setBorder(new EmptyBorder(5, 5, 5, 5));
		font1 = new Font("f", Font.BOLD, 15);

		
		/*
		 콤보 박스에서 시/도 명을 선택하면 sido 필드에 해당 시/도 명을 가지고있는 레코드들을 리스트에 출력
		 */
		comboBox1 = new JComboBox<String>();
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					try {
						list.setModel(new AddressListModel((String) comboBox1.getSelectedItem()));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		comboBox1.setBounds(12, 10, 115, 23);
		hp.add(comboBox1);

		Fvadd = new JButton("즐겨찾기 등록");
		Fvadd.setBounds(140, 10, 115, 23);
		hp.add(Fvadd);
		Fvadd.addActionListener(this);

		Fvdel = new JButton("즐겨찾기 삭제");
		Fvdel.setBounds(270, 10, 115, 23);
		hp.add(Fvdel);
		Fvdel.addActionListener(this);

		Favlist = new JButton("즐겨찾기 목록");
		Favlist.setBounds(400, 10, 115, 23);
		hp.add(Favlist);
		Favlist.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 40, 565, 600);
		hp.add(scrollPane);

		list = new JList<String>();
		scrollPane.setViewportView(list);
		list.addMouseListener(this);
		list.setFont(font1);

		// 초기 시도 데이터 입력
		comboBox1.setModel(new SidoComboBoxModel());
	}

	public void set(String id, String pwd, String sido, String dong) {
		this.id = id;
		this.pwd = pwd;
		this.sido = sido;
		this.dong = dong;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		 더블클릭 시 해당 리스트 정보를 GetMap 으로 넘겨서 지도 화면 frame 출력
		 */
		if (e.getClickCount() == 2) {
			List<String> ls = list.getSelectedValuesList();
			for (String value : ls) {
				new GetMap(value);
			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Fvadd.equals(e.getSource())) {
			List<String> ls = list.getSelectedValuesList();
			for (String value : ls) {
				try {
					String query = "select * from favorites ";
					query += "where id = '" + id + "'";
					query += "and hos_inf = '" + value + "'";

					System.out.println("SQL : " + query);
					cd.rs = cd.stmt.executeQuery(query);
					cd.rs.last();
					if (cd.rs.getRow() == 0) {
						String sql = "insert into favorites values('" + id + "','" + value + "')";
						cd.stmt.executeUpdate(sql);
						System.out.println(value + "가 " + id + "님 즐겨찾기에 등록되었습니다");
						JOptionPane.showMessageDialog(null, value + "가 " + id + "님 즐겨찾기에 등록되었습니다");
					} else {
						JOptionPane.showMessageDialog(null, "이미 등록된 병원입니다", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (Fvdel.equals(e.getSource())) {
			List<String> ls = list.getSelectedValuesList();
			for (String value : ls) {
				String sql = "delete from favorites where id ='" + id + "' and hos_inf = '" + value + "'";
				try {
					cd.stmt.executeUpdate(sql);
					System.out.println(value + "가 " + id + "님 즐겨찾기에서 삭제되었습니다");
					JOptionPane.showMessageDialog(null, value + "가 " + id + "님 즐겨찾기에서 삭제되었습니다");
					list.setModel(new FavoritesListModel(id));
				} catch (SQLException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "등록되지 않은 병원입니다", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		} else if (Favlist.equals(e.getSource())) {
			try {
				list.setModel(new FavoritesListModel(id));
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}
