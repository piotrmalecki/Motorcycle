package pl.wbd.ui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

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

import pl.wbd.managers.StaffStatementHandler;
import pl.wbd.panels.StaffPanel;
import pl.wbd.util.OnlyDigitsInput;

@SuppressWarnings("serial")
public class WorkerAdder extends JFrame {

	private final Dimension dim = new Dimension(280, 330);
	private JPanel contentPane;
	private JTextField textDImie;
	private JTextField textImie;
	private JTextField textNazwisko;
	private JTextField textDataU;
	private JTextField textTel;
	private JTextField pensja;
	private JComboBox<String> stanowisko;
	private JComboBox<String> oddzial;
	private StaffStatementHandler statementHandler;
	private StaffPanel panel;
	private WorkerAdder frame;
	private JLabel errorLabel;
	private JTextField textUser;
	private Vector<String> adres;
	private HashMap<String, Long> idmap;
	private ArrayList<Long> id;
	private String selectedDep;
	/**
	 * Create the frame.
	 */
	public WorkerAdder(StaffStatementHandler sth, StaffPanel st) {

		statementHandler = sth;
		panel = st;
		frame = this;
		adres = new Vector<String>();
		id = new ArrayList<Long>();
		idmap = new HashMap<String,Long>();
		setAlwaysOnTop(true);
		setTitle("Salon motocyklowy - Nowy pracownik");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 417);
		setLocationRelativeTo(null);
		setSize(new Dimension(289, 411));
		setPreferredSize(dim);
		setResizable(false);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 6;
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 12;
		contentPane.add(errorLabel, gbc_errorLabel);

		JLabel lblDodajNowegoPracownika = new JLabel("Dodaj nowego pracownika");
		lblDodajNowegoPracownika.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblDodajNowegoPracownika = new GridBagConstraints();
		gbc_lblDodajNowegoPracownika.gridheight = 2;
		gbc_lblDodajNowegoPracownika.gridwidth = 6;
		gbc_lblDodajNowegoPracownika.insets = new Insets(0, 0, 5, 0);
		gbc_lblDodajNowegoPracownika.gridx = 0;
		gbc_lblDodajNowegoPracownika.gridy = 0;
		contentPane.add(lblDodajNowegoPracownika, gbc_lblDodajNowegoPracownika);

		JLabel lblImi = new JLabel("Imię:");
		GridBagConstraints gbc_lblImi = new GridBagConstraints();
		gbc_lblImi.anchor = GridBagConstraints.EAST;
		gbc_lblImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblImi.gridx = 0;
		gbc_lblImi.gridy = 2;
		contentPane.add(lblImi, gbc_lblImi);

		textImie = new JTextField();
		GridBagConstraints gbc_textImie = new GridBagConstraints();
		gbc_textImie.gridwidth = 4;
		gbc_textImie.insets = new Insets(0, 0, 5, 5);
		gbc_textImie.fill = GridBagConstraints.HORIZONTAL;
		gbc_textImie.gridx = 1;
		gbc_textImie.gridy = 2;
		contentPane.add(textImie, gbc_textImie);
		textImie.setColumns(10);

		JLabel lblDrugieImi = new JLabel("Drugie imię:");
		GridBagConstraints gbc_lblDrugieImi = new GridBagConstraints();
		gbc_lblDrugieImi.anchor = GridBagConstraints.EAST;
		gbc_lblDrugieImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblDrugieImi.gridx = 0;
		gbc_lblDrugieImi.gridy = 3;
		contentPane.add(lblDrugieImi, gbc_lblDrugieImi);

		textDImie = new JTextField();
		GridBagConstraints gbc_textDImie = new GridBagConstraints();
		gbc_textDImie.gridwidth = 4;
		gbc_textDImie.insets = new Insets(0, 0, 5, 5);
		gbc_textDImie.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDImie.gridx = 1;
		gbc_textDImie.gridy = 3;
		contentPane.add(textDImie, gbc_textDImie);
		textDImie.setColumns(10);

