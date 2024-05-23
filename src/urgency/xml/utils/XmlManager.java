package urgency.xml.utils;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;

public class XmlManager {
	
	private static EntityManager em;
	
	public XmlManager() {
		super();
	}

	public Boolean Java2Xml (Doctor doctor) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Doctor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE); //display
			
			File file = new File ("./xmls/"+doctor.getName()+".xml"); //we create a file object
			marshaller.marshal(doctor, file); //then we provide the doctor object and the file we want to save it in
			marshaller.marshal(doctor, System.out);
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean Xml2Java (String pathway) {
		JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		File file = new File(pathway);
		Doctor doctor = (Doctor) unmarshaller.unmarshal(file);
		System.out.println("Doctor:");
		System.out.println("Name: " + doctor.getName());
		System.out.println("Content: " + doctor.getSurname());
		System.out.println("Date: " + doctor.getid());
		List<Patient> patients = doctor.getPatients(); //we can also restore the report into the database
		for (Patient patient : patients) {
			System.out.println("Patient: " + patient.getName());
		}
		List<Box> boxes = doctor.getBoxes(); //we can also restore the report into the database
		for (Box box : boxes) {
			System.out.println("Boxes: " + box.getId());
		}

				// Store the report in the database
				// Create entity manager
				factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
				EntityManager em = factory.createEntityManager();
				em.getTransaction().begin();
				em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
				em.getTransaction().commit();

				// Create a transaction
				EntityTransaction tx1 = em.getTransaction();

				// Start transaction
				tx1.begin();

				// Persist
				// We assume the authors are not already in the database
				// In a real world, we should check if they already exist
				// and update them instead of inserting as new
				for (Employee employee : emps) {
					em.persist(employee); //these employees cannot be already inside the database because if not they cannot be inserted
				}
				em.persist(report);
				
				// End transaction
				tx1.commit();
			}
	}
