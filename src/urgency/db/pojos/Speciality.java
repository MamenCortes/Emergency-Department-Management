package urgency.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Speciality implements Serializable{


	private static final long serialVersionUID = 660690239666232013L;
	private String type;
	private List<Patient> patients;
	
	public Speciality() {
		super();
		this.patients = new ArrayList<Patient>();
		// TODO Auto-generated constructor stub
	}
	
	public Speciality(String type) {
		super();
		this.type = type;
		this.patients = new ArrayList<Patient>(); 
	}

	@Override
	public String toString() {
		return "Speciality [type=" + type + ", patients=" + patients + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speciality other = (Speciality) obj;
		return Objects.equals(type, other.type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	} 
	
	
	
	
}
