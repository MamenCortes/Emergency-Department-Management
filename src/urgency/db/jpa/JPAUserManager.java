package urgency.db.jpa;

import java.util.List;

import javax.persistence.*;

import urgency.db.interfaces.UserManager;
import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class JPAUserManager implements UserManager {

	private EntityManager em;
	
	public JPAUserManager() {
		em = Persistence.createEntityManagerFactory("emergency-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		try {
			this.getRole("");
		} catch(NoResultException e) {
			this.createRole(new Role(""));
			this.createRole(new Role(""));
		}
	}
	
	@Override
	public void register(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	
	@Override
	public User login(String username, String password) {
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
		q.setParameter(1, username);
		//se codifica el password
		q.setParameter(2, password);
		try {
			u = (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return u;
	}
	
	@Override
	public void deleteUser(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void createRole(Role r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role getRole(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignRole(User u, Role r) {
		// TODO Auto-generated method stub
		
	}


}