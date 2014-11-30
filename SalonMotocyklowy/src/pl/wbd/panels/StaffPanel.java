package pl.wbd.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pl.wbd.database.connection.DBConnection;
import pl.wbd.managers.StaffStatementHandler;
import pl.wbd.ui.ButtonColumn;
import pl.wbd.ui.frames.SelectWorker;
import pl.wbd.ui.frames.WorkerAdder;
import pl.wbd.ui.frames.WorkerCard;

@SuppressWarnings("serial")
public class StaffPanel extends JPanel {

	private ArrayList<String> imie;
	private ArrayList<String> dimie;
	private ArrayList<String> nazwiska;
	private ArrayList<String> oddzialy;
	private ArrayList<String> stanowiska;
	private ArrayList<String> dataZatrudnienia;
	private ArrayList<Float> pensja;
	private ArrayList<Long> idPracownika;
	private String orderType = "p.nazwisko";
	private boolean showHired = false;
	private DBConnection con;
	private String showByStanowisko = "wszystkie";
	private final String[] columnNames = { "Imię", "Drugię imię", "Nazwisko",
			"Oddział", "Stanowisko", "Karta pracownika" };
	Object[][] data = { { "", "", "", "", "", "" },

	};

	private StaffStatementHandler sth;

	JComboBox<String> comboBox;

	private enum TableSort {
		nazwiska, oddzialu, stanowiska, datyzatrudnienia, pensji
	};

	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public StaffPanel(DBConnection con) {
		this.con = con;
		sth = new StaffStatementHandler(con.getConnection());
		imie = new ArrayList<String>();
		dimie = new ArrayList<String>();
		nazwiska = new ArrayList<String>();
		oddzialy = new ArrayList<String>();
		stanowiska = new ArrayList<String>();
		dataZatrudnienia = new ArrayList<String>();
		pensja = new ArrayList<Float>();
		idPracownika = new ArrayList<Long>();
		setSize(MainPanel.panelSize);
		setPreferredSize(MainPanel.panelSize);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 209, 34, 0, 0, 30, 21, 0, 33,
				16, 26, 0, 30, 8, 30, 30, 22, 30, 0, 30, 30, 0, 30, 24, 29, 179 };
		gridBagLayout.rowHeights = new int[] { 30, 19, 442, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id",
				"Imi\u0119", "Drugi\u0119 imi\u0119", "Nazwisko",
				"Oddzia\u0142", "Stanowisko", "Data zatrudnienia", "Pensja",
				"Karta prac." }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false,false,true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table = new JTable(data, columnNames);
		table.setBackground(SystemColor.menu);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(32);
		table.getColumnModel().getColumn(0).setMinWidth(32);
		table.getColumnModel().getColumn(0).setMaxWidth(32);
		table.getColumnModel().getColumn(1).setMinWidth(55);
		table.getColumnModel().getColumn(2).setMinWidth(55);
		table.getColumnModel().getColumn(3).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setMinWidth(75);
		table.getColumnModel().getColumn(4).setPreferredWidth(145);
		table.getColumnModel().getColumn(4).setMinWidth(125);
		table.getColumnModel().getColumn(5).setMinWidth(55);
		table.getColumnModel().getColumn(6).setPreferredWidth(111);
		table.getColumnModel().getColumn(6).setMinWidth(111);
		table.getColumnModel().getColumn(6).setMaxWidth(111);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		table.getColumnModel().getColumn(7).setMinWidth(80);
		table.getColumnModel().getColumn(7).setMaxWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setMinWidth(80);
		table.getColumnModel().getColumn(8).setMaxWidth(80);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 25;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		final JCheckBox chckbxPokaTylkoAktualnie = new JCheckBox(
				"Pokaż również zwolnionych");
		GridBagConstraints gbc_chckbxPokaTylkoAktualnie = new GridBagConstraints();
		gbc_chckbxPokaTylkoAktualnie.gridwidth = 5;
		gbc_chckbxPokaTylkoAktualnie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPokaTylkoAktualnie.gridx = 1;
		gbc_chckbxPokaTylkoAktualnie.gridy = 4;
		add(chckbxPokaTylkoAktualnie, gbc_chckbxPokaTylkoAktualnie);

