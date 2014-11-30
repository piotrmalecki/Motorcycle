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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pl.wbd.managers.ClientsStatementHandler;
import pl.wbd.panels.ClientsPanel;

@SuppressWarnings("serial")
public class ClientAdder extends JFrame {

	private final Dimension dim = new Dimension(280, 330);
	private JPanel contentPane;
	private ClientsStatementHandler cth;
	private ClientsPanel panel;
	private ClientAdder frame;
	private Vector<String> adres;
	private HashMap<String, Long> idmap;
	private ArrayList<Long> id;
	private String selectedDep;
	private JLabel lblImi;
	private JLabel lblNazwisko;
	private JLabel lblKodPocztowy;
	private JLabel lblUlica;
	private JLabel lblNumerDomu;
	private JLabel lblPesel;
	private JLabel lblNrTelefonu;
	private JLabel lblKatA;
	private JTextField imieT;
	private JTextField nazwiskoT;
	private JTextField kodT;
	private JTextField ulicaT;
	private JTextField mieszkanieT;
	private JTextField peselT;
	private JTextField telefonT;
	private JLabel lblDrugieImi;
	private JLabel lblDataUrodzenia;
	private JLabel lblMiejscowo;
	private JLabel lblNumerMieszk;
	private JTextField a1T;
	private JLabel lblDodajNowegoKlienta;
	private JLabel lblNewLabel;
	private JTextField a2T;
	private JLabel lblKatA_1;
	private JTextField aT;
	private JTextField domT;
	private JTextField miastoT;
	private JTextField dataT;
	private JTextField dimieT;
	private JButton btnNewButton;
	private JButton btnAnuluj;
	private JLabel errorLabel;

	/**
	 * Create the frame.
	 */
	public ClientAdder(ClientsStatementHandler sth, ClientsPanel st) {

		cth = sth;
		panel = st;
		frame = this;
		adres = new Vector<String>();
		id = new ArrayList<Long>();
		idmap = new HashMap<String, Long>();
		setAlwaysOnTop(true);
		setTitle("Salon motocyklowy - Nowy pracownik");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 417);
		setLocationRelativeTo(null);
		setSize(new Dimension(514, 419));
		setPreferredSize(dim);
		setResizable(false);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 26, 42, 31, 32, 49, 39, 43,
				84, 0, 0, 33, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblDodajNowegoKlienta = new JLabel("Dodaj nowego klienta");
		lblDodajNowegoKlienta.setFont(new Font("Tahoma", Font.BOLD
				| Font.ITALIC, 18));
		GridBagConstraints gbc_lblDodajNowegoKlienta = new GridBagConstraints();
		gbc_lblDodajNowegoKlienta.gridwidth = 6;
		gbc_lblDodajNowegoKlienta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodajNowegoKlienta.gridx = 3;
		gbc_lblDodajNowegoKlienta.gridy = 1;
		contentPane.add(lblDodajNowegoKlienta, gbc_lblDodajNowegoKlienta);

		lblImi = new JLabel("Imię:");
		GridBagConstraints gbc_lblImi = new GridBagConstraints();
		gbc_lblImi.anchor = GridBagConstraints.EAST;
		gbc_lblImi.gridwidth = 2;
		gbc_lblImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblImi.gridx = 0;
		gbc_lblImi.gridy = 3;
		contentPane.add(lblImi, gbc_lblImi);

		imieT = new JTextField();
		GridBagConstraints gbc_imieT = new GridBagConstraints();
		gbc_imieT.gridwidth = 3;
		gbc_imieT.insets = new Insets(0, 0, 5, 5);
		gbc_imieT.fill = GridBagConstraints.HORIZONTAL;
		gbc_imieT.gridx = 2;
		gbc_imieT.gridy = 3;
		contentPane.add(imieT, gbc_imieT);
		imieT.setColumns(10);

		lblDrugieImi = new JLabel("Drugie imię:");
		GridBagConstraints gbc_lblDrugieImi = new GridBagConstraints();
		gbc_lblDrugieImi.anchor = GridBagConstraints.EAST;
		gbc_lblDrugieImi.gridwidth = 2;
		gbc_lblDrugieImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblDrugieImi.gridx = 5;
		gbc_lblDrugieImi.gridy = 3;
		contentPane.add(lblDrugieImi, gbc_lblDrugieImi);

