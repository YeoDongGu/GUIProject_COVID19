package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import covidstat.CovidStatMkDb;
import covidstat.CovidstatPanel;
import hospital.Hos;
import hospital.HosMkDb;
import news.NewsMkDb;
import news.NewsPanel;
import utillclass.ConnDB;
import utillclass.RoundedButton;
import whether.WhetherMkDb;
import whether.WhetherPanel;

/*
 메인 프레임
 */
public class MainFrame implements ActionListener {
	ConnDB cd = new ConnDB();
	protected JTabbedPane t;
	private JMenuBar mb;
	private JMenu update;
	private JMenuItem mcovidhos;
	private JMenuItem mcovidstat;
	private JMenuItem mwhether;
	private JMenuItem mnews;

	private JFrame mf;
	private JLabel user;

	private RoundedButton refresh;
	private RoundedButton logout;
	private RoundedButton delacc;
	private RoundedButton edit;

	public String id;
	public String pwd;
	public String sido;
	public String dong;
	public String rsid;
	public String name;

	public MainFrame() {
		user = new JLabel();
		user.setBounds(630, 20, 100, 50);

		mb = new JMenuBar();
		update = new JMenu("업데이트");
		mcovidhos = new JMenuItem("코로나 진료병원");
		mcovidstat = new JMenuItem("코로나현황");
		mwhether = new JMenuItem("미세먼지");
		mnews = new JMenuItem("뉴스");

		refresh = new RoundedButton("새로고침");
		refresh.setBounds(620, 80, 80, 50);
		refresh.setC(new Color(255, 247, 242));
		refresh.setO(new Color(247, 99, 12));

		logout = new RoundedButton("로그아웃");
		logout.setBounds(620, 140, 80, 50);
		logout.setC(new Color(255, 247, 242));
		logout.setO(new Color(247, 99, 12));

		delacc = new RoundedButton("회원 탈퇴");
		delacc.setBounds(620, 200, 80, 50);
		delacc.setC(new Color(255, 247, 242));
		delacc.setO(new Color(247, 99, 12));

		edit = new RoundedButton("회원 정보 수정");
		edit.setBounds(620, 260, 80, 50);
		edit.setC(new Color(255, 247, 242));
		edit.setO(new Color(247, 99, 12));

		mf = new JFrame("COVID_Information ver 1.0");
		mf.setSize(730, 780);
		mf.setLayout(null);
		mf.setJMenuBar(mb);
		mf.setLocation(600, 100);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		t = new JTabbedPane();
		t.setBounds(10, 0, 600, 700);

	}

	public void startframe() throws ClassNotFoundException, SQLException {
		WhetherPanel w = new WhetherPanel();
		Hos h = new Hos();
		NewsPanel n = new NewsPanel();
		CovidstatPanel cs = new CovidstatPanel();
		h.set(id, pwd, sido, dong);

		if (sido.equals("경북")) {
			h.comboBox1.setSelectedItem("대구"); // 경북에 코로나 진료전문 병원 정보가 없어서 대구로 대체
		} else {
			h.comboBox1.setSelectedItem(sido); // 로그인한 회원의 주소를 받아와서 첫화면으로 설정
		}

		w.comboBox1.setSelectedItem(sido); // 로그인한 회원의 주소를 받아와서 첫화면으로 설정
		w.comboBox2.setSelectedItem(dong); // 로그인한 회원의 주소를 받아와서 첫화면으로 설정
		user.setText("<html><body style='text-align:center;'>안녕하세요<br />" + name + "님 <br /> 환영합니다</body></html>");
		TabImage ti = new TabImage(25, 25);

		t.addTab("코로나 현황", ti.covid, cs.covidstatp);
		t.setBackgroundAt(0, Color.white);
		t.addTab("코로나 진료병원", ti.hos, h.hp);
		t.setBackgroundAt(1, Color.green);
		t.addTab("미세먼지", ti.dust, w.wp);
		t.setBackgroundAt(2, Color.CYAN);
		t.addTab("코로나 뉴스", ti.news, n.np);
		t.setBackgroundAt(3, Color.PINK);

		mf.add(t);
		mf.add(user);
		mf.add(refresh);
		mf.add(logout);
		mf.add(delacc);
		mf.add(edit);

		mb.add(update);
		update.add(mcovidhos);
		update.add(mcovidstat);
		update.add(mwhether);
		update.add(mnews);

		mcovidhos.addActionListener(this);
		mcovidstat.addActionListener(this);
		mwhether.addActionListener(this);
		mnews.addActionListener(this);
		refresh.addActionListener(this);
		logout.addActionListener(this);
		delacc.addActionListener(this);
		edit.addActionListener(this);

		mf.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (mcovidhos.equals(e.getSource())) {
			try {
				new HosMkDb();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (mcovidstat.equals(e.getSource())) {
			try {
				new CovidStatMkDb();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (mwhether.equals(e.getSource())) {
			try {
				new WhetherMkDb();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (mnews.equals(e.getSource())) {
			try {
				new NewsMkDb();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (refresh.equals(e.getSource())) {
			mf.dispose();
			MainFrame m = new MainFrame();
			m.set(id, pwd, name, rsid, sido, dong);
			try {
				m.startframe();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (logout.equals(e.getSource())) {
			mf.dispose();
			FirstFrame ff = new FirstFrame();
			ff.startframe();
		} else if (delacc.equals(e.getSource())) {
			DeleteAc da = new DeleteAc();
			da.set(id, pwd, name, rsid, sido, dong);
			mf.dispose();
		} else if (edit.equals(e.getSource())) {
			Edit ed = new Edit();
			ed.set(id, pwd, name, rsid, sido, dong);
			ed.startframe();
			mf.dispose();
		}

	}

	public void set(String id, String pwd, String name, String rsid, String sido, String dong) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.rsid = rsid;
		this.sido = sido;
		this.dong = dong;
	}

}
