package urgency.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Box implements Serializable{
	
	
	private static final long serialVersionUID = 2176901194552554551L;
	private Integer id;
	private boolean available;
	//private String speciality1;
	private Speciality speciality;
	private List<PatientBox> patientBox;
	private List<DoctorBox> doctorBox;
	
	public Box() {
		super();
	}

	public Box (Integer id, boolean available, Speciality speciality) {
		super();
		this.id = id;
		this.available = available;
		this.speciality = speciality;
		this.setPatients(new ArrayList<PatientBox>());
		this.setDoctors(new ArrayList<DoctorBox>());
	}
	

	public Box(boolean available, Speciality speciality) {
		super();
		this.available = available;
		this.speciality = speciality;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Box other = (Box) obj;
		return Objects.equals(id, other.id);
	}



	@Override
	public String toString() {
		return "Box [id=" + id + ", available=" + available + ", speciality=" + speciality + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = new Speciality(speciality);
	}
	
	private void setDoctors(ArrayList<DoctorBox> arrayList) {
		// TODO Auto-generated method stub
	}

	private void setPatients(ArrayList<PatientBox> arrayList) {
		// TODO Auto-generated method stub
	}

	public List<PatientBox> getPatientBox() {
		return patientBox;
	}

	public void setPatientBox(List<PatientBox> patientBox) {
		this.patientBox = patientBox;
	}

	public List<DoctorBox> getDoctorBox() {
		return doctorBox;
	}

	public void setDoctorBox(List<DoctorBox> doctorBox) {
		this.doctorBox = doctorBox;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}


	
	
}
