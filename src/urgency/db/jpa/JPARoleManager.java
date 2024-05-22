package urgency.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.persistence.Query;
import urgency.db.interfaces.RoleManager;

import urgency.db.pojos.Role;
import urgency.db.pojos.Speciality;
import urgency.db.pojos.User;

public class JPARoleManager implements RoleManager{
	
	private EntityManager em;
	private JPARoleManager jroleMan;
	
	public JPARoleManager() {
		em = Persistence.createEntityManagerFactory("emergency-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		// TODO: IF Roles not present, add them
		addRoles();
	}
	
	private void addRoles() {
			Role role1 = new Role("Recepcionist");
			createRole(role1);
			Role role2 = new Role("Nurse");
			createRole(role2);
			Role role3 = new Role("Manager");
			createRole(role3);
			Role role4 = new Role("Doctor");
			createRole(role4);
		
	}
	
	/*private void addRole(Role r) {
		List<Role> roles = getAllRoles();
		if(!roles.contains(r)) {
			roles.add(r);
		}*/
	
	@Override
	public void createRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String rolename) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE roleName = ?", Role.class);
		q.setParameter(1, rolename);
		Role role = (Role) q.getSingleResult();
		return role;
	}
	
	@Override
	public List<Role> getAllRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	public JPARoleManager getJroleMan() {
		return jroleMan;
	}

	public void setJroleMan(JPARoleManager jroleMan) {
		this.jroleMan = jroleMan;
	}
	
	public static void main(String args[]) {
		
		RoleManager rolMan = new JPARoleManager();
		rolMan.createRole(new Role("Nurse"));
		
	}


}
