package urgency.ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import urgency.db.jdbc.*;
import urgency.db.jpa.JPARoleManager;
import urgency.db.jpa.JPAUserManager;
import urgency.db.pojos.*;


public class Application extends JFrame{

	private static final long serialVersionUID = 1L;
	//JDBC Objects
	public ConnectionManager conMan;
	//public ConnectionManagerJPA userMan; 
		public JPAUserManager jpaUserMan;
		public JPARoleManager jpaRoleMan; //necesitas esto para seleccionar cada rol del usuario
		//para q se muestren las ventanas correspondientes de cada rol
	//public XmlManager xmlMan; 
	
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
	
	//For tests
	private ActorsMenu actorsMenu; 
	private User user; 

	//TODO add UserName/email in Doctor to implement a fake foreign key with JPA
	//TODO create method getDoctorByUserName(User user); 
	//TODO change addDoctor to create and check user
	//TODO check setUser Method
	//TODO add button in doctor to export info to XML file 
	//TODO add button in manager to add doctor from XML eligiendo la ruta a un archivo 
	//TODO create methods in managers to insert random data at the begining


	public Application() {
		conMan = new ConnectionManager();
		//userMan = new ConnectionManagerJPA();
		jpaUserMan = new JPAUserManager();
		appPanels = new ArrayList<JPanel>(); 
		//xmlMan = new XmlManager(); 
		
		//Mamen hay que validar las contraseñas, tengo este metodo tipo que es mejor introducrilo en la interfaz 
		//grafica que tenerlo en el pojo de User:
		
		/*
		 * private void validatePassword(String password) throws IllegalArgumentException {
		boolean passwordVacia = (Objects.isNull(password)) || password.isEmpty();
		boolean goodPassword=false;
		if(passwordVacia || password.length() < 8) {
			for(int i=0; i<8; i++) {
			if(Character.isDigit(password.charAt(i))) {
			goodPassword = true;
			}if(i == 8 && !goodPassword) {
				throw new IllegalArgumentException("The password must have at least one number as well as characters with a lenght of 8 characters.");
			}
		 throw new IllegalArgumentException("Password is empty");
		 }
	   }
	 }
		 * 
		 */
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 436);
		
		//For tests
		actorsMenu = new ActorsMenu(this); 
		appPanels.add(actorsMenu); 
		////////////////////
		
		logInPanel = new UserLogIn(this); 
		appPanels.add(logInPanel); 
		logInPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		recepcionistMenu = new RecepcionistMenu(this); 
		appPanels.add(recepcionistMenu); 
		managerMenu = new ManagerMenu(this);
		appPanels.add(managerMenu);
		addPatient = new AddPatient(this); 
		appPanels.add(addPatient); 
		
		//Example patient
		admitPatient = new AdmitPatient(this); 
		appPanels.add(admitPatient); 
		
		addDoctor = new AddDoctor(this);
		appPanels.add(addDoctor); 
		addSpeciality = new AddSpeciality(this); 
		appPanels.add(addSpeciality); 
		addRoom = new AddRoom(this); 
		appPanels.add(addRoom); 
		
		searchPatient = new SearchPatients(this); 
		appPanels.add(searchPatient); 
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
		nurseView = new NurseView(this); 
		appPanels.add(nurseView); 
		nurseView = new NurseView(this); 
		appPanels.add(nurseView); 
		patientForm = new PatientForm(this); 
		appPanels.add(patientForm); 
		doctorView = new DoctorView(this); 
		appPanels.add(doctorView);		

		setContentPane(actorsMenu);
		//conMan.closeConnection();
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        conMan.closeConnection();
		    }
		});
	}
	
	public void setUser(User user) {
		//TODO Also add here the initialization of only the panels to use. 
		this.user = user; 
		
		Role role = user.getRole(); 
		switch (role.getName()) {
		case  "Doctor":{
			//TODO create method getDoctorByUserName(User user); 
			Doctor doctor = null; 
			//doctor = conMan.getDocMan().getDoctorByUserName(user); 
			changeToDoctorView(doctor);
		} case "Recepcionist":changeToRecepcionistMenu(); break; 
		case "Nurse": changeToNurseView();break; 
		case "Manager":changeToManagerMenu(); break;
		default: changeToUserLogIn(); break;
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
		/*
		hideAllPanels();
		logInPanel.setVisible(true);
		this.setContentPane(logInPanel);*/
		hideAllPanels();
		actorsMenu.setVisible(true);
		this.setContentPane(actorsMenu);
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
