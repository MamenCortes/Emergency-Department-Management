package urgency.db.interfaces;

import java.util.List;

import urgency.db.pojos.*;

public interface BoxManager {
	public void addBox(Box box); 
	public void deleteBox(int id); 
	public List<Box> getBoxesBySpeciality(Speciality speciality_type);
	public List<Box> getBoxes();
	public PatientBox getPatientInBox(int Box_id); 
	public void assignPatientToBox(int Box_id, int Patien_id);
	public Box getBox(int id); 
	public DoctorBox getLastBoxAssignedToDoctor(Doctor doctor);
}
