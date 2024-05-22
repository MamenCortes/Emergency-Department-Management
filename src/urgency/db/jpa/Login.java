package urgency.db.jpa;

import java.security.NoSuchAlgorithmException;

import urgency.db.jpa.JPAUserManager;
import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class Login {

	public static boolean login(String email, String password) throws NoSuchAlgorithmException {
		String passwordCodificada = MD5Cypher.encrypt(password);
		JPAUserManager jpau = new JPAUserManager();
		User user;
		user = jpau.login(email, passwordCodificada);
		
		return (null!=user);
		
	}
	
}

