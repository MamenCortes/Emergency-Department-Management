package urgency.db.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -4330290027484220589L;
	@Id
	@GeneratedValue(generator = "users")
	@TableGenerator(name = "users", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "roles")
	private Integer id;
	@Column(nullable = false, unique = true, name = "")
	
	private String username; // validar xq el username tiene q ser un email corporativo del email.
	//private String email;
	@Column(name="pasword")
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
	public User() {
		super();
	}

	
	public User(String username, String password, Role role2)throws IllegalArgumentException {
		super();
		this.username = username;
	
		validatePassword(password);
		
		this.password = password;
		this.role = role2;
	}
	
	private void validatePassword(String password) throws IllegalArgumentException {
		boolean passwordVacia = (Objects.isNull(password)) || password.isEmpty();
		if(passwordVacia || password.length() < 8) {
		 throw new IllegalArgumentException("Password is empty");
		}
		//que contenga al menos un numero y un caracter
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
}
