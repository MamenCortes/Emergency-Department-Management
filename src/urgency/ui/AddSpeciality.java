package urgency.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import urgency.db.pojos.Box;
import urgency.db.pojos.Speciality;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;

public class AddSpeciality extends FormTemplate {

	private static final long serialVersionUID = -6615615275946952805L;
	private Application appMain; 
	private MyTextField specialityTextField; 
	private Speciality spec; 

	public AddSpeciality(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain;
		this.titleText += "Add Speciality"; 
		

		specialityTextField = new MyTextField(); 
		form1 = new FormPanel("Add Speciality", specialityTextField); 
		initPatientForm(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			resetPanel(); 
			appMain.changeToManagerMenu();
		}else if(e.getSource() == applyChanges) {
			String specType = specialityTextField.getText();
			if(!specType.equals("")) {
				appMain.conMan.getSpecialityManager().addSpeciality(new Speciality(specType));
				resetPanel(); 
				appMain.changeToManagerMenu(); 
			}
			else {
				showErrorMessage("Insert a Speciality Type");
			}
		}
	}
	
	@Override
	protected void resetPanel() {
		specialityTextField.setText(null);
		errorMessage.setVisible(false);
	}

}
