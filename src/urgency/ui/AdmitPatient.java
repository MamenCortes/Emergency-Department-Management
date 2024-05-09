package urgency.ui;

import java.awt.event.ActionEvent;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;

import urgency.db.pojos.Patient;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;

public class AdmitPatient extends FormTemplate {

	private static final long serialVersionUID = -5666968669313018172L;
	private Patient patient; 
	private Application appMain; //In charge of managing events that require external actions

	public AdmitPatient(Application appMain) {
		this.appMain = appMain; 
		//initForm();
		
	}
	
	
	private void initForm() {
		this.option2Text = null; 
		this.option3Text = null; 
		
		this.titleText = "Admit Patient"; 
		name = new MyTextField(); 
		surname = new MyTextField(); 
		sex = new MyComboBox<String>(); 
		emergency = new MyComboBox<String>(); 
		birthDate = new JDateChooser(); //Date format yyyy-MM-dd

		
		form1 = new FormPanel("Admit Patient", name, surname, sex, birthDate, emergency); 
		initPatientForm(); 
		goBackButton.setText("BACK TO MENU");
		applyChanges.setText("ADMIT");
		errorMessage.setVisible(false);
	}
	
	private void showPatientData() {
		name.setText(patient.getName());
		surname.setText(patient.getSurname());
		if(patient.getSex().equals("Man")) {
			sex.setSelectedItem("Man");
		}else {
			sex.setSelectedItem("Woman");
		}
		this.birthDate.setDate(patient.getBirthDate());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			resetPanel(); 
			appMain.changeToRecepcionistMenu();
		}else if(e.getSource() == applyChanges) {
			if(updatePatient()) {
				resetPanel(); 
				appMain.changeToRecepcionistMenu();
			}
		}
	}
	
	@Override
	protected void resetPanel() {
		super.resetPanel();
		emergency.setSelectedItem(null);
		patient = null; 
	}
	
	public void updatePanelWith(Patient patient) {
		this.patient = patient; 
		initForm();
		showPatientData();
	}
	
	private Boolean updatePatient() {
		String name = this.name.getText(); 
		String surname = this.surname.getText(); 
		String sex = this.sex.getSelectedItem().toString();
		 
		if(name.equals("")||surname.equals("")||sex.equals("")||birthDate.getDate() == null){
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
		
		patient.setName(name);
		patient.setSurname(surname);
		patient.setSex(sex);
		patient.setAge(date);
		patient.setUrgency(emergency);
		patient.setStatus("waiting");
		
		appMain.conMan.getPatientMan().updatePatient(patient);;
		System.out.println(patient);
		return true; 
	}
	

	

}
