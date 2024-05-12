package sample.db.jpa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import urgency.db.pojos.*;

public class JPAUpdate {
	//los updates de cada clase base
	
		private static EntityManager em;
		
		private static void printBoxes() {
			Query q1 = em.createNativeQuery("SELECT * FROM Boxes", Box.class);
			List<Box> boxes = (List<Box>) q1.getResultList();
			// Print the boxes:
			for (Box b : boxes) {
				System.out.println(b);
			}
		}
		
		private static void printTriages() {
			Query q2 = em.createNativeQuery("SELECT * FROM Triages", Triage.class);
			List<Triage> triages = (List<Triage>) q2.getResultList();
			// Print the triages:
			for (Triage t: triages) {
				System.out.println(t);
			}
		}
		
		private static void printDoctors() {
			Query q3 = em.createNativeQuery("SELECT * FROM Doctors", Doctor.class);
			List<Doctor> doctors = (List<Doctor>) q3.getResultList();
			// Print the doctors:
			for (Doctor d: doctors) {
				System.out.println(d);
			}
		}
		
		private static void printPatients() {
			Query q4 = em.createNativeQuery("SELECT * FROM Patients", Patient.class);
			List<Patient> patients = (List<Patient>) q4.getResultList();
			// Print the patients:
			for (Patient p: patients) {
				System.out.println(p);
			}
		}
		
		public static void main(String[] args) throws Exception {
			
			em = Persistence.createEntityManagerFactory("urgency-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();

			// Get the new department's location from the command prompt
			System.out.println("Company's departments:");
			printDepartments();
			System.out.print("Choose a department to change its location. Type it's ID:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int dep_id = Integer.parseInt(reader.readLine());
			Query q2 = em.createNativeQuery("SELECT * FROM departments WHERE id = ?", Department.class);
			q2.setParameter(1, dep_id);
			Department dep = (Department) q2.getSingleResult();
			System.out.print("Type it's new location:");
			String newLocation = reader.readLine();
			
			// Begin transaction
			em.getTransaction().begin();
			// Make changes
			patient.setAddress(newLocation);
			// End transaction
			em.getTransaction().commit();
			
			// Close the entity manager
			em.close();
		}
	}

}
