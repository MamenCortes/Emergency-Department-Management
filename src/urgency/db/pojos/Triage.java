package urgency.db.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Triage implements Serializable {

	private static final long serialVersionUID = 4337661194552554031L;
	private Integer id;
	private boolean available;
	
	public Triage () {
		super();
		this.available = true;
		// TODO Auto-generated constructor stub
	}
	
	public Triage (boolean available) {
		super();
		this.available = available;
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
}
