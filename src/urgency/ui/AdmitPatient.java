package urgency.ui;

import java.awt.event.ActionEvent;

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

	public AdmitPatient(Patient patient, Application appMain) {
		this.patient = patient; 
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		
		this.titleText = "Admit Patient"; 
		name = new MyTextField(); 
		surname = new MyTextField(); 
		sex = new MyComboBox<String>(); 
		emergency = new MyComboBox<String>(); 
		birthDate = new JDateChooser(); //Date format yyyy-MM-dd
		
		patientForm1 = new FormPanel("Admit Patient", name, surname, sex, birthDate, emergency); 
		initPatientForm(); 
		showPatientData(); 
		applyChanges.setText("ADMIT");
		
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
			appMain.changeToRecepcionistMenu();
		}else if(e.getSource() == applyChanges) {
			appMain.changeToRecepcionistMenu();
		}
	}
	

	

}
