package urgency.db.jdbc;

import urgency.db.pojos.*;
import java.sql.*;
import java.sql.Date;
import java.time.Instant;
import java.util.*;

import urgency.db.interfaces.TriageManager;

public class JDBCTriageManager implements TriageManager {

	private ConnectionManager conManager;
	private Connection connection;

	public JDBCTriageManager(ConnectionManager conManager) {
		this.conManager = conManager;
		this.connection = conManager.getConnection();
	}
	
	@Override
	public void createRandomTriages() {
		
		String sql = "SELECT COUNT(*) FROM Triages"; 
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			rs.next(); 
			int numTriages = rs.getInt(1); 
			rs.close();
			if(numTriages == 0) {
				addTriage(new Triage(false));
				addTriage(new Triage(false)); 
				addTriage(new Triage(false)); 
				System.out.println("Random Triages created");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void deleteTriage(int id) { 
		try {
			String template = "DELETE FROM Triages WHERE id = ?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
			pstmt.setInt(1, id); 
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Override
	public void addTriage(Triage triage) { 
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
	public List<Triage> getTriages() { 
		List<Triage> triages = new ArrayList<Triage>();
		try {
			String sql = "SELECT * FROM Triages";
			Statement st;
			st = connection.createStatement(); 
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Triage t = new Triage(rs.getInt("id"), rs.getBoolean("available"));
				triages.add(t);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		System.out.println(triages);
		return triages;
	}

	@Override
	public void assingPatientToTriage(int patient_id, int triage_id) { 
		try {
			String sql = "INSERT INTO PatientTriage (patient_id, triage_id, date) VALUES (?,?,?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, patient_id);
			pstmt.setInt(2, triage_id);
			pstmt.setTimestamp(3, Timestamp.from(Instant.now()));
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Override
	public Patient getPatientInTriage(int triage_id) { 
		Patient patient = null;
		try {
			String sql = "SELECT * "
					+ "	FROM Patients JOIN PatientTriage ON Patients.id = PatientTriage.patient_id "
					+ "	JOIN Triages ON Triages.id = PatientTriage.triage_id "
					+ "	WHERE Triages.id = ? AND status = 'assisted'"
					+ "	ORDER BY PatientTriage.date DESC";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, triage_id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next(); //We only want the last Patient assigned to triage
			Integer id1 = rs.getInt(1);
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Float weight = rs.getFloat("weight"); 
			Float height = rs.getFloat("height"); 
			String status = rs.getString("status");
			Integer urgency = rs.getInt("urgency");
			String sex = rs.getString("sex");
			Date birthDate = rs.getDate("birthdate");

			patient = new Patient(id1, name, surname, weight, height, status, urgency, sex, birthDate);
			System.out.print(patient);
			
			rs.close();
			pstmt.close();
			return patient;
		} catch (SQLException e) {
			System.out.println("No patient assigned to triage");
			return null; 
		}
	}

	@Override
	public Triage getTriage(int id) { 
		try {
			String sql = "SELECT * FROM Triages WHERE id =" + id;
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Triage t = new Triage(rs.getInt("id"), rs.getBoolean("available"));
			System.out.println("the id is: " + t.getId() + ", and its availability is: " + t.getAvailable());
			rs.close();
			return t;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void changeAvailability(boolean available, int id) { 
		try {
			String sql = "UPDATE Triages SET available = ? WHERE id = ?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setBoolean(1, available);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}

}
