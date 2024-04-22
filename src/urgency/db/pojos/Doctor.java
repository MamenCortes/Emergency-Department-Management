package urgency.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor implements Serializable{


	private static final long serialVersionUID = 5306355128952164035L;
	private Integer ID;
	private String name;
	private String speciality_type;
	private Boolean in_box;

	private List<Patient> patients;
	private List<Box> boxes;

	public Doctor() {
		super();
	}

	public Doctor(Integer ID, String name, String speciality_type, Boolean in_box) {
		this.ID=ID;
		this.name=name;
		this.speciality_type=speciality_type;
		this.in_box=in_box;
		this.patients=new ArrayList<> ();
		this.boxes=new ArrayList<> ();
	}
	
	public Doctor(Integer iD, String name, String speciality_type) {
		super();
		ID = iD;
		this.name = name;
		this.speciality_type = speciality_type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Doctor other = (Doctor) obj;
		return Objects.equals(ID, other.ID);
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality_type() {
		return speciality_type;
	}

	public void setSpeciality_type(String speciality_type) {
		this.speciality_type = speciality_type;
	}

	public Boolean getIn_box() {
		return in_box;
	}

	public void setIn_box(Boolean in_box) {
		this.in_box = in_box;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}

	@Override
	public String toString() {
		return "Doctor [ID=" + ID + ", name=" + name + ", speciality_type=" + speciality_type + ", in_box=" + in_box
				+ ", patients=" + patients + ", boxes=" + boxes + "]";
	}




}
