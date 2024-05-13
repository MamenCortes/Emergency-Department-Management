package urgency.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.DoctorBox;
import urgency.db.pojos.Speciality;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;

public class ModifyDoctor extends FormTemplate {

	private static final long serialVersionUID = 2614890498000000888L;
	private Application appMain; 
	private List<String> specialities; 
	private Doctor doctor; 
	private Box box; 

	public ModifyDoctor(DoctorBox doctor, Application appMain) {
		this.doctor = doctor.getDoctor(); 
		this.box = doctor.getBox(); 
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		this.titleText = "Modify Doctor"; 
		
		name = new MyTextField(); 
		surname = new MyTextField(); 
		speciality = new MyComboBox<String>(); 
		specialities = appMain.conMan.getSpecialityManager().getSpecialities(); 
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
			speciality.getModel().setSelectedItem(doctor.getSpeciality_type().getType()); 
			if(box != null) {
				boxes.getModel().setSelectedItem(box.getId());
			}else {
				boxes.getModel().setSelectedItem("None");
			}
			
			/*List<Box> boxesList = doctor.getBoxes(); 
			if(boxesList != null && !boxesList.isEmpty()) {
				boxes.getModel().setSelectedItem(boxesList.get(boxesList.size()-1).getId());
			}else {
				boxes.getModel().setSelectedItem("None");
			}*/
			boxes.setEnabled(false);
		}else {
			name.setText("Jane");
			surname.setText("Doe");
			speciality.setSelectedItem("None");
			//boxes.setSelectedItem(null);
			boxes.getModel().setSelectedItem("None");
			boxes.setEnabled(false);
		}

	}
	
	public void updatePanelWith(DoctorBox doctor) {
		this.doctor = doctor.getDoctor(); 
		this.box = doctor.getBox(); 
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
			if(modifyDoctor()) {
				resetPanel(); 
				appMain.changeToManagerMenu();
			} 
		}else if(e.getSource() == deleteButton) {
			System.out.println(doctor.getid());
			appMain.conMan.getDocMan().deleteDoctor(doctor.getid());
			resetPanel(); 
			appMain.changeToSearchDoctor();
		}
	}
	
	@Override
	protected void resetPanel() {
		name.setText(null); 
		surname.setText(null);
		speciality.setSelectedItem(null); 
		boxes.setSelectedItem(null);
		
	}
	
	private Boolean modifyDoctor() {
		String name = this.name.getText(); 
		String surname = this.surname.getText(); 
		
		if(name.equals("")||surname.equals("")||speciality.getSelectedItem()==null){
			showErrorMessage("Please complete all fields");
			return false;
		}
		
		String specText = speciality.getSelectedItem().toString(); 
		Speciality patientSpec = new Speciality(specText); 
		Doctor doctor = new Doctor(name, surname, patientSpec);
		appMain.conMan.getDocMan().updateDoctor(doctor);
		return true; 
	}
	
	

	

}
