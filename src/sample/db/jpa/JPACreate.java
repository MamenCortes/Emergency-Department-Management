package sample.db.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import urgency.db.pojos.*;
public class JPACreate {

		public static void main(String[] args) throws IOException {
			//DIVIDIRIA TODO ESTE CODIGO EN METODOS APARTE EN VEZ DE TENERLO EN EL MAIN
			
			EntityManager em = Persistence.createEntityManagerFactory("urgency-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
			
			//Creation of patient:
			System.out.println("Please, input the patient info:");
			BufferedReader readerPatient = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = readerPatient.readLine();
			System.out.print("Surname: ");
			String surname = readerPatient.readLine();
			System.out.print("Weight: ");
			float weight = Float.parseFloat(readerPatient.readLine());
			System.out.print("Height: ");
			float height = Float.parseFloat(readerPatient.readLine());
			System.out.print("Status: ");
			String status = readerPatient.readLine();
			System.out.print("Urgency: ");
			Integer urgency= Integer.parseInt(readerPatient.readLine());
			System.out.print("Sex: ");
			String sex = readerPatient.readLine();
			System.out.print("Birthdate: ");
			Date birthdate = Date.valueOf(readerPatient.readLine());
						
			Patient patient = new Patient(name, surname, weight, height, status, urgency, sex, birthdate);
			
			em.getTransaction().begin();
			// Store the object:
			em.persist(patient);
			// End transaction
			em.getTransaction().commit();
			
			//Creation of box:
			System.out.println("Please, input the box info:");
			BufferedReader readerBox = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Available: ");
			Boolean available = Boolean.parseBoolean(readerBox.readLine());
			System.out.print("Speciality: ");
			Speciality speciality = new Speciality(readerBox.readLine());
						
			Box box = new Box(available, speciality);
			
			em.getTransaction().begin();
			// Store the object:
			em.persist(box);
			// End transaction
			em.getTransaction().commit();
			
			//Creation of doctor:
			System.out.println("Please, input the doctor info:");
			BufferedReader readerDoctor = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String nameDoctor = readerDoctor.readLine();
			System.out.print("Surname: ");
			String surnameDoctor = readerDoctor.readLine();
			System.out.print("Speciality: ");
			Speciality specialityDoctor = new Speciality(readerBox.readLine());
			System.out.print("In_Box: ");
			Boolean inbox = Boolean.parseBoolean(readerBox.readLine());
				
			Doctor doctor = new Doctor(nameDoctor, surnameDoctor, specialityDoctor, inbox);
			
			em.getTransaction().begin();
			// Store the object:
			em.persist(doctor);
			// End transaction
			em.getTransaction().commit();
			
			//Creation of doctor:
			System.out.println("Please, input the doctor info:");
			BufferedReader readerTriage = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Available: ");
			Boolean availableTriage = Boolean.parseBoolean(readerTriage.readLine());
			
			Triage triage = new Triage(available);
			
			em.getTransaction().begin();
			// Store the object:
			em.persist(triage);
			// End transaction
			em.getTransaction().commit();
			
			// Finally, we close the entity manager:
			em.close();
		}

	}

