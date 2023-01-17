package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utillclass.JTextFieldLimit;
import utillclass.RoundedButton;

public class LoginPanel {
	JPanel loginPanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	JLabel title = new JLabel("Login");
	JLabel id = new JLabel("ID");
	JLabel password = new JLabel("Password");

	JTextField idText = new JTextField("");
	JTextField pwText = new JPasswordField("");

	RoundedButton ok = new RoundedButton("확인");
	RoundedButton no = new RoundedButton("취소");

	LoginPanel() {
		loginPanel.setBackground(new Color(28, 228, 251));
		loginPanel.setLayout(null);

		ok.setC(new Color(3, 199, 90));
		ok.setO(Color.white);
		ok.setFont(new Font("", Font.BOLD, 17));

		no.setC(Color.LIGHT_GRAY);
		no.setO(Color.white);
		no.setFont(new Font("", Font.BOLD, 17));

		title.setBounds(100, 90, 250, 50);
		title.setFont(new Font("Serif", Font.BOLD, 45));
		title.setForeground(new Color(204, 000, 153));

		id.setBounds(75, 170, 310, 50);
		id.setFont(new Font(null, Font.BOLD, 20));

		password.setBounds(75, 270, 310, 50);
		password.setFont(new Font(null, Font.BOLD, 20));

		idText.setBounds(80, 215, 280, 45);
		idText.setFont(new Font(null, Font.PLAIN, 20));
		idText.setBackground(Color.white);
		idText.setDocument((new JTextFieldLimit(10)));

		pwText.setBounds(80, 315, 280, 45);
		pwText.setFont(new Font(null, Font.PLAIN, 20));
		pwText.setBackground(Color.white);
		pwText.setDocument((new JTextFieldLimit(12)));

		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBackground(new Color(28, 228, 251));
		buttonPanel.setBounds(105, 410, 230, 40);
		buttonPanel.add(ok);
		buttonPanel.add(no);

		loginPanel.add(title);
		loginPanel.add(id);
		loginPanel.add(password);
		loginPanel.add(idText);
		loginPanel.add(pwText);
		loginPanel.add(buttonPanel);

	}

}
