package urgency.db.interfaces;

public interface PatientManager {
	public void addPatient(Patient patient);
	public List<Patient> searchPatientsBySurname(String surname); 
	public Patient getPatient(int id);  //empty Lists of boxes, etc. 
	public void admitPatient(int id, int urgency); 
	public void updatePatient(Patient patient); 
	public void dischargePatient(int id); 
	public void setStatus(int id, String status);
	public void addComments(int Patient_id, int Box_id, String comments); 
	public void getPatientWhithRecords(int Patient_id); //It is not void

	
	
}
