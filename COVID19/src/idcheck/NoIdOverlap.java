package idcheck;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoIdOverlap extends JFrame implements ActionListener{
	JPanel background = new JPanel();
	
	JLabel message = new JLabel("사용 가능한 ID 입니다");
	
	JButton ok = new JButton("확인");
	
	public NoIdOverlap() {
		setTitle("ID Succes");
		setSize(190, 100);
		setLocation(575, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		background.setLayout(null);
		background.setBackground(Color.white);
		
		message.setBounds(35, 10, 150, 20);
		
		ok.setBounds(60, 40, 70, 30);
		ok.addActionListener(this);
		
		background.add(message);
		background.add(ok);
		
		add(background);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
	
}