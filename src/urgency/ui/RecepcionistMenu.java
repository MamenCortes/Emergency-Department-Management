package urgency.ui;


import javax.swing.JButton;

import urgency.ui.components.Menu;
import urgency.ui.components.MyButton;

public class RecepcionistMenu extends Menu {

	private static final long serialVersionUID = 6050014345831062858L;
	private JButton addPatientButton; 
	private JButton searchPatientButton; 
	private JButton logOutButton; 

	public RecepcionistMenu() {
		super();
		addButtons(); 
		this.init();

	}
	
	private void addButtons() {
		addPatientButton = new MyButton("Add Patient"); 
		searchPatientButton = new MyButton("Search Patient"); 
		logOutButton = new MyButton("Log Out");
		
		buttons.add(addPatientButton); 
		buttons.add(searchPatientButton);
		buttons.add(logOutButton); 
	}


}
