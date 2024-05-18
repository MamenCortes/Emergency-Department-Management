package urgency.db.interfaces;

import urgency.db.pojos.*;

public interface UserManager {

	public void register(User u);
	// Return null if there is no user
	public User login(String username, String password);
	void deleteUser(User u);
	void changePassword(User u, String password);
}
