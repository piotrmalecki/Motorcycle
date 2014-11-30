package pl.wbd.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

public class OnlyDigitsInput implements KeyListener {

	public boolean isDigit = true;
	private JLabel label;
	private boolean isComaAvailable;

	public OnlyDigitsInput(JLabel label, boolean isComaAvailable) {
		this.label = label;
		this.isComaAvailable = isComaAvailable;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (isComaAvailable) {
			if (!(Character.isDigit(c)) && c != '\b' && c != ',') {
				e.consume();
				isDigit = false;
				if (label != null)
					label.setText("Wprowadzaj tylko liczby!");
			} else {
				isDigit = true;
				if (label != null)
					label.setText("");
			}
		} else {
			if (!(Character.isDigit(c)) && c != '\b' ) {
				e.consume();
				isDigit = false;
				if (label != null)
					label.setText("Wprowadzaj tylko liczby!");
			} else {
				isDigit = true;
				if (label != null)
					label.setText("");
			}
		}
		
		

	}

	public boolean isDigit() {
		return isDigit;
	}
}
