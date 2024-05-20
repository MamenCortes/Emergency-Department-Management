package urgency.ui;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.List;

import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;
import urgency.db.pojos.Speciality;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;

public class AddDoctor extends FormTemplate {


	private static final long serialVersionUID = 150788289055975404L;
	private Application appMain; 
	private List<String> specialities; 
	private MyTextField email; 
	private MyTextField password; 

	public AddDoctor(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		this.titleText = "Modify Doctor"; 
		
		name = new MyTextField(); 
		surname = new MyTextField(); 
		speciality = new MyComboBox<String>(); 
		email = new MyTextField(); 
		password = new MyTextField(); 
		specialities = appMain.conMan.getSpecialityManager().getSpecialities(); 
		form1 = new FormPanel("Add New Doctor", name, surname, speciality, specialities, email, password); 
		initPatientForm(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			resetPanel(); 
			appMain.changeToManagerMenu();
		}else if(e.getSource() == applyChanges) {
			if(createDoctor()) {
				resetPanel(); 
				appMain.changeToManagerMenu(); 
			}
		}
	}
	
	@Override
	protected void resetPanel() {
		name.setText(null);
		surname.setText(null);
		speciality.setSelectedItem(null);
	}
	
	
	private Boolean createDoctor() {
		String name = this.name.getText(); 
		String surname = this.surname.getText(); 
		
		if(name.equals("")||surname.equals("")||speciality.getSelectedItem()==null){
			showErrorMessage("Please complete all fields");
			return false;
		}
		
		String specText = speciality.getSelectedItem().toString(); 
		Speciality patientSpec = new Speciality(specText); 
		//TODO Create UserName
		Doctor doctor = new Doctor(name, surname, patientSpec);
		appMain.conMan.getDocMan().addDoctor(doctor);
		System.out.println(doctor);
		return true; 
	}
	
}
