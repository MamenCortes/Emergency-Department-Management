package urgency.db.interfaces;

import urgency.db.pojos.*;

public interface UserManager {

	public void register(User u);
	public User login(String username, String password);
	void deleteUser(User u);
	void changePassword(User u, String password);
	String getPassword(User u);
}
