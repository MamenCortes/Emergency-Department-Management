package sample.db.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import urgency.db.pojos.*;

public class JPARead {
	
	public static void main(String[] args) throws IOException {
		
		/*EntityManager em = Persistence.createEntityManagerFactory("urgency-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		//Search user by username:
		BufferedReader readerUser = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Write the user's username: ");
		String username = readerUser.readLine();
		System.out.println("Matching users:");
		Query q1 = em.createNativeQuery("SELECT * FROM users WHERE username LIKE ?", User.class);
		q1.setParameter(1, "%" + username + "%");
		List<User> users = (List<User>) q1.getResultList();
		// Print the users
		for (User u: users) {
			System.out.println(u);
		}
		
		//Get user:
		System.out.print("Write the user's id: ");
		int user_id = Integer.parseInt(readerUser.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM users WHERE id = ?", User.class);
		q2.setParameter(1, user_id);
		User user = (User) q2.getSingleResult();
		// Print the user:
		System.out.println(user);
		
		//Get role is already implemented in JPAUserManager.
		
		// Close the entity manager
				em.close();
		
		/*
		// Search in patients by surname
		BufferedReader readerPatient = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Write the patient's surname: ");
		String surname = readerPatient.readLine();
		System.out.println("Matching patients:");
		Query q1 = em.createNativeQuery("SELECT * FROM Patients WHERE surname LIKE ?", Patient.class);
		q1.setParameter(1, "%" + surname + "%");
		List<Patient> patients = (List<Patient>) q1.getResultList();
		// Print the patients
		for (Patient patient : patients) {
			System.out.println(patient);
		}
		
		// Get just one patient:
		// Only use this while looking by unique fields, if not, you could get duplicate results
		System.out.print("Write the patient's ID: ");
		int patient_id = Integer.parseInt(readerPatient.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Patient.class);
		q2.setParameter(1, patient_id);
		Patient patient = (Patient) q2.getSingleResult();
		// Print the patient:
		System.out.println(patient);
		//entiendo que faltaría hacer getPatientRecords en esta clase del Patient
		
		// Search in boxes by speciality:
		BufferedReader readerBox = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Write the box's speciality: ");
		Speciality speciality = new Speciality(readerBox.readLine());
		System.out.println("Matching boxes:");
		Query q3 = em.createNativeQuery("SELECT * FROM Boxes WHERE speciality LIKE ?", Box.class);
		q3.setParameter(1, "%" + speciality + "%");
		List<Box> boxes = (List<Box>) q3.getResultList();
		// Print the boxes
		for (Box b : boxes) {
			System.out.println(b);
		}

		// Get just one box:
		System.out.print("Write the box's ID: ");
		int box_id = Integer.parseInt(readerBox.readLine());
		Query q4 = em.createNativeQuery("SELECT * FROM Boxes WHERE id = ?", Box.class);
		q4.setParameter(1, box_id);
		Box b = (Box) q4.getSingleResult();
		// Print the box:
		System.out.println(b);
		// Faltaría implementar getPatientInBox

		// Specialities:
		BufferedReader readerSpecialities = new BufferedReader(new InputStreamReader(System.in));
		Query q5 = em.createNativeQuery("SELECT * FROM Specialities", Speciality.class);
		List<Speciality> specialities = (List<Speciality>) q5.getResultList();
		// Print the specialities
		for (Speciality s : specialities) {
			System.out.println(s);
		}
				
		// Search in triages:
		BufferedReader readerTriage = new BufferedReader(new InputStreamReader(System.in));
		Query q6 = em.createNativeQuery("SELECT * FROM Triages", Triage.class);
		List<Triage> triages = (List<Triage>) q6.getResultList();
		// Print the triages
		for (Triage t : triages) {
			System.out.println(t);
		}

		// Get just one triage:
		System.out.print("Write the triage's ID: ");
		int triage_id = Integer.parseInt(readerTriage.readLine());
		Query q7 = em.createNativeQuery("SELECT * FROM Patients WHERE id = ?", Triage.class);
		q7.setParameter(1, triage_id);
		Triage triage = (Triage) q7.getSingleResult();
		// Print the triage:
		System.out.println(triage);
		// faltaría hacer getPatientInTriage
		
		// Search in doctors by surname
		BufferedReader readerDoctor = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Write the doctor's surname: ");
		String surnameDoctor = readerDoctor.readLine();
		System.out.println("Matching doctors:");
		Query q8 = em.createNativeQuery("SELECT * FROM Doctors WHERE surname LIKE ?", Doctor.class);
		q8.setParameter(1, "%" + surnameDoctor + "%");
		List<Doctor> doctors = (List<Doctor>) q8.getResultList();
		// Print the doctors
		for (Doctor d: doctors) {
			System.out.println(d);
		}
		
		// Get doctors by speciality:
		System.out.print("Write the doctor's speciality: ");
		String specialityDoctor = readerDoctor.readLine();
		System.out.println("Matching doctors:");
		Query q9 = em.createNativeQuery("SELECT * FROM Doctors WHERE Speciality_type LIKE ?", Doctor.class);
		q9.setParameter(1, "%" + speciality + "%");
		List<Doctor> doctorsSpeciality = (List<Doctor>) q9.getResultList();
		// Print the doctors
		for (Doctor ds : doctorsSpeciality) {
			System.out.println(ds);
		}

		// Get just one doctor:
		System.out.print("Write the doctor's ID: ");
		int doctor_id = Integer.parseInt(readerDoctor.readLine());
		Query q10 = em.createNativeQuery("SELECT * FROM Doctors WHERE id = ?", Doctor.class);
		q10.setParameter(1, doctor_id);
		Doctor doctor = (Doctor) q10.getSingleResult();
		// Print the patient:
		System.out.println(doctor);
		*/
		
	}

}
