package urgency.ui;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.db.pojos.Patient;
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
			if(createPatient()) {
				resetPanel(); 
				appMain.changeToRecepcionistMenu();
			}
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
	
	private Boolean createPatient() {
		String name = this.name.getText(); 
		String surname = this.surname.getText(); 
		String sex = this.sex.getSelectedItem().toString();
		//System.out.println(sex);
		
		if(name.equals("")||surname.equals("")||sex.equals("...")||birthDate.getDate() == null||emergency.getSelectedItem()==null){
			showErrorMessage("Please complete all fields");
			return false;
		}
		
		Date date = new Date(birthDate.getDate().getTime());

		String emergencyString = this.emergency.getSelectedItem().toString();
		Integer emergency = 0;
		if (emergencyString.equals("Low")) {
			emergency = 1; 
		}else if(emergencyString.equals("High")){
			emergency = 5; 
		}else {
			showErrorMessage("Select the urgency");
			return false; 
		}
		Patient patient = new Patient(name, surname, "waiting", emergency, sex, date.toLocalDate());
		appMain.conMan.getPatientMan().addPatient(patient);
		System.out.println(patient);
		return true; 
	}

}
