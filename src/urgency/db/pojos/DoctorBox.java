package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class DoctorBox implements Serializable{
	
	private Doctor doctor;
	private Box box;
	private Date date;
	
	public DoctorBox() {
		super();
	}

	public DoctorBox(Doctor doctor, Box box, Date date) {
		super();
		this.doctor = doctor;
		this.box = box;
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(box, date, doctor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoctorBox other = (DoctorBox) obj;
		return Objects.equals(box, other.box) && Objects.equals(date, other.date)
				&& Objects.equals(doctor, other.doctor);
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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

	@Override
	public String toString() {
		return "DoctorBox [doctor=" + doctor + ", box=" + box + ", date=" + date + "]";
	}
	
	

	
   
}
