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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pl.wbd.managers.ClientsStatementHandler;
import pl.wbd.panels.ClientsPanel;

@SuppressWarnings("serial")
public class ClientCardd extends JFrame {

	private JPanel contentPane;
	private JTextField kod;
	private JTextField miasto;
	private JTextField ulica;
	private JTextField budynek;
	private JTextField mieszkanie;
	private JTextField idKl;
	private ClientsStatementHandler csh;
	private JButton zmienDane;
	private String idKlienta;
	private JLabel errorLabel;
	private JLabel rezL;
	private JLabel kupL;
	private JLabel jazdL;
	private ClientsPanel panel;
	private boolean changeData = false;
	private JTextField imie;
	private JTextField dimie;
	private JTextField nazwisko;
	private JTextField dataur;
	private JTextField pesel;
	private JTextField nrTel;
	private JTextField a1;
	private JTextField a2;
	private JTextField a;

	/**
	 * Create the frame.
	 */
	public ClientCardd(String idk, ClientsStatementHandler cth,
			ClientsPanel panel2) {
		csh = cth;
		panel = panel2;
		idKlienta = idk;
		setAlwaysOnTop(true);
		setTitle("Karta klienta");
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 789);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 35, 53, 0, 33, 0, 0, 0,
				0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 18, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 5;
		gbc_verticalStrut_3.gridy = 0;
		contentPane.add(verticalStrut_3, gbc_verticalStrut_3);

		JLabel lblKartaOddziau = new JLabel("KARTA KLIENTA");
		lblKartaOddziau.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblKartaOddziau = new GridBagConstraints();
		gbc_lblKartaOddziau.gridwidth = 6;
		gbc_lblKartaOddziau.insets = new Insets(0, 0, 5, 5);
		gbc_lblKartaOddziau.gridx = 2;
		gbc_lblKartaOddziau.gridy = 2;
		contentPane.add(lblKartaOddziau, gbc_lblKartaOddziau);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 4;
		gbc_verticalStrut.gridy = 4;
		contentPane.add(verticalStrut, gbc_verticalStrut);

