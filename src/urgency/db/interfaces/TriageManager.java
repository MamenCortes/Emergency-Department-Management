package urgency.db.interfaces;

public interface TriageManager {
	public void addTriage(Triage triage); 
	public void deleteTriage(int id); 
	public List<Triage> getTriages();
	public Patient getPatientInTriage(int Triage_id); 
	public Triage getTriage(int id); 
}
