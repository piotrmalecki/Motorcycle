package pl.wbd.ui.frames;

import java.awt.BorderLayout;
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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.wbd.managers.ClientsStatementHandler;
import pl.wbd.panels.ClientsPanel;

@SuppressWarnings("serial")
public class SelectClient extends JFrame {
	
	private ArrayList<String> idKlientaL;
	private ArrayList<String> imieL;
	private ArrayList<String> nazwiskoL;
	private ArrayList<String> miejscowoscL;
	private ArrayList<String> ulice;
	private ArrayList<String> telefonL;
	private ArrayList<String> nrDomu;
	private ArrayList<Integer> nrMieszkania;
	private ArrayList<String> dimie;
	private ArrayList<String> a1L;
	private ArrayList<String> a2L;
	private ArrayList<String> aL;

	private ArrayList<String> query;
	
	private JTextField imie;
	private JTextField nazwisko;
	private JTextField nrTel;
	private JTextField pesel;
	private JTextField transOd;
	private JTextField transDo;
	
	private JCheckBox chckbxImie;
	private JCheckBox chckbxNazwisko;
	private JCheckBox chckbxOddzia;
	private JCheckBox chckbxPrzedziaTrans;
	private JCheckBox chckbxNrTelefonu;
	private JCheckBox chckbxPesel;
	private JCheckBox chckbxPosiadaA;
	private JCheckBox chckbxPosiadaA_1;
	private JCheckBox chckbxPosiadaKatA;
	
	private JComboBox<String> miejscowosc;
	
	private Boolean imieB = false;
	private Boolean nazwiskoB = false;
	private Boolean nrTelB = false;
	private Boolean miejscowoscB = false;
	private Boolean transakcjeB = false;
	private Boolean peselB = false;
	private Boolean a1B = false;
	private Boolean a2B = false;
	private Boolean aB = false;
	
	private String imieQ;
	private String nazwiskoQ;
	private String miejscowoscQ;
	private String transakcjeQ;
	private String nrTelQ;
	private String peselQ;
	private String a1Q;
	private String a2Q;
	private String aQ;
	
	private ClientsPanel panell;
	private ClientsStatementHandler csh;
	
