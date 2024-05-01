package urgency.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JLabel;

import urgency.db.pojos.*;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;

public class AddRoom extends FormTemplate {

	private static final long serialVersionUID = -3380973585111621415L;
	private Application appMain; 
	private List<String> specialities; 
	private MyComboBox<String> roomType; 
	private Box box; 
	private Triage triage; 
	private boolean isBoxToAdd = false; 

	public AddRoom(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		
		speciality = new MyComboBox<String>(); 
		specialities = appMain.conMan.getSpecialityManager().getSpecialities(); 
		this.titleText += "Add Room"; 
		
		roomType = new MyComboBox<String>(); 
		roomType.addActionListener(this);
		
		form1 = new FormPanel("Add Room", roomType, speciality, specialities); 
		initPatientForm(); 

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			createRoom(); 
			appMain.changeToManagerMenu();
			resetPanel();
		}else if(e.getSource() == applyChanges) {
			createRoom(); 
			resetPanel();
			appMain.changeToManagerMenu(); 
			//showErrorMessage(errorMessage); 
		}else if(e.getSource() == roomType) {
			String type = ""; 
			if(roomType.getSelectedItem() != null) {
				 type = roomType.getSelectedItem().toString();
			}	
			if (type.equals("Triage")) {
				speciality.setEnabled(false);
				isBoxToAdd = false; 
			}else {
				speciality.setEnabled(true);
				isBoxToAdd = true; 
			}
		}
	}
	
	@Override 
	protected void resetPanel() {
		speciality.setSelectedItem(null);
		roomType.setSelectedItem(null);
		deleteButton.setVisible(false);
		box = null; 
		triage = null; 
	}
	
	private void createRoom() {
		if(isBoxToAdd) {
			String specialityType = this.speciality.getSelectedItem().toString();
			Speciality speciality = new Speciality(specialityType);
			Box box = new Box(true, speciality);
			appMain.conMan.getBoxManager().addBox(box);
		}else {
			Triage triage = new Triage(true);
			appMain.conMan.getTriageManager().addTriage(triage);
		}
		
		
	}
	

	
	
	
}
