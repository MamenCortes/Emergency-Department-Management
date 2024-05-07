package urgency.db.interfaces;

import java.util.List;

import urgency.db.pojos.*;

public interface BoxManager {
	public void addBox(Box box); 
	public void deleteBox(int id); 
	public List<Box> getBoxes(Speciality speciality_type);
	public PatientBox getPatientInBox(int Box_id); 
	public void assignPatientToBox(int Box_id, int Patien_id);
	public Box getBox(int id); 
}
