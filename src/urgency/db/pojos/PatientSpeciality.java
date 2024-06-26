package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class PatientSpeciality implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_patient;
	private Speciality id_speciality;
	private Date date;
	
	public PatientSpeciality() {
		super();
	}
	
	public PatientSpeciality(Integer id_patient, Speciality id_speciality, Date date) {
		super();
		this.id_patient = id_patient;
		this.id_speciality = id_speciality;
		this.date = date;
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
