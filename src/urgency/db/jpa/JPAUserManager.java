package urgency.db.jpa;

import java.security.NoSuchAlgorithmException;

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
	
	public void close() {
		em.close();
	}

	/**
	 * Changes the password of a given user
	 */
	@Override
	public boolean changePassword(User u, String password) {
		try {
			em.getTransaction().begin();
			String passwordCodificada;
			passwordCodificada = MD5Cypher.encrypt(password);
			u.setPassword(passwordCodificada);
			em.getTransaction().commit();
			return true; 
		} catch (NoSuchAlgorithmException e) {
			return false; 
			//e.printStackTrace();
		}

		
	}
	
	/**
	 * Inserts a user in the DB. 
	 * Returns true if the user is inserted. 
	 * returns false if the user couldn't be inserted  
	 * throws NoSuchalgorithmException when the password couldn't be encrypted.
	 */
	@Override
	public boolean register(User u) throws NoSuchAlgorithmException, RollbackException {

		try {
			em.getTransaction().begin();

			String password = u.getPassword();
			String passwordEncrypted = MD5Cypher.encrypt(password);
			u.setPassword(passwordEncrypted);
			em.persist(u);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * Checks if a user exists by a given email
	 */
	@Override
	public boolean isUser(String email) {

		Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? ", User.class);
		q.setParameter(1, email);
		try {
			User u = (User) q.getSingleResult();
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	

	/**
	 * Retrieves a user from the DB with a given name and password. 
	 * If the user doesn't exist or the password doesn't match, returns null
	 */
	@Override
	public User login(String email, String password) {
		User u;
		try {
		Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ? AND password = ?", User.class);
		q.setParameter(1, email);
		String passwordEncrypted = MD5Cypher.encrypt(password);
		q.setParameter(2, passwordEncrypted);

		u = (User) q.getSingleResult();
		} catch (NoResultException | NoSuchAlgorithmException e) {
			return null;
		}
		return u;
	}
	
	@Override
	public void deleteUser(User u) {
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}
	
	@Override
	public void assignRole(User u, Role r) {
		em.getTransaction().begin();
		u.setRole(r);
		r.addUser(u);
		em.getTransaction().commit();
	}
	
	@Override
	public User getUserByEmail(String email) {
		User u;
		try {
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email = ?", User.class);
			q.setParameter(1, email);
			u = (User) q.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
		return u;
	}
	
	@Override
	public List<User> getAllUsers() {
		Query q = em.createNativeQuery("SELECT * FROM users", User.class);
		List<User> users = (List<User>) q.getResultList();
		return users;
	}

}