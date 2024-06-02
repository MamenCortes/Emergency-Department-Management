package urgency.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import urgency.db.pojos.*;

public class PatientLifeCycle {
	private Connection connection;
	private ConnectionManager conMan;
	
	public PatientLifeCycle(ConnectionManager conMan) {
		this.conMan = conMan;
		this.connection = conMan.getConnection();
	}
	
	
	//Assign a new patient with status = waiting to triage. 
	public void assignNewPatient2Triage(Triage triage) { //WORKS
		String sql = "SELECT id, name, surname FROM Patients WHERE status = 'waiting'"; 
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			Integer patientId = rs.getInt("id"); 
			rs.close();
			if(triage.getAvailable()) {
				//Assign the patient to triage
				conMan.getTriageManager().assingPatientToTriage(patientId, triage.getId());
				
				//change patient's status to assisted
				conMan.getPatientMan().setStatus(patientId, "assisted");
				System.out.println("Patient with ID "+patientId+" assigned to Triage "+triage.getId());
			}else {
				System.out.println("The triage is not available");
			}
		} catch (SQLException e) {
			System.out.println("Unable to assign a new patient to Triage");
		} 
	}
	
	//Assign a new patient with status = waitingInLine to a box of same spec. 
	//We assume the patientSpec date and patientTriage date are the same 
	public void assignNewPatient2Box(Box box, Speciality spec_type) {
		
		String select = "SELECT p.id, p.name, p.surname, speciality_type"
				+ "	FROM Patients AS p "
				+ "	LEFT JOIN PatientSpeciality AS ps ON ps.patient_id = p.id"
				+ "	WHERE status = 'waitingInLine' AND speciality_type = ?"
				+ "	ORDER BY p.urgency DESC";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(select);
			pstm.setString(1, spec_type.getType());
			
			ResultSet rs = pstm.executeQuery();
			Integer patientId = rs.getInt("id"); 
			rs.close();
			
			if(box.getAvailable()) {
				//Assign the patient to the box
				conMan.getBoxManager().assignPatientToBox(patientId, box.getId());
				
				//Change patients status to assisted
				conMan.getPatientMan().setStatus(patientId, "assistedInBox");
			}else {
				System.out.println("The box is not available");
			}
			
			
		} catch (SQLException e) {
			System.out.println("No patient assigned to box");
		} 
		
	}
	
}
