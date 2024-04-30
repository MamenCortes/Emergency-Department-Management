package urgency.db.jdbc;


import urgency.db.pojos.*;

import java.sql.*;
import java.util.*;

import urgency.db.interfaces.TriageManager;

public class JDBCTriageManager implements TriageManager {
	
	private ConnectionManager conManager;
	private Connection connection;

	public JDBCTriageManager (ConnectionManager conManager) {
		this.conManager = conManager;
		this.connection = conManager.getConnection();
	}
	
	@Override
	public void deleteTriage(int id) { //FUNCIONA
	try {
		String template = "DELETE FROM Triages WHERE id = ?";
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
	public void addTriage(Triage triage) { //FUNCIONA
		try {
			String template = "INSERT INTO Triages (available) VALUES (?)"; 
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
	public List<Triage> getTriages() { //FUNCIONA
		List<Triage> triages = new ArrayList<Triage>();
		try {
			String sql = "SELECT * FROM Triages";
			Statement st;
			st = connection.createStatement(); //se puede hacer con prepared statement
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Triage t = new Triage (rs.getInt("id"), rs.getBoolean("available"));
				triages.add(t);
			}
			rs.close();
			st.close();
	    }catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		} 
		System.out.println(triages);
		return triages;
	}
	

	@Override
	public Patient getPatientInTriage(int id) {
		/*try {
			String sql = "SELECT * FROM 
		} */
		return null; 
	}

	@Override
	public Triage getTriage(int id) { //FUNCIONA
		try {
			String sql = "SELECT * FROM Triages WHERE id =" + id;
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Triage t = new Triage (rs.getInt("id"), rs.getBoolean("available"));
			System.out.println("the id is: "+t.getId()+", and its the availability is: "+t.getAvailable());
			rs.close();
			return t;
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void changeAvailability(boolean available, int id) { //FUNCIONA
		try{
			String sql = "UPDATE Triages SET available = ? WHERE id = ?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setBoolean(1, available);
			pstmt.setInt(2, id); //cambiado en la interfaz
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}


	public static void main (String [] args) {
		ConnectionManager conManager = new ConnectionManager();
		JDBCTriageManager conTriage = new JDBCTriageManager(conManager);
		Triage triage = new Triage (true);
		Triage triage1 = new Triage (false);
		Triage triage2 = new Triage (false);
		Triage triage3 = new Triage (true);
		//conTriage.addTriage(triage3);
		//conTriage.getTriage(3);
		//conTriage.getTriage(4);
		//conTriage.getTriages();
		//conTriage.deleteTriage(3);
		conTriage.changeAvailability(false, 4);
	}
}
