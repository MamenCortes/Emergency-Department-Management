package urgency.ui;

import java.util.ArrayList;

import javax.swing.JButton;

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
		addPatientButton = new JButton("Add Patient"); 
		searchPatientButton = new JButton("Search Patient"); 
		logOutButton = new JButton("Log Out");
		
		buttons.add(addPatientButton); 
		buttons.add(searchPatientButton);
		buttons.add(logOutButton); 
	}


}
