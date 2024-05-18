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
		
		
	}
	
	@Override
	public void changePassword(User u, String password) {
		em.getTransaction().begin();
		em.persist(u);
		u.setPassword(password);
		em.getTransaction().commit();
		
		em.close();
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
		em.remove(u);
		em.getTransaction().commit();
	}


}