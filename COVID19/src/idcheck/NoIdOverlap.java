package idcheck;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoIdOverlap implements ActionListener {
	JFrame noidoverlap = new JFrame();
	JPanel background = new JPanel();

	JLabel message = new JLabel("사용 가능한 ID 입니다");

	JButton ok = new JButton("확인");

	public NoIdOverlap() {
		noidoverlap.setTitle("ID Succes");
		noidoverlap.setSize(190, 100);
		noidoverlap.setLocation(575, 300);
		noidoverlap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		background.setLayout(null);
		background.setBackground(Color.white);

		message.setBounds(35, 10, 150, 20);

		ok.setBounds(60, 40, 70, 30);
		ok.addActionListener(this);

		background.add(message);
		background.add(ok);

		noidoverlap.add(background);

		noidoverlap.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		noidoverlap.dispose();
	}

}