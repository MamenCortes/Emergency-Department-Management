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
		
		form1 = new FormPanel("Admit Patient", name, surname, sex, birthDate, emergency); 
		initPatientForm(); 
		showPatientData(); 
		applyChanges.setText("ADMIT");
		
	}
	
	public AdmitPatient(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		
		this.titleText = "Admit Patient"; 
		name = new MyTextField(); 
		surname = new MyTextField(); 
		sex = new MyComboBox<String>(); 
		emergency = new MyComboBox<String>(); 
		birthDate = new JDateChooser(); //Date format yyyy-MM-dd
		
		form1 = new FormPanel("Admit Patient", name, surname, sex, birthDate, emergency); 
		initPatientForm(); 
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
	}
	
	public void updatePanelWith(Patient patient) {
		this.patient = patient; 
		showPatientData();
		errorMessage.setVisible(false);
	}
	
	private Boolean updatePatient() {
		String name = this.name.getText(); 
		String surname = this.surname.getText(); 
		String sex = this.sex.getSelectedItem().toString();
		 
		if(name.equals("")||surname.equals("")||sex.equals("")||birthDate.getDate() == null||emergency.getSelectedItem()==null){
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
		}
		Integer id = patient.getId();
		System.out.println(patient.getId());
		Patient patient = new Patient(id, name, surname, "waiting", emergency, sex, date);
		appMain.conMan.getPatientMan().updatePatient(patient);;
		System.out.println(patient);
		return true; 
	}
	

	

}
