package urgency.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import urgency.db.jpa.JPAUserManager;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Doctor")
@XmlType(propOrder = {"id", "name", "surname", "username", "email", "speciality_type", "in_box", "patients", "boxes"})
public class Doctor implements Serializable{


	private static final long serialVersionUID = 5306355128952164035L;
	@XmlAttribute
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String surname; 
	@XmlElement
	private String email;
	@XmlElement(name = "speciality")
	private Speciality speciality_type;
	@XmlAttribute
	private Boolean in_box;
	@XmlElement 
    @XmlElementWrapper(name = "Doctor") //the following list is going to be surrounded by an element
	private List<Patient> patients;
	@XmlElement
	@XmlElementWrapper(name = "Doctor")
	private List<Box> boxes;

	public Doctor() {
		super();
	}

	public Doctor(Integer id, String name, String surname, Speciality speciality_type, Boolean in_box, String email) {
		super();
		this.id=id;
		this.name=name;
		this.surname = surname; 
		this.speciality_type=speciality_type;
		this.in_box=in_box;
		this.patients=new ArrayList<> ();
		this.boxes=new ArrayList<> ();
	}
	
	public Doctor(Integer id, String name, String surname, String email, String speciality_type, Boolean in_box) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.speciality_type = new Speciality(speciality_type);
		this.in_box = in_box;
		
	}
	
	public Doctor(String name, String surname, Speciality speciality_type, String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.in_box = false;
		this.email = email; 
		this.speciality_type = speciality_type;
		this.patients=new ArrayList<> ();
		this.boxes=new ArrayList<> ();
	}
	

	public Doctor(String name, String surname, Speciality speciality_type, Boolean in_box) {
		super();
		this.name = name;
		this.surname = surname;
		this.speciality_type = speciality_type;
		this.in_box = in_box;
		this.patients = new ArrayList<>();
		this.boxes = new ArrayList<>();
	}

	public Doctor(Integer id, String name, String surname, String type, Boolean in_box) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.speciality_type= new Speciality(type); 
		this.in_box = in_box;
		this.patients = new ArrayList<>();
		this.boxes = new ArrayList<>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Speciality getSpeciality_type() {
		return speciality_type;
	}

	public void setSpeciality_type(Speciality speciality_type) {
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
		return "Doctor [ID=" + id + ", name=" + name + ", surname=" + surname +", email=" + email + ", speciality_type=" + speciality_type.getType() + ", in_box=" + in_box
				+ ", patients=" + patients + ", boxes=" + boxes + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
