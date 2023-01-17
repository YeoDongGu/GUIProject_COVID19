package frame;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utillclass.RoundedButton;

public class InitDisplay {
	public JPanel Init = new JPanel();
	private JPanel buttonPanel = new JPanel();

	private JLabel title = new JLabel("COVID-19");
	private JLabel subTitle = new JLabel("Information");

	public RoundedButton join = new RoundedButton("회원가입");
	public RoundedButton login = new RoundedButton("로그인");

	public InitDisplay() {

		Init.setBackground(new Color(28, 228, 251));
		Init.setLayout(null);

		join.setC(new Color(255, 247, 242));
		join.setO(new Color(247, 99, 12));

		login.setC(new Color(255, 247, 242));
		login.setO(new Color(247, 99, 12));

		join.setBounds(105, 400, 100, 50);
		login.setBounds(225, 400, 100, 50);

		title.setBounds(80, 160, 300, 50);
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(new Color(204, 000, 153));

		subTitle.setBounds(125, 200, 200, 40);
		subTitle.setFont(new Font("Serif", Font.BOLD, 35));
		subTitle.setForeground(Color.white);

		Init.add(title);
		Init.add(subTitle);
		Init.add(buttonPanel);
		Init.add(login);
		Init.add(join);
	}

}