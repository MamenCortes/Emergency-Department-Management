package urgency.db.interfaces;

import java.util.List;

import urgency.db.pojos.*;

public interface PatientManager {
	public void addPatient(Patient patient);
	public List<Patient> searchPatientsBySurname(String surname); 
	public Patient getPatient(int id);  //empty Lists of boxes, etc. 
	public void updatePatient(Patient patient);  
	public void setStatus(int id, String status);
	public void addComments(int Patient_id, int Box_id, String comments); 
	public List<PatientBox> getPatientRecords(Patient patient);
	public void createRandomPatients(); 
	public Boolean checkIfPatientExists(String name, String surname); 
	
	
	
}
