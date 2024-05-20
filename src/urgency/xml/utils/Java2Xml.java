package urgency.xml.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import urgency.db.pojos.Doctor;

public class Java2Xml { //THIS IS NOT HOW WE SHOULD WRITE THIS CLASS XMLMANAGER 

	// Put entity manager and buffered reader here so it can be used
	// in several methods
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
	private static void printReports() {
		Query q1 = em.createNativeQuery("SELECT * FROM Doctors", Doctor.class);
		List<Doctor> reps = (List<Doctor>) q1.getResultList();
		// Print the departments
		for (Doctor rep : reps) {
			System.out.println(rep);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// Get the entity manager
		// Note that we are using the class' entity manager
		em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin(); //we have an entity manager here
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		// Create the JAXBContext //it is only useful to create from the root element
		JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class); // we have to create it 
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller(); //to get a marshaler object
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE); //so the xml looks pretty
		
		// Choose the report to turn into an XML
		// Choose his new department
		printReports(); 
		System.out.print("Choose a report to turn into an XML file:");
		int rep_id = Integer.parseInt(reader.readLine()); //types the id
		Query q2 = em.createNativeQuery("SELECT * FROM Doctors WHERE id = ?", Doctor.class);
		q2.setParameter(1, rep_id);
		Doctor report = (Doctor) q2.getSingleResult();
		
		// Use the Marshaller to marshal the Java object to a file
		File file = new File("./xmls/Sample-Report.xml"); //we create a file object
		marshaller.marshal(report, file); //then we provide the report object anf the file we want to save it in
		// Printout
		marshaller.marshal(report, System.out); //to print it out

	}
}

