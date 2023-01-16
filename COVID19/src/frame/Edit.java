package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utillclass.RoundedButton;
import whether.GugunComboBoxModel;
import whether.SidoComboBoxModel;

public class Edit implements ActionListener {
	ConnDB cd = new ConnDB();
	MainFrame mf = new MainFrame();
	private JFrame edit;
	private RoundedButton bid = new RoundedButton("ID");
	private RoundedButton bpassword = new RoundedButton("PASSWORD");
	private RoundedButton badd = new RoundedButton("주소");

	private JTextField idText = new JTextField("");
	private JTextField pwText = new JPasswordField("");

	private JComboBox<String> comboBox1;
	private JComboBox<String> comboBox2;

	private RoundedButton ed;
	private RoundedButton cancel;

	String id;
	String pwd;
	String rsid;
	String name;
	String sido;
	String dong;
	public Edit() {
		
	}
	public void startframe() {
		edit = new JFrame("회원정보 수정");
		edit.setLayout(null);
		edit.setSize(370, 450);
		edit.setLocation(450, 100);
		edit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		edit.setVisible(true);

		bid.setBounds(45, 40, 30, 30);
		bid.setFont(new Font(null, Font.BOLD, 15));
		bid.setC(new Color(238, 238, 238));
		bid.setO(Color.black);

		bpassword.setBounds(45, 110, 90, 30);
		bpassword.setFont(new Font(null, Font.BOLD, 15));
		bpassword.setC(new Color(238, 238, 238));
		bpassword.setO(Color.black);

		badd.setBounds(45, 180, 40, 30);
		badd.setFont(new Font(null, Font.BOLD, 15));
		badd.setC(new Color(238, 238, 238));
		badd.setO(Color.black);

		idText.setBounds(45, 75, 280, 35);
		idText.setFont(new Font(null, Font.PLAIN, 15));
		idText.setBackground(Color.white);
		idText.setText(id);
		idText.setEditable(false);

		pwText.setBounds(45, 145, 280, 35);
		pwText.setFont(new Font(null, Font.PLAIN, 15));
		pwText.setBackground(Color.white);

		comboBox1 = new JComboBox<String>();
		comboBox1.setBounds(45, 215, 130, 30);
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
		comboBox2 = new JComboBox<String>();
		comboBox2.setBounds(185, 215, 115, 30);

		try {
			comboBox1.setModel(new SidoComboBoxModel());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ed = new RoundedButton("수정");
		ed.setC(new Color(3, 199, 90));
		ed.setO(Color.white);
		ed.setBounds(75, 305, 100, 50);

		cancel = new RoundedButton("취소");
		cancel.setC(Color.LIGHT_GRAY);
		cancel.setO(Color.white);
		cancel.setBounds(195, 305, 100, 50);

		edit.add(bid);
		edit.add(bpassword);
		edit.add(idText);
		edit.add(pwText);
		edit.add(ed);
		edit.add(cancel);
		edit.add(badd);
		edit.add(comboBox1);
		edit.add(comboBox2);

		ed.addActionListener(this);
		bid.addActionListener(this);
		bpassword.addActionListener(this);
		badd.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void set(String id, String pwd, String name, String rsid, String sido, String dong) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.rsid = rsid;
		this.sido = sido;
		this.dong = dong;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ed.equals(e.getSource())) {
			if (!idText.getText().equals("") && !pwText.getText().equals("") && comboBox1.getSelectedItem() != null
					&& comboBox2.getSelectedItem() != null) {
				String sql2 = "update profile set  pf_pwd = '" + pwText.getText() + "', pf_add1 = '"
						+ comboBox1.getSelectedItem() + "', pf_add2 = '" + comboBox2.getSelectedItem()
						+ "' where pf_id ='" + id + "'";
				try {
					cd.stmt.executeUpdate(sql2);
					JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다.");
					pwd = pwText.getText();
					sido = (String) comboBox1.getSelectedItem();
					dong = (String) comboBox2.getSelectedItem();
					mf.set(id, pwd, name, rsid, sido, dong);
					mf.startframe();
					edit.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "회원정보 수정에 실패하였습니다. 정보를 전부 입력하세요", "ERROR_MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (bpassword.equals(e.getSource())) {
			pwText.setText(pwd);
			pwText.setEditable(false);
		} else if (badd.equals(e.getSource())) {
			comboBox1.setSelectedItem(sido);
			comboBox1.setEditable(false);
			comboBox2.setSelectedItem(dong);
			comboBox2.setEditable(false);
		}

		if (cancel.equals(e.getSource())) {
			edit.dispose();
			mf.set(id, pwd, name, rsid, sido, dong);
			try {
				mf.startframe();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
