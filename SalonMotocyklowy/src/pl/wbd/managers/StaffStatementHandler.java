package pl.wbd.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class StaffStatementHandler {

	private Connection con;

	public StaffStatementHandler(Connection con) {
		this.con = con;
	}

	public void addNewUser(String imie, String dimie, String nazwisko,
			String dataUr, String nrTelefonu, String stanowisko, float pensja,
			Long adrnr, String dataZatr, String nazwaUsera) throws SQLException {
		Statement stmt = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String username;
		String newUser = "insert into personel values (1,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)";
		if (nazwaUsera.isEmpty())
			username = imie.toLowerCase().substring(0, 1)
					+ nazwisko.toLowerCase();
		else
			username = nazwaUsera;

		String newDatabaseUser = "create user " + username + " identified by "
				+ nazwisko.toLowerCase() + imie.toLowerCase()
				+ nrTelefonu.substring(0, 2);
		System.out.println(newDatabaseUser);

		pstmt = con.prepareStatement(newUser);
		stmt = con.createStatement();
		con.setAutoCommit(false);
		pstmt.setString(1, imie);
		pstmt.setString(2, dimie);
		pstmt.setString(3, nazwisko);
		pstmt.setString(4, dataUr);
		pstmt.setString(5, nrTelefonu);
		pstmt.setString(6, username);

		pstmt.executeUpdate();
		stmt.executeUpdate(newDatabaseUser);
		con.commit();
		String hireUser = "insert into zatrudnienia values ('"
				+ getIDPracownika(username)
				+ "', ?, ?, ?,to_date(?,'yyyy-mm-dd'), '')";
		pstmt2 = con.prepareStatement(hireUser);
		pstmt2.setFloat(1, adrnr);
		pstmt2.setString(2, stanowisko);
		pstmt2.setFloat(3, pensja);
		pstmt2.setString(4, dataZatr);
		pstmt2.executeUpdate();
		con.commit();
		con.setAutoCommit(true);
		grantUser(username, stanowisko);

		System.out.println("Dodano nowego użytkownika");
		if (pstmt != null)
			pstmt.close();
		if (stmt != null)
			stmt.close();
	}

	public void fireUser(String id) throws SQLException {
		PreparedStatement pstmt = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String query = "UPDATE zatrudnienia SET zatrudnionyDo = to_date(?,'yyyy-mm-dd') where idPracownika = ?";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, dateFormat.format(new Date()));
		pstmt.setString(2, id);
		pstmt.executeUpdate();

		// String revoke = "REVOKE create session ";
	}

	public void deleteUser(String id) throws SQLException {
		PreparedStatement pstmt = null;
		String getnazwa = "select nazwaUzytkownika from personel where idPracownika = ?";

		pstmt = con.prepareStatement(getnazwa);
		pstmt.setString(1, id);
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		String nazwa = null;
		while (rs.next())
			nazwa = rs.getString(1);

		con.setAutoCommit(false);
		String databaseUser = "drop user " + nazwa;
		Statement stmt = con.createStatement();
		stmt.executeUpdate(databaseUser);
		con.commit();

		String usunZatrudnienia = "delete from zatrudnienia where idPracownika = ?";
		String usunPersonel = "delete from personel where idPracownika = ?";

		pstmt = con.prepareStatement(usunZatrudnienia);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
		pstmt = con.prepareStatement(usunPersonel);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
		con.commit();
		con.setAutoCommit(true);

		System.out.println("DUsunięto użytkownika");
		if (pstmt != null)
			pstmt.close();
	}

	private int getIDPracownika(String username) {
		Statement st = null;
		ResultSet rst = null;
		int id = 0;
		String getUserId = "select idPracownika from Personel where nazwaUzytkownika LIKE ('"
				+ username + "')";
		try {
			st = con.createStatement();
			rst = st.executeQuery(getUserId);
			while (rst.next())
				id = rst.getInt(1);
			if (rst != null)
				rst.close();
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	private void grantUser(String username, String stanowisko) {
		Statement st = null;
		String grant = "grant " + stanowisko + " to " + username;

		try {
			st = con.createStatement();
			st.executeUpdate(grant);
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector<String> getStanowiska() {
		Statement st = null;
		ResultSet rs = null;
		Vector<String> stan = new Vector<String>();
		String stanowiska = "select * from stanowiska order by funkcja DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(stanowiska);

			while (rs.next())
				stan.add(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stan;
	}

	public ResultSet findUsers(ArrayList<String> queries) {
		int querySize = queries.size();
		String query = "select DISTINCT p.idPracownika, p.imie, p.drugieImie, p.nazwisko, o.miasto || ', ' || o.ulica || ' '|| o.nrBudynku, z.funkcja, z.zatrudnionyOd, z.pensja from personel p, zatrudnienia z, oddzialy o where ";
		query = query + queries.get(0);
		for (int i = 1; i < querySize; i++) {
			query = query + " AND " + queries.get(i);
		}
		query = query
				+ " AND p.idPracownika = z.idPracownika and z.idOddzialu = o.idOddzialu";

		System.out.println(query);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet getDepartments() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		String query = "select idOddzialu, miasto || ', ' || ulica || ' '|| nrBudynku as oddzial from oddzialy";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		return rs;
	}

	public int updateWorker(String id, String imie, String dimie,
			String nazwisko, String dataUr, String nrTel, String stanowisko, String pensja, String nazwaUzytkownika,
			String zatrudnionyOd, String zatrudnionyDo) throws SQLException {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		String updateWorker = "update personel set imie = ?, drugieImie = ?, nazwisko = ?, dataUrodzenia = ?, nazwaUzytkownika = ?, nrTelefonu = ? where idPracownika = ?";
		String updateWorkerAgr = "update zatrudnienia set funkcja = ?, pensja = ?, zatrudnionyOd = ?, zatrudnionyDo = ? where idPracownika = ?";

		pstmt = con.prepareStatement(updateWorker);
		pstmt.setString(1, imie);
		pstmt.setString(2, dimie);
		pstmt.setString(3, nazwisko);
		pstmt.setString(4, dataUr);
		pstmt.setString(5, nazwaUzytkownika);
		pstmt.setString(6, nrTel);
		pstmt.setString(7, id);
		pstmt.executeUpdate();
		System.out.println(pensja);
		pstmt2 = con.prepareStatement(updateWorkerAgr);
		pstmt2.setString(1, stanowisko);
		pstmt2.setString(2, pensja);
		pstmt2.setString(3, zatrudnionyOd);
		pstmt2.setString(4, zatrudnionyDo);
		pstmt2.setString(5, id);
		return pstmt2.executeUpdate();
	}

	public ResultSet getSingleWorker(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("ID: " + id);
		String query = "select * from personel p, zatrudnienia z where p.idPracownika = z.idPracownika AND p.idPracownika = ?";

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();

		return rs;
	}

	public ResultSet getDepartmentById(int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("ID: " + id);
		String query = "select idOddzialu, miasto || ', ' || ulica || ' '|| nrBudynku as oddzial from oddzialy where idOddzialu = ?";

		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();

		return rs;

	}

	public ResultSet getSingleClientLicense(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from prawajazdy where idKlienta = ?";

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();

		return rs;
	}

	public int getWorkerSold(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) from DostEgzemplarza where idOddzialu = ?";
		pstmt = con.prepareStatement(query);
		int w = 0;
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while (rs.next())
			w = rs.getInt(1);
		return w;
	}

	public int getWorkerTryouts(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) from sprzedane s, zamowienia z where s.idZamowienia = z.idZamowienia AND z.idKlienta = ?";
		pstmt = con.prepareStatement(query);
		int w = 0;
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while (rs.next())
			w = rs.getInt(1);
		return w;
	}

	public ResultSet getWorkerByUsername(String username) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from JazdyProbne where idKlienta = ?";
		pstmt = con.prepareStatement(query);
		int w = 0;
		pstmt.setString(1, username);
		rs = pstmt.executeQuery();
		return rs;
	}
}
