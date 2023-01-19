package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utillclass.ConnDB;
import utillclass.JTextFieldLimit;
import utillclass.NumberField;
import utillclass.RoundedButton;

public class DeleteAc implements ActionListener {
	private ConnDB cd = new ConnDB();
	private JFrame delac;
	private JLabel lid = new JLabel("접속중인 ID");
	private JLabel lpassword = new JLabel("PASSWORD");
	private JLabel lrsid = new JLabel("생년월일 (주민등록번호 앞자리)");
	private JLabel lname = new JLabel("이름");

	private JTextField idText = new JTextField("");
	private JTextField pwText = new JPasswordField("");
	private NumberField rsidText = new NumberField();
	private JTextField nameText = new JTextField("");

	private RoundedButton del;
	private RoundedButton cancel;

	private String id;
	private String pwd;
	private String rsid;
	private String name;
	private String sido;
	private String dong;

	public DeleteAc() {
		delac = new JFrame("회원탈퇴");
		delac.setLayout(null);
		delac.setSize(370, 450);
		delac.setLocation(450, 100);
		delac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		delac.setVisible(true);

		lid.setBounds(35, 20, 210, 50);
		lid.setFont(new Font(null, Font.BOLD, 15));

		lpassword.setBounds(35, 80, 210, 50);
		lpassword.setFont(new Font(null, Font.BOLD, 15));

		lname.setBounds(35, 140, 210, 50);
		lname.setFont(new Font(null, Font.BOLD, 15));

		lrsid.setBounds(35, 200, 310, 50);
		lrsid.setFont(new Font(null, Font.BOLD, 15));

		idText.setBounds(45, 65, 280, 25);
		idText.setFont(new Font(null, Font.PLAIN, 15));
		idText.setBackground(Color.white);

		pwText.setBounds(45, 125, 280, 25);
		pwText.setFont(new Font(null, Font.PLAIN, 15));
		pwText.setBackground(Color.white);

		nameText.setBounds(45, 185, 280, 25);
		nameText.setFont(new Font(null, Font.PLAIN, 15));
		nameText.setBackground(Color.white);

		rsidText.setBounds(45, 245, 280, 25);
		rsidText.setFont(new Font(null, Font.PLAIN, 15));
		rsidText.setBackground(Color.white);
		rsidText.setDocument((new JTextFieldLimit(6)));

		del = new RoundedButton("확인");
		del.setC(new Color(3, 199, 90));
		del.setO(Color.white);
		del.setBounds(75, 305, 100, 50);

		cancel = new RoundedButton("취소");
		cancel.setC(Color.LIGHT_GRAY);
		cancel.setO(Color.white);
		cancel.setBounds(195, 305, 100, 50);

		delac.add(lid);
		delac.add(lpassword);
		delac.add(lrsid);
		delac.add(lname);
		delac.add(idText);
		delac.add(pwText);
		delac.add(rsidText);
		delac.add(nameText);
		delac.add(del);
		delac.add(cancel);

		del.addActionListener(this);
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

		if (del.equals(e.getSource())) {
			if (idText.getText().equals(id) && pwText.getText().equals(pwd) && rsidText.getText().equals(rsid)) {
				String sql1 = "delete from favorites where id = '" + id + "'";
				try {
					cd.stmt.executeUpdate(sql1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql2 = "delete from profile where pf_id = '" + id + "'";
				try {
					cd.stmt.executeUpdate(sql2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "탈퇴가 완료되었습니다.");
				delac.dispose();
				FirstFrame ff = new FirstFrame();
				ff.startframe();
			} else {
				JOptionPane.showMessageDialog(null, "기입된 정보가 일치하지 않습니다", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
		} else if (cancel.equals(e.getSource())) {
			delac.dispose();
			try {
				MainFrame mf = new MainFrame();
				mf.set(id, pwd, name, rsid, sido, dong);
				mf.startframe();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
