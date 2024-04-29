package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class PatientTriage implements Serializable{
	
	private Integer id_patient;
	private Integer id_triage;
	private Date time;
	
	public PatientTriage() {
		super();
	}

	public PatientTriage(Integer id_patient, Integer id_triage, Date time) {
		super();
		this.id_patient = id_patient;
		this.id_triage = id_triage;
		this.time = time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_patient, id_triage);
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
		return Objects.equals(id_patient, other.id_patient) && Objects.equals(id_triage, other.id_triage);
	}
	
	
	
	

}
