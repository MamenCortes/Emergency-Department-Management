package urgency.db.interfaces;

public interface SpecialityManager {
	public void assignPatientSpeciality(int Patient_id, String Speciality_type); 
	public void assignDoctorSpeciality(int Doctor_id, String Speciality_type); 
	public void assignBoxSpecility(int Box_id, String Speciality_type); 
	public List<String> getSpecialities(); 
	public void addSpeciality(Speciality speciality); 
	
}
