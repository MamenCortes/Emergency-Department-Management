package urgency.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;

public class ModifyDoctor extends FormTemplate {

	private static final long serialVersionUID = 2614890498000000888L;
	private Application appMain; 
	private List<String> specialities; 
	private Doctor doctor; 

	public ModifyDoctor(Doctor doctor, Application appMain) {
		this.doctor = doctor; 
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		this.titleText = "Modify Doctor"; 
		
		name = new MyTextField(); 
		surname = new MyTextField(); 
		speciality = new MyComboBox<String>(); 
		specialities = appMain.conMan.getSpecialityManager().getSpecialities(); 
		//TODO Add box ComboBox
		List<Integer> boxesList = new ArrayList<Integer>(); 
		for (int i = 0; i<10; i++) {
			boxesList.add(i); 
		}
		boxes = new MyComboBox<Integer>(); 
		
		form1 = new FormPanel("Modify Existant Doctor", name, surname, speciality, specialities, boxes, boxesList); 
		initPatientForm(); 
		showDoctorData();
		deleteButton.setVisible(true);
	}
	
	private void showDoctorData() {
		if(doctor != null) {
			name.setText(doctor.getName());
			surname.setText(doctor.getSurname());
			speciality.getModel().setSelectedItem(doctor.getSpeciality_type());
			List<Box> boxesList = doctor.getBoxes(); 
			if(!boxesList.isEmpty()) {
				boxes.getModel().setSelectedItem(boxesList.get(boxesList.size()-1).getId());
			}
		}else {
			name.setText("Jane");
			surname.setText("Doe");
			speciality.setSelectedItem("None");
			boxes.setSelectedItem(1);
		}

	}
	
	public void updatePanelWith(Doctor doctor) {
		this.doctor = doctor; 
		showDoctorData();
		errorMessage.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			resetPanel(); 
			appMain.changeToManagerMenu();
		}else if(e.getSource() == applyChanges) {
			resetPanel(); 
			//TODO implement methods to modify doctor
			appMain.changeToManagerMenu(); 
		}else if(e.getSource() == deleteButton) {
			appMain.conMan.getDocMan().deleteDoctor(doctor.getid());
		}
	}
	
	@Override
	protected void resetPanel() {
		name.setText(null); 
		surname.setText(null);
		speciality.setSelectedItem(null); 
		boxes.setSelectedItem(null);
		
	}
	
	

	

}
