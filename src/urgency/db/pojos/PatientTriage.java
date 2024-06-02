package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class PatientTriage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Patient patient;
	private Triage triage;
	private Timestamp time;
	
	public PatientTriage() {
		super();
	}

	public PatientTriage(Patient patient, Triage triage, Timestamp time) {
		super();
		this.patient = patient;
		this.triage = triage;
		this.time = time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(patient, time, triage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientTriage other = (PatientTriage) obj;
		return Objects.equals(patient, other.patient) && Objects.equals(time, other.time)
				&& Objects.equals(triage, other.triage);
	}

	@Override
	public String toString() {
		return "PatientTriage [patient=" + patient + ", triage=" + triage + ", time=" + time + "]";
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Triage getTriage() {
		return triage;
	}

	public void setTriage(Triage triage) {
		this.triage = triage;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
	
	

}
