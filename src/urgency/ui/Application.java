package urgency.ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import urgency.db.jdbc.*;
import urgency.db.jpa.JPARoleManager;
import urgency.db.jpa.JPAUserManager;
import urgency.db.pojos.*;
import urgency.xml.utils.XmlManager;


public class Application extends JFrame{
	
	public static void main(String[] args) {
		Application app = new Application(); 
		app.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	
	//Manager Objects
	public ConnectionManager conMan;
	public JPAUserManager jpaUserMan;
	public JPARoleManager jpaRoleMan; 
	public XmlManager xmlMan; 
	private User user; 
	
	//UI Panels
	private ArrayList<JPanel> appPanels; 
	private UserLogIn logInPanel;
	private RecepcionistMenu recepcionistMenu; 
	private ManagerMenu managerMenu; 
	private AddPatient addPatient; 
	private AdmitPatient admitPatient; 
	private AddDoctor addDoctor; 
	private AddSpeciality addSpeciality; 
	private AddRoom addRoom;
	private SearchPatients searchPatient; 
	private SearchDoctor searchDoctor; 
	private ModifyDoctor modifyDoctor; 
	private SearchRoom searchRoom;
	private ModifyRoom modifyRoom; 
	private GeneralView generalView; 
	private NurseView nurseView; 
	private PatientForm patientForm; 
	private DoctorView doctorView; 


	public Application() {
		conMan = new ConnectionManager();
		jpaUserMan = conMan.getUserMan(); 
		jpaRoleMan = conMan.getRoleMan();  
		appPanels = new ArrayList<JPanel>(); 
		xmlMan = conMan.getXmlMan(); 
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 436);
		
		logInPanel = new UserLogIn(this); 
		appPanels.add(logInPanel); 
		logInPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		patientForm = new PatientForm(this); 
		appPanels.add(patientForm); 
			

		setContentPane(logInPanel);
		//conMan.closeConnection();
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        conMan.closeConnection();
		    }
		});
	}
	

	
	public void setUser(User user) {
		this.user = user; 
		Role role = user.getRole(); 
		System.out.println(role);
		switch (role.getName()) {
		case  "Doctor":{
			//Initialize panels
			if (doctorView == null) {
				doctorView = new DoctorView(this); 
				appPanels.add(doctorView);
				System.out.println("Doctor Panels initialized");
			}
				
			
			Doctor doctor = conMan.getDocMan().getDoctorByEmail(user.getEmail()); 
			changeToDoctorView(doctor);
			System.out.println("Changed to Doctor View");
			break; 
		} case "Recepcionist": {
			//Initialize panels
			if(recepcionistMenu == null) {
				addPatient = new AddPatient(this); 
				appPanels.add(addPatient); 
				searchPatient = new SearchPatients(this); 
				appPanels.add(searchPatient);
				admitPatient = new AdmitPatient(this); 
				appPanels.add(admitPatient); 
				recepcionistMenu = new RecepcionistMenu(this); 
				appPanels.add(recepcionistMenu); 
				System.out.println("Recpecionist panels initialized");
			}

			
			changeToRecepcionistMenu();
			System.out.println("Changed to Recepcionist View");
			break; }
		case "Nurse": { 
			//Init panels
			if(nurseView == null) {
				nurseView = new NurseView(this); 
				appPanels.add(nurseView); 
				System.out.println("Nurse panels initialized");
			}

			changeToNurseView();
			break; 
		}
		case "Manager": {
			//Initialize panels 
			if(managerMenu == null) {
				managerMenu = new ManagerMenu(this);
				appPanels.add(managerMenu);
				addDoctor = new AddDoctor(this);
				appPanels.add(addDoctor); 
				addSpeciality = new AddSpeciality(this); 
				appPanels.add(addSpeciality); 
				addRoom = new AddRoom(this); 
				appPanels.add(addRoom); 
				searchDoctor = new SearchDoctor(this); 
				appPanels.add(searchDoctor); 
				modifyDoctor = new ModifyDoctor(new DoctorBox(), this);
				appPanels.add(modifyDoctor);
				searchRoom = new SearchRoom(this); 
				appPanels.add(searchRoom); 
				modifyRoom = new ModifyRoom(this); 
				appPanels.add(modifyRoom);
				generalView = new GeneralView(this); 
				appPanels.add(generalView); 
				System.out.println("Manager Menus initialized");
			}
			changeToManagerMenu(); 
			break;}
		default: {changeToUserLogIn(); break;}
		}
	}
	
	private void hideAllPanels() {
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
	}
	
	public void changeToRecepcionistMenu() {
		hideAllPanels();
		recepcionistMenu.setVisible(true);
		this.setContentPane(recepcionistMenu); 
	}

	public void changeToAddPatient() {
		hideAllPanels();
		addPatient.setVisible(true);
		this.setContentPane(addPatient); 
	}
	
	public void changeToAdmitPatient(Patient patient) {
		//admitPatient = new AdmitPatient(patient, this); 
		admitPatient.updatePanelWith(patient);
		hideAllPanels();
		admitPatient.setVisible(true);
		this.setContentPane(admitPatient); 
	}
	
	public void changeToUserLogIn() {
		hideAllPanels();
		logInPanel.setVisible(true);
		this.setContentPane(logInPanel);
	}
	
	public void changeToManagerMenu() {
		hideAllPanels();
		managerMenu.setVisible(true);
		this.setContentPane(managerMenu); 
	}

	public void changeToAddDoctor() {
		hideAllPanels();
		addDoctor.setVisible(true);
		this.setContentPane(addDoctor); 
	}
	
	public void changeToAddSpeciality() {
		hideAllPanels();
		addSpeciality.setVisible(true);
		this.setContentPane(addSpeciality); 
	}
	
	public void changeToAddRoom() {
		hideAllPanels();
		addRoom.setVisible(true);
		this.setContentPane(addRoom); 
	}
	
	public void changeToSearchPatient() {
		hideAllPanels();
		searchPatient.setVisible(true);
		this.setContentPane(searchPatient); 
	}
	
	public void changeToSearchDoctor() {
		hideAllPanels();
		searchDoctor.setVisible(true);
		this.setContentPane(searchDoctor); 
	}
	
	public void changeToModifyDoctor(DoctorBox doctor) {
		modifyDoctor.updatePanelWith(doctor);
		hideAllPanels();
		modifyDoctor.setVisible(true);
		this.setContentPane(modifyDoctor); 
	}
	
	public void changeToSearchRoom() {
		hideAllPanels();
		searchRoom.setVisible(true);
		this.setContentPane(searchRoom); 
	}
	
	public void changeToModifyRoom(Box box) {
		modifyRoom.showRoomData(box);
		hideAllPanels();
		modifyRoom.setVisible(true);
		this.setContentPane(modifyRoom); 
	}
	public void changeToModifyRoom(Triage triage) {
		modifyRoom.showRoomData(triage);
		hideAllPanels();
		modifyRoom.setVisible(true);
		this.setContentPane(modifyRoom); 
	}
	public void changeToGeneralView() {
		generalView.updateView();
		hideAllPanels();
		generalView.setVisible(true);
		this.setContentPane(generalView); 
	}
	public void changeToNurseView() {
		nurseView.updateTriageOptions(); 
		hideAllPanels();
		nurseView.setVisible(true);
		this.setContentPane(nurseView); 
	}
	
	public void changeToPreviousNurseView() {
		hideAllPanels();
		nurseView.setVisible(true);
		this.setContentPane(nurseView); 
	}
	public void changeToPatientNurseForm(Patient patient) {
		patientForm.patientNurseForm(patient); 
		hideAllPanels();
		patientForm.setVisible(true);
		this.setContentPane(patientForm); 
	}
	public void changeToPatientDoctorFor(PatientBox patientBox) {
		patientForm.patientDoctorForm(patientBox); 
		hideAllPanels();
		patientForm.setVisible(true);
		this.setContentPane(patientForm); 
	}
	public void changeToDoctorView(Doctor doctor) {
		doctorView.updateDoctorPanel(doctor);
		hideAllPanels();
		doctorView.setVisible(true);
		this.setContentPane(doctorView); 
	}
	public void changeToDoctorView() {
		hideAllPanels();
		doctorView.setVisible(true);
		this.setContentPane(doctorView); 
	}
	
	
	

}
