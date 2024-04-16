package urgency.db.jdbc;

import urgency.db.interfaces.Doctor;
import urgency.db.interfaces.DoctorManager;
import urgency.db.interfaces.List;

public class JDBCDoctorManager implements DoctorManager {

	@Override
	public void assignBox(int Doctor_id, int Box_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteDoctor(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(boolean in_box) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Doctor> searchDoctorsBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor getDoctor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Doctor> getDoctorsBySpeciality(String speciality_type) {
		// TODO Auto-generated method stub
		return null;
	}

}