		JLabel lblNazwisko = new JLabel("Nazwisko:");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.anchor = GridBagConstraints.EAST;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 0;
		gbc_lblNazwisko.gridy = 4;
		contentPane.add(lblNazwisko, gbc_lblNazwisko);

		textNazwisko = new JTextField();
		GridBagConstraints gbc_textNazwisko = new GridBagConstraints();
		gbc_textNazwisko.gridwidth = 4;
		gbc_textNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_textNazwisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNazwisko.gridx = 1;
		gbc_textNazwisko.gridy = 4;
		contentPane.add(textNazwisko, gbc_textNazwisko);
		textNazwisko.setColumns(10);

		JLabel lblDataUrodzenia = new JLabel("Data urodzenia:");
		GridBagConstraints gbc_lblDataUrodzenia = new GridBagConstraints();
		gbc_lblDataUrodzenia.anchor = GridBagConstraints.EAST;
		gbc_lblDataUrodzenia.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataUrodzenia.gridx = 0;
		gbc_lblDataUrodzenia.gridy = 5;
		contentPane.add(lblDataUrodzenia, gbc_lblDataUrodzenia);

		textDataU = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
		GridBagConstraints gbc_textDataU = new GridBagConstraints();
		gbc_textDataU.gridwidth = 4;
		gbc_textDataU.insets = new Insets(0, 0, 5, 5);
		gbc_textDataU.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDataU.gridx = 1;
		gbc_textDataU.gridy = 5;
		contentPane.add(textDataU, gbc_textDataU);
		textDataU.setColumns(10);

		JLabel lblNumerTelefonu = new JLabel("Numer Telefonu:");
		GridBagConstraints gbc_lblNumerTelefonu = new GridBagConstraints();
		gbc_lblNumerTelefonu.anchor = GridBagConstraints.EAST;
		gbc_lblNumerTelefonu.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumerTelefonu.gridx = 0;
		gbc_lblNumerTelefonu.gridy = 6;
		contentPane.add(lblNumerTelefonu, gbc_lblNumerTelefonu);

		textTel = new JTextField();
		GridBagConstraints gbc_textTel = new GridBagConstraints();
		gbc_textTel.gridwidth = 4;
		gbc_textTel.insets = new Insets(0, 0, 5, 5);
		gbc_textTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTel.gridx = 1;
		gbc_textTel.gridy = 6;
		contentPane.add(textTel, gbc_textTel);
		textTel.setColumns(10);
		textTel.addKeyListener(new OnlyDigitsInput(errorLabel, false));

		JLabel lblNazwaUytkownika = new JLabel("Nazwa użytkownika:");
		GridBagConstraints gbc_lblNazwaUytkownika = new GridBagConstraints();
		gbc_lblNazwaUytkownika.anchor = GridBagConstraints.EAST;
		gbc_lblNazwaUytkownika.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwaUytkownika.gridx = 0;
		gbc_lblNazwaUytkownika.gridy = 7;
		contentPane.add(lblNazwaUytkownika, gbc_lblNazwaUytkownika);

		textUser = new JTextField();
		textUser.setEnabled(false);
		GridBagConstraints gbc_textUser = new GridBagConstraints();
		gbc_textUser.gridwidth = 4;
		gbc_textUser.insets = new Insets(0, 0, 5, 5);
		gbc_textUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUser.gridx = 1;
		gbc_textUser.gridy = 7;
		contentPane.add(textUser, gbc_textUser);
		textUser.setColumns(10);

		JLabel lblNewLabel = new JLabel("Stanowisko");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 8;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		stanowisko = new JComboBox<String>();
		stanowisko.setModel(new DefaultComboBoxModel<String>(sth
				.getStanowiska()));
		GridBagConstraints gbc_stanowisko = new GridBagConstraints();
		gbc_stanowisko.gridwidth = 4;
		gbc_stanowisko.insets = new Insets(0, 0, 5, 5);
		gbc_stanowisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_stanowisko.gridx = 1;
		gbc_stanowisko.gridy = 8;
		contentPane.add(stanowisko, gbc_stanowisko);

