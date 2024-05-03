package urgency.ui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import urgency.ui.components.MenuTemplate;
import urgency.ui.components.MyButton;

public class ManagerMenu extends MenuTemplate {
	
	private JButton addDoctorButton;
	private JButton searchDoctorButton; 
	private JButton addSpecialityButton; 
	private JButton addRoomButton; 
	private JButton searchRoomButton; 
	private JButton generalViewButton; 
	private JButton logOutButton; 
	private Application appMenu; 

	private static final long serialVersionUID = 1L;

	public ManagerMenu(Application appMenu) {
		super();
		this.appMenu = appMenu; 
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addDoctorButton) {
			appMenu.changeToAddDoctor();
		}else if(e.getSource()==searchDoctorButton) {
			appMenu.changeToSearchDoctor();
			
		}else if(e.getSource()==addSpecialityButton) {
			appMenu.changeToAddSpeciality();
			
		}else if(e.getSource()==addRoomButton) {
			appMenu.changeToAddRoom();
			
		}else if(e.getSource()==searchRoomButton) {
			appMenu.changeToSearchRoom();
			
		}else if(e.getSource()==generalViewButton) {
			appMenu.changeToGeneralView();
			
		}else if(e.getSource()==logOutButton) {
			appMenu.changeToUserLogIn();
		}
		
	}

}
