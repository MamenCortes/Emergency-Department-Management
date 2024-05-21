package urgency.db.jpa;

import java.security.NoSuchAlgorithmException;

import urgency.db.interfaces.UserManager;
import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class UserRegister {
	
	public static boolean register(String email, String password, String roleName) throws NoSuchAlgorithmException {
		String passwordCodificada = MD5Cypher.encrypt(password);
		Role role = null;
		JPARoleManager jrm = new JPARoleManager();
		System.out.println("error in register encrypted");
		try {
			role = jrm.getRole(roleName);
			System.out.println(role.getName());
			System.out.println(role.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = new User(email, passwordCodificada, role);
		JPAUserManager jum = new JPAUserManager();
		return jum.register(user);
		
		
	}
	
	public static void main(String [] args) throws NoSuchAlgorithmException {
		//JPARoleManager jroleMan = new JPARoleManager();
		//User u = new User(1, "ramonperez@hospital.es", "pasword1", new Role("Doctor"));
		/*User u2 = new User(2, "elenagomez@hospital.es", "elena123", new Role("Recepcionist"));
		User u3 = new User(3, "martagimenez@hospital.es", "martagm1", new Role("Manager"));
		User u4 = new User(4, "gerardoprados@hospital.es", "user1234", new Role("Nurse"));*/
	    //JPAUserManager juserMan = new JPAUserManager();
	    boolean userWasRegistered = register("ramonperez@hospital.es", "pasword1","Doctor");
	    if(!userWasRegistered) {
	    	System.out.println("User was not registered");
	    }
	    System.out.println("User was registered");
	    //juserMan.login(u.getEmail(), u.getPassword());
	   
		
		
	}

}
