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
	
	public JPAUserManager() {
		em = Persistence.createEntityManagerFactory("emergency-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
			
	}
	
	public JPAUserManager(EntityManager em) {
		super();
		this.em=em;
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
	public void register(User u) {
		em.getTransaction().begin();
		String password = u.getPassword();
		String email = u.getEmail();
		UserRegister reg = null;
		Boolean availableRegister;
		try {
			availableRegister = reg.register(email, password);
			if(!availableRegister) {
				System.out.println("The register cannot be done");
			}
			em.persist(u);
			em.getTransaction().commit();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@Override
	public User login(String email, String password) {
		User u = null;
		Login log = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
		try {
		u = (User) q.getSingleResult();
		q.setParameter(1, email);
		//se codifica el password
		Boolean loginEncrypted = log.login(email, password);
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
		em.persist(u);
		em.remove(u);
		em.getTransaction().commit();
	}
	
	public JPAUserManager getJuserMan() {
		return juserMan;
	}

	public void setJuserMan(JPAUserManager juserMan) {
		this.juserMan = juserMan;
	}

	public static void main(String [] args) {
		User u = new User("ramonperez@hospital.es", "pasword1", new Role("Doctor"));
		User u2 = new User("elenagomez@hospital.es", "elena123", new Role("Recepcionist"));
		User u3 = new User("martagimenez@hospital.es", "martagm1", new Role("Manager"));
		User u4 = new User("gerardoprados@hospital.es", "user1234", new Role("Nurse"));
	    UserManager userMan = new JPAUserManager();
	    userMan.login(u.getEmail(), u.getPassword());
		
		
	}

}