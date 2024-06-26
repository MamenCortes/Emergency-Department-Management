package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import urgency.xml.utils.SQLDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "surname", "weight", "height", "status", "urgency", "sex", "birthDate"})
public class Patient implements Serializable{
	
	private static final long serialVersionUID = -569581925088569537L;
	
	@XmlAttribute
	private Integer id; 
	@XmlElement
	private String name; 
	@XmlElement
	private String surname; 
	@XmlAttribute
	private float weight; //kg
	@XmlAttribute
	private float height; //metros
	@XmlElement
	private String status;
	@XmlAttribute
	private Integer urgency; 
	@XmlElement
	private String sex;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date birthDate; 
	@XmlTransient
	private List<PatientBox> boxesOfPatient;
	@XmlTransient
	private List<PatientSpeciality> specialitiesOfPatient; 
	@XmlTransient
	private List<PatientTriage> triagesOfPatient; 


	public Patient() {
		super();
	}
	
	
	public Patient(Integer id, String name, String surname, float weight, float height, String status, Integer urgency,
			String sex, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.weight = weight;
		this.height = height;
		this.status = status;
		this.urgency = urgency;
		this.sex = sex;
		Date date = birthDate;
		this.birthDate = date;
	}


	/**
	 * @param name = name of the patient
	 * @param surname = surname of the patient
	 * @param status = Accepted values: waiting, assisted, emergency room, discharged or hospitalized
	 * @param urgency = a number from 1-5 indicating the urgency based on patient's physical state
	 * @param sex = sex of the patient. Accepted values:  Man or Woman
	 * @param birthDate = the Birth Date of the patient, type LocalDate: YYYY-MM-DD
	 */
	public Patient(String name, String surname, String status, Integer urgency, String sex, LocalDate birthDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.status = status;
		this.urgency = urgency;
		this.sex = sex;
		Date date = Date.valueOf(birthDate);
		this.birthDate = date;
		
		this.boxesOfPatient = new ArrayList <PatientBox>();
		this.specialitiesOfPatient = new ArrayList <PatientSpeciality>();
		this.triagesOfPatient = new ArrayList <PatientTriage>();
	}
	
	/**
	 * @param id = ID of the patient
	 * @param name = name of the patient
	 * @param surname = surname of the patient
	 * @param status = Accepted values: waiting, assisted, emergency room, discharged or hospitalized
	 * @param urgency = a number from 1-5 indicating the urgency based on patient's physical state
	 * @param sex = sex of the patient. Accepted values:  Man or Woman
	 * @param birthDate = the Birth Date of the patient, type LocalDate: YYYY-MM-DD
	 */
	public Patient(Integer id, String name, String surname, String status, Integer urgency, String sex, Date birthDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.status = status;
		this.urgency = urgency;
		this.sex = sex;
		this.birthDate = birthDate;
		
		this.boxesOfPatient = new ArrayList <PatientBox>();
		this.specialitiesOfPatient = new ArrayList <PatientSpeciality>();
		this.triagesOfPatient = new ArrayList <PatientTriage>();
	}

	public Patient(String name, String surname, float weight, float height, String status, Integer urgency, 
			String sex, Date birthDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.weight = weight;
		this.height = height;
		this.status = status;
		this.urgency = urgency;
		this.sex = sex;
		this.birthDate = birthDate;
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + ", weight=" + weight + ", height="
				+ height + ", status=" + status + ", urgency=" + urgency + ", sex=" + sex + ", birthDate=" + birthDate
				+ ", specialities=" + specialitiesOfPatient + ", boxes=" + boxesOfPatient + ", triages=" + triagesOfPatient + "]";
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUrgency() {
		return urgency;
	}
	public void setUrgency(Integer urgency) {
		this.urgency = urgency;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setAge(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<PatientBox> getBoxesOfPatient() {
		return boxesOfPatient;
	}


	public void setBoxesOfPatient(List<PatientBox> boxesOfPatient) {
		this.boxesOfPatient = boxesOfPatient;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public List<PatientSpeciality> getSpecialitiesOfPatient() {
		return specialitiesOfPatient;
	}


	public void setSpecialitiesOfPatient(List<PatientSpeciality> specialitiesOfPatient) {
		this.specialitiesOfPatient = specialitiesOfPatient;
	}


	public List<PatientTriage> getTriagesOfPatient() {
		return triagesOfPatient;
	}


	public void setTriagesOfPatient(List<PatientTriage> triagesOfPatient) {
		this.triagesOfPatient = triagesOfPatient;
	}
	
	

}
