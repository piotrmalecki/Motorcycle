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
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import pl.wbd.database.connection.DBConnection;
import pl.wbd.managers.DepartStatementHandler;
import pl.wbd.ui.ButtonColumn;
import pl.wbd.ui.frames.DepartAdder;
import pl.wbd.ui.frames.DepartCard;
import pl.wbd.user.UserLevel;

@SuppressWarnings("serial")
public class DepartPanel extends JPanel {

	private ArrayList<String> kod;
	private ArrayList<String> miasta;
	private ArrayList<String> ulice;
	private ArrayList<String> budynki;
	private ArrayList<String> lokale;
	private ArrayList<Long> idOddzialu;
	private JLabel errorLabel;
	private JComboBox<String> comboBox;
	private DBConnection con;
	private String showMiasto = "wszystkie";

	private final String[] columnNames = { "Id", "Kod pocztowy", "Miasto",
			"Ulica", "Numer Budynku", "Numer Lokalu", "Karta oddz." };
	Object[][] data = { { "", "", "", "", "", "" },

	};

	private DepartStatementHandler dth;
	private Vector<String> miastaV;

	private JTable table;
	private DefaultTableModel model;
	private DepartPanel panel;
	/**
	 * Create the panel.
	 */
	public DepartPanel(DBConnection con) {
		this.con = con;
		dth = new DepartStatementHandler(con.getConnection());
		kod = new ArrayList<String>();
		miasta = new ArrayList<String>();
		ulice = new ArrayList<String>();
		budynki = new ArrayList<String>();
		lokale = new ArrayList<String>();
		idOddzialu = new ArrayList<Long>();
		setSize(MainPanel.panelSize);
		setPreferredSize(MainPanel.panelSize);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 103, 0, 0, 51, 47, 55, 38, 21,
				87, 0, 33, 16, 26, 0, 30, 8, 30, 30, 22, 29, 179, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 19, 442, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id",
				"Kod pocztowy", "Miasto", "Ulica", "Numer Budynku",
				"Numer Lokalu", "Karta oddz." }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, true};

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
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setMinWidth(95);
		table.getColumnModel().getColumn(1).setMaxWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setMinWidth(105);
		table.getColumnModel().getColumn(2).setMaxWidth(3000);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setMinWidth(110);
		table.getColumnModel().getColumn(3).setMaxWidth(3000);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setMinWidth(110);
		table.getColumnModel().getColumn(4).setMaxWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setMinWidth(90);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setMinWidth(80);
		table.getColumnModel().getColumn(6).setMaxWidth(90);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 23;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		Vector<String> miasta = new Vector<String>();
		miasta = dth.getMiasta();
		miasta.add(0, "wszystkie");
		table.setRowSelectionAllowed(true);
		panel = this;
		miastaV = new Vector<String>();
		miastaV = dth.getMiasta();
		miastaV.add(0, "wszystkie");

		JLabel lblSortujWedugMiast = new JLabel("Pokaż według miasta:");
		lblSortujWedugMiast.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblSortujWedugMiast = new GridBagConstraints();
		gbc_lblSortujWedugMiast.gridwidth = 2;
		gbc_lblSortujWedugMiast.insets = new Insets(0, 0, 5, 5);
		gbc_lblSortujWedugMiast.gridx = 0;
		gbc_lblSortujWedugMiast.gridy = 3;
		add(lblSortujWedugMiast, gbc_lblSortujWedugMiast);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(miastaV));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		add(comboBox, gbc_comboBox);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showMiasto = (String) comboBox.getSelectedItem();		
				removeAllRows();
				loadAllData();
			}
		});

		JButton btnNowyOddzial = new JButton("Nowy oddział");
		GridBagConstraints gbc_btnNowyOddzial = new GridBagConstraints();
		gbc_btnNowyOddzial.gridwidth = 7;
		gbc_btnNowyOddzial.insets = new Insets(0, 0, 5, 5);
		gbc_btnNowyOddzial.gridx = 8;
		gbc_btnNowyOddzial.gridy = 5;
		add(btnNowyOddzial, gbc_btnNowyOddzial);
		btnNowyOddzial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				errorLabel.setText("");
				new DepartAdder(dth, panel);
				
			}
		});

		JButton btnNewButton = new JButton("Usuń oddział");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 7;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 8;
		gbc_btnNewButton.gridy = 7;
		add(btnNewButton, gbc_btnNewButton);
		
		errorLabel = new JLabel("");
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		errorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridwidth = 11;
		gbc_errorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_errorLabel.gridx = 6;
		gbc_errorLabel.gridy = 9;
		add(errorLabel, gbc_errorLabel);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0){
				errorLabel.setText("");
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1)
					try {
						String idOddzialu = getModel().getValueAt(selectedRow, 0)
								.toString();
						dth.deleteDepart(idOddzialu);
					} catch (SQLException e) {
						if (e.getErrorCode() == 2292) {
							errorLabel.setText("Błąd! Nie można usunąć oddziału zawierającego elementy podrzędne!");
						}
						e.printStackTrace();
					}
				else
					errorLabel.setText("Nie zaznaczono oddziału do usunięcia!");
				deleteRow();
			}
		});

		if (!con.getUserGrants().equals(UserLevel.DYREKTOR) && !con.getUserGrants().equals(UserLevel.ADMIN)) {
			btnNewButton.setVisible(false);
			btnNowyOddzial.setVisible(false);
		}
		
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        Long id = (Long) model.getValueAt(modelRow, 0);
		        System.out.println("NEW DEPART");
		        new DepartCard(id, dth, panel);
		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 6);
		loadAllData();
	}

	public void loadAllData() {
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from oddzialy order by idOddzialu";
		PreparedStatement pstmt = null;
		
		if (showMiasto == "wszystkie") {
			query = "select * from oddzialy order by idOddzialu";
			try {
				stmt = con.getConnection().createStatement();
				rs = stmt.executeQuery(query);
			} catch (SQLException e) {
				errorLabel.setText("Nie można pobrać danych oddziałów");				
			}
		}
		else {
			query = "select * from oddzialy where miasto = ? order by idOddzialu";
			
			try {
				pstmt = con.getConnection().prepareStatement(query);
				pstmt.setString(1, showMiasto);
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				errorLabel.setText("Nie można pobrać danych oddziałów");
			}
		}
			
		try {
			
			while (rs.next()) {
				idOddzialu.add(rs.getLong(1));
				kod.add(rs.getString(2).substring(0, 2) + "-"
						+ rs.getString(2).substring(2, 5));
				miasta.add(rs.getString(3));
				ulice.add(rs.getString(4));
				budynki.add(rs.getString(5));
				lokale.add(rs.getString(6));
			}
			if (rs!= null)
				rs.close();
			if (stmt!= null)
				stmt.close();
			if (pstmt!= null)
				pstmt.close();
		} catch (SQLException e) {
			errorLabel.setText("Nie można załadować danych oddziałów");
		}
		for (int i = 0; i < idOddzialu.size(); i++)
			getModel().addRow(
					new Object[] { idOddzialu.get(i), kod.get(i),
							miasta.get(i), ulice.get(i), budynki.get(i),
							lokale.get(i), "KARTA" });
		idOddzialu.clear();
		miasta.clear();
		ulice.clear();
		kod.clear();
		budynki.clear();
		lokale.clear();
		
	}

	public void removeAllRows() {
		int rowNumber = getModel().getRowCount();
		for (int i = 0; i < rowNumber; i++) {
			getModel().removeRow(0);
		}
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	public void deleteRow() {
		miastaV.clear();
		miastaV = dth.getMiasta();
		miastaV.add(0, "wszystkie");
		comboBox.setModel(new DefaultComboBoxModel<String>(miastaV));
		removeAllRows();
		loadAllData();
	}
}