		JLabel lblNewLabel = new JLabel("ID Klienta:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		idKl = new JTextField();
		idKl.setFont(new Font("Tahoma", Font.BOLD, 11));
		idKl.setEditable(false);
		idKl.setEnabled(false);
		GridBagConstraints gbc_idKl = new GridBagConstraints();
		gbc_idKl.gridwidth = 5;
		gbc_idKl.insets = new Insets(0, 0, 5, 5);
		gbc_idKl.fill = GridBagConstraints.HORIZONTAL;
		gbc_idKl.gridx = 3;
		gbc_idKl.gridy = 5;
		contentPane.add(idKl, gbc_idKl);
		idKl.setColumns(10);
		idKl.setText(idKlienta.toString());

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 8;
		gbc_horizontalStrut.gridy = 5;
		contentPane.add(horizontalStrut, gbc_horizontalStrut);

		Component verticalGlue_4 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_4 = new GridBagConstraints();
		gbc_verticalGlue_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_4.gridx = 5;
		gbc_verticalGlue_4.gridy = 6;
		contentPane.add(verticalGlue_4, gbc_verticalGlue_4);

		JLabel lblNewLabel_2 = new JLabel("Imię:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 7;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		imie = new JTextField();
		imie.setFont(new Font("Tahoma", Font.BOLD, 11));
		imie.setEnabled(false);
		GridBagConstraints gbc_imie = new GridBagConstraints();
		gbc_imie.gridwidth = 5;
		gbc_imie.insets = new Insets(0, 0, 5, 5);
		gbc_imie.fill = GridBagConstraints.HORIZONTAL;
		gbc_imie.gridx = 3;
		gbc_imie.gridy = 7;
		contentPane.add(imie, gbc_imie);
		imie.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Drugie imię:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 8;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		dimie = new JTextField();
		dimie.setFont(new Font("Tahoma", Font.BOLD, 11));
		dimie.setEnabled(false);
		GridBagConstraints gbc_dimie = new GridBagConstraints();
		gbc_dimie.gridwidth = 5;
		gbc_dimie.insets = new Insets(0, 0, 5, 5);
		gbc_dimie.fill = GridBagConstraints.HORIZONTAL;
		gbc_dimie.gridx = 3;
		gbc_dimie.gridy = 8;
		contentPane.add(dimie, gbc_dimie);
		dimie.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Nazwisko:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 9;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		nazwisko = new JTextField();
		nazwisko.setFont(new Font("Tahoma", Font.BOLD, 11));
		nazwisko.setEnabled(false);
		GridBagConstraints gbc_nazwisko = new GridBagConstraints();
		gbc_nazwisko.gridwidth = 5;
		gbc_nazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_nazwisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_nazwisko.gridx = 3;
		gbc_nazwisko.gridy = 9;
		contentPane.add(nazwisko, gbc_nazwisko);
		nazwisko.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Data urodzenia:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 10;
		contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);

		dataur = new JTextField();
		dataur.setFont(new Font("Tahoma", Font.BOLD, 11));
		dataur.setEnabled(false);
		GridBagConstraints gbc_dataur = new GridBagConstraints();
		gbc_dataur.gridwidth = 5;
		gbc_dataur.insets = new Insets(0, 0, 5, 5);
		gbc_dataur.fill = GridBagConstraints.HORIZONTAL;
		gbc_dataur.gridx = 3;
		gbc_dataur.gridy = 10;
		contentPane.add(dataur, gbc_dataur);
		dataur.setColumns(10);

		JLabel lblKodPocztowy = new JLabel("Kod pocztowy:");
		GridBagConstraints gbc_lblKodPocztowy = new GridBagConstraints();
		gbc_lblKodPocztowy.gridwidth = 2;
		gbc_lblKodPocztowy.anchor = GridBagConstraints.EAST;
		gbc_lblKodPocztowy.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodPocztowy.gridx = 1;
		gbc_lblKodPocztowy.gridy = 11;
		contentPane.add(lblKodPocztowy, gbc_lblKodPocztowy);

		kod = new JTextField();
		kod.setFont(new Font("Tahoma", Font.BOLD, 11));
		kod.setEnabled(false);
		GridBagConstraints gbc_kod = new GridBagConstraints();
		gbc_kod.gridwidth = 5;
		gbc_kod.insets = new Insets(0, 0, 5, 5);
		gbc_kod.fill = GridBagConstraints.HORIZONTAL;
		gbc_kod.gridx = 3;
		gbc_kod.gridy = 11;
		contentPane.add(kod, gbc_kod);
		kod.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 8;
		gbc_horizontalStrut_1.gridy = 11;
		contentPane.add(horizontalStrut_1, gbc_horizontalStrut_1);

		Component verticalGlue_3 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_3 = new GridBagConstraints();
		gbc_verticalGlue_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_3.gridx = 5;
		gbc_verticalGlue_3.gridy = 12;
		contentPane.add(verticalGlue_3, gbc_verticalGlue_3);

		JLabel lblMiejscowo = new JLabel("Miejscowość:");
		GridBagConstraints gbc_lblMiejscowo = new GridBagConstraints();
		gbc_lblMiejscowo.gridwidth = 2;
		gbc_lblMiejscowo.anchor = GridBagConstraints.EAST;
		gbc_lblMiejscowo.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiejscowo.gridx = 1;
		gbc_lblMiejscowo.gridy = 13;
		contentPane.add(lblMiejscowo, gbc_lblMiejscowo);

		miasto = new JTextField();
		miasto.setFont(new Font("Tahoma", Font.BOLD, 11));
		miasto.setEnabled(false);
		GridBagConstraints gbc_miasto = new GridBagConstraints();
		gbc_miasto.gridwidth = 5;
		gbc_miasto.insets = new Insets(0, 0, 5, 5);
		gbc_miasto.fill = GridBagConstraints.HORIZONTAL;
		gbc_miasto.gridx = 3;
		gbc_miasto.gridy = 13;
		contentPane.add(miasto, gbc_miasto);
		miasto.setColumns(10);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 8;
		gbc_horizontalStrut_2.gridy = 13;
		contentPane.add(horizontalStrut_2, gbc_horizontalStrut_2);

		JLabel lblUlica = new JLabel("Ulica:");
		GridBagConstraints gbc_lblUlica = new GridBagConstraints();
		gbc_lblUlica.gridwidth = 2;
		gbc_lblUlica.anchor = GridBagConstraints.EAST;
		gbc_lblUlica.insets = new Insets(0, 0, 5, 5);
		gbc_lblUlica.gridx = 1;
		gbc_lblUlica.gridy = 14;
		contentPane.add(lblUlica, gbc_lblUlica);

		ulica = new JTextField();
		ulica.setFont(new Font("Tahoma", Font.BOLD, 11));
		ulica.setEnabled(false);
		GridBagConstraints gbc_ulica = new GridBagConstraints();
		gbc_ulica.gridwidth = 5;
		gbc_ulica.insets = new Insets(0, 0, 5, 5);
		gbc_ulica.fill = GridBagConstraints.HORIZONTAL;
		gbc_ulica.gridx = 3;
		gbc_ulica.gridy = 14;
		contentPane.add(ulica, gbc_ulica);
		ulica.setColumns(10);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_3.gridx = 8;
		gbc_horizontalStrut_3.gridy = 14;
		contentPane.add(horizontalStrut_3, gbc_horizontalStrut_3);

		JLabel lblNrBudynku = new JLabel("Nr budynku:");
		GridBagConstraints gbc_lblNrBudynku = new GridBagConstraints();
		gbc_lblNrBudynku.gridwidth = 2;
		gbc_lblNrBudynku.anchor = GridBagConstraints.EAST;
		gbc_lblNrBudynku.insets = new Insets(0, 0, 5, 5);
		gbc_lblNrBudynku.gridx = 1;
		gbc_lblNrBudynku.gridy = 15;
		contentPane.add(lblNrBudynku, gbc_lblNrBudynku);

		budynek = new JTextField();
		budynek.setFont(new Font("Tahoma", Font.BOLD, 11));
		budynek.setEnabled(false);
		GridBagConstraints gbc_budynek = new GridBagConstraints();
		gbc_budynek.gridwidth = 5;
		gbc_budynek.insets = new Insets(0, 0, 5, 5);
		gbc_budynek.fill = GridBagConstraints.HORIZONTAL;
		gbc_budynek.gridx = 3;
		gbc_budynek.gridy = 15;
		contentPane.add(budynek, gbc_budynek);
		budynek.setColumns(10);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_4.gridx = 8;
		gbc_horizontalStrut_4.gridy = 15;
		contentPane.add(horizontalStrut_4, gbc_horizontalStrut_4);

		Component verticalGlue = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
		gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue.gridx = 5;
		gbc_verticalGlue.gridy = 16;
		contentPane.add(verticalGlue, gbc_verticalGlue);

		JLabel lblNrLokalu = new JLabel("Nr mieszkania:");
		GridBagConstraints gbc_lblNrLokalu = new GridBagConstraints();
		gbc_lblNrLokalu.gridwidth = 2;
		gbc_lblNrLokalu.anchor = GridBagConstraints.EAST;
		gbc_lblNrLokalu.insets = new Insets(0, 0, 5, 5);
		gbc_lblNrLokalu.gridx = 1;
		gbc_lblNrLokalu.gridy = 17;
		contentPane.add(lblNrLokalu, gbc_lblNrLokalu);

		mieszkanie = new JTextField();
		mieszkanie.setFont(new Font("Tahoma", Font.BOLD, 11));
		mieszkanie.setEnabled(false);
		GridBagConstraints gbc_mieszkanie = new GridBagConstraints();
		gbc_mieszkanie.gridwidth = 5;
		gbc_mieszkanie.insets = new Insets(0, 0, 5, 5);
		gbc_mieszkanie.fill = GridBagConstraints.HORIZONTAL;
		gbc_mieszkanie.gridx = 3;
		gbc_mieszkanie.gridy = 17;
		contentPane.add(mieszkanie, gbc_mieszkanie);
		mieszkanie.setColumns(10);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_5.gridx = 8;
		gbc_horizontalStrut_5.gridy = 17;
		contentPane.add(horizontalStrut_5, gbc_horizontalStrut_5);

		JLabel lblNewLabel_7 = new JLabel("PESEL:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.gridwidth = 2;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 18;
		contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);

		pesel = new JTextField();
		pesel.setFont(new Font("Tahoma", Font.BOLD, 11));
		pesel.setEnabled(false);
		GridBagConstraints gbc_pesel = new GridBagConstraints();
		gbc_pesel.gridwidth = 5;
		gbc_pesel.insets = new Insets(0, 0, 5, 5);
		gbc_pesel.fill = GridBagConstraints.HORIZONTAL;
		gbc_pesel.gridx = 3;
		gbc_pesel.gridy = 18;
		contentPane.add(pesel, gbc_pesel);
		pesel.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Nr telefonu:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.gridwidth = 2;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 19;
		contentPane.add(lblNewLabel_8, gbc_lblNewLabel_8);

		nrTel = new JTextField();
		nrTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nrTel.setEnabled(false);
		GridBagConstraints gbc_nrTel = new GridBagConstraints();
		gbc_nrTel.gridwidth = 5;
		gbc_nrTel.insets = new Insets(0, 0, 5, 5);
		gbc_nrTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_nrTel.gridx = 3;
		gbc_nrTel.gridy = 19;
		contentPane.add(nrTel, gbc_nrTel);
		nrTel.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Data wydania A1:");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.gridwidth = 2;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 20;
		contentPane.add(lblNewLabel_9, gbc_lblNewLabel_9);

		a1 = new JTextField();
		a1.setFont(new Font("Tahoma", Font.BOLD, 11));
		a1.setEnabled(false);
		GridBagConstraints gbc_a1 = new GridBagConstraints();
		gbc_a1.gridwidth = 5;
		gbc_a1.insets = new Insets(0, 0, 5, 5);
		gbc_a1.fill = GridBagConstraints.HORIZONTAL;
		gbc_a1.gridx = 3;
		gbc_a1.gridy = 20;
		contentPane.add(a1, gbc_a1);
		a1.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Data wydania A2:");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.gridwidth = 2;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 1;
		gbc_lblNewLabel_10.gridy = 21;
		contentPane.add(lblNewLabel_10, gbc_lblNewLabel_10);

		a2 = new JTextField();
		a2.setFont(new Font("Tahoma", Font.BOLD, 11));
		a2.setEnabled(false);
		GridBagConstraints gbc_a2 = new GridBagConstraints();
		gbc_a2.gridwidth = 5;
		gbc_a2.insets = new Insets(0, 0, 5, 5);
		gbc_a2.fill = GridBagConstraints.HORIZONTAL;
		gbc_a2.gridx = 3;
		gbc_a2.gridy = 21;
		contentPane.add(a2, gbc_a2);
		a2.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Data wydania A:");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_11.gridwidth = 2;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 1;
		gbc_lblNewLabel_11.gridy = 22;
		contentPane.add(lblNewLabel_11, gbc_lblNewLabel_11);

		a = new JTextField();
		a.setFont(new Font("Tahoma", Font.BOLD, 11));
		a.setEnabled(false);
		GridBagConstraints gbc_a = new GridBagConstraints();
		gbc_a.gridwidth = 5;
		gbc_a.insets = new Insets(0, 0, 5, 5);
		gbc_a.fill = GridBagConstraints.HORIZONTAL;
		gbc_a.gridx = 3;
		gbc_a.gridy = 22;
		contentPane.add(a, gbc_a);
		a.setColumns(10);

		zmienDane = new JButton("Zmień dane");
		zmienDane.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_zmienDane = new GridBagConstraints();
		gbc_zmienDane.gridwidth = 2;
		gbc_zmienDane.insets = new Insets(0, 0, 5, 5);
		gbc_zmienDane.gridx = 5;
		gbc_zmienDane.gridy = 23;
		contentPane.add(zmienDane, gbc_zmienDane);
		zmienDane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (changeData) {
					changeData = false;
					zmienDane.setText("Zmień dane");
					kod.setEnabled(false);
					miasto.setEnabled(false);
					budynek.setEnabled(false);
					mieszkanie.setEnabled(false);
					ulica.setEnabled(false);
					imie.setEnabled(false);
					dimie.setEnabled(false);
					nazwisko.setEnabled(false);
					dataur.setEnabled(false);
					pesel.setEnabled(false);
					nrTel.setEnabled(false);
					a1.setEnabled(false);
					a2.setEnabled(false);
					a.setEnabled(false);
					try {
						int res = csh.updateClient(idKlienta, kod.getText()
								.trim(), miasto.getText().trim(), ulica
								.getText().trim(), budynek.getText().trim(),
								mieszkanie.getText().trim(), imie.getText()
										.trim(), dimie.getText().trim(),
								nazwisko.getText().trim(), dataur.getText()
										.trim(), nrTel.getText().trim(), pesel
										.getText().trim(), a1.getText().trim(),
								a2.getText().trim(), a.getText().trim());
						if (res != 0) {
							panel.removeAllRows();
							panel.loadAllData(panel.getOrderType(),
									panel.getShowByMiejscowosc());
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

					loadData();
				} else {
					changeData = true;
					zmienDane.setText("  Zatwierdź  ");
					imie.setEnabled(true);
					dimie.setEnabled(true);
					nazwisko.setEnabled(true);
					dataur.setEnabled(true);
					pesel.setEnabled(true);
					nrTel.setEnabled(true);
					kod.setEnabled(true);
					miasto.setEnabled(true);
					budynek.setEnabled(true);
					mieszkanie.setEnabled(true);
					ulica.setEnabled(true);
					a1.setEnabled(true);
					a2.setEnabled(true);
					a.setEnabled(true);
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Ilość kupionych:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 24;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		kupL = new JLabel("New label");
		GridBagConstraints gbc_kupL = new GridBagConstraints();
		gbc_kupL.insets = new Insets(0, 0, 5, 5);
		gbc_kupL.gridx = 5;
		gbc_kupL.gridy = 24;
		contentPane.add(kupL, gbc_kupL);

		JLabel lblIlorezygnacji = new JLabel("Ilość rezygnacji:");
		lblIlorezygnacji.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblIlorezygnacji = new GridBagConstraints();
		gbc_lblIlorezygnacji.anchor = GridBagConstraints.EAST;
		gbc_lblIlorezygnacji.insets = new Insets(0, 0, 5, 5);
		gbc_lblIlorezygnacji.gridwidth = 5;
		gbc_lblIlorezygnacji.gridx = 0;
		gbc_lblIlorezygnacji.gridy = 25;
		contentPane.add(lblIlorezygnacji, gbc_lblIlorezygnacji);

		rezL = new JLabel("New label");
		GridBagConstraints gbc_rezL = new GridBagConstraints();
		gbc_rezL.insets = new Insets(0, 0, 5, 5);
		gbc_rezL.gridx = 5;
		gbc_rezL.gridy = 25;
		contentPane.add(rezL, gbc_rezL);

		JLabel lblNewLabel_4 = new JLabel("Ilość jazd próbnych:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridwidth = 5;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 26;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		jazdL = new JLabel("New label");
		GridBagConstraints gbc_jazdL = new GridBagConstraints();
		gbc_jazdL.insets = new Insets(0, 0, 5, 5);
		gbc_jazdL.gridx = 5;
		gbc_jazdL.gridy = 26;
		contentPane.add(jazdL, gbc_jazdL);

		JButton btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridwidth = 5;
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 27;
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
		gbc_verticalGlue_5.gridy = 28;
		contentPane.add(verticalGlue_5, gbc_verticalGlue_5);

		JButton btnUsuKlienta = new JButton("Usuń klienta");
		GridBagConstraints gbc_btnUsuKlienta = new GridBagConstraints();
		gbc_btnUsuKlienta.gridwidth = 5;
		gbc_btnUsuKlienta.insets = new Insets(0, 0, 5, 5);
		gbc_btnUsuKlienta.gridx = 2;
		gbc_btnUsuKlienta.gridy = 29;
		contentPane.add(btnUsuKlienta, gbc_btnUsuKlienta);
		btnUsuKlienta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					csh.deleteClient(idKlienta);
					panel.deleteRow();
					dispose();
				} catch (SQLException e1) {
					if (e1.getErrorCode() == 2292)
						errorLabel
								.setText("Nie można usunąć klienta posiadającego podrzędne zależności");
					e1.printStackTrace();
				}
			}
		});

		errorLabel = new JLabel("BTW");
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 7;
		gbc_errorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_errorLabel.gridx = 1;
		gbc_errorLabel.gridy = 30;
		contentPane.add(errorLabel, gbc_errorLabel);
		errorLabel.setVisible(false);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_2.gridx = 4;
		gbc_verticalStrut_2.gridy = 31;
		contentPane.add(verticalStrut_2, gbc_verticalStrut_2);

		loadData();
	}

