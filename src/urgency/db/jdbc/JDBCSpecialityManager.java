package urgency.db.jdbc;

import java.sql.Connection;
import java.util.List;

import urgency.db.interfaces.*;
import urgency.db.interfaces.SpecialityManager;
import urgency.db.pojos.Speciality;

public class JDBCSpecialityManager implements SpecialityManager {
	
	private ConnectionManager conManager;
	private Connection connection; 
	
	public void JDBCSpecilityManager(ConnectionManager conManager) {
		this.conManager = conManager; 
		this.connection = conManager.getConnection(); 
	}

	@Override
	public void assignPatientSpeciality(int Patient_id, String Speciality_type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignDoctorSpeciality(int Doctor_id, String Speciality_type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assignBoxSpecility(int Box_id, String Speciality_type) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getSpecialities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSpeciality(Speciality speciality) {
		// TODO Auto-generated method stub
		
	}


}
