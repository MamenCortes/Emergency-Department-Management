package urgency.ui;


import java.awt.event.ActionEvent;

import javax.swing.JButton;

import urgency.ui.components.MenuTemplate;
import urgency.ui.components.MyButton;

public class RecepcionistMenu extends MenuTemplate{

	private static final long serialVersionUID = 6050014345831062858L;
	private JButton addPatientButton; 
	private JButton searchPatientButton; 
	private JButton logOutButton; 
	private Application appMenu; 

	public RecepcionistMenu(Application appMenu) {
		super();
		this.appMenu = appMenu; 
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addPatientButton) {
			appMenu.changeToAddPatient();
			
		}else if(e.getSource()==searchPatientButton) {
			appMenu.changeToAdmitPatient();
		}else if(e.getSource()==logOutButton) {
			appMenu.changeToUserLogIn(); 
		}
		
	}

}
