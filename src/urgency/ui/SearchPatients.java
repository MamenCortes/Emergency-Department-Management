package urgency.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import urgency.db.pojos.Patient;
import urgency.ui.components.SearchTemplate;

public class SearchPatients extends SearchTemplate {

	private static final long serialVersionUID = -2213334704230710767L;
	private Application appMain; 

	public SearchPatients(Application appMain) {
		initSearchTemplate();
		this.appMain = appMain; 
		showPatients(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) {
			errorMessage.setVisible(false);
			String input = idTextField.getText();  
			System.out.println(input);
			List<Patient> patients = appMain.patientMan.searchPatientsBySurname(input); 
			updatePatientDefModel(patients);
			if(patients.isEmpty()) {
				showErrorMessage("No patient found");
			}else {
				openFormButton.setVisible(true);
			}
			
		}else if(e.getSource() == openFormButton){
			Patient patient = patientList.getSelectedValue(); 
			if(patient == null) {
				showErrorMessage("No patient Selected");
			}else {
				resetPanel();
				appMain.changeToAdmitPatient(patient);
			}
		}else if(e.getSource() == cancelButton){
			resetPanel();
			appMain.changeToRecepcionistMenu();
		}
		
	}
	
	

}
