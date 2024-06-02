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
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "users")
	@TableGenerator(name = "users", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "users")
	private Integer id;
	@Column(nullable = false, unique = true, name = "email")
	private String email; 
	@Column(name="password")
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
	public User() {
		
	}
	
	public User(String email, String password, Role role) {
		if(validateEmail(email)) {
			this.email = email;
		}else {
			this.email = email + "@hospital.com";
		}
		this.password = password;
		this.role = role;
	}

	
	private boolean validateEmail(String email) {
		
		return email.endsWith("@hospital.com");
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
}
