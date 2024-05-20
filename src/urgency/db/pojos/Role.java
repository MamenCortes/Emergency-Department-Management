package urgency.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5192341069760955022L;
	@Id
	@GeneratedValue(generator = "roles")
	@TableGenerator(name = "roles", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "roles")
	private Integer id;
	@Column(name="role")
	private String name;
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	@JoinColumn
	private List<User> users;
	
	public Role() {
		super();
		this.users = new ArrayList<User>();
	}

	public Role(String name) {
		super();
		if(validationRoles(name)) {
		this.name = name;
		}else {
			this.name = null; //se podria asignar un rol q no sea ninguno de los 4 esperados?
		}
		this.users = new ArrayList<User>();
	}
	
	private boolean validationRoles(String name) {
		if(!name.contentEquals("Recepcionist") || !name.contentEquals("Nurse")|| !name.contentEquals("Manager") || !name.contentEquals("Doctor")) {
			return false;
		}
		return true;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
		Role other = (Role) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	public void addUser(User u) {
		if (!users.contains(u))
			users.add(u);
	}
	
	public void removeUser(User u) {
		if (users.contains(u))
			users.remove(u);
	}
}
