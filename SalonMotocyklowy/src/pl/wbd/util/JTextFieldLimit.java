package pl.wbd.util;

import javax.swing.text.*;

/**
 * Klasa odpowiedzialna za ograniczenie długości wpisywanego tekstu do pola
 * tekstowego.
 * 
 * @author Mateusz Filipowicz
 * @version 1.0.0
 * @see javax.swing.text.PlainDocument
 * 
 */
@SuppressWarnings("serial")
public class JTextFieldLimit extends PlainDocument {
	/**
	 * Ilość znaków jakie można wprowadzić do pola tekstowego.
	 */
	private int limit;

	/**
	 * Konstruktor klasy.
	 * 
	 * @param limit
	 *            maksymalna ilość znaków do wprowadzenia.
	 */
	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	/**
	 * Nadpisana metoda z klasy {@link PlainDocument}, odpowiedzialna za
	 * wprowadzanie tekstu i ograniczenie jego długości.
	 */

	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}