	private void loadData() {
		String kodS = null;
		String miastoS = null;
		String ulicaS = null;
		String budynekS = null;
		String lokalS = null;
		String imieS = null;
		String nazwiskoS = null;
		String dimieS = null;
		String dataUr = null;
		String peselS = null;
		String nrTelS = null;
		String a1S = null;
		String a2S = null;
		String aS = null;

		ResultSet rs = null;
		try {
			rs = csh.getSingleClient(idKlienta);
			while (rs.next()) {
				imieS = rs.getString(2);
				dimieS = rs.getString(3);
				nazwiskoS = rs.getString(4);
				dataUr = rs.getString(5);
				kodS = rs.getString(6);
				miastoS = rs.getString(7);
				ulicaS = rs.getString(8);
				budynekS = rs.getString(9);
				lokalS = rs.getString(10);
				peselS = rs.getString(11);
				nrTelS = rs.getString(12);
			}

			if (rs != null)
				rs.close();
			rs = null;
			rs = csh.getSingleClientLicense(idKlienta);
			while (rs.next()) {
				a1S = rs.getString(2);
				a2S = rs.getString(3);
				aS = rs.getString(4);
			}

			imie.setText(imieS);
			dimie.setText(dimieS);
			nazwisko.setText(nazwiskoS);
			dataur.setText(dataUr.substring(0, 10));
			kod.setText(kodS.substring(0, 2) + "-" + kodS.substring(2, 5));
			miasto.setText(miastoS);
			ulica.setText(ulicaS);
			budynek.setText(budynekS);
			mieszkanie.setText(lokalS);
			pesel.setText(peselS);
			nrTel.setText(nrTelS);
			if (a1S != null && a1S != "")
				a1.setText(a1S.substring(0, 10));
			if (a2S != null && a2S != "")
				a2.setText(a2S.substring(0, 10));
			if (aS != null && aS != "")
				a.setText(aS.substring(0, 10));

			Integer bought = csh.getClientBought(idKlienta);
			Integer resigned = csh.getClientResigned(idKlienta);
			Integer tryouts = csh.getClientTryouts(idKlienta);

			kupL.setText(bought.toString());
			rezL.setText(resigned.toString());
			jazdL.setText(tryouts.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
