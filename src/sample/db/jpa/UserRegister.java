package sample.db.jpa;

import java.security.NoSuchAlgorithmException;

import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class UserRegister {
	
	public static boolean register(String username, String password) throws NoSuchAlgorithmException {
		String passwordCodificada = MD5Cypher.encrypt(password);
		Role role = new Role();
		User user = new User(username, passwordCodificada, role);
		JPAUserManager jpau = new JPAUserManager();
		jpau.register(user);
		return true;
		
	}

}
