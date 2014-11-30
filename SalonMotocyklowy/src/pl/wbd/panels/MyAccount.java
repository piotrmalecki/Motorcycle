package pl.wbd.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import pl.wbd.database.connection.DBConnection;

@SuppressWarnings("serial")
public class MyAccount extends JPanel {

	private JTextField currentUser;
	private JButton btnAccept;
	private JPasswordField newPass;
	private JPasswordField repeatNewPass;
	private JButton btnNewButton;
	private DBConnection con;
	private JPanel panel;
	private JSeparator separator;
	private JPanel panel_1;
	private JLabel lblStatystykiPracownika;
	private JLabel lblImiINazwisko;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblIloPrzeprowadzonychJazd;
	private JLabel lblIloSprzedanychMotocykli;
	private JLabel lblIloPrzeprowadzonychJazd_1;
	private JLabel lblBrak;
	private JLabel lblBrak_1;
	private JLabel lblBrak_2;
	private JLabel lblData;
	private JLabel lblBrak_4;
	private JLabel lblBrak_5;
	private JLabel lblStanowisko;

	/**
	 * Create the panel.
	 */
	public MyAccount(DBConnection con) {
		this.con = con;
		setLayout(new BorderLayout());
		setSize(MainPanel.panelSize);
		setPreferredSize(MainPanel.panelSize);
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		add(northPanel,BorderLayout.NORTH);
		
		JLabel userLab = new JLabel("Użytkownik:");
		userLab.setFont(new Font("Tahoma", Font.PLAIN, 12));
		northPanel.add(userLab);
		currentUser = new JTextField();
		currentUser.setHorizontalAlignment(SwingConstants.CENTER);
		currentUser.setEnabled(false);
		currentUser.setEditable(false);
		northPanel.add(currentUser);
		currentUser.setColumns(10);
		currentUser.setText(con.getCurrentUser());
		
		JLabel lblNewPass = new JLabel("Nowe hasło:");
		lblNewPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		northPanel.add(lblNewPass);
		
		newPass = new JPasswordField();
		newPass.setColumns(10);
		northPanel.add(newPass);
		
		JLabel lblRepeatNewPass = new JLabel("Powtórz nowe hasło:");
		lblRepeatNewPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		northPanel.add(lblRepeatNewPass);
		
		repeatNewPass = new JPasswordField();
		repeatNewPass.setColumns(10);
		northPanel.add(repeatNewPass);
		
		btnAccept = new JButton("Zatwierdź");
		btnAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (checkPasswords())
					try {
						changePassword();
						clearFields();
					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						if (e.getErrorCode() == 988)
							System.out.println("Niepoprawne hasło");
						e.printStackTrace();
					}
				
			}
		});
		northPanel.add(btnAccept);
		
		btnNewButton = new JButton("Czyść");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearFields();
			}
		});
		northPanel.add(btnNewButton);
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		separator = new JSeparator();
		panel.add(separator, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[36px][35px][36px][35px][][159px][226px][][][][][][][36px][][36px][35px][276px][36px]", "[40px][39px][40px][26px][][][4px][][][][][10px][][][39px][40px][40px][39px][40px][40px][39px][][40px][40px][39px][40px]"));
		
		lblStatystykiPracownika = new JLabel("STATYSTYKI PRACOWNIKA");
		lblStatystykiPracownika.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatystykiPracownika.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		panel_1.add(lblStatystykiPracownika, "cell 6 3 5 1,grow");
		
		lblImiINazwisko = new JLabel("Imię i nazwisko");
		lblImiINazwisko.setHorizontalAlignment(SwingConstants.CENTER);
		lblImiINazwisko.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblImiINazwisko, "cell 6 13,grow");
		
		lblData = new JLabel("Mateusz Filipowicz");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblData, "cell 7 13,grow");
		
		lblBrak_2 = new JLabel("2013-01-07");
		lblBrak_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblBrak_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblBrak_2, "cell 7 14,grow");
		
		lblStanowisko = new JLabel("Stanowisko");
		lblStanowisko.setHorizontalAlignment(SwingConstants.CENTER);
		lblStanowisko.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblStanowisko, "cell 7 15");
		lblStanowisko.setText(con.getUserGrants().toString());
		
		lblNewLabel_2 = new JLabel("Ilość sprzedanych motocykli:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel_2, "flowx,cell 6 16,grow");
		
		lblBrak_4 = new JLabel("brak");
		lblBrak_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblBrak_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblBrak_4, "cell 7 16,grow");
		
		
		
		lblBrak_5 = new JLabel("brak");
		lblBrak_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblBrak_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblBrak_5, "cell 7 17,grow");
		
		
		lblBrak = new JLabel("brak");
		lblBrak.setHorizontalAlignment(SwingConstants.LEFT);
		lblBrak.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblBrak, "cell 7 18,grow");
		
		
		
		lblBrak_1 = new JLabel("brak");
		lblBrak_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblBrak_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lblBrak_1, "cell 7 19,grow");
		
		
		lblNewLabel = new JLabel("Data zatrudnienia:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel, "cell 6 14,grow");
		
		lblNewLabel_1 = new JLabel("Stanowisko:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel_1, "cell 6 15,grow");
		
		lblIloPrzeprowadzonychJazd = new JLabel("Ilość przeprowadzonych jazd:");
		lblIloPrzeprowadzonychJazd.setHorizontalAlignment(SwingConstants.CENTER);
		lblIloPrzeprowadzonychJazd.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblIloPrzeprowadzonychJazd, "cell 6 17,grow");
		
		lblIloPrzeprowadzonychJazd_1 = new JLabel("Ilość przeprowadzonych jazd w obecnym miesiącu:");
		lblIloPrzeprowadzonychJazd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIloPrzeprowadzonychJazd_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblIloPrzeprowadzonychJazd_1, "cell 6 19,grow");
		
		lblIloSprzedanychMotocykli = new JLabel("Ilość sprzedanych motocykli w obecnym miesiącu:");
		lblIloSprzedanychMotocykli.setHorizontalAlignment(SwingConstants.CENTER);
		lblIloSprzedanychMotocykli.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblIloSprzedanychMotocykli, "flowx,cell 6 18,grow");
		setVisible(true);
	}
	
	public void clearFields() {
		newPass.setText(null);
		repeatNewPass.setText(null);
	}
	
	private boolean checkPasswords() {
		if (new String(newPass.getPassword()).equals(new String(repeatNewPass.getPassword())))
				return true;
		return false;
	}
	
	private void changePassword() throws NullPointerException, SQLException {
		Statement stmt = null;
		String query = "ALTER USER "+con.getCurrentUser()+ " IDENTIFIED BY "+new String(newPass.getPassword());
		stmt = con.getConnection().createStatement();
		stmt.executeUpdate(query);
	}
}


