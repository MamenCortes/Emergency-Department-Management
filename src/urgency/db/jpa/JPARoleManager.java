package urgency.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.persistence.Query;
import urgency.db.interfaces.RoleManager;

import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class JPARoleManager implements RoleManager{
	
	private EntityManager em;
	
	public JPARoleManager() {
		em = Persistence.createEntityManagerFactory("emergency-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}
	
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
