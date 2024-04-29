package urgency.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;

public class AddRoom extends FormTemplate {

	private static final long serialVersionUID = -3380973585111621415L;
	private Application appMain; 
	private List<String> specialities; 
	private MyComboBox<String> roomType; 

	public AddRoom(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		
		speciality = new MyComboBox<String>(); 
		specialities = appMain.specMan.getSpecialities(); 
		this.titleText += "Add Room"; 
		
		roomType = new MyComboBox<String>(); 
		
		form1 = new FormPanel("Add Room", roomType, speciality, specialities); 
		initPatientForm(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			appMain.changeToManagerMenu();
		}else if(e.getSource() == applyChanges) {
			appMain.changeToManagerMenu(); 
			//showErrorMessage(errorMessage); 
		}
	}
	
}
