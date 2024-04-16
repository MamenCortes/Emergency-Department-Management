package urgency.db.pojos;

import java.io.Serializable;
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
	private Integer age; 
	
	private List<Speciality> specialities; 
	private List<Box> boxes; 
	private List<Triage> triages;
	
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Patient(String name, String surname, String status, Integer urgency, String sex, Integer age) {
		super();
		this.name = name;
		this.surname = surname;
		this.status = status;
		this.urgency = urgency;
		this.sex = sex;
		this.age = age;
		
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
				+ height + ", status=" + status + ", urgency=" + urgency + ", sex=" + sex + ", age=" + age
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
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
