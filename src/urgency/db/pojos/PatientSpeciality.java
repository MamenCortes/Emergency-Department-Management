package urgency.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class PatientSpeciality implements Serializable{
	
	private Integer id_patient;
	private Integer id_speciality;
	
	public PatientSpeciality() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_patient, id_speciality);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientSpeciality other = (PatientSpeciality) obj;
		return Objects.equals(id_patient, other.id_patient) && Objects.equals(id_speciality, other.id_speciality);
	}
	
	

}
