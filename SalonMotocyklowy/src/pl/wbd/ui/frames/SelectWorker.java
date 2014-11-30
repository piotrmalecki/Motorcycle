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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.wbd.managers.StaffStatementHandler;
import pl.wbd.panels.StaffPanel;

public class SelectWorker extends JFrame {
	
	private ArrayList<String> imieL;
	private ArrayList<String> dimie;
	private ArrayList<String> nazwiska;
	private ArrayList<String> oddzialy;
	private ArrayList<String> stanowiska;
	private ArrayList<String> dataZatrudnienia;
	private ArrayList<Float> pensja;
	private ArrayList<Long> idPracownika;
	
	private JTextField imie;
	private JTextField nazwisko;
	private JTextField nrTel;
	private JTextField dataZatr;
	private JTextField pensjaOd;
	private JTextField pensjaDo;
	
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxOddzia;
	private JCheckBox chckbxPrzedziaPensji;
	private JCheckBox chckbxNrTelefonu;
	private JCheckBox chckbxDataZatrudnienia;
	
	private JComboBox oddzial;
	
	private Boolean imieB = false;
	private Boolean nazwiskoB = false;
	private Boolean nrTelB = false;
	private Boolean dataZatrB = false;
	private Boolean pensjaB = false;
	private Boolean oddzialB = false;
	
	private String imieQ;
	private String nazwiskoQ;
	private String nrTelQ;
	private String dataZatrQ;
	private String pensjaQ;
	private String oddzialQ;
	
	private ArrayList<String> query = new ArrayList<String>();
	private StaffPanel panell;
	private StaffStatementHandler ssh;
	
