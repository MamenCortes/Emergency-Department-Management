package urgency.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import urgency.db.*;
import urgency.db.interfaces.PatientManager;
import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;
import urgency.db.pojos.PatientBox;

public class JDBCPatientManager implements PatientManager {
	
	private Connection connection;
	private ConnectionManager conMan;
	private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.connection = conMan.getConnection();
	}


	@Override
	public void setStatus(int id, String status) { //FUNCIONA
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
	public void addComments(int Patient_id, int Box_id, String comments) { //FUNCIONA
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
	public List<PatientBox> getPatientRecords(Patient patient) { //FUNCIONA
		List<PatientBox> boxesOfPatient = new ArrayList<PatientBox>();
		try {
			String template = "SELECT box_id, date, comments FROM PatientBox WHERE patient_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(template);
			pstmt.setInt(1, patient.getId());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer box_id = rs.getInt("box_id");
				System.out.println(box_id);
				Box box = conMan.getBoxManager().getBox(box_id); 
				String comments = rs.getString("comments"); 
				Timestamp date = rs.getTimestamp("date"); 
				/*java.util.Date sdfDate1 = dateTimeFormat.parse(rs.getString("date"));
				Timestamp date = new Timestamp(sdfDate1.getTime());*/
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
	public void addPatient(Patient patient) { //FUNCIONA
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
	public List<Patient> searchPatientsBySurname(String surname) { //FUNCIONA
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
				//Patient p = conMan.getPatientMan().getPatient(id);
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
	public Patient getPatient(int id) { //FUNCIONA
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
	public void updatePatient(Patient patient) { //Funciona
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


	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager(); 
		JDBCPatientManager patMan = new JDBCPatientManager(conMan); 
		LocalDate birthDate = LocalDate.EPOCH; 
		
		//Patient patient = new Patient ("Marta", "Garcia", "hospitalized", 4, "Woman", birthDate);
		//patMan.addPatient(patient);
		//patMan.searchPatientsBySurname("Garcia");
		//patMan.setStatus(10, "assisted");
		//patMan.getPatient(10);
		
		/*Patient patient1 = new Patient("Rodrigo", "Gamarra", "waiting", 1, "Man", birthDate); 
		System.out.println(patient1);
		patMan.addPatient(patient1);*/
		
		/*Patient patient1 = patMan.getPatient(1);
		System.out.println(patient1);*/
		
		/*Patient patient2 = new Patient("Paula", "Blanco", "assisted", 1, "Woman", birthDate); 
		patMan.addPatient(patient2);
		Patient patient3 = new Patient("Menganito", "Robledo", "emergency room", 2, "Man", birthDate); 
		patMan.addPatient(patient3);
		Patient patient4 = new Patient("Pepe", "de los parques", "waiting", 3, "Man", birthDate); 
		patMan.addPatient(patient4);*/
		
		/*
		Patient patient2 = new Patient("Luis", "Blanco", "waiting", 1, "Man", birthDate); 
		patMan.addPatient(patient2);
		
		List<Patient> patients = patMan.searchPatientsBySurname("Blanco"); 
		System.out.println(patients);*/
		
		/*Patient patient = patMan.getPatient(1);
		List<PatientBox> boxesOfPatient = patMan.getPatientRecords(patient); 
		
		Patient patient2 = patMan.getPatient(2);
		List<PatientBox> boxesOfPatient2 = patMan.getPatientRecords(patient2); 
		System.out.println(boxesOfPatient);
		System.out.println(boxesOfPatient2);*/
		
		patMan.addComments(1, 1, "First comment made");
		Patient patient = patMan.getPatient(1);
		List<PatientBox> boxesOfPatient = patMan.getPatientRecords(patient); 
		System.out.println(boxesOfPatient);
		
		conMan.closeConnection();
	}

}
