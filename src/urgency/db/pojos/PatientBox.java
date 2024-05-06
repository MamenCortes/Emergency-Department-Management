package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class PatientBox implements Serializable {
   
	private static final long serialVersionUID = 7191994403476647454L;
	private Patient patient;
	private Box box;
	private Date date;
	private String comments;
	
	public PatientBox() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(box, date, patient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientBox other = (PatientBox) obj;
		return Objects.equals(box, other.box) && Objects.equals(date, other.date)
				&& Objects.equals(patient, other.patient);
	}

	@Override
	public String toString() {
		return "PatientBox [patient=" + patient + ", box=" + box + ", date=" + date + ", comments=" + comments + "]";
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	

	
	
	
	
	
	
}
