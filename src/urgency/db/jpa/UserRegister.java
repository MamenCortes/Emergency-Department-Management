package urgency.db.jpa;

import java.security.NoSuchAlgorithmException;

import urgency.db.interfaces.UserManager;
import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class UserRegister {

	public static boolean register(String email, String password, Role role) throws NoSuchAlgorithmException {
		String passwordCodificada = MD5Cypher.encrypt(password);
		JPAUserManager jum = new JPAUserManager();
		//System.out.println("error in register encrypted");

		User user = new User(email, passwordCodificada, role);
		
		return jum.register(user);
		//al llamarlo desde la applicacion se envian mensajes de error
		//si retorna true es q el usuario de ha registrado

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
		  JPARoleManager roleMan = new JPARoleManager();
		  Role r = roleMan.getRole("Doctor");
		   System.out.println(UserRegister.register("email@hospital.com", "password1", r));
		  //System.out.println(UserRegister.register("email2@hospital.com", "password1", r));
		   System.out.println(jum.login("email@hospital.com", "password1"));
		}

}
