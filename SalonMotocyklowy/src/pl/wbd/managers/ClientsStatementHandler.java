package pl.wbd.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class ClientsStatementHandler {

	private Connection con;

	public ClientsStatementHandler(Connection con) {
		this.con = con;
	}

	public void addNewClient(String imie, String dimie, String nazwisko,
			String dataUr, String kodPocztowy, String miasto, String ulica,
			String nrDomu, String string, String pesel, String nrTelefonu,
			String dataA1, String dataA2, String dataA) throws SQLException {
		PreparedStatement pstmt = null;
		PreparedStatement stmt = null;
		String query = "insert into klienci values (1,?,?,?,?,?,?,?,?,?,?,?)";
		con.setAutoCommit(false);

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, imie);
		pstmt.setString(2, dimie);
		pstmt.setString(3, nazwisko);
		pstmt.setString(4, dataUr);
		pstmt.setString(5,
				kodPocztowy.substring(0, 2) + kodPocztowy.substring(3, 6));
		pstmt.setString(6, miasto);
		pstmt.setString(7, ulica);
		pstmt.setString(8, nrDomu);
		pstmt.setString(9, string);
		pstmt.setString(10, pesel);
		pstmt.setString(11, nrTelefonu);
		pstmt.executeUpdate();
		con.commit();
		Statement sstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MAX(idKlienta) from Klienci";
		String max = null;
		sstmt = con.createStatement();
		rs = sstmt.executeQuery(sql);
		while (rs.next())
			max = rs.getString(1);

		String query2 = "insert into PrawaJazdy values (?,?,?,?)";
		stmt = con.prepareStatement(query2);
		stmt.setString(1, max);
		stmt.setString(2, dataA1);
		stmt.setString(3, dataA2);
		stmt.setString(4, dataA);
		stmt.executeUpdate();
		con.commit();
		con.setAutoCommit(true);

		System.out.println("Dodano nowego uklienta");
		if (pstmt != null)
			pstmt.close();
		if (stmt != null)
			stmt.close();
	}

	public void deleteClient(String id) throws SQLException {
		PreparedStatement pstmt = null;
		String usunPrawaJazdy = "delete from PrawaJazdy where idKlienta = ?";
		String usunKlienta = "delete from klienci where idKlienta = ?";

		pstmt = con.prepareStatement(usunPrawaJazdy);
		pstmt.setString(1, id);
		pstmt.execute();
		pstmt = con.prepareStatement(usunKlienta);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
		con.commit();
		con.setAutoCommit(true);

		System.out.println("Usunięto klienta");
		if (pstmt != null)
			pstmt.close();
	}

	public Vector<String> getMiejscowosci() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		Vector<String> m = new Vector<String>();
		String query = "select miasto from klienci";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		while (rs.next())
			m.add(rs.getString(1));

		return m;
	}

	public ResultSet findClients(ArrayList<String> queries) {
		int querySize = queries.size();
		String query = "select k.idKlienta, k.imie, k.drugieImie, k.nazwisko, k.miasto, k.ulica, k.nrDomu, k.nrMieszkania, k.nrTelefonu, p.dataWydaniaA1, p.dataWydaniaA2, p.dataWydaniaA from klienci k, prawajazdy p  where ";
		query = query + queries.get(0);
		for (int i = 1; i < querySize; i++) {
			query = query + " AND " + queries.get(i);
		}
		query = query + " AND k.idKlienta = p.idKlienta";

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

	public int updateClient(String id, String kod, String miasto, String ulica,
			String budynek, String lokal, String imie, String dimie,
			String nazwisko, String dataUr, String nrTel, String pesel,
			String a1, String a2, String a) throws SQLException {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		String updateClient = "update klienci set imie = ?, drugieImie = ?, nazwisko = ?, dataUrodzenia = ?, kodPocztowy = ?, miasto = ?, ulica = ?, nrDomu = ?, nrMieszkania = ?, pesel = ?, nrTelefonu = ? where idKlienta = ?";
		String updateClientLicence = "update prawajazdy set dataWydaniaA1 = ?, dataWydaniaA2 = ?, dataWydaniaA = ? where idKlienta = ?";

		pstmt = con.prepareStatement(updateClient);
		pstmt.setString(1, imie);
		pstmt.setString(2, dimie);
		pstmt.setString(3, nazwisko);
		pstmt.setString(4, dataUr);
		pstmt.setString(5, kod.substring(0, 2) + kod.substring(3, 6));
		pstmt.setString(6, miasto);
		pstmt.setString(7, ulica);
		pstmt.setString(8, budynek);
		pstmt.setString(9, lokal);
		pstmt.setString(10, pesel);
		pstmt.setString(11, nrTel);
		pstmt.setString(12, id);
		pstmt.executeUpdate();

		pstmt2 = con.prepareStatement(updateClientLicence);
		pstmt2.setString(1, a1);
		pstmt2.setString(2, a2);
		pstmt2.setString(3, a);
		pstmt2.setString(4, id);
		return pstmt2.executeUpdate();
	}

	public ResultSet getSingleClient(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("ID: "+id);
		String query = "select * from klienci where idKlienta = ?";

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id);
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

	public int getClientResigned(String id) throws SQLException {
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

	public int getClientBought(String id) throws SQLException {
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

	public int getClientTryouts(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) from JazdyProbne where idKlienta = ?";
		pstmt = con.prepareStatement(query);
		int w = 0;
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while (rs.next())
			w = rs.getInt(1);
		return w;
	}

}
