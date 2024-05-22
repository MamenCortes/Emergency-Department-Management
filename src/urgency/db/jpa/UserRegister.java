package urgency.db.jpa;

import java.security.NoSuchAlgorithmException;

import urgency.db.interfaces.UserManager;
import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class UserRegister {

	private static JPAUserManager jpaUserManager;

	public UserRegister(JPAUserManager jpaUserManager) {
		this.jpaUserManager = jpaUserManager;
	}

	public static boolean register(String email, String password, Role role) throws NoSuchAlgorithmException {
		String passwordCodificada = MD5Cypher.encrypt(password);
		System.out.println("R:" + password);
		System.out.println("R:" + passwordCodificada);
		JPAUserManager jum = new JPAUserManager();
		System.out.println("error in register encrypted");

		User user = new User(email, passwordCodificada, role);
		jum.register(user);
		if(jum.getUser(user.getEmail(), user.getPassword())) {
			return true;
		}
		return false;
		// return jum.register(user));

	}

	/*
	 * public static void main(String [] args) throws NoSuchAlgorithmException {
	 * 
	 * //JPARoleManager jroleMan = new JPARoleManager(); //User u = new User(1,
	 * "ramonperez@hospital.es", "pasword1", new Role("Doctor")); /*User u2 = new
	 * User(2, "elenagomez@hospital.es", "elena123", new Role("Recepcionist")); User
	 * u3 = new User(3, "martagimenez@hospital.es", "martagm1", new
	 * Role("Manager")); User u4 = new User(4, "gerardoprados@hospital.es",
	 * "user1234", new Role("Nurse"));
	 */
	// JPAUserManager juserMan = new JPAUserManager();
	/*
	 * boolean userWasRegistered = register("ramonperez@hospital.es",
	 * "pasword1","Doctor"); if(!userWasRegistered) {
	 * System.out.println("User was not registered"); }
	 * System.out.println("User was registered"); //juserMan.login(u.getEmail(),
	 * u.getPassword());
	 * 
	 * }
	 */

	public static void main(String args[]) throws NoSuchAlgorithmException {
		
		  JPAUserManager jum = new JPAUserManager();
		  UserRegister userRegister = new UserRegister(jpaUserManager);
		  JPARoleManager roleMan = new JPARoleManager();
		  Role r = roleMan.getRole("Doctor");
		   System.out.println(UserRegister.register("email@hospital.com", "password1", r));
		   System.out.println(UserRegister.register("email2@hospital.com", "password1", r));
		   System.out.println(jum.login("email@hospital.com", "password1"));
		}

}
