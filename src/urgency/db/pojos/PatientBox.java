package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class PatientBox implements Serializable {
   
	private Integer id_patient;
	private Integer box_id;
	private Date date;
	private String comments;
	
	public PatientBox() {
		super();
	}
	
	public PatientBox(Integer id_patient, Integer box_id, Date date, String comments) {
		super();
		this.id_patient = id_patient;
		this.box_id = box_id;
		this.date = date;
		this.comments = comments;
	}


	@Override
	public int hashCode() {
		return Objects.hash(box_id, id_patient);
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
		return Objects.equals(box_id, other.box_id) && Objects.equals(id_patient, other.id_patient);
	}

	public Integer getId_patient() {
		return id_patient;
	}

	public void setId_patient(Integer id_patient) {
		this.id_patient = id_patient;
	}

	public Integer getBox_id() {
		return box_id;
	}

	public void setBox_id(Integer box_id) {
		this.box_id = box_id;
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

	@Override
	public String toString() {
		return "PatientBox [id_patient=" + id_patient + ", box_id=" + box_id + ", date=" + date + ", comments="
				+ comments + "]";
	}
	
	
	
	
	
	
}
