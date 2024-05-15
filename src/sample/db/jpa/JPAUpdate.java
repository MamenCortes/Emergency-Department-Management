package sample.db.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import urgency.db.pojos.*;

public class JPAUpdate {
	//los updates de cada clase base
	
		private static EntityManager em;
		
		private static void printUsers() {
			Query q1 = em.createNativeQuery("SELECT * FROM users", User.class);
			List<User> users = (List<User>) q1.getResultList();
			// Print the users:
			for (User u : users) {
				System.out.println(u);
			}
		}
		
		public static void main(String args[]) throws IOException {
			
			/*System.out.println("Urgency's users:");
			printUsers();
			System.out.print("Choose a user to change its username. Type it's id:");
			BufferedReader readerUser = new BufferedReader(new InputStreamReader(System.in));
			int user_id = Integer.parseInt(readerUser.readLine());
			Query q2 = em.createNativeQuery("SELECT * FROM users WHERE id = ?", User.class);
			q2.setParameter(1, user_id);
			User user = (User) q2.getSingleResult();
			System.out.print("Type it's new username:");
			String newUsername = readerUser.readLine();
			
			em.getTransaction().begin();
			user.setUsername(newUsername);
			em.getTransaction().commit();
			
			System.out.println("Urgency's users:");
			printUsers();
			System.out.print("Choose a user to change its password. Type it's id:");
			BufferedReader readerUser2 = new BufferedReader(new InputStreamReader(System.in));
			int user_id2 = Integer.parseInt(readerUser2.readLine());
			Query q3 = em.createNativeQuery("SELECT * FROM users WHERE id = ?", User.class);
			q3.setParameter(1, user_id2);
			User user2 = (User) q3.getSingleResult();
			System.out.print("Type it's new password:");
			String newPassword = readerUser2.readLine();
			
			em.getTransaction().begin();
			user2.setPassword(newPassword);
			em.getTransaction().commit();
			
			System.out.println("Urgency's users:");
			printUsers();
			System.out.print("Choose a user to change its role. Type it's id:");
			BufferedReader readerUser3 = new BufferedReader(new InputStreamReader(System.in));
			int user_id3 = Integer.parseInt(readerUser3.readLine());
			Query q4 = em.createNativeQuery("SELECT * FROM users WHERE id = ?", User.class);
			q4.setParameter(1, user_id3);
			User user3 = (User) q4.getSingleResult();
			System.out.print("Type it's new role:");
			Role newRole = new Role(readerUser3.readLine());
			
			em.getTransaction().begin();
			user.setRole(newRole);
			em.getTransaction().commit();
			
		
			
		   // Close the entity manager
		   em.close();
		
		
		 }

		}
		/*
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
	
			//DUDA, NO CAMBIO EL ID NO?

			System.out.println("Urgency's patients:");
			printPatients();
			System.out.print("Choose a patient to change its name. Type it's ID:");
			BufferedReader readerPatient = new BufferedReader(new InputStreamReader(System.in));
			int patient_id = Integer.parseInt(readerPatient.readLine());
			Query q5 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
			q5.setParameter(1, patient_id);
			Patient patient = (Patient) q5.getSingleResult();
			System.out.print("Type it's new name:");
			String newName = readerPatient.readLine();
			
			em.getTransaction().begin();
			patient.setName(newName);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printPatients();
			System.out.print("Choose a patient to change its surname. Type it's ID:");
			BufferedReader readerPatient2 = new BufferedReader(new InputStreamReader(System.in));
			int patient_id2 = Integer.parseInt(readerPatient2.readLine());
			Query q6 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
			q6.setParameter(1, patient_id);
			Patient patient2 = (Patient) q6.getSingleResult();
			System.out.print("Type it's new surname:");
			String newSurname = readerPatient2.readLine();
			
			em.getTransaction().begin();
			patient2.setSurname(newSurname);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printPatients();
			System.out.print("Choose a patient to change its weight. Type it's ID:");
			BufferedReader readerPatient3 = new BufferedReader(new InputStreamReader(System.in));
			int patient_id3 = Integer.parseInt(readerPatient3.readLine());
			Query q7 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
			q7.setParameter(1, patient_id3);
			Patient patient3 = (Patient) q7.getSingleResult();
			System.out.print("Type it's new weight:");
			float newWeight = Float.parseFloat(readerPatient3.readLine());
			
			em.getTransaction().begin();
			patient3.setWeight(newWeight);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printPatients();
			System.out.print("Choose a patient to change its height. Type it's ID:");
			BufferedReader readerPatient4 = new BufferedReader(new InputStreamReader(System.in));
			int patient_id4 = Integer.parseInt(readerPatient4.readLine());
			Query q8 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
			q8.setParameter(1, patient_id4);
			Patient patient4 = (Patient) q8.getSingleResult();
			System.out.print("Type it's new height:");
			float newHeight = Float.parseFloat(readerPatient4.readLine());
			
			em.getTransaction().begin();
			patient4.setHeight(newHeight);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printPatients();
			System.out.print("Choose a patient to change its status. Type it's ID:");
			BufferedReader readerPatient5 = new BufferedReader(new InputStreamReader(System.in));
			int patient_id5 = Integer.parseInt(readerPatient5.readLine());
			Query q9 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
			q9.setParameter(1, patient_id5);
			Patient patient5 = (Patient) q9.getSingleResult();
			System.out.print("Type it's new status:");
			String newStatus = readerPatient5.readLine();
			
			em.getTransaction().begin();
			patient5.setStatus(newStatus);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printPatients();
			System.out.print("Choose a patient to change its urgency. Type it's ID:");
			BufferedReader readerPatient6 = new BufferedReader(new InputStreamReader(System.in));
			int patient_id6 = Integer.parseInt(readerPatient6.readLine());
			Query q10 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
			q10.setParameter(1, patient_id6);
			Patient patient6 = (Patient) q10.getSingleResult();
			System.out.print("Type it's new urgency:");
			int newUrgency = Integer.parseInt(readerPatient6.readLine());
			
			em.getTransaction().begin();
			patient6.setUrgency(newUrgency);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printPatients();
			System.out.print("Choose a patient to change its sex. Type it's ID:");
			BufferedReader readerPatient7 = new BufferedReader(new InputStreamReader(System.in));
			int patient_id7 = Integer.parseInt(readerPatient7.readLine());
			Query q11 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
			q11.setParameter(1, patient_id7);
			Patient patient7 = (Patient) q11.getSingleResult();
			System.out.print("Type it's new sex:");
			String newSex = readerPatient7.readLine();
			
			em.getTransaction().begin();
			patient7.setSex(newSex);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printPatients();
			System.out.print("Choose a patient to change its birthdate. Type it's ID:");
			BufferedReader readerPatient8 = new BufferedReader(new InputStreamReader(System.in));
			int patient_id8 = Integer.parseInt(readerPatient8.readLine());
			Query q12 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
			q12.setParameter(1, patient_id8);
			Patient patient8 = (Patient) q12.getSingleResult();
			System.out.print("Type it's new birthdate:");
			Date newBirthdate = Date.valueOf(readerPatient8.readLine());
			
			System.out.println("Urgency's triages");
			printTriages();
			System.out.print("Choose a triage to change its availability. Type it's ID:");
			BufferedReader readerTriage = new BufferedReader(new InputStreamReader(System.in));
			int triage_id = Integer.parseInt(readerTriage.readLine());
			Query q13 = em.createNativeQuery("SELECT * FROM Triages WHERE id = ?", Triage.class);
			q13.setParameter(1, triage_id);
			Triage triage = (Triage) q13.getSingleResult();
			System.out.print("Type it's new availability:");
			Boolean av = Boolean.parseBoolean(readerTriage.readLine());
			
			em.getTransaction().begin();
			triage.setAvailable(av);
			em.getTransaction().commit();
			
			System.out.println("Urgency's doctors:");
			printDoctors();
			System.out.print("Choose a doctor to change its name. Type it's ID:");
			BufferedReader readerDoctor = new BufferedReader(new InputStreamReader(System.in));
			int doctor_id = Integer.parseInt(readerDoctor.readLine());
			Query q14 = em.createNativeQuery("SELECT * FROM Doctors WHERE id = ?", Doctor.class);
			q14.setParameter(1, doctor_id);
			Doctor doctor = (Doctor) q14.getSingleResult();
			System.out.print("Type it's new name:");
			String newNameDoctor = readerDoctor.readLine();
			
			em.getTransaction().begin();
			doctor.setName(newNameDoctor);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printDoctors();
			System.out.print("Choose a doctor to change its surname. Type it's ID:");
			BufferedReader readerDoctor2 = new BufferedReader(new InputStreamReader(System.in));
			int doctor_id2 = Integer.parseInt(readerDoctor2.readLine());
			Query q15 = em.createNativeQuery("SELECT * FROM Doctors WHERE id = ?", Doctor.class);
			q15.setParameter(1, doctor_id2);
			Doctor doctor2 = (Doctor) q15.getSingleResult();
			System.out.print("Type it's new surname:");
			String newSurnameDoctor = readerDoctor2.readLine();
			
			em.getTransaction().begin();
			doctor2.setSurname(newSurnameDoctor);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printDoctors();
			System.out.print("Choose a doctor to change its speciality_type. Type it's ID:");
			BufferedReader readerDoctor3 = new BufferedReader(new InputStreamReader(System.in));
			int doctor_id3 = Integer.parseInt(readerDoctor3.readLine());
			Query q16 = em.createNativeQuery("SELECT * FROM Doctors WHERE id = ?", Doctor.class);
			q16.setParameter(1, doctor_id3);
			Doctor doctor3 = (Doctor) q16.getSingleResult();
			System.out.print("Type it's new speciality type:");
			Speciality speciality_type = new Speciality(readerDoctor3.readLine());
			
			em.getTransaction().begin();
			doctor3.setSpeciality_type(speciality_type);
			em.getTransaction().commit();
			
			System.out.println("Urgency's patients:");
			printDoctors();
			System.out.print("Choose a patient to change its in_box. Type it's ID:");
			BufferedReader readerDoctor4 = new BufferedReader(new InputStreamReader(System.in));
			int doctor_id4 = Integer.parseInt(readerDoctor4.readLine());
			Query q17 = em.createNativeQuery("SELECT * FROM Doctors WHERE id = ?", Doctor.class);
			q17.setParameter(1, doctor_id4);
			Doctor doctor4 = (Doctor) q17.getSingleResult();
			System.out.print("Type it's new inbox value:");
			Boolean inbox = Boolean.parseBoolean(readerDoctor4.readLine());
			
			em.getTransaction().begin();
			doctor4.setIn_box(inbox);
			em.getTransaction().commit();
			*/
			


