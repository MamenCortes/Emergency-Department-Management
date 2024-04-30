package urgency.db.pojos;

import java.io.Serializable;
import java.util.*;


public class Triage implements Serializable {

	private static final long serialVersionUID = 4337661194552554031L;
	private Integer id;
	private boolean available;
	private List<Patient> patients;
	
	public Triage (boolean available) {
		super();
		this.available = true;
		this.patients = new ArrayList<Patient>();
	}
	
	public Triage (int id,boolean available) {
		super();
		this.id = id;
		this.available = available;
		this.patients = new ArrayList<Patient>();
		
	}

	@Override
	public String toString() {
		return "Triage [id=" + id + ", available=" + available + "]";
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
		Triage other = (Triage) obj;
		return Objects.equals(id, other.id);
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
	
	
}