		JLabel lblPensja = new JLabel("Pensja");
		GridBagConstraints gbc_lblPensja = new GridBagConstraints();
		gbc_lblPensja.anchor = GridBagConstraints.EAST;
		gbc_lblPensja.insets = new Insets(0, 0, 5, 5);
		gbc_lblPensja.gridx = 0;
		gbc_lblPensja.gridy = 9;
		contentPane.add(lblPensja, gbc_lblPensja);

		pensja = new JTextField();
		GridBagConstraints gbc_pensja = new GridBagConstraints();
		gbc_pensja.gridwidth = 4;
		gbc_pensja.insets = new Insets(0, 0, 5, 5);
		gbc_pensja.fill = GridBagConstraints.HORIZONTAL;
		gbc_pensja.gridx = 1;
		gbc_pensja.gridy = 9;
		contentPane.add(pensja, gbc_pensja);
		pensja.addKeyListener(new OnlyDigitsInput(errorLabel, true));
		pensja.setColumns(10);

		JLabel lblOddzia = new JLabel("Oddział");
		GridBagConstraints gbc_lblOddzia = new GridBagConstraints();
		gbc_lblOddzia.anchor = GridBagConstraints.EAST;
		gbc_lblOddzia.insets = new Insets(0, 0, 5, 5);
		gbc_lblOddzia.gridx = 0;
		gbc_lblOddzia.gridy = 10;
		contentPane.add(lblOddzia, gbc_lblOddzia);

		try {
			ResultSet odd = sth.getDepartments();
			while (odd.next()) {
				id.add(odd.getLong(1));
				idmap.put(odd.getString(2),odd.getLong(1));
				adres.add(odd.getString(2));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		selectedDep = adres.get(0);
		oddzial = new JComboBox<String>();
		oddzial.setModel(new DefaultComboBoxModel<String>(adres));
		GridBagConstraints gbc_oddzial = new GridBagConstraints();
		gbc_oddzial.gridwidth = 4;
		gbc_oddzial.insets = new Insets(0, 0, 5, 5);
		gbc_oddzial.fill = GridBagConstraints.HORIZONTAL;
		gbc_oddzial.gridx = 1;
		gbc_oddzial.gridy = 10;
		contentPane.add(oddzial, gbc_oddzial);
		oddzial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedDep = (String) oddzial.getSelectedItem();	
			}
		});

		JButton btnNewButton = new JButton("Dodaj");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 11;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Long adrnr = idmap.get(selectedDep);
					System.out.println(adrnr);
					statementHandler.addNewUser(textImie.getText().trim(),
							textDImie.getText().trim(), textNazwisko.getText()
									.trim(), textDataU.getText().trim(),
							textTel.getText().trim(), stanowisko
									.getSelectedItem().toString(), Float
									.parseFloat(pensja.getText()), adrnr,
							dateFormat.format(new Date()), textUser.getText()
									.trim());

					panel.removeAllRows();
					panel.loadAllData(panel.getOrderType(),
							panel.isShowHired(), panel.getShowBy());
					dispose();
				} catch (SQLException e) {
					System.err.println("Blad dodajac do bazy "
							+ e.getErrorCode() + " mes: " + e.getMessage());
					if (e.getErrorCode() == 1400) {
						errorLabel
								.setText("Nie wprowadzono wszystkich wartości bądź wartość nieprawidłową");

					}
					if (e.getErrorCode() == 1920) {
						errorLabel
								.setText("Domyslna nazwa uzytkownika jest zajeta. Podaj własną nazwę");
						textUser.setEnabled(true);
					} else
						JOptionPane.showMessageDialog(frame,
								"Spróbuj ponownie!", "Nieznany błąd",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnAnuluj = new JButton("Anuluj");
		GridBagConstraints gbc_btnAnuluj = new GridBagConstraints();
		gbc_btnAnuluj.gridwidth = 2;
		gbc_btnAnuluj.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnuluj.gridx = 2;
		gbc_btnAnuluj.gridy = 11;
		contentPane.add(btnAnuluj, gbc_btnAnuluj);

		btnAnuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
