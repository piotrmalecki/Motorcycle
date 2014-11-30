package pl.wbd.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.wbd.panels.LoginPanel;
import pl.wbd.user.UserLevel;

public class DBConnection {

	protected Connection con;
	public boolean connectionStatus = false;
	protected String user;
	public UserLevel userGrants;
	private String DBUri;
	private String DBsid;
	private String DBUrl;

	public DBConnection(String user, char[] pass) {
		this.user = user;
		con = null;
		DBUrl = new DBUrl().getDBUrl();
		DBsid = new DBUrl().getSid();

		DBUri = "jdbc:oracle:thin:@" + DBUrl + ":" + DBsid;
		System.out.println("Retrieving connection...\nDatabase address: "
				+ DBUrl + "\nDatabase name: " + DBsid);
		connect(user, pass);
	}

	protected void connect(String user, char[] pass) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(DBUri, "system", "a");
			if (con != null) {
				// stmtHandler = new DBStatementHandler(con);
				System.out.println("Connection Established.\nLogged as: "
						+ user + "");
				userGrants = checkGrants();
				System.out.println(userGrants);
				connectionStatus = true;
			}
		} catch (ClassNotFoundException e) {
			System.err 
					.println("Driver not found.\nPlease install database driver first.\n"
							+ e + "\n");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1017) {
				LoginPanel.getErrorText().setText(
						"Nieprawidłowy użytkownik lub hasło!");
				System.err
						.println("Could not connect to database.\nPlease check your authentication\n"
								+ e + "\n");
			} else if (e.getErrorCode() == 20) {
				LoginPanel.getErrorText().setText(
						"Brak połączenia. Sprawdź ustawienia połączenia!");
				System.err
						.println("Could not connect to database.\nPlease check your connection configuration\n"
								+ e + "\n");
			} else {
				LoginPanel.getErrorText().setText(
						"Nieznany błąd. Skontaktuj się z administratorem!");
				System.err
						.println("Could not connect to database.\nPlease check your authentication and connection configuration\n"
								+ e + "\n");
			}
			con = null;
		}
	}

	public void DBCloseConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			System.err
					.println("Could not disconnect from database\nPlease check your connection.");
			e.printStackTrace();
		}
		if (con == null) {
			System.out.println("Disconnected");
			connectionStatus = false;
		}
	}

	public boolean getConnectionStatus() {
		return connectionStatus;
	}

	public Connection getConnection() throws NullPointerException {
		return con;
	}

	/**
	 * @return the userGrants
	 */
	public UserLevel getUserGrants() {
		return userGrants;
	}

	public String getCurrentUser() {
		return user;
	}

	public UserLevel checkGrants() {
		Statement grantAsk = null;
		ResultSet rs = null;
		String query = "select granted_role from user_role_privs";

		try {
			grantAsk = con.createStatement();
			rs = grantAsk.executeQuery(query);
			List<String> codes = new ArrayList<String>();
			while (rs.next()) {
				codes.add(rs.getString(1).toLowerCase());
			}

			if (codes.contains(UserLevel.ADMIN.toString().toLowerCase()))
				return UserLevel.ADMIN;
			else if (codes
					.contains(UserLevel.DYREKTOR.toString().toLowerCase()))
				return UserLevel.DYREKTOR;
			else if (codes.contains(UserLevel.KIEROWNIK.toString()
					.toLowerCase()))
				return UserLevel.KIEROWNIK;
			else if (codes.contains(UserLevel.SPRZEDAWCA.toString()
					.toLowerCase()))
				return UserLevel.SPRZEDAWCA;

		} catch (SQLException e) {
			System.err.println("Unable to get user privileges. \n" + e);
		}

		return UserLevel.NULL;
	}
}
