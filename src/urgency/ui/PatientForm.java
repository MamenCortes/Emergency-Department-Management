package urgency.ui;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import urgency.db.pojos.Patient;
import urgency.db.pojos.Triage;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;


public class PatientForm extends FormTemplate{

	private static final long serialVersionUID = 185680662385014941L;
	private Triage triage; 
	private Application appMain; 
	private MyComboBox<Integer> emergencyCB; 
	private Patient patient; 
	private MyTextField comments;
	private MyTextField weight; 
	private MyTextField height;
	private MyComboBox<String> nextStep;

	public PatientForm(Application appMain) {
		this.appMain = appMain; 
	}

	public void patientNurseForm(Patient Patient) {
		this.patient = Patient; 
		this.option3Text = null; 
		this.titleText = "Patient information"; 
		initGeneralInfoPanel();
		initPhysiologicalDataPanel();
		initPatientForm(); 
		
	}

	public void patientDoctorForm(Patient Patient) {
		this.patient = Patient;
		this.titleText = "Patient information"; 
		
		initGeneralInfoPanel();
		initPhysiologicalDataPanel();
		
		weight.setText(Float.toString(patient.getWeight()));
		weight.setEnabled(false);
		height.setText(Float.toString(patient.getHeight()));
		height.setEnabled(false);
		emergencyCB.getModel().setSelectedItem(patient.getUrgency());
		emergencyCB.setEnabled(false);
		
		comments = new MyTextField();
		nextStep = new MyComboBox<String>(); 
		nextStep.addItem("Discharge"); 
		nextStep.addItem("Hospitalize"); 

		form3 = new FormPanel("Comments", comments, nextStep); 
		initPatientForm(); 
		
	}
	
	private void initGeneralInfoPanel() {
		name = new MyTextField(); 
		name.setText(patient.getName());
		name.setEnabled(false);
		
		
		surname = new MyTextField(); 
		surname.setText(patient.getSurname());
		surname.setEnabled(false);
		
		
		sex = new MyComboBox<String>(); 
		sex.getModel().setSelectedItem(patient.getSex());
		sex.setEnabled(false);
		
		birthDate = new JDateChooser(); //Date format yyyy-MM-dd
		birthDate.setDate(patient.getBirthDate());
		birthDate.setEnabled(false);
		
		
		form1 = new FormPanel("General Info", name, surname, sex, birthDate); 
	}
	
	private void initPhysiologicalDataPanel() {
		comments = new MyTextField(); 
		weight = new MyTextField(); 
		height = new MyTextField(); 
		emergencyCB = new MyComboBox<Integer>(); 
		form2 = new FormPanel("Physiological Info", weight, height, emergencyCB); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			if(form3 == null) {
				appMain.changeToNurseView();
			}else {
				appMain.changeToDoctorView();
			}
			resetPanel();
			
		}
	}
	
	@Override
	protected void resetPanel() {
		super.resetPanel();
		form1 = null; 
		form2 = null; 
		form3 = null; 
		patient = null; 
	}
	

	
	

}
