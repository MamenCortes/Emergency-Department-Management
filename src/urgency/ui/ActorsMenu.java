package urgency.ui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import urgency.ui.components.MenuTemplate;
import urgency.ui.components.MyButton;

public class ActorsMenu extends MenuTemplate{
	
	private JButton doctorViewButton;
	private JButton recepcionistViewButton; 
	private JButton triageViewButton; 
	private JButton managerViewButton; 
	private Application appMenu; 

	private static final long serialVersionUID = -7723318712524263372L;

	public ActorsMenu(Application appMenu) {
		super();
		this.appMenu = appMenu; 
		addButtons(); 
		this.init();
	}
	
	private void addButtons() {
		doctorViewButton = new MyButton("Doctor View"); 
		recepcionistViewButton = new MyButton("Recepcionist View"); 
		triageViewButton = new MyButton("Triage View"); 
		managerViewButton = new MyButton("Manager View"); 
		
		buttons.add(doctorViewButton); 
		buttons.add(recepcionistViewButton);
		buttons.add(triageViewButton); 
		buttons.add(managerViewButton); 

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==doctorViewButton) {
			appMenu.changeToDoctorView(appMenu.conMan.getDocMan().getDoctor(1));
		}else if(e.getSource()==recepcionistViewButton) {
			appMenu.changeToRecepcionistMenu();
			
		}else if(e.getSource()==managerViewButton) {
			appMenu.changeToManagerMenu();
			
		}else if(e.getSource()==triageViewButton) {
			appMenu.changeToNurseView();
			
		}
		
	}
	
	

}
