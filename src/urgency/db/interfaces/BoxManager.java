package urgency.db.interfaces;

public interface BoxManager {
	public void addBox(Box box); 
	public void deleteBox(int id); 
	public List<Box> getBoxes();
	public Patient getPatientInBox(int Box_id); 
	public Box getBox(int id); 
}
