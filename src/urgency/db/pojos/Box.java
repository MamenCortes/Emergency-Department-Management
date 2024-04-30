package urgency.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Box implements Serializable{
	
	
	private static final long serialVersionUID = 2176901194552554551L;
	private Integer id;
	private boolean available;
	private String speciality1; //te lo he cambiado a String en vez de tipo Speciality para
	//que no de errores de compilación
	private Speciality speciality; //lo he vuelto a cambiar a speciality
	//He creado otro constructor que admita un objeto speciality en vez de un string	
	
	public Box() {
		super();
	}
	
	public Box (Integer id, boolean available, String speciality) {
		super();
		this.id = id;
		this.available = available;
		this.speciality = new Speciality(speciality);
	}
	
	public Box (Integer id, boolean available, Speciality speciality) {
		super();
		this.id = id;
		this.available = available;
		this.speciality = speciality;
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
		Box other = (Box) obj;
		return Objects.equals(id, other.id);
	}



	@Override
	public String toString() {
		return "Box [id=" + id + ", available=" + available + ", speciality=" + speciality + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = new Speciality(speciality);
	}
	
	
}
