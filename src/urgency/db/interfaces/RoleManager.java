package urgency.db.interfaces;

import java.util.List;

import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public interface RoleManager {
	
	public void createRole(Role r);
	public Role getRole(String name);
	public List<Role> getAllRoles();


}