	private final Dimension dim = new Dimension(380, 330);
	/**
	 * Create the panel.
	 */
	public SelectClient(ClientsStatementHandler sshh, ClientsPanel st) {
		csh = sshh;
		panell = st;
		
		idKlientaL = new ArrayList<String>();
		imieL = new ArrayList<String>();
		dimie = new ArrayList<String>();
		miejscowoscL= new ArrayList<String>();
		nazwiskoL = new ArrayList<String>();
		nrDomu = new ArrayList<String>();
		telefonL = new ArrayList<String>();
		ulice = new ArrayList<String>();
		nrMieszkania = new ArrayList<Integer>();
		a1L = new ArrayList<String>();
		a2L = new ArrayList<String>();
		aL = new ArrayList<String>();
		
		query = new ArrayList<String>();
		
		setTitle("Wyszukaj klientów");
		setResizable(false);
		setAlwaysOnTop(true);
		setSize(new Dimension(364, 388));
		setPreferredSize(dim);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		setVisible(true);
		JLabel lblWyszukajKlienta = new JLabel("Wyszukaj klientów");
		lblWyszukajKlienta.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblWyszukajKlienta = new GridBagConstraints();
		gbc_lblWyszukajKlienta.gridwidth = 5;
		gbc_lblWyszukajKlienta.insets = new Insets(0, 0, 5, 5);
		gbc_lblWyszukajKlienta.gridx = 1;
		gbc_lblWyszukajKlienta.gridy = 1;
		panel.add(lblWyszukajKlienta, gbc_lblWyszukajKlienta);
		
		chckbxImie = new JCheckBox("Imie");
		GridBagConstraints gbc_chckbxImie = new GridBagConstraints();
		gbc_chckbxImie.anchor = GridBagConstraints.WEST;
		gbc_chckbxImie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxImie.gridx = 1;
		gbc_chckbxImie.gridy = 2;
		panel.add(chckbxImie, gbc_chckbxImie);
		
		
		imie = new JTextField();
		imie.setEnabled(false);
		GridBagConstraints gbc_imie = new GridBagConstraints();
		gbc_imie.gridwidth = 4;
		gbc_imie.insets = new Insets(0, 0, 5, 5);
		gbc_imie.fill = GridBagConstraints.HORIZONTAL;
		gbc_imie.gridx = 2;
		gbc_imie.gridy = 2;
		panel.add(imie, gbc_imie);
		imie.setColumns(10);
		chckbxImie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxImie.isSelected()) {
					imie.setEnabled(true);
					imieB = true;
				} else {
					imie.setEnabled(false);
					imieB = false;
				}
				
			}
		});
		
		chckbxNazwisko = new JCheckBox("Nazwisko");
		GridBagConstraints gbc_chckbxNazwisko = new GridBagConstraints();
		gbc_chckbxNazwisko.anchor = GridBagConstraints.WEST;
		gbc_chckbxNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNazwisko.gridx = 1;
		gbc_chckbxNazwisko.gridy = 3;
		panel.add(chckbxNazwisko, gbc_chckbxNazwisko);
		
		nazwisko = new JTextField();
		nazwisko.setEnabled(false);
		GridBagConstraints gbc_nazwisko = new GridBagConstraints();
		gbc_nazwisko.gridwidth = 4;
		gbc_nazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_nazwisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_nazwisko.gridx = 2;
		gbc_nazwisko.gridy = 3;
		panel.add(nazwisko, gbc_nazwisko);
		nazwisko.setColumns(10);
		chckbxNazwisko.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxNazwisko.isSelected()) {
					nazwisko.setEnabled(true);
					nazwiskoB = true;
				} else {
					nazwisko.setEnabled(false);
					nazwiskoB = false;
				}
				
			}
		});
		
		chckbxOddzia = new JCheckBox("Miejscowość");
		GridBagConstraints gbc_chckbxOddzia = new GridBagConstraints();
		gbc_chckbxOddzia.anchor = GridBagConstraints.WEST;
		gbc_chckbxOddzia.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOddzia.gridx = 1;
		gbc_chckbxOddzia.gridy = 4;
		panel.add(chckbxOddzia, gbc_chckbxOddzia);
		
		miejscowosc = new JComboBox<String>();
		miejscowosc.setEnabled(false);
		Vector<String> miejscowosci = new Vector<String>();
		try {
			miejscowosci = csh.getMiejscowosci();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		miejscowosc.setModel(new DefaultComboBoxModel<String>(miejscowosci));
		GridBagConstraints gbc_miejscowosc = new GridBagConstraints();
		gbc_miejscowosc.gridwidth = 4;
		gbc_miejscowosc.insets = new Insets(0, 0, 5, 5);
		gbc_miejscowosc.fill = GridBagConstraints.HORIZONTAL;
		gbc_miejscowosc.gridx = 2;
		gbc_miejscowosc.gridy = 4;
		panel.add(miejscowosc, gbc_miejscowosc);
		chckbxOddzia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxOddzia.isSelected()) {
					miejscowosc.setEnabled(true);
					miejscowoscB = true;
				} else {
					miejscowosc.setEnabled(false);
					miejscowoscB = false;
				}
				
			}
		});
		
		chckbxPrzedziaTrans = new JCheckBox("Przedział transakcji");
		GridBagConstraints gbc_chckbxPrzedziaTrans = new GridBagConstraints();
		gbc_chckbxPrzedziaTrans.anchor = GridBagConstraints.WEST;
		gbc_chckbxPrzedziaTrans.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPrzedziaTrans.gridx = 1;
		gbc_chckbxPrzedziaTrans.gridy = 5;
		panel.add(chckbxPrzedziaTrans, gbc_chckbxPrzedziaTrans);
		
		transOd = new JTextField();
		transOd.setEnabled(false);
		GridBagConstraints gbc_transOd = new GridBagConstraints();
		gbc_transOd.insets = new Insets(0, 0, 5, 5);
		gbc_transOd.fill = GridBagConstraints.HORIZONTAL;
		gbc_transOd.gridx = 2;
		gbc_transOd.gridy = 5;
		panel.add(transOd, gbc_transOd);
		transOd.setColumns(10);
		
		JLabel lblDo = new JLabel("do");
		GridBagConstraints gbc_lblDo = new GridBagConstraints();
		gbc_lblDo.insets = new Insets(0, 0, 5, 5);
		gbc_lblDo.anchor = GridBagConstraints.EAST;
		gbc_lblDo.gridx = 3;
		gbc_lblDo.gridy = 5;
		panel.add(lblDo, gbc_lblDo);
		
		transDo = new JTextField();
		transDo.setEnabled(false);
		GridBagConstraints gbc_transDo = new GridBagConstraints();
		gbc_transDo.gridwidth = 2;
		gbc_transDo.insets = new Insets(0, 0, 5, 5);
		gbc_transDo.fill = GridBagConstraints.HORIZONTAL;
		gbc_transDo.gridx = 4;
		gbc_transDo.gridy = 5;
		panel.add(transDo, gbc_transDo);
		transDo.setColumns(10);
		chckbxPrzedziaTrans.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxPrzedziaTrans.isSelected()) {
					transOd.setEnabled(true);
					transDo.setEnabled(true);
					transakcjeB = true;
				} else {
					transOd.setEnabled(false);
					transDo.setEnabled(false);
					transakcjeB = false;
				}			
			}
		});
		
		chckbxNrTelefonu = new JCheckBox("Numer telefonu");
		GridBagConstraints gbc_chckbxNrTelefonu = new GridBagConstraints();
		gbc_chckbxNrTelefonu.anchor = GridBagConstraints.WEST;
		gbc_chckbxNrTelefonu.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNrTelefonu.gridx = 1;
		gbc_chckbxNrTelefonu.gridy = 6;
		panel.add(chckbxNrTelefonu, gbc_chckbxNrTelefonu);
		
		nrTel = new JTextField();
		nrTel.setEnabled(false);
		GridBagConstraints gbc_nrTel = new GridBagConstraints();
		gbc_nrTel.gridwidth = 3;
		gbc_nrTel.insets = new Insets(0, 0, 5, 5);
		gbc_nrTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_nrTel.gridx = 2;
		gbc_nrTel.gridy = 6;
		panel.add(nrTel, gbc_nrTel);
		nrTel.setColumns(10);
		chckbxNrTelefonu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxNrTelefonu.isSelected()) {
					nrTel.setEnabled(true);
					nrTelB = true;
				} else {
					nrTel.setEnabled(false);
					nrTelB = false;
				}				
			}
		});
		
		chckbxPesel = new JCheckBox("PESEL");
		GridBagConstraints gbc_chckbxPesel = new GridBagConstraints();
		gbc_chckbxPesel.anchor = GridBagConstraints.WEST;
		gbc_chckbxPesel.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPesel.gridx = 1;
		gbc_chckbxPesel.gridy = 7;
		panel.add(chckbxPesel, gbc_chckbxPesel);
		
		pesel = new JTextField();
		pesel.setEnabled(false);
		GridBagConstraints gbc_pesel = new GridBagConstraints();
		gbc_pesel.insets = new Insets(0, 0, 5, 5);
		gbc_pesel.gridwidth = 3;
		gbc_pesel.fill = GridBagConstraints.HORIZONTAL;
		gbc_pesel.gridx = 2;
		gbc_pesel.gridy = 7;
		panel.add(pesel, gbc_pesel);
		pesel.setColumns(10);
		chckbxPesel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxPesel.isSelected()) {
					pesel.setEnabled(true);
					peselB = true;
				} else {
					pesel.setEnabled(false);
					peselB = false;
				}				
			}
		});
		
		chckbxPosiadaA = new JCheckBox("posiada kat. A1");
		GridBagConstraints gbc_chckbxPosiadaA = new GridBagConstraints();
		gbc_chckbxPosiadaA.anchor = GridBagConstraints.WEST;
		gbc_chckbxPosiadaA.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPosiadaA.gridx = 1;
		gbc_chckbxPosiadaA.gridy = 8;
		panel.add(chckbxPosiadaA, gbc_chckbxPosiadaA);
		chckbxPosiadaA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxPosiadaA.isSelected())
					a1B = true;
				else 
					a1B = false;
				
			}
		});
		
		chckbxPosiadaA_1 = new JCheckBox("posiada kat. A2");
		GridBagConstraints gbc_chckbxPosiadaA_1 = new GridBagConstraints();
		gbc_chckbxPosiadaA_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxPosiadaA_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPosiadaA_1.gridx = 1;
		gbc_chckbxPosiadaA_1.gridy = 9;
		panel.add(chckbxPosiadaA_1, gbc_chckbxPosiadaA_1);
		chckbxPosiadaA_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxPosiadaA_1.isSelected())
					a2B = true;
				else 
					a2B = false;
				
			}
		});
		
		chckbxPosiadaKatA = new JCheckBox("posiada kat. A");
		GridBagConstraints gbc_chckbxPosiadaKatA = new GridBagConstraints();
		gbc_chckbxPosiadaKatA.anchor = GridBagConstraints.WEST;
		gbc_chckbxPosiadaKatA.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPosiadaKatA.gridx = 1;
		gbc_chckbxPosiadaKatA.gridy = 10;
		panel.add(chckbxPosiadaKatA, gbc_chckbxPosiadaKatA);
		chckbxPosiadaKatA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxPosiadaKatA.isSelected())
					aB = true;
				else 
					aB = false;
				
			}
		});
		
		JButton btnWyszukaj = new JButton("Wyszukaj");
		GridBagConstraints gbc_btnWyszukaj = new GridBagConstraints();
		gbc_btnWyszukaj.insets = new Insets(0, 0, 5, 5);
		gbc_btnWyszukaj.gridx = 1;
		gbc_btnWyszukaj.gridy = 11;
		panel.add(btnWyszukaj, gbc_btnWyszukaj);
		btnWyszukaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				generateQuery();
				ResultSet rs = null;
				rs = csh.findClients(query);
				query.clear();
				try {
					while (rs.next()) {
						idKlientaL.add(rs.getString(1));
						imieL.add(rs.getString(2));
						dimie.add(rs.getString(3));
						nazwiskoL.add(rs.getString(4));
						miejscowoscL.add(rs.getString(5));
						ulice.add(rs.getString(6));
						nrDomu.add(rs.getString(7));
						nrMieszkania.add(rs.getInt(8));
						telefonL.add(rs.getString(9));
						a1L.add(rs.getString(10));
						a2L.add(rs.getString(11));
						aL.add(rs.getString(12));
					}
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				panell.removeAllRows();
				for (int i = 0; i < imieL.size(); i++) {
					String prawojazdy = "";
					if (a1L.get(i) != null)
						prawojazdy = prawojazdy + " A1";
					if (a2L.get(i) != null)
						prawojazdy = prawojazdy + " A2";
					if (aL.get(i) != null)
						prawojazdy = prawojazdy + " A";

					panell.getModel().addRow(
							new Object[] {
									idKlientaL.get(i),
									imieL.get(i),
									dimie.get(i),
									nazwiskoL.get(i),
									miejscowoscL.get(i),
									ulice.get(i) + ", " + nrDomu.get(i) + "/"
											+ nrMieszkania.get(i), prawojazdy,
									telefonL.get(i) });
				}
				
				
				idKlientaL.clear();
				imieL.clear();
				dimie.clear();
				nazwiskoL.clear();
				miejscowoscL.clear();
				ulice.clear();
				nrDomu.clear();
				nrMieszkania.clear();
				telefonL.clear();
				a1L.clear();
				a2L.clear();
				aL.clear();
				dispose();
			}
		});
		
		JButton btnAnuluj = new JButton("Anuluj");
		GridBagConstraints gbc_btnAnuluj = new GridBagConstraints();
		gbc_btnAnuluj.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnuluj.gridx = 4;
		gbc_btnAnuluj.gridy = 11;
		panel.add(btnAnuluj, gbc_btnAnuluj);
		btnAnuluj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();			
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 12;
		panel.add(lblNewLabel, gbc_lblNewLabel);
	}

	private void generateQuery() {
		imieQ = "k.imie = '"+imie.getText()+"'";
		nazwiskoQ = "k.nazwisko = '"+nazwisko.getText()+"'";
		miejscowoscQ = (String) miejscowosc.getSelectedItem();
		//transakcjeQ = "z.pensja BETWEEN "+transOd.getText()+" AND "+transDo.getText();
		nrTelQ = "k.nrTelefonu = '"+nrTel.getText()+"'";
		peselQ = "k.pesel = '"+pesel.getText()+"'";
		a1Q = "p.dataWydaniaA1 IS NOT NULL";
		a2Q = "p.dataWydaniaA2 IS NOT NULL";
		aQ = "p.dataWydaniaA IS NOT NULL";
		
		if (imieB)
			query.add(imieQ);
		if (nazwiskoB)
			query.add(nazwiskoQ);
		if (miejscowoscB)
			query.add(miejscowoscQ);
		if (transakcjeB)
			query.add(transakcjeQ);
		if (nrTelB)
			query.add(nrTelQ);
		if (peselB)
			query.add(peselQ);
		if (a1B)
			query.add(a1Q);
		if (a2B)
			query.add(a2Q);
		if (aB)
			query.add(aQ);
		
		for (String single : query)
			System.out.println(single);
	}
}
