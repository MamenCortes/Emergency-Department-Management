package urgency.ui;

import java.awt.event.ActionEvent;

import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;

public class AddSpeciality extends FormTemplate {

	private static final long serialVersionUID = -6615615275946952805L;
	private Application appMain; 

	public AddSpeciality(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain;
		this.titleText += "Add Speciality"; 
		

		MyTextField speciality = new MyTextField(); 
		patientForm1 = new FormPanel("Add Speciality", speciality); 
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
