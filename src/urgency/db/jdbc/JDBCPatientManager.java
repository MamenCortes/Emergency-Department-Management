package urgency.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import urgency.db.interfaces.PatientManager;
import urgency.db.pojos.Box;
import urgency.db.pojos.Patient;
import urgency.db.pojos.PatientBox;

public class JDBCPatientManager implements PatientManager {
	
	private Connection connection;
	private ConnectionManager conMan;
	//private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.connection = conMan.getConnection();
	}

	@Override
	public void createRandomPatients() {
		
		String sql = "SELECT COUNT(*) FROM Patients"; 
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			rs.next(); 
			int numPatients = rs.getInt(1); 
			rs.close();
			if(numPatients == 0) {
				Patient patient1 = new Patient("Paula","Blanco", "waiting", 1, "Woman", LocalDate.of(2004,11,24)); 
				addPatient(patient1);
				Patient patient2 = new Patient("Luis","Blanco", "waiting", 1, "Man", LocalDate.of(2007,9,15)); 
				addPatient(patient2);
				Patient patient3 = new Patient("María","De Los Montes", "waiting", 1, "Woman", LocalDate.of(2000,1,11)); 
				addPatient(patient3);
				Patient patient4 = new Patient("Pepe","Federico", "waiting", 1, "Man", LocalDate.of(1990,4,10)); 
				addPatient(patient4);
				Patient patient5 = new Patient("Marta","Sanchez", "waiting", 1, "Woman", LocalDate.of(2015,10,13)); 
				addPatient(patient5);
				Patient patient6 = new Patient("Jimena","Amarillo", "waiting", 1, "Woman", LocalDate.of(1989,2,12)); 
				addPatient(patient6);
				System.out.println("Random patients created");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void setStatus(int id, String status) { 
		try {
			String sql = "UPDATE Patients SET status = ? WHERE id = ?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,status);
			pstmt.setInt(2,id);
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}

	
	@Override
	public void addComments(int Patient_id, int Box_id, String comments) { 
		try {
			String template = "UPDATE PatientBox SET comments = ? WHERE patient_id = ? AND box_id = ?"; 
			PreparedStatement pstmt = connection.prepareStatement(template);
			pstmt.setString(1, comments);
			pstmt.setInt(2, Patient_id);
			pstmt.setInt(3, Box_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error adding comments in a patient record");
			e.printStackTrace();
		} 
		
		
	}

	

	@Override
	public List<PatientBox> getPatientRecords(Patient patient) { 
		List<PatientBox> boxesOfPatient = new ArrayList<PatientBox>();
		try {
			String template = "SELECT box_id, date, comments FROM PatientBox WHERE patient_id = ? ORDER BY date DESC";
			PreparedStatement pstmt = connection.prepareStatement(template);
			pstmt.setInt(1, patient.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer box_id = rs.getInt("box_id");
				System.out.println(box_id);
				Box box = conMan.getBoxManager().getBox(box_id); 
				String comments = rs.getString("comments"); 
				Timestamp date = rs.getTimestamp("date"); 
				PatientBox patientBox = new PatientBox(patient, box, date, comments);
				boxesOfPatient.add(patientBox);
			}
			rs.close(); 
			pstmt.close(); 
		} catch (SQLException e) {
			System.out.println("Error looking for a patient");
			e.printStackTrace();
			return null; 
		}
		return boxesOfPatient; 

	}

	@Override
	public void addPatient(Patient patient) { 
		try {
			String template = "INSERT INTO Patients (name, surname, weight, height, status, urgency, sex, birthdate) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
			pstmt.setString(1, patient.getName());
			pstmt.setString(2, patient.getSurname());
			pstmt.setFloat(3, patient.getWeight());
			pstmt.setFloat(4, patient.getHeight());
			pstmt.setString(5, patient.getStatus());
			pstmt.setInt(6, patient.getUrgency());
			pstmt.setString(7, patient.getSex());
			pstmt.setDate(8, patient.getBirthDate());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}

	@Override
	public List<Patient> searchPatientsBySurname(String surname) { 
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM Patients WHERE surname LIKE ?";
			PreparedStatement search = connection.prepareStatement(sql);
			search.setString(1, "%" + surname + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String namePatient = rs.getString("name");
				String surnamePatient = rs.getString("surname");
				Float weight = rs.getFloat("weight");
				Float height = rs.getFloat("height");
				String status = rs.getString("status");
				Integer urgency = rs.getInt("urgency");
				String sex = rs.getString("sex");
				Date birthDate = rs.getDate("birthdate");
				Patient newPatient = new Patient(id, namePatient, surnamePatient, weight, height, status, urgency, sex, birthDate);
				patients.add(newPatient);
			}
			rs.close(); 
			search.close();
			return patients;
		} catch (SQLException e) {
			System.out.println("Error looking for a patient");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Patient getPatient(int id) { 
		try {
			String sql = "SELECT * FROM Patients WHERE id = " + id;
			Statement st;
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			
			Patient p = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getFloat("weight"),  
					    rs.getFloat("height"), rs.getString("status"), rs.getInt("urgency"), rs.getString("sex"), rs.getDate("birthdate"));
			rs.close();
			return p;
		}catch(SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePatient(Patient patient) {
		try {
			String template = "UPDATE Patients SET name = ?, surname = ?, weight = ?, height = ?, status = ?, "
					+ "urgency = ?, sex = ?, birthdate = ? "
					+ "WHERE id = ?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
			pstmt.setString(1, patient.getName());
			pstmt.setString(2, patient.getSurname());
			pstmt.setFloat(3, patient.getWeight());
			pstmt.setFloat(4, patient.getHeight());
			pstmt.setString(5, patient.getStatus());
			pstmt.setInt(6, patient.getUrgency());
			pstmt.setString(7, patient.getSex());
			pstmt.setDate(8, patient.getBirthDate());
			pstmt.setInt(9, patient.getId());			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error updating the patient");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Returns true if the patient already exists, false if not, and null if there has been an error. 
	 */
	@Override
	public Boolean checkIfPatientExists(String name, String surname) {
		String sql = "SELECT COUNT(*) FROM Patients WHERE name = ? AND surname = ?"; 
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, surname);
			ResultSet rs = pstm.executeQuery(); 
			rs.next(); 
			if(rs.getInt(1) == 0) {
				return false; 
			}else {
				return true; 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		
		
	}







}
