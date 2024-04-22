package urgency.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import urgency.db.*;
import urgency.db.interfaces.PatientManager;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;

public class JDBCPatientManager implements PatientManager {
	
	private Connection connection;
	private ConnectionManager conMan;
	
	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.connection = conMan.getConnection();
	}

	@Override
	public void admitPatient(int id, int urgency) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dischargePatient(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(int id, String status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addComments(int Patient_id, int Box_id, String comments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getPatientWhithRecords(int Patient_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPatient(Patient patient) {
		// TODO Auto-generated method stub
		try {
			String template = "INSERT INTO patients (name) VALUES (?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
			pstmt.setString(1, patient.getName());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Patient> searchPatientsBySurname(String surname) {
		// TODO Auto-generated method stub
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM patients WHERE surname LIKE ?";
			PreparedStatement search = connection.prepareStatement(sql);
			search.setString(1, "%" + surname + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("ID");
				String namePatient = rs.getString("name");
				String surnamePatient = rs.getString("surname");
				Float weight = rs.getFloat("weight");
				Float height = rs.getFloat("height");
				String status = rs.getString("status");
				Integer urgency = rs.getInt("urgency");
				String sex = rs.getString("sex");
				Integer age = rs.getInt("age");
				Patient p = conMan.getPatientMan().getPatient(id);
				Patient newPatient = new Patient(id, namePatient, surnamePatient, weight, height, status, urgency, sex, age);
				patients.add(newPatient);
			}
			return patients;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public Patient getPatient(int id) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM patients WHERE ID = " + id;
			Statement st;
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Patient p = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getFloat("weight"),  
					    rs.getFloat("height"), rs.getString("status"), rs.getInt("urgency"), rs.getString("sex"), rs.getInt("age"));
			return p;
		}catch(SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}



}