		dimieT = new JTextField();
		GridBagConstraints gbc_dimieT = new GridBagConstraints();
		gbc_dimieT.gridwidth = 4;
		gbc_dimieT.insets = new Insets(0, 0, 5, 5);
		gbc_dimieT.fill = GridBagConstraints.HORIZONTAL;
		gbc_dimieT.gridx = 7;
		gbc_dimieT.gridy = 3;
		contentPane.add(dimieT, gbc_dimieT);
		dimieT.setColumns(10);

		lblNazwisko = new JLabel("Nazwisko:");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.anchor = GridBagConstraints.EAST;
		gbc_lblNazwisko.gridwidth = 2;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 0;
		gbc_lblNazwisko.gridy = 4;
		contentPane.add(lblNazwisko, gbc_lblNazwisko);

		nazwiskoT = new JTextField();
		GridBagConstraints gbc_nazwiskoT = new GridBagConstraints();
		gbc_nazwiskoT.gridwidth = 3;
		gbc_nazwiskoT.insets = new Insets(0, 0, 5, 5);
		gbc_nazwiskoT.fill = GridBagConstraints.HORIZONTAL;
		gbc_nazwiskoT.gridx = 2;
		gbc_nazwiskoT.gridy = 4;
		contentPane.add(nazwiskoT, gbc_nazwiskoT);
		nazwiskoT.setColumns(10);

		lblDataUrodzenia = new JLabel("Data urodzenia:");
		GridBagConstraints gbc_lblDataUrodzenia = new GridBagConstraints();
		gbc_lblDataUrodzenia.anchor = GridBagConstraints.EAST;
		gbc_lblDataUrodzenia.gridwidth = 2;
		gbc_lblDataUrodzenia.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataUrodzenia.gridx = 5;
		gbc_lblDataUrodzenia.gridy = 4;
		contentPane.add(lblDataUrodzenia, gbc_lblDataUrodzenia);

		dataT = new JTextField();
		GridBagConstraints gbc_dataT = new GridBagConstraints();
		gbc_dataT.gridwidth = 4;
		gbc_dataT.insets = new Insets(0, 0, 5, 5);
		gbc_dataT.fill = GridBagConstraints.HORIZONTAL;
		gbc_dataT.gridx = 7;
		gbc_dataT.gridy = 4;
		contentPane.add(dataT, gbc_dataT);
		dataT.setColumns(10);

		lblKodPocztowy = new JLabel("Kod pocztowy:");
		GridBagConstraints gbc_lblKodPocztowy = new GridBagConstraints();
		gbc_lblKodPocztowy.anchor = GridBagConstraints.EAST;
		gbc_lblKodPocztowy.gridwidth = 2;
		gbc_lblKodPocztowy.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodPocztowy.gridx = 0;
		gbc_lblKodPocztowy.gridy = 5;
		contentPane.add(lblKodPocztowy, gbc_lblKodPocztowy);

		kodT = new JTextField();
		GridBagConstraints gbc_kodT = new GridBagConstraints();
		gbc_kodT.gridwidth = 3;
		gbc_kodT.insets = new Insets(0, 0, 5, 5);
		gbc_kodT.fill = GridBagConstraints.HORIZONTAL;
		gbc_kodT.gridx = 2;
		gbc_kodT.gridy = 5;
		contentPane.add(kodT, gbc_kodT);
		kodT.setColumns(10);

		lblMiejscowo = new JLabel("Miejscowość:");
		GridBagConstraints gbc_lblMiejscowo = new GridBagConstraints();
		gbc_lblMiejscowo.anchor = GridBagConstraints.EAST;
		gbc_lblMiejscowo.gridwidth = 2;
		gbc_lblMiejscowo.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiejscowo.gridx = 5;
		gbc_lblMiejscowo.gridy = 5;
		contentPane.add(lblMiejscowo, gbc_lblMiejscowo);

		miastoT = new JTextField();
		GridBagConstraints gbc_miastoT = new GridBagConstraints();
		gbc_miastoT.gridwidth = 4;
		gbc_miastoT.insets = new Insets(0, 0, 5, 5);
		gbc_miastoT.fill = GridBagConstraints.HORIZONTAL;
		gbc_miastoT.gridx = 7;
		gbc_miastoT.gridy = 5;
		contentPane.add(miastoT, gbc_miastoT);
		miastoT.setColumns(10);

