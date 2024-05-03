package urgency.ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import urgency.db.jdbc.*;
import urgency.db.pojos.*;


public class Application extends JFrame{

	private static final long serialVersionUID = 1L;
	//JDBC Objects
	public ConnectionManager conMan;
	public JDBCPatientManager patientMan; 
	//public JDBCSpecialityManager specMan; 
	
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


	//TODO Change speciality.setSelectedItem() by speciality.getModel().setSelectedItem(boxSpeciality);
	//TODO Add Delete functionality in ModifyForms: falta modifyDoctor y admitPatient (implementar método update)
	//TODO Add Create functionality in AddForms: falta addDoctor
	//TODO Solve getBoxes method in Box 
	//TODO Create Triage Nurse View
	//TODO Create Doctor View
	//TODO Speciality no se muestra en el Box cell
	public Application() {
		conMan = new ConnectionManager(); 
		//patientMan = new JDBCPatientManager(conMan); 
		//specMan = new JDBCSpecialityManager(conMan);
		appPanels = new ArrayList<JPanel>(); 
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 436);
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
		
		
		modifyDoctor = new ModifyDoctor(null, this);
		appPanels.add(modifyDoctor);
		searchRoom = new SearchRoom(this); 
		appPanels.add(searchRoom); 
		modifyRoom = new ModifyRoom(this); 
		appPanels.add(modifyRoom);
		generalView = new GeneralView(this); 
		appPanels.add(generalView); 
		

		setContentPane(logInPanel);
		//conMan.closeConnection();
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        conMan.closeConnection();
		    }
		});
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
	
	public void changeToModifyDoctor(Doctor doctor) {
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
		hideAllPanels();
		generalView.setVisible(true);
		this.setContentPane(generalView); 
	}

}
