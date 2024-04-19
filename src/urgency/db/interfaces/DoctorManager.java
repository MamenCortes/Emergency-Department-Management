package urgency.db.interfaces;

public interface DoctorManager {
	public void addDoctor(Doctor doctor);
	public List<Doctor> searchDoctorsBySurname(String surname); 
	public Doctor getDoctor(int id); 
	public void updateDoctor(Doctor doctor); 
	public List<Doctor> getDoctorsBySpeciality(String speciality_type); 
	public void assignBox(int Doctor_id, int Box_id); 
	public void deleteDoctor(int id); 
	public void changeStatus(boolean in_box); 
	
}