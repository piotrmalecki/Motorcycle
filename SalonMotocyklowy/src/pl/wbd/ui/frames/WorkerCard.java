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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pl.wbd.managers.StaffStatementHandler;
import pl.wbd.panels.StaffPanel;

@SuppressWarnings("serial")
public class WorkerCard extends JFrame {

	private JPanel contentPane;
	private JTextField nazwaUzytkownika;
	private JTextField zatrDo;
	private JTextField idPr;
	private StaffStatementHandler ssh;
	private JButton zmienDane;
	private Long idPracownika;
	private JLabel errorLabel;
	private JLabel kupL;
	private JLabel jazdL;
	private StaffPanel panel;
	private boolean changeData = false;
	private JTextField imie;
	private JTextField dimie;
	private JTextField nazwisko;
	private JTextField dataur;
	private JTextField nrTel;
	private JTextField zatrOd;

	private Vector<String> adres;
	private HashMap<String, Long> idmap;
	private ArrayList<Long> id;
	private JComboBox<String> stanowisko;
	private JComboBox<String> oddzial;

	private String selectedDep;
	private JTextField pensja;

	/**
	 * Create the frame.
	 */
	public WorkerCard(Long idk, StaffStatementHandler sth, StaffPanel panel2) {
		ssh = sth;
		panel = panel2;
		idPracownika = idk;
		adres = new Vector<String>();
		id = new ArrayList<Long>();
		idmap = new HashMap<String, Long>();
		setAlwaysOnTop(true);
		setTitle("Karta klienta");
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 35, 53, 0, 33, 30, 0, 0,
				0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 18, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
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

		JLabel lblKartaOddziau = new JLabel("KARTA KLIENTA");
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

		JLabel lblNewLabel = new JLabel("ID Pracownika:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		idPr = new JTextField();
		idPr.setFont(new Font("Tahoma", Font.BOLD, 11));
		idPr.setEditable(false);
		idPr.setEnabled(false);
		GridBagConstraints gbc_idPr = new GridBagConstraints();
		gbc_idPr.gridwidth = 5;
		gbc_idPr.insets = new Insets(0, 0, 5, 5);
		gbc_idPr.fill = GridBagConstraints.HORIZONTAL;
		gbc_idPr.gridx = 3;
		gbc_idPr.gridy = 3;
		contentPane.add(idPr, gbc_idPr);
		idPr.setColumns(10);
		idPr.setText(idPracownika.toString());

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

		JLabel lblNewLabel_2 = new JLabel("Imię:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 5;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		imie = new JTextField();
		imie.setFont(new Font("Tahoma", Font.BOLD, 11));
		imie.setEnabled(false);
		GridBagConstraints gbc_imie = new GridBagConstraints();
		gbc_imie.gridwidth = 5;
		gbc_imie.insets = new Insets(0, 0, 5, 5);
		gbc_imie.fill = GridBagConstraints.HORIZONTAL;
		gbc_imie.gridx = 3;
		gbc_imie.gridy = 5;
		contentPane.add(imie, gbc_imie);
		imie.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Drugie imię:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 6;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		dimie = new JTextField();
		dimie.setFont(new Font("Tahoma", Font.BOLD, 11));
		dimie.setEnabled(false);
		GridBagConstraints gbc_dimie = new GridBagConstraints();
		gbc_dimie.gridwidth = 5;
		gbc_dimie.insets = new Insets(0, 0, 5, 5);
		gbc_dimie.fill = GridBagConstraints.HORIZONTAL;
		gbc_dimie.gridx = 3;
		gbc_dimie.gridy = 6;
		contentPane.add(dimie, gbc_dimie);
		dimie.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Nazwisko:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 7;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		nazwisko = new JTextField();
		nazwisko.setFont(new Font("Tahoma", Font.BOLD, 11));
		nazwisko.setEnabled(false);
		GridBagConstraints gbc_nazwisko = new GridBagConstraints();
		gbc_nazwisko.gridwidth = 5;
		gbc_nazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_nazwisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_nazwisko.gridx = 3;
		gbc_nazwisko.gridy = 7;
		contentPane.add(nazwisko, gbc_nazwisko);
		nazwisko.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Data urodzenia:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 8;
		contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);

		dataur = new JTextField();
		dataur.setFont(new Font("Tahoma", Font.BOLD, 11));
		dataur.setEnabled(false);
		GridBagConstraints gbc_dataur = new GridBagConstraints();
		gbc_dataur.gridwidth = 5;
		gbc_dataur.insets = new Insets(0, 0, 5, 5);
		gbc_dataur.fill = GridBagConstraints.HORIZONTAL;
		gbc_dataur.gridx = 3;
		gbc_dataur.gridy = 8;
		contentPane.add(dataur, gbc_dataur);
		dataur.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 8;
		gbc_horizontalStrut_1.gridy = 9;
		contentPane.add(horizontalStrut_1, gbc_horizontalStrut_1);

		JLabel lblNewLabel_8 = new JLabel("Nr telefonu:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.gridwidth = 2;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 10;
		contentPane.add(lblNewLabel_8, gbc_lblNewLabel_8);

		nrTel = new JTextField();
		nrTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nrTel.setEnabled(false);
		GridBagConstraints gbc_nrTel = new GridBagConstraints();
		gbc_nrTel.gridwidth = 5;
		gbc_nrTel.insets = new Insets(0, 0, 5, 5);
		gbc_nrTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_nrTel.gridx = 3;
		gbc_nrTel.gridy = 10;
		contentPane.add(nrTel, gbc_nrTel);
		nrTel.setColumns(10);

		Component verticalGlue_3 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_3 = new GridBagConstraints();
		gbc_verticalGlue_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_3.gridx = 5;
		gbc_verticalGlue_3.gridy = 11;
		contentPane.add(verticalGlue_3, gbc_verticalGlue_3);

		JLabel lblMiejscowo = new JLabel("Nazwa użytkownika:");
		GridBagConstraints gbc_lblMiejscowo = new GridBagConstraints();
		gbc_lblMiejscowo.gridwidth = 3;
		gbc_lblMiejscowo.anchor = GridBagConstraints.EAST;
		gbc_lblMiejscowo.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiejscowo.gridx = 0;
		gbc_lblMiejscowo.gridy = 12;
		contentPane.add(lblMiejscowo, gbc_lblMiejscowo);

		nazwaUzytkownika = new JTextField();
		nazwaUzytkownika.setFont(new Font("Tahoma", Font.BOLD, 11));
		nazwaUzytkownika.setEnabled(false);
		GridBagConstraints gbc_nazwaUzytkownika = new GridBagConstraints();
		gbc_nazwaUzytkownika.gridwidth = 5;
		gbc_nazwaUzytkownika.insets = new Insets(0, 0, 5, 5);
		gbc_nazwaUzytkownika.fill = GridBagConstraints.HORIZONTAL;
		gbc_nazwaUzytkownika.gridx = 3;
		gbc_nazwaUzytkownika.gridy = 12;
		contentPane.add(nazwaUzytkownika, gbc_nazwaUzytkownika);
		nazwaUzytkownika.setColumns(10);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 8;
		gbc_horizontalStrut_2.gridy = 12;
		contentPane.add(horizontalStrut_2, gbc_horizontalStrut_2);

		JLabel lblOddzial = new JLabel("Oddział:");
		GridBagConstraints gbc_lblOddzial = new GridBagConstraints();
		gbc_lblOddzial.gridwidth = 2;
		gbc_lblOddzial.anchor = GridBagConstraints.EAST;
		gbc_lblOddzial.insets = new Insets(0, 0, 5, 5);
		gbc_lblOddzial.gridx = 1;
		gbc_lblOddzial.gridy = 13;
		contentPane.add(lblOddzial, gbc_lblOddzial);
		try {
			ResultSet odd = sth.getDepartments();
			while (odd.next()) {
				id.add(odd.getLong(1));
				idmap.put(odd.getString(2), odd.getLong(1));
				adres.add(odd.getString(2));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		selectedDep = adres.get(0);
		oddzial = new JComboBox<String>();
		oddzial.setModel(new DefaultComboBoxModel<String>(adres));
		oddzial.setEnabled(false);

		oddzial.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_oddzial = new GridBagConstraints();
		gbc_oddzial.gridwidth = 5;
		gbc_oddzial.insets = new Insets(0, 0, 5, 5);
		gbc_oddzial.fill = GridBagConstraints.HORIZONTAL;
		gbc_oddzial.gridx = 3;
		gbc_oddzial.gridy = 13;
		contentPane.add(oddzial, gbc_oddzial);
		oddzial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedDep = (String) oddzial.getSelectedItem();	
			}
		});

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_3.gridx = 8;
		gbc_horizontalStrut_3.gridy = 13;
		contentPane.add(horizontalStrut_3, gbc_horizontalStrut_3);

		JLabel lblStanowisko = new JLabel("Stanowisko:");
		GridBagConstraints gbc_lblStanowisko = new GridBagConstraints();
		gbc_lblStanowisko.gridwidth = 2;
		gbc_lblStanowisko.anchor = GridBagConstraints.EAST;
		gbc_lblStanowisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblStanowisko.gridx = 1;
		gbc_lblStanowisko.gridy = 14;
		contentPane.add(lblStanowisko, gbc_lblStanowisko);

		stanowisko = new JComboBox();
		stanowisko.setEnabled(false);
		stanowisko.setModel(new DefaultComboBoxModel<String>(sth
				.getStanowiska()));
		stanowisko.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_stanowisko = new GridBagConstraints();
		gbc_stanowisko.gridwidth = 5;
		gbc_stanowisko.insets = new Insets(0, 0, 5, 5);
		gbc_stanowisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_stanowisko.gridx = 3;
		gbc_stanowisko.gridy = 14;
		contentPane.add(stanowisko, gbc_stanowisko);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_4.gridx = 8;
		gbc_horizontalStrut_4.gridy = 14;
		contentPane.add(horizontalStrut_4, gbc_horizontalStrut_4);

		JLabel lblPensja = new JLabel("Pensja:");
		GridBagConstraints gbc_lblPensja = new GridBagConstraints();
		gbc_lblPensja.gridwidth = 2;
		gbc_lblPensja.anchor = GridBagConstraints.EAST;
		gbc_lblPensja.insets = new Insets(0, 0, 5, 5);
		gbc_lblPensja.gridx = 1;
		gbc_lblPensja.gridy = 15;
		contentPane.add(lblPensja, gbc_lblPensja);

		pensja = new JTextField();
		pensja.setFont(new Font("Tahoma", Font.BOLD, 11));
		pensja.setEnabled(false);
		GridBagConstraints gbc_pensja = new GridBagConstraints();
		gbc_pensja.gridwidth = 5;
		gbc_pensja.insets = new Insets(0, 0, 5, 5);
		gbc_pensja.fill = GridBagConstraints.HORIZONTAL;
		gbc_pensja.gridx = 3;
		gbc_pensja.gridy = 15;
		contentPane.add(pensja, gbc_pensja);
		pensja.setColumns(10);

		Component verticalGlue = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
		gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue.gridx = 5;
		gbc_verticalGlue.gridy = 15;
		contentPane.add(verticalGlue, gbc_verticalGlue);

		JLabel lblOd = new JLabel("Zatrudniony od:");
		GridBagConstraints gbc_lblOd = new GridBagConstraints();
		gbc_lblOd.gridwidth = 2;
		gbc_lblOd.anchor = GridBagConstraints.EAST;
		gbc_lblOd.insets = new Insets(0, 0, 5, 5);
		gbc_lblOd.gridx = 1;
		gbc_lblOd.gridy = 16;
		contentPane.add(lblOd, gbc_lblOd);

		zatrOd = new JTextField();
		zatrOd.setFont(new Font("Tahoma", Font.BOLD, 11));
		zatrOd.setEnabled(false);
		GridBagConstraints gbc_zatrOd = new GridBagConstraints();
		gbc_zatrOd.gridwidth = 5;
		gbc_zatrOd.insets = new Insets(0, 0, 5, 5);
		gbc_zatrOd.fill = GridBagConstraints.HORIZONTAL;
		gbc_zatrOd.gridx = 3;
		gbc_zatrOd.gridy = 16;
		contentPane.add(zatrOd, gbc_zatrOd);
		zatrOd.setColumns(10);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_5.gridx = 8;
		gbc_horizontalStrut_5.gridy = 16;
		contentPane.add(horizontalStrut_5, gbc_horizontalStrut_5);

		JLabel lblNewLabel_7 = new JLabel("Zatrudniony do:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.gridwidth = 2;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 17;
		contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);

		zatrDo = new JTextField();
		zatrDo.setFont(new Font("Tahoma", Font.BOLD, 11));
		zatrDo.setEnabled(false);
		GridBagConstraints gbc_zatrDo = new GridBagConstraints();
		gbc_zatrDo.gridwidth = 5;
		gbc_zatrDo.insets = new Insets(0, 0, 5, 5);
		gbc_zatrDo.fill = GridBagConstraints.HORIZONTAL;
		gbc_zatrDo.gridx = 3;
		gbc_zatrDo.gridy = 17;
		contentPane.add(zatrDo, gbc_zatrDo);
		zatrDo.setColumns(10);

		zmienDane = new JButton("Zmień dane");
		zmienDane.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GridBagConstraints gbc_zmienDane = new GridBagConstraints();
		gbc_zmienDane.gridwidth = 2;
		gbc_zmienDane.insets = new Insets(0, 0, 5, 5);
		gbc_zmienDane.gridx = 6;
		gbc_zmienDane.gridy = 18;
		contentPane.add(zmienDane, gbc_zmienDane);
		zmienDane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (changeData) {
					changeData = false;
					zmienDane.setText("Zmień dane");
					nazwaUzytkownika.setEnabled(false);
					zatrOd.setEnabled(false);
					stanowisko.setEnabled(false);
					zatrDo.setEnabled(false);
					imie.setEnabled(false);
					dimie.setEnabled(false);
					nazwisko.setEnabled(false);
					dataur.setEnabled(false);
					nrTel.setEnabled(false);
					pensja.setEnabled(false);
					
					int res = 0;
					try {
						res = ssh.updateWorker(idPracownika.toString(), imie
								.getText().trim(), dimie.getText().trim(),
								nazwisko.getText().trim(), dataur.getText()
										.trim(), nrTel.getText().trim(),stanowisko.getSelectedItem()
										.toString(), pensja.getText().trim(),
								nazwaUzytkownika.getText().trim(), zatrOd
										.getText().trim(), zatrDo.getText()
										.trim());
						if (res != 0) {
							loadData();
							panel.removeAllRows();
							panel.loadAllData(panel.getOrderType(), panel.isShowHired(), panel.getShowByStanowisko());
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					pensja.setText(pensja.getText() + " zł");
					loadData();
				} else {
					changeData = true;
					zmienDane.setText("  Zatwierdź  ");
					imie.setEnabled(true);
					dimie.setEnabled(true);
					nazwisko.setEnabled(true);
					dataur.setEnabled(true);
					nrTel.setEnabled(true);
					nazwaUzytkownika.setEnabled(true);
					zatrDo.setEnabled(true);
					zatrOd.setEnabled(true);
					stanowisko.setEnabled(true);
					pensja.setEnabled(true);
					pensja.setText(pensja.getText()
							.substring(0, pensja.getText().indexOf("z") - 1)
							.trim());
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Ilość sprzedanych:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 19;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		kupL = new JLabel("New label");
		GridBagConstraints gbc_kupL = new GridBagConstraints();
		gbc_kupL.insets = new Insets(0, 0, 5, 5);
		gbc_kupL.gridx = 5;
		gbc_kupL.gridy = 19;
		contentPane.add(kupL, gbc_kupL);

		JLabel lblNewLabel_4 = new JLabel("Ilość jazd próbnych:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridwidth = 4;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 20;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		jazdL = new JLabel("New label");
		GridBagConstraints gbc_jazdL = new GridBagConstraints();
		gbc_jazdL.insets = new Insets(0, 0, 5, 5);
		gbc_jazdL.gridx = 5;
		gbc_jazdL.gridy = 20;
		contentPane.add(jazdL, gbc_jazdL);

		JButton btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridwidth = 7;
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 21;
		contentPane.add(btnOk, gbc_btnOk);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JButton btnUsuKlienta = new JButton("Usuń pracownika");
		GridBagConstraints gbc_btnUsuKlienta = new GridBagConstraints();
		gbc_btnUsuKlienta.gridwidth = 7;
		gbc_btnUsuKlienta.insets = new Insets(0, 0, 5, 5);
		gbc_btnUsuKlienta.gridx = 1;
		gbc_btnUsuKlienta.gridy = 22;
		contentPane.add(btnUsuKlienta, gbc_btnUsuKlienta);
		btnUsuKlienta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ssh.deleteUser(idPracownika.toString());
					panel.deleteRow();
					dispose();
				} catch (SQLException e1) {
					if (e1.getErrorCode() == 2292)
						errorLabel
								.setText("Nie można usunąć Pracownika posiadającego podrzędne zależności");
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
		gbc_errorLabel.gridy = 23;
		contentPane.add(errorLabel, gbc_errorLabel);
		errorLabel.setVisible(false);

		Component verticalGlue_5 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_5 = new GridBagConstraints();
		gbc_verticalGlue_5.insets = new Insets(0, 0, 0, 5);
		gbc_verticalGlue_5.gridx = 4;
		gbc_verticalGlue_5.gridy = 25;
		contentPane.add(verticalGlue_5, gbc_verticalGlue_5);

		loadData();
	}

	private void loadData() {
		int oddzialS = 0;
		String stanowiskoS = null;
		String imieS = null;
		String nazwiskoS = null;
		String dimieS = null;
		String dataUr = null;
		String nazwaS = null;
		String nrTelS = null;
		String zatrOdS = null;
		String zatrDoS = null;
		String pensjaS = null;
		ResultSet rs = null;
		try {
			rs = ssh.getSingleWorker(idPracownika.toString());
			while (rs.next()) {
				oddzialS = rs.getInt(1);
				imieS = rs.getString(2);
				dimieS = rs.getString(3);
				nazwiskoS = rs.getString(4);
				dataUr = rs.getString(5);
				nrTelS = rs.getString(6);
				nazwaS = rs.getString(7);
				oddzialS = rs.getInt(9);
				stanowiskoS = rs.getString(10);
				pensjaS = rs.getString(11);
				zatrOdS = rs.getString(12);
				zatrDoS = rs.getString(13);
			}
			Long adrnr = idmap.get(selectedDep); // id wybranego oddziału
			if (rs != null)
				rs.close();

			String adresS = null;

			rs = null;
			rs = ssh.getDepartmentById(oddzialS);
			while (rs.next()) {
				adresS = rs.getString(2);
			}

			imie.setText(imieS);
			dimie.setText(dimieS);
			nazwisko.setText(nazwiskoS);
			dataur.setText(dataUr.substring(0, 10));
			nazwaUzytkownika.setText(nazwaS);
			if (zatrDoS != null && zatrDoS != "")
				zatrDo.setText(zatrDoS.substring(0, 10));
			if (zatrOdS != null && zatrOdS != "")
				zatrOd.setText(zatrOdS.substring(0, 10));
			stanowisko.setSelectedItem(stanowiskoS);
			oddzial.setSelectedItem(adresS);
			nrTel.setText(nrTelS);
			pensja.setText(pensjaS + " zł");

			Integer sold = ssh.getWorkerSold(idPracownika.toString());
			Integer tryouts = ssh.getWorkerTryouts(idPracownika.toString());

			kupL.setText(sold.toString());
			jazdL.setText(tryouts.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
