package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import idcheck.EmptyID;
import idcheck.IdOverlap;
import idcheck.NoIdOverlap;
import utillclass.ConnDB;

public class FirstFrame implements ActionListener {
	private ConnDB cd = new ConnDB();
	private MainFrame mf = new MainFrame();
	private InitDisplay first = new InitDisplay();
	private LoginPanel logindisplay = new LoginPanel();
	private SignupPanel sg = new SignupPanel();
	private JFrame sf;
	private JDialog sud;
	private JDialog fad;
	private JPanel p2;
	private JPanel p3;

	private JLabel lsulog;
	private JButton sulog;

	private JLabel lfalog;
	private JButton falog;

	private String myid;
	private String mysido;
	private String mydong;
	private String mypwd;
	private String myrsid;
	private String myname;

	public FirstFrame() {
		sf = new JFrame();
		sf.setTitle("COVID_Information ver 1.0");
		sf.setSize(430, 640);
		sf.setLocation(450, 100);
		sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 로그인 성공 화면
		sud = new JDialog(sf, "로그인 성공", true);
		sud.setSize(200, 90);
		sud.setLocation(800, 350);
		sud.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// 로그인 실패 화면
		fad = new JDialog(sf, "로그인 실패", true);
		fad.setSize(200, 90);
		fad.setLocation(800, 350);
		fad.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		p2 = new JPanel();
		p3 = new JPanel();

		lsulog = new JLabel("로그인 성공");
		sulog = new JButton("확인");

		lfalog = new JLabel("로그인 실패");
		falog = new JButton("확인");

	}

	public void startframe() {
		sf.add(first.Init);
		sulog.addActionListener(this);
		falog.addActionListener(this);

		first.join.addActionListener(this);
		first.login.addActionListener(this);

		logindisplay.ok.addActionListener(this);
		logindisplay.no.addActionListener(this);

		sg.ok.addActionListener(this);
		sg.no.addActionListener(this);
		sg.checkOverlap.addActionListener(this);

		sud.add(p2);
		p2.add(lsulog);
		p2.add(sulog);

		fad.add(p3);
		p3.add(lfalog);
		p3.add(falog);

		sf.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (logindisplay.ok.equals(e.getSource())) {
			LoginDAO ldao = new LoginDAO();
			ArrayList<LoginVo> list = ldao.list(logindisplay.idText.getText(), logindisplay.pwText.getText());
			for (int i = 0; i < list.size(); i++) {
				LoginVo data = (LoginVo) list.get(i);
				myid = data.getId();
				mypwd = data.getPwd();
				myname = data.getName();
				myrsid = data.getRsid();
				mysido = data.getSido();
				mydong = data.getDong();
			}
			System.out.println("아이디 : " + myid);
			System.out.println("비밀번호 : " + mypwd);
			System.out.println("이름 : " + myname);
			System.out.println("생년월일 : " + myrsid);
			System.out.println("주소(시/도) : " + mysido);
			System.out.println("주소(동/읍/면/리) : " + mydong);
			if (logindisplay.idText.getText().equals(myid) && logindisplay.pwText.getText().equals(mypwd)) {
				sud.setVisible(true);
			} else {
				fad.setVisible(true);
			}
		} else if (first.join.equals(e.getSource())) {
			sf.remove(first.Init);
			sf.add(sg.sp);
			sf.revalidate();
			sf.repaint();
		} else if (sulog.equals(e.getSource())) {
			sud.setVisible(false);
			sf.setVisible(false);
			mf.set(myid, mypwd, myname, myrsid, mysido, mydong);
			try {
				mf.startframe();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (falog.equals(e.getSource())) {
			fad.setVisible(false);
		} else if (first.login.equals(e.getSource())) {
			sf.remove(first.Init);
			sf.add(logindisplay.loginPanel);
			sf.revalidate();
			sf.repaint();
		} else if (logindisplay.no.equals(e.getSource())) {
			sf.remove(logindisplay.loginPanel);
			sf.add(first.Init);
			sf.revalidate();
			sf.repaint();
		} else if (sg.ok.equals(e.getSource())) {
			if (sg.check == true) {
				SignupDAO sdao = new SignupDAO();
				sdao.list(sg.idText.getText(), sg.pwText.getText(), sg.rsidText.getText(), sg.nnText.getText(),
						(String) sg.comboBox1.getSelectedItem(), (String) sg.comboBox2.getSelectedItem());
				joinDisplayInit();
			} else {
				JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}

		} else if (sg.no.equals(e.getSource())) {
			joinDisplayInit();
			sf.remove(sg.sp);
			sf.add(first.Init);
			sf.revalidate();
			sf.repaint();
		} else if (sg.checkOverlap.equals(e.getSource())) {
			overlapCheck(sg.idText.getText());
		}

	}

	private void joinDisplayInit() {
		sg.idText.setText("");
		sg.pwText.setText("");
		sg.rsidText.setText("");
		sg.nnText.setText("");
		sg.comboBox1.setSelectedItem(null);
		sg.comboBox2.setSelectedItem(null);
		sg.idText.setEditable(true);
		sg.check = false;
	}

	private void overlapCheck(String id) {

		if (id.equals("")) {
			sg.check = false;
			new EmptyID();
			return;
		}

		try {

			cd.stmt = (Statement) cd.con.createStatement();
			cd.rs = cd.stmt.executeQuery("select * from profile where pf_id = '" + id + "'");
			String getID = "";

			while (cd.rs.next()) {
				getID = cd.rs.getString("pf_id");
			}

			if (getID.equals("")) {
				new NoIdOverlap();
				sg.check = true;
				sg.idText.setEditable(false);
			} else {
				new IdOverlap();
				sg.check = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
