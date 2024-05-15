package sample.db.jpa;

import java.util.List;

import javax.persistence.Query;

import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class JPARoleManager {
	
	@Override
	public void createRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
		q.setParameter(1, name);
		Role r = (Role) q.getSingleResult();
		return r;
	}
	
	@Override
	public List<Role> getAllRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public void assignRole(User u, Role r) {
		em.getTransaction().begin();
		if(u!=null) {
		u.setRole(r);
		r.addUser(u);
		em.getTransaction().commit();
		}
		//si es nulo?
	}


}
