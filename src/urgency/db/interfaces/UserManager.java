package urgency.db.interfaces;

import java.security.NoSuchAlgorithmException;

import urgency.db.pojos.*;

public interface UserManager {

	public boolean register(User u) throws NoSuchAlgorithmException;
	public User login(String email, String password);
	public void deleteUser(User u);
	public void changePassword(User u, String password);
	public boolean isUser(String email);
	public void assignRole(User u, Role r);
	User getUserByEmail(String email);
}
