package pl.wbd.ui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import oracle.jrockit.jfr.tools.ConCatRepository;

import pl.wbd.managers.DepartStatementHandler;
import pl.wbd.managers.StaffStatementHandler;
import pl.wbd.panels.DepartPanel;
import pl.wbd.panels.StaffPanel;
import pl.wbd.util.OnlyDigitsInput;

@SuppressWarnings("serial")
public class DepartAdder extends JFrame {

	private final Dimension dim = new Dimension(280, 330);
	private JPanel contentPane;
	private JTextField textMiasto;
	private JTextField textKod;
	private JTextField textUlica;
	private JTextField textBudynek;
	private JTextField textLokal;

	private DepartStatementHandler statementHandler;
	private DepartPanel panel;
	private DepartAdder frame;
	private JLabel errorLabel;

	/**
	 * Create the frame.
	 */
	public DepartAdder(DepartStatementHandler sth, DepartPanel st) {

		statementHandler = sth;
		panel = st;
		frame = this;

		setAlwaysOnTop(true);
		setTitle("Salon motocyklowy - Nowy oddział");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 417);
		setLocationRelativeTo(null);
		setSize(new Dimension(289, 275));
		setPreferredSize(dim);
		setResizable(false);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnNewButton = new JButton("Dodaj");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 7;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println(textBudynek.getText() +" kod: "+textKod.getText().substring(0,2)+textKod.getText().substring(3,6));
					statementHandler.createDepartment(textKod.getText().substring(0,2)+textKod.getText().substring(3,6), textMiasto.getText(), textUlica.getText(), textBudynek.getText(), textLokal.getText());
					panel.removeAllRows();
					panel.loadAllData();
					dispose();
				} catch (SQLException e) {
					System.err.println("Blad dodajac do bazy "
							+ e.getErrorCode() + " mes: " + e.getMessage());
					if (e.getErrorCode() == 1400) {
						errorLabel
								.setText("Nie wprowadzono wszystkich wartości bądź wartość nieprawidłową");

					} else
						JOptionPane.showMessageDialog(frame,
								"Spróbuj ponownie!", "Nieznany błąd",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel lblDodajNowegoPracownika = new JLabel("Dodaj nowy oddział");
		lblDodajNowegoPracownika.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblDodajNowegoPracownika = new GridBagConstraints();
		gbc_lblDodajNowegoPracownika.gridheight = 2;
		gbc_lblDodajNowegoPracownika.gridwidth = 6;
		gbc_lblDodajNowegoPracownika.insets = new Insets(0, 0, 5, 0);
		gbc_lblDodajNowegoPracownika.gridx = 0;
		gbc_lblDodajNowegoPracownika.gridy = 0;
		contentPane.add(lblDodajNowegoPracownika, gbc_lblDodajNowegoPracownika);

		JLabel lblImi = new JLabel("Kod pocztowy:");
		GridBagConstraints gbc_lblImi = new GridBagConstraints();
		gbc_lblImi.anchor = GridBagConstraints.EAST;
		gbc_lblImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblImi.gridx = 0;
		gbc_lblImi.gridy = 2;
		contentPane.add(lblImi, gbc_lblImi);

		textKod = new JTextField();
		GridBagConstraints gbc_textKod = new GridBagConstraints();
		gbc_textKod.gridwidth = 4;
		gbc_textKod.insets = new Insets(0, 0, 5, 5);
		gbc_textKod.fill = GridBagConstraints.HORIZONTAL;
		gbc_textKod.gridx = 1;
		gbc_textKod.gridy = 2;
		contentPane.add(textKod, gbc_textKod);
		textKod.setColumns(10);

		JLabel lblDrugieImi = new JLabel("Miasto:");
		GridBagConstraints gbc_lblDrugieImi = new GridBagConstraints();
		gbc_lblDrugieImi.anchor = GridBagConstraints.EAST;
		gbc_lblDrugieImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblDrugieImi.gridx = 0;
		gbc_lblDrugieImi.gridy = 3;
		contentPane.add(lblDrugieImi, gbc_lblDrugieImi);

		textMiasto = new JTextField();
		GridBagConstraints gbc_textMiasto = new GridBagConstraints();
		gbc_textMiasto.gridwidth = 4;
		gbc_textMiasto.insets = new Insets(0, 0, 5, 5);
		gbc_textMiasto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMiasto.gridx = 1;
		gbc_textMiasto.gridy = 3;
		contentPane.add(textMiasto, gbc_textMiasto);
		textMiasto.setColumns(10);

		JLabel lblNazwisko = new JLabel("Ulica:");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.anchor = GridBagConstraints.EAST;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 0;
		gbc_lblNazwisko.gridy = 4;
		contentPane.add(lblNazwisko, gbc_lblNazwisko);

		textUlica = new JTextField();
		GridBagConstraints gbc_textUlica = new GridBagConstraints();
		gbc_textUlica.gridwidth = 4;
		gbc_textUlica.insets = new Insets(0, 0, 5, 5);
		gbc_textUlica.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUlica.gridx = 1;
		gbc_textUlica.gridy = 4;
		contentPane.add(textUlica, gbc_textUlica);
		textUlica.setColumns(10);

		JLabel lblDataUrodzenia = new JLabel("Numer budynku:");
		GridBagConstraints gbc_lblDataUrodzenia = new GridBagConstraints();
		gbc_lblDataUrodzenia.anchor = GridBagConstraints.EAST;
		gbc_lblDataUrodzenia.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataUrodzenia.gridx = 0;
		gbc_lblDataUrodzenia.gridy = 5;
		contentPane.add(lblDataUrodzenia, gbc_lblDataUrodzenia);

		textBudynek = new JTextField();
		GridBagConstraints gbc_textBudynek = new GridBagConstraints();
		gbc_textBudynek.gridwidth = 4;
		gbc_textBudynek.insets = new Insets(0, 0, 5, 5);
		gbc_textBudynek.fill = GridBagConstraints.HORIZONTAL;
		gbc_textBudynek.gridx = 1;
		gbc_textBudynek.gridy = 5;
		contentPane.add(textBudynek, gbc_textBudynek);
		textBudynek.setColumns(10);

		JLabel lblNumerTelefonu = new JLabel("Numer lokalu:");
		GridBagConstraints gbc_lblNumerTelefonu = new GridBagConstraints();
		gbc_lblNumerTelefonu.anchor = GridBagConstraints.EAST;
		gbc_lblNumerTelefonu.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumerTelefonu.gridx = 0;
		gbc_lblNumerTelefonu.gridy = 6;
		contentPane.add(lblNumerTelefonu, gbc_lblNumerTelefonu);

		textLokal = new JTextField();
		GridBagConstraints gbc_textLokal = new GridBagConstraints();
		gbc_textLokal.gridwidth = 4;
		gbc_textLokal.insets = new Insets(0, 0, 5, 5);
		gbc_textLokal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLokal.gridx = 1;
		gbc_textLokal.gridy = 6;
		contentPane.add(textLokal, gbc_textLokal);
		textLokal.setColumns(10);

		JButton btnAnuluj = new JButton("Anuluj");
		GridBagConstraints gbc_btnAnuluj = new GridBagConstraints();
		gbc_btnAnuluj.gridwidth = 2;
		gbc_btnAnuluj.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnuluj.gridx = 2;
		gbc_btnAnuluj.gridy = 7;
		contentPane.add(btnAnuluj, gbc_btnAnuluj);

		btnAnuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 6;
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 8;
		contentPane.add(errorLabel, gbc_errorLabel);
		textLokal.addKeyListener(new OnlyDigitsInput(errorLabel, false));
	}
}
