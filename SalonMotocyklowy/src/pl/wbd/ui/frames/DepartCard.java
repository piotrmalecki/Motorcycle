package pl.wbd.ui.frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pl.wbd.managers.DepartStatementHandler;
import pl.wbd.panels.DepartPanel;

@SuppressWarnings("serial")
public class DepartCard extends JFrame {

	private JPanel contentPane;
	private JTextField kod;
	private JTextField miasto;
	private JTextField ulica;
	private JTextField budynki;
	private JTextField lokale;
	private JTextField idOddz;
	private DepartStatementHandler dsh;
	private JButton zmienDane;
	private Long idOddzialu;
	private JLabel errorLabel;
	private JLabel jazdL;
	private JLabel dostL;
	private JLabel pracL;
	private JLabel sprzedL;
	private DepartPanel panel;
	private boolean changeData = false;

	/**
	 * Create the frame.
	 */
	public DepartCard(Long id, DepartStatementHandler dth, DepartPanel p) {
		dsh = dth;
		panel = p;
		idOddzialu = id;
		setAlwaysOnTop(true);
		setTitle("Karta oddziału");
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		System.out.println("KARTA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 35, 53, 0, 33, 0, 0, 0,
				0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 18, 0, 0, 0, 13,
				0, 14, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 5;
		gbc_verticalStrut_3.gridy = 0;
		contentPane.add(verticalStrut_3, gbc_verticalStrut_3);

		JLabel lblKartaOddziau = new JLabel("KARTA ODDZIAŁU");
		lblKartaOddziau.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblKartaOddziau = new GridBagConstraints();
		gbc_lblKartaOddziau.gridwidth = 6;
		gbc_lblKartaOddziau.insets = new Insets(0, 0, 5, 5);
		gbc_lblKartaOddziau.gridx = 2;
		gbc_lblKartaOddziau.gridy = 1;
		contentPane.add(lblKartaOddziau, gbc_lblKartaOddziau);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 4;
		gbc_verticalStrut.gridy = 2;
		contentPane.add(verticalStrut, gbc_verticalStrut);

		JLabel lblNewLabel = new JLabel("ID Oddziału:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		idOddz = new JTextField();
		idOddz.setFont(new Font("Tahoma", Font.BOLD, 11));
		idOddz.setEditable(false);
		idOddz.setEnabled(false);
		GridBagConstraints gbc_idOddz = new GridBagConstraints();
		gbc_idOddz.gridwidth = 5;
		gbc_idOddz.insets = new Insets(0, 0, 5, 5);
		gbc_idOddz.fill = GridBagConstraints.HORIZONTAL;
		gbc_idOddz.gridx = 3;
		gbc_idOddz.gridy = 3;
		contentPane.add(idOddz, gbc_idOddz);
		idOddz.setColumns(10);
		idOddz.setText(id.toString());

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 8;
		gbc_horizontalStrut.gridy = 3;
		contentPane.add(horizontalStrut, gbc_horizontalStrut);

		Component verticalGlue_4 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_4 = new GridBagConstraints();
		gbc_verticalGlue_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_4.gridx = 5;
		gbc_verticalGlue_4.gridy = 4;
		contentPane.add(verticalGlue_4, gbc_verticalGlue_4);

		JLabel lblKodPocztowy = new JLabel("Kod pocztowy:");
		GridBagConstraints gbc_lblKodPocztowy = new GridBagConstraints();
		gbc_lblKodPocztowy.gridwidth = 2;
		gbc_lblKodPocztowy.anchor = GridBagConstraints.EAST;
		gbc_lblKodPocztowy.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodPocztowy.gridx = 1;
		gbc_lblKodPocztowy.gridy = 5;
		contentPane.add(lblKodPocztowy, gbc_lblKodPocztowy);

		kod = new JTextField();
		kod.setFont(new Font("Tahoma", Font.BOLD, 11));
		kod.setEnabled(false);
		GridBagConstraints gbc_kod = new GridBagConstraints();
		gbc_kod.gridwidth = 5;
		gbc_kod.insets = new Insets(0, 0, 5, 5);
		gbc_kod.fill = GridBagConstraints.HORIZONTAL;
		gbc_kod.gridx = 3;
		gbc_kod.gridy = 5;
		contentPane.add(kod, gbc_kod);
		kod.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 8;
		gbc_horizontalStrut_1.gridy = 5;
		contentPane.add(horizontalStrut_1, gbc_horizontalStrut_1);

		Component verticalGlue_3 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_3 = new GridBagConstraints();
		gbc_verticalGlue_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_3.gridx = 5;
		gbc_verticalGlue_3.gridy = 6;
		contentPane.add(verticalGlue_3, gbc_verticalGlue_3);

		JLabel lblMiejscowo = new JLabel("Miejscowość:");
		GridBagConstraints gbc_lblMiejscowo = new GridBagConstraints();
		gbc_lblMiejscowo.gridwidth = 2;
		gbc_lblMiejscowo.anchor = GridBagConstraints.EAST;
		gbc_lblMiejscowo.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiejscowo.gridx = 1;
		gbc_lblMiejscowo.gridy = 7;
		contentPane.add(lblMiejscowo, gbc_lblMiejscowo);

		miasto = new JTextField();
		miasto.setFont(new Font("Tahoma", Font.BOLD, 11));
		miasto.setEnabled(false);
		GridBagConstraints gbc_miasto = new GridBagConstraints();
		gbc_miasto.gridwidth = 5;
		gbc_miasto.insets = new Insets(0, 0, 5, 5);
		gbc_miasto.fill = GridBagConstraints.HORIZONTAL;
		gbc_miasto.gridx = 3;
		gbc_miasto.gridy = 7;
		contentPane.add(miasto, gbc_miasto);
		miasto.setColumns(10);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 8;
		gbc_horizontalStrut_2.gridy = 7;
		contentPane.add(horizontalStrut_2, gbc_horizontalStrut_2);

		Component verticalGlue_2 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_2 = new GridBagConstraints();
		gbc_verticalGlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_2.gridx = 5;
		gbc_verticalGlue_2.gridy = 8;
		contentPane.add(verticalGlue_2, gbc_verticalGlue_2);

		JLabel lblUlica = new JLabel("Ulica:");
		GridBagConstraints gbc_lblUlica = new GridBagConstraints();
		gbc_lblUlica.gridwidth = 2;
		gbc_lblUlica.anchor = GridBagConstraints.EAST;
		gbc_lblUlica.insets = new Insets(0, 0, 5, 5);
		gbc_lblUlica.gridx = 1;
		gbc_lblUlica.gridy = 9;
		contentPane.add(lblUlica, gbc_lblUlica);

		ulica = new JTextField();
		ulica.setFont(new Font("Tahoma", Font.BOLD, 11));
		ulica.setEnabled(false);
		GridBagConstraints gbc_ulica = new GridBagConstraints();
		gbc_ulica.gridwidth = 5;
		gbc_ulica.insets = new Insets(0, 0, 5, 5);
		gbc_ulica.fill = GridBagConstraints.HORIZONTAL;
		gbc_ulica.gridx = 3;
		gbc_ulica.gridy = 9;
		contentPane.add(ulica, gbc_ulica);
		ulica.setColumns(10);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_3.gridx = 8;
		gbc_horizontalStrut_3.gridy = 9;
		contentPane.add(horizontalStrut_3, gbc_horizontalStrut_3);

		Component verticalGlue_1 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_1 = new GridBagConstraints();
		gbc_verticalGlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_1.gridx = 5;
		gbc_verticalGlue_1.gridy = 10;
		contentPane.add(verticalGlue_1, gbc_verticalGlue_1);

		JLabel lblNrBudynku = new JLabel("Nr budynku:");
		GridBagConstraints gbc_lblNrBudynku = new GridBagConstraints();
		gbc_lblNrBudynku.gridwidth = 2;
		gbc_lblNrBudynku.anchor = GridBagConstraints.EAST;
		gbc_lblNrBudynku.insets = new Insets(0, 0, 5, 5);
		gbc_lblNrBudynku.gridx = 1;
		gbc_lblNrBudynku.gridy = 11;
		contentPane.add(lblNrBudynku, gbc_lblNrBudynku);

		budynki = new JTextField();
		budynki.setFont(new Font("Tahoma", Font.BOLD, 11));
		budynki.setEnabled(false);
		GridBagConstraints gbc_budynki = new GridBagConstraints();
		gbc_budynki.gridwidth = 5;
		gbc_budynki.insets = new Insets(0, 0, 5, 5);
		gbc_budynki.fill = GridBagConstraints.HORIZONTAL;
		gbc_budynki.gridx = 3;
		gbc_budynki.gridy = 11;
		contentPane.add(budynki, gbc_budynki);
		budynki.setColumns(10);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_4.gridx = 8;
		gbc_horizontalStrut_4.gridy = 11;
		contentPane.add(horizontalStrut_4, gbc_horizontalStrut_4);

		Component verticalGlue = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
		gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue.gridx = 5;
		gbc_verticalGlue.gridy = 12;
		contentPane.add(verticalGlue, gbc_verticalGlue);

		JLabel lblNrLokalu = new JLabel("Nr lokalu:");
		GridBagConstraints gbc_lblNrLokalu = new GridBagConstraints();
		gbc_lblNrLokalu.gridwidth = 2;
		gbc_lblNrLokalu.anchor = GridBagConstraints.EAST;
		gbc_lblNrLokalu.insets = new Insets(0, 0, 5, 5);
		gbc_lblNrLokalu.gridx = 1;
		gbc_lblNrLokalu.gridy = 13;
		contentPane.add(lblNrLokalu, gbc_lblNrLokalu);

		lokale = new JTextField();
		lokale.setFont(new Font("Tahoma", Font.BOLD, 11));
		lokale.setEnabled(false);
		GridBagConstraints gbc_lokale = new GridBagConstraints();
		gbc_lokale.gridwidth = 5;
		gbc_lokale.insets = new Insets(0, 0, 5, 5);
		gbc_lokale.fill = GridBagConstraints.HORIZONTAL;
		gbc_lokale.gridx = 3;
		gbc_lokale.gridy = 13;
		contentPane.add(lokale, gbc_lokale);
		lokale.setColumns(10);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_5.gridx = 8;
		gbc_horizontalStrut_5.gridy = 13;
		contentPane.add(horizontalStrut_5, gbc_horizontalStrut_5);

		zmienDane = new JButton("Zmień dane");
		zmienDane.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_zmienDane = new GridBagConstraints();
		gbc_zmienDane.gridwidth = 2;
		gbc_zmienDane.insets = new Insets(0, 0, 5, 5);
		gbc_zmienDane.gridx = 5;
		gbc_zmienDane.gridy = 14;
		contentPane.add(zmienDane, gbc_zmienDane);
		zmienDane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (changeData) {
					changeData = false;
					zmienDane.setText("Zmień dane");
					kod.setEnabled(false);
					miasto.setEnabled(false);
					budynki.setEnabled(false);
					lokale.setEnabled(false);
					ulica.setEnabled(false);
					try {
						int res = dsh.updateDepart(idOddzialu.toString(), kod
								.getText().trim(), miasto.getText().trim(),
								ulica.getText().trim(), budynki.getText()
										.trim(), lokale.getText().trim());
						if (res != 0) {
							loadData();
							panel.removeAllRows();
							panel.loadAllData();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					changeData = true;
					zmienDane.setText("  Zatwierdź  ");
					kod.setEnabled(true);
					miasto.setEnabled(true);
					budynki.setEnabled(true);
					lokale.setEnabled(true);
					ulica.setEnabled(true);
				}
			}
		});

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GREEN);
		separator.setForeground(Color.RED);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 7;
		gbc_separator.anchor = GridBagConstraints.EAST;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 15;
		contentPane.add(separator, gbc_separator);

		JLabel lblNewLabel_1 = new JLabel("Ilość pracowników:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 16;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		pracL = new JLabel("New label");
		GridBagConstraints gbc_pracL = new GridBagConstraints();
		gbc_pracL.insets = new Insets(0, 0, 5, 5);
		gbc_pracL.gridx = 5;
		gbc_pracL.gridy = 16;
		contentPane.add(pracL, gbc_pracL);

		JLabel lblIloDostpnychMotocykli = new JLabel(
				"Ilość dostępnych motocykli:");
		lblIloDostpnychMotocykli.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblIloDostpnychMotocykli = new GridBagConstraints();
		gbc_lblIloDostpnychMotocykli.anchor = GridBagConstraints.EAST;
		gbc_lblIloDostpnychMotocykli.insets = new Insets(0, 0, 5, 5);
		gbc_lblIloDostpnychMotocykli.gridwidth = 5;
		gbc_lblIloDostpnychMotocykli.gridx = 0;
		gbc_lblIloDostpnychMotocykli.gridy = 17;
		contentPane.add(lblIloDostpnychMotocykli, gbc_lblIloDostpnychMotocykli);

		dostL = new JLabel("New label");
		GridBagConstraints gbc_dostL = new GridBagConstraints();
		gbc_dostL.insets = new Insets(0, 0, 5, 5);
		gbc_dostL.gridx = 5;
		gbc_dostL.gridy = 17;
		contentPane.add(dostL, gbc_dostL);

		JLabel lblNewLabel_4 = new JLabel("Ilość sprzedanych motocykli:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridwidth = 5;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 18;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		sprzedL = new JLabel("New label");
		GridBagConstraints gbc_sprzedL = new GridBagConstraints();
		gbc_sprzedL.insets = new Insets(0, 0, 5, 5);
		gbc_sprzedL.gridx = 5;
		gbc_sprzedL.gridy = 18;
		contentPane.add(sprzedL, gbc_sprzedL);

		JLabel lblIloJazdPrbnych = new JLabel("Ilość jazd próbnych:");
		lblIloJazdPrbnych.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblIloJazdPrbnych = new GridBagConstraints();
		gbc_lblIloJazdPrbnych.anchor = GridBagConstraints.EAST;
		gbc_lblIloJazdPrbnych.gridwidth = 5;
		gbc_lblIloJazdPrbnych.insets = new Insets(0, 0, 5, 5);
		gbc_lblIloJazdPrbnych.gridx = 0;
		gbc_lblIloJazdPrbnych.gridy = 19;
		contentPane.add(lblIloJazdPrbnych, gbc_lblIloJazdPrbnych);

		jazdL = new JLabel("New label");
		GridBagConstraints gbc_jazdL = new GridBagConstraints();
		gbc_jazdL.insets = new Insets(0, 0, 5, 5);
		gbc_jazdL.gridx = 5;
		gbc_jazdL.gridy = 19;
		contentPane.add(jazdL, gbc_jazdL);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 4;
		gbc_verticalStrut_1.gridy = 20;
		contentPane.add(verticalStrut_1, gbc_verticalStrut_1);

		JButton btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridwidth = 3;
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 3;
		gbc_btnOk.gridy = 21;
		contentPane.add(btnOk, gbc_btnOk);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		Component verticalGlue_5 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_5 = new GridBagConstraints();
		gbc_verticalGlue_5.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_5.gridx = 4;
		gbc_verticalGlue_5.gridy = 22;
		contentPane.add(verticalGlue_5, gbc_verticalGlue_5);

		errorLabel = new JLabel("BTW");
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 7;
		gbc_errorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_errorLabel.gridx = 1;
		gbc_errorLabel.gridy = 24;
		contentPane.add(errorLabel, gbc_errorLabel);
		errorLabel.setVisible(false);

		JButton btnUsuOddzia = new JButton("Usuń oddział");
		GridBagConstraints gbc_btnUsuOddzia = new GridBagConstraints();
		gbc_btnUsuOddzia.gridwidth = 3;
		gbc_btnUsuOddzia.insets = new Insets(0, 0, 5, 5);
		gbc_btnUsuOddzia.gridx = 3;
		gbc_btnUsuOddzia.gridy = 23;
		contentPane.add(btnUsuOddzia, gbc_btnUsuOddzia);
		btnUsuOddzia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				errorLabel.setVisible(false);
				try {
					System.out.println("DELETE");
					dsh.deleteDepart(idOddzialu.toString());
					panel.deleteRow();
					dispose();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					if (e1.getErrorCode() == 2292) {
						errorLabel.setVisible(true);
						errorLabel
								.setText("Nie można usunąć niepustego oddziału!");
						System.err.println(e1.getMessage());
					}
				}
			}
		});

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_2.gridx = 4;
		gbc_verticalStrut_2.gridy = 25;
		contentPane.add(verticalStrut_2, gbc_verticalStrut_2);

		loadData();
	}

	private void loadData() {
		String kodS = null;
		String miastoS = null;
		String ulicaS = null;
		String budynekS = null;
		String lokalS = null;
		ResultSet rs = null;
		try {
			rs = dsh.getSingleDepart(idOddzialu.toString());
			while (rs.next()) {
				kodS = rs.getString(2);
				miastoS = rs.getString(3);
				ulicaS = rs.getString(4);
				budynekS = rs.getString(5);
				lokalS = rs.getString(6);
			}

			kod.setText(kodS.substring(0, 2) + "-" + kodS.substring(2, 5));
			miasto.setText(miastoS);
			ulica.setText(ulicaS);
			budynki.setText(budynekS);
			lokale.setText(lokalS);

			if (rs != null)
				rs.close();
			
			Integer workers = dsh.getDepartWorkers(idOddzialu.toString());
			Integer motors = dsh.getDepartMotors(idOddzialu.toString());
			Integer sold = dsh.getDepartSold(idOddzialu.toString());
			Integer tryouts = dsh.getDepartTryouts(idOddzialu.toString());
			
			pracL.setText(workers.toString());
			dostL.setText(motors.toString());
			sprzedL.setText(sold.toString());
			jazdL.setText(tryouts.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
