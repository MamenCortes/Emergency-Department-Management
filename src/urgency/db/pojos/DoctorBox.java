package urgency.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class DoctorBox implements Serializable{
	
	private Integer id_doctor;
	private Integer id_box;
	private Date date;
	
	public DoctorBox() {
		super();
	}

	public DoctorBox(Integer id_doctor, Integer id_box, Date date) {
		super();
		this.id_doctor = id_doctor;
		this.id_box = id_box;
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_box, id_doctor);
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
		return Objects.equals(id_box, other.id_box) && Objects.equals(id_doctor, other.id_doctor);
	}
	
	
	
	
	
   
}
