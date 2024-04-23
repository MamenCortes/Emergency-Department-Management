package urgency.ui;

import javax.swing.JButton;
import urgency.ui.components.Menu;
import urgency.ui.components.MyButton;

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
		addDoctorButton = new MyButton("Add Doctor"); 
		searchDoctorButton = new MyButton("Search Doctor"); 
		addSpecialityButton = new MyButton("Add Speciality"); 
		addRoomButton = new MyButton("Add Room"); 
		searchRoomButton = new MyButton("Search Room"); 
		generalViewButton = new MyButton("General View"); 
		logOutButton = new MyButton("Log Out"); 
		
		//MyButton b2 = new MyButton("Button", "/urgency/ui/icon/mail.png"); 
		
		buttons.add(addDoctorButton); 
		buttons.add(searchDoctorButton);
		buttons.add(addSpecialityButton); 
		buttons.add(addRoomButton); 
		buttons.add(searchRoomButton); 
		buttons.add(generalViewButton); 
		buttons.add(logOutButton); 
	}

}
