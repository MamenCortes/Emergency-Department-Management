package urgency.db.interfaces;

import java.sql.Timestamp;
import java.util.List;

import urgency.db.pojos.*;

public interface SpecialityManager {
	public void assignPatientSpeciality(int Patient_id, String Speciality_type, Timestamp date); 
	//public void assignDoctorSpeciality(int Doctor_id, String Speciality_type); 
	public void assignBoxSpeciality(int Box_id, String Speciality_type); 
	public List<String> getSpecialities(); 
	public void addSpeciality(Speciality speciality); 
	
}
