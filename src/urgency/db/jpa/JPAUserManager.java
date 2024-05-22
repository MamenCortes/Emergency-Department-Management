package urgency.db.jpa;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.*;

import urgency.db.interfaces.UserManager;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class JPAUserManager implements UserManager {

	private EntityManager em;
	private JPAUserManager juserMan;
	private JPAUserManager jum;
	
	public JPAUserManager() {
		em = Persistence.createEntityManagerFactory("emergency-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();	
	}
	
	public void close() {
		em.close();
	}
	
	@Override
	public String getPassword(User u) {
		em.getTransaction().begin();
		if(u.getPassword().isEmpty()) {
			System.out.println("There password do not exist");
		}
		return u.getPassword(); 
		
	}
	
	@Override
	public void changePassword(User u, String password) {
		em.getTransaction().begin();
		em.persist(u);
		u.setPassword(password);
		em.getTransaction().commit();
		
	}
	
	/*
	public void userDoctor(User u) {
		Doctor doc = new Doctor();
		String username = u.getUsername();
		if(u.getRole() == new Role("Doctor")) {
			doc.getUsername() == username;
		}
	}
	*/ //no deberia validar aqui o si?
	
	@Override
	public boolean register(User u) {

		try {
			em.getTransaction().begin();

			String password = u.getPassword();
			String email = u.getEmail();
			Role role = u.getRole();
			Boolean userAlreadyRegistered = this.getUser(email, password);
			if (userAlreadyRegistered) {
				// System.out.println("The register cannot be done");
				return false;
			}
			em.persist(u);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	@Override
	public Boolean getUser(String email, String password) {

		Query q = em.createNativeQuery("SELECT id, email, password FROM users WHERE email = ? AND password = ?", User.class);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			User u = (User) q.getSingleResult();
			return (u.getEmail().equals(email) && u.getPassword().equals(password));
		}catch(Exception e){
		return false;
		}
		
	}

	
	@Override
	public User login(String email, String password) {
		User u;
		//Login log = null;
		Query q = em.createNativeQuery("SELECT id, email, password FROM users WHERE email = ? AND password = ?", User.class);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
		u = (User) q.getSingleResult();
		q.setParameter(1, email);
		//se codifica el password
		Boolean loginEncrypted = Login.login(email, password);
		if(loginEncrypted) {
		q.setParameter(2, password);
		}
		} catch (NoResultException | NoSuchAlgorithmException e) {
			return null;
		}
		return u;
	}
	
	@Override
	public void deleteUser(User u) {
		em.getTransaction().begin();
		//em.persist(u);
		em.remove(u);
		em.getTransaction().commit();
	}
	
	@Override
	public void assignRole(User u, Role r) {
		em.getTransaction().begin();
		if(u!=null) {
		u.setRole(r);
		r.addUser(u);
		em.getTransaction().commit();
		}
	}

	
	public JPAUserManager getJuserMan() {
		return juserMan;
	}

	public void setJuserMan(JPAUserManager juserMan) {
		this.juserMan = juserMan;
	}
	
}