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
	@Column(nullable = false, unique = true, name = "email")
	private String email; // validar xq el username tiene q ser un email corporativo del email.
	@Column(name="password")
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
	public User() {
		super();
	}
	
	public User(Integer id, String email, String password, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	
	public User(String email, String password, Role role2)throws IllegalArgumentException {
		super();
		this.email = email;
	
		validatePassword(password);
		
		this.password = password;
		this.role = role2;
	}
	
	private void validatePassword(String password) throws IllegalArgumentException {
		boolean passwordVacia = (Objects.isNull(password)) || password.isEmpty();
		boolean goodPassword=false;
		if(passwordVacia || password.length() < 8) {
			for(int i=0; i<8; i++) {
			if(Character.isDigit(password.charAt(i))) {
			goodPassword = true;
			}if(i == 8 && !goodPassword) {
				throw new IllegalArgumentException("The password must have at least one number as well as characters with a lenght of 8 characters.");
			}
		 throw new IllegalArgumentException("Password is empty");
		 }
	   }
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
