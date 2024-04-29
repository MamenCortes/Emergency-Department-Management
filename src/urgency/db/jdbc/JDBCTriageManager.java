package urgency.db.jdbc;


import urgency.db.pojos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		/*try {
			
		}catch (SQLException e) {
			System.out.println("Error");
		}*/
		return null;
	}

	@Override
	public Patient getPatientInTriage(int Triage_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Triage getTriage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeAvailability(boolean available) {
		// TODO Auto-generated method stub
		
	}



}
