package urgency.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JLabel;

import urgency.db.pojos.Box;
import urgency.db.pojos.Triage;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;

public class ModifyRoom extends FormTemplate {
	
	private static final long serialVersionUID = -6510483263477205488L;
	private Application appMain; 
	private List<String> specialities; 
	private MyComboBox<String> roomType; 
	private Box box; 
	private Triage triage; 
	private JLabel idText; 


	public ModifyRoom(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		
		speciality = new MyComboBox<String>(); 
		specialities = appMain.conMan.getSpecialityManager().getSpecialities(); 
		this.titleText += "Modify Room"; 
		
		roomType = new MyComboBox<String>(); 
		roomType.addActionListener(this);
		
		form1 = new FormPanel("Modify Room", roomType, speciality, specialities); 
		initPatientForm(); 
		
		idText = new JLabel("ID: "); 
	    idText.setFont(new Font("sansserif", 1, 12));
	    idText.setForeground(new Color(24, 116, 67));
	    form1.add(idText, "grow,center, span");
	    deleteButton.setVisible(true); 
	}
	
	public void showRoomData(Box box) {
		this.box = box; 
		String boxSpeciality = box.getSpeciality().getType(); 
		speciality.getModel().setSelectedItem(boxSpeciality);
		roomType.setSelectedItem("Box");
		roomType.setEnabled(false);
		
		idText.setText("ID: "+box.getId()); 

	}
	
	
	public void showRoomData(Triage triage) {
		this.triage = triage; 
		speciality.setEnabled(false); 
		speciality.setSelectedItem(null);
		appMain.changeToManagerMenu(); 
		roomType.setSelectedItem("Triage");
		roomType.setEnabled(false);
		idText.setText("ID: "+triage.getId()); 
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			appMain.changeToSearchRoom();
			resetPanel();
		}else if(e.getSource() == applyChanges) {
			updateBox();
			resetPanel();
			appMain.changeToManagerMenu(); 
			//showErrorMessage(errorMessage); 
		}else if(e.getSource() == roomType) {
			String type = ""; 
			if(roomType.getSelectedItem() != null) {
				 type = roomType.getSelectedItem().toString();
			}	
			if (type.equals("Triage") || type.equals("Emergency Room")) {
				speciality.setEnabled(false);
			}else {
				speciality.setEnabled(true);
			}
		}else if(e.getSource() == deleteButton) {
			if(box != null) {
				appMain.conMan.getBoxManager().deleteBox(box.getId());
			}else if(triage != null) {
				appMain.conMan.getTriageManager().deleteTriage(triage.getId());
			}
			appMain.changeToManagerMenu();
		}
	}
	
	@Override 
	protected void resetPanel() {
		speciality.setSelectedItem(null);
		roomType.setSelectedItem(null);
		box = null; 
		triage = null; 
	}
	
	private void updateBox() {
		if(box != null) {
			box.setSpeciality(speciality.getModel().getSelectedItem().toString());
			appMain.conMan.getBoxManager().updateBox(box);
		}
	}

	
	

}