		lblUlica = new JLabel("Ulica:");
		GridBagConstraints gbc_lblUlica = new GridBagConstraints();
		gbc_lblUlica.anchor = GridBagConstraints.EAST;
		gbc_lblUlica.gridwidth = 2;
		gbc_lblUlica.insets = new Insets(0, 0, 5, 5);
		gbc_lblUlica.gridx = 0;
		gbc_lblUlica.gridy = 6;
		contentPane.add(lblUlica, gbc_lblUlica);

		ulicaT = new JTextField();
		GridBagConstraints gbc_ulicaT = new GridBagConstraints();
		gbc_ulicaT.gridwidth = 3;
		gbc_ulicaT.insets = new Insets(0, 0, 5, 5);
		gbc_ulicaT.fill = GridBagConstraints.HORIZONTAL;
		gbc_ulicaT.gridx = 2;
		gbc_ulicaT.gridy = 6;
		contentPane.add(ulicaT, gbc_ulicaT);
		ulicaT.setColumns(10);

		lblNumerDomu = new JLabel("Numer domu:");
		GridBagConstraints gbc_lblNumerDomu = new GridBagConstraints();
		gbc_lblNumerDomu.anchor = GridBagConstraints.EAST;
		gbc_lblNumerDomu.gridwidth = 2;
		gbc_lblNumerDomu.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumerDomu.gridx = 5;
		gbc_lblNumerDomu.gridy = 6;
		contentPane.add(lblNumerDomu, gbc_lblNumerDomu);

		domT = new JTextField();
		GridBagConstraints gbc_domT = new GridBagConstraints();
		gbc_domT.gridwidth = 4;
		gbc_domT.insets = new Insets(0, 0, 5, 5);
		gbc_domT.fill = GridBagConstraints.HORIZONTAL;
		gbc_domT.gridx = 7;
		gbc_domT.gridy = 6;
		contentPane.add(domT, gbc_domT);
		domT.setColumns(10);

		lblNumerMieszk = new JLabel("Numer mieszk.");
		GridBagConstraints gbc_lblNumerMieszk = new GridBagConstraints();
		gbc_lblNumerMieszk.gridwidth = 2;
		gbc_lblNumerMieszk.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumerMieszk.gridx = 0;
		gbc_lblNumerMieszk.gridy = 7;
		contentPane.add(lblNumerMieszk, gbc_lblNumerMieszk);

		mieszkanieT = new JTextField();
		GridBagConstraints gbc_mieszkanieT = new GridBagConstraints();
		gbc_mieszkanieT.gridwidth = 3;
		gbc_mieszkanieT.insets = new Insets(0, 0, 5, 5);
		gbc_mieszkanieT.fill = GridBagConstraints.HORIZONTAL;
		gbc_mieszkanieT.gridx = 2;
		gbc_mieszkanieT.gridy = 7;
		contentPane.add(mieszkanieT, gbc_mieszkanieT);
		mieszkanieT.setColumns(10);

		lblPesel = new JLabel("Pesel:");
		GridBagConstraints gbc_lblPesel = new GridBagConstraints();
		gbc_lblPesel.anchor = GridBagConstraints.EAST;
		gbc_lblPesel.gridwidth = 2;
		gbc_lblPesel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesel.gridx = 0;
		gbc_lblPesel.gridy = 8;
		contentPane.add(lblPesel, gbc_lblPesel);

		peselT = new JTextField();
		GridBagConstraints gbc_peselT = new GridBagConstraints();
		gbc_peselT.gridwidth = 3;
		gbc_peselT.insets = new Insets(0, 0, 5, 5);
		gbc_peselT.fill = GridBagConstraints.HORIZONTAL;
		gbc_peselT.gridx = 2;
		gbc_peselT.gridy = 8;
		contentPane.add(peselT, gbc_peselT);
		peselT.setColumns(10);

		lblNrTelefonu = new JLabel("Nr Telefonu:");
		GridBagConstraints gbc_lblNrTelefonu = new GridBagConstraints();
		gbc_lblNrTelefonu.anchor = GridBagConstraints.EAST;
		gbc_lblNrTelefonu.gridwidth = 2;
		gbc_lblNrTelefonu.insets = new Insets(0, 0, 5, 5);
		gbc_lblNrTelefonu.gridx = 5;
		gbc_lblNrTelefonu.gridy = 8;
		contentPane.add(lblNrTelefonu, gbc_lblNrTelefonu);

