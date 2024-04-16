package urgency.db.jdbc;

import urgency.db.*;
import urgency.db.interfaces.PatientManager;

public class JDBCPatientManager implements PatientManager {

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
		
	}

	@Override
	public List<Patient> searchPatientsBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getPatient(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

}
