package urgency.xml.utils;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import urgency.db.jdbc.ConnectionManager;
import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;
import urgency.db.pojos.Speciality;
import urgency.ui.Application;

public class XmlManager {
	
	ConnectionManager conMan; 
	
	public XmlManager(ConnectionManager conMan) {
		super();
		this.conMan = conMan; 
	}

	public Boolean Java2Xml (Doctor doctor) {
		JAXBContext jaxbContext;
		List<Box> boxes = conMan.getBoxManager().getDoctorBoxes(doctor);
		doctor.setBoxes(boxes);
		for (Box box : boxes) {
			List<Patient> patients = conMan.getBoxManager().getPatientsFromBox(box); 
			System.out.println("Patients: "+patients);
			box.setPatientList(patients);
			
		}
		
		try {
			jaxbContext = JAXBContext.newInstance(Doctor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE); //display
			String fileName = doctor.getName()+doctor.getSurname(); 
			File file = new File ("./xmls/"+fileName+".xml"); //we create a file object
			marshaller.marshal(doctor, file); //then we provide the doctor object and the file we want to save it in
			marshaller.marshal(doctor, System.out);
			
			simpleTransform("./xmls/"+fileName+".xml", fileName);
			
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Doctor Xml2Java (String pathway) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File file = new File(pathway);
			if (!file.exists() || !file.isFile()) {
				return null;
			}else {
				Doctor doctor = (Doctor) unmarshaller.unmarshal(file);
				System.out.println("New doctor: "+doctor);
				return doctor;
			}
			
		} catch(JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean simpleTransform(String sourcePath, String fileName) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File("./xmls/Doctor-Style.xslt")));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File("./xmls/"+fileName+".html")));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager();
		XmlManager xmlman = new XmlManager(conMan);
		Doctor d5 = new Doctor(2,"Alex", "Karev", new Speciality("Pediatrics"), true, "alex.karev@hospital.com");
		xmlman.Java2Xml(d5);
	}
}
