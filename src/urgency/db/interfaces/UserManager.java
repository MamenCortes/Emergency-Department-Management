package urgency.db.interfaces;

import java.security.NoSuchAlgorithmException;

import urgency.db.pojos.*;

public interface UserManager {

	public boolean register(User u) throws NoSuchAlgorithmException;
	public User login(String username, String password);
	void deleteUser(User u);
	void changePassword(User u, String password);
	String getPassword(User u);
	Boolean getUser(String email, String password);
	void assignRole(User u, Role r);
}
