package urgency.db.jdbc;


import urgency.db.pojos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import urgency.db.interfaces.TriageManager;

public class JDBCTriageManager implements TriageManager {
	
	private ConnectionManager conManager;
	private Connection connection;

	public JDBCTriageManager (ConnectionManager conManager) {
		this.conManager = conManager;
		this.connection = conManager.getConnection();
	}
	
	@Override
	public void deleteTriage(int id) {
	try {
		String template = "DELETE Triage WHERE id = ?";
		PreparedStatement pstmt;
		pstmt = connection.prepareStatement(template);
		pstmt.setInt(1,id); //creates in java what we want to delete in sql
		pstmt.executeUpdate();
		pstmt.close();
		}catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Override
	public void addTriage(Triage triage) {
		try {
			String template = "INSERT INTO Triage (available) VALUES (?)"; 
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
			pstmt.setBoolean(1, triage.getAvailable());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Override
	public List<Triage> getTriages() {
		List<Triage> triages = new ArrayList<Triage>();
		try {
			String sql = "SELECT * FROM Triages";
			Statement st;
			st = connection.createStatement(); //se puede hacer con prepared statement
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Triage t = new Triage ();
				triages.add(t);
			}
			rs.close();
			st.close();
	    }catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		} 
		return triages;
	}
	

	@Override
	public Patient getPatientInTriage(int Triage_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Triage getTriage(int id) {
		try {
			String sql = "SELECT * FROM Triages WHERE id =" + id;
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Triage t = new Triage (rs.getInt("id"), rs.getBoolean("available"));
			return t;
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void changeAvailability(boolean available) {
		// TODO Auto-generated method stub
		
	}



	public static void main (String [] args) {
		ConnectionManager conManager = new ConnectionManager();
		JDBCTriageManager conTriage = new JDBCTriageManager(conManager);
		
	}
}
