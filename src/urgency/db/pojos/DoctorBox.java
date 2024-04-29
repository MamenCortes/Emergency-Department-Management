package urgency.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class DoctorBox implements Serializable{
	
	private Integer id_doctor;
	private Integer id_box;
	
	public DoctorBox() {
		super();
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
