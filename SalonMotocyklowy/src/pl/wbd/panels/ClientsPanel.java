package pl.wbd.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.CSS;

import pl.wbd.database.connection.DBConnection;
import pl.wbd.managers.ClientsStatementHandler;
import pl.wbd.ui.ButtonColumn;
import pl.wbd.ui.frames.ClientAdder;
import pl.wbd.ui.frames.ClientCardd;
import pl.wbd.ui.frames.SelectClient;
import pl.wbd.user.UserLevel;

@SuppressWarnings("serial")
public class ClientsPanel extends JPanel {

	private ArrayList<String> id;
	private ArrayList<String> imie;
	private ArrayList<String> dimie;
	private ArrayList<String> nazwiska;
	private ArrayList<String> miasta;
	private ArrayList<String> ulice;
	private ArrayList<String> nrDomu;
	private ArrayList<Integer> nrMieszkania;
	private ArrayList<String> nrTelefonu;
	private ArrayList<String> dataA1;
	private ArrayList<String> dataA2;
	private ArrayList<String> dataA;
	private JLabel errorLabel;
	private String orderType = "k.idKlienta";
	private boolean showBought = false;
	private DBConnection con;
	private Vector<String> miejscowosci;
	private String showByMiejscowosc = "wszystkie";
	private final String[] columnNames = { "Imię", "Drugię imię", "Nazwisko",
			"Oddział", "Stanowisko", "Karta pracownika" };
	Object[][] data = { { "", "", "", "", "", "" },

	};

	private ClientsStatementHandler cth;

	JComboBox<String> comboBox;

	private enum TableSort {
		id, nazwiska, miejscowości
	};

	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public ClientsPanel(final DBConnection con) {
		this.con = con;
		cth = new ClientsStatementHandler(con.getConnection());
		imie = new ArrayList<String>();
		dimie = new ArrayList<String>();
		nazwiska = new ArrayList<String>();
		miasta = new ArrayList<String>();
		ulice = new ArrayList<String>();
		nrDomu = new ArrayList<String>();
		nrMieszkania = new ArrayList<Integer>();
		nrTelefonu = new ArrayList<String>();
		dataA1 = new ArrayList<String>();
		dataA2 = new ArrayList<String>();
		dataA = new ArrayList<String>();
		id = new ArrayList<String>();

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
				"Miejscowo\u015B\u0107", "Adres", "Prawo jazdy",
				"Numer telefonu", "Karta klienta" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, true };

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
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setMinWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(135);
		table.getColumnModel().getColumn(5).setMinWidth(135);
		table.getColumnModel().getColumn(6).setMinWidth(55);
		table.getColumnModel().getColumn(7).setPreferredWidth(111);
		table.getColumnModel().getColumn(7).setMinWidth(111);
		table.getColumnModel().getColumn(7).setMaxWidth(111);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setMinWidth(80);
		table.getColumnModel().getColumn(8).setMaxWidth(90);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 25;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		JLabel lblSzukajWedugStanowisk = new JLabel(
				"Szukaj według miejscowości:");
		GridBagConstraints gbc_lblSzukajWedugStanowisk = new GridBagConstraints();
		gbc_lblSzukajWedugStanowisk.gridwidth = 5;
		gbc_lblSzukajWedugStanowisk.insets = new Insets(0, 0, 5, 5);
		gbc_lblSzukajWedugStanowisk.gridx = 7;
		gbc_lblSzukajWedugStanowisk.gridy = 4;
		add(lblSzukajWedugStanowisk, gbc_lblSzukajWedugStanowisk);

