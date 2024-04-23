package urgency.ui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerMenu extends Menu {
	
	private JButton addDoctorButton; 
	private JButton searchDoctorButton; 
	private JButton addSpecialityButton; 
	private JButton addRoomButton; 
	private JButton searchRoomButton; 
	private JButton generalViewButton; 
	private JButton logOutButton; 

	private static final long serialVersionUID = 1L;

	public ManagerMenu() {
		super();
		addButtons(); 
		this.init();
	}
	
	private void addButtons() {
		addDoctorButton = new JButton("Add Doctor"); 
		searchDoctorButton = new JButton("Search Doctor"); 
		addSpecialityButton = new JButton("Add Speciality"); 
		addRoomButton = new JButton("Add Room"); 
		searchRoomButton = new JButton("Search Room"); 
		generalViewButton = new JButton("General View"); 
		logOutButton = new JButton("Log Out"); 
		
		
		buttons.add(addDoctorButton); 
		buttons.add(searchDoctorButton);
		buttons.add(addSpecialityButton); 
		buttons.add(addRoomButton); 
		buttons.add(searchRoomButton); 
		buttons.add(generalViewButton); 
		buttons.add(logOutButton); 
	}

}
