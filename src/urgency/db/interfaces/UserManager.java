package urgency.db.interfaces;

import java.util.List;

import urgency.db.pojos.*;

public interface UserManager {

	public void register(User u);
	public void createRole(Role r);
	public Role getRole(String name);
	public List<Role> getAllRoles();
	public void assignRole(User u, Role r);
	// Return null if there is no user
	public User login(String username, String password);
	void deleteUser(User u);
}