		JLabel lblSzukajWedugStanowisk = new JLabel("Szukaj według stanowisk:");
		GridBagConstraints gbc_lblSzukajWedugStanowisk = new GridBagConstraints();
		gbc_lblSzukajWedugStanowisk.gridwidth = 5;
		gbc_lblSzukajWedugStanowisk.insets = new Insets(0, 0, 5, 5);
		gbc_lblSzukajWedugStanowisk.gridx = 7;
		gbc_lblSzukajWedugStanowisk.gridy = 4;
		add(lblSzukajWedugStanowisk, gbc_lblSzukajWedugStanowisk);

		comboBox = new JComboBox<String>();
		Vector<String> stanowiska = new Vector<String>();
		stanowiska = sth.getStanowiska();
		stanowiska.add(0, "wszystkie");
		comboBox.setModel(new DefaultComboBoxModel<String>(stanowiska));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 12;
		gbc_comboBox.gridy = 4;
		add(comboBox, gbc_comboBox);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showByStanowisko = (String) comboBox.getSelectedItem();
				removeAllRows();
				loadAllData(orderType, showHired, showByStanowisko);
			}
		});

		JLabel lblNewLabel = new JLabel("Sortuj rosnąco według:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 16;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);

		final JComboBox<TableSort> comboBox_1 = new JComboBox<TableSort>();
		comboBox_1.setModel(new DefaultComboBoxModel<TableSort>(TableSort
				.values()));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 3;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 21;
		gbc_comboBox_1.gridy = 4;
		add(comboBox_1, gbc_comboBox_1);
		table.setRowSelectionAllowed(true);

		chckbxPokaTylkoAktualnie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxPokaTylkoAktualnie.isSelected())
					showHired = true;
				else
					showHired = false;
				removeAllRows();
				loadAllData(orderType, showHired, showByStanowisko);

			}
		});

		comboBox_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TableSort sorttype = (TableSort) comboBox_1.getSelectedItem();
				removeAllRows();

				switch (sorttype) {
				case nazwiska:
					orderType = "p.nazwisko";
					break;
				case oddzialu:
					orderType = "o.miasto || ', ' || o.ulica || ' '|| o.nrBudynku ,p.nazwisko";
					break;
				case stanowiska:
					orderType = "z.funkcja, p.nazwisko";
					break;
				case datyzatrudnienia:
					orderType = "z.zatrudnionyOd, p.nazwisko";
					break;
				case pensji:
					orderType = "z.pensja, p.nazwisko";
					break;
				}
				loadAllData(orderType, showHired, showByStanowisko);
			}
		});
		final StaffPanel panel = this;
		
				JButton btnNowyPracownik = new JButton("Nowy pracownik");
				GridBagConstraints gbc_btnNowyPracownik = new GridBagConstraints();
				gbc_btnNowyPracownik.gridwidth = 6;
				gbc_btnNowyPracownik.insets = new Insets(0, 0, 5, 5);
				gbc_btnNowyPracownik.gridx = 11;
				gbc_btnNowyPracownik.gridy = 5;
				add(btnNowyPracownik, gbc_btnNowyPracownik);
				btnNowyPracownik.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new WorkerAdder(sth, panel);

					}
				});
		
				JButton btnSzukajPracownika = new JButton("Szukaj pracownika");
				GridBagConstraints gbc_btnSzukajPracownika = new GridBagConstraints();
				gbc_btnSzukajPracownika.gridwidth = 7;
				gbc_btnSzukajPracownika.insets = new Insets(0, 0, 5, 5);
				gbc_btnSzukajPracownika.gridx = 10;
				gbc_btnSzukajPracownika.gridy = 6;
				add(btnSzukajPracownika, gbc_btnSzukajPracownika);
				btnSzukajPracownika.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new SelectWorker(sth, panel);
					}
				});
		
		JButton btnZwolnijZaznaczonegoPracownika = new JButton("Zwolnij zaznaczonego pracownika");
		GridBagConstraints gbc_btnZwolnijZaznaczonegoPracownika = new GridBagConstraints();
		gbc_btnZwolnijZaznaczonegoPracownika.gridwidth = 13;
		gbc_btnZwolnijZaznaczonegoPracownika.insets = new Insets(0, 0, 5, 5);
		gbc_btnZwolnijZaznaczonegoPracownika.gridx = 7;
		gbc_btnZwolnijZaznaczonegoPracownika.gridy = 7;
		add(btnZwolnijZaznaczonegoPracownika, gbc_btnZwolnijZaznaczonegoPracownika);
		btnZwolnijZaznaczonegoPracownika.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table.getSelectedRow();
				String idPracownika = getModel().getValueAt(selectedRow, 0)
						.toString();
				if (selectedRow != -1)
					try {
						sth.fireUser(idPracownika);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				else
					System.out.println("Nie zaznaczono użytkownika");
				removeAllRows();
				loadAllData(orderType, showHired, showByStanowisko);
			}
		});
		
		JButton btnUsuZaznaczonegoPracownika = new JButton(
				"Usuń zaznaczonego pracownika");
		GridBagConstraints gbc_btnUsuZaznaczonegoPracownika = new GridBagConstraints();
		gbc_btnUsuZaznaczonegoPracownika.gridwidth = 13;
		gbc_btnUsuZaznaczonegoPracownika.insets = new Insets(0, 0, 5, 5);
		gbc_btnUsuZaznaczonegoPracownika.gridx = 7;
		gbc_btnUsuZaznaczonegoPracownika.gridy = 8;
		add(btnUsuZaznaczonegoPracownika, gbc_btnUsuZaznaczonegoPracownika);

		btnUsuZaznaczonegoPracownika.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table.getSelectedRow();
				String idPracownika = getModel().getValueAt(selectedRow, 0)
						.toString();
				if (selectedRow != -1)
					try {
						sth.deleteUser(idPracownika);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				else
					System.out.println("Nie zaznaczono użytkownika");
				removeAllRows();
				loadAllData(orderType, showHired, showByStanowisko);
			}
		});
		
		Action openCard = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        Long idPracownika = (Long) model.getValueAt(modelRow, 0);
		        System.out.println("NEW DEPART "+idPracownika);
		        new WorkerCard(idPracownika, sth, panel);
		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, openCard, 8);
		
		loadAllData(orderType, showHired, showByStanowisko);
	}

	public void loadAllData(String orderTable, Boolean showHired,
			String showByStanowisko) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select p.idPracownika, p.imie, p.drugieImie, p.nazwisko, o.miasto || ', ' || o.ulica || ' '|| o.nrBudynku, z.funkcja, z.zatrudnionyOd, z.pensja from personel p, zatrudnienia z, oddzialy o where p.idPracownika = z.idPracownika AND z.idOddzialu = o.idOddzialu AND z.zatrudnionyDo IS NULL ORDER BY "
				+ orderTable + " ASC";
		
		if (showHired)
			query = "select p.idPracownika, p.imie, p.drugieImie, p.nazwisko, o.miasto || ', ' || o.ulica || ' '|| o.nrBudynku, z.funkcja, z.zatrudnionyOd, z.pensja from personel p, zatrudnienia z, oddzialy o where p.idPracownika = z.idPracownika AND z.idOddzialu = o.idOddzialu ORDER BY "
					+ orderTable + " ASC";
		if (showByStanowisko != "wszystkie" && !showHired)
			query = "select p.idPracownika, p.imie, p.drugieImie, p.nazwisko, o.miasto || ', ' || o.ulica || ' '|| o.nrBudynku, z.funkcja, z.zatrudnionyOd, z.pensja from personel p, zatrudnienia z, oddzialy o where p.idPracownika = z.idPracownika AND z.idOddzialu = o.idOddzialu AND z.zatrudnionyDo IS NULL AND z.funkcja = '"
					+ showByStanowisko + "' ORDER BY " + orderTable + " ASC";
		else if (showByStanowisko != "wszystkie" && showHired)
			query = "select p.idPracownika, p.imie, p.drugieImie, p.nazwisko, o.miasto || ', ' || o.ulica || ' '|| o.nrBudynku, z.funkcja, z.zatrudnionyOd, z.pensja from personel p, zatrudnienia z, oddzialy o where p.idPracownika = z.idPracownika AND z.idOddzialu = o.idOddzialu AND z.funkcja = '"
					+ showByStanowisko
					+ "' ORDER BY "
					+ orderTable
					+ " ASC";
		try {
			stmt = con.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				idPracownika.add(rs.getLong(1));
				imie.add(rs.getString(2));
				dimie.add(rs.getString(3));
				nazwiska.add(rs.getString(4));
				oddzialy.add(rs.getString(5));
				stanowiska.add(rs.getString(6));
				dataZatrudnienia.add(rs.getString(7).substring(0,10));
				pensja.add(rs.getFloat(8));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < imie.size(); i++)
			getModel().addRow(new Object[] { idPracownika.get(i), imie.get(i),
					dimie.get(i), nazwiska.get(i), oddzialy.get(i),
					stanowiska.get(i), dataZatrudnienia.get(i), pensja.get(i), "KARTA" });
		idPracownika.clear();
		imie.clear();
		dimie.clear();
		nazwiska.clear();
		oddzialy.clear();
		stanowiska.clear();
		dataZatrudnienia.clear();
		pensja.clear();	
	}

	public void removeAllRows() {
		int rowNumber = getModel().getRowCount();
		for (int i = 0; i < rowNumber; i++) {
			getModel().removeRow(0);
		}
	}
	
	public void deleteRow() {
		removeAllRows();
		loadAllData(orderType, showHired, showByStanowisko);
	}

	public String getOrderType() {
		return orderType;
	}

	public boolean isShowHired() {
		return showHired;
	}

	public String getShowBy() {
		return showByStanowisko;
	}

	public ArrayList<String> getImie() {
		return imie;
	}

	public void setImie(ArrayList<String> imie) {
		this.imie = imie;
	}

	public ArrayList<String> getDimie() {
		return dimie;
	}

	public void setDimie(ArrayList<String> dimie) {
		this.dimie = dimie;
	}

	public ArrayList<String> getNazwiska() {
		return nazwiska;
	}

	public void setNazwiska(ArrayList<String> nazwiska) {
		this.nazwiska = nazwiska;
	}

	public ArrayList<String> getOddzialy() {
		return oddzialy;
	}

	public void setOddzialy(ArrayList<String> oddzialy) {
		this.oddzialy = oddzialy;
	}

	public ArrayList<String> getStanowiska() {
		return stanowiska;
	}

	public void setStanowiska(ArrayList<String> stanowiska) {
		this.stanowiska = stanowiska;
	}

	public ArrayList<String> getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	public void setDataZatrudnienia(ArrayList<String> dataZatrudnienia) {
		this.dataZatrudnienia = dataZatrudnienia;
	}

	public ArrayList<Float> getPensja() {
		return pensja;
	}

	public void setPensja(ArrayList<Float> pensja) {
		this.pensja = pensja;
	}

	public ArrayList<Long> getIdPracownika() {
		return idPracownika;
	}

	public void setIdPracownika(ArrayList<Long> idPracownika) {
		this.idPracownika = idPracownika;
	}

	public String getShowByStanowisko() {
		return showByStanowisko;
	}

	public void setShowByStanowisko(String showByStanowisko) {
		this.showByStanowisko = showByStanowisko;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	
}
