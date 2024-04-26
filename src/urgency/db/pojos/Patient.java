package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -569581925088569537L;
	
	private Integer id; 
	private String name; 
	private String surname; 
	private float weight; //kg
	private float height; //metros
	private String status; 
	private Integer urgency; 
	private String sex; 
	private Date birthDate; 
	
	private List<Speciality> specialities; 
	private List<Box> boxes; 
	private List<Triage> triages;
	
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
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
		
		this.specialities = new ArrayList<Speciality>(); 
		this.boxes = new ArrayList<Box>(); 
		this.triages = new ArrayList<Triage>(); 
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
				+ ", specialities=" + specialities + ", boxes=" + boxes + ", triages=" + triages + "]";
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
	public List<Speciality> getSpecialities() {
		return specialities;
	}
	public void setSpecialities(List<Speciality> specialities) {
		this.specialities = specialities;
	}
	public List<Box> getBoxes() {
		return boxes;
	}
	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}
	public List<Triage> getTriages() {
		return triages;
	}
	public void setTriages(List<Triage> triages) {
		this.triages = triages;
	} 
	
	

}
