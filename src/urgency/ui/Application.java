package urgency.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import urgency.db.jdbc.*;
import urgency.db.pojos.*;
import urgency.ui.components.*;

public class Application extends JFrame{

	private static final long serialVersionUID = 1L;
	//JDBC Objects
	public ConnectionManager conMan;
	public JDBCPatientManager patientMan; 
	public JDBCSpecialityManager specMan; 
	
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
	private SearchTemplate searchTemplate; 


	/**
	 * Create the frame.
	 */
	public Application() {
		conMan = new ConnectionManager(); 
		patientMan = new JDBCPatientManager(conMan); 
		specMan = new JDBCSpecialityManager(conMan);
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
		Patient patient1 = patientMan.getPatient(3); 
		System.out.println(patient1);
		admitPatient = new AdmitPatient(patient1, this); 
		appPanels.add(admitPatient); 
		
		addDoctor = new AddDoctor(this);
		appPanels.add(addDoctor); 
		addSpeciality = new AddSpeciality(this); 
		appPanels.add(addSpeciality); 
		addRoom = new AddRoom(this); 
		appPanels.add(addRoom); 
		
		searchTemplate = new SearchTemplate(this); 
		

		setContentPane(searchTemplate);
		conMan.closeConnection();
	}
	
	public void changeToRecepcionistMenu() {
		
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
		recepcionistMenu.setVisible(true);
		this.setContentPane(recepcionistMenu); 
	}

	public void changeToAddPatient() {
		
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
		addPatient.setVisible(true);
		this.setContentPane(addPatient); 
	}
	
	public void changeToAdmitPatient() {
		
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
		admitPatient.setVisible(true);
		this.setContentPane(admitPatient); 
	}
	
	public void changeToUserLogIn() {
		
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
		logInPanel.setVisible(true);
		this.setContentPane(logInPanel); 
	}
	
	public void changeToManagerMenu() {
		
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
		managerMenu.setVisible(true);
		this.setContentPane(managerMenu); 
	}

	public void changeToAddDoctor() {
		
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
		addDoctor.setVisible(true);
		this.setContentPane(addDoctor); 
	}
	
	public void changeToAddSpeciality() {
		
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
		addSpeciality.setVisible(true);
		this.setContentPane(addSpeciality); 
	}
	
	public void changeToAddRoom() {
		
		for (JPanel jPanel : appPanels) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
			}
		}
		addRoom.setVisible(true);
		this.setContentPane(addRoom); 
	}


}
