package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utillclass.JTextFieldLimit;
import utillclass.NumberField;
import utillclass.RoundedButton;
import whether.GugunComboBoxModel;
import whether.WhetherSidoComboBoxModel;


public class SignupPanel {
	JPanel sp = new JPanel();
	JPanel buttonPanel = new JPanel();

	JLabel title = new JLabel("JOIN");
	JLabel id = new JLabel("ID (최대10자)");
	JLabel password = new JLabel("PASSWORD (최대12자)");
	JLabel nickname = new JLabel("이름");
	JLabel rsid = new JLabel("생년월일 (주민등록번호 앞자리)");
	JLabel address = new JLabel("주소");

	JTextField idText = new JTextField("");
	JTextField pwText = new JPasswordField("");
	JTextField nnText = new JTextField("");
	NumberField rsidText = new NumberField();

	RoundedButton checkOverlap = new RoundedButton("중복확인");
	RoundedButton ok = new RoundedButton("확인");
	RoundedButton no = new RoundedButton("취소");

	JComboBox<String> comboBox1;
	JComboBox<String> comboBox2;

	boolean check;

	SignupPanel() {
		sp.setBackground(new Color(28, 228, 251));
		sp.setLayout(null);

		ok.setC(new Color(3, 199, 90));
		ok.setO(Color.white);
		ok.setFont(new Font("", Font.BOLD, 17));

		no.setC(Color.LIGHT_GRAY);
		no.setO(Color.white);
		no.setFont(new Font("", Font.BOLD, 17));

		title.setBounds(155, 10, 300, 50);
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(new Color(204, 000, 153));

		id.setBounds(75, 60, 310, 50);
		id.setFont(new Font(null, Font.BOLD, 15));

		password.setBounds(75, 135, 310, 50);
		password.setFont(new Font(null, Font.BOLD, 15));

		nickname.setBounds(75, 205, 310, 50);
		nickname.setFont(new Font(null, Font.BOLD, 15));

		rsid.setBounds(75, 275, 310, 50);
		rsid.setFont(new Font(null, Font.BOLD, 15));

		address.setBounds(75, 345, 310, 50);
		address.setFont(new Font(null, Font.BOLD, 15));

		idText.setBounds(80, 105, 280, 40);
		idText.setFont(new Font(null, Font.PLAIN, 15));
		idText.setBackground(Color.white);
		idText.setDocument((new JTextFieldLimit(10)));

		pwText.setBounds(80, 175, 280, 40);
		pwText.setFont(new Font(null, Font.PLAIN, 15));
		pwText.setBackground(Color.white);
		pwText.setDocument((new JTextFieldLimit(12)));

		nnText.setBounds(80, 245, 280, 40);
		nnText.setFont(new Font(null, Font.PLAIN, 15));
		nnText.setBackground(Color.white);

		rsidText.setBounds(80, 315, 280, 40);
		rsidText.setFont(new Font(null, Font.PLAIN, 15));
		rsidText.setBackground(Color.white);
		rsidText.setDocument((new JTextFieldLimit(6)));

		checkOverlap.setBounds(180, 75, 100, 25);
		checkOverlap.setC(new Color(3, 199, 90));
		checkOverlap.setO(Color.white);

		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBackground(new Color(28, 228, 251));
		buttonPanel.setBounds(105, 480, 230, 40);
		buttonPanel.add(ok);
		buttonPanel.add(no);

		comboBox1 = new JComboBox<String>();
		comboBox1.setBounds(80, 385, 130, 30);
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
		comboBox2.setBounds(220, 385, 115, 30);

		try {
			comboBox1.setModel(new WhetherSidoComboBoxModel());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sp.add(title);
		sp.add(checkOverlap);
		sp.add(id);
		sp.add(password);
		sp.add(nickname);
		sp.add(rsid);
		sp.add(address);
		sp.add(idText);
		sp.add(pwText); 
		sp.add(nnText);
		sp.add(rsidText);
		sp.add(comboBox1);
		sp.add(comboBox2);
		sp.add(buttonPanel);
	}

}
