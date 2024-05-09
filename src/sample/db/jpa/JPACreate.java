package sample.db.jpa;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import urgency.db.pojos.*;

public class JPACreate {
	
	public static void main(String[] args) throws IOException {
		// Get the entity manager
		EntityManager em = Persistence.createEntityManagerFactory("emergency-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		// Get the department info from the command prompt
		System.out.println("Please, input the emergency info:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print(": ");
		String address = reader.readLine();
					
		// Create the object
		Department dep1 = new Department(name, address);
		
		em.getTransaction().begin();
		em.persist(dep1);
		em.getTransaction().commit();
		em.close();
	}

}