		telefonT = new JTextField();
		GridBagConstraints gbc_telefonT = new GridBagConstraints();
		gbc_telefonT.gridwidth = 4;
		gbc_telefonT.insets = new Insets(0, 0, 5, 5);
		gbc_telefonT.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonT.gridx = 7;
		gbc_telefonT.gridy = 8;
		contentPane.add(telefonT, gbc_telefonT);
		telefonT.setColumns(10);

		lblKatA = new JLabel("Kat A1:");
		GridBagConstraints gbc_lblKatA = new GridBagConstraints();
		gbc_lblKatA.anchor = GridBagConstraints.EAST;
		gbc_lblKatA.insets = new Insets(0, 0, 5, 5);
		gbc_lblKatA.gridx = 0;
		gbc_lblKatA.gridy = 10;
		contentPane.add(lblKatA, gbc_lblKatA);

		a1T = new JTextField();
		GridBagConstraints gbc_a1T = new GridBagConstraints();
		gbc_a1T.gridwidth = 2;
		gbc_a1T.insets = new Insets(0, 0, 5, 5);
		gbc_a1T.fill = GridBagConstraints.HORIZONTAL;
		gbc_a1T.gridx = 1;
		gbc_a1T.gridy = 10;
		contentPane.add(a1T, gbc_a1T);
		a1T.setColumns(10);

		lblNewLabel = new JLabel("Kat A2:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 10;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		a2T = new JTextField();
		GridBagConstraints gbc_a2T = new GridBagConstraints();
		gbc_a2T.gridwidth = 2;
		gbc_a2T.insets = new Insets(0, 0, 5, 5);
		gbc_a2T.fill = GridBagConstraints.HORIZONTAL;
		gbc_a2T.gridx = 4;
		gbc_a2T.gridy = 10;
		contentPane.add(a2T, gbc_a2T);
		a2T.setColumns(10);

		lblKatA_1 = new JLabel("Kat A:");
		GridBagConstraints gbc_lblKatA_1 = new GridBagConstraints();
		gbc_lblKatA_1.anchor = GridBagConstraints.EAST;
		gbc_lblKatA_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblKatA_1.gridx = 6;
		gbc_lblKatA_1.gridy = 10;
		contentPane.add(lblKatA_1, gbc_lblKatA_1);

		aT = new JTextField();
		GridBagConstraints gbc_aT = new GridBagConstraints();
		gbc_aT.gridwidth = 2;
		gbc_aT.insets = new Insets(0, 0, 5, 5);
		gbc_aT.fill = GridBagConstraints.HORIZONTAL;
		gbc_aT.gridx = 7;
		gbc_aT.gridy = 10;
		contentPane.add(aT, gbc_aT);
		aT.setColumns(10);

		btnNewButton = new JButton("Dodaj");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 12;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cth.addNewClient(imieT.getText().trim(), dimieT.getText()
							.trim(), nazwiskoT.getText().trim(), dataT.getText()
							.trim(), kodT.getText().trim(), miastoT.getText()
							.trim(), ulicaT.getText().trim(),
							domT.getText().trim(), mieszkanieT.getText().trim(),
							peselT.getText().trim(), telefonT.getText().trim(), a1T
									.getText().trim(), a2T.getText().trim(), aT
									.getText().trim());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					System.err.println("Blad dodajac do bazy "
							+ e1.getErrorCode() + " mes: " + e1.getMessage());
					if (e1.getErrorCode() == 1400) {
						errorLabel
								.setText("Nie wprowadzono wszystkich wartości bądź podano wartość nieprawidłową");

					} else
						JOptionPane.showMessageDialog(frame,
								"Spróbuj ponownie!", "Nieznany błąd",
								JOptionPane.ERROR_MESSAGE);
				}
				panel.removeAllRows();
				panel.loadAllData(panel.getOrderType(), panel.getShowByMiejscowosc());
				dispose();
			}
		});

		btnAnuluj = new JButton("Anuluj");
		GridBagConstraints gbc_btnAnuluj = new GridBagConstraints();
		gbc_btnAnuluj.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnuluj.gridwidth = 4;
		gbc_btnAnuluj.gridx = 6;
		gbc_btnAnuluj.gridy = 12;
		contentPane.add(btnAnuluj, gbc_btnAnuluj);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 7;
		gbc_errorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorLabel.gridx = 2;
		gbc_errorLabel.gridy = 13;
		contentPane.add(errorLabel, gbc_errorLabel);
		btnAnuluj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