		comboBox = new JComboBox<String>();
		miejscowosci = new Vector<String>();
		try {
			miejscowosci = cth.getMiejscowosci();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		miejscowosci.add(0, "wszystkie");
		comboBox.setModel(new DefaultComboBoxModel<String>(miejscowosci));
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
				showByMiejscowosc = (String) comboBox.getSelectedItem();
				removeAllRows();
				loadAllData(orderType, showByMiejscowosc);
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

		comboBox_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TableSort sorttype = (TableSort) comboBox_1.getSelectedItem();
				removeAllRows();

				switch (sorttype) {
				case nazwiska:
					orderType = "k.nazwisko, k.idKlienta";
					break;
				case id:
					orderType = "k.idKlienta";
					break;
				case miejscowości:
					orderType = "k.miasto, k.idKlienta";
					break;

				}
				loadAllData(orderType, showByMiejscowosc);
			}
		});

		JButton btnNowyPracownik = new JButton("Nowy klient");
		GridBagConstraints gbc_btnNowyPracownik = new GridBagConstraints();
		gbc_btnNowyPracownik.gridwidth = 6;
		gbc_btnNowyPracownik.insets = new Insets(0, 0, 5, 5);
		gbc_btnNowyPracownik.gridx = 11;
		gbc_btnNowyPracownik.gridy = 6;
		add(btnNowyPracownik, gbc_btnNowyPracownik);
		final ClientsPanel panel = this;
		btnNowyPracownik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ClientAdder(cth, panel);

			}
		});

		JButton btnSzukajPracownika = new JButton("Szukaj klienta");
		GridBagConstraints gbc_btnSzukajPracownika = new GridBagConstraints();
		gbc_btnSzukajPracownika.gridwidth = 7;
		gbc_btnSzukajPracownika.insets = new Insets(0, 0, 5, 5);
		gbc_btnSzukajPracownika.gridx = 10;
		gbc_btnSzukajPracownika.gridy = 7;
		add(btnSzukajPracownika, gbc_btnSzukajPracownika);
		btnSzukajPracownika.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SelectClient(cth, panel);
			}
		});
		
	
		JButton btnUsuZaznaczonegoPracownika = new JButton(
				"Usuń zaznaczonego klienta");
		GridBagConstraints gbc_btnUsuZaznaczonegoPracownika = new GridBagConstraints();
		gbc_btnUsuZaznaczonegoPracownika.gridwidth = 13;
		gbc_btnUsuZaznaczonegoPracownika.insets = new Insets(0, 0, 5, 5);
		gbc_btnUsuZaznaczonegoPracownika.gridx = 7;
		gbc_btnUsuZaznaczonegoPracownika.gridy = 8;
		add(btnUsuZaznaczonegoPracownika, gbc_btnUsuZaznaczonegoPracownika);

		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 16;
		gbc_errorLabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorLabel.gridx = 4;
		gbc_errorLabel.gridy = 9;
		add(errorLabel, gbc_errorLabel);

		btnUsuZaznaczonegoPracownika.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table.getSelectedRow();
				String idKlienta = getModel().getValueAt(selectedRow, 0)
						.toString();
				if (selectedRow != -1)
					try {
						cth.deleteClient(idKlienta);
					} catch (SQLException e) {
						if (e.getErrorCode() == 2292)
							errorLabel
									.setText("Nie można usunąć klienta posiadającego podrzędne zależności");
						try {
							con.getConnection().rollback();
						} catch (NullPointerException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						e.printStackTrace();
					}
				else
					System.out.println("Nie zaznaczono użytkownika");
				removeAllRows();
				loadAllData(orderType, showByMiejscowosc);
			}
		});
		
		if (!con.getUserGrants().equals(UserLevel.ADMIN) && !con.getUserGrants().equals(UserLevel.DYREKTOR))
				btnUsuZaznaczonegoPracownika.setVisible(false);
		Action openCard = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        String idKlienta = (String) model.getValueAt(modelRow, 0);
		        System.out.println("NEW DEPART "+idKlienta);
		        new ClientCardd(idKlienta, cth, panel);
		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, openCard, 8);
		loadAllData(orderType, showByMiejscowosc);
	}

	public void loadAllData(String orderTable, String showByMiejscowosc) {
		Statement stmt = null;
		ResultSet rs = null;
		String query;
		if (showByMiejscowosc == "wszystkie")
			query = "select k.idKlienta, k.imie, k.drugieImie, k.nazwisko, k.miasto, k.ulica, k.nrDomu, k.nrMieszkania, k.nrTelefonu, p.dataWydaniaA1, p.dataWydaniaA2, p.dataWydaniaA from klienci k, prawajazdy p  where k.idKlienta = p.idKlienta ORDER BY "
					+ orderType + " ASC";
		else
			query = "select k.idKlienta, k.imie, k.drugieImie, k.nazwisko, k.miasto, k.ulica, k.nrDomu, k.nrMieszkania, k.nrTelefonu, p.dataWydaniaA1, p.dataWydaniaA2, p.dataWydaniaA from klienci k, prawajazdy p  where k.idKlienta = p.idKlienta  AND k.miasto = '"
					+ showByMiejscowosc + "' ORDER BY " + orderType + " ASC";
		
		//String checkPrawa = "select idKlienta from ";
		try {
			stmt = con.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id.add(rs.getString(1));
				imie.add(rs.getString(2));
				dimie.add(rs.getString(3));
				nazwiska.add(rs.getString(4));
				miasta.add(rs.getString(5));
				ulice.add(rs.getString(6));
				nrDomu.add(rs.getString(7));
				nrMieszkania.add(rs.getInt(8));
				nrTelefonu.add(rs.getString(9));
				dataA1.add(rs.getString(10));
				dataA2.add(rs.getString(11));
				dataA.add(rs.getString(12));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < imie.size(); i++) {
			String prawojazdy = "";
			if (dataA1.get(i) != null)
				prawojazdy = prawojazdy + " A1";
			if (dataA2.get(i) != null)
				prawojazdy = prawojazdy + " A2";
			if (dataA.get(i) != null)
				prawojazdy = prawojazdy + " A";

			getModel().addRow(
					new Object[] {
							id.get(i),
							imie.get(i),
							dimie.get(i),
							nazwiska.get(i),
							miasta.get(i),
							ulice.get(i) + ", " + nrDomu.get(i) + "/"
									+ nrMieszkania.get(i), prawojazdy,
							nrTelefonu.get(i), "KARTA" });
		}
		id.clear();
		imie.clear();
		dimie.clear();
		nazwiska.clear();
		miasta.clear();
		ulice.clear();
		nrDomu.clear();
		nrMieszkania.clear();
		nrTelefonu.clear();
		dataA1.clear();
		dataA2.clear();
		dataA.clear();
	}

	public void removeAllRows() {
		int rowNumber = getModel().getRowCount();
		for (int i = 0; i < rowNumber; i++) {
			getModel().removeRow(0);
		}
	}
	
	public void deleteRow() {
		miejscowosci.clear();
		try {
			miejscowosci = cth.getMiejscowosci();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		miejscowosci.add(0, "wszystkie");
		comboBox.setModel(new DefaultComboBoxModel<String>(miejscowosci));
		removeAllRows();
		loadAllData(orderType, showByMiejscowosc);
	}

	public String getOrderType() {
		return orderType;
	}

	public boolean isShowBought() {
		return showBought;
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

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public String getShowByMiejscowosc() {
		return showByMiejscowosc;
	}

}
