package utillclass;

import java.awt.event.*;

import javax.swing.*;

/*
 텍스트필드 숫자만 쓸수있게 제한
 */
public class NumberField extends JTextField implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char c;
	public NumberField() {
		addKeyListener(this);
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		// Get the current character you typed...
		c = e.getKeyChar();

		if (!Character.isDigit(c)) {
			e.consume();
			return;
		}
	}
}