	private final Dimension dim = new Dimension(380, 330);
	/**
	 * Create the panel.
	 */
	public SelectWorker(StaffStatementHandler sshh, StaffPanel st) {
		ssh = sshh;
		panell = st;
		
		imieL = new ArrayList<String>();
		dimie = new ArrayList<String>();
		nazwiska = new ArrayList<String>();
		oddzialy = new ArrayList<String>();
		stanowiska = new ArrayList<String>();
		dataZatrudnienia = new ArrayList<String>();
		pensja = new ArrayList<Float>();
		idPracownika = new ArrayList<Long>();
		
		setTitle("Wyszukaj pracowników");
		setResizable(false);
		setAlwaysOnTop(true);
		setSize(new Dimension(364, 315));
		setPreferredSize(dim);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		setVisible(true);
		JLabel lblDodajNowegoPracownika = new JLabel("Wyszukaj pracowników");
		lblDodajNowegoPracownika.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblDodajNowegoPracownika = new GridBagConstraints();
		gbc_lblDodajNowegoPracownika.gridwidth = 5;
		gbc_lblDodajNowegoPracownika.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodajNowegoPracownika.gridx = 1;
		gbc_lblDodajNowegoPracownika.gridy = 1;
		panel.add(lblDodajNowegoPracownika, gbc_lblDodajNowegoPracownika);
		
		chckbxNewCheckBox = new JCheckBox("Imie");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 2;
		panel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		
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
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					imie.setEnabled(true);
					imieB = true;
				} else {
					imie.setEnabled(false);
					imieB = false;
				}
				
			}
		});
		
		chckbxNewCheckBox_1 = new JCheckBox("Nazwisko");
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 3;
		panel.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
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
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_1.isSelected()) {
					nazwisko.setEnabled(true);
					nazwiskoB = true;
				} else {
					nazwisko.setEnabled(false);
					nazwiskoB = false;
				}
				
			}
		});
		
		chckbxOddzia = new JCheckBox("Oddział");
		GridBagConstraints gbc_chckbxOddzia = new GridBagConstraints();
		gbc_chckbxOddzia.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOddzia.gridx = 1;
		gbc_chckbxOddzia.gridy = 4;
		panel.add(chckbxOddzia, gbc_chckbxOddzia);
		
		oddzial = new JComboBox();
		oddzial.setEnabled(false);
		GridBagConstraints gbc_oddzial = new GridBagConstraints();
		gbc_oddzial.gridwidth = 4;
		gbc_oddzial.insets = new Insets(0, 0, 5, 5);
		gbc_oddzial.fill = GridBagConstraints.HORIZONTAL;
		gbc_oddzial.gridx = 2;
		gbc_oddzial.gridy = 4;
		panel.add(oddzial, gbc_oddzial);
		chckbxOddzia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxOddzia.isSelected()) {
					oddzial.setEnabled(true);
					oddzialB = true;
				} else {
					oddzial.setEnabled(false);
					oddzialB = false;
				}
				
			}
		});
		
		chckbxPrzedziaPensji = new JCheckBox("Przedział pensji");
		GridBagConstraints gbc_chckbxPrzedziaPensji = new GridBagConstraints();
		gbc_chckbxPrzedziaPensji.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPrzedziaPensji.gridx = 1;
		gbc_chckbxPrzedziaPensji.gridy = 5;
		panel.add(chckbxPrzedziaPensji, gbc_chckbxPrzedziaPensji);
		
		pensjaOd = new JTextField();
		pensjaOd.setEnabled(false);
		GridBagConstraints gbc_pensjaOd = new GridBagConstraints();
		gbc_pensjaOd.insets = new Insets(0, 0, 5, 5);
		gbc_pensjaOd.fill = GridBagConstraints.HORIZONTAL;
		gbc_pensjaOd.gridx = 2;
		gbc_pensjaOd.gridy = 5;
		panel.add(pensjaOd, gbc_pensjaOd);
		pensjaOd.setColumns(10);
		
		JLabel lblDo = new JLabel("do");
		GridBagConstraints gbc_lblDo = new GridBagConstraints();
		gbc_lblDo.insets = new Insets(0, 0, 5, 5);
		gbc_lblDo.anchor = GridBagConstraints.EAST;
		gbc_lblDo.gridx = 3;
		gbc_lblDo.gridy = 5;
		panel.add(lblDo, gbc_lblDo);
		
		pensjaDo = new JTextField();
		pensjaDo.setEnabled(false);
		GridBagConstraints gbc_pensjaDo = new GridBagConstraints();
		gbc_pensjaDo.gridwidth = 2;
		gbc_pensjaDo.insets = new Insets(0, 0, 5, 5);
		gbc_pensjaDo.fill = GridBagConstraints.HORIZONTAL;
		gbc_pensjaDo.gridx = 4;
		gbc_pensjaDo.gridy = 5;
		panel.add(pensjaDo, gbc_pensjaDo);
		pensjaDo.setColumns(10);
		chckbxPrzedziaPensji.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxPrzedziaPensji.isSelected()) {
					pensjaOd.setEnabled(true);
					pensjaDo.setEnabled(true);
					pensjaB = true;
				} else {
					pensjaOd.setEnabled(false);
					pensjaDo.setEnabled(false);
					pensjaB = false;
				}
				
			}
		});
		
		chckbxNrTelefonu = new JCheckBox("Nr telefonu");
		GridBagConstraints gbc_chckbxNrTelefonu = new GridBagConstraints();
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
		
		chckbxDataZatrudnienia = new JCheckBox("Data zatrudnienia");
		GridBagConstraints gbc_chckbxDataZatrudnienia = new GridBagConstraints();
		gbc_chckbxDataZatrudnienia.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDataZatrudnienia.gridx = 1;
		gbc_chckbxDataZatrudnienia.gridy = 7;
		panel.add(chckbxDataZatrudnienia, gbc_chckbxDataZatrudnienia);
		
		dataZatr = new JTextField();
		dataZatr.setEnabled(false);
		GridBagConstraints gbc_dataZatr = new GridBagConstraints();
		gbc_dataZatr.insets = new Insets(0, 0, 5, 5);
		gbc_dataZatr.gridwidth = 3;
		gbc_dataZatr.fill = GridBagConstraints.HORIZONTAL;
		gbc_dataZatr.gridx = 2;
		gbc_dataZatr.gridy = 7;
		panel.add(dataZatr, gbc_dataZatr);
		dataZatr.setColumns(10);
		chckbxDataZatrudnienia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxDataZatrudnienia.isSelected()) {
					dataZatr.setEnabled(true);
					dataZatrB = true;
				} else {
					dataZatr.setEnabled(false);
					dataZatrB = false;
				}				
			}
		});
		
		JButton btnWyszukaj = new JButton("Wyszukaj");
		GridBagConstraints gbc_btnWyszukaj = new GridBagConstraints();
		gbc_btnWyszukaj.insets = new Insets(0, 0, 5, 5);
		gbc_btnWyszukaj.gridx = 1;
		gbc_btnWyszukaj.gridy = 8;
		panel.add(btnWyszukaj, gbc_btnWyszukaj);
		btnWyszukaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				generateQuery();
				ResultSet rs = null;
				rs = ssh.findUsers(query);
				try {
					while (rs.next()) {
						idPracownika.add(rs.getLong(1));
						imieL.add(rs.getString(2));
						dimie.add(rs.getString(3));
						nazwiska.add(rs.getString(4));
						oddzialy.add(rs.getString(5));
						stanowiska.add(rs.getString(6));
						dataZatrudnienia.add(rs.getString(7).substring(0,10));
						pensja.add(rs.getFloat(8));
					}
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				panell.removeAllRows();
				for (int i = 0; i < imieL.size(); i++)
					panell.getModel().addRow(new Object[] { idPracownika.get(i), imieL.get(i),
							dimie.get(i), nazwiska.get(i), oddzialy.get(i),
							stanowiska.get(i), dataZatrudnienia.get(i), pensja.get(i) });
				
				query.clear();
				idPracownika.clear();
				imieL.clear();
				dimie.clear();
				nazwiska.clear();
				oddzialy.clear();
				stanowiska.clear();
				dataZatrudnienia.clear();
				pensja.clear();
				dispose();
			}
		});
		
		JButton btnAnuluj = new JButton("Anuluj");
		GridBagConstraints gbc_btnAnuluj = new GridBagConstraints();
		gbc_btnAnuluj.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnuluj.gridx = 4;
		gbc_btnAnuluj.gridy = 8;
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
		gbc_lblNewLabel.gridy = 9;
		panel.add(lblNewLabel, gbc_lblNewLabel);
	}

	private void generateQuery() {
		imieQ = "p.imie = '"+imie.getText()+"'";
		nazwiskoQ = "p.nazwisko = '"+nazwisko.getText()+"'";
		oddzialQ = (String) oddzial.getSelectedItem();
		pensjaQ = "z.pensja BETWEEN "+pensjaOd.getText()+" AND "+pensjaDo.getText();
		nrTelQ = "p.nrTelefonu = '"+nrTel.getText()+"'";
		dataZatrQ = "z.zatrudnionyOd = '"+dataZatr.getText()+"'";
		
		if (imieB)
			query.add(imieQ);
		if (nazwiskoB)
			query.add(nazwiskoQ);
		if (oddzialB)
			query.add(oddzialQ);
		if (pensjaB)
			query.add(pensjaQ);
		if (nrTelB)
			query.add(nrTelQ);
		if (dataZatrB)
			query.add(dataZatrQ);
		
		for (String single : query)
			System.out.println(single);
	}
}
