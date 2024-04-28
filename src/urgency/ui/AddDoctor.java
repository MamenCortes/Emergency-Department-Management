package urgency.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;

public class AddDoctor extends FormTemplate {


	private static final long serialVersionUID = 150788289055975404L;
	private Application appMain; 
	private List<String> specialities; 

	public AddDoctor(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		this.titleText = "Add Doctor"; 
		
		name = new MyTextField(); 
		surname = new MyTextField(); 
		speciality = new MyComboBox<String>(); 
		specialities = appMain.specMan.getSpecialities(); 
		patientForm1 = new FormPanel("Add New Doctor", name, surname, speciality, specialities); 
		initPatientForm(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			appMain.changeToManagerMenu();
		}else if(e.getSource() == applyChanges) {
			appMain.changeToManagerMenu(); 
		}
	}
	
}
