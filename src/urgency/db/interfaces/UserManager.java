package urgency.db.interfaces;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import urgency.db.pojos.*;

public interface UserManager {

	public boolean register(User u) throws NoSuchAlgorithmException;
	public User login(String email, String password);
	public void deleteUser(User u);
	public boolean changePassword(User u, String password);
	public boolean isUser(String email);
	public void assignRole(User u, Role r);
	public User getUserByEmail(String email);
	public List<User> getAllUsers();
}
