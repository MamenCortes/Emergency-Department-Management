package urgency.ui;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;

public class AddPatient extends FormTemplate {

	private static final long serialVersionUID = 7053856282647622743L;
	private Application appMain; 

	public AddPatient(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		
		name = new MyTextField(); 
		surname = new MyTextField(); 
		sex = new MyComboBox<String>(); 
		emergency = new MyComboBox<String>(); 
		birthDate = new JDateChooser(); //Date format yyyy-MM-dd
		
		form1 = new FormPanel("Add New Patient", name, surname, sex, birthDate, emergency); 
		initPatientForm(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			resetPanel(); 
			appMain.changeToRecepcionistMenu();
		}else if(e.getSource() == applyChanges) {
			resetPanel(); 
			appMain.changeToRecepcionistMenu();
		}
	}
	
	@Override
	protected void resetPanel() {
		name.setText(null);
		surname.setText(null);
		sex.setSelectedIndex(0);
		emergency.setSelectedIndex(0);
		birthDate.setDate(null);
	}

}
