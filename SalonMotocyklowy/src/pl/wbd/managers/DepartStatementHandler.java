package pl.wbd.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DepartStatementHandler {

	private Connection con;

	public DepartStatementHandler(Connection con) {
		this.con = con;
	}

	public void createDepartment(String kod, String miasto, String ulica,
			String budynek, String lokal) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "insert into oddzialy (kodPocztowy, miasto, ulica, nrBudynku, nrLokalu) values (?,?,?,?,?)";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, kod);
		pstmt.setString(2, miasto);
		pstmt.setString(3, ulica);
		pstmt.setString(4, budynek);
		pstmt.setString(5, lokal);
		pstmt.executeUpdate();
	}
	
	public Vector<String> getMiasta() {
		Statement st = null;
		ResultSet rs = null;
		Vector<String> stan = new Vector<String>();
		String stanowiska = "select DISTINCT miasto from oddzialy";
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
	
	public void deleteDepart(String id) throws NumberFormatException, SQLException {
		PreparedStatement pstmt = null;
		String deleteDepart = "delete from Oddzialy where idOddzialu = ?";
		
		pstmt = con.prepareStatement(deleteDepart);
		pstmt.setLong(1,Long.parseLong(id));
		pstmt.executeUpdate();		
	}
	
	public int updateDepart(String id, String kod, String miasto, String ulica, String budynek, String lokal) throws SQLException {
		PreparedStatement pstmt = null;
		String updateDepart = "update oddzialy set kodPocztowy = ?, miasto = ?, ulica = ?, nrBudynku = ?, nrLokalu = ? where idOddzialu = ?";
		
		pstmt = con.prepareStatement(updateDepart);
		pstmt.setString(1, kod.substring(0, 2)+kod.substring(3, 6));
		pstmt.setString(2, miasto);
		pstmt.setString(3, ulica);
		pstmt.setString(4, budynek);
		pstmt.setString(5, lokal);
		pstmt.setString(6, id);
		return pstmt.executeUpdate();
	}
	
	public ResultSet getSingleDepart(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from oddzialy where idOddzialu = ?";
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		return rs;
	}
	
	public int getDepartWorkers(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) from zatrudnienia z where z.idOddzialu = ?";
		pstmt = con.prepareStatement(query);
		int w = 0;
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while (rs.next())
			w = rs.getInt(1);
		return w;			
	}
	
	public int getDepartMotors(String id) throws SQLException {
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
	
	public int getDepartSold(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) from sprzedane s, zamowienia z where s.idZamowienia = z.idZamowienia AND z.idOddzialu = ?";
		pstmt = con.prepareStatement(query);
		int w = 0;
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while (rs.next())
			w = rs.getInt(1);
		return w;			
	}
	
	public int getDepartTryouts(String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select count(*) from JazdyProbne where idOddzialu = ?";
		pstmt = con.prepareStatement(query);
		int w = 0;
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while (rs.next())
			w = rs.getInt(1);
		return w;			
	}
}
