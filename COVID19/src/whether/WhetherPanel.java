package whether;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import utillclass.ConnDB;

public class WhetherPanel {
	ConnDB cd = new ConnDB();
	WhetherContent wc = new WhetherContent();
	private JList<String> list;
	public JPanel wp;
	public JPanel content;
	public JComboBox<String> comboBox1;
	public JComboBox<String> comboBox2;

	public WhetherPanel() throws ClassNotFoundException, SQLException {

		wp = new JPanel();
		wp.setBackground(Color.white);
		wp.setLayout(null);
		wp.setBorder(new EmptyBorder(5, 5, 5, 5));

		comboBox1 = new JComboBox<String>();
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					try {
						comboBox2.setModel(new GugunComboBoxModel((String) comboBox1.getSelectedItem()));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		comboBox1.setBounds(12, 10, 115, 23);
		wp.add(comboBox1);

		comboBox2 = new JComboBox<String>();
		comboBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg1) {
				if (arg1.getStateChange() == ItemEvent.SELECTED) {
					try {
						if (content == null) {
							list.setModel(new WhetherAddressListModel((String) comboBox1.getSelectedItem(),
									(String) comboBox2.getSelectedItem()));
							wc.startcontent((String) comboBox2.getSelectedItem());
							content = wc.ctp;
							wp.add(content);
							wp.revalidate();
							wp.repaint();
						} else {
							wp.remove(content);
							list.setModel(new WhetherAddressListModel((String) comboBox1.getSelectedItem(),
									(String) comboBox2.getSelectedItem()));
							wc.startcontent((String) comboBox2.getSelectedItem());
							content = wc.ctp;
							wp.add(content);
							wp.revalidate();
							wp.repaint();
							System.out.println(wc.pm10value);
							System.out.println(wc.pm25value);
							System.out.println(wc.no2value);
							System.out.println(wc.o3value);
							System.out.println(wc.covalue);
							System.out.println(wc.so2value);
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		comboBox2.setBounds(139, 10, 115, 23);
		wp.add(comboBox2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 40, 565, 30);
		wp.add(scrollPane);

		list = new JList<String>();
		scrollPane.setViewportView(list);

		// ?????? ?????? ????????? ??????
		comboBox1.setModel(new WhetherSidoComboBoxModel());

	}

}
