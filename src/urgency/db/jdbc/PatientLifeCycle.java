package urgency.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import urgency.db.pojos.*;

public class PatientLifeCycle {
	//TODO metodos de implementacion de la interfaz x detras del recorrido del paciente con la base de datos

	private Connection connection;
	private ConnectionManager conMan;
	private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
	
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
			//e.printStackTrace();
		} 
	}
	
	//TODO test 
	//Assign a new patient with status = waitingInLine to a box of same spec. 
	//We assume the patientSpec date and patientTriage date are the same 
	public void assignNewPatient2Box(Box box, Speciality spec_type) {
		//Get box speciality
		
		/*String select = "SELECT pt.date, p.id 'patient_id', p.name, p.surname, t.id 'triage id', speciality_type"
				+ "	FROM Patients AS p JOIN PatientTriage AS pt ON pt.patient_id = p.id"
				+ "	JOIN Triages AS t ON triage_id = t.id"
				+ "	LEFT JOIN PatientSpeciality AS ps ON ps.patient_id = p.id"
				+ "	WHERE status = 'waitingInLine' AND pt.date > ?" // AND pt.date = ps.date AND speciality_type = ?
				+ "	ORDER BY pt.date ASC";*/
		
		String select = "SELECT p.id, p.name, p.surname, speciality_type"
				+ "	FROM Patients AS p "
				+ "	LEFT JOIN PatientSpeciality AS ps ON ps.patient_id = p.id"
				+ "	WHERE status = 'waitingInLine' AND speciality_type = ?" // AND pt.date = ps.date AND speciality_type = ?
				+ "	ORDER BY p.urgency DESC";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(select);

			/*LocalDateTime date = LocalDate.now().atStartOfDay(); 
			pstm.setTimestamp(1, Timestamp.valueOf(date));*/
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
			//e.printStackTrace();
		} 
		
	}
	
	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager(); 
		PatientLifeCycle pLife = new PatientLifeCycle(conMan); 
		
		//pLife.assignNewPatient2Box(null, null);
		
		pLife.assignNewPatient2Triage(conMan.getTriageManager().getTriage(3));
		
		conMan.closeConnection();
	}
	
}
