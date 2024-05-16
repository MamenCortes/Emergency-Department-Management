package urgency.db.jpa;

import java.security.NoSuchAlgorithmException;

import urgency.db.jpa.JPAUserManager;
import urgency.db.pojos.User;

public class Login {

	public boolean login(String username, String password) throws NoSuchAlgorithmException {
		String passwordCodificada = MD5Cypher.encrypt(password);
		JPAUserManager jpau = new JPAUserManager();
		User user;
		user = jpau.login(username, passwordCodificada);
		
		return (null!=user);
		
	}
}
