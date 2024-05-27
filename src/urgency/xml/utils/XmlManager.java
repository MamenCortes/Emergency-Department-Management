package urgency.xml.utils;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import urgency.db.jdbc.ConnectionManager;
import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;

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
			
			File file = new File ("./xmls/"+doctor.getName()+doctor.getSurname()+".xml"); //we create a file object
			marshaller.marshal(doctor, file); //then we provide the doctor object and the file we want to save it in
			marshaller.marshal(doctor, System.out);
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
}
