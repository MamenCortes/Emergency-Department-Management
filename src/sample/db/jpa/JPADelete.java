package sample.db.jpa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import urgency.db.pojos.*;

public class JPADelete {
	//los delete de cada clase base

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
			Query q4 = em.createNativeQuery("SELECT * FROM Triages", Triage.class);
			List<Triage> triages = (List<Triage>) q4.getResultList();
			// Print the triages:
			for (Triage t: triages) {
				System.out.println(t);
			}
		}
		
		private static void printDoctors() {
			Query q5 = em.createNativeQuery("SELECT * FROM Doctors", Doctor.class);
			List<Doctor> doctors = (List<Doctor>) q5.getResultList();
			// Print the doctors:
			for (Doctor d: doctors) {
				System.out.println(d);
			}
		}

		public static void main(String[] args) throws Exception {
			
			em = Persistence.createEntityManagerFactory("urgency-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();

			// Get a box and delete it:
			System.out.println("Urgency's boxes:");
			printBoxes();
			System.out.print("Choose a box to delete. Type it's ID:");
			BufferedReader readerBox = new BufferedReader(new InputStreamReader(System.in));
			int box_id = Integer.parseInt(readerBox.readLine());
			Query q2 = em.createNativeQuery("SELECT * FROM Boxes WHERE id = ?", Box.class);
			q2.setParameter(1, box_id);
		    Box box_removed = (Box) q2.getSingleResult();

			// Begin transaction
			em.getTransaction().begin();
			// Store the object
			em.remove(box_removed);
			// End transaction
			em.getTransaction().commit();
			
			// Delete triage:
			System.out.println("Urgency's triages:");
			printTriages();
			System.out.print("Choose a triage to delete. Type it's ID:");
			BufferedReader readerTriage = new BufferedReader(new InputStreamReader(System.in));
			int triage_id = Integer.parseInt(readerTriage.readLine());
			Query q3 = em.createNativeQuery("SELECT * FROM Triages WHERE id = ?", Triage.class);
			q3.setParameter(1, triage_id);
			Triage triage_removed = (Triage) q3.getSingleResult();

			em.getTransaction().begin();
			em.remove(triage_removed);
			em.getTransaction().commit();
			
			// Delete doctor:
			System.out.println("Urgency's doctors:");
			printDoctors();
			System.out.print("Choose a doctor to delete. Type it's ID:");
			BufferedReader readerDoctor = new BufferedReader(new InputStreamReader(System.in));
			int doctor_id = Integer.parseInt(readerDoctor.readLine());
			Query q6 = em.createNativeQuery("SELECT * FROM Doctors WHERE id = ?", Doctor.class);
			q6.setParameter(1, doctor_id);
			Doctor doctor_removed = (Doctor) q6.getSingleResult();

			em.getTransaction().begin();
			em.remove(triage_removed);
			em.getTransaction().commit();

			// Close the entity manager
			em.close();
		}


}
